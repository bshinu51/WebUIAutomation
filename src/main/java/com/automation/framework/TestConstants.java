package com.automation.framework;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu>
 * @version 1.0.0
 */
public class TestConstants {
	/**
	 * The fields represent reader package constants
	 */
	// XLS Constants
	public static final String XLS_FILE_TYPE = "xls";
	public static final String XLS_FILE_NAME = "Automation_Test_Script.xls";
	public static final String XLS_CONFIG_SHEET = "config";
	public static final String XLS_EXE_MGR_SHEET = "execution_manager";
	public static final String XLS_OBJECT_REPO_SHEET = "object_repository";
	public static final String XLS_EDITOR_SHEET = "Editor";

	// CSV constants
	public static final String CSV_FILE_TYPE = "csv";

	// Driver constants
	public static final String CHROME_BROWSER = "chrome";
	public static final String FIREFOX_BROWSER = "firefox";
	public static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	public static final String RESOURCE_PATH = "src/main/resources/";
	public static final String SCRIPT_PATH = "script/";
	public static final String GRID_PATH = "grid_startup_windows.bat";
	public static final String CHROME_PATH = "chromedriver.exe";

	// Test
	public static final String FACT_URL = "http://localhost:3000";

	// Pre Execution testcases
	public static final String PRE_EXECUTION = "TC_Login_pre";

	// Actions
	public static final String RESULT_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss:S";
	public static final String ACTION_ISDISPLAYED = "displayed";
	public static final String ACTION_RUN = "run";
	public static final String ACTION_CLICK = "click";
	public static final String ACTION_INPUT = "input";
	public static final String ACTION_SEND = "send";
	public static final String ACTION_RIGHT_CLICK = "rightclick";
	public static final String ACTION_CLEAR = "clear";
	public static final String ACTION_SELECT_BY_TEXT = "selectbytext";
	public static final String ACTION_HOVER = "hover";
	public static final String ACTION_MOUSE_DOWN = "mousedown";
	public static final String ACTION_MOUSE_MOVE = "mousemove";
	public static final String ACTION_MOUSE_UP = "mouseup";
	public static final String ACTION_DRAG_AND_DROP = "draganddrop";
	public static final String ACTION_WINDOW_SWITCH_TO = "switchto";
	public static final String ACTION_WINDOW_OPEN = "open";
	public static final String ACTION_WINDOW_OPEN_GET = "open/get";
	public static final String ACTION_ACCEPT_ALERT = "acceptalert";
	public static final String ACTION_JAVASCRIPT = "javascript";
	public static final String ACTION_SAVE = "save";

	// Elements
	public static final long TIME_OUT = 10; // Wait for 10 Sec
	public static final String BY_ID = "id";
	public static final String BY_XPATH = "xpath";
	public static final String BY_CSS = "css";
	public static final String BY_CLASSNAME = "classname";
	public static final String BY_LINKTEXT = "linktext";
	public static final String BY_NAME = "name";
	public static final String BY_PARTIAL_LT = "partiallinktext";
	public static final String BY_TAGNAME = "tagname";

	// SEND_KEYS
	public static final String SEND_KEY_ESC = "esc";
	public static final String SEND_KEY_ENTER = "enter";
	public static final String SEND_KEY_BACKSPACE = "backspace";
	public static final String SEND_KEY_DOWN = "down";
	public static final String SEND_KEY_UP = "up";
	public static final String SEND_KEY_LEFT = "left";
	public static final String SEND_KEY_RIGHT = "right";
	public static final String SEND_KEY_COPY = "copy";
	public static final String SEND_KEY_PASTE = "paste";

	// MISC
	public static final String GET_SAVED_INPUT = "$";

	// Report-Gen
	public static final String HTML_PROPERTY = "staticHtml.properties";
	public static final String REPORT_NAME_FORMAT = "yyyyMMdd_HHmmss";
	public static final String DISPLAY_DATE = "dd/MM/yyyy";
	public static final String DISPLAY_TIME = "HH:mm:ss";
	public static final String SRC_DIR = "/src/main/resources/report";
	public static final String TARGET_DIR = "/resources";
	public static final String REPORT_DIR = "/reports";
	public static final String REPORT_PREFIX = "reports_";
	public static final String AUTOMATR_IMAGES = "automatr_images";

	// Report-Templete
	public static final String LOOP_MODULE_TAB_TABLE = "@forAll_module_tab_table";
	public static final String LOOP_MODULE_TEMPLETE = "@forAll_module_templete";
	public static final String LOOP_TESTCASE_DETAILS = "@forAll_test_case_details";
	public static final String VAR_PATTERN = "@\\w+";

}
