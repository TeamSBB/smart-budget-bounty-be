@echo off
setlocal

REM Start Spring Boot backend and capture PID
echo Starting backend...
cd /d "C:\Users\ong.jiaquanjoel\Projects\smart-budget-bounty-be\smart-budget-bounty-app"
start "" java -jar target\smart-budget-bounty-app-0.0.1-SNAPSHOT.jar
timeout /t 5 >nul
for /f "tokens=2" %%a in ('tasklist ^| findstr /i "java.exe"') do set BACKEND_PID=%%a

REM Start React frontend using serve
echo Starting frontend...
cd /d "C:\Users\ong.jiaquanjoel\Projects\smart-budget-bounty\smart-budget-bounty-portal"
start "" npx serve -s build 


REM Wait a few seconds for frontend to start
timeout /t 5 >nul

REM Open browser to React app
start http://localhost:3000

REM Wait a few seconds for backend to start
timeout /t 5 >nul

REM Open browser to Backend app
start http://localhost:8082/swagger-ui/index.html

REM Wait for user to close
pause

REM Kill backend process
echo Stopping backend...
taskkill /PID %BACKEND_PID% /F

endlocal
