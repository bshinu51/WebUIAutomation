package com.automation.framework.data;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu>
 * @version 1.0.0
 */
public class TestSuite {
	private HashMap<String, LinkedList<String>> testSuiteMap;

	public void addTestCases(String key, String stepName) {
		if (testSuiteMap.containsKey(key)) {
			LinkedList<String> testCase = testSuiteMap.get(key);
			testCase.add(stepName);
		} else {
			LinkedList<String> value = new LinkedList<String>();
			testSuiteMap.put(key, value);
		}
	}
}
