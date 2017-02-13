package groupSales;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.GenericMethods;
import common.LoginTestData;
import common.Reporter;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.GroupSalesPage;
import eCashPageClasses.HomePage;

public class US454_OtherReceiptsDetailEntryCloud extends AbstractTest
{
	
	//TC1509 : Verify the manual entry fields in Other Receipt  Detail Page on cloud App.
	
	@Test()
	public void groupSales_US454_TC1509() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/** Variable Section : **/
		AbstractTest.tcName = "groupSales_US454_TC1509";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		String validOrgName="Test1234567891234567891234567891234567891234567891";
		String validCheckNumber="123456789123";
		String validdecription="Test12345678912345678912345678 thh";
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		GroupSalesPage groupSalesPage = PageFactory.initElements(driver, GroupSalesPage.class);
		// Navigate to Group Sales Page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		Thread.sleep(2000);
		//go to Other Receipt page
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "OtherReceipts_BT");
		Thread.sleep(2000);
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.OtherReceiptss_ValidatedRecord_Edit_BT, "OtherReceiptss_ValidatedRecord_Edit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		Thread.sleep(2000);
		//Clear all the element
		GenericMethods.clearValueOfElement(groupSalesPage.OtherReceiptsPopUp_OrganizationName_TB, "OtherReceiptsPopUp_OrganizationName_TB");
		GenericMethods.clearValueOfElement(groupSalesPage.OtherReceipts_CheckNumber_TB, "OtherReceipts_CheckNumber_TB");
		GenericMethods.clearValueOfElement(groupSalesPage.OtherReceipts_Description_TB, "OtherReceipts_Description_TB");
		//Enter value in the element
		GenericMethods.enterValueInElement(groupSalesPage.OtherReceiptsPopUp_OrganizationName_TB, "OtherReceiptsPopUp_OrganizationName_TB", validOrgName);
		GenericMethods.enterValueInElement(groupSalesPage.OtherReceipts_CheckNumber_TB, "OtherReceipts_CheckNumber_TB", validCheckNumber);
		GenericMethods.enterValueInElement(groupSalesPage.OtherReceipts_Description_TB, "OtherReceipts_Description_TB", validdecription);
		Thread.sleep(3000);
		if(groupSalesPage.OtherReceiptsPopUp_OrganizationName_TB.getAttribute("value").equalsIgnoreCase(validOrgName) &&
				groupSalesPage.OtherReceipts_CheckNumber_TB.getAttribute("value").equalsIgnoreCase(validCheckNumber) &&
				groupSalesPage.OtherReceipts_Description_TB.getAttribute("value").equalsIgnoreCase(validdecription))
		{
			Reporter.reportPassResult(
					browser,
					"User should be able to edit the fields information with the input limits",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,
					"User should be able to edit the fields information with the input limits",
					"Fail");
			AbstractTest.takeSnapShot();
		}

		
	}
	
	
	//TC1516 : Verify the auto-populated fields in Other Receipts Detail Page
	

	@Test()
	public void groupSales_US454_TC1516() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/** Variable Section : **/
		AbstractTest.tcName = "groupSales_US454_TC1516";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		GroupSalesPage groupSalesPage = PageFactory.initElements(driver, GroupSalesPage.class);
		// Navigate to Group Sales Page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		Thread.sleep(2000);
		//go to Billable Sales Page
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "OtherReceipts_BT");
		Thread.sleep(2000);
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.OtherReceiptss_ValidatedRecord_Edit_BT, "OtherReceiptss_ValidatedRecord_Edit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.OtherReceipts_PopUp_Header));
		System.out.println(driver.findElement(By.xpath("//div[@id='header-other']")).getText());
		System.out.println(groupSalesPage.OtherReceipts_Amount_TB.getAttribute("disabled"));
		if(driver.findElement(By.xpath("//div[@id='header-other']")).getText().contains("Register") &&
				groupSalesPage.OtherReceipts_Amount_TB.getAttribute("disabled").equalsIgnoreCase("true"))
		{
			Reporter.reportPassResult(
					browser,
					"Fields should display in non editable mode",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,
					"Fields should display in non editable mode",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
