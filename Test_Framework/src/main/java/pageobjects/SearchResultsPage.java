package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage extends BasePage{

    static Logger logger = Logger.getLogger(SearchResultsPage.class);

    private static final String ERROR_WHEN_THERE_IS_NO_RESULTS = "There is no product that matches the search criteria.";

    private By resultThumbs = By.cssSelector(".product-thumb");

    public SearchResultsPage(WebDriver _driver){
        super(_driver);
    }

    public int getThumbsCount(){
        Integer result = driver.findElements(resultThumbs).size();
        logger.info(String.format("Getting thumbs count: %s", result));
        return result;
    }

    public boolean isNoResultsVisible(){
        WebElement contentElement = driver.findElement(By.id("content"));
        return contentElement.getAttribute("innerHTML").contains(ERROR_WHEN_THERE_IS_NO_RESULTS);
    }


}
