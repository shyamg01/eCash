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
import eCashPageClasses.AbstractTest;
import eCashPageClasses.GroupSalesPage;
import eCashPageClasses.HomePage;

public class US1901_TaxExemptSalesBundleUserRoleAccess extends AbstractTest{
	
	//TC3372: Verify that "Level 1" users can access to the Tax Exempt Sales page and all tax exempt sales functions in the cloud.
	@Test()
	public void groupSales_US1901_TC3372() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1901_TC3372";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String taxId = String.valueOf(Base.generateNdigitRandomNumber(5));
		String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine1 = "HclTechnologies" +String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine2 = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String city = "Noida"+String.valueOf(Base.generateNdigitRandomNumber(3));
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
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.TextExemptSales_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_User_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_TaxIDNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		Thread.sleep(3000);
		//Edit all editable values in pop up and submit
		groupSalesPage.editTaxExemptSalesRecord(orgName, taxId, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyTaxExemptSalesDetailsUpdated(orgName, taxId)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		Select select = new Select(groupSalesPage.EditTaxExemptSalesPopUp_State_DD);
		//Verify that all values are updated
		System.out.println(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName));
		System.out.println(groupSalesPage.EditTaxExemptSalesPopUp_TaxId_TB.getAttribute("value").equals(taxId));
		System.out.println(groupSalesPage.EditTaxExemptSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName));
		System.out.println(groupSalesPage.EditTaxExemptSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1));
		System.out.println(groupSalesPage.EditTaxExemptSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2));
		System.out.println(groupSalesPage.EditTaxExemptSalesPopUp_City_TB.getAttribute("value").equals(city));
		System.out.println(groupSalesPage.EditTaxExemptSalesPopUp_Email_TB.getAttribute("value").equals(email));
		System.out.println(groupSalesPage.EditTaxExemptSalesPopUp_Zip_TB.getAttribute("value").equals(zip));
		System.out.println(groupSalesPage.EditTaxExemptSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber));
		System.out.println(select.getFirstSelectedOption().getAttribute("value").equals(state));
		if(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_TaxId_TB.getAttribute("value").equals(taxId)
			&& groupSalesPage.EditTaxExemptSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
			&& groupSalesPage.EditTaxExemptSalesPopUp_City_TB.getAttribute("value").equals(city)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Email_TB.getAttribute("value").equals(email)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
			&& groupSalesPage.EditTaxExemptSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
			&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditTaxExemptSalesPopUp_Submit_BT, "EditTaxExemptSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.TaxExemptSales_SalesSaved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyTextExemptSalesDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyTextExemptSalesDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyTextExemptSalesAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyTextExemptSalesAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyTextExemptSalesUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyTextExemptSalesUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		/*GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		*/
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInDescendingOrder();
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
		Select selectDate = new Select(groupSalesPage.TextExempt_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.selectStartdate(startDate).selectEndDate(endDate).TaxExemptSales_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3373: Verify that "Level 2" users can access to the Tax Exempt Sales page and all tax exempt sales functions in the cloud.
	@Test()
	public void groupSales_US1901_TC3373() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1901_TC3373";
		String password = LoginTestData.level2_SSO_Password;
		String userId = LoginTestData.level2_SSO_UserId;
		String storeId = LoginTestData.level2StoreId;
		String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String taxId = String.valueOf(Base.generateNdigitRandomNumber(5));
		String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine1 = "HclTechnologies" +String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine2 = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String city = "Noida"+String.valueOf(Base.generateNdigitRandomNumber(3));
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
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.TextExemptSales_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_User_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_TaxIDNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editTaxExemptSalesRecord(orgName, taxId, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyTaxExemptSalesDetailsUpdated(orgName, taxId)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		Select select = new Select(groupSalesPage.EditTaxExemptSalesPopUp_State_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_TaxId_TB.getAttribute("value").equals(taxId)
			&& groupSalesPage.EditTaxExemptSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
			&& groupSalesPage.EditTaxExemptSalesPopUp_City_TB.getAttribute("value").equals(city)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Email_TB.getAttribute("value").equals(email)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
			&& groupSalesPage.EditTaxExemptSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
			&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditTaxExemptSalesPopUp_Submit_BT, "EditTaxExemptSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.TaxExemptSales_SalesSaved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyTextExemptSalesDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyTextExemptSalesDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyTextExemptSalesAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyTextExemptSalesAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyTextExemptSalesUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyTextExemptSalesUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
	/*	GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}*/
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInDescendingOrder();
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
		Select selectDate = new Select(groupSalesPage.TextExempt_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.selectStartdate(startDate).selectEndDate(endDate).TaxExemptSales_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3373: Verify that "Level 3" users can access to the Tax Exempt Sales page and all tax exempt sales functions in the cloud.
	@Test()
	public void groupSales_US1901_TC3374() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1901_TC3374";
		String password = LoginTestData.level3_SSO_Password;
		String userId = LoginTestData.level3_SSO_UserId;
		String storeId = LoginTestData.level3StoreId;
		String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String taxId = String.valueOf(Base.generateNdigitRandomNumber(5));
		String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine1 = "HclTechnologies" +String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine2 = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String city = "Noida"+String.valueOf(Base.generateNdigitRandomNumber(3));
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
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.TextExemptSales_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_User_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_TaxIDNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editTaxExemptSalesRecord(orgName, taxId, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyTaxExemptSalesDetailsUpdated(orgName, taxId)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		Select select = new Select(groupSalesPage.EditTaxExemptSalesPopUp_State_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_TaxId_TB.getAttribute("value").equals(taxId)
			&& groupSalesPage.EditTaxExemptSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
			&& groupSalesPage.EditTaxExemptSalesPopUp_City_TB.getAttribute("value").equals(city)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Email_TB.getAttribute("value").equals(email)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
			&& groupSalesPage.EditTaxExemptSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
			&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditTaxExemptSalesPopUp_Submit_BT, "EditTaxExemptSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.TaxExemptSales_SalesSaved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyTextExemptSalesDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyTextExemptSalesDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyTextExemptSalesAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyTextExemptSalesAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyTextExemptSalesUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyTextExemptSalesUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		/*GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}*/
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInDescendingOrder();
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
		Select selectDate = new Select(groupSalesPage.TextExempt_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.selectStartdate(startDate).selectEndDate(endDate).TaxExemptSales_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3376: Verify that "Level 4" users can access to the Tax Exempt Sales page and all tax exempt sales functions in the cloud.
	@Test(enabled=false)
	public void groupSales_US1901_TC3376() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1901_TC3376";
		String password = LoginTestData.level4_SSO_Password;
		String userId = LoginTestData.level4_SSO_UserId;
		String storeId = LoginTestData.level4StoreId;
		String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String taxId = String.valueOf(Base.generateNdigitRandomNumber(5));
		String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine1 = "HclTechnologies" +String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine2 = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String city = "Noida"+String.valueOf(Base.generateNdigitRandomNumber(3));
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
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.TextExemptSales_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_User_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_TaxIDNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editTaxExemptSalesRecord(orgName, taxId, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyTaxExemptSalesDetailsUpdated(orgName, taxId)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		Select select = new Select(groupSalesPage.EditTaxExemptSalesPopUp_State_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_TaxId_TB.getAttribute("value").equals(taxId)
			&& groupSalesPage.EditTaxExemptSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
			&& groupSalesPage.EditTaxExemptSalesPopUp_City_TB.getAttribute("value").equals(city)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Email_TB.getAttribute("value").equals(email)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
			&& groupSalesPage.EditTaxExemptSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
			&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditTaxExemptSalesPopUp_Submit_BT, "EditTaxExemptSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.TaxExemptSales_SalesSaved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyTextExemptSalesDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyTextExemptSalesDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyTextExemptSalesAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyTextExemptSalesAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyTextExemptSalesUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyTextExemptSalesUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		/*GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}*/
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInDescendingOrder();
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
		Select selectDate = new Select(groupSalesPage.TextExempt_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.selectStartdate(startDate).selectEndDate(endDate).TaxExemptSales_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3376: Verify that "Level 5" users can access to the Tax Exempt Sales page and all tax exempt sales functions in the cloud.
	@Test()
	public void groupSales_US1901_TC3377() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1901_TC3377";
		String password = LoginTestData.level5_SSO_Password;
		String userId = LoginTestData.level5_SSO_UserId;
		String storeId = LoginTestData.level5StoreId;
		String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String taxId = String.valueOf(Base.generateNdigitRandomNumber(5));
		String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine1 = "HclTechnologies" +String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine2 = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String city = "Noida"+String.valueOf(Base.generateNdigitRandomNumber(3));
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
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.TextExemptSales_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_User_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_TaxIDNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editTaxExemptSalesRecord(orgName, taxId, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyTaxExemptSalesDetailsUpdated(orgName, taxId)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		Select select = new Select(groupSalesPage.EditTaxExemptSalesPopUp_State_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_TaxId_TB.getAttribute("value").equals(taxId)
			&& groupSalesPage.EditTaxExemptSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
			&& groupSalesPage.EditTaxExemptSalesPopUp_City_TB.getAttribute("value").equals(city)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Email_TB.getAttribute("value").equals(email)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
			&& groupSalesPage.EditTaxExemptSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
			&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditTaxExemptSalesPopUp_Submit_BT, "EditTaxExemptSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.TaxExemptSales_SalesSaved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyTextExemptSalesDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyTextExemptSalesDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyTextExemptSalesAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyTextExemptSalesAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyTextExemptSalesUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyTextExemptSalesUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		/*GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}*/
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInDescendingOrder();
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
		Select selectDate = new Select(groupSalesPage.TextExempt_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.selectStartdate(startDate).selectEndDate(endDate).TaxExemptSales_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3378: Verify that "Level 5" users can access to the Tax Exempt Sales page and all tax exempt sales functions in the cloud.
	@Test()
	public void groupSales_US1901_TC3378() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1901_TC3378";
		String password = LoginTestData.supervisor_SSO_Password;
		String userId = LoginTestData.supervisor_SSO_UserId;
		String storeId = LoginTestData.supervisorStoreId;
		String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String taxId = String.valueOf(Base.generateNdigitRandomNumber(5));
		String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine1 = "HclTechnologies" +String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine2 = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String city = "Noida"+String.valueOf(Base.generateNdigitRandomNumber(3));
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
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.TextExemptSales_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_User_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_TaxIDNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editTaxExemptSalesRecord(orgName, taxId, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyTaxExemptSalesDetailsUpdated(orgName, taxId)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		Select select = new Select(groupSalesPage.EditTaxExemptSalesPopUp_State_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_TaxId_TB.getAttribute("value").equals(taxId)
			&& groupSalesPage.EditTaxExemptSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
			&& groupSalesPage.EditTaxExemptSalesPopUp_City_TB.getAttribute("value").equals(city)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Email_TB.getAttribute("value").equals(email)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
			&& groupSalesPage.EditTaxExemptSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
			&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditTaxExemptSalesPopUp_Submit_BT, "EditTaxExemptSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.TaxExemptSales_SalesSaved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyTextExemptSalesDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyTextExemptSalesDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyTextExemptSalesAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyTextExemptSalesAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyTextExemptSalesUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyTextExemptSalesUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		/*GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		*/
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInDescendingOrder();
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
		Select selectDate = new Select(groupSalesPage.TextExempt_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.selectStartdate(startDate).selectEndDate(endDate).TaxExemptSales_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3379: Verify that "Supervisor w/ Role Assignment" users can access to the Tax Exempt Sales page and all tax exempt sales functions in the cloud.
	@Test()
	public void groupSales_US1901_TC3379() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1901_TC3379";
		String password = LoginTestData.supervisorWithRoleAssignment_SSO_Password;
		String userId = LoginTestData.supervisorWithRoleAssignment_SSO_UserId;
		String storeId = LoginTestData.supervisorWithRoleAssignmentStoreId;
		String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String taxId = String.valueOf(Base.generateNdigitRandomNumber(5));
		String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine1 = "HclTechnologies" +String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine2 = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String city = "Noida"+String.valueOf(Base.generateNdigitRandomNumber(3));
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
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.TextExemptSales_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_User_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_TaxIDNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editTaxExemptSalesRecord(orgName, taxId, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyTaxExemptSalesDetailsUpdated(orgName, taxId)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		Select select = new Select(groupSalesPage.EditTaxExemptSalesPopUp_State_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_TaxId_TB.getAttribute("value").equals(taxId)
			&& groupSalesPage.EditTaxExemptSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
			&& groupSalesPage.EditTaxExemptSalesPopUp_City_TB.getAttribute("value").equals(city)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Email_TB.getAttribute("value").equals(email)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
			&& groupSalesPage.EditTaxExemptSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
			&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditTaxExemptSalesPopUp_Submit_BT, "EditTaxExemptSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.TaxExemptSales_SalesSaved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyTextExemptSalesDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyTextExemptSalesDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyTextExemptSalesAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyTextExemptSalesAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyTextExemptSalesUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyTextExemptSalesUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		/*GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}*/
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInDescendingOrder();
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
		Select selectDate = new Select(groupSalesPage.TextExempt_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.selectStartdate(startDate).selectEndDate(endDate).TaxExemptSales_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3380: Verify that "Operator" users can access to the Tax Exempt Sales page and all tax exempt sales functions in the cloud.
	@Test()
	public void groupSales_US1901_TC3380() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1901_TC3380";
		String password = LoginTestData.operator_SSO_Password;
		String userId = LoginTestData.operator_SSO_UserId;
		String storeId = LoginTestData.operatorStoreId;
		String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String taxId = String.valueOf(Base.generateNdigitRandomNumber(5));
		String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine1 = "HclTechnologies" +String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine2 = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String city = "Noida"+String.valueOf(Base.generateNdigitRandomNumber(3));
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
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.TextExemptSales_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_User_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_TaxIDNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editTaxExemptSalesRecord(orgName, taxId, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyTaxExemptSalesDetailsUpdated(orgName, taxId)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		Select select = new Select(groupSalesPage.EditTaxExemptSalesPopUp_State_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_TaxId_TB.getAttribute("value").equals(taxId)
			&& groupSalesPage.EditTaxExemptSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
			&& groupSalesPage.EditTaxExemptSalesPopUp_City_TB.getAttribute("value").equals(city)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Email_TB.getAttribute("value").equals(email)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
			&& groupSalesPage.EditTaxExemptSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
			&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditTaxExemptSalesPopUp_Submit_BT, "EditTaxExemptSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.TaxExemptSales_SalesSaved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyTextExemptSalesDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyTextExemptSalesDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyTextExemptSalesAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyTextExemptSalesAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyTextExemptSalesUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyTextExemptSalesUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		/*GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}*/
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInDescendingOrder();
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
		Select selectDate = new Select(groupSalesPage.TextExempt_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.selectStartdate(startDate).selectEndDate(endDate).TaxExemptSales_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3381: Verify that "Org Admin" users can access to the Tax Exempt Sales page and all tax exempt sales functions in the cloud.
	@Test()
	public void groupSales_US1901_TC3381() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1901_TC3381";
		String password = LoginTestData.orgAdmin_SSO_Password;
		String userId = LoginTestData.orgAdmin_SSO_UserId;
		String storeId = LoginTestData.orgAdminStoreId;
		String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String taxId = String.valueOf(Base.generateNdigitRandomNumber(5));
		String contactName = "TestUser"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine1 = "HclTechnologies" +String.valueOf(Base.generateNdigitRandomNumber(3));
		String addressLine2 = "HclTechnologies"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		String city = "Noida"+String.valueOf(Base.generateNdigitRandomNumber(3));
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
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.TextExemptSales_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_User_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_TaxIDNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editTaxExemptSalesRecord(orgName, taxId, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyTaxExemptSalesDetailsUpdated(orgName, taxId)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  organizationName and TaxID in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		Select select = new Select(groupSalesPage.EditTaxExemptSalesPopUp_State_DD);
		//Verify that all values are updated
		if(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_TaxId_TB.getAttribute("value").equals(taxId)
			&& groupSalesPage.EditTaxExemptSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
			&& groupSalesPage.EditTaxExemptSalesPopUp_City_TB.getAttribute("value").equals(city)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Email_TB.getAttribute("value").equals(email)
			&& groupSalesPage.EditTaxExemptSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
			&& groupSalesPage.EditTaxExemptSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
			&& select.getFirstSelectedOption().getAttribute("value").equals(state)){
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Tax Exempt Sales Pop Up",
					"Fail");
			AbstractTest.takeSnapShot();
			}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditTaxExemptSalesPopUp_Submit_BT, "EditTaxExemptSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.TaxExemptSales_SalesSaved_Confirmation_MSG));
		Thread.sleep(5000);
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyTextExemptSalesDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyTextExemptSalesDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyTextExemptSalesAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyTextExemptSalesAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyTextExemptSalesUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyTextExemptSalesUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		/*GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}*/
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInDescendingOrder();
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
		Select selectDate = new Select(groupSalesPage.TextExempt_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.selectStartdate(startDate).selectEndDate(endDate).TaxExemptSales_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC3382: Verify that "Level6" users can access to the Tax Exempt Sales page and all tax exempt sales functions in the cloud.
	@Test()
	public void groupSales_US1901_TC3382() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US1901_TC3382";
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
		// Verify that columns are displaying
		if (Base.isElementDisplayed(groupSalesPage.TextExemptSales_DateAndTime_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Register_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_Amount_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_User_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_OrganizationName_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_TaxIDNumber_Label)
				& Base.isElementDisplayed(groupSalesPage.TextExemptSales_DepositStatus_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Tax ID Number 6.Organization Name 7.Deposit Status columns in Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on view button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		//Verify that level 6 user is not able to edit sale record
		if(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB.getAttribute("disabled").equals("true")
			&& groupSalesPage.EditTaxExemptSalesPopUp_ContactName_TB.getAttribute("disabled").equals("true")
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine1_TB.getAttribute("disabled").equals("true")
			&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine2_TB.getAttribute("disabled").equals("true")
			&& groupSalesPage.EditTaxExemptSalesPopUp_City_TB.getAttribute("disabled").equals("true")
			&& groupSalesPage.EditTaxExemptSalesPopUp_Email_TB.getAttribute("disabled").equals("true")
			&& groupSalesPage.EditTaxExemptSalesPopUp_Zip_TB.getAttribute("disabled").equals("true")
			&& groupSalesPage.EditTaxExemptSalesPopUp_PhoneNumber_TB.getAttribute("disabled").equals("true")
			&& groupSalesPage.EditTaxExemptSalesPopUp_State_DD.getAttribute("disabled").equals("true")
			&& !Base.isElementDisplayed(groupSalesPage.EditTaxExemptSalesPopUp_Submit_BT)){
			Reporter.reportPassResult(
					browser,
					"Level 6 user should not be able to edit Tax Exempt Sales",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"Level 6 user should not be able to edit Tax Exempt Sales",
					"Fail");
			}
		//Click on Close Button
		GenericMethods.clickOnElement(groupSalesPage.EditTaxExemptSalesPopUp_Close_BT, "EditTaxExemptSalesPopUp_Close_BT");
		Thread.sleep(2000);
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = groupSalesPage.verifyTextExemptSalesDateInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = groupSalesPage.verifyTextExemptSalesDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Register_Label, "TextExemptSales_Register_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = groupSalesPage.verifyTextExemptSalesRegisterInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending register value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = groupSalesPage.verifyTextExemptSalesAmountInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_Amount_Label, "TextExemptSales_Amount_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = groupSalesPage.verifyTextExemptSalesAmountInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending amount value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = groupSalesPage.verifyTextExemptSalesUserInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_User_Label, "TextExemptSales_User_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = groupSalesPage.verifyTextExemptSalesUserInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending user value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_OrganizationName_Label, "TextExemptSales_OrganizationName_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = groupSalesPage.verifyTextExemptSalesOrganizationInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending organization value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		/*GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_TaxIDNumber_Label, "TextExemptSales_TaxIDNumber_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = groupSalesPage.verifyTextExemptSalesTaxIdInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending tax id value in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}*/
		
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInAscendingOrder();
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DepositStatus_Label, "TextExemptSales_DepositStatus_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = groupSalesPage.verifyTextExemptSalesDepositStatusInDescendingOrder();
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
		Select selectDate = new Select(groupSalesPage.TextExempt_DateRange_DD);
		selectDate.selectByVisibleText("Custom Date Range");
		groupSalesPage.selectStartdate(startDate).selectEndDate(endDate).TaxExemptSales_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("6");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		selectedEndDate = dateFormat.format(cal2.getTime());
		cal2.add(Calendar.MONTH, -6);
		selectedStartDate = dateFormat.format(cal2.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 6 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 6 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("9");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date());
		selectedEndDate = dateFormat.format(cal3.getTime());
		cal3.add(Calendar.MONTH, -9);
		selectedStartDate = dateFormat.format(cal3.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 9 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 9 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDate.selectByValue("12");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(new Date());
		selectedEndDate = dateFormat.format(cal4.getTime());
		cal4.add(Calendar.MONTH, -12);
		selectedStartDate = dateFormat.format(cal4.getTime());
		if(groupSalesPage.TaxExemptSales_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.TaxExemptSales_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 12 months in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 12 month date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}

}
