package ua.lilu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lilu on 18.12.2016.
 */
public class WebDriverContainer {
    protected WebDriver driver;

    public WebDriver getDriver() {
        if (driver == null) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }
        return driver;
    }
}
