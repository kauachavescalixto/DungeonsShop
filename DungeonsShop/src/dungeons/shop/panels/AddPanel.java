package dungeons.shop.panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Dshop.JSearchField;

public class AddPanel extends JPanel{
	
	JSearchField nome, preco;
	JRadioButton comum, raro, unico;
	JRadioButton arma, armadura;
	ButtonGroup raridade, tipo;
	
	ImageIcon RBON = new ImageIcon(getClass().getResource("buyButtonON20X21.png"));
	ImageIcon RBOFF = new ImageIcon(getClass().getResource("buyButton20X21.png"));

	ImageIcon img_btadd_add = new ImageIcon(getClass().getResource(""));
	JButton btadd_add = new JButton("");
	
	JLabel Warning = new JLabel(new ImageIcon(getClass().getResource("Warning404X211.png")));
	JButton fechar_Warning = new JButton("");

	
	public AddPanel() {
	

		new JPanel(null);
		setSize(500, 415);
		setBorder(BorderFactory.createDashedBorder(Color.red));
		setLocation(743, 125);
		setOpaque(false);

		nome = new JSearchField();
		nome.setEmptyText("Nome");
		nome.setSize(300, 30);
		nome.setLocation((getWidth() - nome.getWidth()) / 2, 25);
		nome.setFont(new Font("Courier new", 0, 24));
		nome.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		nome.setBackground(new Color(16, 14, 18));
		nome.setForeground(Color.white);
		nome.setBorder(BorderFactory.createLineBorder(Color.white, 2));

		preco = new JSearchField();
		preco.setEmptyText("R$");
		preco.setSize(200, 30);
		preco.setLocation((getWidth() - preco.getWidth()) / 2, 340);
		preco.setFont(new Font("Courier new", 0, 22));
		preco.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		preco.setBackground(new Color(16, 14, 18));
		preco.setForeground(Color.white);
		preco.setBorder(BorderFactory.createLineBorder(Color.white, 2));

		comum = new JRadioButton("Comum");
		comum.setFont(new Font("Courier new", 0, 18));
		comum.setForeground(Color.white);

		raro = new JRadioButton("Raro");
		raro.setFont(new Font("Courier new", 0, 18));
		raro.setForeground(Color.white);

		unico = new JRadioButton("Único");
		unico.setFont(new Font("Courier new", 0, 18));
		unico.setForeground(Color.white);

		raridade = new ButtonGroup();
		raridade.add(comum);
		comum.setSize(150, 50);
		comum.setLocation(((getWidth() - comum.getWidth()) / 2), 65);
		comum.setOpaque(false);
		comum.setIcon(RBOFF);
		comum.setSelectedIcon(RBON);
		comum.setDisabledIcon(RBOFF);

		raridade.add(raro);
		raro.setSize(150, 50);
		raro.setLocation(((getWidth() - raro.getWidth()) / 2), 115);
		raro.setOpaque(false);
		raro.setIcon(RBOFF);
		raro.setSelectedIcon(RBON);
		raro.setDisabledIcon(RBOFF);

		raridade.add(unico);
		unico.setSize(150, 50);
		unico.setLocation(((getWidth() - unico.getWidth()) / 2), 165);
		unico.setOpaque(false);
		unico.setIcon(RBOFF);
		unico.setSelectedIcon(RBON);
		unico.setDisabledIcon(RBOFF);

		arma = new JRadioButton("Arma");
		arma.setFont(new Font("Courier new", 0, 18));
		arma.setForeground(Color.white);

		armadura = new JRadioButton("Armadura");
		armadura.setFont(new Font("Courier new", 0, 18));
		armadura.setForeground(Color.white);

		tipo = new ButtonGroup();
		tipo.add(arma);
		arma.setSize(150, 50);
		arma.setLocation(((getWidth() - arma.getWidth()) / 2), 225);
		arma.setOpaque(false);
		arma.setIcon(RBOFF);
		arma.setSelectedIcon(RBON);
		arma.setDisabledIcon(RBOFF);

		tipo.add(armadura);
		armadura.setSize(150, 50);
		armadura.setLocation(((getWidth() - armadura.getWidth()) / 2), 275);
		armadura.setOpaque(false);
		armadura.setIcon(RBOFF);
		armadura.setSelectedIcon(RBON);
		armadura.setDisabledIcon(RBOFF);

		btadd_add.setSize(100, 25);
		btadd_add.setLocation((getWidth() - btadd_add.getWidth()) / 2, 390);
		btadd_add.setBackground(new Color(13, 8, 13));
		btadd_add.setFont(new Font("Courier new", 0, 12));
		btadd_add.setText("Confirmar");
		btadd_add.setForeground(Color.white);

		Warning.setSize(404, 211);
		Warning.setLocation((getWidth() - Warning.getWidth()) / 2,
				(getHeight() - Warning.getHeight()) / 2);

		fechar_Warning.setSize(25, 25);
		fechar_Warning.setLocation((getWidth() - fechar_Warning.getWidth() + 304) / 2,
				(getHeight() - fechar_Warning.getHeight() - 123) / 2);
		fechar_Warning.setContentAreaFilled(false);
		
		add(fechar_Warning);
		add(Warning);

		add(btadd_add);
		add(nome);
		add(preco);
		add(comum);
		add(raro);
		add(unico);
		add(arma);
		add(armadura);
	
	
	}
	
}
