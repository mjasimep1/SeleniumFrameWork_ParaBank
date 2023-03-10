package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utilities.utility;

import java.io.IOException;
import java.util.List;

public class OpenNewAccPage extends BasePage{
    public OpenNewAccPage(WebDriver driver) {
        super(driver);
    }
    public static String secondAccNo;
    @FindBy(xpath = "//select[1]")
    WebElement drpAccType;
    @FindBy(xpath = "//select[@id='fromAccountId']")
    WebElement drpSelectAcc;
    @FindBy(xpath = "//input[@class='button']")
    WebElement btnCreateNewAcc;
    @FindBy(xpath = "//h1[@class='title']")
    WebElement msgSuccess;
    @FindBy(id = "newAccountId")
    WebElement newAccNoTxt;


    public void selectAccType(String type) {
        Select select = new Select(drpAccType);
        if(type.contains("savings")){
            select.selectByValue("1");
        }
        else{
            select.selectByValue("0");
        }
    }
    public void selectAccForOpenNewAcc(String acc){
        Select select = new Select(drpAccType);
        List<WebElement> accNos=driver.findElements(By.xpath("//select[@id='fromAccountId']"));
        for (WebElement accNo:accNos){
            System.out.println("drp dwn data is : "+accNo.getText());
            if(accNo.getText().equalsIgnoreCase(AccountOverviewPage.openingAccNo)) {
                accNo.click();
            }
            else {
                System.out.println("Account no is not showing");
            }
        }
        //select.selectByValue(acc);
        //select.selectByVisibleText(acc);
    }

    public void clickCreateNewAccBtn(){
        btnCreateNewAcc.click();
    }
    public String getSuccessMsg(){
        return msgSuccess.getText();
    }
    public String getNewlyCreatedSecondAccNo() throws IOException {
        secondAccNo=newAccNoTxt.getText();
        utility.drawRectangleInScreenShot(newAccNoTxt,new Throwable().getStackTrace()[0].getMethodName());
        System.out.println(secondAccNo);
        return newAccNoTxt.getText();
    }
}
