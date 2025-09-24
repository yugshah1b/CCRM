@echo off
setlocal enabledelayedexpansion
set SRC=src
set OUT=out
if not exist %OUT% mkdir %OUT%
javac -encoding UTF-8 -d %OUT% -sourcepath %SRC% %SRC%\edu\ccrm\cli\Main.java
if %ERRORLEVEL% NEQ 0 (
  echo Compilation failed.
  exit /b 1
)
echo Compiled to %OUT%


