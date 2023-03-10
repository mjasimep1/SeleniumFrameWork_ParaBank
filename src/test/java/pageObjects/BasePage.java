package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class BasePage {
    WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void writeToAdditionalData(String property, String value) {
        Properties properties = new Properties();
        try (OutputStream outputStream = new FileOutputStream("src//test//resources//tmpData.properties")) {
            properties.setProperty(property, value);
            properties.setProperty("prop2", "Value2");
            properties.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
