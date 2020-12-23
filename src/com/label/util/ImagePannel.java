package com.label.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * JPanel±£´æÍ¼Æ¬
 * @author ZY
 *
 */
public class ImagePannel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage bi;
	
	public void setBufferedImage(BufferedImage bi){
		this.bi = bi;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(bi != null)
			g.drawImage(bi, 0, 0, bi.getWidth(), bi.getHeight(), this);
	}

}
