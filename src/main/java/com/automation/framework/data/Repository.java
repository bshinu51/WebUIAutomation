package com.automation.framework.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu> <bshinu51@gmail.com>
 * @version 1.0.0
 */
public class Repository {
	LinkedHashMap<String, ArrayList<String>> repoMap;

	public Repository() {
		repoMap = new LinkedHashMap<String, ArrayList<String>>();
	}

	public void updateRepository(String name, String locatorType,
			String locatorValue) {
		ArrayList<String> value = new ArrayList<String>();
		value.add(locatorType);
		value.add(locatorValue);
		repoMap.put(name, value);
	}

	public String getLocatorType(String name) {
		ArrayList<String> value = repoMap.get(name);
		return value.get(0);
	}

	public String getLocatorValue(String name) {
		ArrayList<String> value = repoMap.get(name);
		return value.get(1);
	}
}
