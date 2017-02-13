package drawerCountDown;

import java.io.IOException;
import java.text.ParseException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.Base;
import common.GenericMethods;
import common.LoginTestData;
import common.Reporter;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.DrawerCountDownPage;
import eCashPageClasses.HomePage;

public class US84_DCDLandingPage extends AbstractTest
{
	
	//TC3916 :Verify the Pathway to navigate to Drawer count down Landing Page.
	
	
	@Test()
	public void drawerCountDown_US84_TC3916() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		DrawerCountDownPage drawerCountDownPage;
		AbstractTest.tcName="drawerCountDown_US84_TC3916";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		drawerCountDownPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToDrawerCountDownPage();
		Thread.sleep(2000);
		//Verify navigated successfully on drawer count landing page
		if(GenericMethods.isElementDisplayed(drawerCountDownPage.DrawerCount_Label, "drawerCountDownPage.DrawerCount_Label"))
		{
			Reporter.reportPassResult(browser,
					"User should be successfully navagated to drawer count page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be successfully navagated to drawer count page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	
	//TC3917 : Verify that user is able to view Store number on DCD landing page.
	
	
	@Test()
	public void drawerCountDown_US84_TC3917() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		DrawerCountDownPage drawerCountDownPage;
		AbstractTest.tcName="drawerCountDown_US84_TC3917";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		drawerCountDownPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToDrawerCountDownPage();
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.xpath("//span[@id='nsn']")).getText());
		//Verify navigated successfully on drawer count landing page
		if(driver.findElement(By.xpath("//span[@id='nsn']")).getText().equalsIgnoreCase(storeId))
		{
			Reporter.reportPassResult(browser,
					"Store number should appear next to Title name-eProfitability",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"Store number should appear next to Title name-eProfitability",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	
	
	//TC3918 : Verify the default value for DCD Business Date field on DCD landing page.
	
	
		@Test()
		public void drawerCountDown_US84_TC3918() throws InterruptedException,
				RowsExceededException, BiffException, WriteException, IOException, ParseException {
			/** Variable Section : **/
			DrawerCountDownPage drawerCountDownPage;
			AbstractTest.tcName="drawerCountDown_US84_TC3918";
			String password = LoginTestData.level1_SSO_Password;
			String userId = LoginTestData.level1_SSO_UserId;
			String storeId = LoginTestData.level1StoreId;
			String tDate=Base.returnTodayDate();
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to Group sales page
			drawerCountDownPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
					.goToDrawerCountDownPage();
			Thread.sleep(2000);
			System.out.println(drawerCountDownPage.DrawerCount_Date_TB.getAttribute("value"));
			System.out.println("Expected "+tDate);
			//Verify navigated successfully on drawer count landing page
			if(drawerCountDownPage.DrawerCount_Date_TB.getAttribute("value").equalsIgnoreCase(tDate))
			{
				Reporter.reportPassResult(browser,
						"Current Business Date should appear as default date on DCD business date field.",
						"Pass");
			} else {
				Reporter.reportTestFailure(browser,
						"Current Business Date should appear as default date on DCD business date field.",
						"Fail");
				AbstractTest.takeSnapShot();
			}
		}
		
		
	//TC3919 : 	Verify that user confirmation is not required to view Drawer Counts after selecting desired business date.
	
	

		@Test()
		public void drawerCountDown_US84_TC3919() throws InterruptedException,
				RowsExceededException, BiffException, WriteException, IOException, ParseException {
			/** Variable Section : **/
			DrawerCountDownPage drawerCountDownPage;
			AbstractTest.tcName="drawerCountDown_US84_TC3919";
			String password = LoginTestData.level1_SSO_Password;
			String userId = LoginTestData.level1_SSO_UserId;
			String storeId = LoginTestData.level1StoreId;
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to Group sales page
			drawerCountDownPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
					.goToDrawerCountDownPage();
			Thread.sleep(2000);
			//Get the desire date to select
			String[] x=Base.returnTodayDate().split("/");
			String date;
			if(Integer.parseInt(x[0])==1)
			{
				date=Integer.toString((Integer.parseInt(x[0])+11))+"/"+Integer.toString((Integer.parseInt(x[1])-1))+"/"+Integer.toString((Integer.parseInt(x[2])-1));
			}
			else 
			{
				date=Integer.toString((Integer.parseInt(x[0])-1))+"/"+Integer.toString((Integer.parseInt(x[1])-1))+"/"+x[2];
			}
			System.out.println(date);
			drawerCountDownPage.selectDrawerCountDate(date);
			Thread.sleep(3000);
			if(Base.isElementDisplayed(By.xpath("//table[@id='drawer_counts_table']/tbody/tr/td/div[@class='noResultsImg']")) || driver.findElements(By.xpath("//table[@id='drawer_counts_table']/tbody/tr")).size()>0)
			{
				Reporter.reportPassResult(browser,
						"User should be able to view Drawer count details automatically after selecting business date without any confirmation.",
						"Pass");
			}
			else
			{
				Reporter.reportTestFailure(browser,
						"User should be able to view Drawer count details automatically after selecting business date without any confirmation.",
						"Fail");
				AbstractTest.takeSnapShot();
			}
		}
	
	
	
	
	
	
	
	
	






}
