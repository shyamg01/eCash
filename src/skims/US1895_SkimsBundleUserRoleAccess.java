package skims;

import java.io.IOException;
import java.text.ParseException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import common.Base;
import common.GenericMethods;
import common.GlobalVariable;
import common.LoginTestData;
import common.Reporter;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.HomePage;
import eCashPageClasses.SkimsPage;

public class US1895_SkimsBundleUserRoleAccess extends AbstractTest{
	
	//TC3549: Verify that "Level6" user NOT have access to the Skims page or skims functions on cloud.
	@Test()
	public void skims_US1895_TC3549() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		AbstractTest.tcName="skims_US1895_TC3549";
		String password = LoginTestData.level6_SSO_Password;
		String userId = LoginTestData.level6_SSO_UserId;
		String storeId = LoginTestData.level6StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId);
		GenericMethods.clickOnElement(wait.until(ExpectedConditions.elementToBeClickable(homePage.Menu_DD_BT)), "homePage.Menu_DD_BT");
		Thread.sleep(1500);
		if(!Base.isElementDisplayed(homePage.Skims_BT)){
			Reporter.reportPassResult(
					browser,
					"Level 6 User should not be able to navigate to skims Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Level 6 User should not be able to navigate to skims Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3550: Verify that "Organization Admin" user have access to viewing Skims in the cloud.
	@Test()
	public void skims_US1895_TC3550() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US1895_TC3550";
		String password = LoginTestData.orgAdmin_SSO_Password;
		String userId = LoginTestData.orgAdmin_SSO_UserId;
		String storeId = LoginTestData.orgAdminStoreId;
		String date = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToSkimsPage();
		skimsPage.selectSkimsDate(date).expandRolledUpSkimsRecords();
		GenericMethods.clickOnElement(skimsPage.Skims_CountedRecord_View_BT, "Skims_CountedRecord_View_BT");
		wait.until(ExpectedConditions.visibilityOf(skimsPage.Skim_PopUp_Label));
		if(skimsPage.Skim_PopUp_Register_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Time_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_DepositCode_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Manager_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_EnvelopID_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Amount_TB.getAttribute("disabled").equals("true")){
			Reporter.reportPassResult(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(skimsPage.Skims_PopUp_Cancel_BT, "Skims_PopUp_Cancel_BT");
		if(skimsPage.Skims_RolledUpRecord_List.size() == 0){
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInAscendinOrder = skimsPage.verifySkimsDateInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInDecendinOrder = skimsPage.verifySkimsDateInDescendingOrder();
			if (dateInAscendinOrder & dateInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInAscendinOrder = skimsPage.verifySkimsDepositCodeInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInDecendinOrder = skimsPage.verifySkimsDepositCodeInDescendingOrder();
			if (depositCodeInAscendinOrder & depositCodeInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInAscendinOrder = skimsPage.verifySkimsRegisterInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInDecendinOrder = skimsPage.verifySkimsRegisterInDescendingOrder();
			if (registerInAscendinOrder & registerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInAscendinOrder = skimsPage.verifySkimsAmountInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInDecendinOrder = skimsPage.verifySkimsAmountInDescendingOrder();
			if (amountInAscendinOrder & amountInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInAscendinOrder = skimsPage.verifySkimsPreparerInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInDecendinOrder = skimsPage.verifySkimsPreparerInDescendingOrder();
			if (preparerInAscendinOrder & preparerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInAscendinOrder = skimsPage.verifySkimsSourceInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInDecendinOrder = skimsPage.verifySkimsSourceInDescendingOrder();
			if (sourceInAscendinOrder & sourceInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInAscendinOrder = skimsPage.verifySkimsStatusInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInDecendinOrder = skimsPage.verifySkimsStatusInDescendingOrder();
			if (statusInAscendinOrder & statusInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
		}else{
			Reporter.reportPassResult(
					browser,
					"Rolled up records can not be sorted",
					"Pass");
		}
		Select dipositeCodeDD = new Select(skimsPage.SkimsDepositCode_DD);
		String depositCode = dipositeCodeDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForDepositCode = skimsPage.getNumberOfRecordsForFilterValue(depositCode);
		System.out.println("expectedNumberOfRecordsForDepositCode "+expectedNumberOfRecordsForDepositCode);
		dipositeCodeDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForDepositCode == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForDepositCode == skimsPage.getNumberOfRecordsForFilterValue(depositCode)){
			Reporter.reportPassResult(browser,
					"User should be able to filter values based on the deposit code",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to filter values based on the deposit code",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select registerDD = new Select(skimsPage.Skims_Register_DD);
		String register = registerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForRegister = skimsPage.getNumberOfRecordsForFilterValue(register);
		registerDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForRegister == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForRegister == skimsPage.getNumberOfRecordsForFilterValue(register)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select statusDD = new Select(skimsPage.SkimsStatus_DD);
		String status = "Counted";
		int expectedNumberOfRecordsForStatus = skimsPage.getNumberOfRecordsForFilterValue(status);
		System.out.println("expectedNumberOfRecordsForStatus "+expectedNumberOfRecordsForStatus);
		statusDD.selectByVisibleText(status.toLowerCase());
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForStatus == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForStatus == skimsPage.getNumberOfRecordsForFilterValue(status)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select preparerDD = new Select(skimsPage.SkimsPreparer_DD);
		String preparer = preparerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForPreparer = skimsPage.getNumberOfRecordsForFilterValue(preparer);
		System.out.println("expectedNumberOfRecordsForPreparer "+expectedNumberOfRecordsForPreparer);
		preparerDD.selectByVisibleText(preparer);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForPreparer == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForPreparer == skimsPage.getNumberOfRecordsForFilterValue(preparer)){
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
	
	//TC3551: Verify that "Supervisor w/ Role Assignment" user have access to viewing Skims in the cloud.
	@Test()
	public void skims_US1895_TC3551() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US1895_TC3551";
		String password = LoginTestData.supervisorWithRoleAssignment_SSO_Password;
		String userId = LoginTestData.supervisorWithRoleAssignment_SSO_UserId;
		String storeId = LoginTestData.supervisorWithRoleAssignmentStoreId;
		String date = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToSkimsPage();
		skimsPage.selectSkimsDate(date).expandRolledUpSkimsRecords();
		GenericMethods.clickOnElement(skimsPage.Skims_CountedRecord_View_BT, "Skims_CountedRecord_View_BT");
		wait.until(ExpectedConditions.visibilityOf(skimsPage.Skim_PopUp_Label));
		if(skimsPage.Skim_PopUp_Register_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Time_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_DepositCode_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Manager_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_EnvelopID_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Amount_TB.getAttribute("disabled").equals("true")){
			Reporter.reportPassResult(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(skimsPage.Skims_PopUp_Cancel_BT, "Skims_PopUp_Cancel_BT");
		if(skimsPage.Skims_RolledUpRecord_List.size() == 0){
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInAscendinOrder = skimsPage.verifySkimsDateInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInDecendinOrder = skimsPage.verifySkimsDateInDescendingOrder();
			if (dateInAscendinOrder & dateInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInAscendinOrder = skimsPage.verifySkimsDepositCodeInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInDecendinOrder = skimsPage.verifySkimsDepositCodeInDescendingOrder();
			if (depositCodeInAscendinOrder & depositCodeInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInAscendinOrder = skimsPage.verifySkimsRegisterInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInDecendinOrder = skimsPage.verifySkimsRegisterInDescendingOrder();
			if (registerInAscendinOrder & registerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInAscendinOrder = skimsPage.verifySkimsAmountInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInDecendinOrder = skimsPage.verifySkimsAmountInDescendingOrder();
			if (amountInAscendinOrder & amountInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInAscendinOrder = skimsPage.verifySkimsPreparerInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInDecendinOrder = skimsPage.verifySkimsPreparerInDescendingOrder();
			if (preparerInAscendinOrder & preparerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInAscendinOrder = skimsPage.verifySkimsSourceInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInDecendinOrder = skimsPage.verifySkimsSourceInDescendingOrder();
			if (sourceInAscendinOrder & sourceInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInAscendinOrder = skimsPage.verifySkimsStatusInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInDecendinOrder = skimsPage.verifySkimsStatusInDescendingOrder();
			if (statusInAscendinOrder & statusInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
		}else{
			Reporter.reportPassResult(
					browser,
					"Rolled up records can not be sorted",
					"Pass");
		}
		Select dipositeCodeDD = new Select(skimsPage.SkimsDepositCode_DD);
		String depositCode = dipositeCodeDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForDepositCode = skimsPage.getNumberOfRecordsForFilterValue(depositCode);
		System.out.println("expectedNumberOfRecordsForDepositCode "+expectedNumberOfRecordsForDepositCode);
		dipositeCodeDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForDepositCode == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForDepositCode == skimsPage.getNumberOfRecordsForFilterValue(depositCode)){
			Reporter.reportPassResult(browser,
					"User should be able to filter values based on the deposit code",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to filter values based on the deposit code",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select registerDD = new Select(skimsPage.Skims_Register_DD);
		String register = registerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForRegister = skimsPage.getNumberOfRecordsForFilterValue(register);
		registerDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForRegister == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForRegister == skimsPage.getNumberOfRecordsForFilterValue(register)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select statusDD = new Select(skimsPage.SkimsStatus_DD);
		String status = "Counted";
		int expectedNumberOfRecordsForStatus = skimsPage.getNumberOfRecordsForFilterValue(status);
		System.out.println("expectedNumberOfRecordsForStatus "+expectedNumberOfRecordsForStatus);
		statusDD.selectByVisibleText(status.toLowerCase());
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForStatus == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForStatus == skimsPage.getNumberOfRecordsForFilterValue(status)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select preparerDD = new Select(skimsPage.SkimsPreparer_DD);
		String preparer = preparerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForPreparer = skimsPage.getNumberOfRecordsForFilterValue(preparer);
		System.out.println("expectedNumberOfRecordsForPreparer "+expectedNumberOfRecordsForPreparer);
		preparerDD.selectByVisibleText(preparer);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForPreparer == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForPreparer == skimsPage.getNumberOfRecordsForFilterValue(preparer)){
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
	
	//TC3552:  	Verify that "Supervisor" user have access to viewing Skims in the cloud.
	@Test()
	public void skims_US1895_TC3552() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US1895_TC3552";
		String password = LoginTestData.supervisor_SSO_Password;
		String userId = LoginTestData.supervisor_SSO_UserId;
		String storeId = LoginTestData.supervisorStoreId;
		String date = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToSkimsPage();
		skimsPage.selectSkimsDate(date).expandRolledUpSkimsRecords();
		GenericMethods.clickOnElement(skimsPage.Skims_CountedRecord_View_BT, "Skims_CountedRecord_View_BT");
		wait.until(ExpectedConditions.visibilityOf(skimsPage.Skim_PopUp_Label));
		if(skimsPage.Skim_PopUp_Register_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Time_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_DepositCode_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Manager_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_EnvelopID_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Amount_TB.getAttribute("disabled").equals("true")){
			Reporter.reportPassResult(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(skimsPage.Skims_PopUp_Cancel_BT, "Skims_PopUp_Cancel_BT");
		if(skimsPage.Skims_RolledUpRecord_List.size() == 0){
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInAscendinOrder = skimsPage.verifySkimsDateInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInDecendinOrder = skimsPage.verifySkimsDateInDescendingOrder();
			if (dateInAscendinOrder & dateInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInAscendinOrder = skimsPage.verifySkimsDepositCodeInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInDecendinOrder = skimsPage.verifySkimsDepositCodeInDescendingOrder();
			if (depositCodeInAscendinOrder & depositCodeInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInAscendinOrder = skimsPage.verifySkimsRegisterInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInDecendinOrder = skimsPage.verifySkimsRegisterInDescendingOrder();
			if (registerInAscendinOrder & registerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInAscendinOrder = skimsPage.verifySkimsAmountInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInDecendinOrder = skimsPage.verifySkimsAmountInDescendingOrder();
			if (amountInAscendinOrder & amountInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInAscendinOrder = skimsPage.verifySkimsPreparerInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInDecendinOrder = skimsPage.verifySkimsPreparerInDescendingOrder();
			if (preparerInAscendinOrder & preparerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInAscendinOrder = skimsPage.verifySkimsSourceInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInDecendinOrder = skimsPage.verifySkimsSourceInDescendingOrder();
			if (sourceInAscendinOrder & sourceInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInAscendinOrder = skimsPage.verifySkimsStatusInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInDecendinOrder = skimsPage.verifySkimsStatusInDescendingOrder();
			if (statusInAscendinOrder & statusInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
		}else{
			Reporter.reportPassResult(
					browser,
					"Rolled up records can not be sorted",
					"Pass");
		}
		Select dipositeCodeDD = new Select(skimsPage.SkimsDepositCode_DD);
		String depositCode = dipositeCodeDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForDepositCode = skimsPage.getNumberOfRecordsForFilterValue(depositCode);
		System.out.println("expectedNumberOfRecordsForDepositCode "+expectedNumberOfRecordsForDepositCode);
		dipositeCodeDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForDepositCode == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForDepositCode == skimsPage.getNumberOfRecordsForFilterValue(depositCode)){
			Reporter.reportPassResult(browser,
					"User should be able to filter values based on the deposit code",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to filter values based on the deposit code",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select registerDD = new Select(skimsPage.Skims_Register_DD);
		String register = registerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForRegister = skimsPage.getNumberOfRecordsForFilterValue(register);
		registerDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForRegister == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForRegister == skimsPage.getNumberOfRecordsForFilterValue(register)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select statusDD = new Select(skimsPage.SkimsStatus_DD);
		String status = "Counted";
		int expectedNumberOfRecordsForStatus = skimsPage.getNumberOfRecordsForFilterValue(status);
		System.out.println("expectedNumberOfRecordsForStatus "+expectedNumberOfRecordsForStatus);
		statusDD.selectByVisibleText(status.toLowerCase());
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForStatus == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForStatus == skimsPage.getNumberOfRecordsForFilterValue(status)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select preparerDD = new Select(skimsPage.SkimsPreparer_DD);
		String preparer = preparerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForPreparer = skimsPage.getNumberOfRecordsForFilterValue(preparer);
		System.out.println("expectedNumberOfRecordsForPreparer "+expectedNumberOfRecordsForPreparer);
		preparerDD.selectByVisibleText(preparer);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForPreparer == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForPreparer == skimsPage.getNumberOfRecordsForFilterValue(preparer)){
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
	
	//TC3554:  Verify that "Level 5" user have access to viewing Skims in the cloud.
	@Test()
	public void skims_US1895_TC3553() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US1895_TC3553";
		String password = LoginTestData.level5_SSO_Password;
		String userId = LoginTestData.level5_SSO_UserId;
		String storeId = LoginTestData.level5StoreId;
		String date = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToSkimsPage();
		skimsPage.selectSkimsDate(date).expandRolledUpSkimsRecords();
		GenericMethods.clickOnElement(skimsPage.Skims_CountedRecord_View_BT, "Skims_CountedRecord_View_BT");
		wait.until(ExpectedConditions.visibilityOf(skimsPage.Skim_PopUp_Label));
		if(skimsPage.Skim_PopUp_Register_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Time_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_DepositCode_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Manager_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_EnvelopID_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Amount_TB.getAttribute("disabled").equals("true")){
			Reporter.reportPassResult(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(skimsPage.Skims_PopUp_Cancel_BT, "Skims_PopUp_Cancel_BT");
		if(skimsPage.Skims_RolledUpRecord_List.size() == 0){
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInAscendinOrder = skimsPage.verifySkimsDateInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInDecendinOrder = skimsPage.verifySkimsDateInDescendingOrder();
			if (dateInAscendinOrder & dateInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInAscendinOrder = skimsPage.verifySkimsDepositCodeInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInDecendinOrder = skimsPage.verifySkimsDepositCodeInDescendingOrder();
			if (depositCodeInAscendinOrder & depositCodeInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInAscendinOrder = skimsPage.verifySkimsRegisterInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInDecendinOrder = skimsPage.verifySkimsRegisterInDescendingOrder();
			if (registerInAscendinOrder & registerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInAscendinOrder = skimsPage.verifySkimsAmountInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInDecendinOrder = skimsPage.verifySkimsAmountInDescendingOrder();
			if (amountInAscendinOrder & amountInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInAscendinOrder = skimsPage.verifySkimsPreparerInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInDecendinOrder = skimsPage.verifySkimsPreparerInDescendingOrder();
			if (preparerInAscendinOrder & preparerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInAscendinOrder = skimsPage.verifySkimsSourceInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInDecendinOrder = skimsPage.verifySkimsSourceInDescendingOrder();
			if (sourceInAscendinOrder & sourceInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInAscendinOrder = skimsPage.verifySkimsStatusInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInDecendinOrder = skimsPage.verifySkimsStatusInDescendingOrder();
			if (statusInAscendinOrder & statusInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
		}else{
			Reporter.reportPassResult(
					browser,
					"Rolled up records can not be sorted",
					"Pass");
		}
		Select dipositeCodeDD = new Select(skimsPage.SkimsDepositCode_DD);
		String depositCode = dipositeCodeDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForDepositCode = skimsPage.getNumberOfRecordsForFilterValue(depositCode);
		System.out.println("expectedNumberOfRecordsForDepositCode "+expectedNumberOfRecordsForDepositCode);
		dipositeCodeDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForDepositCode == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForDepositCode == skimsPage.getNumberOfRecordsForFilterValue(depositCode)){
			Reporter.reportPassResult(browser,
					"User should be able to filter values based on the deposit code",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to filter values based on the deposit code",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select registerDD = new Select(skimsPage.Skims_Register_DD);
		String register = registerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForRegister = skimsPage.getNumberOfRecordsForFilterValue(register);
		registerDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForRegister == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForRegister == skimsPage.getNumberOfRecordsForFilterValue(register)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select statusDD = new Select(skimsPage.SkimsStatus_DD);
		String status = "Counted";
		int expectedNumberOfRecordsForStatus = skimsPage.getNumberOfRecordsForFilterValue(status);
		System.out.println("expectedNumberOfRecordsForStatus "+expectedNumberOfRecordsForStatus);
		statusDD.selectByVisibleText(status.toLowerCase());
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForStatus == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForStatus == skimsPage.getNumberOfRecordsForFilterValue(status)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select preparerDD = new Select(skimsPage.SkimsPreparer_DD);
		String preparer = preparerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForPreparer = skimsPage.getNumberOfRecordsForFilterValue(preparer);
		System.out.println("expectedNumberOfRecordsForPreparer "+expectedNumberOfRecordsForPreparer);
		preparerDD.selectByVisibleText(preparer);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForPreparer == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForPreparer == skimsPage.getNumberOfRecordsForFilterValue(preparer)){
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
	
	//TC3554:  Verify that "Level 4" user have access to viewing Skims in the cloud.
	@Test(enabled = false)
	public void skims_US1895_TC3554() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US1895_TC3554";
		String password = LoginTestData.level4_SSO_Password;
		String userId = LoginTestData.level4_SSO_UserId;
		String storeId = LoginTestData.level4UserId;
		String date = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToSkimsPage();
		skimsPage.selectSkimsDate(date).expandRolledUpSkimsRecords();
		GenericMethods.clickOnElement(skimsPage.Skims_CountedRecord_View_BT, "Skims_CountedRecord_View_BT");
		wait.until(ExpectedConditions.visibilityOf(skimsPage.Skim_PopUp_Label));
		if(skimsPage.Skim_PopUp_Register_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Time_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_DepositCode_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Manager_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_EnvelopID_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Amount_TB.getAttribute("disabled").equals("true")){
			Reporter.reportPassResult(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(skimsPage.Skims_PopUp_Cancel_BT, "Skims_PopUp_Cancel_BT");
		if(skimsPage.Skims_RolledUpRecord_List.size() == 0){
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInAscendinOrder = skimsPage.verifySkimsDateInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInDecendinOrder = skimsPage.verifySkimsDateInDescendingOrder();
			if (dateInAscendinOrder & dateInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInAscendinOrder = skimsPage.verifySkimsDepositCodeInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInDecendinOrder = skimsPage.verifySkimsDepositCodeInDescendingOrder();
			if (depositCodeInAscendinOrder & depositCodeInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInAscendinOrder = skimsPage.verifySkimsRegisterInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInDecendinOrder = skimsPage.verifySkimsRegisterInDescendingOrder();
			if (registerInAscendinOrder & registerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInAscendinOrder = skimsPage.verifySkimsAmountInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInDecendinOrder = skimsPage.verifySkimsAmountInDescendingOrder();
			if (amountInAscendinOrder & amountInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInAscendinOrder = skimsPage.verifySkimsPreparerInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInDecendinOrder = skimsPage.verifySkimsPreparerInDescendingOrder();
			if (preparerInAscendinOrder & preparerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInAscendinOrder = skimsPage.verifySkimsSourceInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInDecendinOrder = skimsPage.verifySkimsSourceInDescendingOrder();
			if (sourceInAscendinOrder & sourceInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInAscendinOrder = skimsPage.verifySkimsStatusInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInDecendinOrder = skimsPage.verifySkimsStatusInDescendingOrder();
			if (statusInAscendinOrder & statusInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
		}else{
			Reporter.reportPassResult(
					browser,
					"Rolled up records can not be sorted",
					"Pass");
		}
		Select dipositeCodeDD = new Select(skimsPage.SkimsDepositCode_DD);
		String depositCode = dipositeCodeDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForDepositCode = skimsPage.getNumberOfRecordsForFilterValue(depositCode);
		System.out.println("expectedNumberOfRecordsForDepositCode "+expectedNumberOfRecordsForDepositCode);
		dipositeCodeDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForDepositCode == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForDepositCode == skimsPage.getNumberOfRecordsForFilterValue(depositCode)){
			Reporter.reportPassResult(browser,
					"User should be able to filter values based on the deposit code",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to filter values based on the deposit code",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select registerDD = new Select(skimsPage.Skims_Register_DD);
		String register = registerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForRegister = skimsPage.getNumberOfRecordsForFilterValue(register);
		registerDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForRegister == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForRegister == skimsPage.getNumberOfRecordsForFilterValue(register)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select statusDD = new Select(skimsPage.SkimsStatus_DD);
		String status = "Counted";
		int expectedNumberOfRecordsForStatus = skimsPage.getNumberOfRecordsForFilterValue(status);
		System.out.println("expectedNumberOfRecordsForStatus "+expectedNumberOfRecordsForStatus);
		statusDD.selectByVisibleText(status.toLowerCase());
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForStatus == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForStatus == skimsPage.getNumberOfRecordsForFilterValue(status)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select preparerDD = new Select(skimsPage.SkimsPreparer_DD);
		String preparer = preparerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForPreparer = skimsPage.getNumberOfRecordsForFilterValue(preparer);
		System.out.println("expectedNumberOfRecordsForPreparer "+expectedNumberOfRecordsForPreparer);
		preparerDD.selectByVisibleText(preparer);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForPreparer == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForPreparer == skimsPage.getNumberOfRecordsForFilterValue(preparer)){
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
	
	//TC3555: Verify that "Level 3" user have access to viewing Skims in the cloud.
	@Test()
	public void skims_US1895_TC3555() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US1895_TC3555";
		String password = LoginTestData.level3_SSO_Password;
		String userId = LoginTestData.level3_SSO_UserId;
		String storeId = LoginTestData.level3StoreId;
		String date = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToSkimsPage();
		skimsPage.selectSkimsDate(date).expandRolledUpSkimsRecords();
		GenericMethods.clickOnElement(skimsPage.Skims_CountedRecord_View_BT, "Skims_CountedRecord_View_BT");
		wait.until(ExpectedConditions.visibilityOf(skimsPage.Skim_PopUp_Label));
		if(skimsPage.Skim_PopUp_Register_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Time_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_DepositCode_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Manager_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_EnvelopID_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Amount_TB.getAttribute("disabled").equals("true")){
			Reporter.reportPassResult(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(skimsPage.Skims_PopUp_Cancel_BT, "Skims_PopUp_Cancel_BT");
		if(skimsPage.Skims_RolledUpRecord_List.size() == 0){
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInAscendinOrder = skimsPage.verifySkimsDateInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInDecendinOrder = skimsPage.verifySkimsDateInDescendingOrder();
			if (dateInAscendinOrder & dateInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInAscendinOrder = skimsPage.verifySkimsDepositCodeInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInDecendinOrder = skimsPage.verifySkimsDepositCodeInDescendingOrder();
			if (depositCodeInAscendinOrder & depositCodeInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInAscendinOrder = skimsPage.verifySkimsRegisterInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInDecendinOrder = skimsPage.verifySkimsRegisterInDescendingOrder();
			if (registerInAscendinOrder & registerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInAscendinOrder = skimsPage.verifySkimsAmountInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInDecendinOrder = skimsPage.verifySkimsAmountInDescendingOrder();
			if (amountInAscendinOrder & amountInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInAscendinOrder = skimsPage.verifySkimsPreparerInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInDecendinOrder = skimsPage.verifySkimsPreparerInDescendingOrder();
			if (preparerInAscendinOrder & preparerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInAscendinOrder = skimsPage.verifySkimsSourceInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInDecendinOrder = skimsPage.verifySkimsSourceInDescendingOrder();
			if (sourceInAscendinOrder & sourceInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInAscendinOrder = skimsPage.verifySkimsStatusInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInDecendinOrder = skimsPage.verifySkimsStatusInDescendingOrder();
			if (statusInAscendinOrder & statusInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
		}else{
			Reporter.reportPassResult(
					browser,
					"Rolled up records can not be sorted",
					"Pass");
		}
		Select dipositeCodeDD = new Select(skimsPage.SkimsDepositCode_DD);
		String depositCode = dipositeCodeDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForDepositCode = skimsPage.getNumberOfRecordsForFilterValue(depositCode);
		System.out.println("expectedNumberOfRecordsForDepositCode "+expectedNumberOfRecordsForDepositCode);
		dipositeCodeDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForDepositCode == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForDepositCode == skimsPage.getNumberOfRecordsForFilterValue(depositCode)){
			Reporter.reportPassResult(browser,
					"User should be able to filter values based on the deposit code",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to filter values based on the deposit code",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select registerDD = new Select(skimsPage.Skims_Register_DD);
		String register = registerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForRegister = skimsPage.getNumberOfRecordsForFilterValue(register);
		registerDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForRegister == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForRegister == skimsPage.getNumberOfRecordsForFilterValue(register)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select statusDD = new Select(skimsPage.SkimsStatus_DD);
		String status = "Counted";
		int expectedNumberOfRecordsForStatus = skimsPage.getNumberOfRecordsForFilterValue(status);
		System.out.println("expectedNumberOfRecordsForStatus "+expectedNumberOfRecordsForStatus);
		statusDD.selectByVisibleText(status.toLowerCase());
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForStatus == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForStatus == skimsPage.getNumberOfRecordsForFilterValue(status)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select preparerDD = new Select(skimsPage.SkimsPreparer_DD);
		String preparer = preparerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForPreparer = skimsPage.getNumberOfRecordsForFilterValue(preparer);
		System.out.println("expectedNumberOfRecordsForPreparer "+expectedNumberOfRecordsForPreparer);
		preparerDD.selectByVisibleText(preparer);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForPreparer == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForPreparer == skimsPage.getNumberOfRecordsForFilterValue(preparer)){
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
	
	//TC3555: Verify that "Level 2" user have access to viewing Skims in the cloud.
	@Test()
	public void skims_US1895_TC3556() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US1895_TC3556";
		String password = LoginTestData.level2_SSO_Password;
		String userId = LoginTestData.level2_SSO_UserId;
		String storeId = LoginTestData.level2StoreId;
		String date = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToSkimsPage();
		skimsPage.selectSkimsDate(date).expandRolledUpSkimsRecords();
		GenericMethods.clickOnElement(skimsPage.Skims_CountedRecord_View_BT, "Skims_CountedRecord_View_BT");
		wait.until(ExpectedConditions.visibilityOf(skimsPage.Skim_PopUp_Label));
		if(skimsPage.Skim_PopUp_Register_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Time_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_DepositCode_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Manager_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_EnvelopID_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Amount_TB.getAttribute("disabled").equals("true")){
			Reporter.reportPassResult(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(skimsPage.Skims_PopUp_Cancel_BT, "Skims_PopUp_Cancel_BT");
		if(skimsPage.Skims_RolledUpRecord_List.size() == 0){
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInAscendinOrder = skimsPage.verifySkimsDateInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInDecendinOrder = skimsPage.verifySkimsDateInDescendingOrder();
			if (dateInAscendinOrder & dateInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInAscendinOrder = skimsPage.verifySkimsDepositCodeInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInDecendinOrder = skimsPage.verifySkimsDepositCodeInDescendingOrder();
			if (depositCodeInAscendinOrder & depositCodeInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInAscendinOrder = skimsPage.verifySkimsRegisterInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInDecendinOrder = skimsPage.verifySkimsRegisterInDescendingOrder();
			if (registerInAscendinOrder & registerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInAscendinOrder = skimsPage.verifySkimsAmountInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInDecendinOrder = skimsPage.verifySkimsAmountInDescendingOrder();
			if (amountInAscendinOrder & amountInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInAscendinOrder = skimsPage.verifySkimsPreparerInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInDecendinOrder = skimsPage.verifySkimsPreparerInDescendingOrder();
			if (preparerInAscendinOrder & preparerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInAscendinOrder = skimsPage.verifySkimsSourceInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInDecendinOrder = skimsPage.verifySkimsSourceInDescendingOrder();
			if (sourceInAscendinOrder & sourceInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInAscendinOrder = skimsPage.verifySkimsStatusInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInDecendinOrder = skimsPage.verifySkimsStatusInDescendingOrder();
			if (statusInAscendinOrder & statusInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
		}else{
			Reporter.reportPassResult(
					browser,
					"Rolled up records can not be sorted",
					"Pass");
		}
		Select dipositeCodeDD = new Select(skimsPage.SkimsDepositCode_DD);
		String depositCode = dipositeCodeDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForDepositCode = skimsPage.getNumberOfRecordsForFilterValue(depositCode);
		System.out.println("expectedNumberOfRecordsForDepositCode "+expectedNumberOfRecordsForDepositCode);
		dipositeCodeDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForDepositCode == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForDepositCode == skimsPage.getNumberOfRecordsForFilterValue(depositCode)){
			Reporter.reportPassResult(browser,
					"User should be able to filter values based on the deposit code",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to filter values based on the deposit code",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select registerDD = new Select(skimsPage.Skims_Register_DD);
		String register = registerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForRegister = skimsPage.getNumberOfRecordsForFilterValue(register);
		registerDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForRegister == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForRegister == skimsPage.getNumberOfRecordsForFilterValue(register)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select statusDD = new Select(skimsPage.SkimsStatus_DD);
		String status = "Counted";
		int expectedNumberOfRecordsForStatus = skimsPage.getNumberOfRecordsForFilterValue(status);
		System.out.println("expectedNumberOfRecordsForStatus "+expectedNumberOfRecordsForStatus);
		statusDD.selectByVisibleText(status.toLowerCase());
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForStatus == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForStatus == skimsPage.getNumberOfRecordsForFilterValue(status)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select preparerDD = new Select(skimsPage.SkimsPreparer_DD);
		String preparer = preparerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForPreparer = skimsPage.getNumberOfRecordsForFilterValue(preparer);
		System.out.println("expectedNumberOfRecordsForPreparer "+expectedNumberOfRecordsForPreparer);
		preparerDD.selectByVisibleText(preparer);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForPreparer == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForPreparer == skimsPage.getNumberOfRecordsForFilterValue(preparer)){
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
	
	//TC3557: Verify that "Level 1" user have access to viewing Skims in the cloud.
	@Test()
	public void skims_US1895_TC3557() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US1895_TC3557";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		String date = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToSkimsPage();
		skimsPage.selectSkimsDate(date).expandRolledUpSkimsRecords();
		GenericMethods.clickOnElement(skimsPage.Skims_CountedRecord_View_BT, "Skims_CountedRecord_View_BT");
		wait.until(ExpectedConditions.visibilityOf(skimsPage.Skim_PopUp_Label));
		if(skimsPage.Skim_PopUp_Register_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Time_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_DepositCode_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Manager_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_EnvelopID_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Amount_TB.getAttribute("disabled").equals("true")){
			Reporter.reportPassResult(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(skimsPage.Skims_PopUp_Cancel_BT, "Skims_PopUp_Cancel_BT");
		if(skimsPage.Skims_RolledUpRecord_List.size() == 0){
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInAscendinOrder = skimsPage.verifySkimsDateInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInDecendinOrder = skimsPage.verifySkimsDateInDescendingOrder();
			if (dateInAscendinOrder & dateInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInAscendinOrder = skimsPage.verifySkimsDepositCodeInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInDecendinOrder = skimsPage.verifySkimsDepositCodeInDescendingOrder();
			if (depositCodeInAscendinOrder & depositCodeInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInAscendinOrder = skimsPage.verifySkimsRegisterInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInDecendinOrder = skimsPage.verifySkimsRegisterInDescendingOrder();
			if (registerInAscendinOrder & registerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInAscendinOrder = skimsPage.verifySkimsAmountInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInDecendinOrder = skimsPage.verifySkimsAmountInDescendingOrder();
			if (amountInAscendinOrder & amountInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInAscendinOrder = skimsPage.verifySkimsPreparerInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInDecendinOrder = skimsPage.verifySkimsPreparerInDescendingOrder();
			if (preparerInAscendinOrder & preparerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInAscendinOrder = skimsPage.verifySkimsSourceInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInDecendinOrder = skimsPage.verifySkimsSourceInDescendingOrder();
			if (sourceInAscendinOrder & sourceInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInAscendinOrder = skimsPage.verifySkimsStatusInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInDecendinOrder = skimsPage.verifySkimsStatusInDescendingOrder();
			if (statusInAscendinOrder & statusInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
		}else{
			Reporter.reportPassResult(
					browser,
					"Rolled up records can not be sorted",
					"Pass");
		}
		Select dipositeCodeDD = new Select(skimsPage.SkimsDepositCode_DD);
		String depositCode = dipositeCodeDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForDepositCode = skimsPage.getNumberOfRecordsForFilterValue(depositCode);
		System.out.println("expectedNumberOfRecordsForDepositCode "+expectedNumberOfRecordsForDepositCode);
		dipositeCodeDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForDepositCode == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForDepositCode == skimsPage.getNumberOfRecordsForFilterValue(depositCode)){
			Reporter.reportPassResult(browser,
					"User should be able to filter values based on the deposit code",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to filter values based on the deposit code",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select registerDD = new Select(skimsPage.Skims_Register_DD);
		String register = registerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForRegister = skimsPage.getNumberOfRecordsForFilterValue(register);
		registerDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForRegister == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForRegister == skimsPage.getNumberOfRecordsForFilterValue(register)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select statusDD = new Select(skimsPage.SkimsStatus_DD);
		String status = "Counted";
		int expectedNumberOfRecordsForStatus = skimsPage.getNumberOfRecordsForFilterValue(status);
		System.out.println("expectedNumberOfRecordsForStatus "+expectedNumberOfRecordsForStatus);
		statusDD.selectByVisibleText(status.toLowerCase());
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForStatus == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForStatus == skimsPage.getNumberOfRecordsForFilterValue(status)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select preparerDD = new Select(skimsPage.SkimsPreparer_DD);
		String preparer = preparerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForPreparer = skimsPage.getNumberOfRecordsForFilterValue(preparer);
		System.out.println("expectedNumberOfRecordsForPreparer "+expectedNumberOfRecordsForPreparer);
		preparerDD.selectByVisibleText(preparer);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForPreparer == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForPreparer == skimsPage.getNumberOfRecordsForFilterValue(preparer)){
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
	
	//TC3558: Verify that Operator user have access to viewing Skims in the cloud.
	@Test()
	public void skims_US1895_TC3558() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US1895_TC3558";
		String password = LoginTestData.operator_SSO_Password;
		String userId = LoginTestData.operator_SSO_UserId;
		String storeId = LoginTestData.operatorStoreId;
		String date = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToSkimsPage();
		skimsPage.selectSkimsDate(date).expandRolledUpSkimsRecords();
		GenericMethods.clickOnElement(skimsPage.Skims_CountedRecord_View_BT, "Skims_CountedRecord_View_BT");
		wait.until(ExpectedConditions.visibilityOf(skimsPage.Skim_PopUp_Label));
		if(skimsPage.Skim_PopUp_Register_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Time_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_DepositCode_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Manager_DD.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_EnvelopID_TB.getAttribute("disabled").equals("true")
				& skimsPage.Skim_PopUp_Amount_TB.getAttribute("disabled").equals("true")){
			Reporter.reportPassResult(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should NOT be able to edit skim entries in Cloud app",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(skimsPage.Skims_PopUp_Cancel_BT, "Skims_PopUp_Cancel_BT");
		if(skimsPage.Skims_RolledUpRecord_List.size() == 0){
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInAscendinOrder = skimsPage.verifySkimsDateInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
			Thread.sleep(2000);
			boolean dateInDecendinOrder = skimsPage.verifySkimsDateInDescendingOrder();
			if (dateInAscendinOrder & dateInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending date in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInAscendinOrder = skimsPage.verifySkimsDepositCodeInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
			Thread.sleep(2000);
			boolean depositCodeInDecendinOrder = skimsPage.verifySkimsDepositCodeInDescendingOrder();
			if (depositCodeInAscendinOrder & depositCodeInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending deposit code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInAscendinOrder = skimsPage.verifySkimsRegisterInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
			Thread.sleep(2000);
			boolean registerInDecendinOrder = skimsPage.verifySkimsRegisterInDescendingOrder();
			if (registerInAscendinOrder & registerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInAscendinOrder = skimsPage.verifySkimsAmountInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
			Thread.sleep(2000);
			boolean amountInDecendinOrder = skimsPage.verifySkimsAmountInDescendingOrder();
			if (amountInAscendinOrder & amountInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register code in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInAscendinOrder = skimsPage.verifySkimsPreparerInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
			Thread.sleep(2000);
			boolean preparerInDecendinOrder = skimsPage.verifySkimsPreparerInDescendingOrder();
			if (preparerInAscendinOrder & preparerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Preparer in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInAscendinOrder = skimsPage.verifySkimsSourceInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
			Thread.sleep(2000);
			boolean sourceInDecendinOrder = skimsPage.verifySkimsSourceInDescendingOrder();
			if (sourceInAscendinOrder & sourceInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Source in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInAscendinOrder = skimsPage.verifySkimsStatusInAscendingOrder();
			GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
			Thread.sleep(2000);
			boolean statusInDecendinOrder = skimsPage.verifySkimsStatusInDescendingOrder();
			if (statusInAscendinOrder & statusInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending status in Skims Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
		}else{
			Reporter.reportPassResult(
					browser,
					"Rolled up records can not be sorted",
					"Pass");
		}
		Select dipositeCodeDD = new Select(skimsPage.SkimsDepositCode_DD);
		String depositCode = dipositeCodeDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForDepositCode = skimsPage.getNumberOfRecordsForFilterValue(depositCode);
		System.out.println("expectedNumberOfRecordsForDepositCode "+expectedNumberOfRecordsForDepositCode);
		dipositeCodeDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForDepositCode == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForDepositCode == skimsPage.getNumberOfRecordsForFilterValue(depositCode)){
			Reporter.reportPassResult(browser,
					"User should be able to filter values based on the deposit code",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to filter values based on the deposit code",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select registerDD = new Select(skimsPage.Skims_Register_DD);
		String register = registerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForRegister = skimsPage.getNumberOfRecordsForFilterValue(register);
		registerDD.selectByIndex(1);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForRegister == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForRegister == skimsPage.getNumberOfRecordsForFilterValue(register)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select statusDD = new Select(skimsPage.SkimsStatus_DD);
		String status = "Counted";
		int expectedNumberOfRecordsForStatus = skimsPage.getNumberOfRecordsForFilterValue(status);
		System.out.println("expectedNumberOfRecordsForStatus "+expectedNumberOfRecordsForStatus);
		statusDD.selectByVisibleText(status.toLowerCase());
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForStatus == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForStatus == skimsPage.getNumberOfRecordsForFilterValue(status)){
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
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		Select preparerDD = new Select(skimsPage.SkimsPreparer_DD);
		String preparer = preparerDD.getOptions().get(1).getText();
		int expectedNumberOfRecordsForPreparer = skimsPage.getNumberOfRecordsForFilterValue(preparer);
		System.out.println("expectedNumberOfRecordsForPreparer "+expectedNumberOfRecordsForPreparer);
		preparerDD.selectByVisibleText(preparer);
		GenericMethods.clickOnElement(skimsPage.ShowResults_BT, "ShowResults_BT");
		Thread.sleep(5000);
		if(expectedNumberOfRecordsForPreparer == skimsPage.Skims_AllRecord_List.size()
				& expectedNumberOfRecordsForPreparer == skimsPage.getNumberOfRecordsForFilterValue(preparer)){
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
}
