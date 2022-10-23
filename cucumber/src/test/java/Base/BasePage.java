package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    //constructor:
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }
    protected String getNewUrl() {
        return driver.getCurrentUrl();
    }

    protected void waitForElement(By locator) throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    protected void type(By locator, String text) {
        find(locator).clear();
        find(locator).sendKeys(text);
    }
    public void hideElement(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = this.find(locator);
        js.executeScript("arguments[0].style = 'display:none'", element);
    }
    protected void click(By locator) {
        find(locator).click();
    }

    protected String text(By locator) {
        return driver.findElement(locator).getText();
    }

    protected void getAttribute(By locator, String attributeName) {
        driver.findElement(locator).getAttribute(attributeName);
    }

}
