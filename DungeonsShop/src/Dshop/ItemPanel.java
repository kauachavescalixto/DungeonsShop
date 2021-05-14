package Dshop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import javafx.scene.image.Image;

public class ItemPanel extends JPanel{
	JPanel pnEme = new JPanel();
	JPanel pnImg = new JPanel();
	JPanel pnall;
	JLayeredPane lpn = new JLayeredPane();
	
	JLabel emerald = new JLabel(new ImageIcon(getClass().getResource("Emerald17X24.png")));
	ImageIcon frameIcon = new ImageIcon(getClass().getResource("Frame.png"));
	JLabel frame = new JLabel(frameIcon);
	JLabel item, nome ,preco;
	int[] precos = new int[10];
	
	JCheckBox ck = new JCheckBox();

	String[] itens = new String[] {
			"Guarda","Incendiária","Luvas","Mago","MagoDaSelva",
			"Mercenario","Ocelote","Picareta","PresasDoGelo","Tempestuoso"
	};
	ImageIcon btoff = new ImageIcon(getClass().getResource("buyButton20X21.png"));
	ImageIcon bton = new ImageIcon(getClass().getResource("buyButtonON20X21.png"));
	
	ArrayList<Integer> selecionados = new ArrayList<>();
	
	
	
	public ItemPanel(int index){

		for (int i = 0; i < precos.length; i++) {
			precos[i]=(int) (Math.random()*500);
		}
		init();
		def(index);
		
		item = new JLabel(new ImageIcon(getClass().getResource(itens[index]+".png")));
		preco = new JLabel(String.valueOf(precos[index]));
		nome = new JLabel(itens[index],SwingConstants.CENTER);
		pnEme.add(emerald);
		pnEme.add(preco);
		pnImg.setLayout(new GridLayout(0,1));
		pnImg.add(item);
		preco.setForeground(Color.white);
		nome.setForeground(Color.white);
		preco.setFont(new Font("Courier new", 0,15));
		nome.setFont(new Font("Courier new", 0,15));
		pnEme.setBackground(null);
		pnImg.setBackground(null);
		
		
		ck.setSize(120,180);
		ck.setOpaque(false);
		ck.setIcon(btoff);
		
		
		pnall = new JPanel(new BorderLayout());
		pnall.setSize(111,180);
		pnall.setBackground(new Color(0,0,0,0));
		
		pnall.add(pnEme, "North");
		pnall.add(pnImg, "Center");
		pnall.add(nome, "South");
		pnall.setOpaque(false);
		
		lpn.add(pnall);
		lpn.add(ck);
		lpn.setPreferredSize(new Dimension(111,180));
		lpn.setOpaque(false);
		add(lpn);
		
		
		
	}

	public void def(int index) {
		ck.addActionListener(new ActionListener() {
			
			public void aloga() {
				
			}
			
			public void actionPerformed(ActionEvent arg0) {
				if(ck.isSelected()) {
					ck.setIcon(bton);
					MineDungeonsShop.selected.add(index);
					
				}else {
					ck.setIcon(btoff);
					
					for (int i = 0; i < MineDungeonsShop.selected.size(); i++) {
						if(MineDungeonsShop.selected.get(i)==index) {
							MineDungeonsShop.selected.remove(i);
						}
					}
				}
			}
			
		});
	}

	public void init() {
		setOpaque(false);
		setSize(111,180);
		setLayout(new BorderLayout(0,0));
		setBorder(BorderFactory.createLineBorder(Color.white,3));
		setBackground(new Color(13,8,13));
	}

}
