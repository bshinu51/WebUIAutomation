package com.automation.framework;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * @author Shrinivas Bhat <sbhat10@asu.edu>
 * @version 1.0.0
 */
public class Utils {
	
	public static long timeDifference(String format, String start, String end,
			TimeUnit timeunit) {

		Date date1, date2;
		try {
			date1 = new SimpleDateFormat(format).parse(start);
			date2 = new SimpleDateFormat(format).parse(end);
		} catch (ParseException e) {
			// Parsing error
			return 0;
		}
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeunit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	public static String strConcat(String[] input, int start, int end) {
		String temp = "";
		for (int i = start; i <= end; i++) {
			temp += input[i];
			if (i != end)
				temp += ":";
		}
		temp.replaceAll("\"", "&quot;");
		temp.replaceAll("\'", "&quot;");
		return temp;
	}

	public static String now(String formatType) {
		DateFormat dateFormat = new SimpleDateFormat(formatType);
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String uglify(String input) {
		return input.replaceAll("\\s", "_").toLowerCase();
	}
	
	public static void makeDir(String dirName) {
		File theDir = new File(dirName);
		if (!theDir.exists()) {
		    theDir.mkdir();  
		}
	}

	public static int getIntFromXLSValue(String inputParameter) {
		return (Float.valueOf(inputParameter)).intValue();
	}

}
