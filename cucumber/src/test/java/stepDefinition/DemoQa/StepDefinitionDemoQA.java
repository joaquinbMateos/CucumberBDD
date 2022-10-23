package stepDefinition.DemoQa;

import PageObjects.demoQaPage;
import io.cucumber.java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class StepDefinitionDemoQA {
    private static WebDriver driver;
    private demoQaPage page;

    @Before
    public void setUp(Scenario scenario) throws MalformedURLException {

        ChromeOptions options = new ChromeOptions();
        options.setPlatformName("Windows 10");
        options.setBrowserVersion("latest");

        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", System.getenv("SAUCE_USERNAME"));
        sauceOptions.put("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
        sauceOptions.put("name", scenario.getName());

        options.setCapability("sauce:options", sauceOptions);
        URL url = new URL("https://ondemand.us-west-1.saucelabs.com/wd/hub");

        driver = new RemoteWebDriver(url, options);

        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    //Forms:
    @Given("I am in {string}")
    public void openPage(String url){
        driver.get(url);
        page = new demoQaPage(driver);
    }
    @When("I click on Forms")
    public void goToForms(){
        page.clickForms();
    }

    @Then("I am redirected to {string}")
    public void assertUrl(String url){
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }

    @And("I click on Practice Form")
    public void goToPracticeForms(){
        page.clickPracticeForm();
    }

    //Inputs:
    @When("I fill {string} input with {string}")
    public void fillInputs(String input, String data){
        page.fillInput(input,data);
    }
    @Then("validate {string} input with {string}")
    public void validateInput(String input, String data){
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(page.getElement(input).getAttribute("value"), data);
    }

    //Gender Button:
    @When("I click on gender button")
    public void selectGender(){
        page.selectGender();
    }
    @Then("button status change")
    public void verifyRadioButton(){
        page.verifyRadioButton();
    }

    //Birth Date:
    @When("I complete {string},{string},{string}")
    public void completeBirthDate(String day, String month, String year){
        page.completeBirthDate(day,month,year);
    }
    @Then("validate {string},{string},{string}")
    public void validateDate(String day, String month, String year){
        page.verifyDate(day,month,year);
    }

    //Hobby:
    @When("I click on checkbox")
    public void chooseHobby(){
        page.selectHobby();
    }
    @Then("check box is marked")
    public void assertCheckBox(){
        page.verifyCheckBox();
    }

    //Image:
    @When("I upload image")
    public void uploadImage(){
        page.upLoadImage();
    }
    @Then("image is uploaded")
    public void assertImage(){
        page.verifyImage();
    }

    //State:
    @When("I type {string} state")
    public void selectState(String state){
        page.selectState(state);
    }
    @And("I type {string} city")
    public void selectCity(String city){
        page.selectCity(city);
    }
    @Then("{string} state is selected")
    public void assertState(String state){
        page.verifyState(state);
    }
    @And("{string} city is selected")
    public void assertCity(String city){
        page.verifyCity(city);
    }

    //Submit
    @And("I click on submit")
    public void clickSubmit(){
        page.clickSubmit();
    }

    @Then ("I close popup")
    public void closePopup(){
        page.closePopup();
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
