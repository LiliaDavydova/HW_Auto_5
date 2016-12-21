package ua.lilu.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Lilu on 18.12.2016.
 */
public abstract class AbstractPage {
    protected final String URL = "http://www.lostfilm.tv/";
    protected WebDriver driver;

    public AbstractPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
