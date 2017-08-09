package com.automation.framework.filereader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.automation.framework.FileReaderInterface;
import com.automation.framework.inputdata.InputData;
import com.automation.framework.inputdata.Respository;
import com.automation.framework.inputdata.TestCase;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu> <bshinu51@gmail.com>
 * @version 1.0.0
 */
public abstract class AbstractFileReader implements FileReaderInterface {

	private InputData inputData = null;
	private InputStream inputStream = null;

	public InputData loadFile(String fileName) {
		try {
			inputData = new InputData();
			inputStream = new FileInputStream(fileName);
			inputData.setRepository(loadRepository(inputStream));
			inputData.setTestCase(loadTestCase(inputStream));
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
		return null;
	}

	abstract Respository loadRepository(InputStream inputStream);

	abstract TestCase loadTestCase(InputStream inputStream);

}
