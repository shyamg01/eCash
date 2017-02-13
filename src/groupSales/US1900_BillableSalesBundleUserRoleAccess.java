package groupSales;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class US1900_BillableSalesBundleUserRoleAccess extends AbstractTest
{
	
	
	//TC3393 : 	Verify that "Level 1" users can access to the page and all  Billable sales page functions in the cloud.
	@Test()
	public void groupSales_US1900_TC3393() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1900_TC3393";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String invoicePONumber = String.valueOf(Base.generateNdigitRandomNumber(5));
		String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine1 = "HclTechnologies";
		String addressLine2 = "HclTechnologies";
		String city = "Noida";
		String zip = String.valueOf(Base.generateNdigitRandomNumber(5));
		String email = "testUser@mcd.com";
		String phoneNumber = String.valueOf(Base.generateNdigitRandomNumber(10));
		String state = "AK";
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		//go to Billable Sales Page
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT");
		Thread.sleep(2000);
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.BillableSales_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.BillableSales_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.BillableSales_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.BillableSales_DepositStatus_Label)
				& Base.isElementDisplayed(groupSalesPage.BillableSales_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.BillableSales_InvoicePO_Label)
				& Base.isElementDisplayed(groupSalesPage.BillableSales_User_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "groupSalesPage.BillableSales_ValidatedRecord_Edit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
		//Edit all editable values in pop up and submit
		System.out.println("orgName "+orgName);
		Thread.sleep(3000);
		groupSalesPage.editBillableSalesRecord(orgName, invoicePONumber, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
		//Verify that new Organization name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyBillableSalesDetailsUpdated(orgName, invoicePONumber)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Thread.sleep(1500);
		AbstractPage.executor.executeScript("window.scrollBy(0,100)", "");
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
		Select select = new Select(groupSalesPage.BillableSalesPopUp_State_DD);
		//Verify that all values are updated
		System.out.println(groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName));
		System.out.println(groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").equals(invoicePONumber));
		System.out.println(groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName));
		System.out.println(groupSalesPage.EditBillableSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1));
		System.out.println(groupSalesPage.EditBillableSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2));
		System.out.println(groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").equals(city));
		System.out.println(groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").equals(email));
		System.out.println(groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").equals(zip));
		System.out.println(groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber));
		System.out.println(select.getFirstSelectedOption().getAttribute("value").equals(state));
		
		if(groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").equals(invoicePONumber)
			&& groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
			&& groupSalesPage.EditBillableSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
			&& groupSalesPage.EditBillableSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
			&& groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").equals(city)
			&& groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").equals(email)
			&& groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
			&& groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
			&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Billable Sales Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Billable Sales Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.BillableSalesPopUp_Submit_BT, "BillableSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.BillableSales_SalesSaved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyBillableSalesDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyBillableSalesDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyBillableSalesRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyBillableSalesRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyBillableSalesAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyBillableSalesAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyBillableSalesUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyBillableSalesUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyBillableSalesOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyBillableSalesOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInDescendingOrder();
		if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select selectDate = new Select(groupSalesPage.BillableSales_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		Thread.sleep(2000);
		groupSalesPage.BillableSalesselectStartdate(startDate).BillableSalesselectEndDate(endDate).BillableSales_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Thread.sleep(2000);
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Thread.sleep(2000);
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Thread.sleep(2000);
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}

	//TC3396 : 	Verify that "Level 2" users can access to the Billable Sales page and all Billable sales functions in the cloud.
	
	@Test()
	public void groupSales_US1900_TC3396() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1900_TC3396";
		String password = LoginTestData.level2_SSO_Password;
		String userId = LoginTestData.level2_SSO_UserId;
		String storeId = LoginTestData.level2StoreId;
		String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String invoicePONumber = String.valueOf(Base.generateNdigitRandomNumber(5));
		String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine1 = "HclTechnologies";
		String addressLine2 = "HclTechnologies";
		String city = "Noida";
		String zip = String.valueOf(Base.generateNdigitRandomNumber(5));
		String email = "testUser@mcd.com";
		String phoneNumber = String.valueOf(Base.generateNdigitRandomNumber(10));
		String state = "AK";
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		//go to Billable Sales Page
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT");
		Thread.sleep(2000);
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.BillableSales_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.BillableSales_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.BillableSales_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.BillableSales_DepositStatus_Label)
				& Base.isElementDisplayed(groupSalesPage.BillableSales_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.BillableSales_InvoicePO_Label)
				& Base.isElementDisplayed(groupSalesPage.BillableSales_User_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "groupSalesPage.BillableSales_ValidatedRecord_Edit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
		//Edit all editable values in pop up and submit
		System.out.println("orgName "+orgName);
		Thread.sleep(3000);
		groupSalesPage.editBillableSalesRecord(orgName, invoicePONumber, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
		//Verify that new Organization name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyBillableSalesDetailsUpdated(orgName, invoicePONumber)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Thread.sleep(1500);
		AbstractPage.executor.executeScript("window.scrollBy(0,100)", "");
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
		Select select = new Select(groupSalesPage.BillableSalesPopUp_State_DD);
		//Verify that all values are updated
		if(groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").equals(invoicePONumber)
			&& groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
			&& groupSalesPage.EditBillableSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
			&& groupSalesPage.EditBillableSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
			&& groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").equals(city)
			&& groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").equals(email)
			&& groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
			&& groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
			&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Billable Sales Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Billable Sales Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.BillableSalesPopUp_Submit_BT, "BillableSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.BillableSales_SalesSaved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyBillableSalesDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyBillableSalesDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyBillableSalesRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyBillableSalesRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyBillableSalesAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyBillableSalesAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyBillableSalesUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyBillableSalesUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyBillableSalesOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyBillableSalesOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInDescendingOrder();
		if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select selectDate = new Select(groupSalesPage.BillableSales_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		Thread.sleep(2000);
		groupSalesPage.BillableSalesselectStartdate(startDate).BillableSalesselectEndDate(endDate).BillableSales_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Thread.sleep(2000);
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Thread.sleep(2000);
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Thread.sleep(2000);
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}	
	
	
	
	//TC3397 : 	Verify that "Level 3" users can access to the Billable Sales page and all Billable sales functions in the cloud.
	
	
		@Test()
		public void groupSales_US1900_TC3397() throws InterruptedException,
				RowsExceededException, BiffException, WriteException, IOException, ParseException {
			/** Variable Section : **/
			GroupSalesPage groupSalesPage;
			AbstractTest.tcName="groupSales_US1900_TC3397";
			String password = LoginTestData.level3_SSO_Password;
			String userId = LoginTestData.level3_SSO_UserId;
			String storeId = LoginTestData.level3StoreId;
			String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
			String invoicePONumber = String.valueOf(Base.generateNdigitRandomNumber(5));
			String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
			String addressLine1 = "HclTechnologies";
			String addressLine2 = "HclTechnologies";
			String city = "Noida";
			String zip = String.valueOf(Base.generateNdigitRandomNumber(5));
			String email = "testUser@mcd.com";
			String phoneNumber = String.valueOf(Base.generateNdigitRandomNumber(10));
			String state = "AK";
			String startDate = GlobalVariable.startDate;
			String endDate = GlobalVariable.endDate;
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to Group sales page
			groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
					.goToGroupSalesPage();
			//go to Billable Sales Page
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT");
			Thread.sleep(2000);
			// Verify that columns are displaying
			if (Base.isElementDisplayed(groupSalesPage.BillableSales_DateAndTime_Label)
					& Base.isElementDisplayed(groupSalesPage.BillableSales_Register_Label)
					& Base.isElementDisplayed(groupSalesPage.BillableSales_Amount_Label)
					& Base.isElementDisplayed(groupSalesPage.BillableSales_DepositStatus_Label)
					& Base.isElementDisplayed(groupSalesPage.BillableSales_OrganizationName_Label)
					& Base.isElementDisplayed(groupSalesPage.BillableSales_InvoicePO_Label)
					& Base.isElementDisplayed(groupSalesPage.BillableSales_User_Label)) {
				Reporter.reportPassResult(browser,
						"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
						"Pass");
			} else {
				Reporter.reportTestFailure(browser,
						"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			//Click on edit button for first validated record
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "groupSalesPage.BillableSales_ValidatedRecord_Edit_BT");
			wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
			//Edit all editable values in pop up and submit
			System.out.println("orgName "+orgName);
			Thread.sleep(3000);
			groupSalesPage.editBillableSalesRecord(orgName, invoicePONumber, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
			//Verify that new Organization name and Tax id is displayed in Tax exempt sales landing page
			if (groupSalesPage.verifyBillableSalesDetailsUpdated(orgName, invoicePONumber)) {
				Reporter.reportPassResult(
						browser,
						"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			Thread.sleep(1500);
			AbstractPage.executor.executeScript("window.scrollBy(0,100)", "");
			//Click on edit button for first validated record
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
			wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
			Select select = new Select(groupSalesPage.BillableSalesPopUp_State_DD);
			//Verify that all values are updated
			if(groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
				&& groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").equals(invoicePONumber)
				&& groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
				&& groupSalesPage.EditBillableSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
				&& groupSalesPage.EditBillableSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
				&& groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").equals(city)
				&& groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").equals(email)
				&& groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
				&& groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
				&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
				Reporter.reportPassResult(
						browser,
						"user should be able to view updated  values in edit Billable Sales Pop Up",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to view updated  values in edit Billable Sales Pop Up",
						"Fail");
				AbstractTest.takeSnapShot();
				}
			//Click on Submit Button
			GenericMethods.clickOnElement(groupSalesPage.BillableSalesPopUp_Submit_BT, "BillableSalesPopUp_Submit_BT");
			wait.until(ExpectedConditions.visibilityOf(groupSalesPage.BillableSales_SalesSaved_Confirmation_MSG));
			Thread.sleep(5000);
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
			Thread.sleep(2000);
			boolean dateInAscendinOrder = groupSalesPage.verifyBillableSalesDateInAscendingOrder();
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
			Thread.sleep(2000);
			boolean dateInDecendinOrder = groupSalesPage.verifyBillableSalesDateInDescendingOrder();
			if (dateInAscendinOrder & dateInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
			Thread.sleep(2000);
			boolean registerInAscendinOrder = groupSalesPage.verifyBillableSalesRegisterInAscendingOrder();
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
			Thread.sleep(2000);
			boolean registerInDecendinOrder = groupSalesPage.verifyBillableSalesRegisterInDescendingOrder();
			if (registerInAscendinOrder & registerInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
			Thread.sleep(2000);
			boolean amountInAscendinOrder = groupSalesPage.verifyBillableSalesAmountInAscendingOrder();
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
			Thread.sleep(2000);
			boolean amountInDecendinOrder = groupSalesPage.verifyBillableSalesAmountInDescendingOrder();
			if (amountInAscendinOrder & amountInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
			Thread.sleep(2000);
			boolean userInAscendinOrder = groupSalesPage.verifyBillableSalesUserInAscendingOrder();
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
			Thread.sleep(2000);
			boolean userInDecendinOrder = groupSalesPage.verifyBillableSalesUserInDescendingOrder();
			if (userInAscendinOrder & userInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
			Thread.sleep(2000);
			boolean organizationInAscendinOrder = groupSalesPage.verifyBillableSalesOrganizationInAscendingOrder();
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
			Thread.sleep(2000);
			boolean organizationInDecendinOrder = groupSalesPage.verifyBillableSalesOrganizationInDescendingOrder();
			if (organizationInAscendinOrder & organizationInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
			Thread.sleep(2000);
			boolean taxIdInAscendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInAscendingOrder();
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
			Thread.sleep(2000);
			boolean taxIdInDecendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInDescendingOrder();
			if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
			Thread.sleep(2000);
			boolean depositStatusInAscendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInAscendingOrder();
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
			Thread.sleep(2000);
			boolean depositStatusInDecendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInDescendingOrder();
			if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
				Reporter.reportPassResult(
						browser,
						"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			Select selectDate = new Select(groupSalesPage.BillableSales_DateRange_DD);
			selectDate.selectByVisibleText("Custom Date Range");
			Thread.sleep(2000);
			groupSalesPage.BillableSalesselectStartdate(startDate).BillableSalesselectEndDate(endDate).BillableSales_ShowResults_BT.click();
			Thread.sleep(3000);
			if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(startDate, endDate)){
				Reporter.reportPassResult(
						browser,
						"User should be able to view the records within custom date range in Billable Sales Landing Page",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,
						"User should be able to view the records within custom date range in Billable Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			selectDate.selectByValue("3");
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
			Calendar cal1 = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			cal1.setTime(new Date());
			String selectedEndDate = dateFormat.format(cal1.getTime());
			System.out.println("endDate "+endDate);
			cal1.add(Calendar.MONTH, -3);
			String selectedStartDate = dateFormat.format(cal1.getTime());
			System.out.println("startDate "+startDate);
			if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
					&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
				Reporter.reportPassResult(
						browser,
						"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,
						"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
				Reporter.reportPassResult(
						browser,
						"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,
						"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			Thread.sleep(2000);
			selectDate.selectByValue("6");
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			selectedEndDate = dateFormat.format(cal2.getTime());
			cal2.add(Calendar.MONTH, -6);
			selectedStartDate = dateFormat.format(cal2.getTime());
			if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
					&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
				Reporter.reportPassResult(
						browser,
						"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,
						"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
				Reporter.reportPassResult(
						browser,
						"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,
						"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			Thread.sleep(2000);
			selectDate.selectByValue("9");
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
			Calendar cal3 = Calendar.getInstance();
			cal3.setTime(new Date());
			selectedEndDate = dateFormat.format(cal3.getTime());
			cal3.add(Calendar.MONTH, -9);
			selectedStartDate = dateFormat.format(cal3.getTime());
			if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
					&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
				Reporter.reportPassResult(
						browser,
						"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,
						"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
				Reporter.reportPassResult(
						browser,
						"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,
						"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			Thread.sleep(2000);
			selectDate.selectByValue("12");
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
			Calendar cal4 = Calendar.getInstance();
			cal4.setTime(new Date());
			selectedEndDate = dateFormat.format(cal4.getTime());
			cal4.add(Calendar.MONTH, -12);
			selectedStartDate = dateFormat.format(cal4.getTime());
			if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
					&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
				Reporter.reportPassResult(
						browser,
						"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,
						"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
				Reporter.reportPassResult(
						browser,
						"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,
						"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
						"Fail");
				AbstractTest.takeSnapShot();
			}
		}		
	
	
	

		//TC3398 : 	Verify that "Level 4" users can access to the Billable Sales page and all Billable sales functions in the cloud.
		
		
			@Test(enabled=false)
			public void groupSales_US1900_TC3398() throws InterruptedException,
					RowsExceededException, BiffException, WriteException, IOException, ParseException {
				/** Variable Section : **/
				GroupSalesPage groupSalesPage;
				AbstractTest.tcName="groupSales_US1900_TC3398";
				String password = LoginTestData.level4_SSO_Password;
				String userId = LoginTestData.level4_SSO_UserId;
				String storeId = LoginTestData.level4StoreId;
				String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
				String invoicePONumber = String.valueOf(Base.generateNdigitRandomNumber(5));
				String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
				String addressLine1 = "HclTechnologies";
				String addressLine2 = "HclTechnologies";
				String city = "Noida";
				String zip = String.valueOf(Base.generateNdigitRandomNumber(5));
				String email = "testUser@mcd.com";
				String phoneNumber = String.valueOf(Base.generateNdigitRandomNumber(10));
				String state = "AK";
				String startDate = GlobalVariable.startDate;
				String endDate = GlobalVariable.endDate;
				/***********************************/
				HomePage homePage = PageFactory.initElements(driver, HomePage.class);
				// Navigate to Group sales page
				groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
						.goToGroupSalesPage();
				//go to Billable Sales Page
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT");
				Thread.sleep(2000);
				// Verify that columns are displaying
				if (Base.isElementDisplayed(groupSalesPage.BillableSales_DateAndTime_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_Register_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_Amount_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_DepositStatus_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_OrganizationName_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_InvoicePO_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_User_Label)) {
					Reporter.reportPassResult(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				//Click on edit button for first validated record
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "groupSalesPage.BillableSales_ValidatedRecord_Edit_BT");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
				//Edit all editable values in pop up and submit
				System.out.println("orgName "+orgName);
				Thread.sleep(3000);
				groupSalesPage.editBillableSalesRecord(orgName, invoicePONumber, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
				//Verify that new Organization name and Tax id is displayed in Tax exempt sales landing page
				if (groupSalesPage.verifyBillableSalesDetailsUpdated(orgName, invoicePONumber)) {
					Reporter.reportPassResult(
							browser,
							"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(1500);
				AbstractPage.executor.executeScript("window.scrollBy(0,100)", "");
				//Click on edit button for first validated record
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
				Select select = new Select(groupSalesPage.BillableSalesPopUp_State_DD);
				//Verify that all values are updated
				if(groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
					&& groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").equals(invoicePONumber)
					&& groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
					&& groupSalesPage.EditBillableSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
					&& groupSalesPage.EditBillableSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
					&& groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").equals(city)
					&& groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").equals(email)
					&& groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
					&& groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
					&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
					Reporter.reportPassResult(
							browser,
							"user should be able to view updated  values in edit Billable Sales Pop Up",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to view updated  values in edit Billable Sales Pop Up",
							"Fail");
					AbstractTest.takeSnapShot();
					}
				//Click on Submit Button
				GenericMethods.clickOnElement(groupSalesPage.BillableSalesPopUp_Submit_BT, "BillableSalesPopUp_Submit_BT");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.BillableSales_SalesSaved_Confirmation_MSG));
				Thread.sleep(5000);
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInAscendinOrder = groupSalesPage.verifyBillableSalesDateInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInDecendinOrder = groupSalesPage.verifyBillableSalesDateInDescendingOrder();
				if (dateInAscendinOrder & dateInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
				Thread.sleep(2000);
				boolean registerInAscendinOrder = groupSalesPage.verifyBillableSalesRegisterInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
				Thread.sleep(2000);
				boolean registerInDecendinOrder = groupSalesPage.verifyBillableSalesRegisterInDescendingOrder();
				if (registerInAscendinOrder & registerInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
				Thread.sleep(2000);
				boolean amountInAscendinOrder = groupSalesPage.verifyBillableSalesAmountInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
				Thread.sleep(2000);
				boolean amountInDecendinOrder = groupSalesPage.verifyBillableSalesAmountInDescendingOrder();
				if (amountInAscendinOrder & amountInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
				Thread.sleep(2000);
				boolean userInAscendinOrder = groupSalesPage.verifyBillableSalesUserInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
				Thread.sleep(2000);
				boolean userInDecendinOrder = groupSalesPage.verifyBillableSalesUserInDescendingOrder();
				if (userInAscendinOrder & userInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInAscendinOrder = groupSalesPage.verifyBillableSalesOrganizationInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInDecendinOrder = groupSalesPage.verifyBillableSalesOrganizationInDescendingOrder();
				if (organizationInAscendinOrder & organizationInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
				Thread.sleep(2000);
				boolean taxIdInAscendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
				Thread.sleep(2000);
				boolean taxIdInDecendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInDescendingOrder();
				if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInAscendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInDecendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInDescendingOrder();
				if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Select selectDate = new Select(groupSalesPage.BillableSales_DateRange_DD);
				selectDate.selectByVisibleText("Custom Date Range");
				Thread.sleep(2000);
				groupSalesPage.BillableSalesselectStartdate(startDate).BillableSalesselectEndDate(endDate).BillableSales_ShowResults_BT.click();
				Thread.sleep(3000);
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(startDate, endDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within custom date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within custom date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				selectDate.selectByValue("3");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal1 = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				cal1.setTime(new Date());
				String selectedEndDate = dateFormat.format(cal1.getTime());
				System.out.println("endDate "+endDate);
				cal1.add(Calendar.MONTH, -3);
				String selectedStartDate = dateFormat.format(cal1.getTime());
				System.out.println("startDate "+startDate);
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("6");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				selectedEndDate = dateFormat.format(cal2.getTime());
				cal2.add(Calendar.MONTH, -6);
				selectedStartDate = dateFormat.format(cal2.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("9");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal3 = Calendar.getInstance();
				cal3.setTime(new Date());
				selectedEndDate = dateFormat.format(cal3.getTime());
				cal3.add(Calendar.MONTH, -9);
				selectedStartDate = dateFormat.format(cal3.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("12");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal4 = Calendar.getInstance();
				cal4.setTime(new Date());
				selectedEndDate = dateFormat.format(cal4.getTime());
				cal4.add(Calendar.MONTH, -12);
				selectedStartDate = dateFormat.format(cal4.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
			}		
			
	
			//TC3399 : 	Verify that "Level 5" users can access to the Billable Sales page and all Billable sales functions in the cloud.
			
			
			@Test()
			public void groupSales_US1900_TC3399() throws InterruptedException,
					RowsExceededException, BiffException, WriteException, IOException, ParseException {
				/** Variable Section : **/
				GroupSalesPage groupSalesPage;
				AbstractTest.tcName="groupSales_US1900_TC3399";
				String password = LoginTestData.level5_SSO_Password;
				String userId = LoginTestData.level5_SSO_UserId;
				String storeId = LoginTestData.level5StoreId;
				String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
				String invoicePONumber = String.valueOf(Base.generateNdigitRandomNumber(5));
				String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
				String addressLine1 = "HclTechnologies";
				String addressLine2 = "HclTechnologies";
				String city = "Noida";
				String zip = String.valueOf(Base.generateNdigitRandomNumber(5));
				String email = "testUser@mcd.com";
				String phoneNumber = String.valueOf(Base.generateNdigitRandomNumber(10));
				String state = "AK";
				String startDate = GlobalVariable.startDate;
				String endDate = GlobalVariable.endDate;
				/***********************************/
				HomePage homePage = PageFactory.initElements(driver, HomePage.class);
				// Navigate to Group sales page
				groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
						.goToGroupSalesPage();
				//go to Billable Sales Page
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT");
				Thread.sleep(2000);
				// Verify that columns are displaying
				if (Base.isElementDisplayed(groupSalesPage.BillableSales_DateAndTime_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_Register_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_Amount_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_DepositStatus_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_OrganizationName_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_InvoicePO_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_User_Label)) {
					Reporter.reportPassResult(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				//Click on edit button for first validated record
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "groupSalesPage.BillableSales_ValidatedRecord_Edit_BT");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
				//Edit all editable values in pop up and submit
				System.out.println("orgName "+orgName);
				Thread.sleep(3000);
				groupSalesPage.editBillableSalesRecord(orgName, invoicePONumber, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
				//Verify that new Organization name and Tax id is displayed in Tax exempt sales landing page
				if (groupSalesPage.verifyBillableSalesDetailsUpdated(orgName, invoicePONumber)) {
					Reporter.reportPassResult(
							browser,
							"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(1500);
				AbstractPage.executor.executeScript("window.scrollBy(0,100)", "");
				//Click on edit button for first validated record
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
				Select select = new Select(groupSalesPage.BillableSalesPopUp_State_DD);
				//Verify that all values are updated
				if(groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
					&& groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").equals(invoicePONumber)
					&& groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
					&& groupSalesPage.EditBillableSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
					&& groupSalesPage.EditBillableSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
					&& groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").equals(city)
					&& groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").equals(email)
					&& groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
					&& groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
					&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
					Reporter.reportPassResult(
							browser,
							"user should be able to view updated  values in edit Billable Sales Pop Up",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to view updated  values in edit Billable Sales Pop Up",
							"Fail");
					AbstractTest.takeSnapShot();
					}
				//Click on Submit Button
				GenericMethods.clickOnElement(groupSalesPage.BillableSalesPopUp_Submit_BT, "BillableSalesPopUp_Submit_BT");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.BillableSales_SalesSaved_Confirmation_MSG));
				Thread.sleep(5000);
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInAscendinOrder = groupSalesPage.verifyBillableSalesDateInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInDecendinOrder = groupSalesPage.verifyBillableSalesDateInDescendingOrder();
				if (dateInAscendinOrder & dateInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
				Thread.sleep(2000);
				boolean registerInAscendinOrder = groupSalesPage.verifyBillableSalesRegisterInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
				Thread.sleep(2000);
				boolean registerInDecendinOrder = groupSalesPage.verifyBillableSalesRegisterInDescendingOrder();
				if (registerInAscendinOrder & registerInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
				Thread.sleep(2000);
				boolean amountInAscendinOrder = groupSalesPage.verifyBillableSalesAmountInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
				Thread.sleep(2000);
				boolean amountInDecendinOrder = groupSalesPage.verifyBillableSalesAmountInDescendingOrder();
				if (amountInAscendinOrder & amountInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
				Thread.sleep(2000);
				boolean userInAscendinOrder = groupSalesPage.verifyBillableSalesUserInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
				Thread.sleep(2000);
				boolean userInDecendinOrder = groupSalesPage.verifyBillableSalesUserInDescendingOrder();
				if (userInAscendinOrder & userInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInAscendinOrder = groupSalesPage.verifyBillableSalesOrganizationInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInDecendinOrder = groupSalesPage.verifyBillableSalesOrganizationInDescendingOrder();
				if (organizationInAscendinOrder & organizationInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
				Thread.sleep(2000);
				boolean taxIdInAscendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
				Thread.sleep(2000);
				boolean taxIdInDecendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInDescendingOrder();
				if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInAscendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInDecendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInDescendingOrder();
				if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Select selectDate = new Select(groupSalesPage.BillableSales_DateRange_DD);
				selectDate.selectByVisibleText("Custom Date Range");
				Thread.sleep(2000);
				groupSalesPage.BillableSalesselectStartdate(startDate).BillableSalesselectEndDate(endDate).BillableSales_ShowResults_BT.click();
				Thread.sleep(3000);
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(startDate, endDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within custom date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within custom date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				selectDate.selectByValue("3");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal1 = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				cal1.setTime(new Date());
				String selectedEndDate = dateFormat.format(cal1.getTime());
				System.out.println("endDate "+endDate);
				cal1.add(Calendar.MONTH, -3);
				String selectedStartDate = dateFormat.format(cal1.getTime());
				System.out.println("startDate "+startDate);
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("6");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				selectedEndDate = dateFormat.format(cal2.getTime());
				cal2.add(Calendar.MONTH, -6);
				selectedStartDate = dateFormat.format(cal2.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("9");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal3 = Calendar.getInstance();
				cal3.setTime(new Date());
				selectedEndDate = dateFormat.format(cal3.getTime());
				cal3.add(Calendar.MONTH, -9);
				selectedStartDate = dateFormat.format(cal3.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("12");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal4 = Calendar.getInstance();
				cal4.setTime(new Date());
				selectedEndDate = dateFormat.format(cal4.getTime());
				cal4.add(Calendar.MONTH, -12);
				selectedStartDate = dateFormat.format(cal4.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
			}	
	
//TC3400 : 	Verify that "Supervisor" users can access to the Billable Sales page and all Billable sales functions in the cloud.
			
			
			@Test()
			public void groupSales_US1900_TC3400() throws InterruptedException,
					RowsExceededException, BiffException, WriteException, IOException, ParseException {
				/** Variable Section : **/
				GroupSalesPage groupSalesPage;
				AbstractTest.tcName="groupSales_US1900_TC3400";
				String password = LoginTestData.supervisor_SSO_Password;
				String userId = LoginTestData.supervisor_SSO_UserId;
				String storeId = LoginTestData.supervisorStoreId;
				String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
				String invoicePONumber = String.valueOf(Base.generateNdigitRandomNumber(5));
				String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
				String addressLine1 = "HclTechnologies";
				String addressLine2 = "HclTechnologies";
				String city = "Noida";
				String zip = String.valueOf(Base.generateNdigitRandomNumber(5));
				String email = "testUser@mcd.com";
				String phoneNumber = String.valueOf(Base.generateNdigitRandomNumber(10));
				String state = "AK";
				String startDate = GlobalVariable.startDate;
				String endDate = GlobalVariable.endDate;
				/***********************************/
				HomePage homePage = PageFactory.initElements(driver, HomePage.class);
				// Navigate to Group sales page
				groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
						.goToGroupSalesPage();
				//go to Billable Sales Page
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT");
				Thread.sleep(2000);
				// Verify that columns are displaying
				if (Base.isElementDisplayed(groupSalesPage.BillableSales_DateAndTime_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_Register_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_Amount_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_DepositStatus_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_OrganizationName_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_InvoicePO_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_User_Label)) {
					Reporter.reportPassResult(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				//Click on edit button for first validated record
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "groupSalesPage.BillableSales_ValidatedRecord_Edit_BT");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
				//Edit all editable values in pop up and submit
				System.out.println("orgName "+orgName);
				Thread.sleep(3000);
				groupSalesPage.editBillableSalesRecord(orgName, invoicePONumber, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
				//Verify that new Organization name and Tax id is displayed in Tax exempt sales landing page
				if (groupSalesPage.verifyBillableSalesDetailsUpdated(orgName, invoicePONumber)) {
					Reporter.reportPassResult(
							browser,
							"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(1500);
				AbstractPage.executor.executeScript("window.scrollBy(0,100)", "");
				//Click on edit button for first validated record
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
				Select select = new Select(groupSalesPage.BillableSalesPopUp_State_DD);
				//Verify that all values are updated
				if(groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
					&& groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").equals(invoicePONumber)
					&& groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
					&& groupSalesPage.EditBillableSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
					&& groupSalesPage.EditBillableSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
					&& groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").equals(city)
					&& groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").equals(email)
					&& groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
					&& groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
					&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
					Reporter.reportPassResult(
							browser,
							"user should be able to view updated  values in edit Billable Sales Pop Up",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to view updated  values in edit Billable Sales Pop Up",
							"Fail");
					AbstractTest.takeSnapShot();
					}
				//Click on Submit Button
				GenericMethods.clickOnElement(groupSalesPage.BillableSalesPopUp_Submit_BT, "BillableSalesPopUp_Submit_BT");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.BillableSales_SalesSaved_Confirmation_MSG));
				Thread.sleep(5000);
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInAscendinOrder = groupSalesPage.verifyBillableSalesDateInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInDecendinOrder = groupSalesPage.verifyBillableSalesDateInDescendingOrder();
				if (dateInAscendinOrder & dateInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
				Thread.sleep(2000);
				boolean registerInAscendinOrder = groupSalesPage.verifyBillableSalesRegisterInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
				Thread.sleep(2000);
				boolean registerInDecendinOrder = groupSalesPage.verifyBillableSalesRegisterInDescendingOrder();
				if (registerInAscendinOrder & registerInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
				Thread.sleep(2000);
				boolean amountInAscendinOrder = groupSalesPage.verifyBillableSalesAmountInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
				Thread.sleep(2000);
				boolean amountInDecendinOrder = groupSalesPage.verifyBillableSalesAmountInDescendingOrder();
				if (amountInAscendinOrder & amountInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
				Thread.sleep(2000);
				boolean userInAscendinOrder = groupSalesPage.verifyBillableSalesUserInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
				Thread.sleep(2000);
				boolean userInDecendinOrder = groupSalesPage.verifyBillableSalesUserInDescendingOrder();
				if (userInAscendinOrder & userInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInAscendinOrder = groupSalesPage.verifyBillableSalesOrganizationInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInDecendinOrder = groupSalesPage.verifyBillableSalesOrganizationInDescendingOrder();
				if (organizationInAscendinOrder & organizationInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
				Thread.sleep(2000);
				boolean taxIdInAscendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
				Thread.sleep(2000);
				boolean taxIdInDecendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInDescendingOrder();
				if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInAscendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInDecendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInDescendingOrder();
				if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Select selectDate = new Select(groupSalesPage.BillableSales_DateRange_DD);
				selectDate.selectByVisibleText("Custom Date Range");
				Thread.sleep(2000);
				groupSalesPage.BillableSalesselectStartdate(startDate).BillableSalesselectEndDate(endDate).BillableSales_ShowResults_BT.click();
				Thread.sleep(3000);
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(startDate, endDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within custom date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within custom date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				selectDate.selectByValue("3");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal1 = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				cal1.setTime(new Date());
				String selectedEndDate = dateFormat.format(cal1.getTime());
				System.out.println("endDate "+endDate);
				cal1.add(Calendar.MONTH, -3);
				String selectedStartDate = dateFormat.format(cal1.getTime());
				System.out.println("startDate "+startDate);
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("6");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				selectedEndDate = dateFormat.format(cal2.getTime());
				cal2.add(Calendar.MONTH, -6);
				selectedStartDate = dateFormat.format(cal2.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("9");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal3 = Calendar.getInstance();
				cal3.setTime(new Date());
				selectedEndDate = dateFormat.format(cal3.getTime());
				cal3.add(Calendar.MONTH, -9);
				selectedStartDate = dateFormat.format(cal3.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("12");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal4 = Calendar.getInstance();
				cal4.setTime(new Date());
				selectedEndDate = dateFormat.format(cal4.getTime());
				cal4.add(Calendar.MONTH, -12);
				selectedStartDate = dateFormat.format(cal4.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
			}	
	
//TC3407 : 	Verify that "Supervisor w/ Role Assignment" users can access to the Billable Sales page and all Billable sales functions in the cloud.
			
			
			@Test()
			public void groupSales_US1900_TC3407() throws InterruptedException,
					RowsExceededException, BiffException, WriteException, IOException, ParseException {
				/** Variable Section : **/
				GroupSalesPage groupSalesPage;
				AbstractTest.tcName="groupSales_US1900_TC3407";
				String password = LoginTestData.supervisorWithRoleAssignment_SSO_Password;
				String userId = LoginTestData.supervisorWithRoleAssignment_SSO_UserId;
				String storeId = LoginTestData.supervisorWithRoleAssignmentStoreId;
				String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
				String invoicePONumber = String.valueOf(Base.generateNdigitRandomNumber(5));
				String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
				String addressLine1 = "HclTechnologies";
				String addressLine2 = "HclTechnologies";
				String city = "Noida";
				String zip = String.valueOf(Base.generateNdigitRandomNumber(5));
				String email = "testUser@mcd.com";
				String phoneNumber = String.valueOf(Base.generateNdigitRandomNumber(10));
				String state = "AK";
				String startDate = GlobalVariable.startDate;
				String endDate = GlobalVariable.endDate;
				/***********************************/
				HomePage homePage = PageFactory.initElements(driver, HomePage.class);
				// Navigate to Group sales page
				groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
						.goToGroupSalesPage();
				//go to Billable Sales Page
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT");
				Thread.sleep(2000);
				// Verify that columns are displaying
				if (Base.isElementDisplayed(groupSalesPage.BillableSales_DateAndTime_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_Register_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_Amount_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_DepositStatus_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_OrganizationName_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_InvoicePO_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_User_Label)) {
					Reporter.reportPassResult(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				//Click on edit button for first validated record
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "groupSalesPage.BillableSales_ValidatedRecord_Edit_BT");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
				//Edit all editable values in pop up and submit
				System.out.println("orgName "+orgName);
				Thread.sleep(3000);
				groupSalesPage.editBillableSalesRecord(orgName, invoicePONumber, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
				//Verify that new Organization name and Tax id is displayed in Tax exempt sales landing page
				if (groupSalesPage.verifyBillableSalesDetailsUpdated(orgName, invoicePONumber)) {
					Reporter.reportPassResult(
							browser,
							"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(1500);
				AbstractPage.executor.executeScript("window.scrollBy(0,100)", "");
				//Click on edit button for first validated record
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
				Select select = new Select(groupSalesPage.BillableSalesPopUp_State_DD);
				//Verify that all values are updated
				if(groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
					&& groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").equals(invoicePONumber)
					&& groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
					&& groupSalesPage.EditBillableSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
					&& groupSalesPage.EditBillableSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
					&& groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").equals(city)
					&& groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").equals(email)
					&& groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
					&& groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
					&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
					Reporter.reportPassResult(
							browser,
							"user should be able to view updated  values in edit Billable Sales Pop Up",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to view updated  values in edit Billable Sales Pop Up",
							"Fail");
					AbstractTest.takeSnapShot();
					}
				//Click on Submit Button
				GenericMethods.clickOnElement(groupSalesPage.BillableSalesPopUp_Submit_BT, "BillableSalesPopUp_Submit_BT");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.BillableSales_SalesSaved_Confirmation_MSG));
				Thread.sleep(5000);
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInAscendinOrder = groupSalesPage.verifyBillableSalesDateInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInDecendinOrder = groupSalesPage.verifyBillableSalesDateInDescendingOrder();
				if (dateInAscendinOrder & dateInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
				Thread.sleep(2000);
				boolean registerInAscendinOrder = groupSalesPage.verifyBillableSalesRegisterInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
				Thread.sleep(2000);
				boolean registerInDecendinOrder = groupSalesPage.verifyBillableSalesRegisterInDescendingOrder();
				if (registerInAscendinOrder & registerInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
				Thread.sleep(2000);
				boolean amountInAscendinOrder = groupSalesPage.verifyBillableSalesAmountInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
				Thread.sleep(2000);
				boolean amountInDecendinOrder = groupSalesPage.verifyBillableSalesAmountInDescendingOrder();
				if (amountInAscendinOrder & amountInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
				Thread.sleep(2000);
				boolean userInAscendinOrder = groupSalesPage.verifyBillableSalesUserInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
				Thread.sleep(2000);
				boolean userInDecendinOrder = groupSalesPage.verifyBillableSalesUserInDescendingOrder();
				if (userInAscendinOrder & userInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInAscendinOrder = groupSalesPage.verifyBillableSalesOrganizationInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInDecendinOrder = groupSalesPage.verifyBillableSalesOrganizationInDescendingOrder();
				if (organizationInAscendinOrder & organizationInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
				Thread.sleep(2000);
				boolean taxIdInAscendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
				Thread.sleep(2000);
				boolean taxIdInDecendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInDescendingOrder();
				if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInAscendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInDecendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInDescendingOrder();
				if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Select selectDate = new Select(groupSalesPage.BillableSales_DateRange_DD);
				selectDate.selectByVisibleText("Custom Date Range");
				Thread.sleep(2000);
				groupSalesPage.BillableSalesselectStartdate(startDate).BillableSalesselectEndDate(endDate).BillableSales_ShowResults_BT.click();
				Thread.sleep(3000);
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(startDate, endDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within custom date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within custom date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				selectDate.selectByValue("3");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal1 = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				cal1.setTime(new Date());
				String selectedEndDate = dateFormat.format(cal1.getTime());
				System.out.println("endDate "+endDate);
				cal1.add(Calendar.MONTH, -3);
				String selectedStartDate = dateFormat.format(cal1.getTime());
				System.out.println("startDate "+startDate);
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("6");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				selectedEndDate = dateFormat.format(cal2.getTime());
				cal2.add(Calendar.MONTH, -6);
				selectedStartDate = dateFormat.format(cal2.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("9");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal3 = Calendar.getInstance();
				cal3.setTime(new Date());
				selectedEndDate = dateFormat.format(cal3.getTime());
				cal3.add(Calendar.MONTH, -9);
				selectedStartDate = dateFormat.format(cal3.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("12");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal4 = Calendar.getInstance();
				cal4.setTime(new Date());
				selectedEndDate = dateFormat.format(cal4.getTime());
				cal4.add(Calendar.MONTH, -12);
				selectedStartDate = dateFormat.format(cal4.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
			}	
		
	
	
//TC3411 : 	Verify that "Operator" users can access to the Billable Sales page and all Billable sales functions in the cloud.
			
			
			@Test()
			public void groupSales_US1900_TC3411() throws InterruptedException,
					RowsExceededException, BiffException, WriteException, IOException, ParseException {
				/** Variable Section : **/
				GroupSalesPage groupSalesPage;
				AbstractTest.tcName="groupSales_US1900_TC3411";
				String password = LoginTestData.operator_SSO_Password;
				String userId = LoginTestData.operator_SSO_UserId;
				String storeId = LoginTestData.operatorStoreId;
				String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
				String invoicePONumber = String.valueOf(Base.generateNdigitRandomNumber(5));
				String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
				String addressLine1 = "HclTechnologies";
				String addressLine2 = "HclTechnologies";
				String city = "Noida";
				String zip = String.valueOf(Base.generateNdigitRandomNumber(5));
				String email = "testUser@mcd.com";
				String phoneNumber = String.valueOf(Base.generateNdigitRandomNumber(10));
				String state = "AK";
				String startDate = GlobalVariable.startDate;
				String endDate = GlobalVariable.endDate;
				/***********************************/
				HomePage homePage = PageFactory.initElements(driver, HomePage.class);
				// Navigate to Group sales page
				groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
						.goToGroupSalesPage();
				//go to Billable Sales Page
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT");
				Thread.sleep(2000);
				// Verify that columns are displaying
				if (Base.isElementDisplayed(groupSalesPage.BillableSales_DateAndTime_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_Register_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_Amount_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_DepositStatus_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_OrganizationName_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_InvoicePO_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_User_Label)) {
					Reporter.reportPassResult(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				//Click on edit button for first validated record
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "groupSalesPage.BillableSales_ValidatedRecord_Edit_BT");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
				//Edit all editable values in pop up and submit
				System.out.println("orgName "+orgName);
				Thread.sleep(3000);
				groupSalesPage.editBillableSalesRecord(orgName, invoicePONumber, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
				//Verify that new Organization name and Tax id is displayed in Tax exempt sales landing page
				if (groupSalesPage.verifyBillableSalesDetailsUpdated(orgName, invoicePONumber)) {
					Reporter.reportPassResult(
							browser,
							"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(1500);
				AbstractPage.executor.executeScript("window.scrollBy(0,100)", "");
				//Click on edit button for first validated record
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
				Select select = new Select(groupSalesPage.BillableSalesPopUp_State_DD);
				//Verify that all values are updated
				if(groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
					&& groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").equals(invoicePONumber)
					&& groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
					&& groupSalesPage.EditBillableSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
					&& groupSalesPage.EditBillableSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
					&& groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").equals(city)
					&& groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").equals(email)
					&& groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
					&& groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
					&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
					Reporter.reportPassResult(
							browser,
							"user should be able to view updated  values in edit Billable Sales Pop Up",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to view updated  values in edit Billable Sales Pop Up",
							"Fail");
					AbstractTest.takeSnapShot();
					}
				//Click on Submit Button
				GenericMethods.clickOnElement(groupSalesPage.BillableSalesPopUp_Submit_BT, "BillableSalesPopUp_Submit_BT");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.BillableSales_SalesSaved_Confirmation_MSG));
				Thread.sleep(5000);
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInAscendinOrder = groupSalesPage.verifyBillableSalesDateInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInDecendinOrder = groupSalesPage.verifyBillableSalesDateInDescendingOrder();
				if (dateInAscendinOrder & dateInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
				Thread.sleep(2000);
				boolean registerInAscendinOrder = groupSalesPage.verifyBillableSalesRegisterInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
				Thread.sleep(2000);
				boolean registerInDecendinOrder = groupSalesPage.verifyBillableSalesRegisterInDescendingOrder();
				if (registerInAscendinOrder & registerInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
				Thread.sleep(2000);
				boolean amountInAscendinOrder = groupSalesPage.verifyBillableSalesAmountInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
				Thread.sleep(2000);
				boolean amountInDecendinOrder = groupSalesPage.verifyBillableSalesAmountInDescendingOrder();
				if (amountInAscendinOrder & amountInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
				Thread.sleep(2000);
				boolean userInAscendinOrder = groupSalesPage.verifyBillableSalesUserInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
				Thread.sleep(2000);
				boolean userInDecendinOrder = groupSalesPage.verifyBillableSalesUserInDescendingOrder();
				if (userInAscendinOrder & userInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInAscendinOrder = groupSalesPage.verifyBillableSalesOrganizationInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInDecendinOrder = groupSalesPage.verifyBillableSalesOrganizationInDescendingOrder();
				if (organizationInAscendinOrder & organizationInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
				Thread.sleep(2000);
				boolean taxIdInAscendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
				Thread.sleep(2000);
				boolean taxIdInDecendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInDescendingOrder();
				if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInAscendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInDecendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInDescendingOrder();
				if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Select selectDate = new Select(groupSalesPage.BillableSales_DateRange_DD);
				selectDate.selectByVisibleText("Custom Date Range");
				Thread.sleep(2000);
				groupSalesPage.BillableSalesselectStartdate(startDate).BillableSalesselectEndDate(endDate).BillableSales_ShowResults_BT.click();
				Thread.sleep(3000);
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(startDate, endDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within custom date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within custom date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				selectDate.selectByValue("3");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal1 = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				cal1.setTime(new Date());
				String selectedEndDate = dateFormat.format(cal1.getTime());
				System.out.println("endDate "+endDate);
				cal1.add(Calendar.MONTH, -3);
				String selectedStartDate = dateFormat.format(cal1.getTime());
				System.out.println("startDate "+startDate);
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("6");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				selectedEndDate = dateFormat.format(cal2.getTime());
				cal2.add(Calendar.MONTH, -6);
				selectedStartDate = dateFormat.format(cal2.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("9");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal3 = Calendar.getInstance();
				cal3.setTime(new Date());
				selectedEndDate = dateFormat.format(cal3.getTime());
				cal3.add(Calendar.MONTH, -9);
				selectedStartDate = dateFormat.format(cal3.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("12");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal4 = Calendar.getInstance();
				cal4.setTime(new Date());
				selectedEndDate = dateFormat.format(cal4.getTime());
				cal4.add(Calendar.MONTH, -12);
				selectedStartDate = dateFormat.format(cal4.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
			}	
	
	
//TC3412 : 	Verify that "Org Admin" users can access to the Billable Sales page and all Billable sales functions in the cloud.
			
			
			@Test()
			public void groupSales_US1900_TC3412() throws InterruptedException,
					RowsExceededException, BiffException, WriteException, IOException, ParseException {
				/** Variable Section : **/
				GroupSalesPage groupSalesPage;
				AbstractTest.tcName="groupSales_US1900_TC3412";
				String password = LoginTestData.orgAdmin_SSO_Password;
				String userId = LoginTestData.orgAdmin_SSO_UserId;
				String storeId = LoginTestData.orgAdminStoreId;
				String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
				String invoicePONumber = String.valueOf(Base.generateNdigitRandomNumber(5));
				String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
				String addressLine1 = "HclTechnologies";
				String addressLine2 = "HclTechnologies";
				String city = "Noida";
				String zip = String.valueOf(Base.generateNdigitRandomNumber(5));
				String email = "testUser@mcd.com";
				String phoneNumber = String.valueOf(Base.generateNdigitRandomNumber(10));
				String state = "AK";
				String startDate = GlobalVariable.startDate;
				String endDate = GlobalVariable.endDate;
				/***********************************/
				HomePage homePage = PageFactory.initElements(driver, HomePage.class);
				// Navigate to Group sales page
				groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
						.goToGroupSalesPage();
				//go to Billable Sales Page
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT");
				Thread.sleep(2000);
				// Verify that columns are displaying
				if (Base.isElementDisplayed(groupSalesPage.BillableSales_DateAndTime_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_Register_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_Amount_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_DepositStatus_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_OrganizationName_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_InvoicePO_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_User_Label)) {
					Reporter.reportPassResult(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				//Click on edit button for first validated record
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "groupSalesPage.BillableSales_ValidatedRecord_Edit_BT");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
				//Edit all editable values in pop up and submit
				System.out.println("orgName "+orgName);
				Thread.sleep(3000);
				groupSalesPage.editBillableSalesRecord(orgName, invoicePONumber, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
				//Verify that new Organization name and Tax id is displayed in Tax exempt sales landing page
				if (groupSalesPage.verifyBillableSalesDetailsUpdated(orgName, invoicePONumber)) {
					Reporter.reportPassResult(
							browser,
							"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to view updated  organizationName and invoicePONumber in Billable Sales Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(1500);
				AbstractPage.executor.executeScript("window.scrollBy(0,100)", "");
				//Click on edit button for first validated record
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
				Select select = new Select(groupSalesPage.BillableSalesPopUp_State_DD);
				//Verify that all values are updated
				if(groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
					&& groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").equals(invoicePONumber)
					&& groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
					&& groupSalesPage.EditBillableSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
					&& groupSalesPage.EditBillableSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
					&& groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").equals(city)
					&& groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").equals(email)
					&& groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
					&& groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
					&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
					Reporter.reportPassResult(
							browser,
							"user should be able to view updated  values in edit Billable Sales Pop Up",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to view updated  values in edit Billable Sales Pop Up",
							"Fail");
					AbstractTest.takeSnapShot();
					}
				//Click on Submit Button
				GenericMethods.clickOnElement(groupSalesPage.BillableSalesPopUp_Submit_BT, "BillableSalesPopUp_Submit_BT");
				wait.until(ExpectedConditions.visibilityOf(groupSalesPage.BillableSales_SalesSaved_Confirmation_MSG));
				Thread.sleep(5000);
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInAscendinOrder = groupSalesPage.verifyBillableSalesDateInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInDecendinOrder = groupSalesPage.verifyBillableSalesDateInDescendingOrder();
				if (dateInAscendinOrder & dateInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
				Thread.sleep(2000);
				boolean registerInAscendinOrder = groupSalesPage.verifyBillableSalesRegisterInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
				Thread.sleep(2000);
				boolean registerInDecendinOrder = groupSalesPage.verifyBillableSalesRegisterInDescendingOrder();
				if (registerInAscendinOrder & registerInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
				Thread.sleep(2000);
				boolean amountInAscendinOrder = groupSalesPage.verifyBillableSalesAmountInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
				Thread.sleep(2000);
				boolean amountInDecendinOrder = groupSalesPage.verifyBillableSalesAmountInDescendingOrder();
				if (amountInAscendinOrder & amountInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
				Thread.sleep(2000);
				boolean userInAscendinOrder = groupSalesPage.verifyBillableSalesUserInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
				Thread.sleep(2000);
				boolean userInDecendinOrder = groupSalesPage.verifyBillableSalesUserInDescendingOrder();
				if (userInAscendinOrder & userInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInAscendinOrder = groupSalesPage.verifyBillableSalesOrganizationInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInDecendinOrder = groupSalesPage.verifyBillableSalesOrganizationInDescendingOrder();
				if (organizationInAscendinOrder & organizationInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
				Thread.sleep(2000);
				boolean taxIdInAscendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
				Thread.sleep(2000);
				boolean taxIdInDecendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInDescendingOrder();
				if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInAscendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInDecendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInDescendingOrder();
				if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Select selectDate = new Select(groupSalesPage.BillableSales_DateRange_DD);
				selectDate.selectByVisibleText("Custom Date Range");
				Thread.sleep(2000);
				groupSalesPage.BillableSalesselectStartdate(startDate).BillableSalesselectEndDate(endDate).BillableSales_ShowResults_BT.click();
				Thread.sleep(3000);
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(startDate, endDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within custom date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within custom date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				selectDate.selectByValue("3");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal1 = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				cal1.setTime(new Date());
				String selectedEndDate = dateFormat.format(cal1.getTime());
				System.out.println("endDate "+endDate);
				cal1.add(Calendar.MONTH, -3);
				String selectedStartDate = dateFormat.format(cal1.getTime());
				System.out.println("startDate "+startDate);
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("6");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				selectedEndDate = dateFormat.format(cal2.getTime());
				cal2.add(Calendar.MONTH, -6);
				selectedStartDate = dateFormat.format(cal2.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("9");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal3 = Calendar.getInstance();
				cal3.setTime(new Date());
				selectedEndDate = dateFormat.format(cal3.getTime());
				cal3.add(Calendar.MONTH, -9);
				selectedStartDate = dateFormat.format(cal3.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("12");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal4 = Calendar.getInstance();
				cal4.setTime(new Date());
				selectedEndDate = dateFormat.format(cal4.getTime());
				cal4.add(Calendar.MONTH, -12);
				selectedStartDate = dateFormat.format(cal4.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
			}	
	
	
//TC3414 : 	Verify that "Level 6" users can access to the Billable Sales page and all Billable sales functions in the cloud.
			
			
			@Test()
			public void groupSales_US1900_TC3414() throws InterruptedException,
					RowsExceededException, BiffException, WriteException, IOException, ParseException {
				/** Variable Section : **/
				GroupSalesPage groupSalesPage;
				AbstractTest.tcName="groupSales_US1900_TC3414";
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
				//go to Billable Sales Page
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT");
				Thread.sleep(2000);
				// Verify that columns are displaying
				if (Base.isElementDisplayed(groupSalesPage.BillableSales_DateAndTime_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_Register_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_Amount_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_DepositStatus_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_OrganizationName_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_InvoicePO_Label)
						& Base.isElementDisplayed(groupSalesPage.BillableSales_User_Label)) {
					Reporter.reportPassResult(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Billable Sales Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				//Verify Edit button is not showing against any of the records
				
				List <WebElement> button=driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[16]/eb-button/button"));
				for(int i=0;i<button.size();i++)
				{
					String text=null;
					System.out.println("text iss "+GenericMethods.getText(button.get(i), "button.get"));
					text=GenericMethods.getText(button.get(i), "button.get("+i+")");
					if(text.equalsIgnoreCase("View") && i==button.size()-1)
					{
						Reporter.reportPassResult(
								browser,
								"Edit option should not be display against any of teh records",
								"Pass");
						break;
					}
					else if(text.equalsIgnoreCase("View"))
					{
						continue;
					}
					else
					{
						Reporter.reportTestFailure(
								browser,
								"Edit option should not be display against any of teh records",
								"Fail");
						AbstractTest.takeSnapShot();
						break;
					}
				}
				Thread.sleep(2000);
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInAscendinOrder = groupSalesPage.verifyBillableSalesDateInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DateAndTime_Label, "BillableSales_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInDecendinOrder = groupSalesPage.verifyBillableSalesDateInDescendingOrder();
				if (dateInAscendinOrder & dateInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending date in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
				Thread.sleep(2000);
				boolean registerInAscendinOrder = groupSalesPage.verifyBillableSalesRegisterInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Register_Label, "BillableSales_Register_Label");
				Thread.sleep(2000);
				boolean registerInDecendinOrder = groupSalesPage.verifyBillableSalesRegisterInDescendingOrder();
				if (registerInAscendinOrder & registerInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending register value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
				Thread.sleep(2000);
				boolean amountInAscendinOrder = groupSalesPage.verifyBillableSalesAmountInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_Amount_Label, "BillableSales_Amount_Label");
				Thread.sleep(2000);
				boolean amountInDecendinOrder = groupSalesPage.verifyBillableSalesAmountInDescendingOrder();
				if (amountInAscendinOrder & amountInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending amount value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
				Thread.sleep(2000);
				boolean userInAscendinOrder = groupSalesPage.verifyBillableSalesUserInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_User_Label, "BillableSales_User_Label");
				Thread.sleep(2000);
				boolean userInDecendinOrder = groupSalesPage.verifyBillableSalesUserInDescendingOrder();
				if (userInAscendinOrder & userInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending user value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInAscendinOrder = groupSalesPage.verifyBillableSalesOrganizationInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_OrganizationName_Label, "BillableSales_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInDecendinOrder = groupSalesPage.verifyBillableSalesOrganizationInDescendingOrder();
				if (organizationInAscendinOrder & organizationInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending organization value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
				Thread.sleep(2000);
				boolean taxIdInAscendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_InvoicePO_Label, "BillableSales_InvoicePO_Label");
				Thread.sleep(2000);
				boolean taxIdInDecendinOrder = groupSalesPage.verifyBillableSalesInvoicePOInDescendingOrder();
				if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending Invoice ID value in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInAscendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_DepositStatus_Label, "BillableSales_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInDecendinOrder = groupSalesPage.verifyBillableSalesDepositStatusInDescendingOrder();
				if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Tax exempt Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Select selectDate = new Select(groupSalesPage.BillableSales_DateRange_DD);
				selectDate.selectByVisibleText("Custom Date Range");
				Thread.sleep(2000);
				groupSalesPage.BillableSalesselectStartdate(startDate).BillableSalesselectEndDate(endDate).BillableSales_ShowResults_BT.click();
				Thread.sleep(3000);
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(startDate, endDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within custom date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within custom date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				selectDate.selectByValue("3");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal1 = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				cal1.setTime(new Date());
				String selectedEndDate = dateFormat.format(cal1.getTime());
				System.out.println("endDate "+endDate);
				cal1.add(Calendar.MONTH, -3);
				String selectedStartDate = dateFormat.format(cal1.getTime());
				System.out.println("startDate "+startDate);
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 3 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 3 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("6");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				selectedEndDate = dateFormat.format(cal2.getTime());
				cal2.add(Calendar.MONTH, -6);
				selectedStartDate = dateFormat.format(cal2.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 6 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("9");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal3 = Calendar.getInstance();
				cal3.setTime(new Date());
				selectedEndDate = dateFormat.format(cal3.getTime());
				cal3.add(Calendar.MONTH, -9);
				selectedStartDate = dateFormat.format(cal3.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 9 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("12");
				GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT,"BillableSales_ShowResults_BT");
				Calendar cal4 = Calendar.getInstance();
				cal4.setTime(new Date());
				selectedEndDate = dateFormat.format(cal4.getTime());
				cal4.add(Calendar.MONTH, -12);
				selectedStartDate = dateFormat.format(cal4.getTime());
				if(groupSalesPage.BillableSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.BillableSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyBillableSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 12 month date range in Billable Sales Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
			}		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
