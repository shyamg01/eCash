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

public class US427_BillableSalesDetailEntryCloud extends AbstractTest
{
	
	//TC1310 : Viewing and verifying that following Fields are auto-populated in Billable Sales Detail Entry Page in Cloud App.
	
	@Test()
	public void groupSales_US427_TC1310() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/** Variable Section : **/
		AbstractTest.tcName = "groupSales_US427_TC1310";
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
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT");
		Thread.sleep(2000);
		//Click on edit button for first validated record
		GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "groupSalesPage.BillableSales_ValidatedRecord_Edit_BT");
		wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
		System.out.println(driver.findElement(By.xpath("//div[@id='header-billable']")).getText());
		System.out.println(driver.findElement(By.xpath("//input[@label='Amount']")).getAttribute("disabled"));
		if(driver.findElement(By.xpath("//div[@id='header-billable']")).getText().contains("Register") &&
				driver.findElement(By.xpath("//input[@label='Amount']")).getAttribute("disabled").equalsIgnoreCase("true"))
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
	
	
	//TC1311 : 	Viewing and verifying that following Fields are manual entry fields  in Billable Sales Detail Entry Page on Cloud App.
	
		@Test()
		public void groupSales_US427_TC1311() throws InterruptedException,
				RowsExceededException, BiffException, WriteException, IOException {
			/** Variable Section : **/
			AbstractTest.tcName = "groupSales_US427_TC1311";
			String password = LoginTestData.level1_SSO_Password;
			String userId = LoginTestData.level1_SSO_UserId;
			String storeId = LoginTestData.level1StoreId;
			String invalidOrgName="Test12345678912345678912345678912345678912345678912";
			String validOrgName="Test1234567891234567891234567891234567891234567891";
			String invalidContactNumber="Testcontact";
			String invalidEmail="Test@2345678912345678912345678912345678912345678912";
			String validEmail="Test@234567891234567891234567891234567891234567891";
			String invalidAddressLine1="Test1234567891234567891234567891234567891234567891Test12345678912345678912345678912345678912345678912";
			String validAddressLine1="Test1234567891234567891234567891234567891234567891Test1234567891234567891234567891234567891234567891";
			String invalidAddressLine2="Test1234567891234567891234567891234567891234567891Test12345678912345678912345678912345678912345678912";
			String validAddressLine2="Test1234567891234567891234567891234567891234567891Test1234567891234567891234567891234567891234567891";
			String invalidCity="Test12345678912345678912345678912345678912345678912";
			String validCity="Test1234567891234567891234567891234567891234567891";
			String invalidZip="123456";
			String validZip="12345";
			String invalidInvoicePONumber="Test123456789123456789123456781";
			String validInvoicePONumber="Test12345678912345678912345678";
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			GroupSalesPage groupSalesPage = PageFactory.initElements(driver, GroupSalesPage.class);
			// Navigate to Group Sales Page
			groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
					.goToGroupSalesPage();
			Thread.sleep(2000);
			//go to Billable Sales Page
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT");
			Thread.sleep(2000);
			//Click on edit button for first validated record
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_ValidatedRecord_Edit_BT, "groupSalesPage.BillableSales_ValidatedRecord_Edit_BT");
			wait.until(ExpectedConditions.visibilityOf(groupSalesPage.EditBillableSales_PopUp_Header));
			Thread.sleep(2000);
			//Clear all the element
			GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_OrganizationName_TB, "groupSalesPage.BillableSalesPopUp_OrganizationName_TB");
			GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_Email_TB, "groupSalesPage.BillableSalesPopUp_Email_TB");
			GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_AddressLine1_TB, "groupSalesPage.BillableSalesPopUp_AddressLine1_TB");
			GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_AddressLine2_TB, "groupSalesPage.BillableSalesPopUp_AddressLine2_TB");
			GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_City_TB, "groupSalesPage.BillableSalesPopUp_City_TB");
			GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_Zip_TB, "groupSalesPage.BillableSalesPopUp_Zip_TB");
			GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_InvoicePONO_TB, "groupSalesPage.BillableSalesPopUp_InvoicePONO_TB");
			GenericMethods.clearValueOfElement(groupSalesPage.BillableSalesPopUp_PhoneNumber_TB, "groupSalesPage.BillableSalesPopUp_PhoneNumber_TB");
			//Enter value in the element
			GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_OrganizationName_TB, "groupSalesPage.BillableSalesPopUp_OrganizationName_TB", invalidOrgName);
			GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_Email_TB, "groupSalesPage.BillableSalesPopUp_Email_TB", invalidEmail);
			GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_AddressLine1_TB, "groupSalesPage.BillableSalesPopUp_AddressLine1_TB", invalidAddressLine1);
			GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_AddressLine2_TB, "groupSalesPage.BillableSalesPopUp_AddressLine2_TB", invalidAddressLine2);
			GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_City_TB, "groupSalesPage.BillableSalesPopUp_City_TB", invalidCity);
			GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_Zip_TB, "groupSalesPage.BillableSalesPopUp_Zip_TB", invalidZip);
			GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_InvoicePONO_TB, "groupSalesPage.BillableSalesPopUp_InvoicePONO_TB", invalidInvoicePONumber);
			GenericMethods.enterValueInElement(groupSalesPage.BillableSalesPopUp_PhoneNumber_TB, "groupSalesPage.BillableSalesPopUp_PhoneNumber_TB", invalidContactNumber);
			Thread.sleep(3000);
			System.out.println(groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value"));
			if(groupSalesPage.BillableSalesPopUp_OrganizationName_TB.getAttribute("value").equalsIgnoreCase(validOrgName) &&
					groupSalesPage.BillableSalesPopUp_Email_TB.getAttribute("value").equalsIgnoreCase(validEmail) &&
					groupSalesPage.BillableSalesPopUp_AddressLine1_TB.getAttribute("value").equalsIgnoreCase(validAddressLine1) &&
					groupSalesPage.BillableSalesPopUp_AddressLine2_TB.getAttribute("value").equalsIgnoreCase(validAddressLine2) &&
					groupSalesPage.BillableSalesPopUp_City_TB.getAttribute("value").equalsIgnoreCase(validCity) &&
					groupSalesPage.BillableSalesPopUp_Zip_TB.getAttribute("value").equalsIgnoreCase(validZip) &&
					groupSalesPage.BillableSalesPopUp_InvoicePONO_TB.getAttribute("value").equalsIgnoreCase(validInvoicePONumber) &&
					groupSalesPage.BillableSalesPopUp_PhoneNumber_TB.getAttribute("value").equalsIgnoreCase("(___)___-____"))
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
		
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
