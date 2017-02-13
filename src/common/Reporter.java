package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import eCashPageClasses.AbstractTest;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Reporter 
{
	
	 public static File file=null;
	 public static void createExcelReport() throws WriteException, IOException

		{
			File file= new File("Report_Excel/Report.xls");
			 if(file.exists())
			 {
				 file.delete();
			 }
			 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			 Date date = new Date();
			 FileOutputStream fs = new FileOutputStream(file);
			 WritableWorkbook wbook = Workbook.createWorkbook(fs);
			 WritableSheet wsheet = wbook.createSheet("IE_TestReport", 0);
			 WritableSheet wsheet1 = wbook.createSheet("IE_TestStepReport", 1);
			 WritableSheet wsheet2 = wbook.createSheet("FF_TestReport", 2);
			 WritableSheet wsheet3 = wbook.createSheet("FF_TestStepReport", 3);
			 WritableSheet wsheet4 = wbook.createSheet("Chrome_TestReport", 4);
			 WritableSheet wsheet5 = wbook.createSheet("Chrome_TestStepReport", 5);
			 WritableFont font = new WritableFont(WritableFont.ARIAL,9,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			 WritableCellFormat fcell = new WritableCellFormat(font);
			 WritableCellFormat fcell1 = new WritableCellFormat(font);
			 fcell1.setWrap(true);
			 fcell.setWrap(true);
			 wsheet.setColumnView(0, 25);
			 wsheet.setColumnView(1, 25);
			 wsheet.setColumnView(2, 25);
			 fcell.setBackground(Colour.LIGHT_GREEN);
			 fcell1.setBackground(Colour.YELLOW);
			 fcell.setVerticalAlignment(VerticalAlignment.CENTRE);
			 fcell.setAlignment(Alignment.CENTRE);
			 fcell.setBorder(Border.ALL, BorderLineStyle.THIN);
			 Label l1 = new Label(0,1,"Execution Date & Time",fcell);
			 Label l2 = new Label(0,2,"Total Passed",fcell);
			 Label l3 = new Label(0,3,"Total Failed",fcell);
			 Label l4 = new Label(0,4,"Total Skipped",fcell);
			 Label l5 = new Label(0,8,"Tect Case ID",fcell);
			 Label l6 = new Label(1,8,"Result",fcell);
			 Label l7 = new Label(1,1,dateFormat.format(date),fcell1);
			//For IETestReport
			 wsheet.addCell(l1);
			 wsheet.addCell(l2);
			 wsheet.addCell(l3);
			 wsheet.addCell(l4);
			 wsheet.addCell(l5);
			 wsheet.addCell(l6);
			 wsheet.addCell(l7);
			//For FF TestReport
			 Label l8 = new Label(0,1,"Execution Date & Time",fcell);
			 Label l9 = new Label(0,2,"Total Passed",fcell);
			 Label l10 = new Label(0,3,"Total Failed",fcell);
			 Label l11 = new Label(0,4,"Total Skipped",fcell);
			 Label l12 = new Label(0,8,"Tect Case ID",fcell);
			 Label l13 = new Label(1,8,"Result",fcell);	
			 Label l14 = new Label(1,1,dateFormat.format(date),fcell1);
			 wsheet2.setColumnView(0, 25);
			 wsheet2.setColumnView(1, 25);
			 wsheet2.setColumnView(2, 25);
			 wsheet2.addCell(l8);
			 wsheet2.addCell(l9);
			 wsheet2.addCell(l10);
			 wsheet2.addCell(l11);
			 wsheet2.addCell(l12);
			 wsheet2.addCell(l13);	
			 wsheet2.addCell(l14);

			//For Chrome TestReport
			 Label l15 = new Label(0,1,"Execution Date & Time",fcell);
			 Label l16 = new Label(0,2,"Total Passed",fcell);
			 Label l17 = new Label(0,3,"Total Failed",fcell);
			 Label l18 = new Label(0,4,"Total Skipped",fcell);
			 Label l19 = new Label(0,8,"Tect Case ID",fcell);
			 Label l20 = new Label(1,8,"Result",fcell);
			 Label l21 = new Label(1,1,dateFormat.format(date),fcell1);

			 wsheet4.setColumnView(0, 25);
			 wsheet4.setColumnView(1, 25);
			 wsheet4.setColumnView(2, 25);
			 wsheet4.addCell(l15);
			 wsheet4.addCell(l16);
			 wsheet4.addCell(l17);
			 wsheet4.addCell(l18);
			 wsheet4.addCell(l19);
			 wsheet4.addCell(l20);
			 wsheet4.addCell(l21);
			 //For IE Test step report
			 Label l22 = new Label(2,1,"Step Defination",fcell);
			 Label l23 = new Label(3,1,"Result",fcell);
			 Label l24 = new Label(4,1,"ScreenShot",fcell);
			 Label l25 = new Label(0,1,"Test Case ID",fcell);
			 Label l100 = new Label(1,1,"Step ID",fcell);
			 wsheet1.setColumnView(0, 25);
			 wsheet1.setColumnView(1, 25);
			 wsheet1.setColumnView(2, 50);
			 wsheet1.setColumnView(3, 25);
			 wsheet1.setColumnView(4, 25);
			 wsheet1.addCell(l22);
			 wsheet1.addCell(l23);
			 wsheet1.addCell(l24);
			 wsheet1.addCell(l25);
			 wsheet1.addCell(l100);
			//For FF Test step report
			 Label l26 = new Label(2,1,"Step Defination",fcell);
			 Label l27 = new Label(3,1,"Result",fcell);
			 Label l28 = new Label(4,1,"ScreenShot",fcell);
			 Label l29 = new Label(0,1,"Test Case ID",fcell);
			 Label l101 = new Label(1,1,"Step ID",fcell);
			 wsheet3.setColumnView(0, 25);
			 wsheet3.setColumnView(1, 25);
			 wsheet3.setColumnView(2, 50);
			 wsheet3.setColumnView(3, 25);
			 wsheet3.setColumnView(4, 25);
			 wsheet3.addCell(l26);
			 wsheet3.addCell(l27);
			 wsheet3.addCell(l28);
			 wsheet3.addCell(l29);
			 wsheet3.addCell(l101);
			//For Chrome Test step report
			 Label l30 = new Label(2,1,"Step Defination",fcell);
			 Label l31 = new Label(3,1,"Result",fcell);
			 Label l32 = new Label(4,1,"ScreenShot",fcell);
			 Label l33 = new Label(0,1,"Test Case ID",fcell);
			 Label l102 = new Label(1,1,"Step ID",fcell);

			 wsheet5.setColumnView(0, 25);
			 wsheet5.setColumnView(1, 25);
			 wsheet5.setColumnView(2, 50);
			 wsheet5.setColumnView(3, 25);
			 wsheet5.setColumnView(4, 25);
			 wsheet5.addCell(l30);
			 wsheet5.addCell(l31);
			 wsheet5.addCell(l32);
			 wsheet5.addCell(l33);
			 wsheet5.addCell(l102);
			 wbook.write();
			 wbook.close();

		}
		
	public static void updateTotalCount(String browser) throws BiffException, IOException, RowsExceededException, WriteException
	{
		String passcountff=null;
		String failcountff=null;
		String skipcountff=null;
		int passff=0;
		int failff=0;
		int skipff=0;
		String passcountie=null;
		String failcountie=null;
		String skipcountie=null;
		int passie=0;
		int failie=0;
		int skipie=0;
		String passcountchrome=null;
		String failcountchrome=null;
		String skipcountchrome=null;
		int passchrome=0;
		int failchrome=0;
		int skipchrome=0;
		file= new File("Report_Excel/Report.xls");
		FileInputStream is = new FileInputStream(file);
		Workbook wbook =Workbook.getWorkbook(is);
		WritableFont font = new WritableFont(WritableFont.ARIAL,9,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.GREEN);
		WritableFont font1 = new WritableFont(WritableFont.ARIAL,9,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);
		WritableFont font2 = new WritableFont(WritableFont.ARIAL,9,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.ORANGE);
		WritableCellFormat fcell2 = new WritableCellFormat(font);
		WritableCellFormat fcell3 = new WritableCellFormat(font1);
		WritableCellFormat fcell4 = new WritableCellFormat(font2);
		/*fcell2.setBackground(Colour.GREEN);
		fcell3.setBackground(Colour.RED);
		fcell4.setBackground(Colour.ORANGE);*/
		WritableWorkbook copy = Workbook.createWorkbook(file,wbook);
		WritableSheet fftestReport=copy.getSheet("FF_TestReport");
		WritableSheet ietestReport=copy.getSheet("IE_TestReport");
		WritableSheet chrometestReport=copy.getSheet("Chrome_TestReport");
		if(browser.equalsIgnoreCase("FF"))
		{
		//Update the ff test report
		int usedRowsff=fftestReport.getRows();
	
		for(int i=9;i<usedRowsff;i++)
		{
			
			String status=(fftestReport.getCell(1, i)).getContents();
			if(status.equalsIgnoreCase("Pass"))
			{
				passff=passff+1;
			}
			else if(status.equalsIgnoreCase("Fail"))
			{
				failff=failff+1;
			}
			else if(status.equalsIgnoreCase("Skipped"))
			{
				skipff=skipff+1;
			}
		}
		
		passcountff=Integer.toString(passff);
		failcountff=Integer.toString(failff);
		skipcountff=Integer.toString(skipff);
		Label level = new Label(1,2,passcountff,fcell2);
		Label level1 = new Label(1,3,failcountff,fcell3);
		Label level2 = new Label(1,4,skipcountff,fcell4);
		fftestReport.addCell(level);
		fftestReport.addCell(level1);
		fftestReport.addCell(level2);
		}
		else if(browser.equalsIgnoreCase("IE"))
		{
		//Update the ie test report
				int usedRowsie=ietestReport.getRows();
	
				for(int i=9;i<usedRowsie;i++)
				{
					
					String status=(ietestReport.getCell(1, i)).getContents();
					if(status.equalsIgnoreCase("Pass"))
					{
						passie=passie+1;
					}
					else if(status.equalsIgnoreCase("Fail"))
					{
						failie=failie+1;
					}
					else if (status.equalsIgnoreCase("Skipped"))
					{
						skipie=skipie+1;
					}
				}
				
				passcountie=Integer.toString(passie);
				failcountie=Integer.toString(failie);
				skipcountie=Integer.toString(skipie);
				Label level3 = new Label(1,2,passcountie,fcell2);
				Label level4 = new Label(1,3,failcountie,fcell3);
				Label level5 = new Label(1,4,skipcountie,fcell4);
				ietestReport.addCell(level3);
				ietestReport.addCell(level4);
				ietestReport.addCell(level5);
		}
		else if(browser.equalsIgnoreCase("Chrome"))
		{
				//Update the chrome test report
				int usedRowschrome=chrometestReport.getRows();
			
				for(int i=9;i<usedRowschrome;i++)
				{
					
					String status=(chrometestReport.getCell(1, i)).getContents();
					if(status.equalsIgnoreCase("Pass"))
					{
						passchrome=passchrome+1;
					}
					else if(status.equalsIgnoreCase("Fail"))
					{
						failchrome=failchrome+1;
					}
					else if (status.equalsIgnoreCase("Skipped"))
					{
						skipchrome=skipchrome+1;
					}
				}
				
				passcountchrome=Integer.toString(passchrome);
				failcountchrome=Integer.toString(failchrome);
				skipcountchrome=Integer.toString(skipchrome);
				Label level6 = new Label(1,2,passcountchrome,fcell2);
				Label level7 = new Label(1,3,failcountchrome,fcell3);
				Label level8 = new Label(1,4,skipcountchrome,fcell4);
				chrometestReport.addCell(level6);
				chrometestReport.addCell(level7);
				chrometestReport.addCell(level8);
		}
				copy.write();
				copy.close();
		
	}
	public static void reportPassResult(String browser,String stepDefination,String result) throws IOException, BiffException, RowsExceededException, WriteException
	{
		if(browser.equalsIgnoreCase("IE"))
		{
			file= new File("Report_Excel/Report.xls");
			FileInputStream is = new FileInputStream(file);
			Workbook wbook =Workbook.getWorkbook(is);
			WritableWorkbook copy = Workbook.createWorkbook(file,wbook);
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			WritableCellFormat fcell1 = new WritableCellFormat(font1);
			fcell1.setWrap(true);
			fcell1.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcell1.setAlignment(Alignment.GENERAL);
			WritableFont fontGreen = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.GREEN);
			WritableCellFormat fcellGreen = new WritableCellFormat(fontGreen);
			fcellGreen.setWrap(true);
			fcellGreen.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellGreen.setAlignment(Alignment.GENERAL);
			WritableFont fontRed = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);
			WritableCellFormat fcellRed = new WritableCellFormat(fontRed);
			fcellRed.setWrap(true);
			fcellRed.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellRed.setAlignment(Alignment.GENERAL);
			WritableFont fontYellow = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.ORANGE);
			WritableCellFormat fcellYellow = new WritableCellFormat(fontYellow);
			fcellYellow.setWrap(true);
			fcellYellow.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellYellow.setAlignment(Alignment.GENERAL);
			Sheet wsheet=wbook.getSheet("IE_TestStepReport");
			int usedRows=wsheet.getRows();
			Label level = new Label(0,usedRows,AbstractTest.tcName,fcell1);
			Label levelForStep = new Label(1,usedRows,"Step "+AbstractTest.stepValue,fcell1);
			Label level1 = new Label(2,usedRows,stepDefination,fcell1);
			Label levelGreen = new Label(3,usedRows,result,fcellGreen);
			Label levelRed = new Label(3,usedRows,result,fcellRed);
			Label levelYello = new Label(3,usedRows,result,fcellYellow);
			WritableSheet wsheetNew=copy.getSheet("IE_TestStepReport");
			if(AbstractTest.stepValue==1)
			{
				wsheetNew.addCell(level);

			}
			wsheetNew.addCell(levelForStep);
			wsheetNew.addCell(level1);
			if(result.equalsIgnoreCase("Pass"))
			{
				wsheetNew.addCell(levelGreen);

			}
			else if(result.equalsIgnoreCase("Fail"))
			{
				wsheetNew.addCell(levelRed);

			}
			else
			{
				wsheetNew.addCell(levelYello);

			}
			copy.write();
			AbstractTest.stepValue=AbstractTest.stepValue+1;
			copy.close();
		
		}
		else if(browser.equalsIgnoreCase("FF"))
		{
			file= new File("Report_Excel/Report.xls");
			FileInputStream is = new FileInputStream(file);
			Workbook wbook =Workbook.getWorkbook(is);
			WritableWorkbook copy = Workbook.createWorkbook(file,wbook);
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			WritableCellFormat fcell1 = new WritableCellFormat(font1);
			fcell1.setWrap(true);
			fcell1.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcell1.setAlignment(Alignment.GENERAL);
			WritableFont fontGreen = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.GREEN);
			WritableCellFormat fcellGreen = new WritableCellFormat(fontGreen);
			fcellGreen.setWrap(true);
			fcellGreen.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellGreen.setAlignment(Alignment.GENERAL);
			WritableFont fontRed = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);
			WritableCellFormat fcellRed = new WritableCellFormat(fontRed);
			fcellRed.setWrap(true);
			fcellRed.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellRed.setAlignment(Alignment.GENERAL);
			WritableFont fontYellow = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.ORANGE);
			WritableCellFormat fcellYellow = new WritableCellFormat(fontYellow);
			fcellYellow.setWrap(true);
			fcellYellow.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellYellow.setAlignment(Alignment.GENERAL);
			Sheet wsheet=wbook.getSheet("FF_TestStepReport");
			int usedRows=wsheet.getRows();
			Label level = new Label(0,usedRows,AbstractTest.tcName,fcell1);
			Label levelForStep = new Label(1,usedRows,"Step "+AbstractTest.stepValue,fcell1);
			Label level1 = new Label(2,usedRows,stepDefination,fcell1);
			Label levelGreen = new Label(3,usedRows,result,fcellGreen);
			Label levelRed = new Label(3,usedRows,result,fcellRed);
			Label levelYello = new Label(3,usedRows,result,fcellYellow);
			WritableSheet wsheetNew=copy.getSheet("FF_TestStepReport");
			if(AbstractTest.stepValue==1)
			{
				wsheetNew.addCell(level);

			}
			wsheetNew.addCell(levelForStep);
			wsheetNew.addCell(level1);
			if(result.equalsIgnoreCase("Pass"))
			{
				wsheetNew.addCell(levelGreen);

			}
			else if(result.equalsIgnoreCase("Fail"))
			{
				wsheetNew.addCell(levelRed);


			}
			else
			{
				wsheetNew.addCell(levelYello);

			}
			copy.write();
			AbstractTest.stepValue=AbstractTest.stepValue+1;
			copy.close();
		}
		else if(browser.equalsIgnoreCase("Chrome"))
		{
			file= new File("Report_Excel/Report.xls");
			FileInputStream is = new FileInputStream(file);
			Workbook wbook =Workbook.getWorkbook(is);
			WritableWorkbook copy = Workbook.createWorkbook(file,wbook);
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			WritableCellFormat fcell1 = new WritableCellFormat(font1);
			fcell1.setWrap(true);
			fcell1.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcell1.setAlignment(Alignment.GENERAL);
			WritableFont fontGreen = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.GREEN);
			WritableCellFormat fcellGreen = new WritableCellFormat(fontGreen);
			fcellGreen.setWrap(true);
			fcellGreen.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellGreen.setAlignment(Alignment.GENERAL);
			WritableFont fontRed = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);
			WritableCellFormat fcellRed = new WritableCellFormat(fontRed);
			fcellRed.setWrap(true);
			fcellRed.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellRed.setAlignment(Alignment.GENERAL);
			WritableFont fontYellow = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.ORANGE);
			WritableCellFormat fcellYellow = new WritableCellFormat(fontYellow);
			fcellYellow.setWrap(true);
			fcellYellow.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellYellow.setAlignment(Alignment.GENERAL);
			Sheet wsheet=wbook.getSheet("Chrome_TestStepReport");
			int usedRows=wsheet.getRows();
			Label level = new Label(0,usedRows,AbstractTest.tcName,fcell1);
			Label levelForStep = new Label(1,usedRows,"Step "+AbstractTest.stepValue,fcell1);
			Label level1 = new Label(2,usedRows,stepDefination,fcell1);
			Label levelGreen = new Label(3,usedRows,result,fcellGreen);
			Label levelRed = new Label(3,usedRows,result,fcellRed);
			Label levelYello = new Label(3,usedRows,result,fcellYellow);
			WritableSheet wsheetNew=copy.getSheet("Chrome_TestStepReport");
			if(AbstractTest.stepValue==1)
			{
				wsheetNew.addCell(level);

			}
			wsheetNew.addCell(levelForStep);
			wsheetNew.addCell(level1);
			if(result.equalsIgnoreCase("Pass"))
			{
				wsheetNew.addCell(levelGreen);

			}
			else if(result.equalsIgnoreCase("Fail"))
			{
				wsheetNew.addCell(levelRed);


			}
			else
			{
				wsheetNew.addCell(levelYello);

			}
			copy.write();
			AbstractTest.stepValue=AbstractTest.stepValue+1;
			copy.close();
		}
	}

	
		
	public static void reportTestFailure(String browser,String stepDefination,String result) throws IOException, BiffException, RowsExceededException, WriteException
		{
			if(browser.equalsIgnoreCase("IE"))
			{
				File f=new File("FailureScreenshot/"+browser+"_"+AbstractTest.tcName+AbstractTest.stepValue+".png");
				file= new File("Report_Excel/Report.xls");
				FileInputStream is = new FileInputStream(file);
				Workbook wbook =Workbook.getWorkbook(is);
				WritableWorkbook copy = Workbook.createWorkbook(file,wbook);
				WritableFont font1 = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
				WritableCellFormat fcell1 = new WritableCellFormat(font1);
				fcell1.setWrap(true);
				fcell1.setVerticalAlignment(VerticalAlignment.JUSTIFY);
				fcell1.setAlignment(Alignment.GENERAL);
				WritableFont fontGreen = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.GREEN);
				WritableCellFormat fcellGreen = new WritableCellFormat(fontGreen);
				fcellGreen.setWrap(true);
				fcellGreen.setVerticalAlignment(VerticalAlignment.JUSTIFY);
				fcellGreen.setAlignment(Alignment.GENERAL);
				WritableFont fontRed = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);
				WritableCellFormat fcellRed = new WritableCellFormat(fontRed);
				fcellRed.setWrap(true);
				fcellRed.setVerticalAlignment(VerticalAlignment.JUSTIFY);
				fcellRed.setAlignment(Alignment.GENERAL);
				WritableFont fontYellow = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.ORANGE);
				WritableCellFormat fcellYellow = new WritableCellFormat(fontYellow);
				fcellYellow.setWrap(true);
				fcellYellow.setVerticalAlignment(VerticalAlignment.JUSTIFY);
				fcellYellow.setAlignment(Alignment.GENERAL);
				Sheet wsheet=wbook.getSheet("IE_TestStepReport");
				int usedRows=wsheet.getRows();
				Label level = new Label(0,usedRows,AbstractTest.tcName,fcell1);
				Label levelForStep = new Label(1,usedRows,"Step "+AbstractTest.stepValue,fcell1);
				Label level1 = new Label(2,usedRows,stepDefination,fcell1);
				Label levelGreen = new Label(3,usedRows,result,fcellGreen);
				Label levelRed = new Label(3,usedRows,result,fcellRed);
				Label levelYello = new Label(3,usedRows,result,fcellYellow);
				WritableSheet wsheetNew=copy.getSheet("IE_TestStepReport");
			    WritableHyperlink wh2 = new WritableHyperlink(4, usedRows, 5, usedRows, f.getAbsoluteFile(), "Failure Image");
			    if(AbstractTest.stepValue==1)
			    {
					wsheetNew.addCell(level);

			    }
				wsheetNew.addCell(level1);
				wsheetNew.addCell(levelForStep);

				if(result.equalsIgnoreCase("Pass"))
				{
					wsheetNew.addCell(levelGreen);

				}
				else if(result.equalsIgnoreCase("Fail"))
				{
					wsheetNew.addCell(levelRed);
					wsheetNew.addHyperlink(wh2);
					

				}
				else
				{
					wsheetNew.addCell(levelYello);
					wsheetNew.addHyperlink(wh2);

				}
				copy.write();
				AbstractTest.stepValue=AbstractTest.stepValue+1;
				copy.close();
			
			}
			else if(browser.equalsIgnoreCase("FF"))
			{
				File f=new File("FailureScreenshot/"+browser+"_"+AbstractTest.tcName+AbstractTest.stepValue+".png");
				file= new File("Report_Excel/Report.xls");
				FileInputStream is = new FileInputStream(file);
				Workbook wbook =Workbook.getWorkbook(is);
				WritableWorkbook copy = Workbook.createWorkbook(file,wbook);
				WritableFont font1 = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
				WritableCellFormat fcell1 = new WritableCellFormat(font1);
				fcell1.setWrap(true);
				fcell1.setVerticalAlignment(VerticalAlignment.JUSTIFY);
				fcell1.setAlignment(Alignment.GENERAL);
				WritableFont fontGreen = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.GREEN);
				WritableCellFormat fcellGreen = new WritableCellFormat(fontGreen);
				fcellGreen.setWrap(true);
				fcellGreen.setVerticalAlignment(VerticalAlignment.JUSTIFY);
				fcellGreen.setAlignment(Alignment.GENERAL);
				WritableFont fontRed = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);
				WritableCellFormat fcellRed = new WritableCellFormat(fontRed);
				fcellRed.setWrap(true);
				fcellRed.setVerticalAlignment(VerticalAlignment.JUSTIFY);
				fcellRed.setAlignment(Alignment.GENERAL);
				WritableFont fontYellow = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.ORANGE);
				WritableCellFormat fcellYellow = new WritableCellFormat(fontYellow);
				fcellYellow.setWrap(true);
				fcellYellow.setVerticalAlignment(VerticalAlignment.JUSTIFY);
				fcellYellow.setAlignment(Alignment.GENERAL);
				Sheet wsheet=wbook.getSheet("FF_TestStepReport");
				int usedRows=wsheet.getRows();
				Label level = new Label(0,usedRows,AbstractTest.tcName,fcell1);
				Label levelForStep = new Label(1,usedRows,"Step "+AbstractTest.stepValue,fcell1);
				Label level1 = new Label(2,usedRows,stepDefination,fcell1);
				Label levelGreen = new Label(3,usedRows,result,fcellGreen);
				Label levelRed = new Label(3,usedRows,result,fcellRed);
				Label levelYello = new Label(3,usedRows,result,fcellYellow);
				WritableSheet wsheetNew=copy.getSheet("FF_TestStepReport");
			    WritableHyperlink wh2 = new WritableHyperlink(4, usedRows, 5, usedRows,f.getAbsoluteFile(), "Failure Image");
			    if(AbstractTest.stepValue==1)
			    {
					wsheetNew.addCell(level);

			    }
			    wsheetNew.addCell(levelForStep);
				wsheetNew.addCell(level1);
				if(result.equalsIgnoreCase("Pass"))
				{
					wsheetNew.addCell(levelGreen);

				}
				else if(result.equalsIgnoreCase("Fail"))
				{
					wsheetNew.addCell(levelRed);
					wsheetNew.addHyperlink(wh2);


				}
				else
				{
					wsheetNew.addCell(levelYello);
					wsheetNew.addHyperlink(wh2);

				}
				copy.write();
				AbstractTest.stepValue=AbstractTest.stepValue+1;
				copy.close();
			}
			else if(browser.equalsIgnoreCase("Chrome"))
			{
				File f=new File("FailureScreenshot/"+browser+"_"+AbstractTest.tcName+AbstractTest.stepValue+".png");
				file= new File("Report_Excel/Report.xls");
				FileInputStream is = new FileInputStream(file);
				Workbook wbook =Workbook.getWorkbook(is);
				WritableWorkbook copy = Workbook.createWorkbook(file,wbook);
				WritableFont font1 = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
				WritableCellFormat fcell1 = new WritableCellFormat(font1);
				fcell1.setWrap(true);
				fcell1.setVerticalAlignment(VerticalAlignment.JUSTIFY);
				fcell1.setAlignment(Alignment.GENERAL);
				WritableFont fontGreen = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.GREEN);
				WritableCellFormat fcellGreen = new WritableCellFormat(fontGreen);
				fcellGreen.setWrap(true);
				fcellGreen.setVerticalAlignment(VerticalAlignment.JUSTIFY);
				fcellGreen.setAlignment(Alignment.GENERAL);
				WritableFont fontRed = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);
				WritableCellFormat fcellRed = new WritableCellFormat(fontRed);
				fcellRed.setWrap(true);
				fcellRed.setVerticalAlignment(VerticalAlignment.JUSTIFY);
				fcellRed.setAlignment(Alignment.GENERAL);
				WritableFont fontYellow = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.ORANGE);
				WritableCellFormat fcellYellow = new WritableCellFormat(fontYellow);
				fcellYellow.setWrap(true);
				fcellYellow.setVerticalAlignment(VerticalAlignment.JUSTIFY);
				fcellYellow.setAlignment(Alignment.GENERAL);
				Sheet wsheet=wbook.getSheet("Chrome_TestStepReport");
				int usedRows=wsheet.getRows();
				Label level = new Label(0,usedRows,AbstractTest.tcName,fcell1);
				Label levelForStep = new Label(1,usedRows,"Step "+AbstractTest.stepValue,fcell1);
				Label level1 = new Label(2,usedRows,stepDefination,fcell1);
				Label levelGreen = new Label(3,usedRows,result,fcellGreen);
				Label levelRed = new Label(3,usedRows,result,fcellRed);
				Label levelYello = new Label(3,usedRows,result,fcellYellow);
				WritableSheet wsheetNew=copy.getSheet("Chrome_TestStepReport");
			    WritableHyperlink wh2 = new WritableHyperlink(4, usedRows, 5, usedRows,f.getAbsoluteFile(), "Failure Image");
			    if(AbstractTest.stepValue==1)
			    {
					wsheetNew.addCell(level);

			    }
			    wsheetNew.addCell(levelForStep);
				wsheetNew.addCell(level1);
				if(result.equalsIgnoreCase("Pass"))
				{
					wsheetNew.addCell(levelGreen);

				}
				else if(result.equalsIgnoreCase("Fail"))
				{
					wsheetNew.addCell(levelRed);
					wsheetNew.addHyperlink(wh2);


				}
				else
				{
					wsheetNew.addCell(levelYello);
					wsheetNew.addHyperlink(wh2);


				}
				copy.write();
				AbstractTest.stepValue=AbstractTest.stepValue+1;
				copy.close();
			}
		}
	
	public static void updateTestReport(String browser) throws BiffException, IOException, WriteException
	{
		
		if(browser.equalsIgnoreCase("FF"))
		{
			String tcName=null;
			String tcNamePrevious=null;
			String resultCurrent=null;
			String tcNameForVerify=null;


	
			file= new File("Report_Excel/Report.xls");
			FileInputStream is = new FileInputStream(file);
			Workbook wbook =Workbook.getWorkbook(is);
			WritableWorkbook copy = Workbook.createWorkbook(file,wbook);
			Sheet wsheet=wbook.getSheet("FF_TestStepReport");
			WritableSheet fftestReport=copy.getSheet("FF_TestReport");
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			WritableCellFormat fcell1 = new WritableCellFormat(font1);
			fcell1.setWrap(true);
			fcell1.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcell1.setAlignment(Alignment.GENERAL);
			WritableFont fontGreen = new WritableFont(WritableFont.ARIAL,9,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.GREEN);
			WritableCellFormat fcellGreen = new WritableCellFormat(fontGreen);
			fcellGreen.setWrap(true);
			fcellGreen.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellGreen.setAlignment(Alignment.GENERAL);
			WritableFont fontYellow = new WritableFont(WritableFont.ARIAL,9,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.ORANGE);
			WritableCellFormat fcellYellow = new WritableCellFormat(fontYellow);
			fcellYellow.setWrap(true);
			fcellYellow.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellYellow.setAlignment(Alignment.GENERAL);
			WritableFont fontRed = new WritableFont(WritableFont.ARIAL,9,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);
			WritableCellFormat fcellRed = new WritableCellFormat(fontRed);
			fcellRed.setWrap(true);
			fcellRed.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellRed.setAlignment(Alignment.GENERAL);
			int size=wsheet.getRows();
//			tcName=wsheet.getCell(0, 2).getContents();
			for(int i=size-1;i>=2;i--)
			{
				 tcName=wsheet.getCell(0, i).getContents();
				 if(tcName.equalsIgnoreCase(""))
				 {
					 continue;
				 }
				 else
				 {
					 break;
				 }

			}
			for(int i=size-1;i>=2;i--)
			{
				 resultCurrent=wsheet.getCell(3, i).getContents();
				 tcNameForVerify=wsheet.getCell(0, i).getContents();
				 tcNamePrevious=wsheet.getCell(0, i-1).getContents();
				 if(resultCurrent.equalsIgnoreCase("Fail"))
				 {
					 	int size1=fftestReport.getRows();
						Label levell = new Label(0,size1,tcName,fcell1);
						Label level2 = new Label(1,size1,resultCurrent,fcellRed);
						fftestReport.addCell(levell);
						fftestReport.addCell(level2);
						break;
				 }
				 else if(resultCurrent.equalsIgnoreCase("Skipped"))
				 {
					 int size1=fftestReport.getRows();
						Label levell = new Label(0,size1,tcName,fcell1);
						Label level2 = new Label(1,size1,resultCurrent,fcellYellow);
						fftestReport.addCell(levell);
						fftestReport.addCell(level2);
						break;
				 }
				 else if(resultCurrent.equalsIgnoreCase("Pass") & i==2)
				 {
					 int size1=fftestReport.getRows();
						Label levell = new Label(0,size1,tcName,fcell1);
						Label level2 = new Label(1,size1,resultCurrent,fcellGreen);
						fftestReport.addCell(levell);
						fftestReport.addCell(level2);
						break;
				 }
				 else if(resultCurrent.equalsIgnoreCase("Pass") && !tcNameForVerify.equalsIgnoreCase("") && !tcNameForVerify.equalsIgnoreCase(tcNamePrevious))
				 {
					   int size1=fftestReport.getRows();
						Label levell = new Label(0,size1,tcName,fcell1);
						Label level2 = new Label(1,size1,resultCurrent,fcellGreen);
						fftestReport.addCell(levell);
						fftestReport.addCell(level2);
						break;
						

				 }
				 else
				 {
					 continue;
				 }
			}
			
			
			copy.write();
			copy.close();
		}
		else if(browser.equalsIgnoreCase("IE"))
		{
			String tcName=null;
			String tcNamePrevious=null;
			String resultCurrent=null;
			String tcNameForVerify=null;
	
			file= new File("Report_Excel/Report.xls");
			FileInputStream is = new FileInputStream(file);
			Workbook wbook =Workbook.getWorkbook(is);
			WritableWorkbook copy = Workbook.createWorkbook(file,wbook);
			Sheet wsheet=wbook.getSheet("IE_TestStepReport");
			WritableSheet fftestReport=copy.getSheet("IE_TestReport");
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			WritableCellFormat fcell1 = new WritableCellFormat(font1);
			fcell1.setWrap(true);
			fcell1.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcell1.setAlignment(Alignment.GENERAL);
			WritableFont fontGreen = new WritableFont(WritableFont.ARIAL,9,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.GREEN);
			WritableCellFormat fcellGreen = new WritableCellFormat(fontGreen);
			fcellGreen.setWrap(true);
			fcellGreen.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellGreen.setAlignment(Alignment.GENERAL);
			WritableFont fontRed = new WritableFont(WritableFont.ARIAL,9,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);
			WritableCellFormat fcellRed = new WritableCellFormat(fontRed);
			fcellRed.setWrap(true);
			fcellRed.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellRed.setAlignment(Alignment.GENERAL);
			WritableFont fontYellow = new WritableFont(WritableFont.ARIAL,9,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.ORANGE);
			WritableCellFormat fcellYellow = new WritableCellFormat(fontYellow);
			fcellYellow.setWrap(true);
			fcellYellow.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellYellow.setAlignment(Alignment.GENERAL);
			int size=wsheet.getRows();
//			tcName=wsheet.getCell(0, 2).getContents();
			for(int i=size-1;i>=2;i--)
			{
				 tcName=wsheet.getCell(0, i).getContents();
				 if(tcName.equalsIgnoreCase(""))
				 {
					 continue;
				 }
				 else
				 {
					 break;
				 }

			}
			for(int i=size-1;i>=2;i--)
			{
				 resultCurrent=wsheet.getCell(3, i).getContents();
				 tcNameForVerify=wsheet.getCell(0, i).getContents();
				 tcNamePrevious=wsheet.getCell(0, i-1).getContents();
				 if(resultCurrent.equalsIgnoreCase("Fail"))
				 {
					 	int size1=fftestReport.getRows();
						Label levell = new Label(0,size1,tcName,fcell1);
						Label level2 = new Label(1,size1,resultCurrent,fcellRed);
						fftestReport.addCell(levell);
						fftestReport.addCell(level2);
						break;
				 }
				 else if(resultCurrent.equalsIgnoreCase("Skipped"))
				 {
					 int size1=fftestReport.getRows();
						Label levell = new Label(0,size1,tcName,fcell1);
						Label level2 = new Label(1,size1,resultCurrent,fcellYellow);
						fftestReport.addCell(levell);
						fftestReport.addCell(level2);
						break;
				 }
				 else if(resultCurrent.equalsIgnoreCase("Pass") && i==2)
				 {
					 int size1=fftestReport.getRows();
						Label levell = new Label(0,size1,tcName,fcell1);
						Label level2 = new Label(1,size1,resultCurrent,fcellGreen);
						fftestReport.addCell(levell);
						fftestReport.addCell(level2);
						break;
				 }
				 else if(resultCurrent.equalsIgnoreCase("Pass") && !tcNameForVerify.equalsIgnoreCase("") && !tcNameForVerify.equalsIgnoreCase(tcNamePrevious))
				 {
					   int size1=fftestReport.getRows();
						Label levell = new Label(0,size1,tcName,fcell1);
						Label level2 = new Label(1,size1,resultCurrent,fcellGreen);
						fftestReport.addCell(levell);
						fftestReport.addCell(level2);
						break;
						

				 }
				 else
				 {
					 continue;
				 }
			}
			
			
			copy.write();
			copy.close();
		}
		else if(browser.equalsIgnoreCase("Chrome"))
		{
			String tcName=null;
			String tcNamePrevious=null;
			String resultCurrent=null;
			String tcNameForVerify=null;

	
			file= new File("Report_Excel/Report.xls");
			FileInputStream is = new FileInputStream(file);
			Workbook wbook =Workbook.getWorkbook(is);
			WritableWorkbook copy = Workbook.createWorkbook(file,wbook);
			Sheet wsheet=wbook.getSheet("Chrome_TestStepReport");
			WritableSheet fftestReport=copy.getSheet("Chrome_TestReport");
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			WritableCellFormat fcell1 = new WritableCellFormat(font1);
			fcell1.setWrap(true);
			fcell1.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcell1.setAlignment(Alignment.GENERAL);
			WritableFont fontGreen = new WritableFont(WritableFont.ARIAL,9,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.GREEN);
			WritableCellFormat fcellGreen = new WritableCellFormat(fontGreen);
			fcellGreen.setWrap(true);
			fcellGreen.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellGreen.setAlignment(Alignment.GENERAL);
			WritableFont fontRed = new WritableFont(WritableFont.ARIAL,9,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);
			WritableCellFormat fcellRed = new WritableCellFormat(fontRed);
			fcellRed.setWrap(true);
			fcellRed.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellRed.setAlignment(Alignment.GENERAL);
			WritableFont fontYellow = new WritableFont(WritableFont.ARIAL,9,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.ORANGE);
			WritableCellFormat fcellYellow = new WritableCellFormat(fontYellow);
			fcellYellow.setWrap(true);
			fcellYellow.setVerticalAlignment(VerticalAlignment.JUSTIFY);
			fcellYellow.setAlignment(Alignment.GENERAL);
			int size=wsheet.getRows();
//			tcName=wsheet.getCell(0, 2).getContents();
			for(int i=size-1;i>=2;i--)
			{
				 tcName=wsheet.getCell(0, i).getContents();
				 if(tcName.equalsIgnoreCase(""))
				 {
					 continue;
				 }
				 else
				 {
					 break;
				 }

			}
			for(int i=size-1;i>=2;i--)
			{
				 resultCurrent=wsheet.getCell(3, i).getContents();
				 tcNameForVerify=wsheet.getCell(0, i).getContents();
				 tcNamePrevious=wsheet.getCell(0, i-1).getContents();
				 if(resultCurrent.equalsIgnoreCase("Fail"))
				 {
					 	int size1=fftestReport.getRows();
						Label levell = new Label(0,size1,tcName,fcell1);
						Label level2 = new Label(1,size1,resultCurrent,fcellRed);
						fftestReport.addCell(levell);
						fftestReport.addCell(level2);
						break;
				 }
				 else if(resultCurrent.equalsIgnoreCase("Skipped"))
				 {
					 int size1=fftestReport.getRows();
						Label levell = new Label(0,size1,tcName,fcell1);
						Label level2 = new Label(1,size1,resultCurrent,fcellYellow);
						fftestReport.addCell(levell);
						fftestReport.addCell(level2);
						break;
				 }
				 else if(resultCurrent.equalsIgnoreCase("Pass") && i==2)
				 {
					 int size1=fftestReport.getRows();
						Label levell = new Label(0,size1,tcName,fcell1);
						Label level2 = new Label(1,size1,resultCurrent,fcellGreen);
						fftestReport.addCell(levell);
						fftestReport.addCell(level2);
						break;
				 }
				 else if(resultCurrent.equalsIgnoreCase("Pass") && !tcNameForVerify.equalsIgnoreCase("") && !tcNameForVerify.equalsIgnoreCase(tcNamePrevious))
				 {
					   int size1=fftestReport.getRows();
						Label levell = new Label(0,size1,tcName,fcell1);
						Label level2 = new Label(1,size1,resultCurrent,fcellGreen);
						fftestReport.addCell(levell);
						fftestReport.addCell(level2);
						break;
						

				 }
				 else
				 {
					 continue;
				 }
			}
			
			
			copy.write();
			copy.close();
		}
			
	
	
	}
	
}	
	
	
	
	
	
	
	
	
	
	

