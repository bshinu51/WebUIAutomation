@ECHO OFF

IF NOT EXIST "build.properties" (
ECHO File build.properties not found.  
set ERROR_CODE=1
GOTO EXIT_WITH_ERROR
) ELSE (
REM Setting values from build.properties file
FOR /f "delims=" %%A in (build.properties) DO SET %%A
)

CD %PROJECTROOT%

ECHO Building WebUIAutomation Projects ...

call %MAVEN_PATH%\mvn clean dependency:copy-dependencies package
call %MAVEN_PATH%\mvn install

IF %ERRORLEVEL% NEQ 0 (set ERROR_CODE=2
GOTO EXIT_WITH_ERROR)

ECHO Exiting WebUIAutomation Project build with SUCCESS.
EXIT /B 0

:EXIT_WITH_ERROR
ECHO Exiting WebUIAutomation Project build with FAILURE.
EXIT /B 0

