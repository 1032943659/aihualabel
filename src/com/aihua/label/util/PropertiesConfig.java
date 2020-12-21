package com.aihua.label.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * 配置信息操作工具类
 * @author Administrator
 *
 */
public class PropertiesConfig {
	private static Properties db_p = null;
	private static Properties config = null;
	private static String dbfilePath = "src/Config/mysql.properties";
	private static String filePath = "src/Config/config.properties";
	
    static{
    	db_p=new Properties();
    	config = new Properties();
        try {
        	db_p.load(new FileInputStream(dbfilePath));
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, e);
        }
        
        try {
        	config.load(new FileInputStream(filePath));
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * 根据key值获取value
     * @param key
     * @return
     */
    public static String getDBValues(String key){
        String s = db_p.getProperty(key);
        return s;
    }
    
    public static String getFontValues(String key){
        String s = config.getProperty(key);
        return s;
    }
    
    /**
     * 操作key+value
     * @param key
     * @param value
     * @return
     */
    public static Boolean optionProperty(String key, String value,String filepath ,Properties p) {
		try (FileOutputStream fos = new FileOutputStream(filepath)) {
			p.setProperty(key, value);
			p.store(fos, "Copyright (c) Boxcode Studio");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
		return true;
	}

    /**
     * 更新配置文件,key,value,type1字体文件,type2数据库配置
     * @param key
     * @param value
     * @param type
     * @return
     */
	public static Boolean updateProperty(String key,String value,int type) {
		boolean result = false;
		switch(type) {
			case 1:
				if (config.getProperty(key) == null) {
					JOptionPane.showMessageDialog(null, "获取'"+filePath+"'key'"+key+"'失败!");
				}
				result = optionProperty(key,value,filePath,config);
				break;
			case 2:
				if (db_p.getProperty(key) == null) {
					JOptionPane.showMessageDialog(null, "获取'"+dbfilePath+"'key'"+key+"'失败!");
				}
				result = optionProperty(key,value,dbfilePath,db_p);
				break;
			default:
				JOptionPane.showMessageDialog(null, "更新失败!");
				break;
		}
		return result;
	}
}
