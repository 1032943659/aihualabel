package com.label.util;

import java.math.BigDecimal;

/**
 * ���ִ�������
 * @author ľ��
 *
 */
public class NumberUtil {
	/**
	 * ȥ��float��������Ĭ�ϴ�����0
	 * @param f
	 * @return
	 */
	public static String removeZero(Float f){
		// ����λ��
		int scale = 2;
		// ��ʾ�������룬����ѡ��������ֵ��ʽ������ȥβ���ȵ�.
		int roundingMode = 4;
		BigDecimal bd = new BigDecimal((float) f);
		bd = bd.setScale(scale, roundingMode);
		f = bd.floatValue();
		
		String s = f + "";
		if(s.indexOf(".") > 0){

			  //������
			  s = s.replaceAll("0+?$", "");//ȥ���������õ���

			  s = s.replaceAll("[.]$", "");//��С�������ȫ������ȥ��С����

		}
		return s;
	}
}
