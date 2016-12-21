package ua.lilu.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ua.lilu.WebDriverContainer;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Lilu on 18.12.2016.
 */
public class SerialsPage extends AbstractPage {

    @FindBy(xpath = ".//div[@class='mid']/div[@class='bb']/a")
    private List<WebElement> serials;

    @FindBy(xpath = ".//a[text()='Сериалы']")
    private WebElement serialsButton;

    public SerialsPage(final WebDriver driver) {
        super(driver);
    }

    public void open() {
        serialsButton.click();
    }

    public WebElement findRandomSerialByGenre(String genre) {

        SerialDetailsPage serialDetailsPage = new SerialDetailsPage(new WebDriverContainer().getDriver());
        long seed = System.nanoTime();
        Collections.shuffle(serials, new Random(seed));
        for (WebElement serial : serials) {
            if (serialDetailsPage.containsGenre(serial.getAttribute("href"), genre)) {
                return serial;
            }
        }
        return null;
    }

    public String getRandomSerialLink(String genre) {
        WebElement serial = findRandomSerialByGenre(genre);
        return serial.getText() + ": " + serial.getAttribute("href");
    }

    public void closeBrowser() {
        driver.quit();
    }
}
