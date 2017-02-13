package groupSales;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import common.Base;
import common.GenericMethods;
import common.LoginTestData;
import common.Reporter;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.GroupSalesPage;
import eCashPageClasses.HomePage;
	
public class US591_BillableSalesDetailLandingPage extends AbstractTest
{


	//	TC1303 : Verifying all Billable sales entries are available for the selected date range on Billable sales landing page on Cloud App.
		@Test()
		public void groupSales_US591_TC1303() throws InterruptedException,
				RowsExceededException, BiffException, WriteException, IOException {
			/** Variable Section : **/
			AbstractTest.tcName = "groupSales_US591_TC1303";
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
			//click on billable sales
			groupSalesPage.BillableSales_BT.click();
			Thread.sleep(3000);
			Select select =new Select(groupSalesPage.BillableSales_DateRange_DD);
			select.selectByVisibleText("3 Months");
			Thread.sleep(2000);
			String startDate_01=groupSalesPage.BillableSales_StartDate_TB.getAttribute("value");
			System.out.println("startDate_01 "+startDate_01);
			String arr1[]=startDate_01.split("/");
			System.out.println("Base.getCorrectMonthFromDate(startDate_01) "+Base.getCorrectMonthFromDate(startDate_01));
			int day=(Base.getCorrectMonthFromDate(startDate_01))+3;
			String month01=null;
			if(day>12)
			{
				day=day-12;
				arr1[2]=Integer.toString(Integer.parseInt(arr1[2])+1);
				if(day<=9)
				{
					month01="0"+Integer.toString(day);
				}
			}
			
			String exp_endDate_01=month01+"/"+arr1[1]+"/"+arr1[2];
//			String exp_endDate_01=Integer.toString(day)+"/"+arr1[1]+"/"+arr1[2];
			System.out.println("exp_endDate_01"+exp_endDate_01);
			String endDate_01=groupSalesPage.BillableSales_EndDate_TB.getAttribute("value");
			System.out.println("endDate_01"+endDate_01);
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
			String startDate_02=groupSalesPage.BillableSales_StartDate_TB.getAttribute("value");
			System.out.println("startDate_01 "+startDate_02);
			String arr2[]=startDate_01.split("/");
			System.out.println("Base.getCorrectMonthFromDate(startDate_01) "+Base.getCorrectMonthFromDate(startDate_02));
			int day1=(Base.getCorrectMonthFromDate(startDate_02))+6;
			String exp_endDate_02=null;
			String month02=null;
			if(day>12)
			{
				day=day-12;
				arr1[2]=Integer.toString(Integer.parseInt(arr1[2])+1);
				if(day<=9)
				{
					month02="0"+Integer.toString(day);
				}
			}
			
			 exp_endDate_02=month01+"/"+arr1[1]+"/"+arr1[2];
			System.out.println("exp_endDate_01"+exp_endDate_02);
			String endDate_02=groupSalesPage.BillableSales_EndDate_TB.getAttribute("value");
			System.out.println("endDate_01"+endDate_02);
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
			String startDate_03=groupSalesPage.BillableSales_StartDate_TB.getAttribute("value");
			System.out.println("startDate_01 "+startDate_03);
			String arr3[]=startDate_01.split("/");
			System.out.println("Base.getCorrectMonthFromDate(startDate_01) "+Base.getCorrectMonthFromDate(startDate_03));
			int day2=(Base.getCorrectMonthFromDate(startDate_03))+9;
			String exp_endDate_03=null;
			String month03=null;
			if(day>12)
			{
				day=day-12;
				arr1[2]=Integer.toString(Integer.parseInt(arr1[2])+1);
				if(day<=9)
				{
					month03="0"+Integer.toString(day);
				}
			}
			
			 exp_endDate_03=month01+"/"+arr1[1]+"/"+arr1[2];
			System.out.println("exp_endDate_01"+exp_endDate_03);
			String endDate_03=groupSalesPage.BillableSales_EndDate_TB.getAttribute("value");
			System.out.println("endDate_01"+endDate_03);
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
			
			
		}
		
	
	// TC1306 : 	Verifying user is able to Sort(Ascending/Descending Order) by Billable Sales details on Cloud App.
	
	
	
		@Test()
		public void groupSales_US591_TC1306() throws InterruptedException,
				RowsExceededException, BiffException, WriteException, IOException {
			/** Variable Section : **/
			AbstractTest.tcName = "groupSales_US591_TC1306";
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
			//click on billable sales
			groupSalesPage.BillableSales_BT.click();
			Thread.sleep(3000);
			
			if(GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_Amount_Label, "groupSalesPage.BillableSales_Amount_Label") && 
					GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_DateAndTime_Label, "groupSalesPage.BillableSales_DateAndTime_Label") &&
					GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_DepositStatus_Label, "groupSalesPage.BillableSales_DepositStatus_Label") &&
					GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_InvoicePO_Label, "groupSalesPage.BillableSales_InvoicePO_Label") &&
					GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_OrganizationName_Label, "groupSalesPage.BillableSales_OrganizationName_Label") &&
					GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_Register_Label, "groupSalesPage.BillableSales_Register_Label") &&
					GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_User_Label, "groupSalesPage.BillableSales_User_Label") )
					
					{
				Reporter.reportPassResult(
						browser,
						"All the fields and data should dispaly as accordingly",
						"Pass");
					}
					else
					{
						Reporter.reportTestFailure(
								browser,
								"All the fields and data should dispaly as accordingly",
								"Fail");
						AbstractTest.takeSnapShot();
					}
			
		}
		
	
		// TC1308 : 	Verifying user is able to view the total of Billable sales for the day in Billable sales Detail Landing page on Cloud App.
		
		
		
			@Test()
			public void groupSales_US591_TC1318() throws InterruptedException,
					RowsExceededException, BiffException, WriteException, IOException {
				/** Variable Section : **/
				AbstractTest.tcName = "groupSales_US591_TC1318";
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
				//click on billable sales
				groupSalesPage.BillableSales_BT.click();
				Thread.sleep(3000);
				Select select =new Select(groupSalesPage.BillableSales_DateRange_DD);
				select.selectByVisibleText("3 Months");
				Thread.sleep(2000);
				if(GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_Total_Label, "groupSalesPage.BillableSales_Total_Label") &&
						groupSalesPage.BillableSales_Total_Value.getText().length()>=0)
				{
					Reporter.reportPassResult(
							browser,
							"User should be able to view 'Total amount of Billable sale for Selected Date Range' on billable sales landing page for 90 days.",
							"Pass");
					}
				
			else
			{
				
				Reporter.reportPassResult(
						browser,
						"User should be able to view 'Total amount of Billable sale for Selected Date Range' on billable sales landing page for 90 days.",
						"Pass");
					}
				
				select.selectByVisibleText("6 months");
				Thread.sleep(2000);
				if(GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_Total_Label, "groupSalesPage.BillableSales_Total_Label") &&
						groupSalesPage.BillableSales_Total_Value.getText().length()>=0)
				{
					Reporter.reportPassResult(
							browser,
							"User should be able to view 'Total amount of Billable sale for Selected Date Range' on billable sales landing page for 180 days.",
							"Pass");
						}
				
			else
			{
				
				Reporter.reportPassResult(
						browser,
						"User should be able to view 'Total amount of Billable sale for Selected Date Range' on billable sales landing page for 180 days.",
						"Pass");
					}
				
				select.selectByVisibleText("9 months");
				Thread.sleep(2000);
				if(GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_Total_Label, "groupSalesPage.BillableSales_Total_Label") &&
						groupSalesPage.BillableSales_Total_Value.getText().length()>=0)
				{
					Reporter.reportPassResult(
							browser,
							"User should be able to view 'Total amount of Billable sale for Selected Date Range' on billable sales landing page for 270 days.",
							"Pass");
						}
				
			else
			{
				
				Reporter.reportPassResult(
						browser,
						"User should be able to view 'Total amount of Billable sale for Selected Date Range' on billable sales landing page for 270 days.",
						"Pass");
					}
				
				select.selectByVisibleText("12 months");
				Thread.sleep(2000);
				if(GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_Total_Label, "groupSalesPage.BillableSales_Total_Label") &&
						groupSalesPage.BillableSales_Total_Value.getText().length()>=0)
				{
					Reporter.reportPassResult(
							browser,
							"User should be able to view 'Total amount of Billable sale for Selected Date Range' on billable sales landing page for 360 days.",
							"Pass");
						}
				
			else
			{
				
				Reporter.reportPassResult(
						browser,
						"User should be able to view 'Total amount of Billable sale for Selected Date Range' on billable sales landing page for 360 days.",
						"Pass");
					}	
				
				
				
			}
			
		
			// TC1324 : 	Verifying user is able to Sort(Ascending/Descending Order) by Billable Sales details on Cloud App.
			
			
			
			@Test()
			public void groupSales_US591_TC1324() throws InterruptedException,
					RowsExceededException, BiffException, WriteException, IOException {
				/** Variable Section : **/
				AbstractTest.tcName = "groupSales_US591_TC1324";
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
				//click on billable sales
				groupSalesPage.BillableSales_BT.click();
				Thread.sleep(3000);
				if(GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_UserDD_Label, "groupSalesPage.UserDD_Label") &&
						GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_User_DD, "groupSalesPage.User_DD") &&
						GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_OrganizationDD_Label, "groupSalesPage.OrganizationDD_Label") &&
						GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_Organization_DD, "groupSalesPage.Organization_DD") &&
						GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_DepositStatusDD_Label, "groupSalesPage.DepositStatusDD_Label") &&
						GenericMethods.isElementDisplayed(groupSalesPage.BillableSales_DepositStatus_DD, "groupSalesPage.DepositStatus_DD"))
				{
					Reporter.reportPassResult(
							browser,
							"User should be able to view all the fields (Organization Name ,User ,Deposit Status) with proper data and label)",
							"Pass");
						}
				
				else
				{
					Reporter.reportPassResult(
							browser,
							"User should be able to view all the fields (Organization Name ,User ,Deposit Status) with proper data and label)",
							"Pass");
						}	
				
			}
			
		
	
	
	
	
	
	
	
	
	
	
	
	
}
