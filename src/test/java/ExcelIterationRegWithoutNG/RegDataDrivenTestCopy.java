package ExcelIterationRegWithoutNG;

import org.testng.annotations.Test;
import testBase.BaseClass;

import java.io.IOException;

public class RegDataDrivenTestCopy extends BaseClass {
    @Test()
    public void registerFromExcel() throws IOException {
        String file = System.getProperty("user.dir") + "\\testData\\regData.xlsx";
        int rows = ExcelUtils.getRowCount(file, "Sheet1");
        int cells = ExcelUtils.getCellCount(file, "Sheet1", 1);

        for (int i = 1; i <= rows; i++) {
           /* for(int j=0;j<cells;j++) {

                String data = ExcelUtils.getCellData(file, "Sheet1", i, j);
            }*/

            //Read data from excel
            String fname = ExcelUtils.getCellData(file, "Sheet1", i, 0);
            String lname = ExcelUtils.getCellData(file, "Sheet1", i, 1);
            String address = ExcelUtils.getCellData(file, "Sheet1", i, 2);
            String city = ExcelUtils.getCellData(file, "Sheet1", i, 3);


            try {
                RegisterPageIter registerPageIter = new RegisterPageIter(driver);
                registerPageIter.allRegisterProcess(fname, lname, address, city);
                // registerPageIter.setUserName();
                // registerPageIter.setPassword();
                //  registerPageIter.clickRegister();
                //  registerPageIter.waitAndClickLogoutBtn();
            } catch (Exception e) {

            }
        }
    }
}
