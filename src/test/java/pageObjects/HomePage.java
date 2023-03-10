package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }
    //div[@id='rightPanel']//h1
    @FindBy(xpath = "//div[@id='rightPanel']//h1")
    WebElement welcomeTitle;
    @FindBy(linkText = "Log Out")
    WebElement lnkLogoutBtn;
    @FindBy(linkText = "Accounts Overview")
    WebElement lnkAccountOverview;
    @FindBy(linkText = "Open New Account")
    WebElement lnkOpenNewAcc;


    public String getWelcomeTitle(){
        return welcomeTitle.getText();
    }
    public void clickLogout(){
        lnkLogoutBtn.click();
    }
    public String getTitleHome(){
        return (driver.getTitle());
    }
    public boolean isHomePageExists()   // MyAccount Page heading display status
    {
        try {
            return (welcomeTitle.isDisplayed());
        } catch (Exception e) {
            return (false);
        }
    }
    public void clickAccountOverview(){
        lnkAccountOverview.click();
    }
    public void clickOpenNewAccLink(){
        lnkOpenNewAcc.click();
    }

}
