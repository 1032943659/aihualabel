package com.label.util;

import java.sql.*;

import javax.swing.JOptionPane;

/**
 * ���ݿ����ӹ�����
 * @author ZY
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
            Class.forName(Config.getValues("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "���ݿ���������ʧ�ܣ�");
            System.exit(0);
        }
    }

    /**
     * ��ȡ���ݿ�����
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Config.getValues("url"));
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
     * @return
     */
    public int executeUpdate(String sql,String[] param){
        int num = 0;
        try {
            //��ȡPrepareStatement����
            pst = getConnection().prepareStatement(sql);
            if (param!=null) {
                for(int i = 0; i<param.length;i++){
                    pst.setString(i+1,param[i]);
                }
            }
            num = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * ��ѯ����
     * @param sql ���
     * @param param ����
     * @return
     */
    public ResultSet executeQuery(String sql,String[] param){
        try {
            //��ȡPrepareStatement����
            pst = getConnection().prepareStatement(sql);
            if (param!=null) {
                for(int i = 0;i<param.length;i++){
                    pst.setString(i+1,param[i]);
                }
            }
            rs = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }
}
