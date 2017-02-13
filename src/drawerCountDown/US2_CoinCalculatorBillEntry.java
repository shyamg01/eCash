package drawerCountDown;

import java.io.IOException;
import java.text.ParseException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.GenericMethods;
import common.LoginTestData;
import common.Reporter;

import eCashPageClasses.AbstractTest;
import eCashPageClasses.DrawerCountDownPage;
import eCashPageClasses.HomePage;

public class US2_CoinCalculatorBillEntry extends AbstractTest{
	
	//TC3973: Viewing and verify the system calculated field under "Expected Cash Calculations" heading on DCD detail summary modal screen.
		@Test()
		public void drawerCountDown_US2_TC4524() throws InterruptedException,
				RowsExceededException, BiffException, WriteException, IOException, ParseException {
			/** Variable Section : **/
			DrawerCountDownPage drawerCountDownPage;
			AbstractTest.tcName="drawerCountDown_US2_TC4524";
			String password = LoginTestData.level1_SSO_Password;
			String userId = LoginTestData.level1_SSO_UserId;
			String storeId = LoginTestData.level1StoreId;
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to Group sales page
			drawerCountDownPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
					.goToDrawerCountDownPage();
			Thread.sleep(2000);
			/*GenericMethods.clickOnElement(driver.findElement(By.xpath("//tr[@id='row-header-0']")), "firstRow");
			Thread.sleep(2000);*/
			drawerCountDownPage.expandRolledUpDrawerCountRecords();
			GenericMethods.clickOnElement(drawerCountDownPage.DrawerCountDown_UncountedRecord_View_BT, "DrawerCountDown_UncountedRecord_View_BT");
			wait.until(ExpectedConditions.visibilityOf(drawerCountDownPage.DrawerCountDownPopUp_HeaderText_Value));
			GenericMethods.clickOnElement(drawerCountDownPage.DrawerCountDownPopUp_CashToPull_Calender_BT, "DrawerCountDownPopUp_CashToPull_Calender_BT");
			boolean textBoxDisabled = true;
			for(WebElement qtyTextBox : drawerCountDownPage.DrawerCountDownPopUp_CalculatorTool_Quantity_TB_List){
				textBoxDisabled = textBoxDisabled && qtyTextBox.getAttribute("disabled").equals("true");
			}
			if(textBoxDisabled){
				Reporter.reportPassResult(browser,
						"User should not be able to edit text fields in Calculator Tools Page",
						"Pass");
			} else {
				Reporter.reportTestFailure(browser,
						"User should not be able to edit text fields in Calculator Tools Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
		}

}
