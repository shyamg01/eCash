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
import common.Base;
import common.LoginTestData;
import common.Reporter;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.HomePage;
import eCashPageClasses.SkimsPage;

public class US436_EditingSkims extends AbstractTest
{
	
	//TC3075 : Identifying editable Skim Entries on Skim Landing Page on Cloud App.
	
	@Test()
	public void skims_US436_TC3075() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		SkimsPage skimsPage;
		AbstractTest.tcName="skims_US436_TC3075";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to skims page
		skimsPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToSkimsPage();
		// Click on View button for the uncounted status field
		skimsPage.clickOnViewButton("Uncounted");
		//verify that deposit code is showing as disabled
		if(skimsPage.Skim_PopUp_DepositCode_DD.getAttribute("disabled").equalsIgnoreCase("true") &&
				skimsPage.Skim_PopUp_Manager_DD.getAttribute("disabled").equalsIgnoreCase("true") &&
				skimsPage.Skim_PopUp_Register_DD.getAttribute("disabled").equalsIgnoreCase("true") &&
				skimsPage.Skim_PopUp_Time_TB.getAttribute("disabled").equalsIgnoreCase("true") &&
				skimsPage.Skim_PopUp_EnvelopID_TB.getAttribute("disabled").equalsIgnoreCase("true") &&
				skimsPage.Skim_PopUp_Time_TB.getAttribute("disabled").equalsIgnoreCase("true"))
		{
			Reporter.reportPassResult(
					browser,
					"User should not be able to edit any of the fields on Cloud App.",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,
					"User should not be able to edit any of the fields on Cloud App.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	}
	
	
	//TC3076 : Verifying that user is not able to select/Change existing deposit code on Cloud App..
	
		@Test()
		public void skims_US436_TC3076() throws InterruptedException,
				RowsExceededException, BiffException, WriteException, IOException, ParseException {
			/** Variable Section : **/
			SkimsPage skimsPage;
			AbstractTest.tcName="skims_US436_TC3075";
			String password = LoginTestData.level1_SSO_Password;
			String userId = LoginTestData.level1_SSO_UserId;
			String storeId = LoginTestData.level1StoreId;
			/***********************************/
			HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			// Navigate to skims page
			skimsPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
					.goToSkimsPage();
			// Click on View button for the uncounted status field
			skimsPage.clickOnViewButton("Uncounted");
			//verify that deposit code is showing as disabled
			if(skimsPage.Skim_PopUp_DepositCode_DD.getAttribute("disabled").equalsIgnoreCase("true"))
			{
				Reporter.reportPassResult(
						browser,
						"User should not be able to select/change deposit code",
						"Pass");
			}
			else
			{
				Reporter.reportTestFailure(
						browser,
						"User should not be able to select/change deposit code",
						"Fail");
				AbstractTest.takeSnapShot();
			}
		}
	
	
	
		//TC3077 : Verifying that shift manager is able to view Skims records available on Skims Details Screen as view only on Cloud App.
		
			@Test()
			public void skims_US436_TC3077() throws InterruptedException,
					RowsExceededException, BiffException, WriteException, IOException, ParseException {
				/** Variable Section : **/
				SkimsPage skimsPage;
				AbstractTest.tcName="skims_US436_TC3077";
				String password = LoginTestData.level1_SSO_Password;
				String userId = LoginTestData.level1_SSO_UserId;
				String storeId = LoginTestData.level1StoreId;
				/***********************************/
				HomePage homePage = PageFactory.initElements(driver, HomePage.class);
				// Navigate to skims page
				skimsPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
						.goToSkimsPage();
				Thread.sleep(2000);
				List <WebElement> e=driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[8]/span[text()='Uncounted' or text()='Counted' ]"));
				List <WebElement> e1=driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[8]/span[text()='Uncounted' or text()='Counted' ]/../preceding-sibling::td[6]/span"));
				System.out.println(e.size());
				//table[@id='skims_table']/tbody/tr/td[8]/span[text()='Uncounted' or text()='Counted' ] /../preceding-sibling::td[6]/span
				for(int i=0;i<e.size();i++)
				{
					System.out.println("e.get("+i+").getText() "+e.get(i).getText());
					System.out.println("t "+e1.get(i).getText());
					if(e.get(i).getText().equalsIgnoreCase("Uncounted") && e1.get(i).getText().equalsIgnoreCase("") && i==e.size()-1)
					{
						Reporter.reportPassResult(
								browser,
								"User Should able to view the entries",
								"Pass");
						break;

					}
					else if(e.get(i).getText().equalsIgnoreCase("Counted") && e1.get(i).getText().length()!=0 && i==e.size()-1)
					{
						Reporter.reportPassResult(
								browser,
								"User Should able to view the entries",
								"Pass");
						break;

					}
					else if(e.get(i).getText().equalsIgnoreCase("Uncounted") && e1.get(i).getText().equalsIgnoreCase("") )
					{
						continue;
					}
					else if(e.get(i).getText().equalsIgnoreCase("Counted") && e1.get(i).getText().length()!=0 )
					{
						continue;

					} 
					else
					{
						Reporter.reportTestFailure(
								browser,
								"User Should able to view the entries",
								"Fail");
						AbstractTest.takeSnapShot();
						break;
					}
				}
			}
		
	
	
	
	
	
	
	
	

}
