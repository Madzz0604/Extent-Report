package com.ExtentReports.tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentReportWithCustomization {
	
	/**
	 * 
	 * 1.Change the viewing order in left side panel
	 * 2.remove some menu from left panel
	 * 3.Highlight a particular logline
	 * 4.List of strings-how can i log in the report
	 * 5.Map<String,String> ---how to log this
	 */
	
	@Test
	public void extentTest() throws IOException {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("index.html").viewConfigurer().viewOrder().as(new ViewName[] {ViewName.DASHBOARD,ViewName.TEST,ViewName.CATEGORY}).apply();//html report will be generated
		
		
		spark.config().setTheme(Theme.DARK);
		 spark.config().setDocumentTitle("Automation Report");
		 spark.config().setReportName("extent reports demo");
		 extent.attachReporter(spark);
		
		ExtentTest test = extent.createTest("Login flow").assignAuthor("Madhu").assignCategory("regression").assignDevice("chrome");//create a test node in the report
		test.pass("login test started successfully");//create a test step node in the report
		test.info("url is loaded");
		test.pass("login is successful");
		test.pass(MarkupHelper.createOrderedList(Arrays.asList(new String[] {"Selenium","Appium","RestAssured"})).getMarkup());//to print array of string
		test.pass(MarkupHelper.createUnorderedList(Arrays.asList(new String[] {"Selenium","Appium","RestAssured"})).getMarkup());//to print array of string without numbers count
		
		Map<String,String> map = new HashMap<>();
		map.put("fname", "madhu");
		map.put("lname", "singh");
		map.put("extentreport", "demo");
		
		//map.forEach((k,v)->test.pass(k+" : "+v));
		test.pass(MarkupHelper.createUnorderedList(map).getMarkup());//map data
		
		test.pass(MarkupHelper.createLabel("login is successful", ExtentColor.GREEN));//highlight the log line
		test.fail("dashboard failure");
		test.fail(MarkupHelper.createLabel("dashboard failure", ExtentColor.RED));
		
		ExtentTest test1 = extent.createTest("Registration flow").assignAuthor("Madhu").assignCategory("Regression").assignDevice("firefox");
		test1.pass("registration test started successfully");
		test1.info("url is loaded");
		test1.pass("registration is successful");
		test1.fail("username failure");
		
		extent.flush();//unless you call this method your report will not be written with logs.
		Desktop.getDesktop().browse(new File("index.html").toURI());//no need to manually open the extent report.This code will open the report after execution is completed
		
	}


}
