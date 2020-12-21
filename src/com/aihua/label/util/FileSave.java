package com.aihua.label.util;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * �ļ����湤����
 */
public class FileSave {
	public static void saveFile(JProgressBar jProgressBar, String path, String url, String name, Component notice) throws InterruptedException, IOException {
		FileOutputStream fos = null;
		try {
			// ����URL����ָ������Ӧ�ĵ�ַ��
			URL download = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)download.openConnection();
			// ���ó�ʱ��Ϊ3��
			conn.setConnectTimeout(1 * 1000);
			// ��ֹ���γ���ץȡ������403����
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			Long connLength = conn.getContentLengthLong();
			ProgressBar pbt = new ProgressBar(connLength, jProgressBar, notice);
			// ������������ȡURL�����ݡ�
			BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
//            InputStream is = download.openStream();
			// �����ļ���������洢��Դ��
			int n = url.lastIndexOf(".");
			String type = url.substring(n, url.length());
			// �ļ�����λ��
			File saveDir = new File(path);
			if (!saveDir.exists()) {
				saveDir.mkdir();
			}
			
			String newpath = saveDir + "/" + name + type;
			newpath = newpath.replaceAll("\\\\", "/");
			fos = new FileOutputStream(newpath);
//			RandomAccessFile raf=new RandomAccessFile(newpath, "rwd");
//			raf.seek(0);
			new Thread(pbt).start();
			byte b[]=new byte[1024];
			int i=0;
			// ѭ���ж����is.read()��ֵ������-1����˵���ֽ����л�������ֵ����fos���������
			while ((i = is.read(b)) > -1 && DownUtile.downOver==true) {
				fos.write(b,0,i);
				synchronized (DownUtile.class) {//�˴��漰������ͬ��
                    DownUtile.downLength=DownUtile.downLength+i; //���㵱ǰ�����˶���
                    pbt.updateProgress(DownUtile.downLength);
                }
				
			}
			is.close();
			fos.close();
//			namelable.setText("");
//			urllable.setText("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e);
		} finally {

		}
	}
}