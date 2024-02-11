package com.vthakur.tests.vendorportal;

import com.vthakur.pages.vendorportal.DashboardPage;
import com.vthakur.pages.vendorportal.LoginPage;
import com.vthakur.tests.AbstractTest;
import com.vthakur.tests.vendorportal.model.VendorPortalTestData;
import com.vthakur.util.Config;
import com.vthakur.util.Constants;
import com.vthakur.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath){
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
    }
    @Test(dependsOnMethods ="loginTest" )
    public void dashboardTest(){
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isAt());

        //finance metrics display
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());

        //oder history
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

}
