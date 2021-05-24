package dungeons.shop.panels;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import Dshop.ItemPanel;
import dungeons.shop.bd.CallDatabase;

public class PanelItens extends JScrollPane{
	
	ArrayList<ItemPanel> panel;
	JPanel painelitens;
	
	public PanelItens() {
	
		init();
		
		painelitensinit();
		
		scroll();
	}
	
	public void init() {
		GridLayout layoult = new GridLayout(0, 2);
		layoult.setHgap(10);
		layoult.setVgap(25);
		painelitens = new JPanel(layoult);
		painelitens.setOpaque(false);
		painelitens.setBounds(465, 130, 250, 525);
		
		panel = new ArrayList<ItemPanel>();
	}
	
	public static void painelitensinit() {

		painelitens.removeAll();
		painelitens.revalidate();
		painelitens.validate();
		painelitens.repaint();

		for (int i = 0; i < CallDatabase.IDdositensdobd.size(); i++) {

			panel.add(new ItemPanel(CallDatabase.IDdositensdobd.get(i)));

		}

		for (int i = 0; i < panel.size(); i++) {
			painelitens.add(panel.get(i));

		}

		CallDatabase.IDdositensdobd.clear();
		panel.clear();
		painelitens.revalidate();
		painelitens.repaint();
	}
	
	public void scroll() {
		new JScrollPane(painelitens);
		setBounds(465, 130, 250, 525);
		getVerticalScrollBar().setUnitIncrement(20);
		setBackground(new Color(0, 0, 0, 0));
		getViewport().setOpaque(false);
		setOpaque(false);
		getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		setBorder(null);
		getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
	}
	
}
