package common;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import eCashPageClasses.AbstractTest;



public class Listener  implements ITestListener 
{
	WebDriver driver=null;
	String browser=null;

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onTestFailure(ITestResult result) 
	{
		//Get the  the driver
		driver=AbstractTest.getDriver();
		//Get the name of the browser
		browser=AbstractTest.getBrowserName();
		//Get the name of test case
			/*System.out.println(result.getThrowable());
			System.out.println((result.getThrowable()).getMessage());*/
			try {
				Reporter.reportTestFailure(browser,result.getThrowable().getMessage(), "Fail");
			} catch (RowsExceededException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
     //The below method will save the screen shot in d drive with name "TCXXX.png"
        try {
        	FileUtils.copyFile(scrFile, new File("FailureScreenshot/"+browser+"_"+AbstractTest.tcName+(AbstractTest.stepValue-1)+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
	}

	
}
