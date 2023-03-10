package testCases;

import org.apache.poi.sl.usermodel.TextBox;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class RegisterTest extends BaseClass {
    @Test
    public void TC001_verify_register_link(){
        try {
            logger.info("* Starting "+getClass().getSimpleName()+"-"+new Throwable().getStackTrace()[0].getMethodName()+" *");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickRegister();
            logger.info("Clicked on Register link");
            logger.info("Validating the link");
            Assert.assertEquals(new RegisterPage(driver).getPageTitleMsg(), "Signing up is easy!");
            logger.info("Test is passed");
        }
        catch (Exception e){
            logger.error("Test failed"+e);
            Assert.fail();

        }
        logger.info("* finished "+getClass().getSimpleName()+"-"+new Throwable().getStackTrace()[0].getMethodName()+" *");
    }

    @Test(dependsOnMethods = "TC001_verify_register_link")
    public void TC004_verify_signUp_functionality(){
        try {
            logger.info("* Starting "+getClass().getSimpleName()+"-"+new Throwable().getStackTrace()[0].getMethodName()+" *");
            RegisterPage registerPage=new RegisterPage(driver);
            logger.info("Filling the fields");
            registerPage.fillFields(staticUsername,"efgh","hijk","kochi","kerala","673687","9048090480","123456");
            logger.info("Username entering");
            registerPage.setUserName(staticUsername);
            logger.info("Password entering");
            registerPage.setPassword(staticpassword);
            Thread.sleep(5000);
            logger.info("Register button clicking");
            registerPage.clickRegister();
            logger.info("Validating the register function");
            Assert.assertEquals(new HomePage(driver).getWelcomeTitle(),"Welcome "+staticUsername);
            logger.info("Loging out");
            new HomePage(driver).clickLogout();
            logger.info("Test is passed");

        }
        catch (Exception e){
            logger.error("Test failed"+e);
            Assert.fail();
        }
        logger.info("* finished "+getClass().getSimpleName()+"-"+new Throwable().getStackTrace()[0].getMethodName()+" *");
    }

    //@Test(dependsOnMethods = "TC001_verify_register_link")
    public void TC004_(){
        try {
            logger.info("* Starting "+getClass().getSimpleName()+"-"+new Throwable().getStackTrace()[0].getMethodName()+" *");



            logger.info("Test is passed");
        }
        catch (Exception e){
            logger.error("Test failed"+e);
            Assert.fail();
        }
        logger.info("* finished "+getClass().getSimpleName()+"-"+new Throwable().getStackTrace()[0].getMethodName()+" *");
    }

}
