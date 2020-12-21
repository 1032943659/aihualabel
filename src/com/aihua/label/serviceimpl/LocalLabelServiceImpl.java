package com.aihua.label.serviceimpl;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.aihua.label.dao.LabelDao;
import com.aihua.label.model.AiHuaLabel;
import com.aihua.label.service.LabelService;
import com.aihua.label.util.LogUtil;
import com.aihua.label.util.StringUtil;

/**
 * 	标签业务的本地实现
 * @author Administrator
 *
 */
public class LocalLabelServiceImpl implements LabelService {

	LabelDao labelPrintLogDao = new LabelDao();
	
	/**
	 * 初始化表格
	 */
	@Override
	public int InitializationTable(AiHuaLabel label, JTable jtable) {
		DefaultTableModel dtm = (DefaultTableModel) jtable.getModel();
		dtm.setRowCount(0);
		ResultSet rs = labelPrintLogDao.listSel(label);
		int count = 0;
		try {
			while (rs.next()) {
				Vector<String> v = new Vector<String>();
				v.add(rs.getString("n_Id"));
				v.add(rs.getString("Qty1"));
				v.add(rs.getString("Unit1"));
				if (StringUtil.isEmpty(rs.getString("Qty2"))) {
					v.add("");
					v.add("");
				} else {
					v.add(rs.getString("Qty2"));
					v.add(rs.getString("Unit2"));
				}
				v.add(rs.getString("net_weight"));
				v.add(rs.getString("gross_weight"));
				v.add(rs.getString("PO"));
				v.add(rs.getString("VendorCode"));
				v.add(rs.getString("Vendor"));
				v.add(rs.getString("Item"));
				v.add(rs.getString("Lot_No"));
				v.add(rs.getString("PN"));
				v.add(rs.getString("AISHINo"));
				v.add(rs.getString("production_date"));
				v.add(rs.getString("remarks"));
				v.add(rs.getString("MaterialName"));
				v.add(rs.getString("Material_Property"));
				v.add(rs.getString("SerialNumber"));
				dtm.addRow(v);
				count++;
			}
		} catch (Exception e) {
			LogUtil.exception(e);
		}
		return count;
	}

//	public int InitializationTable(AiHuaLabel label, JTable jtable) {
//		
//	}
}
