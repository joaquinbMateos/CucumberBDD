package Base;

import PageObjects.rahulshettyPracticePage;
import Utilities.BrowserType;
import Utilities.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected static WebDriver driver;
    protected rahulshettyPracticePage page;

    @BeforeTest
    @Parameters({"CHROME"})
    public void setUp(String browser) {
        switch(browser){
            case "FF":
                this.driver = DriverFactory.getDriver(BrowserType.FIREFOX);
                break;
            case "CHROME":
                this.driver = DriverFactory.getDriver(BrowserType.CHROME);
                break;
            case "EDGE":
                this.driver = DriverFactory.getDriver(BrowserType.EDGE);
                break;
            default: this.driver = DriverFactory.getDriver(BrowserType.CHROME);
                break;
        }
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
