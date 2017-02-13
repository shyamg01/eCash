package safeCount;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import common.LoginTestData;
import common.Reporter;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.HomePage;
import eCashPageClasses.SafeCountPage;


public class US259_SafeCountLandingPage extends AbstractTest
{
	//TC3639 : Verify that user is on Safe Count Landing Page and under which Store Number the user is currently operating on cloud.
	
	@Test()
	public void safeCount_US259_TC3639() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SafeCountPage safeCountPage;
		AbstractTest.tcName="safeCount_US259_TC3639";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		safeCountPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToSafeCountPage();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//Verify user is not able to select more than current date for end date
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 0);
		String todayDate = dateFormat.format(cal1.getTime());
		System.out.println("todayDate "+todayDate);
		System.out.println("safeCountPage.SafeCount_Date_TB.getAttribute"+safeCountPage.SafeCount_Date_TB.getAttribute("value"));
		if(safeCountPage.SafeCount_Date_TB.getAttribute("value").equals(todayDate) && Base.isElementDisplayed(homePage.SelectedLocation_Label)){
			Reporter.reportPassResult(
					browser,
					"Current Business Date should appear as default date business date field and default Store ID  on Safe Count landing Page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"Current Business Date should appear as default date business date field and default Store ID  on Safe Count landing Page.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	
	//TC3640 : Verify the sort method by the columns on the Safe Count Landing page on Cloud.
	
	
	@Test()
	public void safeCount_US259_TC3640() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SafeCountPage safeCountPage;
		AbstractTest.tcName="safeCount_US259_TC3639";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		safeCountPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToSafeCountPage();
		
		// Verify that columns are displaying
		if (Base.isElementDisplayed(safeCountPage.Created_Label)
				& Base.isElementDisplayed(safeCountPage.CreatedBY_Label)
				& Base.isElementDisplayed(safeCountPage.PettyCash_Label)
				& Base.isElementDisplayed(safeCountPage.GiftCertificate_Label)
				& Base.isElementDisplayed(safeCountPage.TreatBook_Label)
				& Base.isElementDisplayed(safeCountPage.SafeCash_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Created 2.Created By 3.Petty Cash 4.Gift Certificate 5.Treat Book 6.Safe Cash Safe count landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Created 2.Created By 3.Petty Cash 4.Gift Certificate 5.Treat Book 6.Safe Cash Safe count landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		Thread.sleep(2000);
		GenericMethods.clickOnElement(safeCountPage.SafeCount_Label, "SafeCount_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = safeCountPage.verifySafeCountCreatedDateInAscendingOrder();
		GenericMethods.clickOnElement(safeCountPage.SafeCount_Label, "SafeCount_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = safeCountPage.verifySafeCountCreatedDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in safe Count Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in safe Count Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(safeCountPage.SafeCash_Label, "SafeCash_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = safeCountPage.verifySafeCashInAscendingOrder();
		GenericMethods.clickOnElement(safeCountPage.SafeCash_Label, "SafeCash_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = safeCountPage.verifySafeCashInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending Safe Cash value in Safe count Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending Safe Cash value in Safe count Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(safeCountPage.PettyCash_Label, "PettyCash_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = safeCountPage.verifyPettyCashInAscendingOrder();
		GenericMethods.clickOnElement(safeCountPage.PettyCash_Label, "PettyCash_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = safeCountPage.verifyPettyCashInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending Petty Cash value in Safe Count Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending Petty Cash value in Safe Count Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(safeCountPage.GiftCertificate_Label, "GiftCertificate_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = safeCountPage.verifyGiftCertificateInAscendingOrder();
		GenericMethods.clickOnElement(safeCountPage.GiftCertificate_Label, "GiftCertificate_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = safeCountPage.verifyGiftCertificateInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending Gift Certificate value in  Safe count Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending Gift Certificate value in  Safe count Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(safeCountPage.TreatBook_Label, "TreatBook_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = safeCountPage.verifyTreatBookInAscendingOrder();
		GenericMethods.clickOnElement(safeCountPage.TreatBook_Label, "TreatBook_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = safeCountPage.verifyTreatBookInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending Preparer value of Treat book in Safe Count Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending Preparer value of Treat book in Safe Count Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(safeCountPage.CreatedBY_Label, "CreatedBY_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = safeCountPage.verifyCreatedByInAscendingOrder();
		GenericMethods.clickOnElement(safeCountPage.CreatedBY_Label, "CreatedBY_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = safeCountPage.verifyCreatedByInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending Created By  value in Safe Count Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending Created By  value in Safe Count Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		
		
	}
	
	
	//TC3648 : Verify the fields of Safe Count Landing Page on Cloud App.
	
	
	@Test()
	public void safeCount_US259_TC3648() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SafeCountPage safeCountPage;
		AbstractTest.tcName="safeCount_US259_TC3648";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		safeCountPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToSafeCountPage();
		
		// Verify that columns are displaying
		if (Base.isElementDisplayed(safeCountPage.Created_Label)
				& Base.isElementDisplayed(safeCountPage.CreatedBY_Label)
				& Base.isElementDisplayed(safeCountPage.PettyCash_Label)
				& Base.isElementDisplayed(safeCountPage.GiftCertificate_Label)
				& Base.isElementDisplayed(safeCountPage.TreatBook_Label)
				& Base.isElementDisplayed(safeCountPage.SafeCash_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Created 2.Created By 3.Petty Cash 4.Gift Certificate 5.Treat Book 6.Safe Cash Safe count landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Created 2.Created By 3.Petty Cash 4.Gift Certificate 5.Treat Book 6.Safe Cash Safe count landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
	}
	
	
	//TC3659 : 	Verify the filter method by only "Created by" column on the Safe Count Landing page on cloud.
	
	
		@Test()
		public void safeCount_US259_TC3659() throws InterruptedException,
				RowsExceededException, BiffException, WriteException, IOException, ParseException {
			/** Variable Section : **/
			SafeCountPage safeCountPage;
			AbstractTest.tcName="safeCount_US259_TC3659";
			String password = LoginTestData.level1_SSO_Password;
			String userId = LoginTestData.level1_SSO_UserId;
			String storeId = LoginTestData.level1StoreId;
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to skims page
			safeCountPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
					.goToSafeCountPage();
			
			//Verify that default value of Created by filter is 'All'
			
			if(safeCountPage.CreatedBY_DD.getAttribute("value").equalsIgnoreCase("All"))
			{
				Reporter.reportPassResult(browser,
						"Default value for the created by field should be 'All'",
						"Pass");
			}
			else
			{
				Reporter.reportTestFailure(browser,
						"Default value for the created by field should be 'All'",
						"Fail");
				AbstractTest.takeSnapShot();
			}
			
			Select select =new Select(safeCountPage.CreatedBY_DD);
			select.selectByIndex(1);
			Thread.sleep(2000);
			String expCreatdBY=select.getFirstSelectedOption().getText().trim();
			System.out.println("expCreatdBY "+expCreatdBY);
			List <WebElement> actCreatedBy=driver.findElements(By.xpath("//table[@id='safe_count_table']/tbody/tr/td[8]/span"));
			for(int i=1;i<actCreatedBy.size();i++)
			{
				System.out.println(actCreatedBy.get(i).getText());
				if(actCreatedBy.get(i).getText().equalsIgnoreCase(expCreatdBY) && i==actCreatedBy.size()-1)
				{
					System.out.println("Pass");
					break;
				}
				else if(actCreatedBy.get(i).getText().equalsIgnoreCase(expCreatdBY))
				{
					continue;
				}
				else
				{
					System.out.println("Fail");
					break;
				}
			}
			
			
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



}
