package aloha.dungeons.testes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class one extends JPanel{
	JPanel pnEme = new JPanel();
	JPanel pnImg = new JPanel();
	
	JLabel emerald = new JLabel(new ImageIcon(getClass().getResource("Emerald17X24.png")));
	ImageIcon frameIcon = new ImageIcon(getClass().getResource("Frame.png"));
	JLabel frame = new JLabel(frameIcon);
	JLabel item, nome ,preco;
	int[] precos = new int[10];
	String[] nomes = new String[] {"Stick","idk","yep","a","b","c","pau","aoba","9","10"};
	
	public one(int index) {
		for (int i = 0; i < precos.length; i++) {
			precos[i]=(int) (Math.random()*500);
		}
		init();
		
		item = new JLabel(new ImageIcon(getClass().getResource("item ("+index+").png")));
		preco = new JLabel(String.valueOf(precos[index]));
		nome = new JLabel(nomes[index],SwingConstants.CENTER);
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
		add(pnEme, "North");
		add(pnImg, "Center");
		add(nome, "South");
	}
	public void init() {
		setSize(111,180);
		setLayout(new BorderLayout(0,0));
		setBorder(BorderFactory.createLineBorder(Color.white,3));
		setBackground(new Color(13,8,13));
	}
}
