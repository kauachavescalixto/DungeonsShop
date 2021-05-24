package dungeons.shop;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.ls.LSInput;

import Dshop.BarPanel;
import Dshop.ItemPanel;
import Dshop.JSearchField;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class ref extends JFrame {


	

	

	
	
	BarPanel[] bp = new BarPanel[7];

	

	public ref() {

		

		

		init();
		defeventos();

	}
	

	public void init() {

		avisodinheiro.setBounds(895, 590, 232, 38);
		add(avisodinheiro);
		avisodinheiro.setVisible(false);



		

		

		bpPanel = new JPanel(new GridLayout(0, 1));
		bpPanel.setSize(500, 465);
		bpPanel.setLocation(743, 125);
		bpPanel.setBackground(new Color(0, 0, 0, 0));
		bpPanel.setOpaque(false);
		// bpPanel.setBorder(BorderFactory.createDashedBorder(Color.red));
		for (int i = 0; i < bp.length; i++) {
			bp[i] = new BarPanel(i);
			bp[i].setBackground(new Color(0, 0, 0, 0));
			bpPanel.add(bp[i]);
		}

		btedit.setBounds(1190, 625, 50, 50);
		btadd.setBounds(840, 550, 50, 50);
		btupdate.setBounds(970, 550, 50, 50);
		btremove.setBounds(1100, 550, 50, 50);

		add(btedit);
		add(btadd);
		add(btupdate);
		add(btremove);
		btadd.setVisible(false);
		btupdate.setVisible(false);
		btremove.setVisible(false);
		addpanel();
		add(addPanel);
		addPanel.setVisible(false);
		add(bpPanel);
		
		add(pnVideo);
	}

	public void defeventos() {

		fechar_Warning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Warning.setVisible(false);
				fechar_Warning.setVisible(false);
			}
		});
		
		btadd_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					
					
				} catch (Exception e) {
					Warning.setVisible(true);
					fechar_Warning.setVisible(true);
					return;
				}
			}
		});

		btedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (temp == 0) {
					temp += 1;
					bpPanel.setVisible(false);
					addPanel.setVisible(true);
					btadd.setVisible(true);
					btupdate.setVisible(true);
					btremove.setVisible(true);
				} else {
					temp = 0;
					bpPanel.setVisible(true);
					addPanel.setVisible(false);
					btadd.setVisible(false);
					btupdate.setVisible(false);
					btremove.setVisible(false);
				}

			}
		});

		comum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comum.isSelected()) {

				}
			}
		});

		raro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (raro.isSelected()) {

				}
			}
		});

		unico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (unico.isSelected()) {

				}
			}
		});

		arma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arma.isSelected()) {

				}
			}
		});
		armadura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (armadura.isSelected()) {

				}
			}
		});

		

		bp[0].check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bp[0].check.isSelected()) {

					condicoesdobd.add("comum=\"Y\"");

					execute();
					painelitensinit();

				} else {
					for (int i = 0; i < condicoesdobd.size(); i++) {
						if (condicoesdobd.get(i).contentEquals("comum=\"Y\"")) {
							condicoesdobd.remove(i);

						}
					}
					execute();
					painelitensinit();
				}
			}
		});
		bp[1].check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bp[1].check.isSelected()) {

					condicoesdobd.add("raro=\"Y\"");
					execute();
					painelitensinit();

				} else {
					for (int i = 0; i < condicoesdobd.size(); i++) {
						if (condicoesdobd.get(i).contentEquals("raro=\"Y\"")) {
							condicoesdobd.remove(i);

						}
					}
					execute();
					painelitensinit();

				}
			}
		});
		bp[2].check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bp[2].check.isSelected()) {

					condicoesdobd.add("unico=\"Y\"");
					execute();
					painelitensinit();

				} else {
					for (int i = 0; i < condicoesdobd.size(); i++) {
						if (condicoesdobd.get(i).contentEquals("unico=\"Y\"")) {
							condicoesdobd.remove(i);

						}
					}
					execute();
					painelitensinit();

				}
			}
		});
		bp[3].check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bp[3].check.isSelected()) {

					condicoesdobd.add("arma=\"Y\"");
					execute();
					painelitensinit();

				} else {
					for (int i = 0; i < condicoesdobd.size(); i++) {
						if (condicoesdobd.get(i).contentEquals("arma=\"Y\"")) {
							condicoesdobd.remove(i);

						}
					}
					execute();
					painelitensinit();

				}
			}
		});
		bp[4].check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bp[4].check.isSelected()) {

					condicoesdobd.add("armadura=\"Y\"");
					execute();
					painelitensinit();

				} else {
					for (int i = 0; i < condicoesdobd.size(); i++) {
						if (condicoesdobd.get(i).contentEquals("armadura=\"Y\"")) {
							condicoesdobd.remove(i);

						}
					}
					execute();
					painelitensinit();

				}
			}
		});
		bp[5].check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bp[5].check.isSelected()) {

					condicoesdobd.add("preco>500");
					execute();
					painelitensinit();

				} else {
					for (int i = 0; i < condicoesdobd.size(); i++) {
						if (condicoesdobd.get(i).contentEquals("preco>500")) {
							condicoesdobd.remove(i);

						}
					}
					execute();
					painelitensinit();

				}
			}
		});
		bp[6].check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bp[6].check.isSelected()) {

					condicoesdobd.add("preco<500");
					execute();
					painelitensinit();

				} else {
					for (int i = 0; i < condicoesdobd.size(); i++) {
						if (condicoesdobd.get(i).contentEquals("preco<500")) {
							condicoesdobd.remove(i);

						}
					}
					execute();
					painelitensinit();

				}
			}
		});
	}

}
