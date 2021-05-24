package dungeons.shop.panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Screen extends JPanel{

	private int Localwidth;
	private int Localheight;
	
	Background bg;
	
	public Screen(int width, int height) {
		this.Localwidth = width;
		this.Localheight = height;
		
		setBackground(new Color(31, 30, 35));
		
		setPreferredSize(new Dimension(Localwidth, Localheight));
		setFocusable(true);
		requestFocus();
		
		init();
	}
	
	public void init() {
		bg = new Background(Localwidth, Localheight);
		add(bg);
	}
	
}
