package com.ExtentReports.tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportWithConfigIntegration {
	

	@Test
	public void extentTest() throws IOException {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("index.html");//html report will be generated
		
		//using extentconfig.xml
		/*
		 * final File CONF = new File("extentconfig.xml"); spark.loadXMLConfig(CONF);
		 * 
		 */
		
		//using extentconfig.json
		final File CONF = new File("extentconfig.json");
		spark.loadJSONConfig(CONF);
		extent.attachReporter(spark);
		
		ExtentTest test = extent.createTest("Login flow").assignAuthor("Madhu").assignCategory("regression").assignDevice("chrome");//create a test node in the report
		test.pass("login test started successfully");//create a test step node in the report
		test.info("url is loaded");
		test.pass("login is successful");
		test.fail("dashboard failure");
		
		ExtentTest test1 = extent.createTest("Registration flow").assignAuthor("Madhu").assignCategory("Regression").assignDevice("firefox");
		test1.pass("registration test started successfully");
		test1.info("url is loaded");
		test1.pass("registration is successful");
		test1.fail("username failure");
		
		extent.flush();//unless you call this method your report will not be written with logs.
		Desktop.getDesktop().browse(new File("index.html").toURI());//no need to manually open the extent report.This code will open the report after execution is completed
	}


}
