package com.automation.framework.webdriver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.automation.framework.inputdata.InputData;
import com.automation.framework.inputdata.TestStep;
import com.automation.framework.outputdata.CompleteResult;

public class WebDriverEngine {

	private static final String FILE_NAME = "ExcelReader";
	private static final Logger LOG = Logger.getLogger(FILE_NAME);
	private WebDriver webDriver;

	public WebDriverEngine(WebDriver driver) {
		webDriver = driver;
	}

	public CompleteResult startExecution(InputData inputData,
			ArrayList<String> jenkinTestCases) {
		CompleteResult finalResult = null;
		if (webDriver != null && inputData != null) {
			finalResult = new CompleteResult();
			for (Entry<String, LinkedList<TestStep>> testCase : inputData
					.getTestCase().getTestCaseMap().entrySet()) {

				LinkedList<TestStep> testSteps = testCase.getValue();
				if (testSteps != null) {
					LOG.info("startExecution(): execute " + testCase.getKey()
							+ " test case");
					try {
						finalResult.setStepResults(
								testCase.getValue(),
								executeTestCase(testCase.getValue(), testSteps,
										false));
					} catch (InvalidResultExeception e) {
						e.printStackTrace();
					}
					ActionHandler.closeWindows();
				}
			}
		}
		return finalResult;
	}

	private Object executeTestCase(LinkedList<TestStep> value,
			LinkedList<TestStep> testSteps, boolean b)
			throws InvalidResultExeception {
		return null;
	}

}
