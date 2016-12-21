package ua.lilu.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ua.lilu.WebDriverContainer;
import ua.lilu.pages.MainPage;
import ua.lilu.pages.SerialCommentsPage;
import ua.lilu.pages.fragments.LoginFragment;

/**
 * Created by Lilu on 20.12.2016.
 */
public class SerialCommentsTests {
    private WebDriverContainer driverContainer;
    private MainPage mainPage;
    private SerialCommentsPage serialCommentsPage;
    private Logger logger;


    @BeforeTest
    public void beforeTest() {
        logger = Logger.getLogger(SerialCommentsTests.class);
        driverContainer = new WebDriverContainer();
        mainPage = new MainPage(driverContainer.getDriver());
        serialCommentsPage = new SerialCommentsPage(driverContainer.getDriver());
        mainPage.open();
        mainPage.login(LoginFragment.LOGIN, LoginFragment.PASSWORD);
    }

    @Test
    public void commentTest() {
        logger.info("start positive comment test");
        String comment = "Спасибо!";
        serialCommentsPage.commentNewestSerial(comment);
        logger.debug("set comment to: " + comment);
        serialCommentsPage.clickOnLastPageLink();
        logger.debug("clicked send button");
        logger.debug("checking the results");
        Assert.assertTrue(serialCommentsPage.containsUserComment(comment));
        logger.info("end positive comment test");
    }

    @Test
    public void ratingTest() {
        logger.info("start positive rating test");
        int rate = 9;
        serialCommentsPage.rateNewestSerial(rate);
        logger.debug("set rate to: " + rate);
        serialCommentsPage.clickOnLastPageLink();
        logger.debug("rate was selected");
        logger.debug("checking the results");
        int actualRate = serialCommentsPage.getActualRate();
        Assert.assertEquals(actualRate, rate, "Rate was NOT set");
        logger.info("end positive rating test");
    }

    @AfterTest
    public void afterTest() {
        serialCommentsPage.closeBrowser();
    }
}
