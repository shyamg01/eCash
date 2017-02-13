package groupSales;

import java.io.IOException;
import java.text.ParseException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.Base;
import common.GenericMethods;
import common.LoginTestData;
import common.Reporter;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.GroupSalesPage;
import eCashPageClasses.HomePage;

public class US559_Searching_AutoPopulatingTaxExemptSalesDetail  extends AbstractTest{
	
	//TC3482: Verify that Tax exempt Sales details are able to get saved for future reference on Cloud App.
	@Test()
	public void groupSales_US559_TC3482() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US559_TC3482";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		//Edit all editable values in pop up and submit
		String existingOrgName = groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB.getAttribute("value");
		GenericMethods.clearValueOfElement(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB, "EditTaxExemptSalesPopUp_OrganizationName_TB");
		GenericMethods.enterValueInElement(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB, "EditTaxExemptSalesPopUp_OrganizationName_TB", orgName);
		GenericMethods.clickOnElement(groupSalesPage.EditTaxExemptSalesPopUp_Submit_BT, "EditTaxExemptSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.TaxExemptSales_SalesSaved_Confirmation_MSG));
		//Click on edit button for first validated record
		//GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT, "Validated Record Edit Button");
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", groupSalesPage.TextExemptSales_ValidatedRecord_Edit_BT);
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditTaxExemptSales_PopUp_Header));
		GenericMethods.clearValueOfElement(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB, "EditTaxExemptSalesPopUp_OrganizationName_TB");
		GenericMethods.enterValueInElement(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB, "EditTaxExemptSalesPopUp_OrganizationName_TB", orgName.substring(0, 1));
		if (groupSalesPage.EditTaxExemptSalesPopUp_Organization_Suggestions_List
				.size() > 0) {
			Reporter.reportPassResult(
					browser,
					"User should be able to view autocomplete suggestions on searching matching values",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view autocomplete suggestions on searching matching values",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		System.out.println("existingOrgName "+existingOrgName);
		groupSalesPage.selectTaxExemptSaleOrganizationNameFromAutoCompleteSuggestions(existingOrgName);
		Thread.sleep(2000);
		if (groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB
				.getAttribute("value").equals(existingOrgName)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view selected value in Organization text box",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view selected value in Organization text box",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clearValueOfElement(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB, "EditTaxExemptSalesPopUp_OrganizationName_TB");
		GenericMethods.enterValueInElement(groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB, "EditTaxExemptSalesPopUp_OrganizationName_TB", orgName.substring(0, 1));
		groupSalesPage.selectTaxExemptSaleOrganizationNameFromAutoCompleteSuggestions(orgName);
		Thread.sleep(2000);
		if (groupSalesPage.EditTaxExemptSalesPopUp_OrganizationName_TB
				.getAttribute("value").equals(orgName)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view selected value in Organization text box",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view selected value in Organization text box",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.EditTaxExemptSalesPopUp_Submit_BT, "EditTaxExemptSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.TaxExemptSales_SalesSaved_Confirmation_MSG));
	}

}
