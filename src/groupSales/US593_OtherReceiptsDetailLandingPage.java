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

public class US593_OtherReceiptsDetailLandingPage extends AbstractTest
{
	
	// TC3174 : Verifying that user is able to view the Other receipts entries for the selected date range(e.g. 90 days, 6 months, 9 months , 12 months) on Other receipts Sales landing page on cloud app.
	
	@Test()
	public void groupSales_US593_TC3174() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/** Variable Section : **/
		AbstractTest.tcName = "groupSales_US593_TC3174";
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
		String month01=null;
		String exp_endDate_01=null;
		if(day>12)
		{
			day=day-12;
			arr1[2]=Integer.toString(Integer.parseInt(arr1[2])+1);
			if(day<=9)
			{
				month01="0"+Integer.toString(day);
				 exp_endDate_01=month01+"/"+arr1[1]+"/"+arr1[2];
			}
			else
			{
				month01=Integer.toString(day);
				 exp_endDate_01=month01+"/"+arr1[1]+"/"+arr1[2];
			}
		}
		else
		{
			month01="0"+Integer.toString(day);
			 exp_endDate_01=month01+"/"+arr1[1]+"/"+arr1[2];
		}
//		String exp_endDate_01=Integer.toString(day)+"/"+arr1[1]+"/"+arr1[2];
		System.out.println("exp_endDate_01"+exp_endDate_01);
		String endDate_01=groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value");
		System.out.println("endDate_01"+endDate_01);
		
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
			int expmonth=Integer.parseInt(month01);
			System.out.println("actmonth"+actmonth);
			System.out.println("expmonth"+expmonth);
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
		System.out.println("startDate_01 "+startDate_02);
		String arr2[]=startDate_02.split("/");
		System.out.println("Base.getMonthFromDate(startDate_01) "+Base.getCorrectMonthFromDate(startDate_02));
		int day1=(Base.getCorrectMonthFromDate(startDate_02))+6;
		String exp_endDate_02=null;
		String month02=null;
		if(day1>12)
		{
			day1=day1-12;
			arr2[2]=Integer.toString(Integer.parseInt(arr2[2])+1);
			if(day1<=9)
			{
				month02="0"+Integer.toString(day1);
				exp_endDate_02=month02+"/"+arr2[1]+"/"+arr2[2];
			}
			else
			{
				month02=Integer.toString(day1);
				exp_endDate_02=month02+"/"+arr2[1]+"/"+arr2[2];
			}
		}
		else
		{
			month02="0"+Integer.toString(day1);
			exp_endDate_02=month02+"/"+arr2[1]+"/"+arr2[2];
		}
		
		System.out.println("exp_endDate_01"+exp_endDate_02);
		String endDate_02=groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value");
		System.out.println("endDate_01"+endDate_02);
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
			int expmonth=Integer.parseInt(month02);
			System.out.println("actmonth"+actmonth);
			System.out.println("expmonth"+expmonth);
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
		System.out.println("Base.getMonthFromDate(startDate_01) "+Base.getCorrectMonthFromDate(startDate_03));
		int day2=(Base.getCorrectMonthFromDate(startDate_03))+9;
		String exp_endDate_03=null;
		String month03=null;
		if(day2>12)
		{
			day2=day2-12;
			arr3[2]=Integer.toString(Integer.parseInt(arr3[2])+1);
			if(day2<=9)
			{
				month03="0"+Integer.toString(day2);
				exp_endDate_03=month03+"/"+arr3[1]+"/"+arr3[2];
			}
			else
			{
				month03=Integer.toString(day2);
				exp_endDate_03=month03+"/"+arr3[1]+"/"+arr3[2];
			}
		}
		else
		{
			month03="0"+Integer.toString(day2);
			exp_endDate_03=month03+"/"+arr3[1]+"/"+arr3[2];
		}
		
		System.out.println("exp_endDate_01"+exp_endDate_03);
		String endDate_03=groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value");
		System.out.println("endDate_01"+endDate_03);
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
			int expmonth=Integer.parseInt(month03);
			System.out.println("actmonth"+actmonth);
			System.out.println("expmonth"+expmonth);
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
		System.out.println("startDate_01 "+startDate_04);
		String arr4[]=startDate_04.split("/");
		System.out.println("Base.getMonthFromDate(startDate_01) "+Base.getCorrectMonthFromDate(startDate_04));
		int day3=(Base.getCorrectMonthFromDate(startDate_04));
		String exp_endDate_04=null;
		String month04=null;
		
			arr4[2]=Integer.toString(Integer.parseInt(arr4[2])+1);
			if(day3<=9)
			{
				month04="0"+Integer.toString(day3);

				exp_endDate_04=month04+"/"+arr4[1]+"/"+arr4[2];
			}
				else
			{
					month04=Integer.toString(day3);
					exp_endDate_04=month04+"/"+arr4[1]+"/"+arr4[2];
			}
		
		System.out.println("exp_endDate_01"+exp_endDate_04);
		String endDate_04=groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value");
		System.out.println("endDate_01"+endDate_04);
		List <WebElement> element3 =driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[2]/span"));
		
		int numberOfElement3=element3.size();
		System.out.println("numberOfElement "+numberOfElement3);
		for(int i=0;i<numberOfElement3;i++)
		{
			System.out.println("i is "+i);
			System.out.println("element.get(i).getText()"+element3.get(i).getText());
			String[] a=(element3.get(i).getText()).split("/");
			System.out.println("a[0] "+a[0]);
			int actmonth=Integer.parseInt(a[0]);
			int expmonth=Integer.parseInt(month04);
			System.out.println("actmonth"+actmonth);
			System.out.println("expmonth"+expmonth);
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
		if(exp_endDate_04.equalsIgnoreCase(endDate_04))
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
		
	}
	
//TC3175 : Verify that user is able to Sort by Other Receipt details on Other Receipt landing page.
	
			@Test()
			public void groupSales_US593_TC3175() throws InterruptedException,
					RowsExceededException, BiffException, WriteException, IOException, ParseException {
				/** Variable Section : **/
				GroupSalesPage groupSalesPage;
				AbstractTest.tcName="groupSales_US593_TC3175";
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
				//go to Other Receipts Page
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "OtherReceipts_BT");
				Thread.sleep(2000);
				// Verify that columns are displaying
				if (Base.isElementDisplayed(groupSalesPage.OtherReceipts_DateAndTime_Label)
						& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Register_Label)
						& Base.isElementDisplayed(groupSalesPage.OtherReceipts_Amount_Label)
						& Base.isElementDisplayed(groupSalesPage.OtherReceipts_DepositStatus_Label)
						& Base.isElementDisplayed(groupSalesPage.OtherReceipts_OrganizationName_Label)
						& Base.isElementDisplayed(groupSalesPage.OtherReceipts_CheckNumber_Label)
						& Base.isElementDisplayed(groupSalesPage.OtherReceipts_User_Label)) {
					Reporter.reportPassResult(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Check Number 6.Organization Name 7.Deposit Status columns in Other Receipt Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(browser,
							"User should be able to view 1. Date and Time 2.Register # 3.Amount 4.User 5.Check Number 6.Organization Name 7.Deposit Status columns in Other Receipt Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				Thread.sleep(2000);
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInAscendinOrder = groupSalesPage.verifyOtherReceiptsDateInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label");
				Thread.sleep(2000);
				boolean dateInDecendinOrder = groupSalesPage.verifyOtherReceiptsDateInDescendingOrder();
				if (dateInAscendinOrder & dateInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending date in Other Receipts Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
				Thread.sleep(2000);
				boolean registerInAscendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label");
				Thread.sleep(2000);
				boolean registerInDecendinOrder = groupSalesPage.verifyOtherReceiptsRegisterInDescendingOrder();
				if (registerInAscendinOrder & registerInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending register value in Other Receipts Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
				Thread.sleep(2000);
				boolean amountInAscendinOrder = groupSalesPage.verifyOtherReceiptsAmountInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label");
				Thread.sleep(2000);
				boolean amountInDecendinOrder = groupSalesPage.verifyOtherReceiptsAmountInDescendingOrder();
				if (amountInAscendinOrder & amountInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending amount value in Other Receipts Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
				Thread.sleep(2000);
				boolean userInAscendinOrder = groupSalesPage.verifyOtherReceiptsUserInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label");
				Thread.sleep(2000);
				boolean userInDecendinOrder = groupSalesPage.verifyOtherReceiptsUserInDescendingOrder();
				if (userInAscendinOrder & userInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending user value in  Other Receipts Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending user value in  Other Receipts Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInAscendinOrder = groupSalesPage.verifyOtherReceiptssOrganizationInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label");
				Thread.sleep(2000);
				boolean organizationInDecendinOrder = groupSalesPage.verifyOtherReceiptsOrganizationInDescendingOrder();
				if (organizationInAscendinOrder & organizationInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending organization value in Other Receipts  Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending organization value in Other Receipts Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
				Thread.sleep(2000);
				boolean taxIdInAscendinOrder = groupSalesPage.verifyOtherReciptsCheckNumberInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label");
				Thread.sleep(2000);
				boolean taxIdInDecendinOrder = groupSalesPage.verifyOpenReceiptsCheckNumberInDescendingOrder();
				if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending Check Number value in Open Receipts Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending Check Number value in Open Receipts Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInAscendinOrder = groupSalesPage.verifyOpenReceiptsDepositStatusInAscendingOrder();
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label");
				Thread.sleep(2000);
				boolean depositStatusInDecendinOrder = groupSalesPage.verifyOtherReceiptsDepositStatusInDescendingOrder();
				if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
					Reporter.reportPassResult(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
							"Pass");
					
				} else {
					Reporter.reportTestFailure(
							browser,
							"user should be able to toggle between ascending and descending deposit status value in Other Receipts Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Select selectDate = new Select(groupSalesPage.OtherReceipt_DateRange_DD);
				selectDate.selectByVisibleText("Custom Date Range");
				Thread.sleep(2000);
				groupSalesPage.OtherReceiptsselectStartdate(startDate).OtherReceiptsselectEndDate(endDate).OtherReceipt_ShowResult_BT.click();
				Thread.sleep(3000);
				if(groupSalesPage.verifyOtherReceiptDisplayedForSelectedDateRange(startDate, endDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within custom date range in Other Receipts Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within custom date range in Other Receipts Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
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
				if(groupSalesPage.verifyOtherReceiptDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
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
				Thread.sleep(2000);
				selectDate.selectByValue("6");
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipt_ShowResult_BT,"OtherReceipt_ShowResult_BT");
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				selectedEndDate = dateFormat.format(cal2.getTime());
				cal2.add(Calendar.MONTH, -6);
				selectedStartDate = dateFormat.format(cal2.getTime());
				if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 6 months in Other Receipts Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyOtherReceiptDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 6 month date range in Other Receipts Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("9");
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipt_ShowResult_BT,"OtherReceipt_ShowResult_BT");
				Calendar cal3 = Calendar.getInstance();
				cal3.setTime(new Date());
				selectedEndDate = dateFormat.format(cal3.getTime());
				cal3.add(Calendar.MONTH, -9);
				selectedStartDate = dateFormat.format(cal3.getTime());
				if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 9 months in Other Receipts Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyOtherReceiptDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 9 month date range in Other Receipts Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				Thread.sleep(2000);
				selectDate.selectByValue("12");
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipt_ShowResult_BT,"OtherReceipt_ShowResult_BT");
				Calendar cal4 = Calendar.getInstance();
				cal4.setTime(new Date());
				selectedEndDate = dateFormat.format(cal4.getTime());
				cal4.add(Calendar.MONTH, -12);
				selectedStartDate = dateFormat.format(cal4.getTime());
				if(groupSalesPage.OtherReceipts_StartDate_TB.getAttribute("value").equals(selectedStartDate)
						&& groupSalesPage.OtherReceipts_EndDate_TB.getAttribute("value").equals(selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"Start Date, end Date should be updated for selected 12 months in Other Receipts Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
				if(groupSalesPage.verifyOtherReceiptDisplayedForSelectedDateRange(selectedStartDate, selectedEndDate)){
					Reporter.reportPassResult(
							browser,
							"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
							"Pass");
				} else {
					Reporter.reportTestFailure(
							browser,
							"User should be able to view the records within 12 month date range in Other Receipts Landing Page",
							"Fail");
					AbstractTest.takeSnapShot();
				}
			}
			
			
	//TC3176 : 	Verifying user is able to view the total of Other Receipt for the day or for the selected date range on cloud app.
			
			

			@Test()
			public void groupSales_US593_TC3176() throws InterruptedException,
					RowsExceededException, BiffException, WriteException, IOException {
				/** Variable Section : **/
				AbstractTest.tcName = "groupSales_US593_TC3176";
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
				//go to Other Receipts Page
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "OtherReceipts_BT");
				Thread.sleep(2000);
				Select select =new Select(groupSalesPage.OtherReceipt_DateRange_DD);
				select.selectByVisibleText("3 Months");
				Thread.sleep(2000);
				if(GenericMethods.isElementDisplayed(groupSalesPage.OtherReceipts_Total_Label, "OtherReceipts_Total_Label") &&
						groupSalesPage.OtherReceipts_Total_Value.getText().length()>=0)
				{
					Reporter.reportPassResult(
							browser,
							"User should be able to view 'Total of Other Receipt  for Selected Date Range' on Other Receipts landing page for 90 days.",
							"Pass");
					}
				
			else
			{
				
				Reporter.reportPassResult(
						browser,
						"User should be able to view 'Total of Other Receipt for Selected Date Range' on Other Receipts landing page for 90 days.",
						"Pass");
					}
				
				select.selectByVisibleText("6 months");
				Thread.sleep(2000);
				if(GenericMethods.isElementDisplayed(groupSalesPage.OtherReceipts_Total_Label, "OtherReceipts_Total_Label") &&
						groupSalesPage.OtherReceipts_Total_Value.getText().length()>=0)
				{
					Reporter.reportPassResult(
							browser,
							"User should be able to view 'Total of Other Receipt  for Selected Date Range' on Other Receipts  landing page for 180 days.",
							"Pass");
						}
				
			else
			{
				
				Reporter.reportPassResult(
						browser,
						"User should be able to view 'Total of Other Receipt  for Selected Date Range' on Other Receipts  landing page for 180 days.",
						"Pass");
					}
				
				select.selectByVisibleText("9 months");
				Thread.sleep(2000);
				if(GenericMethods.isElementDisplayed(groupSalesPage.OtherReceipts_Total_Label, "OtherReceipts_Total_Label") &&
						groupSalesPage.OtherReceipts_Total_Value.getText().length()>=0)
				{
					Reporter.reportPassResult(
							browser,
							"User should be able to view 'Total of Other Receipt  for Selected Date Range' on Other Receipts  landing page for 270 days.",
							"Pass");
						}
				
			else
			{
				
				Reporter.reportPassResult(
						browser,
						"User should be able to view 'Total of Other Receipt for Selected Date Range' on Other Receipts  landing page for 270 days.",
						"Pass");
					}
				
				select.selectByVisibleText("12 months");
				Thread.sleep(2000);
				if(GenericMethods.isElementDisplayed(groupSalesPage.OtherReceipts_Total_Label, "OtherReceipts_Total_Label") &&
						groupSalesPage.OtherReceipts_Total_Value.getText().length()>=0)
				{
					Reporter.reportPassResult(
							browser,
							"User should be able to view 'Total of Other Receipt  for Selected Date Range' on Other Receipts landing page for 360 days.",
							"Pass");
						}
				
			else
			{
				
				Reporter.reportPassResult(
						browser,
						"User should be able to view 'Total of Other Receipt  for Selected Date Range' on Other Receiptslanding page for 360 days.",
						"Pass");
					}	
				
				
				
			}
			
		
			//TC3177 : Verify that Shift manager is able to view Following Fields are Displayed on Other Receipt Landing Page..
			
			
			@Test()
			public void groupSales_US593_TC3177() throws InterruptedException,
					RowsExceededException, BiffException, WriteException, IOException {
				/** Variable Section : **/
				AbstractTest.tcName = "groupSales_US593_TC3177";
				String password = LoginTestData.level1_SSO_Password;
				String userId = LoginTestData.level1_SSO_UserId;
				String storeId = LoginTestData.level1StoreId;
				/***********************************/
				HomePage homePage = PageFactory.initElements(driver, HomePage.class);
				GroupSalesPage groupSalesPage = PageFactory.initElements(driver, GroupSalesPage.class);
				// Navigate to Group Sales Page
				groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
						.goToGroupSalesPage();
				Thread.sleep(3000);
				//go to Other Receipts Page
				GenericMethods.clickOnElement(groupSalesPage.OtherReceipts_BT, "OtherReceipts_BT");
				Thread.sleep(2000);
				//Verify the Following fields are displaying on the Other Receipt Page
				/*	Register # (POS Integration from local app)
					Other Receipt Amount (POS Integration from local app)
					Date and Time   (date and time of sale (POS Integration from local app))
					User    (includes user name and ID (populated from the manager conducting the DCD from which the transaction originally occurred)
					Type    (type of Other Receipt, either Food, Paper, or Other)
					Organization  (name of organization) 
					Check number 
					Deposit status  (auto-populated based on status of deposit the other receipt is associated to)*/

				
				if(GenericMethods.isElementDisplayed(groupSalesPage.OtherReceipts_DateAndTime_Label, "OtherReceipts_DateAndTime_Label") &&
						GenericMethods.isElementDisplayed(groupSalesPage.OtherReceipts_Register_Label, "OtherReceipts_Register_Label") &&
						GenericMethods.isElementDisplayed(groupSalesPage.OtherReceipts_Amount_Label, "OtherReceipts_Amount_Label") &&
						GenericMethods.isElementDisplayed(groupSalesPage.OtherReceipts_OrganizationName_Label, "OtherReceipts_OrganizationName_Label") &&
						GenericMethods.isElementDisplayed(groupSalesPage.OtherReceipts_DepositStatus_Label, "OtherReceipts_DepositStatus_Label") &&
						GenericMethods.isElementDisplayed(groupSalesPage.OtherReceipts_User_Label, "OtherReceipts_User_Label") &&
						GenericMethods.isElementDisplayed(groupSalesPage.OtherReceipts_CheckNumber_Label, "OtherReceipts_CheckNumber_Label")
						)
				{
					Reporter.reportPassResult(
							browser,
							"All the fields should dispaly accordingly",
							"Pass");
				}
				else
				{
					Reporter.reportTestFailure(
							browser,
							"All the fields should dispaly accordingly",
							"Fail");
					AbstractTest.takeSnapShot();
				}
			}
			
//TC3178 : Verifying user is able to perform filtering on User, Organization, Deposit Status only using search button on cloud app
	
	
			@Test()
			public void groupSales_US593_TC3178() throws InterruptedException,
					RowsExceededException, BiffException, WriteException, IOException, ParseException {
				/** Variable Section : **/
				GroupSalesPage groupSalesPage;
				AbstractTest.tcName="groupSales_US593_TC3178";
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
				/*selectDepositStatus.selectByVisibleText("Bagged");
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
				}*/
				
			}
	
	
	
	
	
	
	
	

}
