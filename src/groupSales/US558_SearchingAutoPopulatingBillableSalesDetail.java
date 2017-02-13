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

public class US558_SearchingAutoPopulatingBillableSalesDetail extends AbstractTest{
	
	//TC1358:Verify that saved details are auto-populated in future sales to minimize the key strokes to the end user on Cloud App on Billable sales detail page(Form).
	@Test()
	public void groupSales_US558_TC1358() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US558_TC1358";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		String orgName = "TestAutomation"+ String.valueOf(Base.generateNdigitRandomNumber(3));
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
		String existingOrgName = groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value");
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_OrganizationName_TB, "BillableSalesPopUp_OrganizationName_TB");
		GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_OrganizationName_TB, "BillableSalesPopUp_OrganizationName_TB", orgName);
		GenericMethods.clickOnElement(groupSalesPage.BillableSalesPopUp_Submit_BT, "BillableSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.BillableSales_SalesSaved_Confirmation_MSG));
		Thread.sleep(5000);
		//Click on edit button for first validated record
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", groupSalesPage.BillableSales_ValidatedRecord_Edit_BT);
		//GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "Billable sales validated Record Edit Button");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_OrganizationName_TB, "BillableSalesPopUp_OrganizationName_TB");
		GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_OrganizationName_TB, "BillableSalesPopUp_OrganizationName_TB", orgName.substring(0, 1));
		if (groupSalesPage.EditBillableSalesPopUp_Organization_Suggestions_List.size() > 0) {
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
		groupSalesPage.selectBillableSaleOrganizationNameFromAutoCompleteSuggestions(existingOrgName);
		Thread.sleep(2000);
		if (groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equals(existingOrgName)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view selected value in edit Tax Exempt Sales Pop Up",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view selected value in edit Tax Exempt Sales Pop Up",
					"Fail");
		}
		GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_OrganizationName_TB, "BillableSalesPopUp_OrganizationName_TB");
		GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_OrganizationName_TB, "BillableSalesPopUp_OrganizationName_TB", orgName.substring(0, 1));
		groupSalesPage.selectBillableSaleOrganizationNameFromAutoCompleteSuggestions(orgName);
		Thread.sleep(2000);
		if (groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equals(orgName)) {
			Reporter.reportPassResult(
					browser,
					"user should be able to view selected value in edit Tax Exempt Sales Pop Up",
					"Pass");

		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to view selected value in edit Tax Exempt Sales Pop Up",
					"Fail");
		}
		//Click on Submit Button
		GenericMethods.clickOnElement(groupSalesPage.BillableSalesPopUp_Submit_BT, "BillableSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.BillableSales_SalesSaved_Confirmation_MSG));
	}

}
