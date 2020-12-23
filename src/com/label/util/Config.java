package com.label.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

/**
 * ��ȡ���ݿ����Ӳ���
 * @author ZY
 *
 */
public class Config {
	private static Properties p = null;

    static{
        p=new Properties();
        try {
            p.load(new FileInputStream("src/Config/mysql.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValues(String key){

        String s = p.getProperty(key);
        return s;
    }
    
    public static Boolean optionProperty(String key, String value) {
		try (FileOutputStream fos = new FileOutputStream("src/Config/mysql.properties")) {
			p.setProperty(key, value);
			p.store(fos, "Copyright (c) Boxcode Studio");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
		return true;
	}

	public static Boolean updateProperty(String key, String value) {
		if (p.getProperty(key) == null) {
			JOptionPane.showMessageDialog(null, "��ȡkeyʧ��!");
		}
		return optionProperty(key, value);
	}

	public static Boolean addProperty(String key, String value) {
		if (p.getProperty(key) != null) {
			JOptionPane.showMessageDialog(null, "keyֵ�Ѵ���!");
			return false;
		}
		return optionProperty(key, value);
	}
}
