package com.tabela.kaua;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class TabelaPrincipal extends JPanel{

	
	Mytable mtb;
	
	JPanel pnmes = new JPanel();
	JButton[] btmeses = new JButton[12];
	
	JLabel BG = new JLabel(new ImageIcon(getClass().getResource("BG1280X720.png")));
	
	JSearchField nome_sf = new JSearchField();
	JSearchField vencimento_sf = new JSearchField();
	JSearchField valor_sf = new JSearchField();
	JTextArea total = new JTextArea();
	JTextArea totalFake = new JTextArea();
	JLabel totallb = new JLabel("Total:");
	
	JPanel pnbotoes = new JPanel();
	JButton paga = new JButton("");
	JButton remover = new JButton("");
	JButton add = new JButton("");
	
	public TabelaPrincipal() {
		PnBotoes();
		PnMes();
		MTB();
		def_eventos();
		init();
		
	}
	
	public void init() {
		setLayout(null);
		//setBackground(Color.gray);

		add(mtb);
		add(pnbotoes);
		add(pnmes);
		BG.setSize(1280,720);
		//add(BG);
	}

	public void PnBotoes() {
		//pnBotoes
			pnbotoes.setLayout(null);
			pnbotoes.setBounds(900,25,340,620);
			
			add.setBounds(47,235,247,62);
			paga.setBounds(47,422,247,62);
			remover.setBounds(47,557,247,62);
			remover.setContentAreaFilled(false);
			paga.setContentAreaFilled(false);
			add.setContentAreaFilled(false);
			nome_sf.setFont(new Font("arial",0,24));
			vencimento_sf.setFont(new Font("arial",0,24));
			valor_sf.setFont(new Font("arial",0,24));
			
			nome_sf.setEmptyText("Nome");
			nome_sf.setBounds(3,0,335,40);
			
			vencimento_sf.setEmptyText("Data de Vencimento");
			vencimento_sf.setBounds(3,157,335,40);

			valor_sf.setEmptyText("Valor");
			valor_sf.setBounds(3,73,335,40);
			pnbotoes.add(nome_sf);
			pnbotoes.add(valor_sf);
			pnbotoes.add(vencimento_sf);
			pnbotoes.add(add);
			pnbotoes.add(paga);
			pnbotoes.add(remover);
			pnbotoes.setOpaque(false);
		//pnBotoes
	}
	public void PnMes() {
		pnmes.setBounds(14,26,170,675);
		for (int i = 0; i < btmeses.length; i++) {
			btmeses[i] = new JButton();
			btmeses[i].setContentAreaFilled(false);
		}
		GridLayout layout = new GridLayout(0,1);
		layout.setVgap(20);
		pnmes.setLayout(layout);
		pnmes.setBackground(new Color(0,0,0,0));
		pnmes.setOpaque(false);
		for (JButton x : btmeses) {
			pnmes.add(x);
		}
	}
	
	public void MTB() {
		mtb	= new Mytable();
		mtb.setSize(647,623);
		mtb.setLocation(202,69);
		mtb.setBorder(BorderFactory.createDashedBorder(Color.red));	
	}
	
	public void def_eventos() {
		rodar(btmeses[0],0);
		rodar(btmeses[1],1);
		rodar(btmeses[2],2);
		rodar(btmeses[3],3);
		rodar(btmeses[4],4);
		rodar(btmeses[5],5);
		rodar(btmeses[6],6);
		rodar(btmeses[7],7);
		rodar(btmeses[10],10);
		rodar(btmeses[11],11);
	}
	
	public void rodar(JButton bt, int a) {
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(a);
				Mytable.index = a;
				
			}
		});
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Tabela De Depesas v1.3");
		frame.getContentPane().add(new TabelaPrincipal());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(""));

	}

}
