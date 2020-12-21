package com.aihua.label.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

/**
 * 日志类
 * @author Administrator
 *
 */
public class LogUtil {
	private static String log_path = "src/log/";// 日志保存路径
    private static String log_name = "labellog";// 日志文件名（前部分）
    private static boolean console_out = true;// 日志是否输出到控制台
    /*
     * yyyy-MM: 每个月更新一个log日志 
     * yyyy-ww: 每个星期更新一个log日志
     * yyyy-MM-dd: 每天更新一个log日志
     * yyyy-MM-dd-a: 每天的午夜和正午更新一个log日志 
     * yyyy-MM-dd-HH: 每小时更新一个log日志
     * yyyy-MM-dd-HH-mm: 每分钟更新一个log日志
     */
    private static String update_hz = "yyyy-MM-dd";//更新日志的频率，每日更新一次
    private static long max_log_size = 1024 * 1024 * 2;//单个日志文件最大大小2M

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
     * 写日志
     * 
     * @param sWord
     *            要写入日志里的文本内容
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
                sWord = "运行日志\r\n" + "[" + dt + "]\t" + sWord;
            } else {
                long logSize = f.length();
                // 文件大小超过2M，备份
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
            JOptionPane.showMessageDialog(null, "记录日志异常!"+e.toString());
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
