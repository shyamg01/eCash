package skims;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import common.LoginTestData;
import common.Reporter;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.HomePage;
import eCashPageClasses.SkimsPage;

public class US510_ManualEntryOfSkim extends AbstractTest
{

	//TC2237 : Verify that different types of skim entries on skims landing page, as  a shift manager can track the skims Manual generated Skims and POS generated skims as per the Skim Type
	
	
	
	@Test()
	public void skims_US510_TC2237() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US510_TC2237";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToSkimsPage();
		//Verify that only manual and POS type entries are showing in source field
		List <WebElement> e=driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[7]/span"));
		for(int i=0;i<e.size();i++)
		{
			if((e.get(i).getText().equalsIgnoreCase("POS") || e.get(i).getText().equalsIgnoreCase("Manual")) && i==e.size()-1)
			{
				Reporter.reportPassResult(
						browser,
						"User should be able to view only 'Manual' and 'POS' type entries",
						"Pass");
				break;
			}
			else if((e.get(i).getText().equalsIgnoreCase("POS") || e.get(i).getText().equalsIgnoreCase("Manual")))
			{
				continue;
			}
			else
			{
				Reporter.reportTestFailure(
						browser,
						"User should be able to view only 'Manual' and 'POS' type entries",
						"Fail");
				AbstractTest.takeSnapShot();
				break;
			}
		}
		
	}
	
	

	//TC3021 : 	Verify that Auto-populated Skims on skims landing page on Cloud application.
	
	
	
	@Test()
	public void skims_US510_TC3021() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US510_TC3021";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToSkimsPage();
		//Verify that only manual and POS type entries are showing in source field
		List <WebElement> e=driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[6]/span"));
		List <WebElement> e1=driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[1]/span"));
		for(int i=0;i<e.size();i++)
		{
			if((e.get(i).getText().length()>0 || e1.get(i).getText().length()>0 ) && i==e.size()-1)
			{
				Reporter.reportPassResult(
						browser,
						"User should be able to view mentioned fields are auto-populated fields on skims landing page.",
						"Pass");
				break;
			}
			else if((e.get(i).getText().length()>0 || e1.get(i).getText().length()>0 ) )
			{
				continue;
			}
			else
			{
				Reporter.reportTestFailure(
						browser,
						"User should be able to view mentioned fields are auto-populated fields on skims landing page.",
						"Fail");
				AbstractTest.takeSnapShot();
				break;
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
