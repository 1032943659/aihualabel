package com.aihua.label.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 * 数据库连接
 * @author Administrator
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
            Class.forName(PropertiesConfig.getDBValues("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据库驱动加载失败！");
            System.exit(0);
        }
    }

    /**
     * 获取数据库连接
     * @return 连接对象
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(PropertiesConfig.getDBValues("url"));
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
     * @return 受影响行数
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
     * 查询操作
     * @param sql 语句
     * @param param 参数
     * @return 查询结果集
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
        	LogUtil.exception(e);
        }
    }
}
