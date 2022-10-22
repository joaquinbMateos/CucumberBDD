package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory{
    private DriverFactory(){

    }
    private static final Supplier<WebDriver> CHROME = () ->{
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    };
    private static final Supplier<WebDriver> FIREFOX = () ->{
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    };
    private static final Supplier<WebDriver> EDGE = () ->{
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    };
    private static final Map<BrowserType,Supplier<WebDriver>> MAP = new EnumMap<>(BrowserType.class);

    static{
        MAP.put(BrowserType.CHROME, CHROME);
        MAP.put(BrowserType.FIREFOX, FIREFOX);
        MAP.put(BrowserType.EDGE, EDGE);
    }
    public static WebDriver getDriver(BrowserType browser){
        return MAP.get(browser).get();
    }
}