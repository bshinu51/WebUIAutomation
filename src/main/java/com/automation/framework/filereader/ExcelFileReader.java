package com.automation.framework.filereader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

import com.automation.framework.data.Repository;
import com.automation.framework.data.TestCase;
import com.automation.framework.data.TestStep;
import com.automation.framework.data.TestSuite;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu> <bshinu51@gmail.com>
 * @version 1.0.0
 */
public class ExcelFileReader extends AbstractFileReader {

	private static final String FILE_NAME = "ExcelFileReader";
	private static final Logger LOG = Logger.getLogger(FILE_NAME);

	private static HSSFWorkbook workBook;
	private static final String XLS_REPOSITORY_SHEET = "Repository";
	private static final String XLS_TEST_CASE_SHEET = "TestCase";
	private static final String XLS_TEST_SUITE_SHEET = "TestSuite";

	@Override
	Repository loadRepository() {
		if (workBook == null)
			workBook = getWorkBook(inputStream);
		HSSFSheet mSheetInstance = workBook.getSheet(XLS_REPOSITORY_SHEET);
		Iterator<Row> rows = mSheetInstance.rowIterator();
		Repository repository = new Repository();
		if (rows.hasNext())
			rows.next();
		while (rows.hasNext()) {
			Row r = rows.next();
			repository.updateRepository(r.getCell(0).toString(), r.getCell(1).toString(), r.getCell(2).toString());
		}
		LOG.info("The repository is: " + repository);
		return repository;
	}

	@Override
	TestCase loadTestCase() {
		if (workBook == null)
			workBook = getWorkBook(inputStream);
		HSSFSheet mSheetInstance = workBook.getSheet(XLS_TEST_CASE_SHEET);
		Iterator<Row> rows = mSheetInstance.rowIterator();
		TestCase testCase = new TestCase();
		if (rows.hasNext())
			rows.next();
		while (rows.hasNext()) {
			Row r = rows.next();
			TestStep step = new TestStep(r.getCell(1).toString(), r.getCell(2).toString(), r.getCell(3).toString(),
					r.getCell(4) == null ? null : r.getCell(4).toString());
			testCase.addTestStep(r.getCell(0).toString(), step);
		}
		return testCase;
	}

	@Override
	TestSuite loadTestSuite() {
		if (workBook == null)
			workBook = getWorkBook(inputStream);
		HSSFSheet mSheetInstance = workBook.getSheet(XLS_TEST_SUITE_SHEET);
		Iterator<Row> rows = mSheetInstance.rowIterator();
		TestSuite testSuite = new TestSuite();
		if (rows.hasNext())
			rows.next();
		while (rows.hasNext()) {
			Row r = rows.next();
			String step = r.getCell(1).toString();
			testSuite.addTestCases(r.getCell(0).toString(), step);
		}
		return testSuite;
	}

	private HSSFWorkbook getWorkBook(InputStream inputStream) {
		if (inputStream != null) {
			try {
				POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);
				workBook = new HSSFWorkbook(fileSystem);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return workBook;
	}
}
