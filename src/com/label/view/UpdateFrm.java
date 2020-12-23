package com.label.view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import com.label.util.FileSave;
import com.label.util.UpgraderUtil;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.io.IOException;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextArea;

/**
 * 版本升级提示框
 * 
 * @author 木易
 */
public class UpdateFrm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JProgressBar progressBar;
	private FileThread thread;
	private JButton start;
	private String name = "update";
	private String url = UpgraderUtil.jarurl32;
	private String savepath = "tmp";

	public UpdateFrm(JFrame This, String description) {
		setTitle("版本升级");
		setBounds(100, 100, 311, 334);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// 设置居中显示
		this.setLocationRelativeTo(null);
		this.setEnabled(false);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u7248\u672C\u4FE1\u606F",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 10, 285, 250);
		contentPane.add(panel);
		panel.setLayout(null);

		start = new JButton("开始下载");
		start.setBounds(347, 95, 124, 23);
		start.setVisible(false);
		panel.add(start);
		start.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);        //激活自动换行功能 
		textArea.setWrapStyleWord(true);
		textArea.setBounds(10, 23, 265, 217);
		textArea.setText(description);
		panel.add(textArea);

		progressBar = new JProgressBar();
		progressBar.setBounds(70, 274, 225, 20);
		contentPane.add(progressBar);
		progressBar.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		progressBar.setForeground(Color.GRAY);
		progressBar.setStringPainted(true);
		progressBar.setValue(0);

		JLabel label = new JLabel("下载进度:");
		label.setBounds(10, 270, 62, 25);
		contentPane.add(label);
		label.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));

		thread = new FileThread(This, this, name, url);
		thread.start();
	}

	class FileThread extends Thread {
		private String fileurl, filename;
		private JFrame frame_f;
		private JFrame frame_c;

		public FileThread(JFrame frame_f, JFrame frame_c, String name, String url) {
			this.frame_f = frame_f;
			this.frame_c = frame_c;
			this.fileurl = url;
			this.filename = name;
		}

		@Override
		public void run() {
			try {
				try {
					FileSave.saveFile(progressBar, savepath, fileurl, filename, start);
					frame_f.setEnabled(true);
					frame_c.dispose();
					UpgraderUtil.update();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
}
