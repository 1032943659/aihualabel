package com.aihua.label.util;

/**
 * 对象工具类
 * @author Administrator
 *
 */
public class ObjectUtil {
	/**
	 * 获取对象的数据类型
	 * @param object
	 * @return
	 */
	public static String getType(Object object) {
		String typeName = object.getClass().getName();
		int length = typeName.lastIndexOf(".");
		String type = typeName.substring(length + 1);
		return type;
	}
}
