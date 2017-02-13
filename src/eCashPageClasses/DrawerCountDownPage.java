package eCashPageClasses;

import java.io.IOException;
import java.util.List;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Base;
import common.GenericMethods;
import common.Reporter;


public class DrawerCountDownPage extends AbstractPage{
	
	public DrawerCountDownPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='dc_biz_date_picker_input']")
	public WebElement DrawerCount_Date_TB;
	
	@FindBy(xpath="//h1[text()='Drawer Count']")
	public WebElement DrawerCount_Label;
	
	@FindBy(xpath="//table[@id='drawer_counts_table']/tbody/tr")
	public List<WebElement>DrawerCountDown_Records_List;
	
	@FindBy(xpath="//table[@id='drawer_counts_table']/tbody/tr/td[3]/span[text()='Counted']/../following-sibling::td/eb-button/button")
	public WebElement DrawerCountDown_CountedRecord_View_BT;
	
	@FindBy(xpath="//table[@id='drawer_counts_table']/tbody/tr/td[3]/span[text()='Uncounted']/../following-sibling::td/eb-button/button")
	public WebElement DrawerCountDown_UncountedRecord_View_BT;
	
	@FindBy(xpath="//h2[text()='Drawer Count']")
	public WebElement DrawerCountDownPopUp_HeaderText_Value;
	
	@FindBy(xpath="//eb-button[@id='next_drawer']/button")
	public WebElement DrawerCountDownPopUp_Next_BT;
	
	@FindBy(xpath="//eb-button[@id='next_details']/button")
	public WebElement DrawerCountDownPopUp_CountCash_Next_BT;
	
	@FindBy(xpath="//div[@id='expected_cash_calc_accordion']")
	public WebElement DrawerCountDownPopUp_AdditionalInfo_ExpectedCashCalculation_DD;
	
	@FindBy(xpath="//span[@id='forv_dif_drawer']")
	public WebElement DrawerCountDownPopUp_ForeverDifference_Value;
	
	@FindBy(xpath="//span[@id='co_adj_ref_ovr_amt']")
	public WebElement DrawerCountDownPopUp_ManualRefundOverrings_Value;
	
	@FindBy(xpath="//span[@id='adj_cr_sale_amt']")
	public WebElement DrawerCountDownPopUp_BillableSales_Value;
	
	@FindBy(xpath="//span[@id='co_adj_rdm_gct_amt']")
	public WebElement DrawerCountDownPopUp_GiftCertificateRedeemed_Value;
	
	@FindBy(xpath="//span[@id='reg_skim_amt']")
	public WebElement DrawerCountDownPopUp_Skims_Value;
	
	@FindBy(xpath="//span[@id='co_tot_ovring_amt']")
	public WebElement DrawerCountDownPopUp_POSOverRings_Value;
	
	@FindBy(xpath="//span[@id='co_refund_amt']")
	public WebElement DrawerCountDownPopUp_CashRefunds_Value;
	
	@FindBy(xpath="//span[@id='cashless_gross_amt']")
	public WebElement DrawerCountDownPopUp_CashLessSales_Value;
	
	@FindBy(xpath="//span[@id='gcard_rdm_amt']")
	public WebElement DrawerCountDownPopUp_ArchCardRedeemed_Value;
	
	@FindBy(xpath="//span[@id='expected_cash_accordion']")
	public WebElement DrawerCountDownPopUp_ExpectedCash_Value;
	
	@FindBy(xpath="//span[@id='dc_counted_cash']")
	public WebElement DrawerCountDownPopUp_CountedCash_Value;
	
	@FindBy(xpath="//span[@id='fc_amount_us']")
	public WebElement DrawerCountDownPopUp_ForeignCurrency_Value;
	
	@FindBy(xpath="//span[@id='expected_cash']")
	public WebElement DrawerCountDownPopUp_ExpectedDrawerCash_Value;
	
	@FindBy(xpath="//span[@id='cash_over_short_input']")
	public WebElement DrawerCountDownPopUp_CashOverShort_Value;
	
	@FindBy(xpath="//i[@id='dc_cc_button']")
	public WebElement DrawerCountDownPopUp_CashToPull_Calender_BT;
	
	@FindBy(xpath="//input[@placeholder='quantity']")
	public List<WebElement> DrawerCountDownPopUp_CalculatorTool_Quantity_TB_List;
	
	@FindBy(xpath="//select[@id='deposit_code']")
	public WebElement DipositCode_DD;

	@FindBy(xpath="//select[@id='register']")
	public WebElement Register_DD;
	
	@FindBy(xpath="//select[@id='status']")
	public WebElement Status_DD;
	
	@FindBy(xpath="//select[@id='preparer']")
	public WebElement Preparer_DD;
	
	@FindBy(xpath="//eb-button[@id='show_results']/button")
	public WebElement ShowResults_BT;
	
	@FindBy(xpath="//label[text()='Register']")
	public WebElement Register_Label;
	
	@FindBy(xpath="//label[text()='Drawer Count Time']")
	public WebElement DrawerCountTime_Label;
	
	@FindBy(xpath="//label[text()='Status']")
	public WebElement Status_Label;
	
	@FindBy(xpath="//label[text()='Amount']")
	public WebElement Amount_Label;
	
	@FindBy(xpath="//label[text()='Deposit Code']")
	public WebElement DepositCode_Label;
	
	@FindBy(xpath="//label[text()='Preparer']")
	public WebElement Preparer_Label;
	
	@FindBy(xpath="//label[text()='Type']")
	public WebElement Type_Label;
	
	
	//Method to select drawer count date
	public DrawerCountDownPage selectDrawerCountDate(String date)throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException {
		GenericMethods.clickOnElement(DrawerCount_Date_TB, "DrawerCount_Date_TB");
		Thread.sleep(2000);
		int day = Base.getDayFromDate(date);
		int month = Base.getMonthFromDate(date);
		System.out.println("Month is "+month);
		selectMonthFromDatePicker(Base.getMonthName(month+1),1);
		Reporter.reportPassResult(AbstractTest.browser, "Month "+Base.getMonthName(month)+" is selected From Date Picker", "Pass");
		System.out.println("(//div[@class='xdsoft_calendar'])[1]//tbody/tr//td[@data-month='"+month+"']/div[text()='"+day+"']");
		GenericMethods.clickOnElement(driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[1]//tbody/tr//td[@data-month='"+month+"']/div[text()='"+day+"']")), "clicked on "+day+"in calender");
		GenericMethods.clickOnElement(DrawerCount_Label, "DrawerCount_Label");
		return PageFactory.initElements(driver, DrawerCountDownPage.class);
	}
	
	public int getNumberOfRecordsForFilterValue(String depositCode){
		System.out.println("//span[text()='"+depositCode+"']");
		return driver.findElements(By.xpath("//span[text()='"+depositCode+"']")).size();
	}
	
	public void expandRolledUpDrawerCountRecords() throws InterruptedException{
		List<WebElement> drawerCountRolledUpList = driver.findElements(By.xpath("//table[@id='drawer_counts_table']/tbody/tr[contains(@id,'row-header')]"));
		for(WebElement rolledUpItem : drawerCountRolledUpList){
			executor.executeScript("arguments[0].click();", rolledUpItem);
			Thread.sleep(2000);
		}
				
	}
	

}
