<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
   <h1>${username}，您好！欢迎访问苹果网。</h1>
   <br>
   <p>这里不仅卖苹果，还卖雪梨。</p>

   <p>还剩多少个雪梨？<a href="pear-store?which=new">用新的PT进行查询</a> <a href="pear-store?which=old">用上次旧的PT进行查询</a>
	<br><p>想吃雪梨？<a href="http://www.pear.com:18080/sss-pear/greeting">进去看看吧。</a>

	<ul>
		<li>雪梨网的配置：
			<ul>
				<li>配置文件为spring-security.xml。</li>
				<li>配置为服务代理，即通过回调地址接收pgt。</li>
				<li>配置了票据缓存
					<ul>
						<li>票据从创建时间算起（即从第一次使用到现在）的有效时间为120秒。</li>
						<li>票据从上次访问时间算起（即两次调用的间隔时间）的有效时间为60秒。</li>
					</ul>
				</li>
			</ul>
		</li>

		<li>cas服务器关于PT使用策略的配置为：
			<ul>
				<li>每个票据最多允许验证3次。注意，虽然允许多次验证，但如果发送方作为服务代理，则cas将试图为这个票据创建pgt。
				由于cas只允许为一个票据生成一个pgt，因此即使第二次能够验证通过票据，但为该票据创建pgt时将失败。
				cas服务器报TicketGrantingTicket already generated for this ServiceTicket.  Cannot grant more than one TGT for ServiceTicket异常。</li>
			</ul>
		</li>
	</ul>

	<p>因此，
	<ul>
		<li>如果每次都用新的PT去调用雪梨网，则一切正常。</li>
		<li>如果用旧的PT去调用雪梨网、并且两次调用间隔时间超过60秒，则由于该PT在雪梨网中失效，导致雪梨网认为是新的PT，并进而去cas服务器验证。
		cas服务器能够验证通过该PT，但由于雪梨网配置为服务代理（即接收pgt），而cas已为该PT生成过了pgt、并且cas只允许为一个票据生成一个pgt，最终调用失败。</li>
		<li>如果用旧的PT去调用雪梨网，不论是否调用、还是空闲，120秒后由于该PT在雪梨网中失效，从而导致调用失败。理由同上。</li>
		<li>由于雪梨网做了票据缓存，因此只会第一次去cas服务器验证（或者失效后试图再次去验证）。如果雪梨网重启，用旧PT再去调用将导致雪梨网再次去cas验证，
		但cas已不允许为该PT创建新的pgt了，导致失败。</li>
   </ul>
</body>
</html>
