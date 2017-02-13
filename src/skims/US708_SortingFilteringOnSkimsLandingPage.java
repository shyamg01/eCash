package skims;

import java.io.IOException;
import java.text.ParseException;


import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.Test;

import common.Base;
import common.GenericMethods;
import common.LoginTestData;
import common.Reporter;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.HomePage;
import eCashPageClasses.SkimsPage;

public class US708_SortingFilteringOnSkimsLandingPage extends AbstractTest
{
	
	
	//TC2977 : Verifying that user is able Sort entries on Skims Landing Page on Cloud App.
	
	@Test()
	public void skims_US708_TC2977() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US708_TC2977";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToSkimsPage();
		
		// Verify that columns are displaying
		if (Base.isElementDisplayed(skimsPage.SkimsTime_Label)
				& Base.isElementDisplayed(skimsPage.DepositCode_Label)
				& Base.isElementDisplayed(skimsPage.Register_Label)
				& Base.isElementDisplayed(skimsPage.Amount_Label)
				& Base.isElementDisplayed(skimsPage.Preparer_Label)
				& Base.isElementDisplayed(skimsPage.Source_Label)
				& Base.isElementDisplayed(skimsPage.Status_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Skim Time 2.Register # 3.Amount 4.Deposit Code 5.Preparer 6.Source 7. Status columns on Skims landing page Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Skim Time 2.Register # 3.Amount 4.Deposit Code 5.Preparer 6.Source 7. Status columns on Skims landing page Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		Thread.sleep(2000);
		GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
		Thread.sleep(2000);
		boolean dateInAscendinOrder = skimsPage.verifySkimsDateInAscendingOrder();
		GenericMethods.clickOnElement(skimsPage.SkimsTime_Label, "SkimsTime_Label");
		Thread.sleep(2000);
		boolean dateInDecendinOrder = skimsPage.verifySkimsDateInDescendingOrder();
		if (dateInAscendinOrder & dateInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending date in skims Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending date in skims Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
		Thread.sleep(2000);
		boolean registerInAscendinOrder = skimsPage.verifySkimsDepositCodeInAscendingOrder();
		GenericMethods.clickOnElement(skimsPage.DepositCode_Label, "DepositCode_Label");
		Thread.sleep(2000);
		boolean registerInDecendinOrder = skimsPage.verifySkimsDepositCodeInDescendingOrder();
		if (registerInAscendinOrder & registerInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending Deposit Code value in skims Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending Deposit Code value in skims Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
		Thread.sleep(2000);
		boolean amountInAscendinOrder = skimsPage.verifySkimsRegisterInAscendingOrder();
		GenericMethods.clickOnElement(skimsPage.Register_Label, "Register_Label");
		Thread.sleep(2000);
		boolean amountInDecendinOrder = skimsPage.verifySkimsRegisterInDescendingOrder();
		if (amountInAscendinOrder & amountInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending Register value in Skims Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending Register value in Skims Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
		Thread.sleep(2000);
		boolean userInAscendinOrder = skimsPage.verifySkimsAmountInAscendingOrder();
		GenericMethods.clickOnElement(skimsPage.Amount_Label, "Amount_Label");
		Thread.sleep(2000);
		boolean userInDecendinOrder = skimsPage.verifySkimsAmountInDescendingOrder();
		if (userInAscendinOrder & userInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending Amout value in  Skims Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending Amout value in  Skims Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
		Thread.sleep(2000);
		boolean organizationInAscendinOrder = skimsPage.verifySkimsPreparerInAscendingOrder();
		GenericMethods.clickOnElement(skimsPage.Preparer_Label, "Preparer_Label");
		Thread.sleep(2000);
		boolean organizationInDecendinOrder = skimsPage.verifySkimsPreparerInDescendingOrder();
		if (organizationInAscendinOrder & organizationInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending Preparer value in skims  Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending Preparer value in skims  Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
		Thread.sleep(2000);
		boolean taxIdInAscendinOrder = skimsPage.verifySkimsSourceInAscendingOrder();
		GenericMethods.clickOnElement(skimsPage.Source_Label, "Source_Label");
		Thread.sleep(2000);
		boolean taxIdInDecendinOrder = skimsPage.verifySkimsSourceInDescendingOrder();
		if (taxIdInAscendinOrder & taxIdInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending Source value in Skims Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending Source value in Skims Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
		Thread.sleep(2000);
		boolean depositStatusInAscendinOrder = skimsPage.verifySkimsStatusInAscendingOrder();
		GenericMethods.clickOnElement(skimsPage.Status_Label, "Status_Label");
		Thread.sleep(2000);
		boolean depositStatusInDecendinOrder = skimsPage.verifySkimsStatusInDescendingOrder();
		if (depositStatusInAscendinOrder & depositStatusInDecendinOrder) {
			Reporter.reportPassResult(
					browser,
					"user should be able to toggle between ascending and descending status value in skims  Landing Page",
					"Pass");
			
		} else {
			Reporter.reportTestFailure(
					browser,
					"user should be able to toggle between ascending and descending status value in skims  Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		
	}
	
	
	//TC 2978 : 	Validating the columns on Skims Landing Page on Cloud App.
	
	
	
	
	@Test()
	public void skims_US708_TC2978() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US708_TC2978";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToSkimsPage();
		
		// Verify that columns are displaying
		if (Base.isElementDisplayed(skimsPage.SkimsTime_Label)
				& Base.isElementDisplayed(skimsPage.DepositCode_Label)
				& Base.isElementDisplayed(skimsPage.Register_Label)
				& Base.isElementDisplayed(skimsPage.Amount_Label)
				& Base.isElementDisplayed(skimsPage.Preparer_Label)
				& Base.isElementDisplayed(skimsPage.Source_Label)
				& Base.isElementDisplayed(skimsPage.Status_Label)) {
			Reporter.reportPassResult(browser,
					"User should be able to view 1. Skim Time 2.Register # 3.Amount 4.Deposit Code 5.Preparer 6.Source 7. Status columns on Skims landing page Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(browser,
					"User should be able to view 1. Skim Time 2.Register # 3.Amount 4.Deposit Code 5.Preparer 6.Source 7. Status columns on Skims landing page Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		
		
	}
	
	
	
	
	
	
	

}
