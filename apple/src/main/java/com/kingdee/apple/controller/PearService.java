package com.kingdee.apple.controller;

import javax.jws.WebService;

/**
 * ѩ�������
 *
 * @author yangwu
 *
 */
@WebService(targetNamespace="http://www.kingdeehit.com")
public interface PearService {

	/**
	 * ��ѯѩ��ĵ�ǰ�������
	 *
	 * @return �����
	 */
	int queryPearAmount();

	/**
	 * �Ž�һ��ѩ�档
	 *
	 * @return �û���
	 */
	String inc();

	/**
	 * ����һ��ѩ�档
	 *
	 * @return �û���
	 */
	String dec();

}
