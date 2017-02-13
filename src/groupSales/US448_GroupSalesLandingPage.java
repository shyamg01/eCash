package groupSales;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.GenericMethods;
import common.LoginTestData;
import common.Reporter;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.GroupSalesPage;
import eCashPageClasses.HomePage;

public class US448_GroupSalesLandingPage extends AbstractTest
{
	
	//TC1298 : 	Verify that as a shift manager is able to navigate into Group Sale Landing Page from application's  main menu.
	@Test()
	public void groupSales_US448_TC1298() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/** Variable Section : **/
		AbstractTest.tcName = "groupSales_US448_TC1298";
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
		if(GenericMethods.isElementDisplayed(groupSalesPage.GroupSales_Label, "groupSalesPage.GroupSales_Label") &&
				GenericMethods.isElementDisplayed(groupSalesPage.TaxExemptSales_BT, "groupSalesPage.TaxExemptSales_BT") &&
				GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT") &&
				GenericMethods.isElementDisplayed(groupSalesPage.OtherReceipts_BT, "groupSalesPage.OtherReceipts_BT"))
		{
			Reporter.reportPassResult(
					browser,
					"All the fields should display as expected",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,
					"All the fields should display as expected",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
	}
	
	
	//TC1300:Identify that user is in "Group Sales" application within the application.
	@Test()
	public void groupSales_US448_TC1300() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/** Variable Section : **/
		AbstractTest.tcName = "groupSales_US448_TC1300";
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
		if(GenericMethods.isElementDisplayed(groupSalesPage.GroupSales_Label, "groupSalesPage.GroupSales_Label") &&
				GenericMethods.isElementDisplayed(groupSalesPage.TaxExemptSales_BT, "groupSalesPage.TaxExemptSales_BT") &&
				GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT") &&
				GenericMethods.isElementDisplayed(groupSalesPage.OtherReceipts_BT, "groupSalesPage.OtherReceipts_BT"))
		{
			Reporter.reportPassResult(
					browser,
					"All the fields should display as expected",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,
					"All the fields should display as expected",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
	}
	
	
	
	//	TC3324 : Identify that as a shift manager can identify the "Store Number" the user currently operation under the application.
		@Test()
		public void groupSales_US448_TC3324() throws InterruptedException,
				RowsExceededException, BiffException, WriteException, IOException {
			/** Variable Section : **/
			AbstractTest.tcName = "groupSales_US448_TC3324";
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
			if(driver.findElement(By.xpath("//div[@id='location-settings']/a/span[1]")).getText().equalsIgnoreCase(storeId))
			{
				Reporter.reportPassResult(
						browser,
						"All the fields should display as expected",
						"Pass");
			}
			else
			{
				Reporter.reportTestFailure(
						browser,
						"All the fields should display as expected",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
		}
		
	/*	TC3327 : Verify that as a shift manager able to navigate into different sub-functions of group sale.
		Billable Sales
		Tax Exempt Sales
		Other Receipts
	*/
	
	
		@Test()
		public void groupSales_US448_TC3327() throws InterruptedException,
				RowsExceededException, BiffException, WriteException, IOException {
			/** Variable Section : **/
			AbstractTest.tcName = "groupSales_US448_TC3327";
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
			if(driver.findElement(By.xpath("//label[text()='Tax ID Number']")).isDisplayed())
			{
				Reporter.reportPassResult(
						browser,
						"Tax Exempt Sales Page should be opened successfully",
						"Pass");
			}
			else
			{
				Reporter.reportTestFailure(
						browser,
						"Tax Exempt Sales Page should be opened successfully",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			//Click on Billable Sales
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT");
			Thread.sleep(2000);
			if(driver.findElement(By.xpath("//label[text()='Invoice/PO #']")).isDisplayed())
			{
				Reporter.reportPassResult(
						browser,
						"Billable Sales Page should be opened successfully",
						"Pass");
			}
			else
			{
				Reporter.reportTestFailure(
						browser,
						"Billable Sales Page should be opened successfully",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			//Click on Other Receipt Sales
			GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "groupSalesPage.OtherReceipts_BT");
			Thread.sleep(2000);
			if(driver.findElement(By.xpath("//label[text()='Check Number']")).isDisplayed())
			{
				Reporter.reportPassResult(
						browser,
						"Other Receipts Page should be opened successfully",
						"Pass");
			}
			else
			{
				Reporter.reportTestFailure(
						browser,
						"Other Receipts Page should be opened successfully",
						"Fail");
				AbstractTest.takeSnapShot();
			}
		}
	

}
