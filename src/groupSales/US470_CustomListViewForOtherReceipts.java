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

public class US470_CustomListViewForOtherReceipts extends AbstractTest
{
	
	
	//TC1506 : Verify that Store Level user is able to view/sort the Other Receipts details  by user selectable date range on Other Receipts landing page
	
	
	@Test()
	public void groupSales_US470_TC1506() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/** Variable Section : **/
		AbstractTest.tcName = "groupSales_US470_TC1506";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		GroupSalesPage groupSalesPage = PageFactory.initElements(driver, GroupSalesPage.class);
		// Navigate to Group Sales Page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		//Go to Other Receipts page
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "groupSalesPage.OtherReceipts_BT");
		Thread.sleep(2000);
		Select select =new Select(groupSalesPage.OtherReceipt_DateRange_DD);
		select.selectByVisibleText("3 Months");
		Thread.sleep(2000);
		//click on Show Result button
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipt_ShowResult_BT, "groupSalesPage.OtherReceipt_ShowResult_BT");
		Thread.sleep(2000);
		
		
		String startDate_01=groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value");
		System.out.println("startDate_01 "+startDate_01);
		String arr1[]=startDate_01.split("/");
		System.out.println("Base.getMonthFromDate(startDate_01) "+Base.getCorrectMonthFromDate(startDate_01));
		int day=(Base.getCorrectMonthFromDate(startDate_01))+3;
		String month=null;
		if(day>12)
		{
			day=day-12;
			arr1[2]=Integer.toString(Integer.parseInt(arr1[2])+1);
			if(day<=9)
			{
				 month="0"+Integer.toString(day);
			}
		}
		
		String exp_endDate_01=month+"/"+arr1[1]+"/"+arr1[2];
		System.out.println("exp_endDate_01 "+exp_endDate_01);
		String endDate_01=groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value");
		System.out.println("endDate_01 "+endDate_01);
		
		List <WebElement> element =driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[2]/span"));
		
		int numberOfElement=element.size();
		System.out.println("numberOfElement "+numberOfElement);
		for(int i=0;i<numberOfElement;i++)
		{
			System.out.println("i is "+i);
			System.out.println("element.get(i).getText()"+element.get(i).getText());
			String[] a=(element.get(i).getText()).split("/");
			System.out.println("a[0] "+a[0]);
			int actmonth=Integer.parseInt(a[0]);
			int expmonth=Integer.parseInt(month);
			System.out.println("actmonth for 3 months"+actmonth);
			System.out.println("expmonth for 3 months"+expmonth);
			if(actmonth==(expmonth) || actmonth==(expmonth+1) || actmonth==(expmonth+2) || actmonth==(expmonth+3) && i==numberOfElement-1)
			{
				Reporter.reportPassResult(
						browser,
						"All the record of the selected date range should display",
						"Pass");
				break;
			}
			else if(actmonth==(expmonth) || actmonth==(expmonth+1) || actmonth==(expmonth+2) || actmonth==(expmonth+3))
			{
				continue;
				
			}
			else
			{
				Reporter.reportTestFailure(
						browser,
						"All the record of the selected date range should display",
						"Fail");
				AbstractTest.takeSnapShot();
				break;
			}
		}
			if(exp_endDate_01.equalsIgnoreCase(endDate_01))
			{
				Reporter.reportPassResult(
					browser,
					"User should be able to view Start Date and End Date is updated for selected Date range says 90 days",
					"Pass");
			}
			else
			{
				Reporter.reportTestFailure(
					browser,
					"User should be able to view Start Date and End Date is updated for selected Date range says 90 days",
					"Fail");
				AbstractTest.takeSnapShot();
			}
		
		select.selectByVisibleText("6 months");
		Thread.sleep(2000);
		//click on Show Result button
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipt_ShowResult_BT, "groupSalesPage.OtherReceipt_ShowResult_BT");
		Thread.sleep(2000);
				
		String startDate_02=groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value");
		System.out.println("startDate_02 "+startDate_02);
		String arr2[]=startDate_02.split("/");
		System.out.println("Base.getMonthFromDate(startDate_02) "+Base.getCorrectMonthFromDate(startDate_02));
		int day1=(Base.getCorrectMonthFromDate(startDate_02))+6;
		String exp_endDate_02=null;
		String month1=null;
		if(day1>12)
		{
			day1=day1-12;
			arr2[2]=Integer.toString(Integer.parseInt(arr2[2])+1);
			if(day1<=9)
			{
				 month1="0"+Integer.toString(day1);
			}
		}
		
		 exp_endDate_02=month1+"/"+arr2[1]+"/"+arr2[2];
	/*	if(day1>12)
		{
			String yearnew=Integer.toString(Integer.parseInt(arr2[2])+1);
			 exp_endDate_02=Integer.toString(day1-12)+"/"+arr2[1]+"/"+yearnew;
		}
		else
		{
			 exp_endDate_02=Integer.toString(day1)+"/"+arr2[1]+"/"+arr2[2];

		}*/
		System.out.println("exp_endDate_02 "+exp_endDate_02);
		String endDate_02=groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value");
		System.out.println("endDate_02 "+endDate_02);
		Thread.sleep(2000);
		List <WebElement> element1 =driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[2]/span"));
		
		int numberOfElement1=element1.size();
		System.out.println("numberOfElement "+numberOfElement1);
		for(int i=0;i<numberOfElement1;i++)
		{
			System.out.println("i is "+i);
			System.out.println("element.get(i).getText()"+element1.get(i).getText());
			String[] a=(element1.get(i).getText()).split("/");
			System.out.println("a[0] "+a[0]);
			int actmonth=Integer.parseInt(a[0]);
			int expmonth=Integer.parseInt(month1);
			System.out.println("actmonth for 6 month "+actmonth);
			System.out.println("expmonth for 6 month "+expmonth);
			if(actmonth==(expmonth) || actmonth==(expmonth+1) || actmonth==(expmonth+2) || actmonth==(expmonth+3) || actmonth==(expmonth+2) || actmonth==(expmonth+4) || actmonth==(expmonth+2) || actmonth==(expmonth+5) || actmonth==(expmonth+2) || actmonth==(expmonth+6) && i==numberOfElement-1)
			{
				Reporter.reportPassResult(
						browser,
						"All the record of the selected date range should display",
						"Pass");
				break;
			}
			else if(actmonth==(expmonth) || actmonth==(expmonth+1) || actmonth==(expmonth+2) || actmonth==(expmonth+3) || actmonth==(expmonth+2) || actmonth==(expmonth+4) || actmonth==(expmonth+2) || actmonth==(expmonth+5) || actmonth==(expmonth+2) || actmonth==(expmonth+6))
			{
				continue;
				
			}
			else
			{
				Reporter.reportTestFailure(
						browser,
						"All the record of the selected date range should display",
						"Fail");
				AbstractTest.takeSnapShot();
				break;
			}
		}
		if(exp_endDate_02.equalsIgnoreCase(endDate_02))
		{
			Reporter.reportPassResult(
					browser,
					"User should be able to view Start Date and End Date is updated for selected Date range says 180 days",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,
					"User should be able to view Start Date and End Date is updated for selected Date range says 180 days",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		
		
		select.selectByVisibleText("9 months");
		Thread.sleep(2000);
		//click on Show Result button
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipt_ShowResult_BT, "groupSalesPage.OtherReceipt_ShowResult_BT");
		Thread.sleep(2000);
		String startDate_03=groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value");
		System.out.println("startDate_03 "+startDate_03);
		String arr3[]=startDate_03.split("/");
		System.out.println("Base.getMonthFromDate(startDate_03) "+Base.getCorrectMonthFromDate(startDate_03));
		int day2=(Base.getCorrectMonthFromDate(startDate_03))+9;
		String exp_endDate_03=null;
		/*if(day2>12)
		{
			String yearnew=Integer.toString(Integer.parseInt(arr3[2])+1);
			exp_endDate_03=(Integer.toString(day2-12))+"/"+arr3[1]+"/"+yearnew;
		}
		else
		{
			exp_endDate_03=Integer.toString(day2)+"/"+arr2[1]+"/"+arr2[2];

		}*/
		
		String month2=null;
		if(day2>12)
		{
			day2=day2-12;
			arr3[2]=Integer.toString(Integer.parseInt(arr3[2])+1);
			if(day2<=9)
			{
				 month2="0"+Integer.toString(day2);
			}
		}
		
		 exp_endDate_03=month2+"/"+arr3[1]+"/"+arr3[2];
		System.out.println("exp_endDate_03 "+exp_endDate_03);
		String endDate_03=groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value");
		System.out.println("endDate_03 "+endDate_03);
		List <WebElement> element2 =driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[2]/span"));
		
		int numberOfElement2=element2.size();
		System.out.println("numberOfElement "+numberOfElement2);
		for(int i=0;i<numberOfElement2;i++)
		{
			System.out.println("i is "+i);
			System.out.println("element.get(i).getText()"+element2.get(i).getText());
			String[] a=(element2.get(i).getText()).split("/");
			System.out.println("a[0] "+a[0]);
			int actmonth=Integer.parseInt(a[0]);
			int expmonth=Integer.parseInt(month2);
			System.out.println("actmonth for 9 moth "+actmonth);
			System.out.println("expmonth for 9 month"+expmonth);
			if(actmonth==(expmonth) || actmonth==(expmonth+1) || actmonth==(expmonth+2) || actmonth==(expmonth+3) || actmonth==(expmonth+2) || actmonth==(expmonth+4) || actmonth==(expmonth+2) || actmonth==(expmonth+5) || actmonth==(expmonth+2) || actmonth==(expmonth+6) || actmonth==(expmonth+7) || actmonth==(expmonth+8) || actmonth==(expmonth+9) && i==numberOfElement-1)
			{
				Reporter.reportPassResult(
						browser,
						"All the record of the selected date range should display",
						"Pass");
				break;
			}
			else if(actmonth==(expmonth) || actmonth==(expmonth+1) || actmonth==(expmonth+2) || actmonth==(expmonth+3) || actmonth==(expmonth+2) || actmonth==(expmonth+4) || actmonth==(expmonth+2) || actmonth==(expmonth+5) || actmonth==(expmonth+2) || actmonth==(expmonth+6) || actmonth==(expmonth+7) || actmonth==(expmonth+8) || actmonth==(expmonth+9) )
			{
				continue;
				
			}
			else
			{
				Reporter.reportTestFailure(
						browser,
						"All the record of the selected date range should display",
						"Fail");
				AbstractTest.takeSnapShot();
				break;
			}
		}
		if(exp_endDate_03.equalsIgnoreCase(endDate_03))
		{
			Reporter.reportPassResult(
					browser,
					"User should be able to view Start Date and End Date is updated for selected Date range says 270 days",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,
					"User should be able to view Start Date and End Date is updated for selected Date range says 270 days",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		
		select.selectByVisibleText("12 months");
		Thread.sleep(2000);
		//click on Show Result button
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipt_ShowResult_BT, "groupSalesPage.OtherReceipt_ShowResult_BT");
		Thread.sleep(2000);
		String startDate_04=groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value");
		System.out.println("startDate_04 "+startDate_04);
		String arr4[]=startDate_04.split("/");
		System.out.println("Base.getMonthFromDate(startDate_04) "+Base.getCorrectMonthFromDate(startDate_04));
		int day3=(Base.getCorrectMonthFromDate(startDate_04));
		String exp_endDate_04=null;
		String month3=null;
		/*if(day3>12)
		{
			day3=day3-12;
			arr4[2]=Integer.toString(Integer.parseInt(arr4[2])+1);
			if(day3<=9)
			{
				 month3="0"+Integer.toString(day3);
			}
		}*/
		if(day3<=9)
		{
			month3="0"+Integer.toString(day3);
		}
		
		 exp_endDate_04=month3+"/"+arr4[1]+"/"+Integer.toString(Integer.parseInt(arr4[2])+1);
		/*if(day3>12)
		{
			String yearnew=Integer.toString(Integer.parseInt(arr4[2])+1);
			exp_endDate_04=Integer.toString(day3)+"/"+arr4[1]+"/"+yearnew;
		}
		else
		{
			exp_endDate_04=Integer.toString(day3)+"/"+arr4[1]+"/"+arr3[2];

		}*/
		System.out.println("exp_endDate_04 "+exp_endDate_04);
		String endDate_04=groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value");
		System.out.println("endDate_04 "+endDate_04);
		/*List <WebElement> element3 =driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[2]/span"));
		
		int numberOfElement3=element3.size();
		System.out.println("numberOfElement "+numberOfElement3);
		for(int i=0;i<numberOfElement3;i++)
		{
			System.out.println("i is "+i);
			System.out.println("element.get(i).getText()"+element3.get(i).getText());
			String[] a=(element3.get(i).getText()).split("/");
			System.out.println("a[0] "+a[0]);
			int actmonth=Integer.parseInt(a[0]);
			int expmonth=Base.getCorrectMonthFromDate(startDate_04);
			System.out.println("actmonth for 12 month"+actmonth);
			System.out.println("expmonth for 12 month"+expmonth);
			if(actmonth==(expmonth) || actmonth==(expmonth+1) || actmonth==(expmonth+2) || actmonth==(expmonth+3) && i==numberOfElement-1)
			{
				Reporter.reportPassResult(
						browser,
						"All the record of the selected date range should display",
						"Pass");
				break;
			}
			else if(actmonth==(expmonth) || actmonth==(expmonth+1) || actmonth==(expmonth+2) || actmonth==(expmonth+3))
			{
				continue;
				
			}
			else
			{
				Reporter.reportTestFailure(
						browser,
						"All the record of the selected date range should display",
						"Fail");
				AbstractTest.takeSnapShot();
				break;
			}
		}*/
		if(exp_endDate_04.equalsIgnoreCase(endDate_04))
		{
			Reporter.reportPassResult(
					browser,
					"User should be able to view Start Date and End Date is updated for selected Date range says 360 days",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,
					"User should be able to view Start Date and End Date is updated for selected Date range says 360 days",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		
		
	}
	
	//TC1507 : Verify that store level user is able to filter Other Receipts entries by organization field on Other Receipts landing page.
	
	
	

	@Test()
	public void groupSales_US470_TC1507() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US470_TC1507";
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
		//go to Other Receipts Page
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "OtherReceipts_BT");
		Thread.sleep(2000);
		Select selectDate = new Select(groupSalesPage.OtherReceipt_DateRange_DD);
		selectDate.selectByValue("3");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipt_ShowResult_BT,"OtherReceipt_ShowResult_BT");
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.setTime(new Date());
		String selectedEndDate = dateFormat.format(cal1.getTime());
		System.out.println("endDate "+endDate);
		cal1.add(Calendar.MONTH, -3);
		String selectedStartDate = dateFormat.format(cal1.getTime());
		System.out.println("startDate "+startDate);
		if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
				&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Start Date, end Date should be updated for selected 3 months in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within 3 month date range in Other Receipts Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Select selectOrganization = new Select(groupSalesPage.OtherReceipt_Organization_DD);
		selectOrganization.selectByIndex(1);
		String selectedOrg = selectOrganization.getFirstSelectedOption().getAttribute("value").trim();
		System.out.println("Selected Org "+ selectedOrg);
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipt_ShowResult_BT,"OtherReceipt_ShowResult_BT");
		Thread.sleep(2000);
		if(groupSalesPage.verifyOtherReceiptsDisplayedForSelectedOrganization(selectedOrg)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'Name Of Organization' on Other Receipts landing page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'Name Of Organization' on Other Receipts landing page.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectOrganization.selectByVisibleText("All");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipt_ShowResult_BT,"OtherReceipt_ShowResult_BT");
		Thread.sleep(2000);
		Select selectUser = new Select(groupSalesPage.OtherReceipts_User_DD);
		selectUser.selectByIndex(1);
		String selectedUser = selectUser.getFirstSelectedOption().getAttribute("value").trim();
		System.out.println("Selected User "+ selectedUser);
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipt_ShowResult_BT,"OtherReceipt_ShowResult_BT");
		Thread.sleep(2000);
		if(groupSalesPage.verifyOtherReceiptDisplayedForSelectedUser(selectedUser)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'Name Of User' on Other Receipt  landing page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'Name Of User' on Other Receipt landing page.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectUser.selectByVisibleText("All");
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipt_ShowResult_BT,"OtherReceipt_ShowResult_BT");
		Thread.sleep(2000);
		Select selectDepositStatus = new Select(groupSalesPage.OtherReceipt_DepositStatus_DD);
		selectDepositStatus.selectByIndex(1);
		String selectedStatus = selectDepositStatus.getFirstSelectedOption().getAttribute("value").trim();
		System.out.println("selectedStatus "+ selectedStatus);
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipt_ShowResult_BT,"OtherReceipt_ShowResult_BT");
		Thread.sleep(2000);
		if(groupSalesPage.verifyOtherReceiptDisplayedForSelectedDepositStatus(selectedStatus)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'DepositStatus' "+selectedStatus+" on Other Receipt landing page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'DepositStatus' "+selectedStatus+" on Other Receipt landing page.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		selectDepositStatus.selectByIndex(2);
		selectedStatus = selectDepositStatus.getFirstSelectedOption().getAttribute("value").trim();
		System.out.println("selectedStatus "+ selectedStatus);
		GenericMethods.clickOnElement(groupSalesPage.OtherReceipt_ShowResult_BT,"OtherReceipt_ShowResult_BT");
		Thread.sleep(2000);
		if(groupSalesPage.verifyOtherReceiptDisplayedForSelectedDepositStatus(selectedStatus)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'DepositStatus' "+selectedStatus+" on Other Receipt landing page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view all Tax Exempt sales entries are filtered by 'DepositStatus' "+selectedStatus+" on Other Receipt landing page.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
				
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
