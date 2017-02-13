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

public class US429_CustomListViewBillableSales extends AbstractTest
{
	
	
	//TC1305 : 	Verifying user is able to view/sort the Billable Sales details by user selectable date range on Billable Sales landing page on Cloud App.
	
	
		@Test()
		public void groupSales_US429_TC1305() throws InterruptedException,
				RowsExceededException, BiffException, WriteException, IOException, ParseException {
			/** Variable Section : **/
			GroupSalesPage groupSalesPage;
			AbstractTest.tcName="groupSales_US429_TC1305";
			String password = LoginTestData.level1_SSO_Password;
			String userId = LoginTestData.level1_SSO_UserId;
			String storeId = LoginTestData.level1StoreId;
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
		
		
	
// TC1307 : Verifying user is able to Filter Billable sales entries by organization field on Billable sales landing page on Cloud  App.
	
	
		
		
		@Test()
		public void groupSales_US429_TC1307() throws InterruptedException,
		RowsExceededException, BiffException, WriteException, IOException, ParseException {
			/** Variable Section : **/
			GroupSalesPage groupSalesPage;
			AbstractTest.tcName="groupSales_US429_TC1307";
			String password = LoginTestData.level1_SSO_Password;
			String userId = LoginTestData.level1_SSO_UserId;
			String storeId = LoginTestData.level1StoreId;
	
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to Group sales page
			groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
					.goToGroupSalesPage();
			//go to Billable Sales Page
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_BT, "groupSalesPage.BillableSales_BT");
			Thread.sleep(2000);
			//Select the Organization name and verify that only the records of specified organization's are displaying
			Select select = new Select(groupSalesPage.BillableSales_Organization_DD);
			select.selectByIndex(1);
			WebElement element=select.getOptions().get(1);
			String expOrgName=element.getText();
			//Click on Show Result button
			GenericMethods.clickOnElement(groupSalesPage.BillableSales_ShowResults_BT, "groupSalesPage.BillableSales_ShowResults_BT");
			Thread.sleep(2000);
			//Find the list of corresponding  Organization name in the table
			List <WebElement> element_01=driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[6]/span"));
			for(int i=0;i<element_01.size();i++)
			{
				String actOrgName=null;
				System.out.println("element_01.get(i).getText() "+element_01.get(i).getText());
				actOrgName=element_01.get(i).getText();
				if(actOrgName.equalsIgnoreCase(expOrgName) && i==element_01.size()-1)
				{
					Reporter.reportPassResult(
							browser,
							"Only the records of the corresponding organization name should display",
							"Pass");
					break;
				}
				else if(actOrgName.equalsIgnoreCase(expOrgName))
				{
					continue;
				}
				else
				{
					Reporter.reportTestFailure(
							browser,
							"Only the records of the corresponding organization name should display",
							"Fail");
					AbstractTest.takeSnapShot();
					break;
				}
			}

		}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
