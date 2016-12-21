package ua.lilu.tests;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ua.lilu.EmailSender;
import ua.lilu.WebDriverContainer;
import ua.lilu.pages.MainPage;
import ua.lilu.pages.SerialsPage;
import ua.lilu.pages.fragments.LoginFragment;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Lilu on 20.12.2016.
 */
public class SerialsTests {
    private WebDriverContainer driverContainer;
    private MainPage mainPage;
    private SerialsPage serialsPage;
    private Logger logger;


    @BeforeTest
    public void beforeTest() {
        logger = Logger.getLogger(SerialsTests.class);
        driverContainer = new WebDriverContainer();
        mainPage = new MainPage(driverContainer.getDriver());
        serialsPage = new SerialsPage(driverContainer.getDriver());
        mainPage.open();
        mainPage.login(LoginFragment.LOGIN, LoginFragment.PASSWORD);
    }

    @Test
    public void sendNewsTest() {
        logger.info("start send news test");
        String email = ResourceBundle.getBundle("application").getString("email.username") + ResourceBundle.getBundle("application").getString("email.domain");
        String password = ResourceBundle.getBundle("application").getString("email.password");
        String subject = "Lostfilm news";
        String content = mainPage.getLatestNewsHTML();
        logger.debug("checking content is not empty");
        Assert.assertTrue(content != null && content.length() != 0);
        EmailSender sender = new EmailSender(email, password);
        sender.send(subject, content, email, email);
        logger.debug("news were sent to " + email);
        logger.info("end send news test");
    }

    @Test
    public void saveSerialByGenreTest() {
        logger.info("start save serial by genre test");
        serialsPage.open();
        try {
            String genre = "комедия";
            String link = serialsPage.getRandomSerialLink(genre);
            logger.debug("end of searching serial by genre " + genre);
            String path = "src/main/resources/test.txt";
            FileUtils.writeStringToFile(new File(path), link);
            logger.debug("link to serial saved in " + path);
        } catch (IOException e) {
            logger.error(e.getMessage());
            Assert.fail();
        }
        logger.info("end save serial by genre test");
    }

    @AfterTest
    public void afterTest() {
        serialsPage.closeBrowser();
    }
}
