package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(name = "username")
    WebElement txtUserName;
    @FindBy(name = "password")
    WebElement txtPassword;
    @FindBy(xpath = "//input[@type='submit']")
    WebElement btnLogin;
    @FindBy(linkText = "Register")
    WebElement lnkRegister;

    //Action methods
    public RegisterPage clickRegister(){
        lnkRegister.click();
        return new RegisterPage(driver);
    }
    public void setUsername(String username){
        txtUserName.sendKeys(username);
    }
    public void setPassword(String password){
        txtPassword.sendKeys(password);
    }
    public HomePage clickLogin(){
        btnLogin.click();
        return new HomePage(driver);
    }



}
