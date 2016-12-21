package ua.lilu.pages.fragments;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ResourceBundle;

/**
 * Created by Lilu on 18.12.2016.
 */
public class LoginFragment extends AbstractFragment {
    public static final String LOGIN = ResourceBundle.getBundle("application").getString("login");
    public static final String PASSWORD = ResourceBundle.getBundle("application").getString("password");

    @FindBy(name = "login")
    private WebElement loginInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = ".//*[@type='submit' and @value=' Войти ']")
    private WebElement loginButton;

    @FindBy(xpath = ".//*[@class='user_menu_link' and text()='Выйти']")
    private WebElement logoutButton;

    @FindBy(xpath = ".//*[@class='user_menu_link' and text()='Профиль']")
    private WebElement profileButton;


    public LoginFragment(final WebDriver driver) {
        super(driver);
    }

    public void login(String login, String password) {
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public void logout() {
        logoutButton.click();
    }

    public boolean containsLoginButton() {
        try {
            return loginButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean containsLogoutButton() {
        try {
            return logoutButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean containsProfileButton() {
        try {
            return profileButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
