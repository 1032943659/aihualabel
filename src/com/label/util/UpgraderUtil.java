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
 * �汾����
 * @author ľ��
 */
public class UpgraderUtil {

	public static Float currentversion = 1.6f;//ϵͳ��ǰ�汾��
	public static Float newversion = currentversion; //����������ϻ�ȡ�����°汾��
	public static boolean downloaded = false;//����������
	public static boolean errored = false;//���س������
	public static boolean updateflag = false;//ǿ�Ƹ��±�־
	public static String versinurl = "http://ftp6383756.host116.sanfengyun.cn/version/version.json"; //�汾��ŵ�ַ
	public static String jarurl64 = "http://ftp6383756.host116.sanfengyun.cn/download/update64.exe"; // 64λjar��ŵ�ַ
	public static String jarurl32 = "http://ftp6383756.host116.sanfengyun.cn/download/update32.exe"; // 64λjar��ŵ�ַ
	public static String string2dowload = "http://ftp6383756.host116.sanfengyun.cn/download/update64.exe"; //�����������װ��ַ
	public static String printListTemplates = "http://ftp6383756.host116.sanfengyun.cn/download/printListTemplates.xls";//��ӡ�嵥ģ������
	public static String standardWord= "http://ftp6383756.host116.sanfengyun.cn/download/������Ӧ�̱�ǩ����涨.docx";//������Ӧ�̱�ǩ����涨����
	public static String fxListTemplates= "http://ftp6383756.host116.sanfengyun.cn/download/fxListTemplates.xls";//���Ͳ������Ա�׼ֵ����ģ������
	public static String source1= "http://ftp6383756.host116.sanfengyun.cn/download/source1.rar";//���Ͳ������Ա�׼ֵ����ģ������
	public static String description = "";//�°汾������Ϣ
	public static String date ="";//�°汾������Ϣ
	/**
	 * ��Ĭ�������°汾
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
	 * ��װ����
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
	 * ��ȡ���°汾��
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
	 * �Զ�����
	 * @return true ���� false ���޸���
	 */
	public static Boolean autoupgrade(JFrame This) {
		getnewversion();// ��ȡ�������ϵİ汾��Ϣ
		if (newversion > currentversion) {
			Font font = new Font("΢���ź�", Font.BOLD, 14);
			UIManager.put("OptionPane.font", font);
			UIManager.put("OptionPane.messageFont", font);
			UIManager.put("OptionPane.buttonFont", new Font("΢���ź�", Font.BOLD, 12));
			if(updateflag==true){//�Ƿ�ǿ�Ƹ���
				This.setEnabled(false);
				UpdateFrm updateFrm =  new UpdateFrm(This,description);
				updateFrm.setVisible(true);
			}else{
				if(JOptionPane.showConfirmDialog(null, "\r\n�汾����:" + "\r\n" + description + "\r\n" + "�Ƿ����ذ�װ?\r\n",
						"�µİ汾! " + "v" + newversion, JOptionPane.YES_NO_CANCEL_OPTION)==0){
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
	 * ��get���󣬻�ȡ�ı�
	 * @param getUrl
	 * @return ��ҳcontext
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
			JOptionPane.showMessageDialog(null, e+"\r\n������������!");
		}
		return sb.toString();
	}
	
	/**
	 * ������Url�������ļ�
	 * @param urlStr
	 * @param fileName
	 * @param savePath
	 * @throws IOException
	 */
	public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// ���ó�ʱ��Ϊ3��
		conn.setConnectTimeout(1 * 1000);
		// ��ֹ���γ���ץȡ������403����
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		// �õ�������
		InputStream inputStream = conn.getInputStream();
		// ��ȡ�Լ�����
		byte[] getData = readInputStream(inputStream);

		// �ļ�����λ��
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
	 * ���������л�ȡ�ֽ�����
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
