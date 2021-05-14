package Dshop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BarPanel extends JPanel{

	JLabel on = new JLabel(new ImageIcon(getClass().getResource("Barra499X37.png")));
	JLabel off = new JLabel(new ImageIcon(getClass().getResource("BarraOFF499X37.png")));
	
	JCheckBox check = new JCheckBox();
	
	JLabel txt;
	
	String[] conditions = new String[]{
			"Somente Comuns","Somente Raros","Somente Únicos","Somente Armas", "Somente Armaduras"
			,"Mais caros que 500", "Mais baratos que 500"
		};
	
	public BarPanel(int index) {
		init();
		def_eventos(index);
		txt = new JLabel(conditions[index]);
		
		off.setSize(499,37);
		on.setSize(499,37);
		check.setSize(50,29);
		check.setLocation(400,5);
		check.setContentAreaFilled(false);

		txt.setSize(400,37);
		txt.setLocation(10,0);
		txt.setForeground(Color.white);
		txt.setFont(new Font("Courier New", Font.BOLD,18));
		
		
		check.setOpaque(false);
		
		
		add(txt);
		
		add(on);
		on.setVisible(false);
		add(off);
		add(check);
		
	}

	public void init() {
		setOpaque(false);
		setLayout(null);
		setSize(499,37);
		setBackground(new Color(0,0,0,0));
	}
	
	public void def_eventos(int index) {
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(check.isSelected()) {
					on.setVisible(true);
					off.setVisible(false);
					
				}else {
					off.setVisible(true);
					on.setVisible(false);
					
				}
				
			}
		});
		
	}
}
