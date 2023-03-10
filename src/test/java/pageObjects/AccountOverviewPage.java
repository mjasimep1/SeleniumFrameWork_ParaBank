package pageObjects;

import org.openqa.selenium.*;
import testBase.BaseClass;
import utilities.utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

public class AccountOverviewPage extends BasePage {
    public static String openingAccNo;

    public AccountOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getOpeningAccountNo() throws IOException {
        WebElement accNo = driver.findElement(By.xpath("//table//td[1]"));
        System.out.println("Acc no is: " + accNo.getText());
        utility.drawRectangleInScreenShot(accNo,new Throwable().getStackTrace()[0].getMethodName());
        writeToAdditionalData("OpeningAccNo", accNo.getText());
        String opening = accNo.getText();
        openingAccNo = opening;
        return accNo.getText();
    }

    public double getTotalBalanceDisplayed() throws IOException {
        List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));
        int r = rows.size() - 2;
        String total1 = driver.findElement(By.xpath("//table[@id='accountTable']//tr[" + r + "]//td[2]")).getText();
        Double toalAmountDisplayed = Double.parseDouble(total1.substring(1));
        utility.drawRectangleInScreenShot(driver.findElement(By.xpath("//table[@id='accountTable']//tr[" + r + "]//td[2]")),new Throwable().getStackTrace()[0].getMethodName());
        System.out.println("Total displayed amount: " + toalAmountDisplayed);
        return toalAmountDisplayed;
    }

    public double getSumOfTotalBalance() {
        List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));
        int r = rows.size() - 2;
        double sum = 0;
        for (int i = 1; i < r; i++) {
            String balance = driver.findElement(By.xpath("//table//tr[" + i + "]//td[2]")).getText();
            Double toalAmountDisplayed = Double.parseDouble(balance.substring(1));
            sum = sum + toalAmountDisplayed;
            System.out.println("sum of balance is: " + sum);
        }
        return sum;
    }

    public boolean newAccNo_displayed_inThe_table(String accNo) throws IOException {
        List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));
        String acc = "";
        boolean tst = false;
        int r = rows.size() - 2;
        for (int i = 1; i < r; i++) {
            WebElement accNoEle = driver.findElement(By.xpath("//table//tr[" + i + "]//td[1]"));
            acc=accNoEle.getText();
            System.out.println("checking the table: " + accNoEle.getText());
            if (acc.equalsIgnoreCase(accNo)) {
                System.out.println("Created acc no is present in the overview");
                tst=true;
                break;
                //return acc;

            } else {
                System.out.println("Created acc no is not present in the overview");
                tst=false;
                utility.drawRectangleInScreenShot(accNoEle,new Throwable().getStackTrace()[0].getMethodName());
            }
        }
        return tst;
    }
}

