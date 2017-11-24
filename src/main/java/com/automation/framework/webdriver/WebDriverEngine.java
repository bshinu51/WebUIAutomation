package com.automation.framework.webdriver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.automation.framework.data.InputData;
import com.automation.framework.data.TestCase;
import com.automation.framework.data.TestStep;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu> <bshinu51@gmail.com>
 * @version 1.0.0
 */
public class WebDriverEngine {

	private static final String FILE_NAME = "ExcelReader";
	private static final Logger LOG = Logger.getLogger(FILE_NAME);
	private WebDriver webDriver;
	private InputData mInputData;

	public WebDriverEngine(WebDriver driver) {
		webDriver = driver;
	}

	public void startExecution(InputData inputData, ArrayList<String> jenkinTestCases) {
		if (webDriver != null && inputData != null) {
			mInputData = inputData;
			ActionHandler.initialize(webDriver);
			for (Entry<String, LinkedList<String>> testSuite : inputData.getTestSuite().getTestSuiteMap().entrySet())
				for (String testCase : testSuite.getValue()) {
					LinkedList<TestStep> testSteps = inputData.getTestCase().getTestCaseMap().get(testCase);
					;
					if (testSteps != null) {
						LOG.info("startExecution(): execute " + testCase + " test case");
						try {
							executeTestCase(testCase, testSteps, false);
						} catch (InvalidResultExeception e) {
							e.printStackTrace();
						}
						//ActionHandler.closeWindows();
					}
				}
		}
	}

	private void executeTestCase(String testName, LinkedList<TestStep> testSteps, boolean b)
			throws InvalidResultExeception {
		for (TestStep testStep : testSteps) {
			try {
				System.out.println("befor " + testStep);
				testStep.setResult(ActionHandler.parseAndAct(testName, testStep,
						mInputData.getRepository().getLocatorTypeByEleName(testStep.getElementName()),
						mInputData.getRepository().getLocatorValueByEleName(testStep.getElementName())));

				System.out.println("after " + testStep);
				if (testStep.getResult().isNested) {
					System.out.println(testStep.getName());
					LinkedList<TestStep> additionalSteps = mInputData.getTestCase().getTestCaseMap()
							.get(testStep.getName());
					executeTestCase(testName, additionalSteps, true);
				}

			} catch (InvalidResultExeception e) {
				e.printStackTrace();
				if (testStep.getResult() != null && testStep.getResult().isNested) {
					testStep.setResult(e.getResult());
				} else {
					if (testStep.getResult() != null)
						break;
				}
			}
		}
	}

	public void cleanUp() {
		webDriver.quit();
	}
}
