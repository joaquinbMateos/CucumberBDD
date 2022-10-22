package stepDefinition.PracticePage;

import PageObjects.rahulshettyPracticePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class stepDefinitionPracticePage {
    private WebDriver driver;
    private rahulshettyPracticePage page;
    private  List<WebElement> radioBTN;
    private int randomButton;


    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("I am in practice page")
    public void openPage() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        page = new rahulshettyPracticePage(driver);
    }

    //Radio Buttons:
    @When("I click on radio button")
    public void clickRadioButton() {
        radioBTN = driver.findElements(page.radioButtons());
        Random rand = new Random();
        randomButton = rand.nextInt(2);
        radioBTN.get(randomButton).click();
    }

    @Then("radio button status change")
    public void printButtons() {
        radioBTN = driver.findElements(By.xpath("//input[@class=\"radioButton\"]//ancestor::label"));
        System.out.println("Random Radio Button Option: "+ (randomButton+1));
        for(int i=0; i< radioBTN.size(); i++){
            if (i != randomButton){
                System.out.println("NOT Selected Radiobutton: "+radioBTN.get(i).getText());
            }else{
                System.out.println("selected Radiobutton: "+radioBTN.get(i).getText());
            }
        }
    }

    //Search Box:
    @When("I type {string}")
    public void searchCountry(String arg){
        System.out.println(page.searchBox().getAttribute("placeholder"));
        page.searchBox().sendKeys(arg);
        page.searchBox().sendKeys(Keys.ARROW_DOWN);
        page.searchBox().sendKeys(Keys.ENTER);
    }
    @Then("search box show complete name")
    public void firstOption() throws InterruptedException {
        Thread.sleep(5000);
        page.printOption();
    }

    //Drop Down:
    @When("I select {string} from dropdown")
    public void dropDown(String arg){
        switch(arg){
            case "Option2":
                page.dropDownOption(arg);
                break;
            case "Option3":
                page.dropDownOptionMouse(arg);
                break;
            default:
                break;
        }
    }
    @Then("option selected is shown")
    public void printOption(){
        page.printDropDownOption();
    }

    //Header Buttons:
    @When("I click on {string}")
    public void clickHeaderButton(String arg){
        page.clickHeaderButtons(arg);
    }
    @Then("I should navigate to {string} section")
    public void checkButtonSection(String arg){
        page.assertHeaderButtons(arg);
    }

    //Browser Tabs:
    @When("I click 'open tab' button {int} times")
    public void openTabs(int N){
        for (int i = 1; i < N; i++){
            String handle = driver.getWindowHandle();
            page.tabButton();
            driver.switchTo().window(handle);
        }
    }
    @Then("print the number of tabs I opened")
    public void countTabs(){
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        System.out.println("Number of tabs: " + tabs.size());
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
