package PageObjects;

import Base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class demoQaPage extends BasePage {

    public demoQaPage(WebDriver driver) {
        super(driver);
    }

    By forms = By.xpath("//*[contains(text(),'Forms')]");
    By practice_forms = By.xpath("//li[contains(@class,'btn btn-light')]//child::span[contains(text(),'Practice Form')]");
    ////*[name()='svg']//*[name()='g']//*[name()='path']
    By name = By.xpath("//input[contains(@id,'firstName')]");
    By lastName = By.xpath("//input[contains(@id,'lastName')]");
    By email = By.xpath("//input[contains(@id,'userEmail')]");
    By mobile = By.xpath("//input[contains(@id,'userNumber')]");
    By subject = By.xpath("//input[contains(@id,'subjectsInput')]");
    By address = By.xpath("//textarea[contains(@id,'currentAddress')]");
    By male_gender = By.xpath("//input[@id = 'gender-radio-1']");
    By birth_date = By.xpath("//input[contains(@id,'dateOfBirthInput')]");
    By month = By.xpath("//select[contains(@class,'react-datepicker__month-select')]");
    By year = By.xpath("//select[contains(@class,'react-datepicker__year-select')]");
    By day = By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[5]/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div[4]/div[6]"); //I know it's bad practice
    // day: //div[contains(@class,'react-datepicker__day react-datepicker__day--022 react-datepicker__day--selected')]
    By music_hobby = By.xpath("//label[contains(text(),'Music')]");
    By picture = By.xpath("//input[contains(@id,'uploadPicture')]");
    By select_state = By.xpath("//input[contains(@id,'react-select-3-input')]"); //Again: I know it's bad practice
    By select_city = By.xpath("//input[contains(@id,'react-select-4-input')]");
    By sumbit_button = By.xpath("//button[@id = 'submit']");
    By footer = By.xpath("//footer");
    By add_banner = By.xpath("//div[@id = 'fixedban']");
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

    public void clickForms(){
        jsExecutor.executeScript("window.scrollBy(0,400)");
        click(forms);
    }
    public void clickPracticeForm(){
        click(practice_forms);
    }

    public void fillInput(String input,String data){
        switch (input) {
            case "name":
                type(name, data);
                break;
            case "lastName":
                type(lastName, data);
                break;
            case "email":
                type(email, data);
                break;
            case "mobile":
                type(mobile, data);
                break;
            case "subjects":
                find(subject).sendKeys(data);
                find(subject).sendKeys(Keys.ARROW_DOWN);
                find(subject).sendKeys(Keys.ENTER);
                break;
            case "address":
                type(address, data);
                break;
        }
    }

    public WebElement getElement(String elementName){
            WebElement element = null;
        switch (elementName) {
            case "name":
                element = find(name);
                break;
            case "lastName":
                element = find(lastName);
                break;
            case "email":
                element = find(email);
                break;
            case "mobile":
                element = find(mobile);
                break;
            case "subjects":
                element = find(subject);
                break;
            case "address":
                element = find(address);
                break;
        }
            return element;
    }
    public void selectGender(){
        Actions action = new Actions(driver);
        WebElement radioButton = find(male_gender);
        action.moveToElement(radioButton).click().build().perform();
    }
    public void verifyRadioButton(){
        WebElement radioButton = find(male_gender);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", radioButton);
        radioButton.isSelected();
    }

    public void completeBirthDate(){
        Actions action = new Actions(driver);
        action.moveToElement(find(birth_date)).click().perform();
        action.moveToElement(find(month)).click().perform();
        Select month_option = new Select(find(month));
        month_option.selectByValue("2");
        Select year_option = new Select(find(year));
        year_option.selectByValue("1991");
        action.moveToElement(find(day)).click().perform();
    }

    public void verifyDate(String date){
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(find(birth_date).getAttribute("value"), date, "incorrect birth date");
    }

    public void selectHobby(){
        Actions action = new Actions(driver);
        action.moveToElement(find(music_hobby)).click().build().perform();
    }

    public void verifyCheckBox(){
        find(music_hobby).isSelected();
    }

    public void upLoadImage(){
        WebElement upload_file = find(picture);
        upload_file.sendKeys(System.getProperty("user.dir")+"/src/test/resources/profile.jpg");
    }

    public void verifyImage(){
        System.out.println(find(picture).getText());
    }

    public void selectState(String option){
        hideElement(add_banner);
        hideElement(footer);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", find(select_state));
        find(select_state).sendKeys(option);
        find(select_state).sendKeys(Keys.ARROW_DOWN);
        find(select_state).sendKeys(Keys.ENTER);
    }
    public void selectCity(String option){
        hideElement(add_banner);
        hideElement(footer);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", find(select_state));
        find(select_state).sendKeys(option);
        find(select_state).sendKeys(Keys.ARROW_DOWN);
        find(select_state).sendKeys(Keys.ENTER);
    }
    public void verifyState(String state){
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(find(select_state).getAttribute("value"), state, "incorrect State");
    }

    public void verifyCity(String city){
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(find(select_city).getAttribute("value"), city, "incorrect State");
    }

    public void clickSubmit(){
        hideElement(add_banner);
        hideElement(footer);
        click(sumbit_button);
    }
    //----------------------------------------------------------------------------------------------//

    public void testForms(){
        jsExecutor.executeScript("window.scrollBy(0,400)");
        click(forms);
        String expectedUrl = "demoqa.com/forms";
        String actualUrl = getNewUrl();
        boolean assertFormsUrl = actualUrl.equals(expectedUrl);
        if (!assertFormsUrl){
            System.out.println("Incorrect URL");
        }else{
            System.out.println("Correct URL");
        }
    }

    public void testPracticeForms(){
        click(practice_forms);
        String expectedUrl = "demoqa.com/automation-practice-form";
        String actualUrl = getNewUrl();
        boolean assertFormsUrl = actualUrl.equals(expectedUrl);
        if (!assertFormsUrl){
            System.out.println("Incorrect URL");
        }else{
            System.out.println("Correct URL");
        }
        //given url = demoqa.com/automation-practice-form
        //actual url = https://demoqa.com/automation-practice-form
    }
    public void testFillForm(){
        /*
         * Fill the inputs for Name, Email, Mobile, Subjects and Current Address using the
         * correct method and assert that each input contains the correct text.
         */
        type(name, "joaquin");
        type(lastName, "mateos");
        type(email, "jmateos@applaudostudios.dev");
        type(mobile,"6543218847");
        find(subject).sendKeys("Computer Science");
        find(subject).sendKeys(Keys.ARROW_DOWN);
        find(subject).sendKeys(Keys.ENTER);
        type(address, "very real adress");

        //asserts could be refactor into iterative structure using lists.
        boolean assertName = find(name).getAttribute("value").equals("joaquin");
        if (!assertName){
            System.out.println("Incorrect text in first name field");
        }

        boolean assertLastName = find(lastName).getAttribute("value").equals("mateos");
        if (!assertLastName){
            System.out.println("Incorrect text in last name field");
        }

        boolean assertEmail = find(email).getAttribute("value").equals("jmateos@applaudostudios.dev");
        if (!assertEmail){
            System.out.println("Incorrect text in email field");
        }

        boolean assertNumber = find(mobile).getAttribute("value").equals("6543218847");
        if (!assertNumber){
            System.out.println("Incorrect text in number field");
        }

        boolean assertSubject = find(subject).getAttribute("value").equals("Computer Science");
        if (!assertSubject){
            System.out.println("Incorrect text in subject field");
        }

        boolean assertAdress = find(address).getAttribute("value").equals("very real adress");
        if (!assertAdress){
            System.out.println("Incorrect text in adress field");
        }
    }
    public void testMaleGender(){
        Actions action = new Actions(driver);
        WebElement radioButton = driver.findElement(male_gender);
        action.moveToElement(radioButton).click().build().perform();
    }

    public void testDate() {
        jsExecutor.executeScript("window.scrollBy(0,400)");
        Actions action = new Actions(driver);
        action.moveToElement(find(birth_date)).click().perform();
        action.moveToElement(find(month)).click().perform();
        Select month_option = new Select(find(month));
        month_option.selectByValue("2");
        Select year_option = new Select(find(year));
        year_option.selectByValue("1991");
        action.moveToElement(find(day)).click().perform();

        boolean asserDate = find(birth_date).getAttribute("value").equals("22 Mar 1991");
        if (!asserDate){
            System.out.println("Incorrect text in date field");
        }else{
            System.out.println("Correct birth date");
        }
    }
    public void testHobby(){ //data should be parameterized;
        Actions action = new Actions(driver);
        action.moveToElement(find(music_hobby)).click().build().perform(); //checkbox is not clicked
        //find(music_hobby).sendKeys(Keys.ENTER);
    }
    public void testImage(){
        WebElement upload_file = driver.findElement(picture);
        upload_file.sendKeys("C:\\Users\\Joaco\\Desktop\\Joaco\\CV\\profile.png");
    }

    public void testDropdown(){
        //Complete the dropdown of State and City and validate the text displayed is correct.
        jsExecutor.executeScript("window.scrollBy(0,600)");
        click(select_state);
    }

    public void testSubmitButton(){
        WebElement submitBTN = driver.findElement(sumbit_button);
        submitBTN.submit();
    }

    public void removeAdd(){
        //jsExecutor.executeScript("return document.getElementsByid('canvas')[0].remove();");
        //WebElement add = driver.findElement(By.id("ad_unit"));
        driver.switchTo().frame(0);
    }
}
