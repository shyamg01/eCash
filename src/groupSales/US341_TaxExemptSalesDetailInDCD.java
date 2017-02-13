package groupSales;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.Base;
import common.GenericMethods;
import common.LoginTestData;
import common.Reporter;
import eCashPageClasses.AbstractTest;
import eCashPageClasses.GroupSalesPage;
import eCashPageClasses.HomePage;

public class US341_TaxExemptSalesDetailInDCD  extends AbstractTest

{
	
	//TC1329 : Verify that All Tax Exempt Sales details are editable in the cloud once a deposit is Finalized.
	
	
	@Test()
	public void groupSales_US341_TC1329() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		GroupSalesPage groupSalesPage;
		AbstractTest.tcName="groupSales_US341_TC1329";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Group sales page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		Thread.sleep(2000);
		//Verify that only the records with deposit status validated can be edit
		List <WebElement> element =driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[17]"));
		for(int i=0;i<element.size();i++)
		{
			String depositStatus=null;
			depositStatus=element.get(i).getText();
			System.out.println("depositStatus "+depositStatus);
			if((depositStatus.equalsIgnoreCase("Validated")) || (depositStatus.equalsIgnoreCase("Banked")) && i==(element.size()-1))
			{
				String buttonName=null;
				buttonName=driver.findElement(By.xpath("//table[@id='tax_exempt_table']/tbody/tr["+(i+1)+"]/td[18]/eb-button/button")).getAttribute("Value");
				System.out.println("buttonName "+buttonName);
				if(buttonName.equalsIgnoreCase("Edit"))
				{
					Reporter.reportPassResult(
							browser,
							"The record should display with proper Edit or View button",
							"Pass");
					break;
				}
				else
				{
					Reporter.reportTestFailure(
							browser,
							"The record should display with proper Edit or View button",
							"Fail");
					AbstractTest.takeSnapShot();

					break;
				}
			}
			else if((depositStatus.equalsIgnoreCase("Validated")) || (depositStatus.equalsIgnoreCase("Banked")))
			{
				String buttonName=null;
				buttonName=driver.findElement(By.xpath("//table[@id='tax_exempt_table']/tbody/tr["+(i+1)+"]/td[18]/eb-button/button")).getAttribute("Value");
				System.out.println("buttonName "+buttonName);
				if(buttonName.equalsIgnoreCase("Edit"))
				{
					continue;
				}
				else
				{
					Reporter.reportTestFailure(
							browser,
							"The record should display with proper Edit or View button",
							"Fail");
					AbstractTest.takeSnapShot();

					break;
				}
			}
			if((!(depositStatus.equalsIgnoreCase("Validated")) || (depositStatus.equalsIgnoreCase("Banked")))  && i==(element.size()-1))
			{
				String buttonName=null;
				buttonName=driver.findElement(By.xpath("//table[@id='tax_exempt_table']/tbody/tr["+(i+1)+"]/td[18]/eb-button/button")).getAttribute("Value");
				System.out.println("buttonName "+buttonName);
				if(buttonName.equalsIgnoreCase("View"))
				{
					Reporter.reportPassResult(
							browser,
							"The record should display with proper Edit or View button",
							"Pass");
					break;
				}
				else
				{
					Reporter.reportTestFailure(
							browser,
							"The record should display with proper Edit or View button",
							"Fail");
					AbstractTest.takeSnapShot();

					break;
				}
			}
			else
			{
				String buttonName=null;
				buttonName=driver.findElement(By.xpath("//table[@id='tax_exempt_table']/tbody/tr["+(i+1)+"]/td[18]/eb-button/button")).getAttribute("Value");
				System.out.println("buttonName "+buttonName);
				if(buttonName.equalsIgnoreCase("View"))
				{
					continue;
				}
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
