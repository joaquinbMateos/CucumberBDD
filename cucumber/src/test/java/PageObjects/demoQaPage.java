package PageObjects;

import Base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

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
    By month_button = By.xpath("//select[contains(@class,'react-datepicker__month-select')]");
    By year_button = By.xpath("//select[contains(@class,'react-datepicker__year-select')]");
    By music_hobby = By.xpath("//label[contains(text(),'Music')]");
    By picture = By.xpath("//input[contains(@id,'uploadPicture')]");
    By select_state = By.xpath("//input[contains(@id,'react-select-3-input')]"); //Again: I know it's bad practice
    By select_city = By.xpath("//input[contains(@id,'react-select-4-input')]");
    By sumbit_button = By.xpath("//button[@id = 'submit']");
    By footer = By.xpath("//footer");
    By add_banner = By.xpath("//div[@id = 'fixedban']");
    By close_popup_button = By.xpath("//button[@id = 'closeLargeModal']");
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

    protected void clickDayJsExecutor(WebElement element, String day) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('.react-datepicker__day.react-datepicker__day--0" + day + "').click()", element);
    }
    public void completeBirthDate(String day, String month, String year){
        hideElement(add_banner);
        WebElement date = find(birth_date);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", date);

        Actions action = new Actions(driver);
        action.moveToElement(find(birth_date)).click().perform();
        //driver.find_element_by_xpath("//select[@id = 'selectId']/option[contains(text(), '" + myVariable + "')]").click()

        action.moveToElement(find(month_button)).click().perform();
        driver.findElement(By.xpath("//option[contains(text(),'"+month+"')]")).click();

        action.moveToElement(find(year_button)).click().perform();
        driver.findElement(By.xpath("//option[contains(text(),'"+year+"')]")).click();
        action.moveToElement(find(year_button)).click().perform();
        driver.findElement(By.xpath("//option[contains(text(),'"+year+"')]")).click();
        clickDayJsExecutor(date, day);
    }

    public void verifyDate(String day, String month, String year){
        String date = day+" "+month+" "+" "+year;
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

    public void closePopup(){
        click(close_popup_button);
    }
}
