package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class LoginTest extends BaseClass {
    @Test
    public void TC005_verify_login_with_valid_data(){
        try {
            logger.info("* Starting "+getClass().getSimpleName()+"-"+new Throwable().getStackTrace()[0].getMethodName()+" *");
            LoginPage loginPage=new LoginPage(driver);
            logger.info("Entering username");
            loginPage.setUsername(staticUsername);
            logger.info("Entering password");
            loginPage.setPassword(staticpassword);
            logger.info("Clicking the login button");
            loginPage.clickLogin();
            logger.info("Validating the login functionality");
            Assert.assertEquals(new HomePage(driver).getTitleHome(),"ParaBank | Accounts Overview");
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
