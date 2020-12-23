package com.label.util;

import com.label.model.LabelPrintLog;

/**
 * 字符串工具类
 * @author ZY
 *
 */
public class StringUtil {
	/**
	 * 判断字符串为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断字符串不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if(str !=null&& !"".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	/**
	 * 将实测值中数值与单位之间的'|'替换掉，以加入二维码
	 * @param labelPrintLog
	 * @return
	 */
	public static String getMaterialProperty(LabelPrintLog labelPrintLog) {
		// TODO Auto-generated method stub
		StringBuffer MaterialProperty = new StringBuffer();
		if("正箔".equals(labelPrintLog.getMaterialName())&&!"".equals(labelPrintLog.getMaterialName())){
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[0]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[1]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[2]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[3]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[4]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[5]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[6]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[7]);
		}else if("负箔".equals(labelPrintLog.getMaterialName())&&!"".equals(labelPrintLog.getMaterialName())){
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[0]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[1]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[2]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[3]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[4]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[5]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[6]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[7]);
		}else if("电解纸".equals(labelPrintLog.getMaterialName())&&!"".equals(labelPrintLog.getMaterialName())){
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[0]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[1]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[2]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[3]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[4]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[5]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[6]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[7]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[8]);
		}else if("正导针".equals(labelPrintLog.getMaterialName())&&!"".equals(labelPrintLog.getMaterialName())){
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[0]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[1]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[2]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[3]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[4]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[5]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[6]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[7]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[8]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[9]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[10]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[11]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[12]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[13]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[14]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[15]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[16]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[17]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[18]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[19]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[20]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[21]);
		}else if("负导针".equals(labelPrintLog.getMaterialName())&&!"".equals(labelPrintLog.getMaterialName())){
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[0]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[1]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[2]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[3]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[4]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[5]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[6]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[7]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[8]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[9]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[10]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[11]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[12]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[13]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[14]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[15]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[16]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[17]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[18]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[19]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[20]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[21]);
		}else if("胶带".equals(labelPrintLog.getMaterialName())&&!"".equals(labelPrintLog.getMaterialName())){
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[0]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[1]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[2]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[3]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[4]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[5]);
		}else if("基座".equals(labelPrintLog.getMaterialName())&&!"".equals(labelPrintLog.getMaterialName())){
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[0]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[1]);
		}else if("热封上带".equals(labelPrintLog.getMaterialName())&&!"".equals(labelPrintLog.getMaterialName())){
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[0]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[1]);
		}else if("载带".equals(labelPrintLog.getMaterialName())&&!"".equals(labelPrintLog.getMaterialName())){
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[0]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[1]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[2]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[3]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[4]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[5]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[6]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[7]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[8]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[9]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[10]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[11]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[12]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[13]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[14]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[15]);
			MaterialProperty.append("|");
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[16]);
			MaterialProperty.append(labelPrintLog.getMaterial_Property().split("\\|")[17]);
		}
		return MaterialProperty.toString();
	}
}
                                      