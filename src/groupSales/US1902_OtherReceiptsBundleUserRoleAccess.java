package groupSales;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import eCashPageClasses.AbstractPage;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.GroupSalesPage;
import eCashPageClasses.HomePage;

public class US1902_OtherReceiptsBundleUserRoleAccess extends AbstractTest{
	
	//TC3401: Verify that "Level 1" users can access to the Other Receipts page and all Other Receipts functions in the cloud.
	@Test()
	public void groupSales_US1902_TC3401() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1902_TC3401";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		String orgName = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String checkNumber = String.valueOf(Base.generateNdigitRandomNumber(5));
		String description = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		String type = "Paper";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "groupSalesPage.OtherReceipts_BT");
		Thread.sleep(2000);
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.OtherReceipts_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_User_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Type_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_CheckNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editOtherReceiptsRecord(orgName, type, checkNumber, description);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyOtherReceiptsDetailsUpdated(orgName, type, checkNumber)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		Select select = new Select(groupSalesPage.EditOtherReceiptsPopUp_Type_DD);
		//Verify that all values are updated
		System.out.println(groupSalesPage.EditOtherReceiptsPopUp_OrganizationName_TB.getAttribute("value").equals(orgName));
		System.out.println(groupSalesPage.EditOtherReceiptsPopUp_CheckNumber_TB.getAttribute("value").equals(checkNumber));
		System.out.println(groupSalesPage.EditOtherReceiptsPopUp_Description_TB.getAttribute("value").equals(description));
		System.out.println(select.getFirstSelectedOption().getAttribute("value").equals(type));
		if(groupSalesPage.EditOtherReceiptsPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditOtherReceiptsPopUp_CheckNumber_TB.getAttribute("value").equals(checkNumber)
			&& groupSalesPage.EditOtherReceiptsPopUp_Description_TB.getAttribute("value").equals(description)
			&& select.getFirstSelectedOption().getAttribute("value").equals(type)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditOtherReceiptsPopUp_Submit_BT, "EditOtherReceiptsPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_Saved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyOtherReceiptsDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyOtherReceiptsDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyOtherReceiptsAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyOtherReceiptsAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyOtherReceiptsUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyOtherReceiptsUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyOtherReceiptsTypeInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyOtherReceiptsTypeInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInAscendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInDecendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInDescendingOrder();
		if (checkNumberInAscendinOrder & checkNumberInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInDescendingOrder();
		if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select selectDate = new Select(groupSalesPage.OtherReceipts_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.otherReceiptsSelectStartdate(startDate).otherReceiptsSelectEndDate(endDate).OtherReceipts_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3403: Verify that "Level 2" users can access to the Other Receipts page and all Other Receipts functions in the cloud.
	@Test()
	public void groupSales_US1902_TC3403() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1902_TC3403";
		String password = LoginTestData.level2_SSO_Password;
		String userId = LoginTestData.level2_SSO_UserId;
		String storeId = LoginTestData.level2StoreId;
		String orgName = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String checkNumber = String.valueOf(Base.generateNdigitRandomNumber(5));
		String description = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		String type = "Paper";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "groupSalesPage.OtherReceipts_BT");
		Thread.sleep(2000);
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.OtherReceipts_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_User_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Type_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_CheckNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editOtherReceiptsRecord(orgName, type, checkNumber, description);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyOtherReceiptsDetailsUpdated(orgName, type, checkNumber)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		Select select = new Select(groupSalesPage.EditOtherReceiptsPopUp_Type_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditOtherReceiptsPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditOtherReceiptsPopUp_CheckNumber_TB.getAttribute("value").equals(checkNumber)
			&& groupSalesPage.EditOtherReceiptsPopUp_Description_TB.getAttribute("value").equals(description)
			&& select.getFirstSelectedOption().getAttribute("value").equals(type)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditOtherReceiptsPopUp_Submit_BT, "EditOtherReceiptsPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_Saved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyOtherReceiptsDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyOtherReceiptsDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyOtherReceiptsAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyOtherReceiptsAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyOtherReceiptsUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyOtherReceiptsUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyOtherReceiptsTypeInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyOtherReceiptsTypeInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInAscendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInDecendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInDescendingOrder();
		if (checkNumberInAscendinOrder & checkNumberInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInDescendingOrder();
		if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select selectDate = new Select(groupSalesPage.OtherReceipts_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.otherReceiptsSelectStartdate(startDate).otherReceiptsSelectEndDate(endDate).OtherReceipts_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3404: Verify that "Level 3" users can access to the Other Receipts page and all Other Receipts functions in the cloud.
	@Test()
	public void groupSales_US1902_TC3404() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1902_TC3404";
		String password = LoginTestData.level3_SSO_Password;
		String userId = LoginTestData.level3_SSO_UserId;
		String storeId = LoginTestData.level3StoreId;
		String orgName = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String checkNumber = String.valueOf(Base.generateNdigitRandomNumber(5));
		String description = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		String type = "Paper";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "groupSalesPage.OtherReceipts_BT");
		Thread.sleep(2000);
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.OtherReceipts_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_User_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Type_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_CheckNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editOtherReceiptsRecord(orgName, type, checkNumber, description);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyOtherReceiptsDetailsUpdated(orgName, type, checkNumber)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		Select select = new Select(groupSalesPage.EditOtherReceiptsPopUp_Type_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditOtherReceiptsPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditOtherReceiptsPopUp_CheckNumber_TB.getAttribute("value").equals(checkNumber)
			&& groupSalesPage.EditOtherReceiptsPopUp_Description_TB.getAttribute("value").equals(description)
			&& select.getFirstSelectedOption().getAttribute("value").equals(type)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditOtherReceiptsPopUp_Submit_BT, "EditOtherReceiptsPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_Saved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyOtherReceiptsDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyOtherReceiptsDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyOtherReceiptsAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyOtherReceiptsAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyOtherReceiptsUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyOtherReceiptsUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyOtherReceiptsTypeInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyOtherReceiptsTypeInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInAscendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInDecendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInDescendingOrder();
		if (checkNumberInAscendinOrder & checkNumberInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInDescendingOrder();
		if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select selectDate = new Select(groupSalesPage.OtherReceipts_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.otherReceiptsSelectStartdate(startDate).otherReceiptsSelectEndDate(endDate).OtherReceipts_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3405: Verify that "Level 4" users can access to the Other Receipts page and all Other Receipts functions in the cloud.
	@Test(enabled=false)
	public void groupSales_US1902_TC3405() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1902_TC3405";
		String password = LoginTestData.level4_SSO_Password;
		String userId = LoginTestData.level4_SSO_UserId;
		String storeId = LoginTestData.level4StoreId;
		String orgName = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String checkNumber = String.valueOf(Base.generateNdigitRandomNumber(5));
		String description = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		String type = "Paper";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "groupSalesPage.OtherReceipts_BT");
		Thread.sleep(2000);
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.OtherReceipts_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_User_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Type_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_CheckNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editOtherReceiptsRecord(orgName, type, checkNumber, description);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyOtherReceiptsDetailsUpdated(orgName, type, checkNumber)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		Select select = new Select(groupSalesPage.EditOtherReceiptsPopUp_Type_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditOtherReceiptsPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditOtherReceiptsPopUp_CheckNumber_TB.getAttribute("value").equals(checkNumber)
			&& groupSalesPage.EditOtherReceiptsPopUp_Description_TB.getAttribute("value").equals(description)
			&& select.getFirstSelectedOption().getAttribute("value").equals(type)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditOtherReceiptsPopUp_Submit_BT, "EditOtherReceiptsPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_Saved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyOtherReceiptsDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyOtherReceiptsDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyOtherReceiptsAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyOtherReceiptsAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyOtherReceiptsUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyOtherReceiptsUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyOtherReceiptsTypeInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyOtherReceiptsTypeInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInAscendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInDecendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInDescendingOrder();
		if (checkNumberInAscendinOrder & checkNumberInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInDescendingOrder();
		if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select selectDate = new Select(groupSalesPage.OtherReceipts_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.otherReceiptsSelectStartdate(startDate).otherReceiptsSelectEndDate(endDate).OtherReceipts_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3405: Verify that "Level 5" users can access to the Other Receipts page and all Other Receipts functions in the cloud.
	@Test()
	public void groupSales_US1902_TC3406() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1902_TC3406";
		String password = LoginTestData.level5_SSO_Password;
		String userId = LoginTestData.level5_SSO_UserId;
		String storeId = LoginTestData.level5StoreId;
		String orgName = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String checkNumber = String.valueOf(Base.generateNdigitRandomNumber(5));
		String description = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		String type = "Paper";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "groupSalesPage.OtherReceipts_BT");
		Thread.sleep(2000);
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.OtherReceipts_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_User_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Type_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_CheckNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editOtherReceiptsRecord(orgName, type, checkNumber, description);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyOtherReceiptsDetailsUpdated(orgName, type, checkNumber)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		Select select = new Select(groupSalesPage.EditOtherReceiptsPopUp_Type_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditOtherReceiptsPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditOtherReceiptsPopUp_CheckNumber_TB.getAttribute("value").equals(checkNumber)
			&& groupSalesPage.EditOtherReceiptsPopUp_Description_TB.getAttribute("value").equals(description)
			&& select.getFirstSelectedOption().getAttribute("value").equals(type)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditOtherReceiptsPopUp_Submit_BT, "EditOtherReceiptsPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_Saved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyOtherReceiptsDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyOtherReceiptsDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyOtherReceiptsAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyOtherReceiptsAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyOtherReceiptsUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyOtherReceiptsUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyOtherReceiptsTypeInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyOtherReceiptsTypeInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInAscendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInDecendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInDescendingOrder();
		if (checkNumberInAscendinOrder & checkNumberInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInDescendingOrder();
		if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select selectDate = new Select(groupSalesPage.OtherReceipts_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.otherReceiptsSelectStartdate(startDate).otherReceiptsSelectEndDate(endDate).OtherReceipts_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3408: Verify that "Org admin" users can access to the Other Receipts page and all Other Receipts functions in the cloud.
	@Test()
	public void groupSales_US1902_TC3408() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1902_TC3408";
		String password = LoginTestData.orgAdmin_SSO_Password;
		String userId = LoginTestData.orgAdmin_SSO_UserId;
		String storeId = LoginTestData.orgAdminStoreId;
		String orgName = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String checkNumber = String.valueOf(Base.generateNdigitRandomNumber(5));
		String description = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		String type = "Paper";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "groupSalesPage.OtherReceipts_BT");
		Thread.sleep(2000);
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.OtherReceipts_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_User_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Type_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_CheckNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editOtherReceiptsRecord(orgName, type, checkNumber, description);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyOtherReceiptsDetailsUpdated(orgName, type, checkNumber)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		Select select = new Select(groupSalesPage.EditOtherReceiptsPopUp_Type_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditOtherReceiptsPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditOtherReceiptsPopUp_CheckNumber_TB.getAttribute("value").equals(checkNumber)
			&& groupSalesPage.EditOtherReceiptsPopUp_Description_TB.getAttribute("value").equals(description)
			&& select.getFirstSelectedOption().getAttribute("value").equals(type)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditOtherReceiptsPopUp_Submit_BT, "EditOtherReceiptsPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_Saved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyOtherReceiptsDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyOtherReceiptsDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyOtherReceiptsAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyOtherReceiptsAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyOtherReceiptsUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyOtherReceiptsUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyOtherReceiptsTypeInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyOtherReceiptsTypeInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInAscendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInDecendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInDescendingOrder();
		if (checkNumberInAscendinOrder & checkNumberInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInDescendingOrder();
		if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select selectDate = new Select(groupSalesPage.OtherReceipts_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.otherReceiptsSelectStartdate(startDate).otherReceiptsSelectEndDate(endDate).OtherReceipts_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3409: Verify that "Supervisor with role assignment" users can access to the Other Receipts page and all Other Receipts functions in the cloud.
	@Test()
	public void groupSales_US1902_TC3409() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1902_TC3409";
		String password = LoginTestData.supervisorWithRoleAssignment_SSO_Password;
		String userId = LoginTestData.supervisorWithRoleAssignment_SSO_UserId;
		String storeId = LoginTestData.supervisorWithRoleAssignmentStoreId;
		String orgName = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String checkNumber = String.valueOf(Base.generateNdigitRandomNumber(5));
		String description = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		String type = "Paper";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "groupSalesPage.OtherReceipts_BT");
		Thread.sleep(2000);
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.OtherReceipts_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_User_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Type_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_CheckNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editOtherReceiptsRecord(orgName, type, checkNumber, description);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyOtherReceiptsDetailsUpdated(orgName, type, checkNumber)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		Select select = new Select(groupSalesPage.EditOtherReceiptsPopUp_Type_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditOtherReceiptsPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditOtherReceiptsPopUp_CheckNumber_TB.getAttribute("value").equals(checkNumber)
			&& groupSalesPage.EditOtherReceiptsPopUp_Description_TB.getAttribute("value").equals(description)
			&& select.getFirstSelectedOption().getAttribute("value").equals(type)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditOtherReceiptsPopUp_Submit_BT, "EditOtherReceiptsPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_Saved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyOtherReceiptsDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyOtherReceiptsDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyOtherReceiptsAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyOtherReceiptsAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyOtherReceiptsUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyOtherReceiptsUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyOtherReceiptsTypeInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyOtherReceiptsTypeInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInAscendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInDecendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInDescendingOrder();
		if (checkNumberInAscendinOrder & checkNumberInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInDescendingOrder();
		if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select selectDate = new Select(groupSalesPage.OtherReceipts_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.otherReceiptsSelectStartdate(startDate).otherReceiptsSelectEndDate(endDate).OtherReceipts_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3409: Verify that "Supervisor" users can access to the Other Receipts page and all Other Receipts functions in the cloud.
	@Test()
	public void groupSales_US1902_TC3410() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1902_TC3410";
		String password = LoginTestData.supervisor_SSO_Password;
		String userId = LoginTestData.supervisor_SSO_UserId;
		String storeId = LoginTestData.supervisorStoreId;
		String orgName = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String checkNumber = String.valueOf(Base.generateNdigitRandomNumber(5));
		String description = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		String type = "Paper";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "groupSalesPage.OtherReceipts_BT");
		Thread.sleep(2000);
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.OtherReceipts_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_User_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Type_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_CheckNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editOtherReceiptsRecord(orgName, type, checkNumber, description);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyOtherReceiptsDetailsUpdated(orgName, type, checkNumber)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		Select select = new Select(groupSalesPage.EditOtherReceiptsPopUp_Type_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditOtherReceiptsPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditOtherReceiptsPopUp_CheckNumber_TB.getAttribute("value").equals(checkNumber)
			&& groupSalesPage.EditOtherReceiptsPopUp_Description_TB.getAttribute("value").equals(description)
			&& select.getFirstSelectedOption().getAttribute("value").equals(type)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditOtherReceiptsPopUp_Submit_BT, "EditOtherReceiptsPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_Saved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyOtherReceiptsDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyOtherReceiptsDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyOtherReceiptsAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyOtherReceiptsAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyOtherReceiptsUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyOtherReceiptsUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyOtherReceiptsTypeInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyOtherReceiptsTypeInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInAscendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInDecendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInDescendingOrder();
		if (checkNumberInAscendinOrder & checkNumberInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInDescendingOrder();
		if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select selectDate = new Select(groupSalesPage.OtherReceipts_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.otherReceiptsSelectStartdate(startDate).otherReceiptsSelectEndDate(endDate).OtherReceipts_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3402: Verify that "operator" users can access to the Other Receipts page and all Other Receipts functions in the cloud.
	@Test()
	public void groupSales_US1902_TC3402() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1902_TC3402";
		String password = LoginTestData.operator_SSO_Password;
		String userId = LoginTestData.operator_SSO_UserId;
		String storeId = LoginTestData.operatorStoreId;
		String orgName = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String checkNumber = String.valueOf(Base.generateNdigitRandomNumber(5));
		String description = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		String type = "Paper";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "groupSalesPage.OtherReceipts_BT");
		Thread.sleep(2000);
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.OtherReceipts_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_User_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Type_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_CheckNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editOtherReceiptsRecord(orgName, type, checkNumber, description);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyOtherReceiptsDetailsUpdated(orgName, type, checkNumber)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName,type and check number in Other Receipts Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		Select select = new Select(groupSalesPage.EditOtherReceiptsPopUp_Type_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditOtherReceiptsPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditOtherReceiptsPopUp_CheckNumber_TB.getAttribute("value").equals(checkNumber)
			&& groupSalesPage.EditOtherReceiptsPopUp_Description_TB.getAttribute("value").equals(description)
			&& select.getFirstSelectedOption().getAttribute("value").equals(type)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Other receipts Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditOtherReceiptsPopUp_Submit_BT, "EditOtherReceiptsPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_Saved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyOtherReceiptsDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyOtherReceiptsDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyOtherReceiptsAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyOtherReceiptsAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyOtherReceiptsUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyOtherReceiptsUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyOtherReceiptsTypeInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyOtherReceiptsTypeInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInAscendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInDecendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInDescendingOrder();
		if (checkNumberInAscendinOrder & checkNumberInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInDescendingOrder();
		if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select selectDate = new Select(groupSalesPage.OtherReceipts_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.otherReceiptsSelectStartdate(startDate).otherReceiptsSelectEndDate(endDate).OtherReceipts_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3413: Verify that "level6" users can access to the Other Receipts page and all Other Receipts functions in the cloud.
	@Test()
	public void groupSales_US1902_TC3413() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1902_TC3413";
		String password = LoginTestData.level6_SSO_Password;
		String userId = LoginTestData.level6_SSO_UserId;
		String storeId = LoginTestData.level6StoreId;
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "groupSalesPage.OtherReceipts_BT");
		Thread.sleep(2000);
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.OtherReceipts_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_User_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Type_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_CheckNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.OtherReceipts_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Type 6.Organization Name 7.Deposit Status 8. Check Number columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.OtherReceipts_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		if(groupSalesPage.EditOtherReceiptsPopUp_OrganizationName_TB.getAttribute("disabled").equals("true")
			&& groupSalesPage.EditOtherReceiptsPopUp_CheckNumber_TB.getAttribute("disabled").equals("true")
			&& groupSalesPage.EditOtherReceiptsPopUp_Description_TB.getAttribute("disabled").equals("true")
			&& groupSalesPage.EditOtherReceiptsPopUp_Type_DD.getAttribute("disabled").equals("true")){
			Reporter.reportPassResult(
					browser,
					"Level 6 user should not be able to edit Other receipts Details",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Level 6 user should not be able to edit Other receipts details",
					"Fail");
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditOtherReceiptsPopUp_Close_BT, "EditOtherReceiptsPopUp_Close_BT");
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyOtherReceiptsDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyOtherReceiptsDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyOtherReceiptsAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyOtherReceiptsAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyOtherReceiptsUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyOtherReceiptsUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyOtherReceiptsTypeInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Type_Label, "OtherReceipts_Type_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyOtherReceiptsTypeInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending type value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInAscendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
		Thread.sleep(2000);
		boolean checkNumberInDecendinOrder = groupSalesPage.verifyOtherReceiptsCheckNumberInDescendingOrder();
		if (checkNumberInAscendinOrder & checkNumberInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending checkNumber value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInDescendingOrder();
		if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select selectDate = new Select(groupSalesPage.OtherReceipts_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.otherReceiptsSelectStartdate(startDate).otherReceiptsSelectEndDate(endDate).OtherReceipts_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_ShowResults_BT,"OtherReceipts_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}

}
