sample-spring-security
======================

���ļ��а����������̣�����˵��Spring Security�������ر��������ʾ��Spring Security��JaSig CAS�ļ��ɡ�

1���߼�����
* www.cas.com�䵱CAS��֤��������
* www.apple.com�䵱һ��WEBӦ�á�
* www.pear.com�䵱һ����̨����

                                +----------------------------------+
                    +---------- | https://www.cas.com:9443/sss-cas | ---------+
                    |           | cas.jks��trust.jks               |          |
                    |           +----------------------------------+          |
                    | 8443                                                    | 18443
                    |                                                         |
                    V                                                         V
+--------------------------------------+                   +-------------------------------------+
| http://www.apple.com:8080/sss-apple  |      18080        | http://www.pear.com:18080/sss-pear  |
| https://www.apple.com:8443/sss-apple |  ------------->   | https://www.pear.com:18443/sss-pear |
| apple.jks��trust.jks                 |                   | pear.jks��trust.jks                 |
+--------------------------------------+                   +-------------------------------------+
                 ^
                 | 8080
                 |
             +----------+
             | IE����� |
             +----------+

������Կ��
��Կ������࣬һ���ΪkeyStore����һ���ΪtrustStore���������ε�֤��⣩��keyStore�ﲻ����֤�黹��˽����Կ����trustStore��ֻ��֤�顣

ÿ̨���������ֱ�����������Կ���ļ������У�trust.jksΪtrustStore���͡�

����������֤�鶼��ŵ�cas��������trust.jks�apple��pear����������Ҫ����cas��֤�顣����
* cas��������trust.jks��������֤�飬�ֱ����Լ���֤�顢apple��֤���pear��֤�顣
* apple��trust.jks��ֻ��cas��֤�飬�Լ���֤����������Լ���trust.jks�
* pearͬappleһ������pear��trust.jks��ֻ��cas��֤�飬�Լ���֤����������Լ���trust.jks�

2������˵��
���Ǽȿ��԰���������war�ֱ�����̨��������ϣ�Ҳ�ɲ���ͬһ̨�����ϡ����水һ̨���������˵����

* ����
��.../etc/hosts�ļ��а�����������������Ϊ127.0.0.1�ı���������

127.0.0.1  localhost  www.cas.com  www.apple.com  www.pear.com

* ����
�������DOS���ڷֱ������Ե�Ŀ¼��pom.xml����Ŀ¼����Ȼ��ֱ�������

a) ����apple��pear����ִ�У�mvn tomcat7:run

b) ����cas��ִ�У�mvn tomcat7:run-war
   ��ע��cas����ʹ����Maven��War Overlay������tomcat7:runĿǰ�в���ȫ֧�ָü�������

* ����
������ɺ��IE������������룺
http://www.apple.com:8080/sss-apple/welcome

Ȼ����cas��¼����������wisetop/wisetop��
