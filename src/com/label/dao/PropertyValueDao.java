package com.label.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.label.model.PropertyValue;
import com.label.util.DBHelper;

/**
 * 富贤材料属性DAO
 * @author 木易
 *
 */
public class PropertyValueDao {
	/**
	 * 添加标准值到数据库表
	 * @param fxstandardValue
	 * @return
	 */
	public int insertToFxstandardValue(PropertyValue fxstandardValue){
		DBHelper dbHelper = new DBHelper();
		String sql="insert into tFx_standardValue"
				+ "(MaterialName,Property1,Unit1,Property2,Unit2,Property3,Unit3,Property4,Unit4,Property5,Unit5,"
				+ "Property6,Unit6,Property7,Unit7,Property8,Unit8,Property9,Unit9,Property10,Unit10,Property11,Unit11)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String param[] ={fxstandardValue.getMaterialName(),
				fxstandardValue.getProperty1(),
				fxstandardValue.getUnit1(),
				fxstandardValue.getProperty2(),
				fxstandardValue.getUnit2(),
				fxstandardValue.getProperty3(),
				fxstandardValue.getUnit3(),
				fxstandardValue.getProperty4(),
				fxstandardValue.getUnit4(),
				fxstandardValue.getProperty5(),
				fxstandardValue.getUnit5(),
				fxstandardValue.getProperty6(),
				fxstandardValue.getUnit6(),
				fxstandardValue.getProperty7(),
				fxstandardValue.getUnit7(),
				fxstandardValue.getProperty8(),
				fxstandardValue.getUnit8(),
				fxstandardValue.getProperty9(),
				fxstandardValue.getUnit9(),
				fxstandardValue.getProperty10(),
				fxstandardValue.getUnit10(),
				fxstandardValue.getProperty11(),
				fxstandardValue.getUnit11(),
				};
		int i = dbHelper.executeUpdate(sql, param);
		DBHelper.CloseAll(null,  null,DBHelper.getConnection());
		return i;
	}
	
	/**
	 * 查询所有的标准值记录
	 * @return
	 * @throws SQLException
	 */
	public ResultSet listFxstandardValue() throws SQLException{
		StringBuffer sql = new StringBuffer("select * from tFx_standardValue ");
		PreparedStatement pstmt = DBHelper.getConnection().prepareStatement(sql.toString());
		return pstmt.executeQuery();
	}
	
	/**
	 * 删除标准值记录指定行
	 * @param id
	 * @return
	 */
	public int deleteFxstandardValueRow(int id){
		DBHelper dbHelper = new DBHelper();
		String sql="delete from tFx_standardValue where n_Id = ?";
		String param[] = {id+""};
		int i = dbHelper.executeUpdate(sql, param);
		DBHelper.CloseAll(null,  null,DBHelper.getConnection());
		return i;
	}
	
	/**
	 * 清空标准值记录
	 * @return
	 */
	public int truncateFxstandardValue(){
		DBHelper dbHelper = new DBHelper();
		String sql="truncate table tFx_standardValue";
		int i = dbHelper.executeUpdate(sql, null);
		DBHelper.CloseAll(null,  null,DBHelper.getConnection());
		return i;
	}
}
