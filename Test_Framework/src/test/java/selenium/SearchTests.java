package selenium;

import dataproviders.SearchProvider;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pojo.SearchData;

public class SearchTests extends BaseClass {

    static Logger logger = Logger.getLogger(SearchTests.class);

    @Test(description = "This test searches on the website",
            dataProvider = "getSearchDataFromJson",
            dataProviderClass = SearchProvider.class)
    public void SearchTest(SearchData testSearchData){

        logger.info("SEARCH TEST");

        headerPage().search(testSearchData.getSearchCriteria());

        if (testSearchData.getExpectedResults() > 0) {

            int results = searchResultsPage().getThumbsCount();
            Assert.assertEquals(results, testSearchData.getExpectedResults(),
                    String.format("Was expecting %s, but got %s.", testSearchData.getExpectedResults(), results));
        }
        else
            Assert.assertTrue(searchResultsPage().isNoResultsVisible(), "Error message was not displayed");
    }
}