package ua.lilu.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import ua.lilu.WebDriverContainer;
import ua.lilu.pages.MainPage;
import ua.lilu.pages.fragments.LoginFragment;


/**
 * Created by Lilu on 18.12.2016.
 */
public class LoginTests {
    private WebDriverContainer driverContainer;
    private MainPage mainPage;
    private Logger logger;

    @BeforeTest
    public void beforeTest() {
        logger = Logger.getLogger(LoginTests.class);
        driverContainer = new WebDriverContainer();
        mainPage = new MainPage(driverContainer.getDriver());
    }

    @BeforeMethod
    public void beforeMethod() {
        mainPage.open();
    }

    @DataProvider
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {"karazin_hw", "test1234"},
                {"test", "qwer1234"}
        };
    }

    @Test(priority = 1, dataProvider = "invalidLoginData")
    public void loginNegativeTest(String login, String password) {
        logger.info("start negative login test");
        mainPage.login(login, password);
        logger.debug("checking the results");
        Assert.assertFalse(mainPage.isUserLoggedIn());
        logger.info("end negative login test");
    }

    @Test(priority = 2)
    public void loginPositiveTest() {
        logger.info("start positive login test");
        mainPage.login(LoginFragment.LOGIN, LoginFragment.PASSWORD);
        logger.debug("checking the results");
        Assert.assertTrue(mainPage.isUserLoggedIn());
        logger.info("end positive login test");
    }

    @Test(priority = 3, dependsOnMethods = "loginPositiveTest")
    public void logoutPositiveTest() {
        logger.info("start positive logout test");
        mainPage.logout();
        logger.debug("checking the results");
        Assert.assertFalse(mainPage.isUserLoggedIn());
        logger.info("end positive logout test");
    }

    @AfterTest
    public void afterTest() {
        mainPage.closeBrowser();
    }
}
