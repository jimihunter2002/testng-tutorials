package org.test;

import org.testng.annotations.Test;

public class GroupOneTest {

    @Test(groups = "smoke")
    public void checkNewFeature() {
        System.out.println("this is from the new feature check: smoke GroupOne Class");
    }

    @Test(groups = "regression")
    public void checkNewCustomer() {
        System.out.println("this is from the customer check: regression GroupOne Class");
    }
}
