package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utilities.utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

public class BaseClass {
    public Logger logger; //import from import org.apache.logging.log4j.Logger
    public static WebDriver driver;
    public ResourceBundle rb;
    public ResourceBundle tmpData;
    public static String staticUsername=RandomStringUtils.randomAlphabetic(5).toLowerCase();
    public static String staticUsernameTmp="demo@123";
    public static String staticpasswordTmp="123456";
    public static String staticpassword=RandomStringUtils.randomAlphanumeric(5);


    @BeforeClass
    @Parameters("browser")
    public void setup(String br){
        rb=ResourceBundle.getBundle("config"); //Load config.properties file
        tmpData=ResourceBundle.getBundle("tmpData");
        logger= LogManager.getLogger(this.getClass());
        logger.info("-->["+ utility.dateTime()+"] Starting the test automation <--");
    //used for avoid the "control by automation" message from browser
       // EdgeOptions options=new EdgeOptions();
       // options.setExperimentalOption("excludeSwitches",new String[] {"enable-automation"});
        if(br.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (br.equals("ie")) {
            WebDriverManager.iedriver().setup();
            driver=new InternetExplorerDriver();
        }
        else {
            System.out.println("Not given any browser");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(rb.getString("appUrl"));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        //driver.quit();
        logger.info("-->["+ utility.dateTime()+"] Finished test automation <--");
    }

    public String randomString(){
        String generatedString= RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

    public String randomNumber(){
        String generatedString1=RandomStringUtils.randomNumeric(10);
        return generatedString1;
    }
    public String randomAlphaNumeric(){
        String generatedString2=RandomStringUtils.randomAlphanumeric(5);
        return generatedString2;
    }

    public String captureScreen(String tname) throws IOException {

		/*Date dt=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmss");
		String timeStamp=df.format(dt);
		*/
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            e.getMessage();
        }
        return destination;
    }





}
