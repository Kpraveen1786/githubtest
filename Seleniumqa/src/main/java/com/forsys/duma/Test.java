package com.forsys.duma;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test {

	WebDriver driver;

	@BeforeTest
	public void setUp() {
		System.out.println("setup()");
		try {
			// setDriverProperties();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.navigate().to("http://www.google.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
