package com.aihua.label.view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import com.aihua.label.util.FileSave;
import com.aihua.label.util.UpgraderUtil;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;

public class SourceDownloadFrm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JProgressBar progressBar;
	private String savepath;
	private JButton start;
	private JLabel label_1;
	private JTextField url;
	private JButton filechoose;
	private FileThread thread;

	public SourceDownloadFrm(JFrame This) {
		setTitle("文件下载");
		setBounds(100, 100, 517, 334);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// 设置居中显示
		this.setLocationRelativeTo(null);
		
		//将主界面状态设为可操作
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				This.setEnabled(true);
			}
		});
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "打印清单模板", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 481, 171);
		contentPane.add(panel);
		panel.setLayout(null);

		label_1 = new JLabel("文件地址:");
		label_1.setBounds(10, 61, 51, 22);
		panel.add(label_1);
		label_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));

		url = new JTextField(UpgraderUtil.printListTemplates);
		url.setBounds(81, 60, 390, 25);
		panel.add(url);
		url.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
		url.setEditable(false);
		url.setColumns(10);

		JLabel lblNewLabel = new JLabel("文件重命名:");
		lblNewLabel.setBounds(10, 93, 71, 25);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));

		name = new JTextField("打印清单模板");
		name.setBounds(81, 93, 127, 25);
		panel.add(name);
		name.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		name.setColumns(10);

		filechoose = new JButton("更改保存目录");
		filechoose.setBounds(218, 95, 119, 25);
		panel.add(filechoose);
		filechoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser choose = new JFileChooser();
				FileSystemView fsv = FileSystemView.getFileSystemView(); // 注意了，这里重要的一句
				choose.setCurrentDirectory(fsv.getHomeDirectory());
				System.out.println(fsv.getHomeDirectory());
				choose.setAcceptAllFileFilterUsed(false);
				choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				choose.setDialogTitle("选择文件目录");
				choose.showOpenDialog(null);
				if (choose.getSelectedFile() == null) {
					savepath = fsv.getHomeDirectory() + "";
				} else {
					savepath = choose.getSelectedFile().getPath();
				}

			}
		});
		filechoose.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));

		start = new JButton("开始下载");
		start.setBounds(347, 95, 124, 23);
		panel.add(start);
		start.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));

		JLabel label = new JLabel("下载进度:");
		label.setBounds(10, 122, 62, 25);
		panel.add(label);
		label.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));

		progressBar = new JProgressBar();
		progressBar.setBounds(81, 128, 390, 20);
		panel.add(progressBar);
		progressBar.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		progressBar.setForeground(Color.GRAY);
		progressBar.setStringPainted(true);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedItem()=="打印清单导入模板"){
					url.setText(UpgraderUtil.printListTemplates);
					progressBar.setValue(0);
					name.setText("打印清单模板");
				}else if(comboBox.getSelectedItem()=="艾华供应商标签管理规定"){
					url.setText(UpgraderUtil.standardWord);
					progressBar.setValue(0);
					name.setText("艾华供应商标签管理规定");
				}else if(comboBox.getSelectedItem()=="富贤材料属性标准值导入模板"){
					url.setText(UpgraderUtil.fxListTemplates);
					progressBar.setValue(0);
					name.setText("富贤材料属性标准值导入模板");
				}else if(comboBox.getSelectedItem()=="相关说明文件下载"){
					url.setText(UpgraderUtil.source1);
					progressBar.setValue(0);
					name.setText("标签打印相关说明文件");
				}
			}
		});
		comboBox.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "打印清单导入模板","艾华供应商标签管理规定", "富贤材料属性标准值导入模板", "相关说明文件下载"}));
		comboBox.setBounds(81, 31, 216, 21);
		panel.add(comboBox);
		
		JLabel label_2 = new JLabel("选择文件:");
		label_2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		label_2.setBounds(10, 29, 51, 22);
		panel.add(label_2);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText("\u6CE8\u610F\u4E8B\u9879:\r\n1.\u8BF7\u786E\u4FDD\u7F51\u7EDC\u8FDE\u63A5\r\n2.\u6587\u4EF6\u9ED8\u8BA4\u4FDD\u5B58\u5728\u684C\u9762\r\n3.\u5982\u679C\u4E0B\u8F7D\u5931\u8D25\uFF0C\u8BF7\u590D\u5236\u6587\u4EF6\u5730\u5740\u7C98\u8D34\u5230\u6D4F\u89C8\u5668\u5730\u5740\u680F\u624B\u52A8\u8FDB\u884C\u4E0B\u8F7D");
		textPane.setBounds(10, 191, 481, 94);
		contentPane.add(textPane);
		
		
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				progressBar.setValue(0);
				String filename = name.getText();
				String fileurl = url.getText();
				if (filename.equals("")) {
					filename = "打印清单模板";
				} else if (savepath == null || savepath == "") {
					FileSystemView fsv = FileSystemView.getFileSystemView();
					savepath = fsv.getHomeDirectory() + "";
				}
				thread = new FileThread(filename, fileurl);
				thread.start();
			}
		});
	}

	class FileThread extends Thread {
		private String fileurl, filename;

		public FileThread(String name, String url) {
			this.fileurl = url;
			this.filename = name;
		}

		@Override
		public void run() {
			try {
				try {
					FileSave.saveFile(progressBar, savepath, fileurl, filename, start);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e);
			}
		}
	}
}
