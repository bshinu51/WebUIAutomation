package com.automation.framework.webdriver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.automation.framework.data.InputData;
import com.automation.framework.data.TestStep;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu> <bshinu51@gmail.com>
 * @version 1.0.0
 */
public class WebDriverEngine {

	private static final String FILE_NAME = "ExcelReader";
	private static final Logger LOG = Logger.getLogger(FILE_NAME);
	private WebDriver webDriver;

	public WebDriverEngine(WebDriver driver) {
		webDriver = driver;
	}

	public void startExecution(InputData inputData, ArrayList<String> jenkinTestCases) {
		if (webDriver != null && inputData != null) {
			for (Entry<String, LinkedList<TestStep>> testCase : inputData.getTestCase().getTestCaseMap().entrySet()) {

				LinkedList<TestStep> testSteps = testCase.getValue();
				if (testSteps != null) {
					LOG.info("startExecution(): execute " + testCase.getKey() + " test case");
					try {
						executeTestCase(testCase.getValue(), testSteps, false);
					} catch (InvalidResultExeception e) {
						e.printStackTrace();
					}
					ActionHandler.closeWindows();
				}
			}
		}
	}

	private void executeTestCase(LinkedList<TestStep> value, LinkedList<TestStep> testSteps, boolean b)
			throws InvalidResultExeception {
	}

}
