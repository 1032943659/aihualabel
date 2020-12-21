package com.aihua.label.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 * ���ݿ�����
 * @author Administrator
 *
 */
public class DBHelper {
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	/**
	 * �������ݿ�����
	 */
    static{
        try {
            Class.forName(PropertiesConfig.getDBValues("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "���ݿ���������ʧ�ܣ�");
            System.exit(0);
        }
    }

    /**
     * ��ȡ���ݿ�����
     * @return ���Ӷ���
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(PropertiesConfig.getDBValues("url"));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ�");
            System.exit(0);
        }
        return conn;
    }

    /**
     * ��ɾ�Ĳ���
     * @param sql ���
     * @param param ����
     * @return ��Ӱ������
     */
	public int executeUpdate(String sql, String[] param) {
		int num = 0;
		Connection conn = getConnection();
		try {
			pst = conn.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pst.setString(i + 1, param[i]);
				}
			}
			num = pst.executeUpdate();
		} catch (SQLException e) {
			LogUtil.exception(e);
		}finally {
			CloseAll(null, pst, conn);
		}
		return num;
    }

    /**
     * ��ѯ����
     * @param sql ���
     * @param param ����
     * @return ��ѯ�����
     */
    public ResultSet executeQuery(String sql,String[] param){
    	Connection conn = getConnection();
    	try {
            pst = conn.prepareStatement(sql);
            if (param!=null) {
                for(int i = 0;i<param.length;i++){
                    pst.setString(i+1,param[i]);
                }
            }
            rs = pst.executeQuery();
        } catch (SQLException e) {
        	LogUtil.exception(e);
        }finally {
        	CloseAll(rs, pst, conn);
        }
        return rs;
    }

    /**
     * �ر�����
     * @param rs
     * @param pst
     * @param conn
     */
    public static void CloseAll(ResultSet rs,PreparedStatement pst,Connection conn){
        try {
            if(rs!=null){
                rs.close();
            }
            if(pst!=null){
                pst.close();
            }
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
        	LogUtil.exception(e);
        }
    }
}
