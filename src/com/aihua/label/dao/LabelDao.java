package com.aihua.label.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aihua.label.model.AiHuaLabel;
import com.aihua.label.util.DBHelper;
import com.aihua.label.util.LogUtil;
import com.aihua.label.util.StringUtil;

public class LabelDao {
	private DBHelper dbHelper = new DBHelper();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	/**
	 * 增加打印记录
	 * @param labelPrintLog
	 * @return
	 */
	public int insertPrintLog(AiHuaLabel labelPrintLog) {
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
						,labelPrintLog.getProductionDate()
						,labelPrintLog.getNetWeight()+""
						,labelPrintLog.getGrossWeight()+""
						,labelPrintLog.getBoxType()+""
						,labelPrintLog.getSerialNumber()
						,labelPrintLog.getRemarks()
						,labelPrintLog.getMaterialName()
						,labelPrintLog.getMaterial_Property()
						};
		return dbHelper.executeUpdate(sql, param);
	}

	/**
	 * 清空打印清单
	 */
	public void truncateList() {
		String sql="truncate table tVendorLabelPrint_list";
		dbHelper.executeUpdate(sql, null);
	}

	/**
	 * 查询打印清单
	 * @param labelPrintLog
	 * @return
	 */
	public ResultSet listSel(AiHuaLabel labelPrintLog) {
		StringBuffer sql = new StringBuffer("select * from tVendorLabelPrint_list where 1 = 1 ");
		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			LogUtil.exception(e);
		}finally {
			DBHelper.CloseAll(rs,pstmt,DBHelper.getConnection());
		}
		return rs;
	}

	/**
	 * 删除打印清单指定行
	 * @param id
	 * @return
	 */
	public int deleteListRow(int id) {
		String sql="delete from tVendorLabelPrint_list where n_Id = ?";
		String param[] = {id+""};
		return dbHelper.executeUpdate(sql, param);
	}

	/**
	 * 将材料实测值更新到打印清单
	 * @param insidelabelPrintList
	 * @param id 打印清单行id
	 * @return
	 */
	public int updateToList(AiHuaLabel insidelabelPrintList, int id) {
		String sql="update tVendorLabelPrint_list set MaterialName = ? ,Material_Property = ? where n_Id = ?";
		String param[] = {insidelabelPrintList.getMaterialName()
						,insidelabelPrintList.getMaterial_Property()
						,id+""};
		return dbHelper.executeUpdate(sql, param);
	}

	/**
	 * 清空打印清单
	 */
	public void truncatePrintLog() {
		String sql="truncate table tVendorLabelPrint";
		dbHelper.executeUpdate(sql, null);
	}

	/**
	 * 查询打印记录
	 * @param labelPrintLog
	 * @return
	 */
	public ResultSet list(AiHuaLabel labelPrintLog) {
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
		if(StringUtil.isNotEmpty(labelPrintLog.getProductionDate())){
			sql.append(" and production_Date = " +"'" +labelPrintLog.getProductionDate() +"'");
		}
		if(labelPrintLog.getBoxType()!=-1){
			sql.append(" and BoxType = "+"'"+labelPrintLog.getBoxType() +"'");
		}
		sql.append("order by n_Id");

		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			LogUtil.exception(e);
		}finally {
			DBHelper.CloseAll(null, pstmt, DBHelper.getConnection());
		}
		return rs;
	}

	/**
	 * 增加打印清单记录
	 * @param labelPrintLog
	 * @return
	 */
	public int insertList(AiHuaLabel labelPrintLog) {
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
						,labelPrintLog.getProductionDate()
						,labelPrintLog.getNetWeight()+""
						,labelPrintLog.getGrossWeight()+""
						,labelPrintLog.getBoxType()+""
						,labelPrintLog.getSerialNumber()
						,labelPrintLog.getRemarks()
						,labelPrintLog.getMaterialName()
						,labelPrintLog.getMaterial_Property()
						};
		return dbHelper.executeUpdate(sql, param);
	}

	/**
	 * 删除打印记录
	 * @param id
	 */
	public void deletePrintLog(int id) {
		String sql = "delete from tVendorLabelPrint where n_Id = ?";
		String param[] = {id+""};
		dbHelper.executeUpdate(sql, param);
	}

	/**
	 * 根据关联箱号查询当前箱号
	 * @param labelPrintLog
	 * @return
	 */
	public int SelBoxNidByNnId(AiHuaLabel labelPrintLog) {
		int n_id = 0;
		StringBuffer sql = new StringBuffer("select * from tVendorLabelPrint where 1 =  1");
		if(StringUtil.isNotEmpty(labelPrintLog.getN_n_Id())){
			sql.append(" and n_n_Id like  '%" +labelPrintLog.getN_n_Id()+"%'");
			try {
				pstmt = DBHelper.getConnection().prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				while(rs.next()){
					n_id = rs.getInt("n_Id");
				}
			} catch (SQLException e) {
				LogUtil.exception(e);
			}finally{
				DBHelper.CloseAll(rs,pstmt,DBHelper.getConnection());
			}
			return n_id;
		}
		else{
			return n_id;
		}
	}

	/**
	 * 更新内外箱id
	 * @param insideBox_id
	 * @param outsideBox_id
	 */
	public void UpdateInsideBoxNnId(int insideBox_id, int outsideBox_id) {
		String sql = "update tVendorLabelPrint set n_n_Id = ? where n_Id = ?";
		String param[] ={outsideBox_id+"",insideBox_id+""};
		dbHelper.executeUpdate(sql, param);
	}
	
}
