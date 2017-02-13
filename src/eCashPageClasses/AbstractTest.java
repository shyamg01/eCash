package eCashPageClasses;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import common.GenericMethods;
import common.GlobalVariable;
import common.ReadTestData;
import common.Reporter;
public class AbstractTest 

{
	protected static WebDriver driver;
	protected Properties prop;
	protected static WebDriverWait wait;
	protected Actions action;
	protected static String browser=null;
	public static int stepValue=1;
	public static String tcName=null;
	
	public static void takeSnapShot()
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	     //The below method will save the screen shot in d drive with name "TCXXX.png"
	        try {
	        	FileUtils.copyFile(scrFile, new File("FailureScreenshot/"+browser+"_"+AbstractTest.tcName+(AbstractTest.stepValue-1)+".png"));
			} catch (IOException e) 
	        {
				e.printStackTrace();
			}
	}
	
	public static WebDriver getDriver()
	{
		return driver;
		
	}
	
   public static String getBrowserName()
   {
	return browser;
	   
   }
	
   @BeforeSuite(groups={"A","B","Smoke"})
	public void beforeTest() throws WriteException, IOException
	{
		Reporter.createExcelReport();
		FileUtils.cleanDirectory(new File("FailureScreenshot")); 
	}
   
   @Parameters({"browser","StoreId"})
   @BeforeTest(groups={"A","B","Smoke"})
   public void initializeTestData(String browser,String StoreId){
	   
	   GlobalVariable.StoreId=StoreId;
	   if(browser.equalsIgnoreCase("FF")){
		   ReadTestData.getTestScenarioSheet("TestData/eInventoryTestData_Firefox.xls");
	   }else if(browser.equalsIgnoreCase("IE")){
		   ReadTestData.getTestScenarioSheet("TestData/eInventoryTestData_IE.xls");
	   }else if(browser.equalsIgnoreCase("Chrome")){
		   ReadTestData.getTestScenarioSheet("TestData/eInventoryTestData_Chrome.xls");
	   }
	   
   }
   
	@Parameters({"browser"})
	@BeforeMethod(groups={"A","B","Smoke"})
	public void beforeMethod(String browser) throws Exception,TimeoutException
		{
			this.browser=browser;
		//initialize the driver and launch the application
			if(browser.equalsIgnoreCase("IE"))
			{
				System.setProperty("webdriver.ie.driver","Drivers/IEDriverServer.exe");
				driver=new InternetExplorerDriver();
				wait = new WebDriverWait(driver,60);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
				action = new Actions(driver);
				driver.get("https://qa.ebos.qsrsoft.com/#");
				driver.manage().window().maximize();
			}
			else if(browser.equalsIgnoreCase("FF"))
			{
				ProfilesIni profile=new ProfilesIni();
				FirefoxProfile fprofile=new FirefoxProfile();
				fprofile=profile.getProfile("MyProfile");
				driver =new FirefoxDriver(fprofile);
				action = new Actions(driver);
				wait = new WebDriverWait(driver,60);					
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
				driver.get("https://qa.ebos.qsrsoft.com/#");
				driver.manage().window().maximize();
			}
			else if(browser.equalsIgnoreCase("Chrome"))
			{	
                ChromeOptions options = new ChromeOptions();
                options.addArguments("chrome.switches","--disable-extensions");
                options.addArguments("test-type");              
				System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
				driver=new ChromeDriver(options);
				wait = new WebDriverWait(driver,15);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
				action = new Actions(driver);
				driver.get("https://qa.ebos.qsrsoft.com/#");
//				Reporter.log("Step 1 : Application is launched");
				driver.manage().window().maximize();
				
			}
			else if(browser.equalsIgnoreCase("Safari"))
			{	
				driver = new SafariDriver();
			    action = new Actions(driver);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
				driver.get("http://www.google.com/");
				driver.manage().window().maximize();

			}
					
		}
	
	@AfterTest(groups={"A","B","Smoke"})
	public void afterTest() throws BiffException, WriteException, IOException
	{
		Reporter.updateTotalCount(browser);

	}
	
	@AfterSuite
	public void afterSuite() throws RowsExceededException, BiffException, WriteException, IOException
	{
//		Reporter.updateTotalCount();
	}
	
	@AfterMethod(groups={"A","B","Smoke"}) 
	
	public void afterMethod() throws Exception, BiffException, IOException, WriteException, InterruptedException{ 
	
    /*    try { 
                driver.findElement(By.xpath("//div[@id='signOut']")).click(); //(file://driver.findelement(by.xpath(/); 
                Thread.sleep(2000); 
                Alert alert = driver.switchTo().alert(); 
                alert.accept(); 
                Thread.sleep(5000); 
        } catch (Exception e) { 
        } */
        try { 
        	GenericMethods.clickOnElement(driver.findElement(By.xpath("//div[@id='signOut']")), "Sign Out");
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='htmlButton']/span[text()='Yes']"))).click();
    		Thread.sleep(5000);
    } catch (Exception e) { 
    } 
    	tcName=null;
		stepValue=1;
		Reporter.updateTestReport(browser); 
		
        driver.close(); 
        driver.quit(); 
}


}
