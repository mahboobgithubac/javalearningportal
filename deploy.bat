@echo off
set /p message=Enter your commit message: 
git add .
git commit -m "%message%"
git push origin master
pause
