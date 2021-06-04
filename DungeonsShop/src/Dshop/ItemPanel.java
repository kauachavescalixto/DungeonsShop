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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pnEme = new JPanel();
	JPanel pnImg = new JPanel();
	JPanel pnall;
	JLayeredPane lpn = new JLayeredPane();
	
	JLabel emerald = new JLabel(new ImageIcon(getClass().getResource("Emerald17X24.png")));
	ImageIcon frameIcon = new ImageIcon(getClass().getResource("Frame.png"));
	JLabel frame = new JLabel(frameIcon);
	JLabel item, nome ,preco;
	
	
	JCheckBox ck = new JCheckBox();

	ImageIcon btoff = new ImageIcon(getClass().getResource("buyButton20X21.png"));
	ImageIcon bton = new ImageIcon(getClass().getResource("buyButtonON20X21.png"));
	
	ArrayList<Integer> selecionados = new ArrayList<>();
	
	
	public ItemPanel(int index){
		
			
		
			if(MineDungeonsShop.listacomuns.get(index-1).contentEquals("Y")) {
				setBorder(BorderFactory.createLineBorder(new Color(156, 156, 156)));
			}
			if(MineDungeonsShop.listararos.get(index-1).contentEquals("Y")) {
				setBorder(BorderFactory.createLineBorder(new Color(26, 199, 49)));
			}
			if(MineDungeonsShop.listaunicos.get(index-1).contentEquals("Y")){
				setBorder(BorderFactory.createLineBorder(new Color(247, 121, 25)));
			}
		
		

		
		
		init();
		def(index-1);
		try {
			item = new JLabel(new ImageIcon(getClass().getResource(MineDungeonsShop.nomes.get(index-1)+".png")));
			pnImg.add(item);
		} catch (Exception e) {
			
		}
		
		preco = new JLabel(String.valueOf(MineDungeonsShop.precos.get(index-1))+" R$");
		nome = new JLabel(MineDungeonsShop.nomes.get(index-1),SwingConstants.CENTER);
		pnEme.add(emerald);
		pnEme.add(preco);
		pnImg.setLayout(new GridLayout(0,1));
	
		preco.setForeground(Color.white);
		nome.setForeground(Color.white);
		preco.setFont(new Font("Courier new", 0,12));
		nome.setFont(new Font("Courier new", 0,12));
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
			
			public void actionPerformed(ActionEvent arg0) {
				if(ck.isSelected()) {
					ck.setIcon(bton);
					MineDungeonsShop.selected.add(index-1);
					
				}else {
					ck.setIcon(btoff);
					for (int i = 0; i < MineDungeonsShop.selected.size(); i++) {
						if(MineDungeonsShop.selected.get(i)==index-1) {
							MineDungeonsShop.selected.remove(i);
						}
					}
				}
			}
			
		});
	}

	public void init() {
		//setOpaque(false);
		setSize(111,180);
		setLayout(new BorderLayout(0,0));
		
		setBackground(new Color(13,8,13));
	}

}
