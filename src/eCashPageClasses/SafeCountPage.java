package eCashPageClasses;

import java.io.IOException;
import java.text.ParseException;
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

public class SafeCountPage extends AbstractPage {

	public SafeCountPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='sc_biz_date_picker_input']")
	public WebElement SafeCount_Date_TB;

	@FindBy(xpath="//h1[text()='Safe Count']")
	public WebElement SafeCount_Label;
	
	@FindBy(xpath="//label[text()='Created']")
	public WebElement Created_Label;
	
	@FindBy(xpath="//label[text()='Safe Cash']")
	public WebElement SafeCash_Label;
	
	@FindBy(xpath="//label[text()='Petty Cash']")
	public WebElement PettyCash_Label;
	
	@FindBy(xpath="//label[text()='Gift Certificate']")
	public WebElement GiftCertificate_Label;
	
	@FindBy(xpath="//span[text()='Treat Book']")
	public WebElement TreatBook_Label;
	
	@FindBy(xpath="//Label[text()='Created By']")
	public WebElement CreatedBY_Label;
	
	@FindBy(xpath="//select[@id='created_by']")
	public WebElement CreatedBY_DD;
	
	@FindBy(xpath="//table[@id='safe_count_table']/tbody/tr/td/eb-button/button")
	public WebElement SafeCountRecord_View_BT;
	
	@FindBy(xpath="//table[@id='safe_count_table']/tbody/tr/td[1]/span")
	public List<WebElement> SafeCount_Date_List;
	
	//Method to select drawer count date
	public SkimsPage selectSafeCountDate(String date)throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException {
		GenericMethods.clickOnElement(SafeCount_Date_TB, "SafeCount_Date_TB");
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
	
	public boolean verifySafeCountDateIsDisabled(String startDate) throws InterruptedException{
		int day = Base.getDayFromDate(startDate);
		int month = Base.getMonthFromDate(startDate);
		selectMonthFromDatePicker(Base.getMonthName(month+1),1);
		boolean dateEnabled = driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[1]//tbody/tr//td[@data-month='"+month+"' and @data-date='"+day+"']")).getAttribute("class").contains("xdsoft_disabled");
		return dateEnabled;
	}
	
	public boolean verifySafeCountCreatedDateInAscendingOrder() throws ParseException{
		List<WebElement>createdDateList = driver.findElements(By.xpath("//table[@id='safe_count_table']/tbody/tr/td[1]/span"));
		List<String>createdDateValueList = Base.getTextListFromWebElements(createdDateList);
		return Base.verifyDateInAscendingOrder(createdDateValueList);
	}
	
	public boolean verifySafeCountCreatedDateInDescendingOrder() throws ParseException
	{
		List<WebElement>createdDateList = driver.findElements(By.xpath("//table[@id='safe_count_table']/tbody/tr/td[1]/span"));
		List<String>createdDateValueList = Base.getTextListFromWebElements(createdDateList);
		return Base.verifyDateInDescendingOrder(createdDateValueList);
	}
	
	
	public boolean verifySafeCashInDescendingOrder() throws ParseException{
		List<WebElement>safeCashList = driver.findElements(By.xpath("//table[@id='safe_count_table']/tbody/tr/td[2]"));
		List<String>safeCashValueList = Base.getTextListFromWebElements(safeCashList);
		return Base.verifyAmountIsInDescendingOrder(safeCashValueList);
	}

	public boolean verifySafeCashInAscendingOrder() throws ParseException{
		List<WebElement>safeCashList = driver.findElements(By.xpath("//table[@id='safe_count_table']/tbody/tr/td[2]"));
		List<String>safeCashValueList = Base.getTextListFromWebElements(safeCashList);
		return Base.verifyAmountIsInAscendingOrder(safeCashValueList);
	}
	
	
	public boolean verifyPettyCashInDescendingOrder() throws ParseException{
		List<WebElement>PettyCashList = driver.findElements(By.xpath("//table[@id='safe_count_table']/tbody/tr/td[4]"));
		List<String>PettyCashValueList = Base.getTextListFromWebElements(PettyCashList);
		return Base.verifyAmountIsInDescendingOrder(PettyCashValueList);
	}

	public boolean verifyPettyCashInAscendingOrder() throws ParseException{
		List<WebElement>PettyCashList = driver.findElements(By.xpath("//table[@id='safe_count_table']/tbody/tr/td[4]"));
		List<String>PettyCashValueList = Base.getTextListFromWebElements(PettyCashList);
		return Base.verifyAmountIsInAscendingOrder(PettyCashValueList);
	}
	
	
	public boolean verifyGiftCertificateInDescendingOrder() throws ParseException{
		List<WebElement>giftCertificateList = driver.findElements(By.xpath("//table[@id='safe_count_table']/tbody/tr/td[6]"));
		List<String>giftCertificateValueList = Base.getTextListFromWebElements(giftCertificateList);
		return Base.verifyAmountIsInDescendingOrder(giftCertificateValueList);
	}

	public boolean verifyGiftCertificateInAscendingOrder() throws ParseException{
		List<WebElement>giftCertificateList = driver.findElements(By.xpath("//table[@id='safe_count_table']/tbody/tr/td[6]"));
		List<String>giftCertificateValueList = Base.getTextListFromWebElements(giftCertificateList);
		return Base.verifyAmountIsInAscendingOrder(giftCertificateValueList);
	}
	
		
	
	public boolean verifyTreatBookInDescendingOrder() throws ParseException{
		List<WebElement>TreatBookList = driver.findElements(By.xpath("//table[@id='safe_count_table']/tbody/tr/td[7]"));
		List<String>TreatBookValueList = Base.getTextListFromWebElements(TreatBookList);
		return Base.verifyAmountIsInDescendingOrder(TreatBookValueList);
	}

	public boolean verifyTreatBookInAscendingOrder() throws ParseException{
		List<WebElement>TreatBookList = driver.findElements(By.xpath("//table[@id='safe_count_table']/tbody/tr/td[7]"));
		List<String>TreatBookValueList = Base.getTextListFromWebElements(TreatBookList);
		return Base.verifyAmountIsInAscendingOrder(TreatBookValueList);
	}
	
	
	public boolean verifyCreatedByInDescendingOrder() throws ParseException{
		List<WebElement>CreatedByList = driver.findElements(By.xpath("//table[@id='safe_count_table']/tbody/tr/td[8]/span"));
		List<String>CreatedByValueList = Base.getTextListFromWebElements(CreatedByList);
		return Base.verifyStringInAsscendingOrder(CreatedByValueList);
	}

	public boolean verifyCreatedByInAscendingOrder() throws ParseException{
		List<WebElement>CreatedByList = driver.findElements(By.xpath("//table[@id='safe_count_table']/tbody/tr/td[8]/span"));
		List<String>CreatedByValueList = Base.getTextListFromWebElements(CreatedByList);
		return Base.verifyStringInDescendingOrder(CreatedByValueList);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
