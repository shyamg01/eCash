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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import common.GenericMethods;
import common.GlobalVariable;
import common.LoginTestData;
import common.Reporter;

import eCashPageClasses.AbstractTest;
import eCashPageClasses.GroupSalesPage;
import eCashPageClasses.HomePage;

public class US442_CustomListViewTaxExemptSales extends AbstractTest{
	
	//TC1492: Verifying user is able to view/sort by day,week,month or user selectable date range on cloud App.
	@Test()
	public void groupSales_US442_TC1492() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US442_TC1492";
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
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
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
	
	//TC1494: Verify that store level user is able to filter Tax Exempt sales entries by organization field on Tax Exempt sales landing page.
	@Test()
	public void groupSales_US442_TC1494() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US442_TC1494";
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
		GenericMethods.clickOnElement(groupSalesPage.TextExemptSales_DateAndTime_Label, "TextExemptSales_DateAndTime_Label");
		Thread.sleep(2000);
		Select selectDate = new Select(groupSalesPage.TextExempt_DateRange_DD);
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
		Select selectOrganization = new Select(groupSalesPage.TaxExemptSales_Organization_DD);
		selectOrganization.selectByIndex(1);
		String selectedOrg = selectOrganization.getFirstSelectedOption().getAttribute("value").trim();
		System.out.println("Selected Org "+ selectedOrg);
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Thread.sleep(2000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedOrganization(selectedOrg)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'Name Of Organization' on Tax Exempt sales landing page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'Name Of Organization' on Tax Exempt sales landing page.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectOrganization.selectByVisibleText("All");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Thread.sleep(2000);
		Select selectUser = new Select(groupSalesPage.TaxExemptSales_User_DD);
		selectUser.selectByIndex(1);
		String selectedUser = selectUser.getFirstSelectedOption().getAttribute("value").trim();
		System.out.println("Selected User "+ selectedUser);
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Thread.sleep(2000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedUser(selectedUser)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'Name Of User' on Tax Exempt sales landing page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'Name Of User' on Tax Exempt sales landing page.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectUser.selectByVisibleText("All");
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Thread.sleep(2000);
		Select selectDepositStatus = new Select(groupSalesPage.TaxExemptSales_DepositStatus_DD);
		selectDepositStatus.selectByIndex(1);
		String selectedStatus = selectDepositStatus.getFirstSelectedOption().getAttribute("value").trim();
		System.out.println("selectedStatus "+ selectedStatus);
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Thread.sleep(2000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDepositStatus(selectedStatus)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'DepositStatus' "+selectedStatus+" on Tax Exempt sales landing page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'DepositStatus' "+selectedStatus+" on Tax Exempt sales landing page.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDepositStatus.selectByIndex(2);
		selectedStatus = selectDepositStatus.getFirstSelectedOption().getAttribute("value").trim();
		System.out.println("selectedStatus "+ selectedStatus);
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Thread.sleep(2000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDepositStatus(selectedStatus)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'DepositStatus' "+selectedStatus+" on Tax Exempt sales landing page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'DepositStatus' "+selectedStatus+" on Tax Exempt sales landing page.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		try
		{
		selectDepositStatus.selectByIndex(3);
		} catch (Exception e)
		{
			//Do nothing
		}
			
		selectedStatus = selectDepositStatus.getFirstSelectedOption().getAttribute("value").trim();
		System.out.println("selectedStatus "+ selectedStatus);
		GenericMethods.clickOnElement(groupSalesPage.TaxExemptSales_ShowResults_BT,"TaxExemptSales_ShowResults_BT");
		Thread.sleep(2000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDepositStatus(selectedStatus)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'DepositStatus' "+selectedStatus+" on Tax Exempt sales landing page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'DepositStatus' "+selectedStatus+" on Tax Exempt sales landing page.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
	}

}
