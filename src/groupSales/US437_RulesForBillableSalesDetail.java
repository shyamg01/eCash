package groupSales;

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
import common.LoginTestData;
import common.Reporter;

import eCashPageClasses.AbstractPage;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.GroupSalesPage;
import eCashPageClasses.HomePage;

public class US437_RulesForBillableSalesDetail extends AbstractTest{
	
	//TC1328:Verify that Store Level User is able to enter values in Manual entry  fields on Billable Sales Detail screen in Cloud App.
	@Test()
	public void groupSales_US437_TC1328() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US437_TC1328";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		char[] CHARSET_AZ_09 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		String invalidOrgName = Base.randomString(CHARSET_AZ_09, 60);
		String validOrgName = Base.randomString(CHARSET_AZ_09, 50);
		String invalidPhoneNumber = Base.randomString(CHARSET_AZ_09, 10);
		String validPhoneNumber = String.valueOf(Base.generateNdigitRandomNumber(10));
		String invalidEmail = Base.randomString(CHARSET_AZ_09, 60)+"@mcd.com";
		String validEmail = Base.randomString(CHARSET_AZ_09, 42)+"@mcd.com";
		//String invalidContactName = Base.randomString(CHARSET_AZ_09, 60).concat(Base.randomString(CHARSET_AZ_09, 60));
		//String validContactName = Base.randomString(CHARSET_AZ_09, 50).concat(Base.randomString(CHARSET_AZ_09, 50));
		String invalidCity = Base.randomString(CHARSET_AZ_09, 60);
		String validCity = Base.randomString(CHARSET_AZ_09, 50);
		String invalidZipcode = String.valueOf(Base.generateNdigitRandomNumber(10));
		String validZipcode = String.valueOf(Base.generateNdigitRandomNumber(5));
		String invalidInvoiceNumber = Base.randomString(CHARSET_AZ_09, 50);
		String validInvoiceNumber = Base.randomString(CHARSET_AZ_09, 30);
		
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		//go to Billable Sales Page
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT");
		Thread.sleep(2000);
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "groupSalesPage.BillableSales_ValidatedRecord_Edit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
		//Edit all editable values in pop up and submit
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_OrganizationName_TB, "BillableSalesPopUp_OrganizationName_TB");
		GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_OrganizationName_TB, "BillableSalesPopUp_OrganizationName_TB", validOrgName);
		if (groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").length()==50
				& groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equals(validOrgName)) {
			Reporter.reportPassResult(
					browser,
					"User should be able to enter upto 50 characters in organization name for billable sales",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to enter upto 50 characters in organization name for billable sales",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_OrganizationName_TB, "BillableSalesPopUp_OrganizationName_TB");
		GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_OrganizationName_TB, "BillableSalesPopUp_OrganizationName_TB", invalidOrgName);
		if (groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").length()==50
				& groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equals(invalidOrgName.substring(0,50))) {
			Reporter.reportPassResult(
					browser,
					"User should not be able to enter more than 50 characters in organization name for billable sales",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should not be able to enter more than 50 characters in organization name for billable sales",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Edit all editable values in pop up and submit
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_InvoicePONO_TB, "BillableSalesPopUp_InvoicePONO_TB");
		GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_InvoicePONO_TB, "BillableSalesPopUp_InvoicePONO_TB", validInvoiceNumber);
		if (groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").length() == 30
				& groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").equals(validInvoiceNumber)) {
			Reporter.reportPassResult(
					browser,
					"User should be able to enter upto 30 characters in invoice PONO field for billable sales",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to enter upto 30 characters in invoice PONO field for billable sales",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_InvoicePONO_TB,"BillableSalesPopUp_InvoicePONO_TB");
		GenericMethods.enterValueInElement(	groupSalesPage.BillableSalesPopUp_InvoicePONO_TB,"BillableSalesPopUp_InvoicePONO_TB", invalidInvoiceNumber);
		if (groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").length() == 30
				& groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").equals(invalidInvoiceNumber.substring(0, 30))) {
			Reporter.reportPassResult(
					browser,
					"User should be able to enter upto 30 characters in invoice PONO field for billable sales",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to enter upto 30 characters in invoice PONO field for billable sales",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Edit all editable values in pop up and submit
		/*GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_ContactName_TB, "BillableSalesPopUp_ContactName_TB");
		GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_ContactName_TB, "BillableSalesPopUp_ContactName_TB", validContactName);
		System.out.println("valid contact name "+groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value"));
		if (groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value").length() == 100
				& groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value").equals(validContactName)) {
			Reporter.reportPassResult(
					browser,
					"User should be able to enter upto 100 characters in Contact name field for billable sales",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to enter upto 100 characters in Contact name field for billable sales",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_ContactName_TB,"BillableSalesPopUp_ContactName_TB");
		GenericMethods.enterValueInElement(	groupSalesPage.BillableSalesPopUp_ContactName_TB,"BillableSalesPopUp_ContactName_TB", invalidContactName);
		System.out.println("Invalid contact name "+groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value"));
		if (groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value").length() == 100
				& groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value").equals(invalidContactName.substring(0, 100))) {
			Reporter.reportPassResult(
					browser,
					"User should not be able to enter more than 100 characters in Contact name field for billable sales",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should not be able to enter more than 100 characters in Contact name field for billable sales",
					"Fail");
			AbstractTest.takeSnapShot();
		}*/
		// Edit all editable values in pop up and submit
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_City_TB,"BillableSalesPopUp_City_TB");
		GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_City_TB,"BillableSalesPopUp_City_TB", validCity);
		if (groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").length() == 50
				& groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").equals(validCity)) {
			Reporter.reportPassResult(
					browser,
					"User should be able to enter upto 50 characters in city field for billable sales",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to enter upto 50 characters in city field for billable sales",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_City_TB,"BillableSalesPopUp_City_TB");
		GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_City_TB,"BillableSalesPopUp_City_TB", invalidCity);
		if (groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").length() == 50
				& groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").equals(invalidCity.substring(0, 50))) {
			Reporter.reportPassResult(
					browser,
					"User should not be able to enter more than 50 characters in city field for billable sales",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should not be able to enter more than 50 characters in city field for billable sales",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		// Edit all editable values in pop up and submit
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_Email_TB,"BillableSalesPopUp_Email_TB");
		GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_Email_TB,"BillableSalesPopUp_Email_TB", validEmail);
		if (groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").length() == 50
				& groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").equals(validEmail)) {
			Reporter.reportPassResult(
					browser,
					"User should be able to enter upto 50 characters in email field for billable sales",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to enter upto 50 characters in email field for billable sales",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_Email_TB,"BillableSalesPopUp_Email_TB");
		GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_Email_TB,"BillableSalesPopUp_Email_TB", invalidEmail);
		if (groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").length() == 50
				& groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").equals(invalidEmail.substring(0, 50))) {
			Reporter.reportPassResult(
					browser,
					"User should not be able to enter more than 50 characters in email field for billable sales",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should not be able to enter  more than 50 characters in email field for billable sales",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		// Edit all editable values in pop up and submit
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_Zip_TB,"BillableSalesPopUp_Zip_TB");
		GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_Zip_TB,"BillableSalesPopUp_Zip_TB", validZipcode);
		if (groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").length() == 5
				& groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").equals(validZipcode)) {
			Reporter.reportPassResult(
					browser,
					"User should be able to enter upto 5 characters in zip code field for billable sales",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to enter upto 5 characters in zip code field for billable sales",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_Zip_TB,"BillableSalesPopUp_Zip_TB");
		GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_Zip_TB,"BillableSalesPopUp_Zip_TB", invalidZipcode);
		if (groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").length() == 5
				& groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").equals(invalidZipcode.substring(0, 5))) {
			Reporter.reportPassResult(
					browser,
					"User should not be able to enter more than 5 characters in zip code field for billable sales",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should not be able to enter more than 5 characters in zip code field for billable sales",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		// Edit all editable values in pop up and submit
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_PhoneNumber_TB,"BillableSalesPopUp_PhoneNumber_TB");
		GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_PhoneNumber_TB,"BillableSalesPopUp_PhoneNumber_TB", validPhoneNumber);
		System.out.println("valid phone "+groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value"));
		if (groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").length() == 10
				& groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(validPhoneNumber)) {
			Reporter.reportPassResult(
					browser,
					"User should be able to enter only numeric value in phone number code field for billable sales",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to enter only numeric value in phone number code field for billable sales",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Thread.sleep(1500);
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_PhoneNumber_TB,"BillableSalesPopUp_PhoneNumber_TB");
		Thread.sleep(1500);
		GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_PhoneNumber_TB,"BillableSalesPopUp_PhoneNumber_TB", invalidPhoneNumber);
		System.out.println("invalid phone "+groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value"));
		if (groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").length() == 0
				& groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals("")) {
			Reporter.reportPassResult(
					browser,
					"User should not be able to enter alphanumeric value in phone number code field for billable sales",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should not be able to enter alphanumeric value in phone number code field for billable sales",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}

	//TC1359 : Verify Billable Sales Details entered are Editable only  when deposits are finalized on Cloud App.
	@Test()
	public void groupSales_US437_TC1359() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException,ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName = "groupSales_US437_TC1359";
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
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToGroupSalesPage();
		// go to Billable Sales Page
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT,"groupSalesPage.BillableSales_BT");
		Thread.sleep(2000);
		// Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT,"groupSalesPage.BillableSales_ValidatedRecord_Edit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
		// Edit all editable values in pop up and submit
		Thread.sleep(3000);
		groupSalesPage.editBillableSalesRecord(orgName, invoicePONumber,contactName, addressLine1, addressLine2, city, state, zip, email, phoneNumber);
		// Verify that new Organization name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyBillableSalesDetailsUpdated(orgName,invoicePONumber)) {
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
		AbstractPage.executor.executeScript("arguments[0].click();", groupSalesPage.BillableSales_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
		Select select = new Select(groupSalesPage.BillableSalesPopUp_State_DD);
		// Verify that all values are updated
		if (groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)
				&& groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").equals(invoicePONumber)
				&& groupSalesPage.BillableSalesPopUp_ContactName_TB.getAttribute("value").equals(contactName)
				&& groupSalesPage.EditBillableSalesPopUp_AddressLine1_TB.getAttribute("value").equals(addressLine1)
				&& groupSalesPage.EditBillableSalesPopUp_AddressLine2_TB.getAttribute("value").equals(addressLine2)
				&& groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").equals(city)
				&& groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").equals(email)
				&& groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").equals(zip)
				&& groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").replaceAll("[^a-zA-Z0-9]", "").equals(phoneNumber)
				&& select.getFirstSelectedOption().getAttribute("value").equals(state)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view updated  values in edit Billable Sales Pop Up",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view updated  values in edit Billable Sales Pop Up",
					"Fail");
		}
	}
	
	

}
