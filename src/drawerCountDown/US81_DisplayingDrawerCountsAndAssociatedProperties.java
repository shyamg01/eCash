package drawerCountDown;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import common.Base;
import common.GenericMethods;
import common.GlobalVariable;
import common.LoginTestData;
import common.Reporter;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.DrawerCountDownPage;
import eCashPageClasses.HomePage;

public class US81_DisplayingDrawerCountsAndAssociatedProperties extends AbstractTest{
	
	//TC3934: Verify that user is able to filter Drawer count down entries by the fields available on the page.
	@Test()
	public void drawerCountDown_US81_TC3934() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		DrawerCountDownPage drawerCountDownPage;
		AbstractTest.tcName="drawerCountDown_US81_TC3934";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		String date = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		drawerCountDownPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToDrawerCountDownPage();
		Thread.sleep(2000);
		drawerCountDownPage.selectDrawerCountDate(date).expandRolledUpDrawerCountRecords();
		Select dipositeCodeDD = new Select(drawerCountDownPage.DipositCode_DD);
		String depositCode = dipositeCodeDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForDepositCode = drawerCountDownPage.getNumberOfRecordsForFilterValue(depositCode);
		System.out.println("expectedNumberOfRecordsForDepositCode "+expectedNumberOfRecordsForDepositCode);
		dipositeCodeDD.selectByIndex(1);
		GenericMethods.clickOnElement(drawerCountDownPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForDepositCode == drawerCountDownPage.DrawerCountDown_Records_List.size()
				& expectedNumberOfRecordsForDepositCode == drawerCountDownPage.getNumberOfRecordsForFilterValue(depositCode)){
			Reporter.reportPassResult(browser,
					"User should be able to filter values based on the deposit code",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to filter values based on the deposit code",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select registerDD = new Select(drawerCountDownPage.Register_DD);
		String register = registerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForRegister = drawerCountDownPage.getNumberOfRecordsForFilterValue(register);
		registerDD.selectByIndex(1);
		GenericMethods.clickOnElement(drawerCountDownPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForRegister == drawerCountDownPage.DrawerCountDown_Records_List.size()
				& expectedNumberOfRecordsForRegister == drawerCountDownPage.getNumberOfRecordsForFilterValue(register)){
			Reporter.reportPassResult(browser,
					"User should be able to filter values based on the Register Value",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to filter values based on the Register Value",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		registerDD.selectByIndex(0);
		GenericMethods.clickOnElement(drawerCountDownPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select statusDD = new Select(drawerCountDownPage.Status_DD);
		String status = "Counted";
		int expectedNumberOfRecordsForStatus = drawerCountDownPage.getNumberOfRecordsForFilterValue(status);
		System.out.println("expectedNumberOfRecordsForStatus "+expectedNumberOfRecordsForStatus);
		statusDD.selectByVisibleText(status);
		GenericMethods.clickOnElement(drawerCountDownPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForStatus == drawerCountDownPage.DrawerCountDown_Records_List.size()
				& expectedNumberOfRecordsForStatus == drawerCountDownPage.getNumberOfRecordsForFilterValue(status)){
			Reporter.reportPassResult(browser,
					"User should be able to filter values based on the status Value",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to filter values based on the status Value",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		statusDD.selectByIndex(0);
		GenericMethods.clickOnElement(drawerCountDownPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select preparerDD = new Select(drawerCountDownPage.Preparer_DD);
		String preparer = preparerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForPreparer = drawerCountDownPage.getNumberOfRecordsForFilterValue(preparer);
		System.out.println("expectedNumberOfRecordsForPreparer "+expectedNumberOfRecordsForPreparer);
		preparerDD.selectByVisibleText(preparer);
		GenericMethods.clickOnElement(drawerCountDownPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForPreparer == drawerCountDownPage.DrawerCountDown_Records_List.size()
				& expectedNumberOfRecordsForPreparer == drawerCountDownPage.getNumberOfRecordsForFilterValue(preparer)){
			Reporter.reportPassResult(browser,
					"User should be able to filter values based on the preparer Value",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to filter values based on the preparer Value",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3935: Verfiy the fields available on drawer count down page
	@Test()
	public void drawerCountDown_US81_TC3935() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		DrawerCountDownPage drawerCountDownPage;
		AbstractTest.tcName="drawerCountDown_US81_TC3935";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		String date = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		drawerCountDownPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToDrawerCountDownPage();
		Thread.sleep(2000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//Verify user is not able to select more than current date for end date
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 0);
		String todayDate = dateFormat.format(cal1.getTime());
		if(Base.isElementDisplayed(drawerCountDownPage.DipositCode_DD)
				& Base.isElementDisplayed(drawerCountDownPage.Register_DD)
				& Base.isElementDisplayed(drawerCountDownPage.Status_DD)
				& Base.isElementDisplayed(drawerCountDownPage.Preparer_DD)
				& Base.isElementDisplayed(drawerCountDownPage.DrawerCount_Date_TB)
				& drawerCountDownPage.DrawerCount_Date_TB.getAttribute("value").equals(todayDate)){
			Reporter.reportPassResult(browser,
					"User should be able to view Deposit Status, Register, Status, Preparer droup downs in Landing page. Default business selected sould be current date",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view Deposit Status, Register, Status, Preparer droup downs in Landing page. Default business selected sould be current date",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		drawerCountDownPage.selectDrawerCountDate(date).expandRolledUpDrawerCountRecords();
		if(Base.isElementDisplayed(drawerCountDownPage.Register_Label)
				& Base.isElementDisplayed(drawerCountDownPage.DrawerCountTime_Label)
				& Base.isElementDisplayed(drawerCountDownPage.Status_Label)
				& Base.isElementDisplayed(drawerCountDownPage.Amount_Label)
				& Base.isElementDisplayed(drawerCountDownPage.DepositCode_Label)
				& Base.isElementDisplayed(drawerCountDownPage.Preparer_Label)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view Register,DrawerCount Time, Status, Amount,DepositCode, Preparer Headers in Landing page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view Register,DrawerCount Time, Status, Amount,DepositCode, Preparer Headers in Landing page.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}

}
