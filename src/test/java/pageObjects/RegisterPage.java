package pageObjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{
    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//h1[@class='title']")
    WebElement pageTitleMsg;
    @FindBy(name="customer.firstName")
    WebElement txtFName;
    @FindBy(name="customer.lastName")
    WebElement txtLName;
    @FindBy(name="customer.address.street")
    WebElement txtAddress;
    @FindBy(name="customer.address.city")
    WebElement txtCity;
    @FindBy(name="customer.address.state")
    WebElement txtState;
    @FindBy(name="customer.address.zipCode")
    WebElement txtZip;
    @FindBy(name="customer.phoneNumber")
    WebElement txtPhone;
    @FindBy(name="customer.ssn")
    WebElement txtSsn;
    @FindBy(name="customer.username")
    WebElement txtUserName;
    @FindBy(name="customer.password")
    WebElement txtPassword;
    @FindBy(name="repeatedPassword")
    WebElement txtConfPassword;
    @FindBy(xpath="//input[@value='Register']")
    WebElement btnRegister;


    public String getPageTitleMsg(){
        return pageTitleMsg.getText();
    }
    public void fillFields(String fname,String lname,String address,String city,String state,String zip,String phone,String ssn){
        txtFName.sendKeys(fname);
        txtLName.sendKeys(lname);
        txtAddress.sendKeys(address);
        txtCity.sendKeys(city);
        txtState.sendKeys(state);
        txtZip.sendKeys(zip);
        txtPhone.sendKeys(phone);
        txtSsn.sendKeys(ssn);
    }
    public void setUserName(String username){
        txtUserName.sendKeys(username);
    }
    public void setPassword(String password){
        txtPassword.sendKeys(password);
        txtConfPassword.sendKeys(password);
    }
    public void clickRegister(){
        btnRegister.click();
    }



//Register process
    public String randomString(){
        String generatedString= RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

    public String randomNumber(){
        String generatedString1=RandomStringUtils.randomNumeric(10);
        return generatedString1;
    }
    public void allRegisterProcess(){
        new LoginPage(driver).clickRegister();
        txtFName.sendKeys(randomString());
        txtLName.sendKeys(randomString());
        txtAddress.sendKeys(randomString());
        txtCity.sendKeys(randomString());
        txtState.sendKeys(randomString());
        txtZip.sendKeys(randomNumber());
        txtPhone.sendKeys(randomNumber());
        txtSsn.sendKeys(randomNumber());
        setUserName(randomString());
        setPassword(randomString());
        clickRegister();
    }

}
