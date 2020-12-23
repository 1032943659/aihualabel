package com.label.view;

import javax.swing.JFrame;

/**
 * 帮助菜单
 * @author 木易
 *
 */
public class HelpInterFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public HelpInterFrm() {
		setTitle("操作说明");
		//setClosable(true);
		//setIconifiable(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		//设置居中显示
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
	}
}
