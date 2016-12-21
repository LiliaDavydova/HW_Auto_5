package ua.lilu.pages;

import org.apache.commons.lang3.text.WordUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Lilu on 18.12.2016.
 */
public class SerialDetailsPage extends AbstractPage {
    public SerialDetailsPage(final WebDriver driver) {
        super(driver);
    }

    public boolean containsGenre(String url, String genre) {
        driver.get(url);
        try {
            WebElement genreElement = driver.findElement(By.xpath(".//div[@class='mid']/div/span[contains(text(), '" + genre.toLowerCase() + "') or contains(text(), '" + WordUtils.capitalize(genre) + "')]"));
            return genreElement != null;
        } catch (Exception e) {
            return false;
        }
    }
}
