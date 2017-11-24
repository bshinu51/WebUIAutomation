package com.automation.framework.data;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu>
 * @version 1.0.0
 */
public class TestSuite {
	private LinkedHashMap<String, LinkedList<String>> testSuiteMap;

	public TestSuite() {
		testSuiteMap = new LinkedHashMap<>();
	}

	public void addTestCases(String key, String stepName) {
		if (testSuiteMap.containsKey(key)) {
			LinkedList<String> testCase = testSuiteMap.get(key);
			testCase.add(stepName);
		} else {
			LinkedList<String> value = new LinkedList<String>();
			value.add(stepName);
			testSuiteMap.put(key, value);
		}
	}

	public LinkedHashMap<String, LinkedList<String>> getTestSuiteMap() {
		return testSuiteMap;
	}

	public void setTestSuiteMap(LinkedHashMap<String, LinkedList<String>> testSuiteMap) {
		this.testSuiteMap = testSuiteMap;
	}
}
