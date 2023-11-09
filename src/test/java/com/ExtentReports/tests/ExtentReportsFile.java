package com.ExtentReports.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsFile {
	
	@Test
	public void extentTest() {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("index.html");//html report will be generated
		
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("extent reports demo");
		extent.attachReporter(spark);
		
		ExtentTest test = extent.createTest("Login flow").assignAuthor("Madhu").assignCategory("regression").assignDevice("chrome");//create a test node in the report
		test.pass("login test started successfully");//create a test step node in the report
		test.info("url is loaded");
		test.pass("login is successful");
		
		ExtentTest test1 = extent.createTest("Registration flow").assignAuthor("Madhu").assignCategory("Regression").assignDevice("firefox");
		test1.pass("registration test started successfully");
		test1.info("url is loaded");
		test1.pass("registration is successful");
		
		extent.flush();//unless you call this method your report will not be written with logs.
		
		
	}

}
