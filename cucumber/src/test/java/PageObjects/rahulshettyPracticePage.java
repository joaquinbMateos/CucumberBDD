package PageObjects;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class rahulshettyPracticePage extends BasePage {
    String expectedUrl = "https://rahulshettyacademy.com/AutomationPractice/";
    //constructor:
    public rahulshettyPracticePage(WebDriver driver) {
        super(driver);
    }
    //locators:
    By radiobuttons = By.xpath("//input[@class=\"radioButton\"]");
    By first_option = By.xpath("//div[@class = 'ui-menu-item-wrapper']");
    By input_one = By.xpath("//input[@class=\"inputs ui-autocomplete-input\"]");
    By drop_down = By.xpath("//select[contains(@name,'dropdown-class-example')]");
    By header_buttons = By.xpath("//header//child::button[contains(@class,'btn')]");
    By btn1 = By.xpath("//button[contains(text(),'Home')]");
    By btn2 = By.xpath("//button[contains(text(),'Practice')]");
    By btn3 = By.xpath("//button[contains(text(),'Login')]");
    By btn4 = By.xpath("//button[contains(text(),'Signup')]");
    By open_tab_btn = By.xpath("//a[contains(@id,'opentab')]");

    //methods:
    public By radioButtons(){
        return radiobuttons;
    }

    public WebElement searchBox(){
        return find(input_one);
    }

    public void printOption(){
        System.out.println(find(first_option).getText());
    }

    Select dropDown = new Select(find(drop_down));
    public void dropDownOption(String option){
        dropDown = new Select(find(drop_down));
        dropDown.selectByVisibleText(option);
    }

    public void dropDownOptionMouse(String selection){
        WebElement drop_down_menu = find(drop_down);
        Actions mouse = new Actions(driver);
        mouse.moveToElement(drop_down_menu).click().perform();

        Select option = new Select(find(drop_down));
        option.selectByVisibleText(selection);
    }

    public void printDropDownOption(){
        System.out.println(dropDown.getFirstSelectedOption().getText());;
    }

    public void clickHeaderButtons(String btnName) {
        //WebElement button;
        switch (btnName) {
            case "Home":
                click(btn1);
                break;
            case "Practice":
                click(btn2);
                break;
            case "Login":
                click(btn3);
                break;
            case "Signup":
                click(btn4);
                break;
        }
    }

    public void assertHeaderButtons(String button){
        boolean assertUrl = driver.getCurrentUrl().equals(expectedUrl);
        if (!assertUrl){
            System.out.println("Different URL - btn: "+ button);
            driver.navigate().back();
        }else{
            System.out.println("Same URL - btn:"+ button);
        }
    }

    public void tabButton(){
        click(open_tab_btn);
    }
}
