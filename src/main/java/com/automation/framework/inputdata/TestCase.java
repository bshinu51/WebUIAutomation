package com.automation.framework.inputdata;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu> <bshinu51@gmail.com>
 * @version 1.0.0
 */
public class TestCase {
	private HashMap<String, LinkedList<TestStep>> testCaseMap;

	public TestCase() {
		testCaseMap = new HashMap<String, LinkedList<TestStep>>();
	}

	public void addTestStep(String key, TestStep step) {
		if (testCaseMap.containsKey(key)) {
			LinkedList<TestStep> teststep = testCaseMap.get(key);
			teststep.add(step);
		} else {
			LinkedList<TestStep> value = new LinkedList<TestStep>();
			testCaseMap.put(key, value);
		}
	}

	public HashMap<String, LinkedList<TestStep>> getTestCaseMap() {
		return testCaseMap;
	}
}
