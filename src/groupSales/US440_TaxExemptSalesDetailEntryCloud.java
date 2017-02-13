package groupSales;

import java.io.IOException;
import java.text.ParseException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import common.Base;
import common.GenericMethods;
import common.LoginTestData;
import common.Reporter;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.GroupSalesPage;
import eCashPageClasses.HomePage;

public class US440_TaxExemptSalesDetailEntryCloud extends AbstractTest{
	
	//TC3480: Viewing and verifying that following Fields are manual entry fields  in tax exempt Sales Detail Entry Page on Cloud App.
	@Test()
	public void groupSales_US440_TC3480() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US440_TC3480";
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
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		//Edit all editable values in pop up and submit
		groupSalesPage.editTaxExemptSalesRecord(orgName, taxId, contactName, addressLine1, addressLine2, city,state, zip, email, phoneNumber);
		//Verify that new Organozation name and Tax id is displayed in Tax exempt sales landing page
		if (groupSalesPage.verifyTaxExemptSalesDetailsUpdated(orgName, taxId)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to edit and save values for manual entry fields in Tax Exempt Sales Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to edit and save values for manual entry fields in Tax Exempt Sales Page",
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
	}
	
	//TC3481: Viewing and verifying that following Fields are manual entry fields  in tax exempt Sales Detail Entry Page on Cloud App.
	@Test()
	public void groupSales_US440_TC3481() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US440_TC3481";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		//Click on edit button for first validated record
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();",groupSalesPage.TextExemptSales_BaggedRecord_View_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		String headerText = groupSalesPage.TaxExemptSalesPopUp_HeaderText_Value.getText();
		System.out.println("Header Text "+ headerText);
		String register = headerText.split("\\|")[0].trim();
		String date = headerText.split("\\|")[1].trim();
		String time = headerText.split("\\|")[2].trim();
		String user = headerText.split("\\|")[3].trim();
		if (date.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view auto pupulated date in view Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view auto pupulated date in view Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if (time.matches("([0-2]{1,}[0-9]{1,})(:)([0-5]{1,}[0-9]{1,})")) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view auto pupulated time in view Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view auto pupulated time in view Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(!register.isEmpty() & !user.isEmpty() & !groupSalesPage.EditTaxExemptSalesPopUp_Amount_TB.getAttribute("value").isEmpty()){
			Reporter.reportPassResult(
					browser,
					"user should be able to view auto pupulated Amount,Register #,User in view Tax Exempt Sales Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view auto pupulated Amount,Register #,User in view Tax Exempt Sales Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		
		
		
	}
}
