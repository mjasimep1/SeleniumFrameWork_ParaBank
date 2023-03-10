package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import testBase.BaseClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class utility extends BaseClass {
    public static String dateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public static void drawRectangleInScreenShot(WebElement e,String methName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_hh:mm:ss").format(new Date());
        File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        final BufferedImage image = ImageIO.read(screenShotFile);
        Graphics g = image.getGraphics();
        g.setColor(Color.red);
        g.drawOval(e.getLocation().getX()-10, e.getLocation().getY(), e.getSize().getWidth()+10, e.getSize().getHeight()+10);
       // g.drawRect(e.getLocation().getX(), e.getLocation().getY(), e.getSize().getWidth(), e.getSize().getHeight());
        // g.setFont(g.getFont().deriveFont(30f));
        //g.drawString("Failed because of this!!", accNoEle.getLocation().getX(), accNoEle.getLocation().getY()); //Top-left coordinates of your failed element
        //g.dispose();
        ImageIO.write(image, "png", new File("testData\\test"+methName+".png"));
    }
}
