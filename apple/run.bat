@echo off
@echo *
@echo * 本批命令脚本用内嵌Tomcat来启动当前WEB应用，这个内嵌Tomcat由Maven自动下载，与本机器是否安装了Tomcat无关。
@echo * 由于是用内嵌Tomcat来启动，因此每次启动都是干净的环境。如果你仍怀疑环境是否干净，您可选择执行下面的“9. 删除target目录”。
@echo *

:l_begin
rem 设置内存大小
set MAVEN_OPTS=-Xms512m -Xmx512m -XX:MaxPermSize=256m

set mopts=

set choice=""
echo ===
echo 1. 正常方式启动
echo 2. 调试方式启动
echo 9. 删除target目录（即执行mvn clean）
echo 0. 退出
set /p choice=请选择（直接回车默认为选择1）：
if "%choice%" == """" goto l_normal
if "%choice%" == "0" goto l_end
if "%choice%" == "1" goto l_normal
if "%choice%" == "2" goto l_jpda
if "%choice%" == "9" goto l_clean
goto l_begin

:l_normal
goto l_run

:l_jpda
set mopts=-Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n
goto l_run

:l_clean
call mvn clean
goto l_begin

:l_run
set MAVEN_OPTS=%MAVEN_OPTS% %mopts%
echo MAVEN_OPTS=%MAVEN_OPTS%
mvn tomcat7:run
goto l_begin

:l_end
