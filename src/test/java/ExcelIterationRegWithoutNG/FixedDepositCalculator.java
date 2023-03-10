package ExcelIterationRegWithoutNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class FixedDepositCalculator {
    //Day 33|00:00:00======>It has issues in set data to excel
    WebDriver driver;
    @BeforeTest
    public void launchUrl(){

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test(enabled = false)
    public void dataDriven() throws IOException, InterruptedException {
        String file=System.getProperty("user.dir")+"\\testdata\\caldata.xlsx";
        int rows=ExcelUtils.getRowCount(file,"Sheet1");

        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement popUp=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='wzrk-alert wiz-show-animate']//button[2]")));
        popUp.click();

        for(int i=1;i<=rows;i++){
        //Read data from excel
            String princ=ExcelUtils.getCellData(file,"Sheet1",i,0);
            String rateOfIntrst=ExcelUtils.getCellData(file,"Sheet1",i,1);
            String per1=ExcelUtils.getCellData(file,"Sheet1",i,2);
            String per2=ExcelUtils.getCellData(file,"Sheet1",i,3);
            String fre=ExcelUtils.getCellData(file,"Sheet1",i,4);
            String exp_mValue=ExcelUtils.getCellData(file,"Sheet1",i,5);


        //Pass data to app
            WebElement principal= driver.findElement(By.id("principal"));
            principal.sendKeys(princ);
            WebElement rateOfIntrt= driver.findElement(By.id("interest"));
            rateOfIntrt.sendKeys(rateOfIntrst);
            WebElement period= driver.findElement(By.id("tenure"));
            period.sendKeys(per1);
            Select day=new Select(driver.findElement(By.id("tenurePeriod")));
            day.selectByVisibleText(per2);
            Select frequency=new Select(driver.findElement(By.id("frequency")));
            frequency.selectByVisibleText(fre);
            Thread.sleep(1000);
            WebElement calculateBtn= driver.findElement(By.xpath("//div[@class='CTR PT15']//a[1]//img"));
            calculateBtn.click();
            Thread.sleep(1000);
            String resultMValue=driver.findElement(By.xpath("//div[@class='PR20 PT5']//span[2]")).getText();
            System.out.println(resultMValue);


        //Test the maturity value
            //if (resultMValue.equalsIgnoreCase(exp_mValue)){
            if(Double.parseDouble(resultMValue)==Double.parseDouble(exp_mValue)){
                System.out.println("Test passed for "+princ);
                ExcelUtils.setCellData(file,"Sheet1",i,7,"Passed");
                ExcelUtils.fillGreenColor(file,"Sheet1",i,7);

            }
            else{
                System.out.println("Test failed for "+princ);
                ExcelUtils.setCellData(file,"Sheet1",i,7,"Failed");
                ExcelUtils.fillRedColor(file,"Sheet1",i,7);
            }
            WebElement clearBtn= driver.findElement(By.xpath("//div[@class='CTR PT15']//a[2]//img"));
            clearBtn.click();

        }
    }
}
