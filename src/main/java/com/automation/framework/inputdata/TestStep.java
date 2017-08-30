package com.automation.framework.inputdata;

public class TestStep {
	private String name;
	private String elementName;
	private String action;
	private String inputParameter;

	public TestStep(String name, String elementName, String action,
			String inputParameter) {
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

	@Override
	public String toString() {
		return name + " " + elementName + " " + action + " " + inputParameter;
	}
}
