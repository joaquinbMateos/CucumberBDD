package stepDefinition.DemoQa;

import PageObjects.demoQaPage;
import io.cucumber.java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class StepDefinitionDemoQA {
    private static WebDriver driver;
    private demoQaPage page;

    @BeforeAll
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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
    @When("I complete brithdate")
    public void completeBirthDate(){
        page.completeBirthDate();
    }
    @Then("validate {string}")
    public void validateDate(String date){
        page.verifyDate(date);
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

    @AfterAll
    public static void tearDown(){
        //driver.quit();
    }
}
