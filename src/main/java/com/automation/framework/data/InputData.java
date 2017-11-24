package com.automation.framework.data;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu> <bshinu51@gmail.com>
 * @version 1.0.0
 */
public class InputData {

	private Repository repository;
	private TestCase testCase;

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	public Repository getRepository() {
		return repository;
	}

	public TestCase getTestCase() {
		return testCase;
	}
}
