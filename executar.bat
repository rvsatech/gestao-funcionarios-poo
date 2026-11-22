@echo off
cd /d "%~dp0"
if not exist out mkdir out
if not exist dados mkdir dados
javac -encoding UTF-8 -d out -cp lib\derby-10.14.2.0.jar src\empresa\Main.java src\empresa\modelo\*.java src\empresa\dao\*.java
if errorlevel 1 pause & exit /b 1
java -cp "out;lib\derby-10.14.2.0.jar" empresa.Main
pause
