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
 * 文件保存工具类
 */
public class FileSave {
	public static void saveFile(JProgressBar jProgressBar, String path, String url, String name, Component notice) throws InterruptedException, IOException {
		FileOutputStream fos = null;
		try {
			// 创建URL对象并指定所对应的地址。
			URL download = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)download.openConnection();
			// 设置超时间为3秒
			conn.setConnectTimeout(1 * 1000);
			// 防止屏蔽程序抓取而返回403错误
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			Long connLength = conn.getContentLengthLong();
			ProgressBar pbt = new ProgressBar(connLength, jProgressBar, notice);
			// 建立输入流获取URL的数据。
			BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
//            InputStream is = download.openStream();
			// 建立文件输出流来存储资源。
			int n = url.lastIndexOf(".");
			String type = url.substring(n, url.length());
			// 文件保存位置
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
			// 循环判断如果is.read()的值不等于-1，则说明字节流中还存在数值，用fos进行输出。
			while ((i = is.read(b)) > -1 && DownUtile.downOver==true) {
				fos.write(b,0,i);
				synchronized (DownUtile.class) {//此处涉及到变量同步
                    DownUtile.downLength=DownUtile.downLength+i; //计算当前下载了多少
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