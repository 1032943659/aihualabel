package com.aihua.label.util;

import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JLabel;

import com.aihua.label.view.LabelPrintFrm;

/**
 * JLabel自动换行
 * @author 木易
 *
 */
public class JLabelUtil {
	public static void JlabelSetText(JLabel jLabel, String longString) throws InterruptedException {
		StringBuilder builder = new StringBuilder("<html>");
		char[] chars = longString.toCharArray();
		FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
		int start = 0;
		int len = 0;
		while (start + len < longString.length()) {
			while (true) {
				len++;
				if (start + len > longString.length())
					break;
				if (fontMetrics.charsWidth(chars, start, len) > jLabel.getWidth()) {
					break;
				}
			}
			builder.append(chars, start, len - 1).append("<br/>");
			start = start + len - 1;
			len = 0;
		}
		builder.append(chars, start, longString.length() - start);
		builder.append("</html>");
		jLabel.setText(builder.toString());
		jLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, LabelPrintFrm.otherfontSize));
	}
}
