package com.aihua.label.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

/**
 * ��־��
 * @author Administrator
 *
 */
public class LogUtil {
	private static String log_path = "src/log/";// ��־����·��
    private static String log_name = "labellog";// ��־�ļ�����ǰ���֣�
    private static boolean console_out = true;// ��־�Ƿ����������̨
    /*
     * yyyy-MM: ÿ���¸���һ��log��־ 
     * yyyy-ww: ÿ�����ڸ���һ��log��־
     * yyyy-MM-dd: ÿ�����һ��log��־
     * yyyy-MM-dd-a: ÿ�����ҹ���������һ��log��־ 
     * yyyy-MM-dd-HH: ÿСʱ����һ��log��־
     * yyyy-MM-dd-HH-mm: ÿ���Ӹ���һ��log��־
     */
    private static String update_hz = "yyyy-MM-dd";//������־��Ƶ�ʣ�ÿ�ո���һ��
    private static long max_log_size = 1024 * 1024 * 2;//������־�ļ�����С2M

    public static void debug(String msg) {
        runWrite(msg, log_path, log_name + "_debug");
    }

    public static void info(String msg) {
        runWrite(msg, log_path, log_name + "_info");
    }

    public static void error(String msg) {
        runWrite(msg, log_path, log_name + "_error");
    }

    public static void exception(Exception e) {
        String errorMessage = e.toString() + "";
        StackTraceElement[] eArray = e.getStackTrace();
        for (int i = 0; i < eArray.length; i++) {
            String className = e.getStackTrace()[i].getClassName();
            String MethodName = e.getStackTrace()[i].getMethodName();
            int LineNumber = e.getStackTrace()[i].getLineNumber();
            errorMessage = LineNumber>0?(errorMessage + "\n---" + className + "." + MethodName + ",\tline:" + LineNumber):(errorMessage + "\n---" + className + "." + MethodName);
        }
        logResult(errorMessage, log_path, log_name + "_exception");
    }
    
    /**
     * д��־
     * 
     * @param sWord
     *            Ҫд����־����ı�����
     */
    public static void logResult(String sWord) {
        runWrite(sWord, log_path, log_name);
    }

    public static void logResult(String sWord, String logPath, String logName) {
        FileWriter writer = null;
        try {
            File dir = new File(logPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
            File f = new File(logPath + logName + "_" + new SimpleDateFormat(update_hz).format(new Date()) + ".txt");
            if (!f.exists()) {
                f.createNewFile();
                sWord = "������־\r\n" + "[" + dt + "]\t" + sWord;
            } else {
                long logSize = f.length();
                // �ļ���С����2M������
                if (logSize >= max_log_size) {
                    String backLogName = logPath + logName
                            + new SimpleDateFormat("_yyyy-MM-dd.HHmmss.SSS").format(new Date()) + ".txt";
                    f.renameTo(new File(backLogName));
                }
            }
            writer = new FileWriter(f, true);
            writer.write("[" + dt + "]\t" + sWord + "\r\n");
            if (console_out) {
                System.out.println("[" + dt + "]\t" + sWord);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "��¼��־�쳣!"+e.toString());
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void runWrite(final String sWord,final String logPath,final String logName) {
            new Thread() {
                public void run() {
                    logResult(sWord, logPath, logName);
                }
            }.start();;
    }
}
