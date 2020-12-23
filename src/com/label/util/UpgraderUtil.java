package com.label.util;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import com.alibaba.fastjson.JSONObject;
import com.label.view.UpdateFrm;

/**
 * 版本控制
 * @author 木易
 */
public class UpgraderUtil {

	public static Float currentversion = 1.6f;//系统当前版本号
	public static Float newversion = currentversion; //保存从网络上获取的最新版本号
	public static boolean downloaded = false;//下载完成与否
	public static boolean errored = false;//下载出错与否
	public static boolean updateflag = false;//强制更新标志
	public static String versinurl = "http://ftp6383756.host116.sanfengyun.cn/version/version.json"; //版本存放地址
	public static String jarurl64 = "http://ftp6383756.host116.sanfengyun.cn/download/update64.exe"; // 64位jar存放地址
	public static String jarurl32 = "http://ftp6383756.host116.sanfengyun.cn/download/update32.exe"; // 64位jar存放地址
	public static String string2dowload = "http://ftp6383756.host116.sanfengyun.cn/download/update64.exe"; //完整版软件安装地址
	public static String printListTemplates = "http://ftp6383756.host116.sanfengyun.cn/download/printListTemplates.xls";//打印清单模板下载
	public static String standardWord= "http://ftp6383756.host116.sanfengyun.cn/download/艾华供应商标签管理规定.docx";//艾华供应商标签管理规定下载
	public static String fxListTemplates= "http://ftp6383756.host116.sanfengyun.cn/download/fxListTemplates.xls";//富贤材料属性标准值导入模板下载
	public static String source1= "http://ftp6383756.host116.sanfengyun.cn/download/source1.rar";//富贤材料属性标准值导入模板下载
	public static String description = "";//新版本更新信息
	public static String date ="";//新版本更新信息
	/**
	 * 静默下载最新版本
	 */
	public static void dowload() {
		try {
			downLoadFromUrl(jarurl32, "update.exe", "tmp");
			downloaded = true;
		} catch (Exception e) {
			downloaded = false;
			errored = true;
			e.printStackTrace();
		}
	}

	/**
	 * 安装更新
	 * @throws InterruptedException 
	 */
	public static void update() {
		try {
			Process p= Runtime.getRuntime().exec("cmd /c cd tmp &&start update.exe");
			InputStream  error = p.getErrorStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(error,"GB2312"));
			StringBuffer buffer = new StringBuffer();
	  		String s = "";
	  		while ((s = bufferedReader.readLine()) != null) {
	  			buffer.append(s);
	  		}
	  		bufferedReader.close();
	  		if(!StringUtil.isEmpty(buffer.toString())){
	  			JOptionPane.showMessageDialog(null, buffer.toString());
	  		}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取最新版本号
	 * @return
	 */
	public static void getnewversion() {
		String json = sendGetRequest(versinurl);
		JSONObject ob =  JSONObject.parseObject(json);
		newversion = ob.getFloat("version");
		description = ob.getString("desc");
		date = ob.getString("date");
		updateflag = ob.getBoolean("flag");
	}
	
	/**
	 * 自动更新
	 * @return true 更新 false 暂无更新
	 */
	public static Boolean autoupgrade(JFrame This) {
		getnewversion();// 获取服务器上的版本信息
		if (newversion > currentversion) {
			Font font = new Font("微软雅黑", Font.BOLD, 14);
			UIManager.put("OptionPane.font", font);
			UIManager.put("OptionPane.messageFont", font);
			UIManager.put("OptionPane.buttonFont", new Font("微软雅黑", Font.BOLD, 12));
			if(updateflag==true){//是否强制更新
				This.setEnabled(false);
				UpdateFrm updateFrm =  new UpdateFrm(This,description);
				updateFrm.setVisible(true);
			}else{
				if(JOptionPane.showConfirmDialog(null, "\r\n版本描述:" + "\r\n" + description + "\r\n" + "是否下载安装?\r\n",
						"新的版本! " + "v" + newversion, JOptionPane.YES_NO_CANCEL_OPTION)==0){
					This.setEnabled(false);
					UpdateFrm updateFrm =  new UpdateFrm(This,description);
					updateFrm.setVisible(true);
				}
			}
			return true;
		}else{
			return false;
		}
		
	}

	/**
	 * 发get请求，获取文本
	 * @param getUrl
	 * @return 网页context
	 */
	public static String sendGetRequest(String getUrl) {
		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			URL url = new URL(getUrl);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setAllowUserInteraction(false);
			isr = new InputStreamReader(url.openStream(),"UTF-8");
			br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e+"\r\n请检查网络设置!");
		}
		return sb.toString();
	}
	
	/**
	 * 从网络Url中下载文件
	 * @param urlStr
	 * @param fileName
	 * @param savePath
	 * @throws IOException
	 */
	public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置超时间为3秒
		conn.setConnectTimeout(1 * 1000);
		// 防止屏蔽程序抓取而返回403错误
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		// 得到输入流
		InputStream inputStream = conn.getInputStream();
		// 获取自己数组
		byte[] getData = readInputStream(inputStream);

		// 文件保存位置
		File saveDir = new File(savePath);
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}
		File file = new File(saveDir + File.separator + fileName);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(getData);
		if (fos != null) {
			fos.close();
		}
		if (inputStream != null) {
			inputStream.close();
		}
	}

	/**
	 * 从输入流中获取字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}
	
}
