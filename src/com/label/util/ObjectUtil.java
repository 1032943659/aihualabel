package com.label.util;

/**
 * object工具类
 * @author 木易
 *
 */
public class ObjectUtil {
	/**
	   * 获取数据类型
	   * @param object
	   * @return
	   */
	  public static String getType(Object object){
			String typeName=object.getClass().getName();
			int length= typeName.lastIndexOf(".");
			String type =typeName.substring(length+1);
			return type;
		}
}
