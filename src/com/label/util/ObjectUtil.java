package com.label.util;

/**
 * object������
 * @author ľ��
 *
 */
public class ObjectUtil {
	/**
	   * ��ȡ��������
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
