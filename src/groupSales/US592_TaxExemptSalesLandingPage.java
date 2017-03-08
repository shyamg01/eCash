package groupSales;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import eCashPageClasses.AbstractTest;
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
import eCashPageClasses.GroupSalesPage;
import eCashPageClasses.HomePage;

public class US592_TaxExemptSalesLandingPage extends AbstractTest
{
	
	//TC3169 : 	Verifying that user is able to view the tax exempt sale entries for the selected date range(e.g. 90 days, 6 months, 9 months , 12 months)  on Tax Exempt Sales landing page on cloud app
	
	@Test()
	public void groupSales_US592_TC3169() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException, ParseException {
		/** Variable Section : **/
		AbstractTest.tcName = "groupSales_US592_TC3169";
		String password = LoginTestData.level1_SSO_Password;
		String userId = LoginTestData.level1_SSO_UserId;
		String storeId = LoginTestData.level1StoreId;
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		GroupSalesPage groupSalesPage = PageFactory.initElements(driver, GroupSalesPage.class);
		// Navigate to Group Sales Page
		groupSalesPage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.goToGroupSalesPage();
		Thread.sleep(3000);
		Select select = new Select(groupSalesPage.TextExempt_DateRange_DD);
		select.selectByVisibleText("Custom Date Range");
		groupSalesPage.selectStartdate(startDate).selectEndDate(endDate).TaxExemptSales_ShowResults_BT.click();
		Thread.sleep(3000);
		if(groupSalesPage.verifyTaxExemptSalesDisplayedForSelectedDateRange(startDate, endDate)){
			Reporter.reportPassResult(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the records within custom date range in Tax exempt Sales Landing Page",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		select.selectByVisibleText("3 Months");
		Thread.sleep(2000);
		//click on Show Result button
		GenericMethods.clickOnElement(groupSalesPage.TaxEximptSales_ShowResult_BT, "groupSalesPage.TaxEximptSales_ShowResult_BT");
		Thread.sleep(2000);
		
		
		String startDate_01=groupSalesPage.TaxExempt_StartDate_TB.getAttribute("value");
		System.out.println("startDate_01 "+startDate_01);
		String arr1[]=startDate_01.split("/");
		System.out.println("Base.getCorrectMonthFromDate(startDate_01) "+Base.getCorrectMonthFromDate(startDate_01));
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
		String endDate_01=groupSalesPage.TaxExempt_EndDate_TB.getAttribute("value");
		System.out.println("endDate_01"+endDate_01);
		
		List <WebElement> element =driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[2]/span"));
		
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
		GenericMethods.clickOnElement(groupSalesPage.TaxEximptSales_ShowResult_BT, "groupSalesPage.TaxEximptSales_ShowResult_BT");
		Thread.sleep(2000);
				
		String startDate_02=groupSalesPage.TaxExempt_StartDate_TB.getAttribute("value");
		System.out.println("startDate_01 "+startDate_02);
		String arr2[]=startDate_02.split("/");
		System.out.println("Base.getCorrectMonthFromDate(startDate_01) "+Base.getCorrectMonthFromDate(startDate_02));
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
		String endDate_02=groupSalesPage.TaxExempt_EndDate_TB.getAttribute("value");
		System.out.println("endDate_01"+endDate_02);
		
		List <WebElement> element1 =driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[2]/span"));
		
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
		GenericMethods.clickOnElement(groupSalesPage.TaxEximptSales_ShowResult_BT, "groupSalesPage.TaxEximptSales_ShowResult_BT");
		Thread.sleep(2000);
		String startDate_03=groupSalesPage.TaxExempt_StartDate_TB.getAttribute("value");
		System.out.println("startDate_03 "+startDate_03);
		String arr3[]=startDate_03.split("/");
		System.out.println("Base.getCorrectMonthFromDate(startDate_01) "+Base.getCorrectMonthFromDate(startDate_03));
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
		String endDate_03=groupSalesPage.TaxExempt_EndDate_TB.getAttribute("value");
		System.out.println("endDate_01"+endDate_03);
		List <WebElement> element2 =driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[2]/span"));
		
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
		GenericMethods.clickOnElement(groupSalesPage.TaxEximptSales_ShowResult_BT, "groupSalesPage.TaxEximptSales_ShowResult_BT");
		Thread.sleep(2000);
		String startDate_04=groupSalesPage.TaxExempt_StartDate_TB.getAttribute("value");
		System.out.println("startDate_01 "+startDate_04);
		String arr4[]=startDate_04.split("/");
		System.out.println("Base.getCorrectMonthFromDate(startDate_01) "+Base.getCorrectMonthFromDate(startDate_04));
		int day3=(Base.getCorrectMonthFromDate(startDate_04));
		System.out.println("day3 "+day3);
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
		String endDate_04=groupSalesPage.TaxExempt_EndDate_TB.getAttribute("value");
		System.out.println("endDate_01"+endDate_04);
		List <WebElement> element3 =driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[2]/span"));
		
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
	
//TC3171 : 	Verifying user is able to view the total of Tax Exempt Sales for the day on Tax Exempt Sales Detail Landing page on cloud app.
	
	
	@Test()
	public void groupSales_US592_TC3171() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/** Variable Section : **/
		AbstractTest.tcName = "groupSales_US592_TC3171";
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
		Select select =new Select(groupSalesPage.TextExempt_DateRange_DD);
		select.selectByVisibleText("3 Months");
		Thread.sleep(2000);
		//click on Show Result button
		GenericMethods.clickOnElement(groupSalesPage.TaxEximptSales_ShowResult_BT, "groupSalesPage.TaxEximptSales_ShowResult_BT");
		Thread.sleep(2000);
			
		List <WebElement> element =driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[4]/span"));
		
		int numberOfElement=element.size();
		float[] expAmount = new float[numberOfElement];
		System.out.println("numberOfElement "+numberOfElement);
		for(int i=0;i<numberOfElement;i++)
		{
			System.out.println("i is "+i);
			System.out.println("element.get(i).getText()"+element.get(i).getText());
			float amount=Float.parseFloat(element.get(i).getText().replace("$", ""));
			System.out.println("amount "+amount);
			expAmount[i]=amount;
			
		}
		
		String expTotalAmount=null;
		float sum=0;
		for(int i=0;i<expAmount.length;i++)
		{
		
			 sum=sum+expAmount[i];
		}
		  BigDecimal bd = new BigDecimal(Float.toString(sum));
	      bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
	     expTotalAmount= "$"+String.valueOf(bd);
		
		System.out.println("expTotalAmount "+expTotalAmount);
		String actamount=groupSalesPage.Total_Value.getText().replace(",", "");
		System.out.println("actamount "+actamount);
		if(actamount.equalsIgnoreCase(expTotalAmount))
		{
			Reporter.reportPassResult(
					browser,
					"User should be able to view the updated Total of Tax Exempt Sales field on Tax Exempt Sales Landing page as per the date range selected for 90 days.",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the updated Total of Tax Exempt Sales field on Tax Exempt Sales Landing page as per the date range selected for 90 days.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
	
		//Six Month
		
		select.selectByVisibleText("6 months");
		Thread.sleep(2000);
		//click on Show Result button
		GenericMethods.clickOnElement(groupSalesPage.TaxEximptSales_ShowResult_BT, "groupSalesPage.TaxEximptSales_ShowResult_BT");
		Thread.sleep(2000);
			
		List <WebElement> element_01 =driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[4]/span"));
		
		int numberOfElement_01=element_01.size();
		float[] expAmount_01 = new float[numberOfElement_01];
		System.out.println("numberOfElement "+numberOfElement_01);
		for(int i=0;i<numberOfElement_01;i++)
		{
			System.out.println("i is "+i);
			System.out.println("element.get(i).getText()"+element_01.get(i).getText());
			float amount_01=Float.parseFloat(element_01.get(i).getText().replace("$", ""));
			System.out.println("amount "+amount_01);
			expAmount_01[i]=amount_01;
			
		}
		
		String expTotalAmount_01=null;
		float sum_01=0;
		for(int i=0;i<expAmount_01.length;i++)
		{
		
			 sum_01=sum_01+expAmount_01[i];
		}
		BigDecimal bd1 = new BigDecimal(Float.toString(sum_01));
	      bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
	     expTotalAmount_01= "$"+String.valueOf(bd1);
		
		System.out.println("expTotalAmount_01 "+expTotalAmount_01);
		System.out.println("actamount_01 "+groupSalesPage.Total_Value.getText());

		if(groupSalesPage.Total_Value.getText().replace(",", "").equalsIgnoreCase(expTotalAmount_01))
		{
			Reporter.reportPassResult(
					browser,
					"User should be able to view the updated Total of Tax Exempt Sales field on Tax Exempt Sales Landing page as per the date range selected for 180 days.",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the updated Total of Tax Exempt Sales field on Tax Exempt Sales Landing page as per the date range selected for 180 days.",
					"Fail");
			AbstractTest.takeSnapShot();
		}
		
		
		//Nine  Month

		select.selectByVisibleText("9 months");
		Thread.sleep(2000);
		//click on Show Result button
		GenericMethods.clickOnElement(groupSalesPage.TaxEximptSales_ShowResult_BT, "groupSalesPage.TaxEximptSales_ShowResult_BT");
		Thread.sleep(2000);

		List <WebElement> element_02 =driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[4]/span"));

		int numberOfElement_02=element_02.size();
		float[] expAmount_02 = new float[numberOfElement_02];
		System.out.println("numberOfElement "+numberOfElement_02);
		for(int i=0;i<numberOfElement_02;i++)
		{
			System.out.println("i is "+i);
			System.out.println("element.get(i).getText()"+element_02.get(i).getText());
			float amount_02=Float.parseFloat(element_02.get(i).getText().replace("$", ""));
			System.out.println("amount "+amount_02);
			expAmount_02[i]=amount_02;

		}

		String expTotalAmount_02=null;
		float sum_02=0;
		for(int i=0;i<expAmount_02.length;i++)
		{

			sum_02=sum_02+expAmount_02[i];
		}
		BigDecimal bd2 = new BigDecimal(Float.toString(sum_02));
	      bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
	     expTotalAmount_02= "$"+String.valueOf(bd2);
		
		System.out.println("expTotalAmount_02 "+expTotalAmount_02);
		System.out.println("actamount_02 "+groupSalesPage.Total_Value.getText());
		if(groupSalesPage.Total_Value.getText().replace(",", "").equalsIgnoreCase(expTotalAmount_02))
		{
			Reporter.reportPassResult(
					browser,
					"User should be able to view the updated Total of Tax Exempt Sales field on Tax Exempt Sales Landing page as per the date range selected for 270 days.",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the updated Total of Tax Exempt Sales field on Tax Exempt Sales Landing page as per the date range selected for 270 days.",
					"Fail");
			AbstractTest.takeSnapShot();
		}

	
		
		
		

		//Nine  Month

		select.selectByVisibleText("12 months");
		Thread.sleep(2000);
		//click on Show Result button
		GenericMethods.clickOnElement(groupSalesPage.TaxEximptSales_ShowResult_BT, "groupSalesPage.TaxEximptSales_ShowResult_BT");
		Thread.sleep(2000);

		List <WebElement> element_03 =driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[4]/span"));

		int numberOfElement_03=element_03.size();
		float[] expAmount_03 = new float[numberOfElement_03];
		System.out.println("numberOfElement "+numberOfElement_03);
		for(int i=0;i<numberOfElement_03;i++)
		{
			System.out.println("i is "+i);
			System.out.println("element.get(i).getText()"+element_03.get(i).getText());
			float amount_03=Float.parseFloat(element_03.get(i).getText().replace("$", ""));
			System.out.println("amount "+amount_03);
			expAmount_03[i]=amount_03;

		}

		String expTotalAmount_03=null;
		float sum_03=0;
		for(int i=0;i<expAmount_03.length;i++)
		{

			sum_03=sum_03+expAmount_03[i];
		}
		  BigDecimal bd3 = new BigDecimal(Float.toString(sum_03));
	      bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
	      expTotalAmount_03= "$"+String.valueOf(bd3);
		
		System.out.println("expTotalAmount_03 "+expTotalAmount_03);
		System.out.println("actamount_03 "+groupSalesPage.Total_Value.getText());
		if(groupSalesPage.Total_Value.getText().replace(",", "").equalsIgnoreCase(expTotalAmount_03))
		{
			Reporter.reportPassResult(
					browser,
					"User should be able to view the updated Total of Tax Exempt Sales field on Tax Exempt Sales Landing page as per the date range selected for 360 days.",
					"Pass");
		}
		else
		{
			Reporter.reportTestFailure(
					browser,
					"User should be able to view the updated Total of Tax Exempt Sales field on Tax Exempt Sales Landing page as per the date range selected for 360 days.",
					"Fail");
			AbstractTest.takeSnapShot();
		}

	}
	
	
	
	//TC3172 : 	Verify that Shift manager is able to view Following Fields are Displayed on Tax Exempt Sales Landing Page.
	
	
	@Test()
	public void groupSales_US592_TC3172() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		/** Variable Section : **/
		AbstractTest.tcName = "groupSales_US592_TC3172";
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
		//Verify the Following fields are displaying on the Tax Exempt Sales Page
	/*	Date and Time   (date and time of sale (POS Integration from local app))
		Register #    (POS Integration from local app)
		Amount      (POS Integration from local app)
		User    (includes user name and ID (populated from the manager conducting the DCD from which the transaction originally occurred)
		Tax ID Number    (manual entry at time of DCD)
		Organization Name   (manual entry at time of DCD)
		Deposit Status  (auto-populated based on status of deposit the tax exempt sale is associated to)*/

		
		if(GenericMethods.isElementDisplayed(groupSalesPage.TaxExemptSales_DateAndTime_Label, "groupSalesPage.TaxExemptSales_DateAndTime_Label") &&
				GenericMethods.isElementDisplayed(groupSalesPage.TaxExemptSales_Register_Label, "groupSalesPage.TaxExemptSales_Register_Label") &&
				GenericMethods.isElementDisplayed(groupSalesPage.TaxExemptSales_Amount_Label, "groupSalesPage.TaxExemptSales_Amount_Label") &&
				GenericMethods.isElementDisplayed(groupSalesPage.TaxExemptSales_OrganizationName_Label, "groupSalesPage.TaxExemptSales_OrganizationName_Label") &&
				GenericMethods.isElementDisplayed(groupSalesPage.TaxExemptSales_DepositStatus_Label, "groupSalesPage.TaxExemptSales_DepositStatus_Label") &&
				GenericMethods.isElementDisplayed(groupSalesPage.TaxExemptSales_User_Label, "groupSalesPage.TaxExemptSales_User_Label") &&
				GenericMethods.isElementDisplayed(groupSalesPage.TaxExemptSales_DateAndTime_Label, "groupSalesPage.TaxExemptSales_DateAndTime_Label")
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
