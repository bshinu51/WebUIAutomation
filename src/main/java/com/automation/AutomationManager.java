package com.automation;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.automation.framework.FileReaderInterface;
import com.automation.framework.data.InputData;
import com.automation.framework.webdriver.WebDriverEngine;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu> <bshinu51@gmail.com>
 * @version 1.0.0
 */
public class AutomationManager {

	private static final String FILE_NAME = "AutomationManager";
	private static final Logger LOG = Logger.getLogger(FILE_NAME);

	private static String TEST_FILE_NAME = "Automation_Test_Script.xls";
	private static boolean failed;

	public static void initiateTestExecution(ArrayList<String> jenkinArgs) {
		if (jenkinArgs != null && jenkinArgs.size() > 0) {
			TEST_FILE_NAME = jenkinArgs.get(0);
		}
		FileReaderInterface reader = AutomationFactory.getFileReaderInstance(TEST_FILE_NAME);
		InputData inputData = reader.loadFile(TEST_FILE_NAME);
		WebDriver mDriver = AutomationFactory.getWebDriverInstance("chrome");
		WebDriverEngine webDriverEngine = new WebDriverEngine(mDriver);
		executeConfigs(webDriverEngine, inputData, jenkinArgs);
	}

	private static void executeConfigs(WebDriverEngine webDriverEngine, InputData entireInputData,
			ArrayList<String> jenkinTestCases) {
		webDriverEngine.startExecution(entireInputData, jenkinTestCases);
	}

	public static boolean hasFailed() {
		return failed;
	}

}
