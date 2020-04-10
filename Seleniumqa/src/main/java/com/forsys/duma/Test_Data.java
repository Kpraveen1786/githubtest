package com.forsys.duma;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test_Data {

	public static WebDriver browser;
	public static String ORDER_NUMBER;
	public static String Line_Number;
	public static String Item;
	public static String New_Schedule_Ship_Date;
	public static String filepath = "";
	public static String username = "";
	public static String error = "";

	@BeforeTest()
	public void Excel_Data() throws Exception {
		try {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxoptions = new FirefoxOptions();
			firefoxoptions.setPageLoadStrategy(PageLoadStrategy.NONE);
			// firefoxoptions.addArguments("headless");
			browser = new FirefoxDriver(firefoxoptions);
			// WebDriverManager.chromedriver().setup();
			// ChromeOptions options = new ChromeOptions();
			// options.setPageLoadStrategy(PageLoadStrategy.NONE);
			// options.addArguments("headless");
			// browser = new ChromeDriver(options);
			browser.manage().window().maximize();
			browser.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			browser.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			browser.get("https://egmn-test.fa.us2.oraclecloud.com");
			System.out.println("Test_Data programm started Execution");
			browser.findElement(By.id("userid")).sendKeys("janakiram.nalla@forsysinc.com");

			System.out.println("--> " + By.xpath("userid"));
			browser.findElement(By.id("password")).sendKeys("qugtsicy186z");
			System.out.println("===>" + browser.findElement(By.id("userid")).getText());
			browser.findElement(By.id("btnActive")).click();
			username = By.xpath("userid").toString();
			System.out.println(By.id("//a[@id='pt1:_UIShome']").toString());
			// browser.findElement(By.id("//a[@id='pt1:_UIShome']")).click();
			Thread.sleep(5000);
			browser.findElement(By.id("pt1:commandLink1")).click();
			Thread.sleep(5000);
			browser.findElement(By.id("groupNode_order_management_6")).click();
			browser.findElement(By.id("itemNode_order_management_order_management_1")).click();
		} catch (Exception e) {
			System.out.println("Error occured");
			error = e.getMessage();
			e.printStackTrace();
		}
	}

	// @Test()
	public void Excel_data1() throws Exception {
		System.out.println("Inside excel_data1()");
		try {
			int n = 1;
			for (int i = 1; i <= 5; i++) {
				// File f = new File(System.getProperty("user.dir") + "\\ExcelData\\Test.xlsx");
				File f = new File(TestRunner.path);
				FileInputStream fis = new FileInputStream(f);
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				XSSFSheet sheet = wb.getSheetAt(0);
				sheet.getRow(0).createCell(6).setCellValue("Results");
				int rowcount = sheet.getPhysicalNumberOfRows();
				if (n <= rowcount) {
					System.out.println("count: " + n);
					n++;
					// ORDER_NUMBER = sheet.getRow(i).getCell(0).getStringCellValue();
					// Line_Number = sheet.getRow(i).getCell(1).getStringCellValue();
					// Item = sheet.getRow(i).getCell(2).getStringCellValue();
					New_Schedule_Ship_Date = sheet.getRow(i).getCell(5).getStringCellValue();

					Thread.sleep(5000);
					browser.findElement(By.xpath(
							"//*[@id=\"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:0:AP1:m1\"]"))
							.click();
					browser.findElement(By.xpath("//td[contains(text(),'Manage Fulfillment Lines')]")).click();
					browser.findElement(By.xpath(
							"//*[@id=\"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:q1:value20::content\"]"))
							.click();
					browser.findElement(By.xpath(
							"//*[@id=\"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:q1:value20::content\"]"))
							.sendKeys(ORDER_NUMBER);
					browser.findElement(By.xpath(
							"//*[@id=\"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:q1:value30::content\"]"))
							.click();
					browser.findElement(By.xpath(
							"//*[@id=\"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:q1:value30::content\"]"))
							.sendKeys(Line_Number);
					browser.findElement(By.xpath(
							"//*[@id=\"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:q1:value50::content\"]"))
							.click();
					browser.findElement(By.xpath(
							"//*[@id=\"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:q1:value50::content\"]"))
							.sendKeys(Item);
					browser.findElement(By.id(
							"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:q1::search"))
							.click();
					Thread.sleep(4000);
					try {
						browser.findElement(By.xpath(
								"//*[@id=\"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:AT1:_ATp:ATt1::db\"]/table/tbody/tr/td[1]"))
								.click();
						WebElement edit = browser.findElement(By.xpath(
								"//*[@id=\"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:AT1:_ATp:edit::icon\"]"));
						JavascriptExecutor js = (JavascriptExecutor) browser;
						js.executeScript("arguments[0].click()", edit);
						Thread.sleep(3000);
						browser.findElement(By.xpath(
								"//*[@id=\"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:AT1:r2:1:overrideScheduleDate::content\"]"))
								.click();
						Select sc = new Select(browser.findElement(By.xpath(
								"//*[@id=\"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:AT1:r2:1:overrideScheduleDate::content\"]")));
						sc.selectByVisibleText("Yes");
						Thread.sleep(3000);
						browser.findElement(By.xpath(
								"//*[@id=\"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:AT1:r2:1:id1::content\"]"))
								.click();
						browser.findElement(By.xpath(
								"//*[@id=\"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:AT1:r2:1:id1::content\"]"))
								.sendKeys(New_Schedule_Ship_Date);
						browser.findElement(By.xpath("//*[text()='ave and Close']")).click();
						try {
							Thread.sleep(2000);
							browser.findElement(By.id(
									"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:AT1:d9::ok"))
									.click();
							System.out.println("Valid Message");
							sheet.getRow(i).createCell(6).setCellValue("Pass");
						} catch (Exception e) {
							browser.findElement(By.id("d1::msgDlg::cancel")).click();
							browser.findElement(By.id(
									"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:AT1:d3::cancel"))
									.click();
							sheet.getRow(i).createCell(6).setCellValue("Fail");
						}
						JavascriptExecutor jsup = (JavascriptExecutor) browser;
						jsup.executeScript("window.scrollBy(0,-400)");
						Thread.sleep(4000);
						browser.findElement(By.id(
								"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:cb2"))
								.click();
						Thread.sleep(5000);
						browser.findElement(By.id(
								"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:cb1"))
								.click();
					} catch (Exception e1) {
						Thread.sleep(3000);
						browser.findElement(By.id(
								"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:cb2"))
								.click();
						Thread.sleep(4000);
						browser.findElement(By.id(
								"pt1:_FOr1:1:_FOSritemNode_order_management_order_management:0:_FOTsr1:1:FulSAP:cb1"))
								.click();
						sheet.getRow(i).createCell(6).setCellValue("Fail");
						e1.printStackTrace();
					}
					try {
						FileOutputStream fos = new FileOutputStream(f);
						wb.write(fos);
						wb.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void insertInExcelSheet() {

		System.out.println("Inside excel_data1()");
		try {
			int n = 1;
			for (int i = 1; i <= 5; i++) {
				// File f = new File(System.getProperty("user.dir") + "\\ExcelData\\Test.xlsx");
				File f = new File(TestRunner.path);
				FileInputStream fis = new FileInputStream(f);
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				XSSFSheet sheet = wb.getSheetAt(0);
				sheet.getRow(0).createCell(6).setCellValue("Results");
				int rowcount = sheet.getPhysicalNumberOfRows();
				if (n <= rowcount) {
					System.out.println("count: " + n);
					n++;
					// ORDER_NUMBER = sheet.getRow(i).getCell(0).getStringCellValue();
					// Line_Number = sheet.getRow(i).getCell(1).getStringCellValue();
					// Item = sheet.getRow(i).getCell(2).getStringCellValue();
					// New_Schedule_Ship_Date = sheet.getRow(i).getCell(5).getStringCellValue();
					Cell cell = sheet.getRow(0).getCell(13);
					if (cell == null)
						cell = sheet.getRow(0).createCell(3);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(username);
					cell = sheet.getRow(0).createCell(15);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(error);

					try {
						FileOutputStream fos = new FileOutputStream(f);
						wb.write(fos);
						wb.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						if (fis != null) {
							fis.close();
							System.out.println("Fileinput stream is closed");
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterTest()
	public void Quit_Browser() {
		browser.quit();
	}
}
