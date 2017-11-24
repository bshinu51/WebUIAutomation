package com.automation.framework.webdriver;

import com.automation.framework.data.Result;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu> <bshinu51@gmail.com>
 * @version 1.0.0
 */
public class InvalidResultExeception extends Exception {

	private static final long serialVersionUID = -8404564189981503760L;

	private Result result;

	public InvalidResultExeception(Result result) {
		this.result = result;
	}

	public Result getResult() {
		return result;
	}

}
