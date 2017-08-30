package com.automation.framework.filereader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.automation.framework.FileReaderInterface;
import com.automation.framework.inputdata.InputData;
import com.automation.framework.inputdata.Repository;
import com.automation.framework.inputdata.TestCase;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu> <bshinu51@gmail.com>
 * @version 1.0.0
 */
public abstract class AbstractFileReader implements FileReaderInterface {

	protected InputData inputData = null;
	protected InputStream inputStream = null;

	public InputData loadFile(String fileName) {
		try {
			inputData = new InputData();
			inputStream = new FileInputStream(fileName);
			inputData.setRepository(loadRepository());
			inputData.setTestCase(loadTestCase());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return inputData;
	}

	abstract Repository loadRepository();

	abstract TestCase loadTestCase();

}
