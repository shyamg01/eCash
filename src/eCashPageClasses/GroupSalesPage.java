package eCashPageClasses;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import common.Base;
import common.GenericMethods;

public class GroupSalesPage extends AbstractPage
{

	public GroupSalesPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//h1[text()='Group Sales']")
	 public WebElement GroupSales_Label;
	
	@FindBy(xpath="//a[contains(.,'Tax Exempt Sales')]")
	 public WebElement TaxExemptSales_BT;
	
	@FindBy(xpath="//a[contains(.,'Billable Sales')]")
	 public WebElement BillableSales_BT;
	
	@FindBy(xpath="//a[contains(.,'Other Receipts')]")
	 public WebElement OtherReceipts_BT;
	
	@FindBy(xpath="//select[@id='tax_exempt_sales_date_range']")
	 public WebElement TextExempt_DateRange_DD;
	
	@FindBy(xpath="//select[@id='other_receipts_date_range']")
	 public WebElement OtherReceipt_DateRange_DD;
	
	@FindBy(xpath="//select[@id='billable_sales_date_range']")
	 public WebElement BillableSales_DateRange_DD;

	@FindBy(xpath="//select[@id='organization_te']")
	 public WebElement TaxExemptSales_Organization_DD;
	
	@FindBy(xpath="//select[@id='organization_or']")
	 public WebElement OtherReceipt_Organization_DD;
	
	@FindBy(xpath="//select[@id='user_te']")
	 public WebElement TaxExemptSales_User_DD;
	
	@FindBy(xpath="//select[@id='user_or']")
	 public WebElement OtherReceipts_User_DD;
	
	@FindBy(xpath="//select[@id='deposit_status_te']")
	 public WebElement TaxExemptSales_DepositStatus_DD;
	
	@FindBy(xpath="//select[@id='deposit_status_or']")
	 public WebElement OtherReceipt_DepositStatus_DD;
	
	@FindBy(xpath="//input[@id='start_date_picker_input_te']")
	 public WebElement TaxExempt_StartDate_TB;
	
	@FindBy(xpath="//input[@id='start_date_picker_input_or']")
	 public WebElement OtherReceipts_StartDate_TB;
	
	@FindBy(xpath="//input[@id='end_date_picker_input_te']")
	 public WebElement TaxExempt_EndDate_TB;
	
	@FindBy(xpath="//input[@id='end_date_picker_input_or']")
	 public WebElement OtherReceipts_EndDate_TB;
	
	@FindBy(xpath="//table[@id='billable_sales_table']//label[text()='Date & Time']")
	 public WebElement BillableSales_DateAndTime_Label;
	
	@FindBy(xpath="//table[@id='other_receipts_table']//label[text()='Date & Time']")
	 public WebElement OtherReceipts_DateAndTime_Label;
	
	@FindBy(xpath="//label[text()='Date & Time']")
	 public WebElement TaxExemptSales_DateAndTime_Label;
	
	@FindBy(xpath="//table[@id='billable_sales_table']//label[text()='Register']")
	 public WebElement BillableSales_Register_Label;
	
	@FindBy(xpath="//table[@id='other_receipts_table']//label[text()='Register']")
	 public WebElement OtherReceipts_Register_Label;
	
	@FindBy(xpath="//label[text()='Register']")
	 public WebElement TaxExemptSales_Register_Label;
	
	@FindBy(xpath="//table[@id='billable_sales_table']//label[text()='Amount']")
	 public WebElement BillableSales_Amount_Label;
	
	@FindBy(xpath="//table[@id='other_receipts_table']//label[text()='Amount']")
	 public WebElement OtherReceipts_Amount_Label;
	
	@FindBy(xpath="//label[text()='Amount']")
	 public WebElement TaxExemptSales_Amount_Label;
	
	@FindBy(xpath="//table[@id='billable_sales_table']//label[text()='User']")
	 public WebElement BillableSales_User_Label;
	
	@FindBy(xpath="//table[@id='other_receipts_table']//label[text()='User']")
	 public WebElement OtherReceipts_User_Label;
	
	@FindBy(xpath="//label[text()='User']")
	 public WebElement TaxExemptSales_User_Label;
	
	@FindBy(xpath="//table[@id='billable_sales_table']//label[text()='Organization Name']")
	 public WebElement BillableSales_OrganizationName_Label;
	
	
	@FindBy(xpath="//table[@id='other_receipts_table']//label[text()='Organization Name']")
	 public WebElement OtherReceipts_OrganizationName_Label;
	
	@FindBy(xpath="//label[text()='Organization Name']")
	 public WebElement TaxExemptSales_OrganizationName_Label;
	
	@FindBy(xpath="//label[text()='Tax ID Number']")
	 public WebElement TaxExemptSales_TaxIDNumber_Label;
	
	@FindBy(xpath="//table[@id='billable_sales_table']//label[text()='Invoice/PO #']")
	 public WebElement BillableSales_InvoicePO_Label;
	
	@FindBy(xpath="//table[@id='other_receipts_table']//label[text()='Check Number']")
	 public WebElement OtherReceipts_CheckNumber_Label;
	
	@FindBy(xpath="//table[@id='billable_sales_table']//label[text()='Deposit Status']")
	 public WebElement BillableSales_DepositStatus_Label;
	
	@FindBy(xpath="//table[@id='other_receipts_table']//label[text()='Deposit Status']")
	 public WebElement OtherReceipts_DepositStatus_Label;
	
	@FindBy(xpath="//label[text()='Deposit Status']")
	 public WebElement TaxExemptSales_DepositStatus_Label;
	
	@FindBy(xpath="//table[@id='billable_sales_table']//span[text()='Total:']")
	 public WebElement BillableSales_Total_Label;
	
	@FindBy(xpath="//table[@id='other_receipts_table']//span[text()='Total:']")
	 public WebElement OtherReceipts_Total_Label;
	
	@FindBy(xpath="//span[@id='total_billable']")
	 public WebElement BillableSales_Total_Value;
	
	@FindBy(xpath="//span[@id='totalOther']")
	 public WebElement OtherReceipts_Total_Value;
	
	@FindBy(xpath="//div[@id='billable_sales']//label[@id='user_label']")
	 public WebElement BillableSales_UserDD_Label;

	@FindBy(xpath="//select[@id='user_bs']")
	 public WebElement BillableSales_User_DD;
	
	@FindBy(xpath="//div[@id='billable_sales']//label[@id='organization_label']")
	 public WebElement BillableSales_OrganizationDD_Label;
	
	@FindBy(xpath="//select[@id='organization_bs']")
	 public WebElement BillableSales_Organization_DD;
	
	@FindBy(xpath="//div[@id='billable_sales']//label[@id='deposit_status_label']")
	 public WebElement BillableSales_DepositStatusDD_Label;
	
	@FindBy(xpath="//select[@id='deposit_status_bs']")
	 public WebElement BillableSales_DepositStatus_DD;
	
	@FindBy(xpath="//div[@id='tax_exempt_sales']//button[@id='htmlButton' and @value='Show Results']")
	 public WebElement TaxEximptSales_ShowResult_BT;
	
	@FindBy(xpath="//div[@id='other_receipts']//button[@id='htmlButton' and @value='Show Results']")
	 public WebElement OtherReceipt_ShowResult_BT;
	
	@FindBy(xpath="//span[@id='totalTax']")
	 public WebElement Total_Value;
	
	@FindBy(xpath="//h2[text()='Tax Exempt Sales']")
	public WebElement EditTaxExemptSales_PopUp_Header;
	
	@FindBy(xpath="//h2[text()='Billable Sales']")
	public WebElement EditBillableSales_PopUp_Header;
	
	@FindBy(xpath="//h2[text()='Other Receipts']")
	public WebElement OtherReceipts_PopUp_Header;
	
	@FindBy(xpath="//input[@id='tax_exempt_organization_autocomplete_input0']")
	public WebElement EditTaxExemptSalesPopUp_OrganizationName_TB;
	
	@FindBy(xpath="//input[@id='billable_sales_organization_input0']")
	public WebElement BillableSalesPopUp_OrganizationName_TB;
	
	@FindBy(xpath="//input[@id='other_receipts_organization_input0']")
	public WebElement OtherReceiptsPopUp_OrganizationName_TB;
	
	@FindBy(xpath="//input[@id='invoice_bs']")
	public WebElement BillableSalesPopUp_InvoicePONO_TB;
	
	@FindBy(xpath="//input[@id='validatedInput' and @label='Check Number']")
	public WebElement OtherReceipts_CheckNumber_TB;
	
	@FindBy(xpath="//input[@id='validatedInput' and @label='Amount']")
	public WebElement OtherReceipts_Amount_TB;
	
	@FindBy(xpath="//input[@label='Tax ID']")
	public WebElement EditTaxExemptSalesPopUp_TaxId_TB;
	
	@FindBy(xpath="//input[@id='tax_exempt_contact_name_autocomplete0']")
	public WebElement EditTaxExemptSalesPopUp_ContactName_TB;
	
	@FindBy(xpath="//input[@id='contact_bs0']")
	public WebElement BillableSalesPopUp_ContactName_TB;
	
	@FindBy(xpath="//input[@id='idTax_ex_address']")
	public WebElement EditTaxExemptSalesPopUp_AddressLine1_TB;
	
	@FindBy(xpath="//label[text()='Address Line 1']/../input")
	public WebElement EditBillableSalesPopUp_AddressLine1_TB;
	
	@FindBy(xpath="//input[@id='idTax_ex_address2']")
	public WebElement EditTaxExemptSalesPopUp_AddressLine2_TB;
	
	@FindBy(xpath="//label[text()='Address Line 2']/../input")
	public WebElement EditBillableSalesPopUp_AddressLine2_TB;
	
	@FindBy(xpath="//input[@id='idTax_ex_city']")
	public WebElement EditTaxExemptSalesPopUp_City_TB;
	
	@FindBy(xpath="//label[text()='City']/../input")
	public WebElement BillableSalesPopUp_City_TB;
	
	@FindBy(xpath="//input[@id='idEmail_te']")
	public WebElement EditTaxExemptSalesPopUp_Email_TB;
	
	@FindBy(xpath="//label[text()='E-Mail']/../input")
	public WebElement BillableSalesPopUp_Email_TB;
	
	@FindBy(xpath="//label[text()='Address Line 1']/../input")
	public WebElement BillableSalesPopUp_AddressLine1_TB;
	
	@FindBy(xpath="//label[text()='Address Line 2']/../input")
	public WebElement BillableSalesPopUp_AddressLine2_TB;
	
	@FindBy(xpath="//input[@label='Zip']")
	public WebElement EditTaxExemptSalesPopUp_Zip_TB;
	
	@FindBy(xpath="//input[@label='Zip']")
	public WebElement BillableSalesPopUp_Zip_TB;
	
	@FindBy(xpath="//input[@id='idTax_ex_phone']")
	public WebElement EditTaxExemptSalesPopUp_PhoneNumber_TB;
	
	@FindBy(xpath="//label[text()='Phone Number']/../input")
	public WebElement BillableSalesPopUp_PhoneNumber_TB;
	
	@FindBy(xpath="//textarea[@id='idDescription0']")
	public WebElement OtherReceipts_Description_TB;
	
	@FindBy(xpath="//input[@label='Amount']")
	public WebElement EditTaxExemptSalesPopUp_Amount_TB;
	
	@FindBy(xpath="//select[@id='idTax_ex_state']")
	public WebElement EditTaxExemptSalesPopUp_State_DD;
	
	@FindBy(xpath="//label[text()='State']/../select[@class='form-control ebos-input']")
	public WebElement BillableSalesPopUp_State_DD;
	
	@FindBy(xpath="//eb-button[@id='save_te']/button")
	public WebElement EditTaxExemptSalesPopUp_Submit_BT;
	
	@FindBy(xpath="//button[@id='htmlButton' and @value='Submit']")
	public WebElement BillableSalesPopUp_Submit_BT;
	
	@FindBy(xpath="//eb-button[@value='Close']/button")
	public WebElement EditTaxExemptSalesPopUp_Close_BT;
	
	@FindBy(xpath = "//div[@class='toast-message' and contains(text(),'Tax Exempt Sale Saved.')]")
	public WebElement TaxExemptSales_SalesSaved_Confirmation_MSG;
	
	@FindBy(xpath = "//div[@class='toast-message' and contains(text(),'Billable Sale Saved.')]")
	public WebElement BillableSales_SalesSaved_Confirmation_MSG;
	
	@FindBy(xpath = "//input[@id='start_date_picker_input_te']")
	public WebElement TaxExemptSales_StartDate_TB;
	
	@FindBy(xpath = "//input[@id='start_date_picker_input']")
	public WebElement BillableSales_StartDate_TB;
	
	@FindBy(xpath = "//input[@id='end_date_picker_input_te']")
	public WebElement TaxExemptSales_EndDate_TB;
	
	@FindBy(xpath = "//input[@id='end_date_picker_input']")
	public WebElement BillableSales_EndDate_TB;
	
	@FindBy(xpath = "(//eb-button[@value='Show Results'])[1]/button")
	public WebElement TaxExemptSales_ShowResults_BT;
	
	@FindBy(xpath = "//div[@id='billable_sales']//button[@id='htmlButton'  and @value='Show Results']")
	public WebElement BillableSales_ShowResults_BT;
	
	@FindBy(xpath="(//label[text()='Date & Time'])[1]")
	 public WebElement TextExemptSales_DateAndTime_Label;
	
	@FindBy(xpath="(//label[text()='Register'])[1]")
	 public WebElement TextExemptSales_Register_Label;
	
	@FindBy(xpath="(//label[text()='Amount'])[1]")
	 public WebElement TextExemptSales_Amount_Label;
	
	@FindBy(xpath="(//label[text()='User'])[1]")
	 public WebElement TextExemptSales_User_Label;
	
	@FindBy(xpath="(//label[text()='Organization Name'])[1]")
	 public WebElement TextExemptSales_OrganizationName_Label;
	
	@FindBy(xpath="(//label[text()='Tax ID Number'])[1]")
	 public WebElement TextExemptSales_TaxIDNumber_Label;
	
	@FindBy(xpath="(//label[text()='Deposit Status'])[1]")
	public WebElement TextExemptSales_DepositStatus_Label;
	
	@FindBy(xpath="(//table[@id='tax_exempt_table']/tbody/tr/td[text()='Validated'])[1]/following-sibling::td/eb-button")
	public WebElement TextExemptSales_ValidatedRecord_Edit_BT;
	
	@FindBy(xpath="(//table[@id='billable_sales_table']/tbody/tr/td[text()='Validated'])[1]/following-sibling::td/eb-button/button")
	public WebElement BillableSales_ValidatedRecord_Edit_BT;
	
	@FindBy(xpath="(//table[@id='other_receipts_table']/tbody/tr/td[text()='Validated'])[1]/following-sibling::td/eb-button")
	public WebElement OtherReceiptss_ValidatedRecord_Edit_BT;

	@FindBy(xpath="(//table[@id='tax_exempt_table']/tbody/tr/td[text()='Bagged'])[1]/following-sibling::td/eb-button")
	public WebElement TextExemptSales_BaggedRecord_View_BT;
	
	@FindBy(xpath="//div[@id='header-tax']")
	public WebElement TaxExemptSalesPopUp_HeaderText_Value;
	
	@FindBy(xpath="(//div[@class='autocomplete-suggestions'])[2]/div[@class='autocomplete-suggestion']")
	public List<WebElement> EditTaxExemptSalesPopUp_Organization_Suggestions_List;
	
	@FindBy(xpath="//div[@class='autocomplete-suggestions']/div[@class='autocomplete-suggestion']")
	public List<WebElement> EditBillableSalesPopUp_Organization_Suggestions_List;
	
	@FindBy(xpath="(//label[text()='Type'])[1]")
	 public WebElement OtherReceipts_Type_Label;
	
	@FindBy(xpath="(//table[@id='other_receipts_table']/tbody/tr/td[text()='Validated'])[1]/following-sibling::td/eb-button")
	public WebElement OtherReceipts_ValidatedRecord_Edit_BT;
	
	@FindBy(xpath="//input[@id='other_receipts_organization_input0']")
	public WebElement EditOtherReceiptsPopUp_OrganizationName_TB;
	
	@FindBy(xpath="//select[@id='type_or0']")
	public WebElement EditOtherReceiptsPopUp_Type_DD;
	
	@FindBy(xpath="//input[@label ='Check Number']")
	public WebElement EditOtherReceiptsPopUp_CheckNumber_TB;
	
	@FindBy(xpath="//textarea[@id='idDescription0']")
	public WebElement EditOtherReceiptsPopUp_Description_TB;
	
	@FindBy(xpath="//eb-button[@id='save_or']/button")
	public WebElement EditOtherReceiptsPopUp_Submit_BT;
	
	@FindBy(xpath="//eb-button[@value='Close']/button")
	public WebElement EditOtherReceiptsPopUp_Close_BT;
	
	@FindBy(xpath = "//div[@class='toast-message' and contains(text(),'Other Reciept Saved.')]")
	public WebElement OtherReceipts_Saved_Confirmation_MSG;
	
	@FindBy(xpath="//select[@id='other_receipts_date_range']")
	 public WebElement OtherReceipts_DateRange_DD;
	
	@FindBy(xpath = "(//eb-button[@value='Show Results'])[3]/button")
	public WebElement OtherReceipts_ShowResults_BT;
	
	
	
	

	public boolean verifyTextExemptSalesDateInAscendingOrder() throws ParseException{
		List<WebElement>dateList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[2]/span"));
		List<String>dateValueList = getDateFromWebElements(dateList);
		return Base.verifyDateInAscendingOrder(dateValueList);
	}
	
	public boolean verifyBillableSalesDateInAscendingOrder() throws ParseException{
		List<WebElement>dateList = driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[2]/span"));
		List<String>dateValueList = getDateFromWebElements(dateList);
		return Base.verifyDateInAscendingOrder(dateValueList);
	}
	

	public boolean verifyTextExemptSalesDateInDescendingOrder() throws ParseException
	{
		List<WebElement>dateList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[2]/span"));
		List<String>dateValueList = getDateFromWebElements(dateList);
		return Base.verifyDateInDescendingOrder(dateValueList);
	}
	
	public boolean verifyBillableSalesDateInDescendingOrder() throws ParseException
	{
		List<WebElement>dateList = driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[2]/span"));
		List<String>dateValueList = getDateFromWebElements(dateList);
		return Base.verifyDateInDescendingOrder(dateValueList);
	}
	

	public static List<String> getDateFromWebElements(List<WebElement> elementList){
		ArrayList<String>textValueList = new ArrayList<String>();
		for(WebElement element : elementList){
			textValueList.add(element.getText().split("\\|")[0].trim());
		}
		return textValueList;
	}
	
	public boolean verifyTextExemptSalesRegisterInDescendingOrder() throws ParseException{
		List<WebElement>registerList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[3]/span"));
		List<String>registerValueList = Base.getTextListFromWebElements(registerList);
		return Base.verifyBigIntInDescendingOrder(registerValueList);
	}
	
	public boolean verifyBillableSalesRegisterInDescendingOrder() throws ParseException{
		List<WebElement>registerList = driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[3]/span"));
		List<String>registerValueList = Base.getTextListFromWebElements(registerList);
		return Base.verifyBigIntInDescendingOrder(registerValueList);
	}
	

	
	public boolean verifyTextExemptSalesRegisterInAscendingOrder() throws ParseException{
		List<WebElement>registerList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[3]/span"));
		List<String>registerValueList = Base.getTextListFromWebElements(registerList);
		return Base.verifyBigIntInAsscendingOrder(registerValueList);
	}
	
	public boolean verifyBillableSalesRegisterInAscendingOrder() throws ParseException{
		List<WebElement>registerList = driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[3]/span"));
		List<String>registerValueList = Base.getTextListFromWebElements(registerList);
		return Base.verifyBigIntInAsscendingOrder(registerValueList);
	}
	
	
	
	public boolean verifyTextExemptSalesAmountInDescendingOrder() throws ParseException{
		List<WebElement>amountList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[4]/span"));
		List<String>amountValueList = Base.getTextListFromWebElements(amountList);
		return Base.verifyAmountIsInDescendingOrder(amountValueList);
	}
	
	public boolean verifyBillableSalesAmountInDescendingOrder() throws ParseException{
		List<WebElement>amountList = driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[4]"));
		List<String>amountValueList = Base.getTextListFromWebElements(amountList);
		return Base.verifyAmountIsInDescendingOrder(amountValueList);
	}
	

	
	
	public boolean verifyTextExemptSalesAmountInAscendingOrder() throws ParseException{
		List<WebElement>amountList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[4]/span"));
		List<String>amountValueList = Base.getTextListFromWebElements(amountList);
		return Base.verifyAmountIsInAscendingOrder(amountValueList);
	}
	
	public boolean verifyBillableSalesAmountInAscendingOrder() throws ParseException{
		List<WebElement>amountList = driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[4]"));
		List<String>amountValueList = Base.getTextListFromWebElements(amountList);
		return Base.verifyAmountIsInAscendingOrder(amountValueList);
	}
	

	public boolean verifyTextExemptSalesUserInDescendingOrder() throws ParseException{
		List<WebElement>userList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[6]/span[1]"));
		List<String>userValueList = Base.getTextListFromWebElements(userList);
		return Base.verifyStringInDescendingOrder(userValueList);
	}
	
	public boolean verifyBillableSalesUserInDescendingOrder() throws ParseException{
		List<WebElement>userList = driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[5]/span[1]"));
		List<String>userValueList = Base.getTextListFromWebElements(userList);
		return Base.verifyStringInDescendingOrder(userValueList);
	}
	
	public boolean verifyTextExemptSalesUserInAscendingOrder() throws ParseException{
		List<WebElement>userList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[6]/span[1]"));
		List<String>userValueList = Base.getTextListFromWebElements(userList);
		return Base.verifyStringInAsscendingOrder(userValueList);
	}
	
	public boolean verifyBillableSalesUserInAscendingOrder() throws ParseException{
		List<WebElement>userList = driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[5]/span[1]"));
		List<String>userValueList = Base.getTextListFromWebElements(userList);
		return Base.verifyStringInAsscendingOrder(userValueList);
	}
	

	public boolean verifyTextExemptSalesOrganizationInDescendingOrder() throws ParseException{
		List<WebElement>organizationList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[7]/span"));
		List<String>organizationValueList = Base.getTextListFromWebElements(organizationList);
		return Base.verifyStringInDescendingOrder(organizationValueList);
	}
	
	public boolean verifyBillableSalesOrganizationInDescendingOrder() throws ParseException{
		List<WebElement>organizationList = driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[6]/span"));
		List<String>organizationValueList = Base.getTextListFromWebElements(organizationList);
		return Base.verifyStringInDescendingOrder(organizationValueList);
	}
	
	
	
	public boolean verifyTextExemptSalesOrganizationInAscendingOrder() throws ParseException{
		List<WebElement>organizationList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[7]/span"));
		List<String>organizationValueList = Base.getTextListFromWebElements(organizationList);
		return Base.verifyStringInAsscendingOrder(organizationValueList);
	}
	
	public boolean verifyBillableSalesOrganizationInAscendingOrder() throws ParseException{
		List<WebElement>organizationList = driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[6]/span"));
		List<String>organizationValueList = Base.getTextListFromWebElements(organizationList);
		return Base.verifyStringInAsscendingOrder(organizationValueList);
	}
	
	public boolean verifyOtherReceiptssOrganizationInAscendingOrder() throws ParseException{
		List<WebElement>organizationList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[6]/span"));
		List<String>organizationValueList = Base.getTextListFromWebElements(organizationList);
		return Base.verifyStringInAsscendingOrder(organizationValueList);
	}
	
	public boolean verifyTextExemptSalesTaxIdInDescendingOrder() throws ParseException{
		List<WebElement>taxIdList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[15]/span"));
		List<String>taxIdValueList = Base.getTextListFromWebElements(taxIdList);
		return Base.verifyStringInDescendingOrder(taxIdValueList);
	}
	
	public boolean verifyBillableSalesInvoicePOInDescendingOrder() throws ParseException{
		List<WebElement>taxIdList = driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[14]/span"));
		List<String>taxIdValueList = Base.getTextListFromWebElements(taxIdList);
		return Base.verifyStringInDescendingOrder(taxIdValueList);
	}
	
	
	public boolean verifyOpenReceiptsCheckNumberInDescendingOrder() throws ParseException{
		List<WebElement>taxIdList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[8]/span"));
		List<String>taxIdValueList = Base.getTextListFromWebElements(taxIdList);
		return Base.verifyBigIntInDescendingOrder(taxIdValueList);
	}
	
	public boolean verifyTextExemptSalesTaxIdInAscendingOrder() throws ParseException{
		List<WebElement>taxIdList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[15]/span"));
		List<String>taxIdValueList = Base.getTextListFromWebElements(taxIdList);
		return Base.verifyStringInAsscendingOrder(taxIdValueList);
	}
	
	public boolean verifyBillableSalesInvoicePOInAscendingOrder() throws ParseException{
		List<WebElement>taxIdList = driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[14]/span"));
		List<String>taxIdValueList = Base.getTextListFromWebElements(taxIdList);
		return Base.verifyStringInAsscendingOrder(taxIdValueList);
	}
	
	public boolean verifyOtherReciptsCheckNumberInAscendingOrder() throws ParseException{
		List<WebElement>taxIdList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[8]/span"));
		List<String>taxIdValueList = Base.getTextListFromWebElements(taxIdList);
		return Base.verifyBigIntInAsscendingOrder(taxIdValueList);
	}
	
	public boolean verifyTextExemptSalesDepositStatusInDescendingOrder() throws ParseException{
		List<WebElement>depositStatusList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[17]"));
		List<String>depositValueValueList = Base.getTextListFromWebElements(depositStatusList);
		return Base.verifyStringInDescendingOrder(depositValueValueList);
	}
	
	public boolean verifyBillableSalesDepositStatusInDescendingOrder() throws ParseException{
		List<WebElement>depositStatusList = driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[15]"));
		List<String>depositValueValueList = Base.getTextListFromWebElements(depositStatusList);
		return Base.verifyStringInDescendingOrder(depositValueValueList);
	}
	

	public boolean verifyTextExemptSalesDepositStatusInAscendingOrder() throws ParseException{
		List<WebElement>depositStatusList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[17]"));
		List<String>depositValueValueList = Base.getTextListFromWebElements(depositStatusList);
		return Base.verifyStringInAsscendingOrder(depositValueValueList);
	}
	
	public boolean verifyBillableSalesDepositStatusInAscendingOrder() throws ParseException{
		List<WebElement>depositStatusList = driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[15]"));
		List<String>depositValueValueList = Base.getTextListFromWebElements(depositStatusList);
		return Base.verifyStringInAsscendingOrder(depositValueValueList);
	}
	
	public boolean verifyOpenReceiptsDepositStatusInAscendingOrder() throws ParseException{
		List<WebElement>depositStatusList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[9]"));
		List<String>depositValueValueList = Base.getTextListFromWebElements(depositStatusList);
		return Base.verifyStringInAsscendingOrder(depositValueValueList);
	}
	
	public boolean verifyTaxExemptSalesDetailsUpdated(String orgnizationName, String taxIdNumber){
		return Base.isElementDisplayed(By.xpath("(//table[@id='tax_exempt_table']//tr/td[text()='Validated'])[1]/preceding-sibling::td/span[text()='"+taxIdNumber+"']/../preceding-sibling::td/span[text()='"+orgnizationName+"']"));
	}
	
	public boolean verifyBillableSalesDetailsUpdated(String orgnizationName, String invoicePONumber){
		System.out.println("(//table[@id='billable_sales_table']//tr/td[text()='Validated'])[1]/preceding-sibling::td/span[text()='"+invoicePONumber+"']/../preceding-sibling::td/span[text()='"+orgnizationName+"']");
		return Base.isElementDisplayed(By.xpath("(//table[@id='billable_sales_table']//tr/td[text()='Validated'])[1]/preceding-sibling::td/span[text()='"+invoicePONumber+"']/../preceding-sibling::td/span[text()='"+orgnizationName+"']"));
		//return Base.isElementDisplayed(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td/span[text()='"+orgnizationName+"']/../following-sibling::td/span[text()='"+taxIdNumber+"']"));
	}
	
	public boolean verifyOtherReceiptsDetailsUpdated(String orgnizationName, String type,String checkNumber){
		return Base.isElementDisplayed(By.xpath("(//table[@id='other_receipts_table']//tr/td[text()='Validated'])[1]/preceding-sibling::td/span[text()='"+checkNumber+"']/../preceding-sibling::td/span[text()='"+type+"']/../preceding-sibling::td/span[contains(text(),'"+orgnizationName+"')]"));
	}
	
	public void editTaxExemptSalesRecord(String orgnizationName, String taxIdNumber,String contactNumber, String address1, String address2, String city,String state, String zip, String email, String phoneNumber) throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException{
		GenericMethods.clearValueOfElement(EditTaxExemptSalesPopUp_OrganizationName_TB, "EditTaxExemptSalesPopUp_OrganizationName_TB");
		GenericMethods.enterValueInElement(EditTaxExemptSalesPopUp_OrganizationName_TB, "EditTaxExemptSalesPopUp_OrganizationName_TB", orgnizationName);
		GenericMethods.clearValueOfElement(EditTaxExemptSalesPopUp_TaxId_TB, "EditTaxExemptSalesPopUp_TaxId_TB");
		GenericMethods.enterValueInElement(EditTaxExemptSalesPopUp_TaxId_TB, "EditTaxExemptSalesPopUp_TaxId_TB", taxIdNumber);
		GenericMethods.clearValueOfElement(EditTaxExemptSalesPopUp_ContactName_TB, "EditTaxExemptSalesPopUp_ContactName_TB");
		GenericMethods.enterValueInElement(EditTaxExemptSalesPopUp_ContactName_TB, "EditTaxExemptSalesPopUp_ContactName_TB", contactNumber);
		GenericMethods.clearValueOfElement(EditTaxExemptSalesPopUp_AddressLine1_TB, "EditTaxExemptSalesPopUp_AddressLine1_TB");
		GenericMethods.enterValueInElement(EditTaxExemptSalesPopUp_AddressLine1_TB, "EditTaxExemptSalesPopUp_AddressLine1_TB", address1);
		GenericMethods.clearValueOfElement(EditTaxExemptSalesPopUp_AddressLine2_TB, "EditTaxExemptSalesPopUp_AddressLine2_TB");
		GenericMethods.enterValueInElement(EditTaxExemptSalesPopUp_AddressLine2_TB, "EditTaxExemptSalesPopUp_AddressLine2_TB", address2);
		GenericMethods.clearValueOfElement(EditTaxExemptSalesPopUp_City_TB, "EditTaxExemptSalesPopUp_City_TB");
		GenericMethods.enterValueInElement(EditTaxExemptSalesPopUp_City_TB, "EditTaxExemptSalesPopUp_City_TB", city);
		GenericMethods.clearValueOfElement(EditTaxExemptSalesPopUp_Email_TB, "EditTaxExemptSalesPopUp_Email_TB");
		GenericMethods.enterValueInElement(EditTaxExemptSalesPopUp_Email_TB, "EditTaxExemptSalesPopUp_Email_TB", email);
		GenericMethods.clearValueOfElement(EditTaxExemptSalesPopUp_Zip_TB, "EditTaxExemptSalesPopUp_Zip_TB");
		GenericMethods.enterValueInElement(EditTaxExemptSalesPopUp_Zip_TB, "EditTaxExemptSalesPopUp_Zip_TB", zip);
		GenericMethods.clearValueOfElement(EditTaxExemptSalesPopUp_PhoneNumber_TB, "EditTaxExemptSalesPopUp_PhoneNumber_TB");
		GenericMethods.enterValueInElement(EditTaxExemptSalesPopUp_PhoneNumber_TB, "EditTaxExemptSalesPopUp_PhoneNumber_TB", phoneNumber);
		GenericMethods.selectValueFormDropDownElement(EditTaxExemptSalesPopUp_State_DD, "EditTaxExemptSalesPopUp_State_DD", state);
		GenericMethods.clickOnElement(EditTaxExemptSalesPopUp_Submit_BT, "EditTaxExemptSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(TaxExemptSales_SalesSaved_Confirmation_MSG));
		Thread.sleep(3000);
	}
	
	public void editBillableSalesRecord(String orgnizationName, String invoicePONumber,String contactNumber, String address1, String address2, String city,String state, String zip, String email, String phoneNumber) throws RowsExceededException, BiffException, WriteException, IOException, InterruptedException{
		GenericMethods.clearValueOfElement(BillableSalesPopUp_OrganizationName_TB, "BillableSalesPopUp_OrganizationName_TB");
		GenericMethods.enterValueInElement(BillableSalesPopUp_OrganizationName_TB, "BillableSalesPopUp_OrganizationName_TB", orgnizationName);
		GenericMethods.clearValueOfElement(BillableSalesPopUp_InvoicePONO_TB, "BillableSalesPopUp_InvoicePONO_TB");
		GenericMethods.enterValueInElement(BillableSalesPopUp_InvoicePONO_TB, "BillableSalesPopUp_InvoicePONO_TB", invoicePONumber);
		GenericMethods.clearValueOfElement(BillableSalesPopUp_ContactName_TB, "BillableSalesPopUp_ContactName_TB");
		GenericMethods.enterValueInElement(BillableSalesPopUp_ContactName_TB, "BillableSalesPopUp_ContactName_TB", contactNumber);
		GenericMethods.clearValueOfElement(EditBillableSalesPopUp_AddressLine1_TB, "BillableSalesPopUp_AddressLine1_TB");
		GenericMethods.enterValueInElement(EditBillableSalesPopUp_AddressLine1_TB, "BillableSalesPopUp_AddressLine1_TB", address1);
		GenericMethods.clearValueOfElement(EditBillableSalesPopUp_AddressLine2_TB, "BillableSalesPopUp_AddressLine2_TB");
		GenericMethods.enterValueInElement(EditBillableSalesPopUp_AddressLine2_TB, "BillableSalesPopUp_AddressLine2_TB", address2);
		GenericMethods.clearValueOfElement(BillableSalesPopUp_City_TB, "BillableSalesPopUp_City_TB");
		GenericMethods.enterValueInElement(BillableSalesPopUp_City_TB, "BillableSalesPopUp_City_TB", city);
		GenericMethods.clearValueOfElement(BillableSalesPopUp_Email_TB, "BillableSalesPopUp_Email_TB");
		GenericMethods.enterValueInElement(BillableSalesPopUp_Email_TB, "BillableSalesPopUp_Email_TB", email);
		GenericMethods.clearValueOfElement(BillableSalesPopUp_Zip_TB, "BillableSalesPopUp_Zip_TB");
		GenericMethods.enterValueInElement(BillableSalesPopUp_Zip_TB, "BillableSalesPopUp_Zip_TB", zip);
		GenericMethods.clearValueOfElement(BillableSalesPopUp_PhoneNumber_TB, "BillableSalesPopUp_PhoneNumber_TB");
		GenericMethods.enterValueInElement(BillableSalesPopUp_PhoneNumber_TB, "BillableSalesPopUp_PhoneNumber_TB", phoneNumber);
		GenericMethods.selectValueFormDropDownElement(BillableSalesPopUp_State_DD, "BillableSalesPopUp_State_DD", state);
		GenericMethods.clickOnElement(BillableSalesPopUp_Submit_BT, "BillableSalesPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(BillableSales_SalesSaved_Confirmation_MSG));
		Thread.sleep(3000);
	}
	
	public void editOtherReceiptsRecord(String orgnizationName, String type,String checkNumber, String description)
			throws RowsExceededException, BiffException, WriteException,IOException, InterruptedException {
		GenericMethods.clearValueOfElement(EditOtherReceiptsPopUp_OrganizationName_TB, "EditOtherReceiptsPopUp_OrganizationName_TB");
		GenericMethods.enterValueInElement(EditOtherReceiptsPopUp_OrganizationName_TB, "EditOtherReceiptsPopUp_OrganizationName_TB", orgnizationName);
		GenericMethods.selectValueFormDropDownElement(EditOtherReceiptsPopUp_Type_DD, "EditOtherReceiptsPopUp_Type_DD", type);
		GenericMethods.clearValueOfElement(EditOtherReceiptsPopUp_CheckNumber_TB, "EditOtherReceiptsPopUp_CheckNumber_TB");
		GenericMethods.enterValueInElement(EditOtherReceiptsPopUp_CheckNumber_TB, "EditOtherReceiptsPopUp_CheckNumber_TB", checkNumber);
		EditOtherReceiptsPopUp_Description_TB.click();
		GenericMethods.clearValueOfElement(EditOtherReceiptsPopUp_Description_TB, "EditOtherReceiptsPopUp_Description_TB");
		GenericMethods.enterValueInElement(EditOtherReceiptsPopUp_Description_TB, "EditOtherReceiptsPopUp_Description_TB", description);
		GenericMethods.clickOnElement(EditOtherReceiptsPopUp_Submit_BT, "EditOtherReceiptsPopUp_Submit_BT");
		wait.until(ExpectedConditions.visibilityOf(OtherReceipts_Saved_Confirmation_MSG));
		Thread.sleep(3000);
	}
	
	public GroupSalesPage selectStartdate(String date) throws InterruptedException{
		TaxExemptSales_StartDate_TB.click();
		Thread.sleep(2000);
		int day = Base.getDayFromDate(date);
		int month = Base.getMonthFromDate(date);
		selectFutureMonthFromDatePicker(Base.getMonthName(month+1),5);
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[5]//tbody/tr//td[@data-month='"+month+"']/div[text()='"+day+"']")).click();
		return PageFactory.initElements(driver, GroupSalesPage.class);
	}
	
	public GroupSalesPage BillableSalesselectStartdate(String date) throws InterruptedException{
		BillableSales_StartDate_TB.click();
		Thread.sleep(2000);
		int day = Base.getDayFromDate(date);
		int month = Base.getMonthFromDate(date);
		selectFutureMonthFromDatePicker(Base.getMonthName(month+1),1);
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[1]//tbody/tr//td[@data-month='"+month+"']/div[text()='"+day+"']")).click();
		return PageFactory.initElements(driver, GroupSalesPage.class);
	}
	
	public GroupSalesPage OtherReceiptsselectStartdate(String date) throws InterruptedException{
		OtherReceipts_StartDate_TB.click();
		Thread.sleep(2000);
		int day = Base.getDayFromDate(date);
		int month = Base.getMonthFromDate(date);
		selectFutureMonthFromDatePicker(Base.getMonthName(month+1),3);
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[3]//tbody/tr//td[@data-month='"+month+"']/div[text()='"+day+"']")).click();
		return PageFactory.initElements(driver, GroupSalesPage.class);
	}
	
	public GroupSalesPage selectEndDate(String date) throws InterruptedException{
		TaxExemptSales_EndDate_TB.click();
		Thread.sleep(2000);
		int day = Base.getDayFromDate(date);
		int month = Base.getMonthFromDate(date);
		selectMonthFromDatePicker(Base.getMonthName(month+1),6);
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[6]//tbody/tr//td[@data-month='"+month+"']/div[text()='"+day+"']")).click();
		return PageFactory.initElements(driver, GroupSalesPage.class);
	}
	
	public GroupSalesPage BillableSalesselectEndDate(String date) throws InterruptedException{
		BillableSales_EndDate_TB.click();
		Thread.sleep(2000);
		int day = Base.getDayFromDate(date);
		int month = Base.getMonthFromDate(date);
		selectMonthFromDatePicker(Base.getMonthName(month+1),2);
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[2]//tbody/tr//td[@data-month='"+month+"']/div[text()='"+day+"']")).click();
		return PageFactory.initElements(driver, GroupSalesPage.class);
	}
	
	
	public GroupSalesPage OtherReceiptsselectEndDate(String date) throws InterruptedException{
		OtherReceipts_EndDate_TB.click();
		Thread.sleep(2000);
		int day = Base.getDayFromDate(date);
		int month = Base.getMonthFromDate(date);
		selectMonthFromDatePicker(Base.getMonthName(month+1),4);
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[4]//tbody/tr//td[@data-month='"+month+"']/div[text()='"+day+"']")).click();
		return PageFactory.initElements(driver, GroupSalesPage.class);
	}
	
	public boolean verifyTaxExemptSalesDisplayedForSelectedDateRange(String startDate, String endDate) throws ParseException{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date stDate = df.parse(startDate);
		Date eDate = df.parse(endDate);
		List<WebElement> recordDateList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[2]/span"));
		boolean result = true;
		for(WebElement record : recordDateList ){
			String date = record.getText().split("\\|")[0];
			Date recordDate = df.parse(date);
			if((recordDate.compareTo(stDate)>0 ||recordDate.compareTo(stDate) == 0) & (recordDate.compareTo(eDate)<0||recordDate.compareTo(eDate)==0)){
				result = result & true;
				System.out.println("RecordDate " + recordDate);
				System.out.println("RecordDatecomp " + result);
			} else {
				result = result & false;
				System.out.println("RecordDate " + recordDate);
				System.out.println("RecordDatecomp " + result);
			}
		}
		return result;
		
	}
	


	
	public boolean verifyBillableSalesDisplayedForSelectedDateRange(String startDate, String endDate) throws ParseException{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date stDate = df.parse(startDate);
		Date eDate = df.parse(endDate);
		List<WebElement> recordDateList = driver.findElements(By.xpath("//table[@id='billable_sales_table']/tbody/tr/td[2]/span"));
		boolean result = true;
		for(WebElement record : recordDateList ){
			String date = record.getText().split("\\|")[0];
			Date recordDate = df.parse(date);
			if((recordDate.compareTo(stDate)>0 ||recordDate.compareTo(stDate) == 0) & (recordDate.compareTo(eDate)<0||recordDate.compareTo(eDate)==0)){
				result = result & true;
				System.out.println("RecordDate" + recordDate);
				System.out.println("RecordDatecomp" + result);
			} else {
				result = result & false;
				System.out.println("RecordDate" + recordDate);
				System.out.println("RecordDatecomp" + result);
			}
		}
		return result;
	}
	
	public boolean verifyOtherReceiptDisplayedForSelectedDateRange(String startDate, String endDate) throws ParseException{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date stDate = df.parse(startDate);
		Date eDate = df.parse(endDate);
		List<WebElement> recordDateList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[2]/span"));
		boolean result = true;
		for(WebElement record : recordDateList ){
			String date = record.getText().split("\\|")[0];
			Date recordDate = df.parse(date);
			if((recordDate.compareTo(stDate)>0 ||recordDate.compareTo(stDate) == 0) & (recordDate.compareTo(eDate)<0||recordDate.compareTo(eDate)==0)){
				result = result & true;
				System.out.println("RecordDate" + recordDate);
				System.out.println("RecordDatecomp" + result);
			} else {
				result = result & false;
				System.out.println("RecordDate" + recordDate);
				System.out.println("RecordDatecomp" + result);
			}
		}
		return result;
		
	}
	
	public boolean verifyTaxExemptSalesDisplayedForSelectedOrganization(String organization){
		List<WebElement> organizationList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[7]/span"));
		boolean result = true;
		for(WebElement orgName : organizationList){
			System.out.println("Organization Name "+ orgName.getText().trim());
			result = result & orgName.getText().trim().equals(organization);
		}
		return result;
		
	}
	
	public boolean verifyOtherReceiptsDisplayedForSelectedOrganization(String organization){
		List<WebElement> organizationList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[6]/span"));
		boolean result = true;
		for(WebElement orgName : organizationList){
			System.out.println("Organization Name "+ orgName.getText().trim());
			result = result & orgName.getText().trim().equals(organization);
		}
		return result;
		
	}
	
	public boolean verifyTaxExemptSalesDisplayedForSelectedUser(String user){
		List<WebElement> userList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[6]"));
		boolean result = true;
		for(WebElement userName : userList){
			result = result & userName.getText().trim().equals(user);
		}
		return result;
	}
	
	public boolean verifyOtherReceiptDisplayedForSelectedUser(String user){
		List<WebElement> userList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[5]"));
		boolean result = true;
		for(WebElement userName : userList){
			result = result & userName.getText().trim().equals(user);
		}
		return result;
	}
	
	public boolean verifyTaxExemptSalesDisplayedForSelectedDepositStatus(String status){
		List<WebElement> statusList = driver.findElements(By.xpath("//table[@id='tax_exempt_table']/tbody/tr/td[17]"));
		boolean result = true;
		for(WebElement depositStatus : statusList){
			System.out.println("depositStatus "+ depositStatus.getText());
			result = result & depositStatus.getText().trim().equals(status);
		}
		return result;
	}
	
	public boolean verifyOtherReceiptDisplayedForSelectedDepositStatus(String status){
		List<WebElement> statusList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[9]"));
		boolean result = true;
		for(WebElement depositStatus : statusList){
			System.out.println("depositStatus "+ depositStatus.getText());
			result = result & depositStatus.getText().trim().equals(status);
		}
		return result;
	}
	
	public void selectTaxExemptSaleOrganizationNameFromAutoCompleteSuggestions(String searchText)
	{
		System.out.println("searchText "+searchText);
		for(WebElement suggestion : EditTaxExemptSalesPopUp_Organization_Suggestions_List){
			System.out.println("suggestion TaxExemptSales "+ suggestion.getText());
			if(suggestion.getText().trim().contains(searchText)){
				System.out.println("True");
				suggestion.click();
				break;
			}
		}
	}
	
	public void selectBillableSaleOrganizationNameFromAutoCompleteSuggestions(String searchText){
		for(WebElement suggestion : EditBillableSalesPopUp_Organization_Suggestions_List){
			System.out.println("searchText "+searchText);
			System.out.println("suggestion "+ suggestion.getText());
			if(suggestion.getText().trim().contains(searchText)){
				suggestion.click();
				break;
			}
		}
	}
	
	//Other Receipts:
	public boolean verifyOtherReceiptsDateInAscendingOrder() throws ParseException{
		List<WebElement>dateList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[2]/span"));
		List<String>dateValueList = getDateFromWebElements(dateList);
		return Base.verifyDateInAscendingOrder(dateValueList);
	}
	
	public boolean verifyOtherReceiptsDateInDescendingOrder() throws ParseException{
		List<WebElement>dateList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[2]/span"));
		List<String>dateValueList = getDateFromWebElements(dateList);
		return Base.verifyDateInDescendingOrder(dateValueList);
	}
	
	public boolean verifyOtherReceiptsRegisterInDescendingOrder() throws ParseException{
		List<WebElement>registerList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[3]/span"));
		List<String>registerValueList = Base.getTextListFromWebElements(registerList);
		return Base.verifyBigIntInDescendingOrder(registerValueList);
	}
	
	public boolean verifyOtherReceiptsRegisterInAscendingOrder() throws ParseException{
		List<WebElement>registerList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[3]/span"));
		List<String>registerValueList = Base.getTextListFromWebElements(registerList);
		return Base.verifyBigIntInAsscendingOrder(registerValueList);
	}

	public boolean verifyOtherReceiptsAmountInDescendingOrder() throws ParseException{
		List<WebElement>amountList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[4]"));
		List<String>amountValueList = Base.getTextListFromWebElements(amountList);
		return Base.verifyAmountIsInDescendingOrder(amountValueList);
	}
	
	public boolean verifyOtherReceiptsAmountInAscendingOrder() throws ParseException{
		List<WebElement>amountList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[4]"));
		List<String>amountValueList = Base.getTextListFromWebElements(amountList);
		return Base.verifyAmountIsInAscendingOrder(amountValueList);
	}
	
	public boolean verifyOtherReceiptsUserInDescendingOrder() throws ParseException{
		List<WebElement>userList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[5]/span[1]"));
		List<String>userValueList = Base.getTextListFromWebElements(userList);
		return Base.verifyStringInDescendingOrder(userValueList);
	}
	
	public boolean verifyOtherReceiptsUserInAscendingOrder() throws ParseException{
		List<WebElement>userList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[5]/span[1]"));
		List<String>userValueList = Base.getTextListFromWebElements(userList);
		return Base.verifyStringInAsscendingOrder(userValueList);
	}
	
	public boolean verifyOtherReceiptsOrganizationInDescendingOrder() throws ParseException{
		List<WebElement>organizationList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[6]/span[1]"));
		List<String>organizationValueList = Base.getTextListFromWebElements(organizationList);
		return Base.verifyStringInDescendingOrder(organizationValueList);
	}
	
	public boolean verifyOtherReceiptsOrganizationInAscendingOrder() throws ParseException{
		List<WebElement>organizationList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[6]/span[1]"));
		List<String>organizationValueList = Base.getTextListFromWebElements(organizationList);
		return Base.verifyStringInAsscendingOrder(organizationValueList);
	}
	
	public boolean verifyOtherReceiptsTypeInDescendingOrder() throws ParseException{
		List<WebElement>typeList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[7]/span[1]"));
		List<String>typeValueList = Base.getTextListFromWebElements(typeList);
		return Base.verifyStringInDescendingOrder(typeValueList);
	}
	
	public boolean verifyOtherReceiptsTypeInAscendingOrder() throws ParseException{
		List<WebElement>typeList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[7]/span[1]"));
		List<String>typeValueList = Base.getTextListFromWebElements(typeList);
		return Base.verifyStringInAsscendingOrder(typeValueList);
	}
	
	public boolean verifyOtherReceiptsCheckNumberInDescendingOrder() throws ParseException{
		List<WebElement>checkNumberList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[8]/span"));
		List<String>checkNumberValueList = Base.getTextListFromWebElements(checkNumberList);
		return Base.verifyBigIntInDescendingOrder(checkNumberValueList);
	}
	
	public boolean verifyOtherReceiptsCheckNumberInAscendingOrder() throws ParseException{
		List<WebElement>checkNumberList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[8]/span"));
		List<String>checkNumberValueList = Base.getTextListFromWebElements(checkNumberList);
		return Base.verifyBigIntInAsscendingOrder(checkNumberValueList);
	}
	
	public boolean verifyOtherReceiptsDepositStatusInDescendingOrder() throws ParseException{
		List<WebElement>depositStatusList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[9]"));
		List<String>depositValueValueList = Base.getTextListFromWebElements(depositStatusList);
		return Base.verifyStringInDescendingOrder(depositValueValueList);
	}
	
	public boolean verifyOtherReceiptsDepositStatusInAscendingOrder() throws ParseException{
		List<WebElement>depositStatusList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[9]"));
		List<String>depositValueValueList = Base.getTextListFromWebElements(depositStatusList);
		return Base.verifyStringInAsscendingOrder(depositValueValueList);
	}
	
	public boolean verifyOtherReceiptsDisplayedForSelectedDateRange(String startDate, String endDate) throws ParseException{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date stDate = df.parse(startDate);
		Date eDate = df.parse(endDate);
		List<WebElement> recordDateList = driver.findElements(By.xpath("//table[@id='other_receipts_table']/tbody/tr/td[2]/span"));
		boolean result = true;
		for(WebElement record : recordDateList ){
			String date = record.getText().split("\\|")[0];
			Date recordDate = df.parse(date);
			if((recordDate.compareTo(stDate)>0 ||recordDate.compareTo(stDate) == 0) & (recordDate.compareTo(eDate)<0||recordDate.compareTo(eDate)==0)){
				result = result & true;
				System.out.println("RecordDate" + recordDate);
				System.out.println("RecordDatecomp" + result);
			} else {
				result = result & false;
				System.out.println("RecordDate" + recordDate);
				System.out.println("RecordDatecomp" + result);
			}
		}
		return result;
	}
	
	public GroupSalesPage otherReceiptsSelectStartdate(String date) throws InterruptedException{
		OtherReceipts_StartDate_TB.click();
		Thread.sleep(2000);
		int day = Base.getDayFromDate(date);
		int month = Base.getMonthFromDate(date);
		selectFutureMonthFromDatePicker(Base.getMonthName(month+1),3);
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[3]//tbody/tr//td[@data-month='"+month+"']/div[text()='"+day+"']")).click();
		return PageFactory.initElements(driver, GroupSalesPage.class);
	}
	
	public GroupSalesPage otherReceiptsSelectEndDate(String date) throws InterruptedException{
		OtherReceipts_EndDate_TB.click();
		Thread.sleep(2000);
		int day = Base.getDayFromDate(date);
		int month = Base.getMonthFromDate(date);
		selectMonthFromDatePicker(Base.getMonthName(month+1),4);
		driver.findElement(By.xpath("(//div[@class='xdsoft_calendar'])[4]//tbody/tr//td[@data-month='"+month+"']/div[text()='"+day+"']")).click();
		return PageFactory.initElements(driver, GroupSalesPage.class);
	}

}
