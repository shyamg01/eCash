package eCashPageClasses;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.GenericMethods;
import common.GlobalVariable;

public class AbstractPage 

{
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions action;
	public static JavascriptExecutor executor;
	
	
	 AbstractPage (WebDriver driver)
	{
		this.driver=driver;
		wait = new WebDriverWait(driver,15);
		action = new Actions(driver);
		executor = (JavascriptExecutor)driver;
	}
	//Method Reporter
		//This method will select a user from user dropdown	 
		public HomePage selectUser(String userId) throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException {
			HomePage homePage = new HomePage(driver);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOf(homePage.User_DD_BT));
			GenericMethods.clickOnElement(homePage.User_DD_BT,"homePage.User_DD_BT");

			wait.until(ExpectedConditions.visibilityOf(homePage.User_DD));
			GenericMethods.clickOnElement(driver.findElement(By.xpath("//div[@id='user-settings-dropdown']/div/a[text()='"+userId+"']")),"UserID");
//			driver.findElement(By.xpath("//div[@id='user-settings-dropdown']/div/a[text()='"+userId+"']")).click();
			wait.until(ExpectedConditions.visibilityOf(homePage.SelectedUserName_Label));
			return PageFactory.initElements(driver, HomePage.class);
		}
	
	// This method will use SSO login to select user for a specific store 
	public HomePage selectUserWithSSOLogin(String userId, String password)
			throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException {
		HomePage homePage = new HomePage(driver);
		/*wait.until(ExpectedConditions.visibilityOf(homePage.User_DD_BT));
		homePage.User_DD_BT.click();
		wait.until(ExpectedConditions.visibilityOf(homePage.User_DD));
		driver.findElement(By.xpath("//ul[@id='user-settings-dropdown']/li/a[text()='SSO Login']")).click();*/
		if(GlobalVariable.loginMode.equals("SSO")){
			homePage.ssoLogin(userId, password);
		}else{
			selectUser(userId);
		}
		return PageFactory.initElements(driver, HomePage.class);
	}
	//Go to Group Sales Page
	
	//Method Reporter
	//This method will select a store from locations dropdown	 
	public HomePage selectLocation(String storeId) throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException {
		HomePage homePage = new HomePage(driver);
		wait.until(ExpectedConditions.visibilityOf(homePage.Locations_DD_BT));
		GenericMethods.clickOnElement(homePage.Locations_DD_BT,"homePage.Locations_DD_BT");
		wait.until(ExpectedConditions.visibilityOf(homePage.Locations_DD));
		GenericMethods.clickOnElement(driver.findElement(By.xpath("//div[@id='store-locations-dropdown']/li/a[contains(text(),'"+storeId+"')]")), "Store ID");
		Thread.sleep(3000);
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	//Go to Group Sales Page
	 public GroupSalesPage goToGroupSalesPage() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	 {
		 HomePage homePage = new HomePage(driver);
		 GenericMethods.clickOnElement(wait.until(ExpectedConditions.elementToBeClickable(homePage.Menu_DD_BT)), "homePage.Menu_DD_BT");
		 Thread.sleep(1500);
		 wait.until(ExpectedConditions.visibilityOf(homePage.GroupSales_BT));
		 GenericMethods.clickOnElement(homePage.GroupSales_BT, "homePage.GroupSales_BT");
		 GroupSalesPage groupSalesPage=new GroupSalesPage(driver);
		 wait.until(ExpectedConditions.visibilityOf(groupSalesPage.GroupSales_Label));
		 return  PageFactory.initElements(driver, GroupSalesPage.class);
	 }
	 
	 
	 
		//Go to Group Sales Page
	 public DrawerCountDownPage goToDrawerCountDownPage() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	 {
		 HomePage homePage = new HomePage(driver);
		 GenericMethods.clickOnElement(wait.until(ExpectedConditions.elementToBeClickable(homePage.Menu_DD_BT)), "homePage.Menu_DD_BT");
		 Thread.sleep(1500);
		 wait.until(ExpectedConditions.visibilityOf(homePage.DrawerCountdown_BT));
		 GenericMethods.clickOnElement(homePage.DrawerCountdown_BT, "homePage.DrawerCountdown_BT");
		 DrawerCountDownPage drawerCountDownPage=new DrawerCountDownPage(driver);
		 wait.until(ExpectedConditions.visibilityOf(drawerCountDownPage.DrawerCount_Label));
		 return  PageFactory.initElements(driver, DrawerCountDownPage.class);
	 }
	 
		//Go to Skims Page
	 public SkimsPage goToSkimsPage() throws InterruptedException, RowsExceededException, BiffException, WriteException, IOException
	 {
		 HomePage homePage = new HomePage(driver);
		 GenericMethods.clickOnElement(wait.until(ExpectedConditions.elementToBeClickable(homePage.Menu_DD_BT)), "homePage.Menu_DD_BT");
		 Thread.sleep(1500);
		 wait.until(ExpectedConditions.visibilityOf(homePage.Skims_BT));
		 GenericMethods.clickOnElement(homePage.Skims_BT, "homePage.Skims_BT");
		 SkimsPage skimsPage=new SkimsPage(driver);
		 wait.until(ExpectedConditions.visibilityOf(skimsPage.Skims_Label));
		 Thread.sleep(2500);
		 return  PageFactory.initElements(driver, SkimsPage.class);
	 }
	 
		//Go to Skims Page
	public SafeCountPage goToSafeCountPage() throws InterruptedException,
			RowsExceededException, BiffException, WriteException, IOException {
		 HomePage homePage = new HomePage(driver);
		 GenericMethods.clickOnElement(wait.until(ExpectedConditions.elementToBeClickable(homePage.Menu_DD_BT)), "homePage.Menu_DD_BT");
		 Thread.sleep(1500);
		 wait.until(ExpectedConditions.visibilityOf(homePage.SafeCount_BT));
		 GenericMethods.clickOnElement(homePage.SafeCount_BT, "homePage.SafeCount_BT");
		 SafeCountPage safeCountPage=new SafeCountPage(driver);
		 wait.until(ExpectedConditions.visibilityOf(safeCountPage.SafeCount_Label));
		 Thread.sleep(2500);
		 return  PageFactory.initElements(driver, SafeCountPage.class);
	 }
	

		public void selectMonthFromDatePicker(String monthName,int calIndex) throws InterruptedException
		{
			Thread.sleep(3000);
			String selectedMonth = driver.findElement(By.xpath("(//div[@class='xdsoft_mounthpicker'])["+calIndex+"]/div[1]/span")).getText();
			while (!selectedMonth.equals(monthName))
			{
					driver.findElement(By.xpath("(//div[@class='xdsoft_mounthpicker'])["+calIndex+"]/button[@class='xdsoft_prev']")).click();
					selectedMonth = driver.findElement(By.xpath("(//div[@class='xdsoft_mounthpicker'])["+calIndex+"]/div[contains(@class,'xdsoft_month')]/span")).getText();
					System.out.println("monthName found "+selectedMonth);
			}
		}
		
		public void selectFutureMonthFromDatePicker(String monthName,int calIndex) throws InterruptedException
		{
			System.out.println("monthName next "+ monthName);
			System.out.println("calIndex "+calIndex);
			Thread.sleep(3000);
			String selectedMonth = driver.findElement(By.xpath("(//div[@class='xdsoft_mounthpicker'])["+calIndex+"]/div[1]/span")).getText();
			System.out.println("(//div[@class='xdsoft_mounthpicker'])["+calIndex+"]/div[1]/span");
			System.out.println("(//div[@class='xdsoft_mounthpicker'])["+calIndex+"]/button[@class='xdsoft_next']");
			System.out.println("Selected month"+selectedMonth);
			while (!selectedMonth.equals(monthName))
			{
				
					driver.findElement(By.xpath("(//div[@class='xdsoft_mounthpicker'])["+calIndex+"]/button[@class='xdsoft_next']")).click();
					selectedMonth = driver.findElement(By.xpath("(//div[@class='xdsoft_mounthpicker'])["+calIndex+"]/div[contains(@class,'xdsoft_month')]/span")).getText();
					System.out.println("monthName found "+selectedMonth);
			}
		}
		
		public HomePage signOut() throws InterruptedException {
			try {
				GenericMethods.clickOnElement(driver.findElement(By.xpath("//div[@id='signOut']")), "Sign Out");
	    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='htmlButton']/span[text()='Yes']"))).click();
				AbstractTest.stepValue=AbstractTest.stepValue+1;
				Thread.sleep(5000);
			} catch (Exception e) {}
			return PageFactory.initElements(driver, HomePage.class);
		}
	 
		
}
