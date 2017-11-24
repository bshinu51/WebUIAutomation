package com.automation.framework.webdriver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.framework.TestConstants;
import com.automation.framework.Utils;
import com.automation.framework.data.Result;
import com.automation.framework.data.TestStep;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu> <bshinu51@gmail.com>
 * @version 1.0.0
 */
public class ActionHandler {

	private static final String FILE_NAME = "ActionHandler";
	private static final Logger LOG = Logger.getLogger(FILE_NAME);

	static WebDriver mDriver;
	private static String storeTemp;
	private static List<String> windowHandles = new ArrayList<String>();
	private static String parentWindowHandle;
	private static boolean isDirCreated;

	public static void initialize(WebDriver driver) {
		mDriver = driver;
	}

	public static Result parseAndAct(String testName, TestStep testStep, String locType, String locValue)
			throws InvalidResultExeception {
		String action = testStep.getAction().toLowerCase();
		WebElement element = null;
		Result result = new Result();
		result.startTime = Utils.now(TestConstants.RESULT_TIME_FORMAT);
		if (locType != null && locValue != null) {
			try {
				element = getElement(locType, locValue);
			} catch (InvalidResultExeception e) {
				result.hasPassed = false;
				result.screenShotPath = captureScreenShot(element, testName, testStep);
				result.endTime = Utils.now(TestConstants.RESULT_TIME_FORMAT);
				e.printStackTrace();
				throw new InvalidResultExeception(result);
			}
		}
		Actions mouseAction = null;
		Select select = null;
		LOG.info("perform " + action + "() on element " + element);
		try {
			switch (action) {
			case TestConstants.ACTION_RUN:
				result.isNested = true;
				break;
			case TestConstants.ACTION_WINDOW_SWITCH_TO:
			case TestConstants.ACTION_WINDOW_OPEN_GET:
			case TestConstants.ACTION_WINDOW_OPEN:
				handleWindowAction(testStep, action);
				break;
			case TestConstants.ACTION_CLICK:
				if (element.isDisplayed()) {
					element.click();
				} else {
					try {
						((JavascriptExecutor) mDriver).executeScript("arguments[0].scrollIntoView(true);", element);
						Thread.sleep(1000);
						element.click();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			case TestConstants.ACTION_INPUT:
				String input = testStep.getInputParameter();
				if (input.contains(TestConstants.GET_SAVED_INPUT))
					input = storeTemp;
				element.sendKeys(input);
				break;
			case TestConstants.ACTION_SEND:
				sendKeys(element, testStep.getInputParameter());
				break;
			case TestConstants.ACTION_SAVE:
				if (element.getText() != null && !element.getText().isEmpty()) {
					storeTemp = element.getText();
				} else if (element.getAttribute("value") != null && !element.getAttribute("value").isEmpty()) {
					storeTemp = element.getAttribute("value");
				} else {
					result.hasPassed = false;
				}
				break;
			case TestConstants.ACTION_ACCEPT_ALERT:
				Alert alert = mDriver.switchTo().alert();
				alert.accept();
				break;
			case TestConstants.ACTION_JAVASCRIPT:
				if (mDriver instanceof JavascriptExecutor) {
					((JavascriptExecutor) mDriver).executeScript(testStep.getInputParameter());
					result.hasPassed = true;
				}
				break;
			case TestConstants.ACTION_RIGHT_CLICK:
				mouseAction = new Actions(mDriver);
				mouseAction.moveToElement(element);
				mouseAction.contextClick(element).build().perform();
				break;
			case TestConstants.ACTION_CLEAR:
				element.clear();
				break;
			case TestConstants.ACTION_SELECT_BY_TEXT:
				select = new Select(element);
				select.selectByVisibleText(testStep.getInputParameter());
				break;
			case TestConstants.ACTION_HOVER:
				try {
					mouseAction = new Actions(mDriver);
					mouseAction.moveToElement(element).perform();
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;

			case TestConstants.ACTION_MOUSE_DOWN:
				mouseAction = new Actions(mDriver);
				mouseAction.moveToElement(element);
				mouseAction.clickAndHold().build().perform();
				break;
			// deprecated
			/*
			 * case TestConstants.ACTION_MOUSE_MOVE: String misc[] =
			 * testStep.getMisc().split(","); int xOffset =
			 * Utils.getIntFromXLSValue(misc[0]); int yOffset =
			 * Utils.getIntFromXLSValue(misc[1]); mouseAction = new Actions(mDriver);
			 * mouseAction.moveByOffset(xOffset, yOffset).build().perform(); break;
			 */

			case TestConstants.ACTION_MOUSE_UP:
				mouseAction = new Actions(mDriver);
				mouseAction.release().build().perform();
				break;

			case TestConstants.ACTION_DRAG_AND_DROP:
				try {
					WebElement onElement = element;
					WebElement toElement = mDriver.findElement(By.xpath(testStep.getInputParameter()));
					mouseAction = new Actions(mDriver);
					mouseAction.clickAndHold(onElement);
					Thread.sleep(500);
					mouseAction.moveToElement(toElement);
					Thread.sleep(500);
					mouseAction.release();
					mouseAction.perform();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
		} catch (Exception e) {
			result.hasPassed = false;
			result.screenShotPath = captureScreenShot(element, testName, testStep);
			result.endTime = Utils.now(TestConstants.RESULT_TIME_FORMAT);
			e.printStackTrace();
			throw new InvalidResultExeception(result);
		}
		result.endTime = Utils.now(TestConstants.RESULT_TIME_FORMAT);
		return result;

	}

	private static void sendKeys(WebElement element, String inputParameter) {
		switch (inputParameter) {
		case TestConstants.SEND_KEY_BACKSPACE:
			element.sendKeys(Keys.BACK_SPACE);
			break;
		case TestConstants.SEND_KEY_ENTER:
			element.sendKeys(Keys.ENTER);
			break;
		case TestConstants.SEND_KEY_ESC:
			element.sendKeys(Keys.ESCAPE);
			break;
		case TestConstants.SEND_KEY_LEFT:
			element.sendKeys(Keys.LEFT);
			break;
		case TestConstants.SEND_KEY_RIGHT:
			element.sendKeys(Keys.RIGHT);
			break;
		case TestConstants.SEND_KEY_UP:
			element.sendKeys(Keys.UP);
			break;
		case TestConstants.SEND_KEY_DOWN:
			element.sendKeys(Keys.DOWN);
			break;
		case TestConstants.SEND_KEY_PASTE:
			element.sendKeys(Keys.chord(Keys.CONTROL, "p"));
			break;
		case TestConstants.SEND_KEY_COPY:
			element.sendKeys(Keys.chord(Keys.CONTROL, "c"));
			break;
		default:
			break;
		}
	}

	public static WebElement getElement(String type, String value) throws InvalidResultExeception {
		WebDriverWait wait = new WebDriverWait(mDriver, TestConstants.TIME_OUT);
		try {
			switch (type.toLowerCase()) {

			case TestConstants.BY_ID:
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.id(value)));

			case TestConstants.BY_XPATH:
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(value)));

			case TestConstants.BY_CSS:
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(value)));

			case TestConstants.BY_CLASSNAME:
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.className(value)));

			case TestConstants.BY_LINKTEXT:
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(value)));

			case TestConstants.BY_NAME:
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.name(value)));

			case TestConstants.BY_PARTIAL_LT:
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(value)));

			case TestConstants.BY_TAGNAME:
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(value)));

			default:
			}
		} catch (Exception e) {
			LOG.info("There was a runtime exception " + e.getMessage());
			throw new InvalidResultExeception(null);
		}
		return null;
	}

	private static void handleWindowAction(TestStep testStep, String action) {
		if (action.equals(TestConstants.ACTION_WINDOW_SWITCH_TO)) {
			String windowName = windowHandles.get(Utils.getIntFromXLSValue(testStep.getInputParameter()));
			mDriver.switchTo().window(windowName);
			mDriver.manage().window().maximize();

		} else if (action.equals(TestConstants.ACTION_WINDOW_OPEN_GET)
				|| action.equals(TestConstants.ACTION_WINDOW_OPEN)) {
			if (mDriver instanceof JavascriptExecutor) {
				String JS = "window.open('" + testStep.getInputParameter() + "', '_blank');";
				((JavascriptExecutor) mDriver).executeScript(JS);
			}

			Iterator<String> itr = mDriver.getWindowHandles().iterator();
			Object lastWindow = itr.next();
			while (itr.hasNext()) {
				lastWindow = itr.next();
			}

			if (parentWindowHandle == null)
				parentWindowHandle = (String) lastWindow;
			windowHandles.add((String) lastWindow);
			mDriver.switchTo().window((String) lastWindow);
			mDriver.manage().window().maximize();
		}
	}

	private static String captureScreenShot(WebElement element, String testCaseName, TestStep testStep) {
		if (element != null) {
			((JavascriptExecutor) mDriver).executeScript("arguments[0].style.boxShadow='0px 0px 40px red'", element);
			((JavascriptExecutor) mDriver).executeScript("arguments[0].style.border='3px solid red'", element);
		}
		File outPutFile = ((TakesScreenshot) mDriver).getScreenshotAs(OutputType.FILE);
		String automatrPath = System.getProperty("user.dir") + "/" + TestConstants.AUTOMATR_IMAGES;
		if (!isDirCreated) {
			File locDir = new File(automatrPath);
			if (!locDir.exists()) {
				locDir.mkdir();
			}
			isDirCreated = true;
		}
		try {
			String fileName = testCaseName + "-" + testStep.getName().trim() + ".jpg";
			File destFile = new File(automatrPath + "/" + fileName);
			FileUtils.copyFile(outPutFile, destFile);
			LOG.info("ScreenShot Path is: " + destFile.getAbsolutePath());
			return fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void closeWindows() {
		Iterator<String> it = mDriver.getWindowHandles().iterator();
		parentWindowHandle = it.next();
		while (it.hasNext()) {
			mDriver.switchTo().window(it.next()).close();
		}
		mDriver.switchTo().window(parentWindowHandle);
		windowHandles.clear();
	}
}
