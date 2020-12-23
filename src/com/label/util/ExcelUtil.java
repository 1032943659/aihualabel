package com.label.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.swing.JTable;
import javax.swing.table.TableModel;


/**
 * JTable导出到excel
 * @author 木易
 *
 */
public class ExcelUtil {
    /**
     * JTable导出到excel
     * @param table
     * @param file
     * @throws IOException
     */
	public void exportTable(JTable table,File file) throws IOException {
        TableModel model = table.getModel();
        BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file), "GB2312"));
        for(int i=0; i < model.getColumnCount(); i++) {
            bWriter.write(model.getColumnName(i));
            bWriter.write("\t");
        }
        bWriter.newLine();
        for(int i=0; i< model.getRowCount(); i++) {
            for(int j=0; j < model.getColumnCount(); j++) {
            	if(model.getValueAt(i,j)==null){
            		bWriter.write("");
            		bWriter.write("\t");
            	}else{
            		bWriter.write(model.getValueAt(i,j).toString());
                    bWriter.write("\t");
            	}
                
            }
            bWriter.newLine();
        }
        bWriter.close();
    }
	
}
