package eCashPageClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import common.Base;
import common.GenericMethods;
import common.Reporter;

public class SkimsPage extends AbstractPage
{
	public SkimsPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//h1[text()='Skims']")
	public WebElement Skims_Label;
	
	@FindBy(xpath="//input[@id='skim_biz_date_picker_input']")
	public WebElement Skims_Date_TB;
	
	@FindBy(xpath="//th[text()='Skim Time']")
	public WebElement SkimsTime_Label;
	
	@FindBy(xpath = "//th[text()='Deposit Code']")
	public WebElement DepositCode_Label;
	
	@FindBy(xpath = "//div[@class='eb-modal-body']//select[@id='deposit_code']")
	public WebElement Skim_PopUp_DepositCode_DD;
	
	@FindBy(xpath = "//div[@class='eb-modal-body']//select[@id='register']")
	public WebElement Skim_PopUp_Register_DD;
	
	@FindBy(xpath = "//div[@class='eb-modal-body']//select[@id='manager']")
	public WebElement Skim_PopUp_Manager_DD;
	
	@FindBy(xpath = "//input[@id='validatedInput' and @label='Envelope ID:']")
	public WebElement Skim_PopUp_EnvelopID_TB;
	
	@FindBy(xpath = "//input[@id='validatedInput' and @label='Amount']")
	public WebElement Skim_PopUp_Amount_TB;
	
	@FindBy(xpath = "//input[@id='pos_entry_time']")
	public WebElement Skim_PopUp_Time_TB;

	@FindBy(xpath = "//th[text()='Register']")
	public WebElement Register_Label;

	@FindBy(xpath = "//th[text()='Amount']")
	public WebElement Amount_Label;

	@FindBy(xpath = "//th[text()='Preparer']")
	public WebElement Preparer_Label;

	@FindBy(xpath = "//th[text()='Source']")
	public WebElement Source_Label;

	@FindBy(xpath = "//th[text()='Status']")
	public WebElement Status_Label;
	
	@FindBy(xpath = "//h2[text()='Skim']")
	public WebElement Skim_PopUp_Label;
	
	@FindBy(xpath="//table[@id='skims_table']/tbody/tr/td/span[text()='Counted']/../following-sibling::td/eb-button/button")
	public WebElement Skims_CountedRecord_View_BT;
	
	@FindBy(xpath="//table[@id='skims_table']/tbody/tr")
	public List<WebElement> Skims_AllRecord_List;
	
	@FindBy(xpath="//table[@id='skims_table']/tbody/tr[contains(@id,'row-header')]")
	public List<WebElement> Skims_RolledUpRecord_List;
	
	@FindBy(xpath="(//select[@id='register'])[1]")
	public WebElement Skims_Register_DD;

	@FindBy(xpath="(//select[@id='deposit_code'])[1]")
	public WebElement SkimsDepositCode_DD;
	
	@FindBy(xpath="//select[@id='status']")
	public WebElement SkimsStatus_DD;
	
	@FindBy(xpath="//select[@id='preparer']")
	public WebElement SkimsPreparer_DD;
	
	@FindBy(xpath="//eb-button[@id='show_results']/button")
	public WebElement ShowResults_BT;
	
	@FindBy(xpath="//eb-button [@id='cancel_skim']/button")
	public WebElement Skims_PopUp_Cancel_BT;
	
	//Method to select drawer count date
	public SkimsPage selectSkimsDate(String date)throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException {
		GenericMethods.clickOnElement(Skims_Date_TB, "Skims_Date_TB");
		Thread.sleep(2000);
		int day = Base.getDayFromDate(date);
		int month = Base.getMonthFromDate(date);
		System.out.println("Month is "+month);
		selectMonthFromDatePicker(Base.getMonthName(month+1),1);
		Reporter.reportPassResult(AbstractTest.browser, "Month "+Base.getMonthName(month)+" is selected From Date Picker", "Pass");
		System.out.println("(//div[@class='xdsoft_calendar'])[1]//tbody/tr//td[@data-month='"+month+"']/div[text()='"+day+"']");
		GenericMethods.clickOnElement(driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[1]//tbody/tr//td[@data-month='"+month+"']/div[text()='"+day+"']")), "clicked on "+day+"in calender");
		//GenericMethods.clickOnElement(DrawerCount_Label, "DrawerCount_Label");
		return PageFactory.initElements(driver, SkimsPage.class);
	}
	
	public boolean verifySkimsDateIsDisabled(String startDate) throws InterruptedException{
		int day = Base.getDayFromDate(startDate);
		int month = Base.getMonthFromDate(startDate);
		selectMonthFromDatePicker(Base.getMonthName(month+1),1);
		boolean dateEnabled = driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[1]//tbody/tr//td[@data-month='"+month+"' and @data-date='"+day+"']")).getAttribute("class").contains("xdsoft_disabled");
		return dateEnabled;
	}
	
	public void expandRolledUpSkimsRecords() throws InterruptedException{
		List<WebElement> skimsRolledUpList = driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr[contains(@id,'row-header')]"));
		for(WebElement rolledUpItem : skimsRolledUpList){
			executor.executeScript("arguments[0].click();", rolledUpItem);
			Thread.sleep(2000);
		}
	}
	public static List<String> getDateFromWebElements(List<WebElement> elementList){
		ArrayList<String>textValueList = new ArrayList<String>();
		for(WebElement element : elementList){
			textValueList.add(element.getText().split("\\|")[0].trim());
		}
		return textValueList;
	}
	
	public boolean verifySkimsDateInAscendingOrder() throws ParseException{
		List<WebElement>dateList = driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[1]/span"));
		List<String>dateValueList = getDateFromWebElements(dateList);
		return Base.verifyDateInAscendingOrder(dateValueList);
	}
	
	public boolean verifySkimsDateInDescendingOrder() throws ParseException
	{
		List<WebElement>dateList = driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[1]/span"));
		List<String>dateValueList = getDateFromWebElements(dateList);
		return Base.verifyDateInDescendingOrder(dateValueList);
	}
	
	public boolean verifySkimsDepositCodeInAscendingOrder() throws ParseException{
		List<WebElement>depositCodeList = driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[2]/span"));
		List<String>depositCodeValueList = Base.getTextListFromWebElements(depositCodeList);
		return Base.verifyStringInAsscendingOrder(depositCodeValueList);
	}
	
	public boolean verifySkimsDepositCodeInDescendingOrder() throws ParseException
	{
		List<WebElement>depositCodeList = driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[2]/span"));
		List<String>depositCodeValueList = Base.getTextListFromWebElements(depositCodeList);
		return Base.verifyStringInDescendingOrder(depositCodeValueList);
	}
	
	public boolean verifySkimsRegisterInAscendingOrder() throws ParseException{
		List<WebElement>registerList = driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[3]/span"));
		List<String>registerValueList = Base.getTextListFromWebElements(registerList);
		return Base.verifyBigIntInAsscendingOrder(registerValueList);
	}
	
	public boolean verifySkimsRegisterInDescendingOrder() throws ParseException{
		List<WebElement>registerList = driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[3]/span"));
		List<String>registerValueList = Base.getTextListFromWebElements(registerList);
		return Base.verifyBigIntInDescendingOrder(registerValueList);
	}
	
	public boolean verifySkimsAmountInDescendingOrder() throws ParseException{
		List<WebElement>amountList = driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[4]/span"));
		List<String>amountValueList = Base.getTextListFromWebElements(amountList);
		return Base.verifyAmountIsInDescendingOrder(amountValueList);
	}

	public boolean verifySkimsAmountInAscendingOrder() throws ParseException{
		List<WebElement>amountList = driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[4]/span"));
		List<String>amountValueList = Base.getTextListFromWebElements(amountList);
		return Base.verifyAmountIsInAscendingOrder(amountValueList);
	}
	
	public boolean verifySkimsPreparerInDescendingOrder() throws ParseException{
		List<WebElement>userList = driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[6]/span[1]"));
		List<String>userValueList = Base.getTextListFromWebElements(userList);
		return Base.verifyStringInDescendingOrder(userValueList);
	}
	
	public boolean verifySkimsPreparerInAscendingOrder() throws ParseException{
		List<WebElement>userList = driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[6]/span[1]"));
		List<String>userValueList = Base.getTextListFromWebElements(userList);
		return Base.verifyStringInAsscendingOrder(userValueList);
	}
	
	public boolean verifySkimsSourceInDescendingOrder() throws ParseException{
		List<WebElement>userList = driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[7]/span[1]"));
		List<String>userValueList = Base.getTextListFromWebElements(userList);
		return Base.verifyStringInDescendingOrder(userValueList);
	}
	
	public boolean verifySkimsSourceInAscendingOrder() throws ParseException{
		List<WebElement>userList = driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[7]/span[1]"));
		List<String>userValueList = Base.getTextListFromWebElements(userList);
		return Base.verifyStringInAsscendingOrder(userValueList);
	}
	
	public boolean verifySkimsStatusInDescendingOrder() throws ParseException{
		List<WebElement>userList = driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[8]/span[1]"));
		List<String>userValueList = Base.getTextListFromWebElements(userList);
		return Base.verifyStringInDescendingOrder(userValueList);
	}
	
	public boolean verifySkimsStatusInAscendingOrder() throws ParseException{
		List<WebElement>userList = driver.findElements(By.xpath("//table[@id='skims_table']/tbody/tr/td[8]/span[1]"));
		List<String>userValueList = Base.getTextListFromWebElements(userList);
		return Base.verifyStringInAsscendingOrder(userValueList);
	}
	
	public int getNumberOfRecordsForFilterValue(String filter){
		System.out.println("//span[text()='"+filter+"']");
		return driver.findElements(By.xpath("//span[text()='"+filter+"']")).size();
	}
	
	//Click on View button for the first counted and uncounted records
		public SkimsPage clickOnViewButton(String status)throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException {
			GenericMethods.clickOnElement(driver.findElement(By.xpath("(//button[@id='htmlButton']/../preceding::td/span[text()='Uncounted']/../following-sibling::td//button)[1]")), "View button");
			wait.until(ExpectedConditions.visibilityOf(Skim_PopUp_Label));
			//GenericMethods.clickOnElement(DrawerCount_Label, "DrawerCount_Label");
			return PageFactory.initElements(driver, SkimsPage.class);
		}
}
