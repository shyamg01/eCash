package drawerCountDown;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.GenericMethods;
import common.LoginTestData;
import common.Reporter;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.DrawerCountDownPage;
import eCashPageClasses.HomePage;

public class US1_DrawerCountSummary extends AbstractTest
{
	//TC3973: Viewing and verify the system calculated field under "Expected Cash Calculations" heading on DCD detail summary modal screen.
	@Test()
	public void drawerCountDown_US1_TC3973() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		DrawerCountDownPage drawerCountDownPage;
		AbstractTest.tcName="drawerCountDown_US1_TC3973";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		drawerCountDownPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToDrawerCountDownPage();
		Thread.sleep(2000);
//		GenericMethods.clickOnElement(driver.findElement(By.xpath("//tr[@id='row-header-0']")), "firstRow");
//		Thread.sleep(2000);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();",drawerCountDownPage.DrawerCountDown_CountedRecord_View_BT);
		wait.until(ExpectedConditions.visibilityOf(drawerCountDownPage.DrawerCountDownPopUp_HeaderText_Value));
		GenericMethods.clickOnElement(drawerCountDownPage.DrawerCountDownPopUp_Next_BT, "DrawerCountDownPopUp_Next_BT");
		GenericMethods.clickOnElement(drawerCountDownPage.DrawerCountDownPopUp_CountCash_Next_BT, "DrawerCountDownPopUp_CountCash_Next_BT");
		Thread.sleep(1000);
		if(!drawerCountDownPage.DrawerCountDownPopUp_AdditionalInfo_ExpectedCashCalculation_DD.getAttribute("class").contains("chevron-down")){
			GenericMethods.clickOnElement(drawerCountDownPage.DrawerCountDownPopUp_AdditionalInfo_ExpectedCashCalculation_DD, "DrawerCountDownPopUp_AdditionalInfo_ExpectedCashCalculation_DD");
			Thread.sleep(1000);
			}
		BigDecimal calculatedExpectedCash = new BigDecimal(drawerCountDownPage.DrawerCountDownPopUp_ForeverDifference_Value.getText().replace("$","").trim()).
				subtract(new BigDecimal(drawerCountDownPage.DrawerCountDownPopUp_ManualRefundOverrings_Value.getText().replace("$", "").trim())).
				subtract(new BigDecimal(drawerCountDownPage.DrawerCountDownPopUp_BillableSales_Value.getText().replace("$", "").trim())).
				subtract(new BigDecimal(drawerCountDownPage.DrawerCountDownPopUp_GiftCertificateRedeemed_Value.getText().replace("$", "").trim())).
				subtract(new BigDecimal(drawerCountDownPage.DrawerCountDownPopUp_Skims_Value.getText().replace("$", "").trim())).
				subtract(new BigDecimal(drawerCountDownPage.DrawerCountDownPopUp_POSOverRings_Value.getText().replace("$", "").trim())).
				subtract(new BigDecimal(drawerCountDownPage.DrawerCountDownPopUp_CashRefunds_Value.getText().replace("$", "").trim())).
				subtract(new BigDecimal(drawerCountDownPage.DrawerCountDownPopUp_CashLessSales_Value.getText().replace("$", "").trim())).
				subtract(new BigDecimal(drawerCountDownPage.DrawerCountDownPopUp_ArchCardRedeemed_Value.getText().replace("$", "").trim()));
		if(calculatedExpectedCash.equals(new BigDecimal(drawerCountDownPage.DrawerCountDownPopUp_ExpectedCash_Value.getText().replace("$", "").trim()))){
			Reporter.reportPassResult(browser,
					"Value of Forever Difference field displayed should be same as the value calculated by the formula.",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"Value of Forever Difference field displayed should be same as the value calculated by the formula.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	// TC3975: Viewing and verify that Cash Over/Short is system calculated field under the Summary tab.
	@Test()
	public void drawerCountDown_US1_TC3975() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException,
			ParseException {
		/** Variable Section : **/
		DrawerCountDownPage drawerCountDownPage;
		AbstractTest.tcName = "drawerCountDown_US1_TC3975";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		drawerCountDownPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToDrawerCountDownPage();
		Thread.sleep(2000);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();",drawerCountDownPage.DrawerCountDown_CountedRecord_View_BT);
		wait.until(ExpectedConditions.visibilityOf(drawerCountDownPage.DrawerCountDownPopUp_HeaderText_Value));
		GenericMethods.clickOnElement(drawerCountDownPage.DrawerCountDownPopUp_Next_BT, "DrawerCountDownPopUp_Next_BT");
		GenericMethods.clickOnElement(drawerCountDownPage.DrawerCountDownPopUp_CountCash_Next_BT, "DrawerCountDownPopUp_CountCash_Next_BT");
		Thread.sleep(1000);
		BigDecimal calculatedCashOverShort = new BigDecimal(drawerCountDownPage.DrawerCountDownPopUp_CountedCash_Value.getText().replace("$","").trim()).
					add(new BigDecimal(drawerCountDownPage.DrawerCountDownPopUp_ForeignCurrency_Value.getText().replace("$", "").trim())).
					subtract(new BigDecimal(drawerCountDownPage.DrawerCountDownPopUp_ExpectedDrawerCash_Value.getText().replace("$", "").trim()));
		String cashOverSort = drawerCountDownPage.DrawerCountDownPopUp_CashOverShort_Value.getText();
		BigDecimal actualCashOverShort = new BigDecimal(cashOverSort.replace("$", "").replace("-", "").trim());
		if(cashOverSort.contains("-")){
			actualCashOverShort = actualCashOverShort.negate();
		}
		if (calculatedCashOverShort.equals(actualCashOverShort)) {
			Reporter.reportPassResult(
					browser,
					"Cash Over/Short should be calculated by system",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Cash Over/Short should be calculated by system",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
}
