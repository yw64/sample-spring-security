sample-spring-security
======================

本文件夹包含三个工程，用于说明Spring Security技术，特别地我们演示了Spring Security与JaSig CAS的集成。

逻辑部署
----------

* www.cas.com 充当CAS认证服务器。
* www.apple.com 充当一个WEB应用。
* www.pear.com 充当一个后台服务。

<pre>
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
             +--------+
             | 浏览器 |
             +----------+
</pre>

密钥库
------

密钥库文件已放入各自的工程中，并在pom.xml里进行了配置。您可跳过本节说明直接进入测试。

下面说说密钥库文件里的内容。

密钥库分两类，一类称为keyStore，另一类称为trustStore（即受信任的证书库）。keyStore里不仅有证书还有私有密钥，而trustStore里只有证书。

每台服务器都分别有这两个密钥库文件，其中，trust.jks为trustStore类型。

各服务器的证书都需放到cas服务器的trust.jks里，apple和pear服务器里需要放入cas的证书。即：
* cas服务器的trust.jks里有三个证书，分别是自己的证书、apple的证书和pear的证书。
* apple的trust.jks里只有cas的证书，自己的证书无需放入自己的trust.jks里。
* pear同apple一样，即pear的trust.jks里只有cas的证书，自己的证书无需放入自己的trust.jks里。

测试说明
--------

我们既可以把上面三个war分别部署到三台物理机器上，也可部署到同一台机器上。下面按一台机情况加以说明。

<dl>
<dt>设置域名</dt>
<dd>在.../etc/hosts文件中把上面三个域名都作为127.0.0.1的别名，即：</dd>
<pre>
127.0.0.1  localhost  www.cas.com  www.apple.com  www.pear.com
</pre>

<dt>启动apple应用</dt>
<dd>打开新的DOS命令窗口，进入apple工程的pom.xml所在目录，然后执行：<code>mvn tomcat7:run</code>。</dd>

<dt>启动pear应用</dt>
<dd>打开新的DOS命令窗口，进入pear工程的pom.xml所在目录，然后执行：<code>mvn tomcat7:run</code>。</dd>

<dt>启动cas应用</dt>
<dd>打开新的DOS命令窗口，进入cas工程的pom.xml所在目录，然后执行：<code>mvn tomcat7:run-war</code>。</dd>


<dt>测试</dt>
<dd>apple、pear和cas三个应用启动后请打开浏览器并输入：<code>http://www.apple.com:8080/sss-apple/welcome</code>。
<br/>如果一切正常，浏览器将转向到cas登录界面，请在cas登录界面里输入用户名/密码（wisetop/wisetop）。</dd>
</dl>

