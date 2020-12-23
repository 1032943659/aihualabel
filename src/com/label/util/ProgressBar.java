package com.label.util;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
/**
 * 下载进度条
 * @author 木易
 *
 */
public class ProgressBar extends JPanel implements Runnable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ArrayList<Integer> proList = new ArrayList<Integer>();
    private long totalSize;//总大小
    private boolean run = true;
    private JProgressBar progressBar;
    private Component notice;
    public ProgressBar(long totalSize,JProgressBar progressBar,Component notice){
        this.totalSize = totalSize;
        this.progressBar=progressBar;
        this.notice=notice;
        //TODO 创建进度条
    }
    /**
     * @param l 进度
     */
    public void updateProgress(int l){
        synchronized (this.proList) {
            if(this.run){
                this.proList.add(l);
                this.proList.notify();
            }
        }
    }
 
    public void finish(){
        this.run = false;
        //关闭进度条
    }
    
 
    @Override
    public void run() {
        synchronized (this.proList) {
            try {
                while (this.run) {
                    if(this.proList.size()==0){
                        this.proList.wait();
                    }
                    synchronized (proList) {
                    	int k =this.proList.get(this.proList.size()-1)*100;
                    	this.proList.remove(0);
                        int progress=(int) (k/this.totalSize );
                        if (progress<100&&progress>0){
                            notice.setEnabled(false);
                        }else {
                        	notice.setEnabled(true);
                        }
                        progressBar.setValue(progress);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
