package ua.lilu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ua.lilu.pages.fragments.BrandNewSerialsFragment;
import ua.lilu.pages.fragments.LoginFragment;

/**
 * Created by Lilu on 20.12.2016.
 */
public class SerialCommentsPage extends AbstractPage {

    @FindBy(id = "FormComment")
    private WebElement commentInput;

    @FindBy(xpath = ".//*[@class='a_submit']")
    private WebElement sendButton;

    @FindBy(xpath = ".//*[@id='rate_block']/b")
    private WebElement actualRate;

    @FindBy(xpath = ".//a[@class='d_pages_link'][last()]")
    private WebElement lastPageLink;

    private BrandNewSerialsFragment brandNewSerialsFragment;

    public SerialCommentsPage(final WebDriver driver) {
        super(driver);
        brandNewSerialsFragment = new BrandNewSerialsFragment(driver);
    }

    public void commentNewestSerial(String comment) {
        brandNewSerialsFragment.clickFirstCommentButton();
        commentInput.sendKeys(comment);
        sendButton.click();
    }

    public void rateNewestSerial(int rate) {
        brandNewSerialsFragment.clickFirstCommentButton();
        WebElement rating = driver.findElement(By.id("ri_" + rate));
        rating.click();
    }

    public int getActualRate() {
        return Integer.valueOf(actualRate.getText());
    }

    public void clickOnLastPageLink() {
        try {
            lastPageLink.click();
        } catch (NoSuchElementException e) {
            //no link when there is one page
        }
    }

    public boolean containsUserComment(String comment) {
        WebElement commentBlock = driver.findElement(By.xpath(".//*[@class='username' and text()='" + LoginFragment.LOGIN + "']/parent::div/parent::td"));
        return commentBlock.getText().contains(comment);
    }

    public void closeBrowser() {
        driver.quit();
    }
}
