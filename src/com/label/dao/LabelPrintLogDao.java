package com.label.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.label.model.LabelPrintLog;
import com.label.util.DBHelper;
import com.label.util.StringUtil;

/**
 * 打印记录DAO
 * @author ZY
 *
 */
public class LabelPrintLogDao {
	
	/**
	 * 新增打印记录
	 */
	public int insertPrintLog(LabelPrintLog labelPrintLog){
		DBHelper dbHelper = new DBHelper();
		String sql="insert into tVendorLabelPrint"
				+ "(n_n_Id,PO,VendorCode,Vendor,Item,Lot_No,PN,AISHINo,Qty1,Qty2,Unit1,Unit2,production_date,net_weight,gross_weight"
				+ ",BoxType,SerialNumber,remarks,MaterialName,Material_Property) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String param[] = {labelPrintLog.getN_n_Id()
						,labelPrintLog.getPO()
						,labelPrintLog.getVendorCode()
						,labelPrintLog.getVendor()
						,labelPrintLog.getItem()
						,labelPrintLog.getLot_No()
						,labelPrintLog.getPN()
						,labelPrintLog.getAISHINo()
						,labelPrintLog.getQty1()+""
						,labelPrintLog.getQty2()+""
						,labelPrintLog.getUnit1()
						,labelPrintLog.getUnit2()
						,labelPrintLog.getProduction_Date()
						,labelPrintLog.getNet_Weight()+""
						,labelPrintLog.getGross_Weight()+""
						,labelPrintLog.getBoxType()+""
						,labelPrintLog.getSerialNumber()
						,labelPrintLog.getRemarks()
						,labelPrintLog.getMaterialName()
						,labelPrintLog.getMaterial_Property()
						};
		int i = dbHelper.executeUpdate(sql, param);
		
		DBHelper.CloseAll(null,  null,DBHelper.getConnection());
		return i;
	}
	
	/**
	 * 增加打印清单
	 * @param labelPrintLog
	 * @return
	 */
	public int insertList(LabelPrintLog labelPrintLog){
		DBHelper dbHelper = new DBHelper();
		String sql="insert into tVendorLabelPrint_list"
				+ "(PO,VendorCode,Vendor,Item,Lot_No,PN,AISHINo,Qty1,Qty2,Unit1,Unit2,production_date,net_weight,gross_weight"
				+ ",BoxType,SerialNumber,remarks,MaterialName,Material_Property) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String param[] = {labelPrintLog.getPO()
						,labelPrintLog.getVendorCode()
						,labelPrintLog.getVendor()
						,labelPrintLog.getItem()
						,labelPrintLog.getLot_No()
						,labelPrintLog.getPN()
						,labelPrintLog.getAISHINo()
						,labelPrintLog.getQty1()+""
						,labelPrintLog.getQty2()+""
						,labelPrintLog.getUnit1()
						,labelPrintLog.getUnit2()
						,labelPrintLog.getProduction_Date()
						,labelPrintLog.getNet_Weight()+""
						,labelPrintLog.getGross_Weight()+""
						,labelPrintLog.getBoxType()+""
						,labelPrintLog.getSerialNumber()
						,labelPrintLog.getRemarks()
						,labelPrintLog.getMaterialName()
						,labelPrintLog.getMaterial_Property()
						};
		int i = dbHelper.executeUpdate(sql, param);
		DBHelper.CloseAll(null,  null,DBHelper.getConnection());
		return i;
	}
			
	/**
	 * 查询清单
	 * @param labelPrintLog
	 * @return
	 * @throws SQLException
	 */
	public ResultSet listSel(LabelPrintLog labelPrintLog) throws SQLException{
		StringBuffer sql = new StringBuffer("select * from tVendorLabelPrint_list where 1 = 1 ");
		PreparedStatement pstmt = DBHelper.getConnection().prepareStatement(sql.toString());
		return pstmt.executeQuery();
	}
	
	/**
	 * 根据关联箱号查询当前记录的箱号
	 * @param labelPrintLog
	 * @return
	 * @throws SQLException
	 */
	public int SelBoxNidByNnId(LabelPrintLog labelPrintLog) throws SQLException{
		StringBuffer sql = new StringBuffer("select * from tVendorLabelPrint where 1 =  1");
		if(StringUtil.isNotEmpty(labelPrintLog.getN_n_Id())){
			sql.append(" and n_n_Id like  '%" +labelPrintLog.getN_n_Id()+"%'");
			PreparedStatement pstmt = DBHelper.getConnection().prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			int n_id = 0;
			while(rs.next()){
				n_id = rs.getInt("n_Id");
			}
			return n_id;
		}
		else{
			System.out.println("ceshi");
			return 0;
		}
	}
	
	/**
	 * 更新内箱关联id
	 * @param insideBox_id
	 * @param outsideBox_id
	 * @return
	 */
	public int UpdateInsideBoxNnId(int insideBox_id ,int outsideBox_id){
		DBHelper dbHelper = new DBHelper();
		String sql = "update tVendorLabelPrint set n_n_Id = ? where n_Id = ?";
		String param[] ={outsideBox_id+"",insideBox_id+""};
		int i = dbHelper.executeUpdate(sql, param);
		return i;
	}
	
	/**
	 * 查询记录
	 * @param labelPrintLog
	 * @return
	 * @throws SQLException 
	 */
	public ResultSet list(LabelPrintLog labelPrintLog) throws SQLException{
		StringBuffer sql = new StringBuffer("select * from tVendorLabelPrint where 1 = 1 ");
		
		if(StringUtil.isNotEmpty(labelPrintLog.getPO())){
			sql.append(" and PO = "  +"'"+labelPrintLog.getPO() +"'");
		}
		if(StringUtil.isNotEmpty(labelPrintLog.getVendorCode())){
			sql.append(" and VendorCode = " +"'" +labelPrintLog.getVendorCode() +"'");
		}
		if(StringUtil.isNotEmpty(labelPrintLog.getLot_No())){
			sql.append(" and Lot_No = " +"'" +labelPrintLog.getLot_No() +"'");
		}
		if(StringUtil.isNotEmpty(labelPrintLog.getPN())){
			sql.append(" and PN = " +"'" +labelPrintLog.getPN() +"'");
		}
		if(StringUtil.isNotEmpty(labelPrintLog.getAISHINo())){
			sql.append(" and AISHINo = " +"'" +labelPrintLog.getAISHINo() +"'");
		}
		if(StringUtil.isNotEmpty(labelPrintLog.getProduction_Date())){
			sql.append(" and production_Date = " +"'" +labelPrintLog.getProduction_Date() +"'");
		}
		if(labelPrintLog.getBoxType()!=-1){
			sql.append(" and BoxType = "+"'"+labelPrintLog.getBoxType() +"'");
		}
		sql.append("order by n_Id");
		PreparedStatement pstmt = DBHelper.getConnection().prepareStatement(sql.toString());
		return pstmt.executeQuery();
	}
	
	/**
	 * 删除打印记录
	 * @param id
	 * @return
	 */
	public int deletePrintLog(int id){
		DBHelper dbHelper = new DBHelper();
		String sql = "delete from tVendorLabelPrint where n_Id = ?";
		String param[] = {id+""};
		int i = dbHelper.executeUpdate(sql, param);
		return i;
	}
	
	/**
	 * 清空打印记录
	 * @return
	 */
	public int truncatePrintLog(){
		DBHelper dbHelper = new DBHelper();
		String sql="truncate table tVendorLabelPrint";
		int i = dbHelper.executeUpdate(sql, null);
		DBHelper.CloseAll(null,  null,DBHelper.getConnection());
		return i;
	}
	
	/**
	 * 清空打印清单
	 * @return
	 */
	public int truncateList(){
		DBHelper dbHelper = new DBHelper();
		String sql="truncate table tVendorLabelPrint_list";
		int i = dbHelper.executeUpdate(sql, null);
		DBHelper.CloseAll(null,  null,DBHelper.getConnection());
		return i;
	}
	
	/**
	 * 删除打印清单指定行
	 * @param id
	 * @return
	 */
	public int deleteListRow(int id){
		DBHelper dbHelper = new DBHelper();
		String sql="delete from tVendorLabelPrint_list where n_Id = ?";
		String param[] = {id+""};
		int i = dbHelper.executeUpdate(sql, param);
		DBHelper.CloseAll(null,  null,DBHelper.getConnection());
		return i;
	}

	/**
	 * 将材料实测值更新到打印清单
	 * @param insidelabelPrintList
	 * @return
	 */
	public int updateToList(LabelPrintLog insidelabelPrintList,int id) {
		DBHelper dbHelper = new DBHelper();
		String sql="update tVendorLabelPrint_list set MaterialName = ? ,Material_Property = ? where n_Id = ?";
		String param[] = {insidelabelPrintList.getMaterialName()
						,insidelabelPrintList.getMaterial_Property()
						,id+""};
		int i = dbHelper.executeUpdate(sql, param);
		DBHelper.CloseAll(null,  null,DBHelper.getConnection());
		return i;
	}
}
