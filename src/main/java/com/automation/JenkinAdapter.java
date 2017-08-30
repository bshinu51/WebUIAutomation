package com.automation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * To trigger build from Jenkin this Adapter is used. Call main method with
 * selected test cases if required. Or the framework will execute the default
 * test cases extracted from the Automation.xls.
 * 
 * <p>
 * <strong> Note that if any of the test case fails, then the system shall exit
 * with failure, and generate report and stores the report into <em>reports</em>
 * folder. If all the test cases pass, then system shall exit with
 * success.</strong>
 * 
 * @author Shrinivas Bhat <sbhat10@asu.edu> <bshinu51@gmail.com>
 * @version 1.0.0
 */
public class JenkinAdapter {

	public static void main(String[] args) {
		ArrayList<String> jenkinTestCases = new ArrayList<String>(
				Arrays.asList(args));
		AutomationManager.initiateTestExecution(jenkinTestCases);
		if (AutomationManager.hasFailed()) {
			System.exit(1);
		}
		System.exit(0);
	}
}
