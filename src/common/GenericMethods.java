package common;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import eCashPageClasses.AbstractTest;


public class GenericMethods extends AbstractTest
{
	
	public static void navigateTo(String url) throws RowsExceededException, BiffException, WriteException, IOException
	{

			driver.navigate().to(url);
			Reporter.reportPassResult(browser,"Successfully navigated to "+url, "Pass");
//			Reporter.log("Step "+stepValue+": "+elementName+" is successfully clicked");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;

	}
	
	public static String  getText(WebElement element,String elementName) throws RowsExceededException, BiffException, WriteException, IOException
	{
		
		String returnText= element.getText();
		Reporter.reportPassResult(browser,"Get Text successfull for "+elementName, "Pass");
		return returnText;
		
	}
	
	public static boolean textCompare(String expText,String actText) throws RowsExceededException, BiffException, WriteException, IOException
	{
		if(expText.equalsIgnoreCase(actText))
		{
			Reporter.reportPassResult(browser,expText+" is Successfully comapred", "Pass");
			return true;
		}
		else
		{
			Reporter.reportTestFailure(browser,expText+" is not Successfully comapred", "Fail");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
			AbstractTest.takeSnapShot();
			return false;
		}
		
	}

	
	public static void clickOnElement(WebElement element,String elementName) throws RowsExceededException, BiffException, WriteException, IOException
	{

			element.click();
			Reporter.reportPassResult(browser,elementName+" is successfully clicked", "Pass");
//			Reporter.log("Step "+stepValue+": "+elementName+" is successfully clicked");
			// // AbstractTest.stepValue=AbstractTest.stepValue+1;

	}
	
	public static void enterValueInElement(WebElement element,String elementName,String value) throws RowsExceededException, BiffException, WriteException, IOException
	{

			element.sendKeys(value);
			Reporter.reportPassResult(browser,value+" is successfully inserted in "+elementName, "Pass");
//			Reporter.log("Step "+stepValue+": "+value+" is successfully inserted in "+elementName);
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
	
	}
	
	public static void clearValueOfElement(WebElement element,String elementName) throws RowsExceededException, BiffException, WriteException, IOException
	{

			element.clear();
			Reporter.reportPassResult(browser,"Value of "+elementName+" is successfully cleared", "Pass");
//			Reporter.log("Step "+stepValue+": "+"Value of "+elementName+" is successfully cleared");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;

	}
	
	public static void selectTextFormDropDownElement(WebElement element,String elementName,String text) throws RowsExceededException, BiffException, WriteException, IOException
	{

			Select select = new Select(element);
			select.selectByVisibleText(text);
			Reporter.reportPassResult(browser,text+" is successfully selected from the"+elementName, "Pass");
//			Reporter.log("Step "+stepValue+": "+text+" is successfully selected from the"+elementName+" dropdown");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
	
	}
	
	public static void selectValueFormDropDownElement(WebElement element,String elementName,String value) throws RowsExceededException, BiffException, WriteException, IOException
	{

			Select select = new Select(element);
			select.selectByValue(value);
			Reporter.reportPassResult(browser,value+" is successfully selected from the"+elementName, "Pass");
//			Reporter.log("Step "+stepValue+": "+text+" is successfully selected from the"+elementName+" dropdown");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
	
	}
	
	public static void selectIndexFormDropDownElement(WebElement element,String elementName,int index) throws RowsExceededException, BiffException, WriteException, IOException
	{
	
			Select select = new Select(element);
			select.selectByIndex(index);
			Reporter.reportPassResult(browser,index+" index value is successfully selected from the"+elementName, "Pass");
//			Reporter.log("Step "+stepValue+": "+index+" index value is successfully selected from the"+elementName+" dropdown");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
		
	}
	
	public static boolean isElementDisplayed(By element,String elementName) throws RowsExceededException, BiffException, WriteException, IOException
	{
		Boolean result=Base.isElementDisplayed(element);
		if(result)
		{
			Reporter.reportPassResult(browser,elementName+" is present", "Pass");
//			Reporter.log("Step "+stepValue+": "+elementName+" is present");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
			return result;
		}
		else
		{
			Reporter.reportTestFailure(browser,elementName+" is not present", "Fail");
			AbstractTest.takeSnapShot();
//			Reporter.log("Step "+stepValue+": "+elementName+" is not present");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
			return result;
		}
		
		
	}
	
	public static boolean isElementDisplayed(WebElement element,String elementName) throws RowsExceededException, BiffException, WriteException, IOException
	{
		Boolean result=Base.isElementDisplayed(element);
		if(result)
		{
			Reporter.reportPassResult(browser,elementName+" is present", "Pass");
//			Reporter.log("Step "+stepValue+": "+elementName+" is present");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
			return result;
		}
		else
		{
			Reporter.reportTestFailure(browser,elementName+" is not present", "Fail");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
			AbstractTest.takeSnapShot();
			return result;
		}
		
		
	}
	
	/*
	public static void clickOnElement(WebElement element,String elementName)
	{
		try
		{
			element.click();
			Reporter.log("Step "+stepValue+": "+elementName+" is successfully clicked");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
		} catch (Exception e)
		{
			Reporter.log("Step "+stepValue+": "+elementName+" is not successfully clicked");
			Assert.assertTrue(false);
		}
	}
	
	public static void enterValueInElement(WebElement element,String elementName,String value)
	{
		try
		{
			element.sendKeys(value);
			Reporter.log("Step "+stepValue+": "+value+" is successfully inserted in "+elementName);
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
		} catch (Exception e)
		{
			Reporter.log("Step "+stepValue+": "+value+" is not successfully inserted in "+elementName);
			Assert.assertTrue(false);
		}
	}
	
	public static void clearValueOfElement(WebElement element,String elementName)
	{
		try
		{
			element.clear();
			Reporter.log("Step "+stepValue+": "+"Value of "+elementName+" is successfully cleared");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
		} catch (Exception e)
		{
			Reporter.log("Step "+stepValue+": "+"Value of "+elementName+" is not successfully cleared");
			Assert.assertTrue(false);
		}
	}
	
	public static void selectValueFormDropDownElement(WebElement element,String elementName,String text)
	{
		try
		{
			Select select = new Select(element);
			select.selectByVisibleText(text);
			Reporter.log("Step "+stepValue+": "+text+" is successfully selected from the"+elementName+" dropdown");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
		} catch (Exception e)
		{
			Reporter.log("Step "+stepValue+": "+text+" is successfully selected from the"+elementName+" dropdown");
			Assert.assertTrue(false);
		}
	}
	
	public static void selectIndexFormDropDownElement(WebElement element,String elementName,int index)
	{
		try
		{
			Select select = new Select(element);
			select.selectByIndex(index);
			Reporter.log("Step "+stepValue+": "+index+" index value is successfully selected from the"+elementName+" dropdown");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
		} catch (Exception e)
		{
			Reporter.log("Step "+stepValue+": "+index+" index value is not successfully selected from the"+elementName+" dropdown");
			Assert.assertTrue(false);
		}
	}
	
	public static boolean isElementDisplayed(By element,String elementName)
	{
		Boolean result=Base.isElementDisplayed(element);
		if(result)
		{
			Reporter.log("Step "+stepValue+": "+elementName+" is present");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
			return result;
		}
		else
		{
			Reporter.log("Step "+stepValue+": "+elementName+" is not present");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
			return result;
		}
		
		
	}
	
	public static boolean isElementDisplayed(WebElement element,String elementName)
	{
		Boolean result=Base.isElementDisplayed(element);
		if(result)
		{
			Reporter.log("Step "+stepValue+": "+elementName+" is present");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
			return result;
		}
		else
		{
			Reporter.log("Step "+stepValue+": "+elementName+" is not present");
			// AbstractTest.stepValue=AbstractTest.stepValue+1;
			return result;
		}
		
		
	}*/
	

}
