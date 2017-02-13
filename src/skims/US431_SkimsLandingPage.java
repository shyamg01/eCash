package skims;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.Base;
import common.GenericMethods;
import common.GlobalVariable;
import common.LoginTestData;
import common.Reporter;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.HomePage;
import eCashPageClasses.SkimsPage;

public class US431_SkimsLandingPage extends AbstractTest
{
		
	
	//TC2903: Verify the Pathway to navigate to Skims Landing Page from Main Menu in the cloud application.
	@Test()
	public void skims_US431_TC2903() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US431_TC2903";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToSkimsPage();
		if(Base.isElementDisplayed(skimsPage.Skims_Label)){
			Reporter.reportPassResult(
					browser,
					"User should be able to navigate to skims Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to navigate to skims Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC2904: Validating the value for default Default Business Date field on Skim Landing Page on the Cloud app
	@Test()
	public void skims_US431_TC2904() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US431_TC2903";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToSkimsPage();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//Verify user is not able to select more than current date for end date
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 0);
		String todayDate = dateFormat.format(cal1.getTime());
		if(skimsPage.Skims_Date_TB.getAttribute("value").equals(todayDate)){
			Reporter.reportPassResult(
					browser,
					"Current Business Date should appear as default date on Skims business date field.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Current Business Date should appear as default date on Skims business date field.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC2906: Verifying that the user is able to view the following fields are displayed on Skims Landing Page on Cloud app.
	@Test()
	public void skims_US431_TC2906() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException,ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName = "skims_US431_TC2906";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToSkimsPage();
		if(Base.isElementDisplayed(skimsPage.SkimsTime_Label)
				& Base.isElementDisplayed(skimsPage.DepositCode_Label)
				& Base.isElementDisplayed(skimsPage.Register_Label)
				& Base.isElementDisplayed(skimsPage.Amount_Label)
				& Base.isElementDisplayed(skimsPage.Preparer_Label)
				& Base.isElementDisplayed(skimsPage.Source_Label)
				& Base.isElementDisplayed(skimsPage.Status_Label)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view columns Register # ,Skim Time, Preparer, Skim Amount, Deposit code, Source, Status in  skims Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view columns Register # ,Skim Time, Preparer, Skim Amount, Deposit code, Source, Status in  skims Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC2907: Verifying that user is able to go back 60 days within the calendar view and after selecting any date in this range, result appears in 'View Only' format in cloud app.
	@Test()
	public void skims_US431_TC2907() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException,ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName = "skims_US431_TC2907";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToSkimsPage();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//Verify user is not able to select more than current date for end date
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -61);
		String invalidBackDate = dateFormat.format(cal1.getTime());
		System.out.println("60 days back "+invalidBackDate);
		skimsPage.selectSkimsDate(invalidBackDate);
		if (skimsPage.verifySkimsDateIsDisabled(invalidBackDate)) {
			Reporter.reportPassResult(
					browser,
					"System should not allow user to select date more than the date selected in End Date.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"System should not allow user to select date more than the date selected in End Date.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		//get yesterday date and select end date as yesterday date
		GenericMethods.clickOnElement(skimsPage.Skims_Date_TB, "Skims_Date_TB");
		Thread.sleep(1000);
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -1);
		String yesterdayDate = dateFormat.format(cal2.getTime());
		System.out.println("yesterdayDate "+yesterdayDate);
		skimsPage.selectSkimsDate(yesterdayDate);
		//Verify user is not able to select today date for start date when end date is already selected as yesterday date
		if(skimsPage.Skims_Date_TB.getAttribute("value").equals(yesterdayDate)){
			Reporter.reportPassResult(
					browser,
					"System should allow user to select yesterday date",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"System should allow user to select yesterday date",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		Calendar cal3 = Calendar.getInstance();
		cal3.add(Calendar.DATE, -30);
		String validBackDate = dateFormat.format(cal3.getTime());
		System.out.println("validBackDate "+validBackDate);
		skimsPage.selectSkimsDate(validBackDate);
		//Verify user is not able to select today date for start date when end date is already selected as yesterday date
		if(skimsPage.Skims_Date_TB.getAttribute("value").equals(validBackDate)){
			Reporter.reportPassResult(
					browser,
					"System should allow user to select 30 days back date",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"System should allow user to select 30 days back date",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		if(Base.isElementDisplayed(skimsPage.Skims_CountedRecord_View_BT)){
			if(skimsPage.Skims_CountedRecord_View_BT.getText().equals("View")){
				Reporter.reportPassResult(
						browser,
						"Skim search results for a selected date should be view only",
						"Pass");
			} else {
				Reporter.reportTestFailure(
						browser,
						"Skim search results for a selected date should be view only",
						"Fail");
				AbstractTest.takeSnapShot();
			}
		}
	}
	
	//TC2908: Verifying that the user is not able to select future dates on the Skims landing page on cloud app.
	@Test()
	public void skims_US431_TC2908() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException,ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName = "skims_US431_TC2908";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToSkimsPage();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//Verify user is not able to select more than current date for end date
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 1);
		String tomorrowDate = dateFormat.format(cal1.getTime());
		System.out.println("tomorrowDate "+tomorrowDate);
		skimsPage.selectSkimsDate(tomorrowDate);
		if (skimsPage.verifySkimsDateIsDisabled(tomorrowDate)) {
			Reporter.reportPassResult(
					browser,
					"System should not allow user to select date future date",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"System should not allow user to select date future date",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC2909: Verifying that the user is able to identify the store number on the skims landing page on the Cloud app.
	@Test()
	public void skims_US431_TC2909() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException,ParseException {
		/** Variable Section : **/
		AbstractTest.tcName = "skims_US431_TC2909";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToSkimsPage();
		if (Base.isElementDisplayed(homePage.SelectedLocation_Label)) {
			Reporter.reportPassResult(
					browser,
					"User should be able to view selected store number next to the user icon.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view selected store number next to the user icon.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC2910: Verifying that the User confirmation not required to view skims after selecting desired business date on the Cloud app.
	@Test()
	public void skims_US431_TC2910() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException,ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName = "skims_US431_TC2910";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		String date = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToSkimsPage();
		skimsPage.selectSkimsDate(date).expandRolledUpSkimsRecords();
		if (skimsPage.Skims_AllRecord_List.size()>0) {
			Reporter.reportPassResult(
					browser,
					"User should be able to view Skims details automatically after selecting business date without any confirmation.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view Skims details automatically after selecting business date without any confirmation.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	//TC2911: Verifying that the user is able to select date by using Calendar function on the Skims Landing Page on Cloud app.
	@Test()
	public void skims_US431_TC2911() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException,ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName = "skims_US431_TC2911";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		String date = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password)
				.selectLocation(storeId).goToSkimsPage();
		skimsPage.selectSkimsDate(date).expandRolledUpSkimsRecords();
		if (skimsPage.Skims_Date_TB.getAttribute("value").equals(date)
				& skimsPage.Skims_AllRecord_List.size()>0) {
			Reporter.reportPassResult(
					browser,
					"User should be able to view Skims details automatically after selecting business date without any confirmation.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view Skims details automatically after selecting business date without any confirmation.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}

}
