package common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import eCashPageClasses.AbstractPage;
import eCashPageClasses.AbstractTest;

public class Base extends AbstractTest {

	// Method to return the today's date in MM/DD/YYYY format
	public static String returnTodayDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String todayDate = dateFormat.format(date);
		return todayDate;
	}

	// To verify element is displayed or not
	public static boolean isElementDisplayed(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (Exception e) {
			System.out.println("Catch " + element);
			return false;
		}
	}
	
	// This overloaded method will take the locater of element as argument and verify element is displayed or not
	public static boolean isElementDisplayed(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Catch " + by);
			return false;
		}
	}

	// To generate five digit random number
	public static String randomNumberFiveDigit() {
		Random r = new Random(System.currentTimeMillis());
		int i = 10000 + r.nextInt(20000);
		return Integer.toString(i);
	}
	
	public static String randomString(char[] characterSet, int length) {
	    Random random = new SecureRandom();
	    char[] result = new char[length];
	    for (int i = 0; i < result.length; i++) {
	        // picks a random index out of character set > random character
	        int randomCharIndex = random.nextInt(characterSet.length);
	        result[i] = characterSet[randomCharIndex];
	    }
	    return new String(result);
	}

	
	//Generate N Digit Random Number
	public static int generateNdigitRandomNumber(int n){
		Random randGen = new Random();
		Double startNum = Math.pow(10, n-1);
		int num1 = startNum.intValue();
		Double endNum = Math.pow(10, n);
		int num2 = endNum.intValue();
		int range = num2 - num1 + 1;
		int randomNum = randGen.nextInt(range) + num1;
		return randomNum;
	}
	
	//Generate N Digit Random Number
	public static BigDecimal generateDecimalRandomNumber(int n){
		BigDecimal randomNumber = new BigDecimal(generateNdigitRandomNumber(n));
		System.out.println("randomNumber "+randomNumber);
		BigDecimal decimalNumber = randomNumber.divide(new BigDecimal(100));
		return decimalNumber;
	}
	
	
	
	public static String getMonthName(int Month) {
		String month;
		if (Month == 1) {
			month = "January";
		} else if (Month == 2) {
			month = "February";
		} else if (Month == 3) {
			month = "March";
		} else if (Month == 4) {
			month = "April";
		} else if (Month == 5) {
			month = "May";
		} else if (Month == 6) {
			month = "June";
		} else if (Month == 7) {
			month = "July";
		} else if (Month == 8) {
			month = "August";
		} else if (Month == 9) {
			month = "September";
		} else if (Month == 10) {
			month = "October";
		} else if (Month == 11) {
			month = "November";
		} else {
			month = "December";
		}
		return month;
	}
	
	// This method will take date as argument and return month from date
	public static int getMonthFromDate(String date) {
		String currentMonth = date.split("/")[0];
		if (currentMonth.startsWith("0")) {
			currentMonth = currentMonth.replace("0", "");
		}
		int month = ((Integer.parseInt(currentMonth)) - 1);
		return month;
	}
	// This method will take date as argument and return month from date
		public static int getCorrectMonthFromDate(String date) {
			String currentMonth = date.split("/")[0];
			if (currentMonth.startsWith("0")) {
				currentMonth = currentMonth.replace("0", "");
			}
			int month = ((Integer.parseInt(currentMonth)));
			return month;
		}


	// This method will take date as argument and return day from date
	public static int getDayFromDate(String date) {
		String currentDay = date.split("/")[1];
		if (currentDay.startsWith("0")) {
			currentDay = currentDay.replace("0", "");
		}
		int day = Integer.parseInt(currentDay);
		return day;
	}
	
	// This method will take date as argument and return Year from date
	public static int getYearFromDate(String date) {
		String currentYear = date.split("/")[2];
		return Integer.parseInt(currentYear);
	}
	// This method will take hour and minutes as parameter and return 15 minutes earlier time
	public static String get15MinuteTimeSlice(int hour, int minute) {
		int nextMinute;
		if (minute == 0) {
			minute = 60;
			hour--;
		}
		nextMinute = minute - 15;
		String hourValue = String.valueOf(hour);
		if (hourValue.length() == 1) {
			hourValue = "0" + hourValue;
		}
		String minuteValue = String.valueOf(nextMinute);
		if (minuteValue.equals("0")) {
			minuteValue = "00";
		}
		return hourValue + ":" + minuteValue;

	}
	
	// This method will take hour and minutes as parameter and return next 15 minutes time
	public static String getNext15MinuteTimeSlice(int hour, int minute) {
		int nextMinute;
		if (minute == 45) {
			nextMinute = 0;
			hour++;
		}else
		{nextMinute = minute + 15;}
		String hourValue = String.valueOf(hour);
		if (hourValue.length() == 1) {
			hourValue = "0" + hourValue;
		}
		String minuteValue = String.valueOf(nextMinute);
		if (minuteValue.equals("0")) {
			minuteValue = "00";
		}
		return hourValue + ":" + minuteValue;

	}
		
	// This method will return hour value from the time(HH:MM)
	public static int getHourFromTime(String time) {
		return Integer.parseInt(time.split(":")[0]);
	}

	// This method will return Minutes value from the time(HH:MM)
	public static int getMinuteFromTime(String time) {
		return Integer.parseInt(time.split(":")[1]);
	}
	
	public static String get15MinuteTimeStamp(int hour, int minutes) {
		String timeStamp ="";
		if (minutes >= 0 && minutes < 15) {
			timeStamp = (hour + ":" + "00");
		} else if (minutes >= 15 && minutes < 30) {
			timeStamp = (hour + ":" + "15");
		} else if (minutes >= 30 && minutes < 45) {
			timeStamp = (hour + ":" + "30");
		} else if (minutes >= 45 && minutes <= 59) {
			timeStamp = (hour + ":" + "45");
		}

		return timeStamp;
	}
	
	public static String getCurrentTimeStamp() {
		String currentTime = new SimpleDateFormat("HH:mm").format(new Date());
		String timeStamp ="";
		int hour = getHourFromTime(currentTime);
		int minutes = Integer.parseInt(currentTime.split(":")[1]);
		if (minutes >= 0 && minutes < 15) {
			timeStamp = (hour + ":" + "00");
		} else if (minutes >= 15 && minutes < 30) {
			timeStamp = (hour + ":" + "15");
		} else if (minutes >= 30 && minutes < 45) {
			timeStamp = (hour + ":" + "30");
		} else if (minutes >= 45 && minutes <= 59) {
			timeStamp = (hour + ":" + "45");
		}

		return timeStamp;
	}
	
	public static void executeJavaScript(String script){
		((JavascriptExecutor) driver).executeScript(script);
	}
	
	//Get date in yyyymmdd format
	public static String getFormattedDate(String date){
		int day = getDayFromDate(date);
		String month = date.split("/")[0];
		String year = date.split("/")[2];
		String newdate;
		if (day > 0 && day < 10) {
			newdate = "0" + day;
		} else {
			newdate = String.valueOf(day);
		}
		String formattedDate =  year+month + newdate;
		return formattedDate;
		
	}
	
	// Get date in mmddyyyy format
	public static String getFormattedDate1(String date) {
		int day = getDayFromDate(date);
		String month = date.split("/")[0];
		String year = date.split("/")[2];
		String newdate;
		if (day > 0 && day < 10) {
			newdate = "0" + day;
		} else {
			newdate = String.valueOf(day);
		}
		String formattedDate = month + newdate+ year;
		return formattedDate;
	}
	
	public static void scrollToTheElement(WebElement element){
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}
	
	public static List<String> getTextListFromWebElements(List<WebElement> elementList){
		ArrayList<String>textValueList = new ArrayList<String>();
		for(WebElement element : elementList){
			textValueList.add(element.getText().replace("-", "").trim());
			System.out.println("textValueList "+textValueList);
		}
		return textValueList;
	}
	
	public static boolean verifyBigIntInAsscendingOrder(List<String> stringList)throws ParseException {
		boolean result = true;
		for (int i = 0; i < stringList.size(); i++) {
			if (i < stringList.size() - 1) {
				if (!stringList.get(i).equals("") && !stringList.get(i + 1).equals("")) {
					BigInteger Value1 = new BigInteger(stringList.get(i).replace("-", ""));
					System.out.println("Ascending Value2 " + Value1);
					BigInteger Value2 = new BigInteger(stringList.get(i + 1).replace("-", ""));
					System.out.println("Ascending Value2 " + Value2);
					System.out.println("Compare " + Value1.compareTo(Value2));
					System.out.println("Ascending result is "+ (Value1.compareTo(Value2) < 0 || Value1.compareTo(Value2) == 0));
					if ((Value1.compareTo(Value2) < 0 || Value1	.compareTo(Value2) == 0)) {
						result = result & true;
					} else {
						result = result & false;
					}
				}
			}
		}
		return result;
	}
	
	public static boolean verifyBigIntInDescendingOrder(List<String> stringList) throws ParseException{
		boolean result = true;
		for(int i=0;i< stringList.size();i++){
			if(i<stringList.size()-1){
				if(!stringList.get(i).equals("") && !stringList.get(i+1).equals("")){
				BigInteger Value1 = new BigInteger(stringList.get(i).replace("-", ""));
				System.out.println("Descending Value2 "+Value1);
				BigInteger Value2  = new BigInteger(stringList.get(i+1).replace("-", ""));
				System.out.println("Descending Value2 "+Value2);
				System.out.println("Descending result is "+ (Value1.compareTo(Value2)>0 || Value1.compareTo(Value2) == 0));
				if((Value1.compareTo(Value2)>0 || Value1.compareTo(Value2) == 0)){
					result = result & true;
				}else{
					result = result & false;
					}
				}
			}
		}
		return result;
	}
	
	public static boolean verifyStringInDescendingOrder(List<String> stringList) throws ParseException{
		boolean result = true;
		for(int i=0;i< stringList.size();i++){
			if(i<stringList.size()-1){
				String stringValue1 = stringList.get(i);
				System.out.println("Descending stringValue1 "+stringValue1);
				String stringValue2 = stringList.get(i+1);
				System.out.println("Descending stringValue2 "+stringValue2);
				System.out.println("Descending result is "+ (stringValue1.compareToIgnoreCase(stringValue2)>0 || stringValue1.compareToIgnoreCase(stringValue2) == 0));
				if(stringValue1.compareToIgnoreCase(stringValue2)>0 || stringValue1.compareToIgnoreCase(stringValue2) == 0){
					result = result & true;
				}else{
					result = result & false;
					}
			}
		}
		return result;
	}
	
	public static boolean verifyStringInAsscendingOrder(List<String> stringList) throws ParseException{
		boolean result = true;
		for(int i=0;i< stringList.size();i++){
			if(i<stringList.size()-1){
				String stringValue1 = stringList.get(i);
				System.out.println("Asscending stringValue1 "+stringValue1);
				String stringValue2 = stringList.get(i+1);
				System.out.println("Asscending stringValue2 "+stringValue2);
				System.out.println("Asscending result is "+ (stringValue1.compareToIgnoreCase(stringValue2) < 0 || stringValue1.compareToIgnoreCase(stringValue2) == 0));
				if(stringValue1.compareToIgnoreCase(stringValue2) <0 || stringValue1.compareToIgnoreCase(stringValue2) == 0){
					result = result & true;
				}else{
					result = result & false;
					}
			}
		}
		return result;
	}
	
	public static boolean verifyDateInDescendingOrder(List<String>dateList) throws ParseException{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		boolean result = true;
		for(int i=0;i< dateList.size();i++){
			if(i<dateList.size()-1){
				String date1 = dateList.get(i);
				System.out.println("date1 "+date1);
				Date recordDate1 = df.parse(date1);
				String date2 = dateList.get(i+1);
				System.out.println("date2 "+date2);
				Date recordDate2 = df.parse(date2);
				System.out.println("result is "+ (recordDate1.after(recordDate2) ||recordDate1.equals(recordDate2)));
				if((recordDate1.compareTo(recordDate2)>0 ||recordDate1.compareTo(recordDate2) == 0)){
					result = result & true;
				}else{
					result = result & false;
					}
			}
		}
		return result;
	}
	
	public static boolean verifyDateInAscendingOrder(List<String>dateList) throws ParseException{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		boolean result = true;
		for(int i=0;i< dateList.size();i++){
			if(i<dateList.size()-1){
				String date1 = dateList.get(i);
				System.out.println("date1 "+date1);
				Date recordDate1 = df.parse(date1);
				String date2 = dateList.get(i+1);
				System.out.println("date2 "+date2);
				Date recordDate2 = df.parse(date2);
				System.out.println("result is "+ (recordDate1.before(recordDate2) ||recordDate1.equals(recordDate2)));
				if((recordDate1.compareTo(recordDate2)<0 ||recordDate1.compareTo(recordDate2) == 0)){
					result = result & true;
				}else{
					result = result & false;
					}
			}
		}
		return result;
	}
	
	public static boolean verifyAmountIsInAscendingOrder(List<String> amountList){
		boolean result = true;
		for(int i = 0;i<amountList.size();i++){
			if(i<amountList.size()-1){
				String amountValue1 = amountList.get(i).replace("-", "").replace("$", "").replace(",", "");
				System.out.println("amountValue1 "+amountValue1);
				BigDecimal value1 = new BigDecimal(amountValue1);
				String amountValue2 = amountList.get(i + 1).replace("-", "").replace("$", "").replace(",", "");
				System.out.println("amountValue2 "+amountValue2);
				BigDecimal value2 = new BigDecimal(amountValue2);
				System.out.println("Comp "+ ((value1.compareTo(value2) < 0) ||(value1.compareTo(value2) == 0)));
				result = result & ((value1.compareTo(value2) < 0) ||(value1.compareTo(value2) == 0)) ;
			}
		}
		return result;
	}
	
	public static boolean verifyAmountIsInDescendingOrder(List<String> amountList){
		boolean result = true;
		for(int i = 0;i<amountList.size();i++){
			if(i<amountList.size()-1){
				String amountValue1 = amountList.get(i).replace("-", "").replace("$", "").replace(",", "");
				System.out.println("amountValue1 "+amountValue1);
				BigDecimal value1 = new BigDecimal(amountValue1);
				String amountValue2 = amountList.get(i + 1).replace("-", "").replace("$", "").replace(",", "");
				System.out.println("amountValue2 "+amountValue2);
				BigDecimal value2 = new BigDecimal(amountValue2);
				System.out.println("Comp "+ ((value1.compareTo(value2) > 0) ||(value1.compareTo(value2) == 0)));
				result = result & ((value1.compareTo(value2) > 0) ||(value1.compareTo(value2) == 0)) ;
			}
		}
		return result;
	}
	
	public static void toReachbottomOfthePage()
	{
		AbstractPage.executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}
	
	public static void toReachTopOfthePage()
	{
		AbstractPage.executor.executeScript("window.scrollTo(0, 0)");

	}
	
}
