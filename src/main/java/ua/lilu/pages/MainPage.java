package ua.lilu.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ua.lilu.model.News;
import ua.lilu.pages.fragments.LoginFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lilu on 18.12.2016.
 */
public class MainPage extends AbstractPage {

    @FindBy(xpath = ".//*[@class='content_body']/h1")
    private List<WebElement> newsHeaders;

    @FindBy(xpath = ".//*[@class='content_body']/p/img")
    private List<WebElement> newsImages;

    @FindBy(xpath = ".//*[@class='content_body']/p[not(descendant::img) and string-length(text()) > 0]")
    private List<WebElement> newsContents;

    @FindBy(xpath = ".//*[@class='a_full_news']")
    private List<WebElement> newsLinks;

    @FindBy(className = "content_body")
    private WebElement newsContent;

    private LoginFragment loginFragment;

    public MainPage(final WebDriver driver) {
        super(driver);
        loginFragment = new LoginFragment(driver);
    }

    public void open() {
        driver.get(URL);
    }

    public void login(String login, String password) {
        loginFragment.login(login, password);
    }

    public void logout() {
        loginFragment.logout();
    }

    public boolean isUserLoggedIn() {
        return loginFragment.containsLogoutButton() && loginFragment.containsProfileButton() && !loginFragment.containsLoginButton();
    }

    public List<News> getLatestNewsList() {
        List<News> news = new ArrayList<News>();
        for (int i = 0; i < newsLinks.size(); i++) {
            News item = new News();
            item.setHeader(newsHeaders.get(i).getText());
            item.setContentHTML(newsContents.get(i).getAttribute("innerHTML"));
            item.setImgLink(newsImages.get(i).getAttribute("src"));
            item.setLink(newsLinks.get(i).getAttribute("href"));
            news.add(item);
        }

        return news;
    }

    public String getLatestNewsHTML() {
        StringBuilder message = new StringBuilder();
        for (News item : getLatestNewsList()) {
            message.append("<h1>").append(item.getHeader()).append("</h1>");
            message.append(item.getContentHTML());
            message.append("<img src=\"").append(item.getImgLink()).append("\" />");
            message.append("<a href='").append(item.getLink()).append("'>Подробнее</a>");
        }
        return message.toString();
    }

    public void closeBrowser() {
        driver.quit();
    }

}
