package org.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFileReader {
    public FileInputStream fileInputStream;
    public FileOutputStream fileOutputStream;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    String path;

    public ExcelFileReader(String filePath) {
        this.path = filePath;
    }

    //define method for getting row count and sheet row needs to be known
    public int getRowCount(String sheetName) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fileInputStream.close();

        return rowCount;
    }

    //define a method for getting column count on a sheet excel sheet
    public int getColumnCount(String sheetName, int rowNumber) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNumber);
        int colCount = row.getLastCellNum();
        workbook.close();
        fileInputStream.close();


        return colCount;
    }

    public String getCellData(String sheetName, int rowNumber, int colNumber) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNumber);
        cell = row.getCell(colNumber);

        //handle null or empty data cell in the excel file with DataFormatter
        DataFormatter df = new DataFormatter();
        String data;
        try {
            data = df.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }


        return data;
    }
}
