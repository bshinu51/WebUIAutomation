package com.automation.framework.data;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu> <bshinu51@gmail.com>
 * @version 1.0.0
 */
public class TestStep {
	private String name;
	private String elementName;
	private String action;
	private String inputParameter;
	private Result result;

	public TestStep(String name, String elementName, String action, String inputParameter) {
		this.name = name;
		this.elementName = elementName;
		this.action = action;
		this.inputParameter = inputParameter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getInputParameter() {
		return inputParameter;
	}

	public void setInputParameter(String inputParameter) {
		this.inputParameter = inputParameter;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Test Step[ name:" + name + ", elementName:" + elementName + ", action:" + action
				+ ", inputParameter:" + inputParameter + ", result:" + result + "]";
	}
}
