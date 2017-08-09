package com.automation;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.automation.framework.FileReaderInterface;
import com.automation.framework.filereader.CSVFileReader;
import com.automation.framework.filereader.ExcelFileReader;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu> <bshinu51@gmail.com>
 * @version 1.0.0
 */
public class AutomationFactory {

	private static final String FILE_NAME = "AutomationFactory";
	private static final Logger LOG = Logger.getLogger(FILE_NAME);

	private static FileReaderInterface sRreaderInstance = null;

	private static final String XLS_FILE_TYPE = ".xls";
	private static final String CSV_FILE_TYPE = ".csv";

	public static final String CHROME_BROWSER = "chrome";
	public static final String FIREFOX_BROWSER = "firefox";
	public static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	public static final String RESOURCE_PATH = "src/main/resources/";
	public static final String CHROME_PATH = "chromedriver.exe";

	private AutomationFactory() {
	}

	public static FileReaderInterface getFileReaderInstance(String file) {
		if (sRreaderInstance == null) {
			if (file.contains(XLS_FILE_TYPE)) {
				sRreaderInstance = new ExcelFileReader();
			} else if (file.contains(CSV_FILE_TYPE)) {
				sRreaderInstance = new CSVFileReader();
			}
		}
		LOG.info("Created " + sRreaderInstance.getClass() + " instance");
		return sRreaderInstance;
	}

	public static WebDriver getWebDriverInstance(String driver) {
		if (driver.equalsIgnoreCase(CHROME_BROWSER)) {
			System.setProperty(CHROME_DRIVER_PROPERTY, RESOURCE_PATH
					+ CHROME_PATH);
			return new ChromeDriver();

		} else if (driver.equalsIgnoreCase(FIREFOX_BROWSER)) {
			return new FirefoxDriver();
		}
		return null;
	}

}
