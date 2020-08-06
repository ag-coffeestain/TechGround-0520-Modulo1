package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderPage extends BasePage{

    static Logger logger = Logger.getLogger(HeaderPage.class);


//   elementos
    private By myAccountLinkLocator = By.xpath("//span[text()='My Account']");
    private By loginLocator = By.linkText("Login");
    private By logoutLocator = By.xpath("//a[text()='Logout']");
    private By searchField = By.name("search");


    public HeaderPage(WebDriver _driver){
        super(_driver);
    }

    public void clickOnMyAccount(){
        driver.findElement(myAccountLinkLocator).click();
    }

    public void clickOnLogin(){
        driver.findElement(loginLocator).click();
    }

    public void goToLogin(){
        this.clickOnMyAccount();
        this.clickOnLogin();
    }

    public boolean isLogoutEnabled(){
        return driver.findElement(logoutLocator).isEnabled();
    }

    // segundo metodo
    public WebElement logoutLink(){
        return driver.findElement(logoutLocator);
    }

    public boolean isLogoutLinkEnabled(){
        this.clickOnMyAccount();
        return this.isLogoutEnabled();
    }

    public void search(String searchCriteria){

        logger.info(String.format("Searching for %s.", searchCriteria ));

        WebElement searchBar = driver.findElement(searchField);

        searchBar.clear();
        searchBar.sendKeys(searchCriteria, Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.urlContains(String.format("search=%s", searchCriteria)));
    }

}
