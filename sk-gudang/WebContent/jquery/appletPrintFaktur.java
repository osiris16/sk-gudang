package org.radot.utils;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;

public class appletPrintFaktur extends Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9066189221434400409L;
	Font _font = new Font("ARIAL", Font.BOLD, 17);
	public void paint(Graphics s) {
		
	s.drawString("Testing", 0, 0);

	}	

}
