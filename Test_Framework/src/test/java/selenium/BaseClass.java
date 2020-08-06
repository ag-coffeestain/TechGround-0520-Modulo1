package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class BaseClass extends PageObjectHandler{

    public static Logger logger =  Logger.getLogger(BaseClass.class);

    @BeforeTest
    public void setupTest(){
        logger.debug("Start of test execution");
    }

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setupMethod(@Optional("chrome") String browser, ITestContext context, ITestResult result) throws MalformedURLException {

        logger.info(String.format("Test has started: %s - %s",
                result.getMethod().getMethodName(),
                result.getMethod().getDescription()));

        setInitialConfiguration(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result){

        if (!result.isSuccess()){
            logger.error(String.format("Test Failed. Reason: %s" ,result.getThrowable().getMessage()));
            logger.error(result.getThrowable().getStackTrace().toString());
        }

//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        getException().printStackTrace(pw);
//
//        toReturn += String.format("\nFailure Reason: %s ...", getException().toString());
//        toReturn += String.format("\nStack Trace: %s ...", sw.toString());

        logger.info(String.format("Finishing test. Collecting data: %s",
                result.getMethod().getMethodName()));

        pagesCleanUp();

        TakeScreenshot(driver);

        try {
            UploadLogs();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.close();

        try {
            driver.quit();
        }
        catch (WebDriverException ex){
            logger.warn("Driver was attempted to closed but it was already closed");
        }

        logger.info("Test has completed");
    }

    private void setInitialConfiguration(String browser) throws MalformedURLException {

//        DesiredCapabilities cap = DesiredCapabilities.chrome();
//        cap.setBrowserName("chrome");
//        String Node = "http://localhost:4444/wd/hub";
//        driver = new RemoteWebDriver(new URL(Node), cap);

        if (browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--log-level=3");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.get("https://demo.opencart.com/index.php");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    @Attachment(value = "screenshot", type = "image/png")
    public byte[] TakeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

    }
    @Attachment(value = "executionlogs")
    public String UploadLogs() throws IOException {
        return FileUtils.readFileToString(new File("test-execution.log"));
    }

}


//npm i -g webdriver-manager