@echo off

echo [INFO] ʹ��maven����pom.xml ��������jar��/lib
call mvn dependency:copy-dependencies -DoutputDirectory=lib -Dsilent=true

pause