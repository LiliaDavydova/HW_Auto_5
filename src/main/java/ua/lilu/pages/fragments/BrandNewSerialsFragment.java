package ua.lilu.pages.fragments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Lilu on 20.12.2016.
 */
public class BrandNewSerialsFragment extends AbstractFragment {
    @FindBy(xpath = ".//*[@id='new_sd_list']/a[@class='a_discuss'][1]")
    private WebElement firstCommentButton;

    public BrandNewSerialsFragment(final WebDriver driver) {
        super(driver);
    }

    public void clickFirstCommentButton() {
        firstCommentButton.click();
    }
}