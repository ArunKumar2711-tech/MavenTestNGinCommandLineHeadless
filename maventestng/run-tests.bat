@echo off
setlocal

:: Prompt user for parameters
set /p searchText="Enter searchText: "
set /p otherParam1="Enter otherParam1: "
set /p otherParam2="Enter otherParam2: "
:: Add more prompts as needed for additional parameters

:: Display the parameters (optional)
echo You entered:
echo searchText: %searchText%
echo otherParam1: %otherParam1%
echo otherParam2: %otherParam2%

:: Run Maven test with user-provided parameters
mvn test -DsearchText=%searchText% -DotherParam1=%otherParam1% -DotherParam2=%otherParam2%

endlocal
