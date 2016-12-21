package ua.lilu.pages.fragments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Lilu on 18.12.2016.
 */
public class AbstractFragment {
    protected WebDriver driver;

    public AbstractFragment(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
