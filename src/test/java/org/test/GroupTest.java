package org.test;

import org.testng.annotations.Test;

public class GroupTest {

    @Test(groups = "smoke")
    public void loginPageCheck() {
        System.out.println("this is to check login page display: smoke Group Class");
    }

    @Test(groups = "smoke")
    public void homePageTitle() {
        System.out.println("this is to check home page title: smoke Group Class");
    }

    @Test(groups = "regression")
    public void fundTransfer() {
        System.out.println("this is to check fund transfer: regression Group Class");
    }

    @Test(groups = "regression2")
    public void userProfileChange() {
        System.out.println("this is test to check user profile change: regression2 Group Class");
    }
}
