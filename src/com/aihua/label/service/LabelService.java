package com.aihua.label.service;

import javax.swing.JTable;

import com.aihua.label.model.AiHuaLabel;

/**
 * 标签接口
 * @author Administrator
 *
 */
public interface LabelService {

	/**
	 * 初始化表格数据
	 * @param label	 条件
	 * @param jtable 表对象
	 * @return 返回表格行数
	 */
	public int InitializationTable(AiHuaLabel label,JTable jtable);
}
