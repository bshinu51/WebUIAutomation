package com.automation.framework.data;

/**
 * @author Shrinivas Bhat <sbhat10@asu.edu>
 * @version 1.0.0
 */
public class Result {

	public boolean isNested = false;
	public boolean hasPassed = true;
	public String startTime;
	public String endTime;
	public String screenShotPath; // present only if hasPassed is false

	public boolean isNested() {
		return isNested;
	}

	public void setNested(boolean isNested) {
		this.isNested = isNested;
	}
	
	public boolean isHasPassed() {
		return hasPassed;
	}

	public void setHasPassed(boolean hasPassed) {
		this.hasPassed = hasPassed;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getScreenShotPath() {
		return screenShotPath;
	}

	public void setScreenShotPath(String screenShotPath) {
		this.screenShotPath = screenShotPath;
	}

	@Override
	public String toString() {
		return "Result [ hasPassed:" + hasPassed + ", startTime" + startTime + ", endTime" + endTime
				+ screenShotPath == null ? "" : (", screenShotPath:" + screenShotPath) + "]";
	}
}
