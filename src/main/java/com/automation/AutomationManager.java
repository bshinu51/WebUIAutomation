package com.automation;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.automation.framework.FileReaderInterface;
import com.automation.framework.inputdata.InputData;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu> <bshinu51@gmail.com>
 * @version 1.0.0
 */
public class AutomationManager {

	private static final String FILE_NAME = "AutomationManager";
	private static final Logger LOG = Logger.getLogger(FILE_NAME);

	private static final String TEST_FILE_NAME = "Automation_Test_Script.xls";

	public static void initiateTestExecution(ArrayList<String> jenkinTestCases) {
		if (jenkinTestCases != null && jenkinTestCases.size() > 0) {
			setUrl(jenkinTestCases.get(0));
			LOG.info("The url extracted is: " + getUrl());
		}
		FileReaderInterface reader = AutomationFactory
				.getFileReaderInstance(TEST_FILE_NAME);
		InputData inputData = reader.loadFile(TEST_FILE_NAME);
		executeConfigs(inputData, jenkinTestCases);

	}

	private static String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	private static void setUrl(String string) {
		// TODO Auto-generated method stub

	}

	private static void executeConfigs(InputData entireInputData,
			ArrayList<String> jenkinTestCases) {
		// TODO Auto-generated method stub

	}

	public static boolean hasFailed() {
		// TODO Auto-generated method stub
		return false;
	}

}
