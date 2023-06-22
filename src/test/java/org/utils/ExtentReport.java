package org.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import freemarker.template.SimpleDate;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.*;

public class ExtentReport extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports xReport;
    public ExtentTest xTest;

    public void onStart(ITestContext testContext) {

        String datestamp = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());

        String reportName = "./Reports/" + "ExtentReport" +  datestamp + ".html";


        htmlReporter = new ExtentHtmlReporter(reportName);
        htmlReporter.config().setReportName("Automation Execution Report");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setAutoCreateRelativePathMedia(true);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");


        xReport = new ExtentReports();
        xReport.attachReporter(htmlReporter);
        xReport.setSystemInfo("System OS", System.getProperty("os.name"));
        xReport.setSystemInfo("Executioner", System.getProperty("user.name"));

    }

    public void onFinish(ITestContext testContext) {
        xReport.flush();
    }

    public void onTestSuccess(ITestResult tr) {
        xTest = xReport.createTest(tr.getName());
        xTest.log(Status.PASS, "This test is passed");
        xTest.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
    }

    public void onTestFailure(ITestResult tr) {
        xTest = xReport.createTest(tr.getName());
        xTest.log(Status.FAIL, "This test is failed");
        xTest.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
        xTest.log(Status.FAIL, tr.getThrowable());

        try {
            String shotPath = getProperty("user.dir") + "./screenshots/" + tr.getName()+".png";
            xTest.addScreenCaptureFromPath(shotPath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void onTestSkipped(ITestResult tr) {
        xTest = xReport.createTest(tr.getName());
        xTest.log(Status.SKIP, "This test is skipped");
        xTest.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREY));
        xTest.log(Status.SKIP, tr.getThrowable());
    }

}

