package ExcelIterationReg;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class RegDataDrivenTest extends BaseClass {
    @Test(dataProvider="RegData", dataProviderClass= DataProviders.class)
    public void registerFromExcel(String fstname,String lstname,String address,String city,String state,String zip,String phone,String ssn,String uname,String password){
        try {
            RegisterPageIter registerPageIter=new RegisterPageIter(driver);
            registerPageIter.allRegisterProcess(fstname,lstname,address,city,state,zip,phone,ssn);
            registerPageIter.setUserName(uname);
            registerPageIter.setPassword(password);
            registerPageIter.clickRegister();
            registerPageIter.waitAndClickLogoutBtn();
        }
        catch (Exception e){

        }
        }
}
