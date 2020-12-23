package com.label.util;

import java.math.BigDecimal;

/**
 * 数字处理工具类
 * @author 木易
 *
 */
public class NumberUtil {
	/**
	 * 去除float类型整数默认带出的0
	 * @param f
	 * @return
	 */
	public static String removeZero(Float f){
		// 设置位数
		int scale = 2;
		// 表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
		int roundingMode = 4;
		BigDecimal bd = new BigDecimal((float) f);
		bd = bd.setScale(scale, roundingMode);
		f = bd.floatValue();
		
		String s = f + "";
		if(s.indexOf(".") > 0){

			  //正则表达
			  s = s.replaceAll("0+?$", "");//去掉后面无用的零

			  s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点

		}
		return s;
	}
}
