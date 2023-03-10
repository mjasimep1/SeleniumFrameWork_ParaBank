package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class LoginDataDrivenTest extends BaseClass {
    @Test(dataProvider="LoginData", dataProviderClass= DataProviders.class)
    public void TC010_verify_dataProvider_test(String username, String password,String exp){
        try {
            logger.info("* Starting "+getClass().getSimpleName()+"-"+new Throwable().getStackTrace()[0].getMethodName()+" *");
            LoginPage loginPage=new LoginPage(driver);
            loginPage.setUsername(username);
            System.out.println("username entered " +username);
            loginPage.setPassword(password);
            System.out.println("password entered "+password);
            System.out.println(exp);
            Thread.sleep(1000);
            loginPage.clickLogin();
            Thread.sleep(3000);
            HomePage homePage=new HomePage(driver);
            boolean targetPage=homePage.isHomePageExists();
            System.out.println("get boolean");
            if(exp.equals("valid"))
            {
                if(targetPage ==true)
                {
                    System.out.println("entered valid if");
                    Thread.sleep(3000);
                    homePage.clickLogout();
                    Assert.assertTrue(true);
                }
                else
                {
                    System.out.println("entered valid else");
                    Assert.fail();
                }
            }


            if(exp.equals("invalid"))
            {
                if(targetPage ==true)
                {
                    System.out.println("entered invalid if");
                    Thread.sleep(3000);
                    homePage.clickLogout();
                    Assert.fail();
                }
                else
                {
                    System.out.println("entered invalid else");
                    Assert.assertTrue(true);
                }
            }
            System.out.println("finished");
            logger.info("Test is passed");
        }
        catch (Exception e){
            logger.error("Test failed"+e);
            Assert.fail();
        }
        logger.info("* finished "+getClass().getSimpleName()+"-"+new Throwable().getStackTrace()[0].getMethodName()+" *");

    }
}
