package com.aihua.label.service;

import javax.swing.JTable;

import com.aihua.label.model.AiHuaLabel;

/**
 * ��ǩ�ӿ�
 * @author Administrator
 *
 */
public interface LabelService {

	/**
	 * ��ʼ���������
	 * @param label	 ����
	 * @param jtable �����
	 * @return ���ر������
	 */
	public int InitializationTable(AiHuaLabel label,JTable jtable);
}
