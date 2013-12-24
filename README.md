sample-spring-security
======================

本文件夹包含三个工程，用于说明Spring Security技术，特别地我们演示了Spring Security与JaSig CAS的集成。

1、逻辑部署
* www.cas.com充当CAS认证服务器。
* www.apple.com充当一个WEB应用。
* www.pear.com充当一个后台服务。

                                +----------------------------------+
                    +---------- | https://www.cas.com:9443/sss-cas | ---------+
                    |           | cas.jks、trust.jks               |          |
                    |           +----------------------------------+          |
                    | 8443                                                    | 18443
                    |                                                         |
                    V                                                         V
+--------------------------------------+                   +-------------------------------------+
| http://www.apple.com:8080/sss-apple  |      18080        | http://www.pear.com:18080/sss-pear  |
| https://www.apple.com:8443/sss-apple |  ------------->   | https://www.pear.com:18443/sss-pear |
| apple.jks、trust.jks                 |                   | pear.jks、trust.jks                 |
+--------------------------------------+                   +-------------------------------------+
                 ^
                 | 8080
                 |
             +----------+
             | IE浏览器 |
             +----------+

二、密钥库
密钥库分两类，一类称为keyStore，另一类称为trustStore（即受信任的证书库）。keyStore里不仅有证书还有私有密钥，而trustStore里只有证书。

每台服务器都分别有这两个密钥库文件，其中，trust.jks为trustStore类型。

各服务器的证书都需放到cas服务器的trust.jks里，apple和pear服务器里需要放入cas的证书。即：
* cas服务器的trust.jks里有三个证书，分别是自己的证书、apple的证书和pear的证书。
* apple的trust.jks里只有cas的证书，自己的证书无需放入自己的trust.jks里。
* pear同apple一样，即pear的trust.jks里只有cas的证书，自己的证书无需放入自己的trust.jks里。

2、测试说明
我们既可以把上面三个war分别部署到三台物理机器上，也可部署到同一台机器上。下面按一台机情况加以说明。

* 域名
在.../etc/hosts文件中把上面三个域名都作为127.0.0.1的别名，即：

127.0.0.1  localhost  www.cas.com  www.apple.com  www.pear.com

* 启动
请打开三个DOS窗口分别进入各自的目录（pom.xml所在目录），然后分别启动。

a) 启动apple和pear都请执行：mvn tomcat7:run

b) 启动cas请执行：mvn tomcat7:run-war
   （注：cas工程使用了Maven的War Overlay技术，tomcat7:run目前尚不完全支持该技术。）

* 测试
启动完成后打开IE浏览器，并输入：
http://www.apple.com:8080/sss-apple/welcome

然后在cas登录界面里输入wisetop/wisetop。
