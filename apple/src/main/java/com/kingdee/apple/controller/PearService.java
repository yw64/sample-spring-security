package com.kingdee.apple.controller;

import javax.jws.WebService;

/**
 * 雪梨库存服务。
 *
 * @author yangwu
 *
 */
@WebService(targetNamespace="http://www.kingdeehit.com")
public interface PearService {

	/**
	 * 查询雪梨的当前库存量。
	 *
	 * @return 库存量
	 */
	int queryPearAmount();

	/**
	 * 放进一个雪梨。
	 *
	 * @return 用户名
	 */
	String inc();

	/**
	 * 拿走一个雪梨。
	 *
	 * @return 用户名
	 */
	String dec();

}
