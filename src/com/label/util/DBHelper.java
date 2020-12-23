package com.label.util;

import java.sql.*;

import javax.swing.JOptionPane;

/**
 * 数据库连接工具类
 * @author ZY
 *
 */
public class DBHelper {
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	/**
	 * 加载数据库驱动
	 */
    static{
        try {
            Class.forName(Config.getValues("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据库驱动加载失败！");
            System.exit(0);
        }
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Config.getValues("url"));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据库连接失败！");
            System.exit(0);
        }
        return conn;
    }

    /**
     * 增删改操作
     * @param sql 语句
     * @param param 参数
     * @return
     */
    public int executeUpdate(String sql,String[] param){
        int num = 0;
        try {
            //获取PrepareStatement对象
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
     * 查询操作
     * @param sql 语句
     * @param param 参数
     * @return
     */
    public ResultSet executeQuery(String sql,String[] param){
        try {
            //获取PrepareStatement对象
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
     * 关闭连接
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
