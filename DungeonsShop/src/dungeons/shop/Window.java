package dungeons.shop;

import java.awt.Color;

import javax.swing.JFrame;

import dungeons.shop.panels.Screen;

public class Window extends JFrame{

	private final int WIDTH = 1280;
	private final int HEIGHT = 720;
	
	public Window() {
		setContentPane(new Screen(WIDTH, HEIGHT));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MineDungeonsShop");
		setVisible(true);

	}

}
