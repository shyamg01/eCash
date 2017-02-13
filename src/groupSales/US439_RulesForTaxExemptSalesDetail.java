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

public class US439_RulesForTaxExemptSalesDetail  extends AbstractTest{
	
	//TC3475: Verify Tax exempt Sales Details entered are Editable only  when deposits are finalized on Cloud App.
	@Test()
	public void groupSales_US439_TC3475() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US439_TC3475";
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
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();",groupSalesPage.TextExemptSales_BaggedRecord_View_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		if(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB.getAttribute("disabled").equals("true")
				&& groupSalesPage.EditTaxExemptSalesPopUp_TaxId_TB.getAttribute("disabled").equals("true")
				&& groupSalesPage.EditTaxExemptSalesPopUp_ContactName_TB.getAttribute("disabled").equals("true")
				&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine1_TB.getAttribute("disabled").equals("true")
				&& groupSalesPage.EditTaxExemptSalesPopUp_AddressLine2_TB.getAttribute("disabled").equals("true")
				&& groupSalesPage.EditTaxExemptSalesPopUp_City_TB.getAttribute("disabled").equals("true")
				&& groupSalesPage.EditTaxExemptSalesPopUp_Email_TB.getAttribute("disabled").equals("true")
				&& groupSalesPage.EditTaxExemptSalesPopUp_Zip_TB.getAttribute("disabled").equals("true")
				&& groupSalesPage.EditTaxExemptSalesPopUp_PhoneNumber_TB.getAttribute("disabled").equals("true")
				&& groupSalesPage.EditTaxExemptSalesPopUp_State_DD.getAttribute("disabled").equals("true")){
				Reporter.reportPassResult(
						browser,
						"All Tax exempt detail fields should be disabled before finalize the deposit",
						"Pass");
				
			} else {
				Reporter.reportTestFailure(
						browser,
						"All Tax exempt detail fields should be disabled before finalize the deposit",
						"Fail");
				AbstractTest.takeSnapShot();
				}
		GenericMethods.clickOnElement(groupSalesPage.EditTaxExemptSalesPopUp_Close_BT, "EditTaxExemptSalesPopUp_Close_BT");
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		//Edit all editable values in pop up and submit
		Thread.sleep(3000);
		Select select = new Select(groupSalesPage.EditTaxExemptSalesPopUp_State_DD);
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

}
