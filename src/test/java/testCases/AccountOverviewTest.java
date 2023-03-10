package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountOverviewPage;
import pageObjects.HomePage;
import pageObjects.OpenNewAccPage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class AccountOverviewTest extends BaseClass {
    @Test
    public void TC007_verify_account_overview() throws InterruptedException {
        try {
            logger.info("* Starting " + getClass().getSimpleName() + "-" + new Throwable().getStackTrace()[0].getMethodName() + " *");
            logger.info("Registering new user");
            new RegisterPage(driver).allRegisterProcess();
            HomePage homePage = new HomePage(driver);
            homePage.clickAccountOverview();
            logger.info("Opened account overview page");
            Thread.sleep(3000);
            AccountOverviewPage accountOverviewPage = new AccountOverviewPage(driver);
            accountOverviewPage.getOpeningAccountNo();
            logger.info("Validating the total balance");
            Assert.assertEquals(accountOverviewPage.getTotalBalanceDisplayed(), accountOverviewPage.getSumOfTotalBalance());
            logger.info("Test is passed");
        }
        catch (Exception e){
            logger.error("Test failed"+e);
            Assert.fail();
        }
        logger.info("* finished "+getClass().getSimpleName()+"-"+new Throwable().getStackTrace()[0].getMethodName()+" *");
    }
    @Test
    public void TC008_verify_create_new_Acc() throws InterruptedException {
        try {
            logger.info("* Starting " + getClass().getSimpleName() + "-" + new Throwable().getStackTrace()[0].getMethodName() + " *");
            logger.info("Creating new account");
            HomePage homePage = new HomePage(driver);
            homePage.clickOpenNewAccLink();
            Thread.sleep(3000);
            OpenNewAccPage openNewAccPage = new OpenNewAccPage(driver);
            openNewAccPage.selectAccType("savings");
            logger.info("Selected account type from the dropdown");
            Thread.sleep(3000);
            openNewAccPage.selectAccForOpenNewAcc(AccountOverviewPage.openingAccNo);
            logger.info("Selected account number from the dropdown");
            logger.info("Clicking the Create new account number button");
            openNewAccPage.clickCreateNewAccBtn();
            Thread.sleep(3000);
            logger.info("Validating the success message");
            openNewAccPage.getNewlyCreatedSecondAccNo();
            Assert.assertEquals(openNewAccPage.getSuccessMsg(),"Account Opened!");
            logger.info("Test is passed");
        }
        catch (Exception e){
            logger.error("Test failed"+e);
            Assert.fail();
        }
        logger.info("* finished "+getClass().getSimpleName()+"-"+new Throwable().getStackTrace()[0].getMethodName()+" *");
    }
    @Test
    public void TC009_verify_created_Acc_inThe_Overview() throws InterruptedException {
        try {
            logger.info("* Starting " + getClass().getSimpleName() + "-" + new Throwable().getStackTrace()[0].getMethodName() + " *");
            logger.info("Opening account overview");
            HomePage homePage = new HomePage(driver);
            homePage.clickAccountOverview();
            Thread.sleep(3000);
            logger.info("Verify the newly created acc no in the overview table");
            AccountOverviewPage accountOverviewPage=new AccountOverviewPage(driver);
            Assert.assertEquals(accountOverviewPage.getSumOfTotalBalance(),accountOverviewPage.getTotalBalanceDisplayed());
            Assert.assertTrue(accountOverviewPage.newAccNo_displayed_inThe_table(OpenNewAccPage.secondAccNo));

            logger.info("Test is passed");
        }
        catch (Exception e){
            logger.error("Test failed"+e);
            Assert.fail();
        }
        logger.info("* finished "+getClass().getSimpleName()+"-"+new Throwable().getStackTrace()[0].getMethodName()+" *");
    }
}
