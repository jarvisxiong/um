@echo off
echo ****************************************************
echo 复制到服务器 192.168.180.240
net use \\192.168.180.240\ipc$ /del
net use \\192.168.180.240\ipc$   "p2400509[]"  /user:"pl" 
xcopy D:\deploy\war\PowerDesk.war \\192.168.180.240\d$\war /y

echo 复制到服务器 192.168.180.101
net use \\192.168.180.101\ipc$ /del
net use \\192.168.180.101\ipc$   "p2400509[]"  /user:"pl" 
xcopy D:\deploy\war\PowerDesk.war \\192.168.180.101\d$\war /y
pause