# WebUIAutomation

- This project help you run Integration testing on any web url.
- The test cases should be written in "Automation_Test_Script.xls"
- After running the project report will be generated and displayed. (Report of the test suite will be in /report folder.)


  - [Installation](#installation)
  - [Output example](#output-example)

## Installation

  1) Import the project in Eclipse as 'Existing maven project'.
  2) Right click on the imported project in 'Project Explorer'.
  3) Now click on 'Run as' -> 'Maven Clean'.
  4) Once again 'Run as' -> 'Maven Install'.
  5) Once the project is built successfully
  6) Right click on 'JenkinAdapter.java' and then click on 'Run as' -> 'Java Application'. 
	
	## or
	
  1) Go to WebUIAutomation/buildscript
  		- > cd buildscript
  2) Edit 'build.properties' file.
		a) Change MAVEN_PATH="To_Your_Maven_Directory"
		b) Change PROJECTROOT="To_Your_Project_Path(WebUIAutomation)"
  3) Run 'BuildScript.bat' file.
		- > BuildScript.Bat
  4) Run 'JenkinAdapter' using command prompt.(The first argument is the URL that you want to test and second argument is the XLS file(Located in 'WebUIAutomation' foleder) that you want include)
		- > java -cp "target\WebUIAutomation-1.0.0.jar;target\classes\*;target\dependency\*" com.automation.JenkinAdapter "https://www.amazon.com" "Automation_Test_Script.xls"

## Output example
### Screen Records
<img src="/extras/WebUIAutomation.gif" width="600" height="320" />
