@echo off
@echo *
@echo * ��������ű�����ǶTomcat��������ǰWEBӦ�ã������ǶTomcat��Maven�Զ����أ��뱾�����Ƿ�װ��Tomcat�޹ء�
@echo * ����������ǶTomcat�����������ÿ���������Ǹɾ��Ļ�����������Ի��ɻ����Ƿ�ɾ�������ѡ��ִ������ġ�9. ɾ��targetĿ¼����
@echo *

:l_begin
rem �����ڴ��С
set MAVEN_OPTS=-Xms512m -Xmx512m -XX:MaxPermSize=256m

set mopts=

set choice=""
echo ===
echo 1. ������ʽ����
echo 2. ���Է�ʽ����
echo 9. ɾ��targetĿ¼����ִ��mvn clean��
echo 0. �˳�
set /p choice=��ѡ��ֱ�ӻس�Ĭ��Ϊѡ��1����
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
