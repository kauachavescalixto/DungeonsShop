package Dshop;

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
import javax.swing.JComboBox;
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
import javax.xml.namespace.QName;

import org.w3c.dom.ls.LSInput;

import com.mysql.jdbc.Statement;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MineDungeonsShop extends JFrame {

	JPanel pnVideo = new JPanel();
	private final JFXPanel jfxpanel = new JFXPanel();

	ArrayList<ItemPanel> panel = new ArrayList<ItemPanel>();

	JPanel painelitens;
	JScrollPane scroll;

	JLabel resultados = new JLabel("");
	JLabel totaleme = new JLabel("");
	int Nresultados = 0;
	int carteira = (int) (Math.random() * 10000);

	JLabel bg = new JLabel(new ImageIcon(getClass().getResource("BG.png")));
	JLabel avisodinheiro = new JLabel(new ImageIcon(getClass().getResource("avisodinheiro232X38.png")));

	BarPanel[] bp = new BarPanel[7];

	JPanel bpPanel, addPanel, updatePanel, removePanel;

	ImageIcon IMGcomprarbt = new ImageIcon(getClass().getResource("ComprarBt336X84.png"));
	JButton comprarbt = new JButton("", IMGcomprarbt);
	ImageIcon imgEdit = new ImageIcon(getClass().getResource("editbt50X50.png"));
	ImageIcon imgAdd = new ImageIcon(getClass().getResource("addbt50X50.png"));
	ImageIcon imgUpdate = new ImageIcon(getClass().getResource("updatebt50X50.png"));
	ImageIcon imgRemove = new ImageIcon(getClass().getResource("removebt50X50.png"));

	JButton btedit = new JButton("", imgEdit);
	JButton btadd = new JButton("", imgAdd);
	JButton btupdate = new JButton("", imgUpdate);
	JButton btremove = new JButton("", imgRemove);
	
	
	ImageIcon img_btadd_add = new ImageIcon(getClass().getResource(""));
	
	JButton btadd_add = new JButton("");
	JButton btupdateADD = new JButton("");
	JButton btremoveADD = new JButton("");
	
	JLabel Warning = new JLabel(new ImageIcon(getClass().getResource("Warning404X211.png")));
	JButton fechar_Warning = new JButton("");

	JButton fechar = new JButton("");

	static ArrayList<Integer> selected = new ArrayList<>();

	static ArrayList<Integer> IDdositensdobd = new ArrayList<>();

	static ArrayList<Integer> precos = new ArrayList<Integer>();

	static ArrayList<String> nomes = new ArrayList<>();

	static ArrayList<String> listararos = new ArrayList<>();
	static ArrayList<String> listacomuns = new ArrayList<>();
	static ArrayList<String> listaunicos = new ArrayList<>();

	static ArrayList<String> condicoesdobd = new ArrayList<>();
	String texto = "";

	ArrayList<Integer> idbanidas = new ArrayList<Integer>();

	private bd bd;
	private PreparedStatement st;
	private ResultSet rs;

	JSearchField nome, preco;
	JSearchField nome2, preco2;
	JSearchField nome3, preco3;
	JRadioButton comum, raro, unico;
	JRadioButton comum2, raro2, unico2;
	JRadioButton comum3, raro3, unico3;
	JRadioButton arma, armadura;
	JRadioButton arma2, armadura2;
	JRadioButton arma3, armadura3;
	ButtonGroup raridade, tipo;
	ButtonGroup raridade2, tipo2;
	ButtonGroup raridade3, tipo3;

	ImageIcon RBON = new ImageIcon(getClass().getResource("buyButtonON20X21.png"));
	ImageIcon RBOFF = new ImageIcon(getClass().getResource("buyButton20X21.png"));

	
	 itensDAO f;
	
	public MineDungeonsShop() {
		setLayout(null);
		setBackground(new Color(31, 30, 35));
		setSize(1290, 750);

		GridLayout layoult = new GridLayout(0, 2);
		layoult.setHgap(10);
		layoult.setVgap(25);
		painelitens = new JPanel(layoult);
		painelitens.setOpaque(false);

		bd = new bd();
		if (!bd.getConnection()) {
			JOptionPane.showMessageDialog(null, "Falha na  conexão!");
			System.exit(0);
		}

		execute();
		init();
		videoinstance();
		defeventos();
		

	}

	public void escrever() {

		texto = "select * from itens2";

		for (int i = 0; i < condicoesdobd.size(); i++) {

			if (i > 0) {
				texto += " and " + condicoesdobd.get(i);
			} else {
				texto += " where " + condicoesdobd.get(i);
			}

		}
	}

	public void execute() {
		try {
			escrever();

			st = bd.c.prepareStatement(texto);

			rs = st.executeQuery();

			chamarbancodedados();

		} catch (SQLException e) {
			System.out.println(e.toString());
		}

	}

	int qtdeColunas;
	
	public void chamarbancodedados() {

		try {
			qtdeColunas = rs.getMetaData().getColumnCount();

			IDdositensdobd.clear();

			while (rs.next()) {
				try {
					
					IDdositensdobd.add(Integer.parseInt(rs.getString("id")));
					precos.add(Integer.parseInt(rs.getString("preco")));
					nomes.add(rs.getString("nome"));
					listacomuns.add(rs.getString("comum"));
					listararos.add(rs.getString("raro"));
					listaunicos.add(rs.getString("unico"));
					
					
					
					
					String[] dados = new String[qtdeColunas];
					for (int i = 1; i <= qtdeColunas; i++) {
						dados[i - 1] = rs.getString(i);
					}
					
				} catch (SQLException erro) {
					System.out.println(erro.toString());
				}

			}

			for (int i = 0; i < idbanidas.size(); i++) {
				for (int j = 0; j < IDdositensdobd.size(); j++) {
					if (IDdositensdobd.get(j) == (idbanidas.get(i) + 2)) {

						IDdositensdobd.remove(j);
					}
				}
			}

			for (int i = 1; i < IDdositensdobd.size(); i++) {
				
				String sql = "update itens2 set id = "+i+" where nome = \""+nomes.get(i-1)+"\"";
				st = bd.c.prepareStatement(sql);
				st.execute();
			}
			
			
			Nresultados = IDdositensdobd.size();
			resultados.setText(Nresultados + " Resultados");

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Comando Inválido" + erro.toString());
		}
	}

	public void videoinstance() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				File file = new File("C:\\DungeonsVideo4-1.m4v");
				MediaPlayer video = new MediaPlayer(new Media(file.toURI().toString()));
				jfxpanel.setScene(new Scene(new Group(new MediaView(video))));
				video.setVolume(0);
				video.setCycleCount(MediaPlayer.INDEFINITE);
				video.play();
				repaint();
			}
		});
		pnVideo.setSize(440, 556);
		pnVideo.setLayout(new BorderLayout());
		pnVideo.add(jfxpanel, BorderLayout.CENTER);
		pnVideo.setBackground(null);
		pnVideo.setLocation(10, 98);
	}

	public void painelitensinit() {

		painelitens.removeAll();
		painelitens.revalidate();
		painelitens.validate();
		painelitens.repaint();

		for (int i = 0; i < IDdositensdobd.size(); i++) {
			
			panel.add(new ItemPanel(IDdositensdobd.get(i)));

		}

		for (int i = 0; i < panel.size(); i++) {
			painelitens.add(panel.get(i));

		}

		IDdositensdobd.clear();
		panel.clear();
		painelitens.revalidate();
		painelitens.repaint();
	}

	public void init() {

		f = new itensDAO();
		if (!f.bd.getConnection()) { // verificação da conexão com o bd.
			JOptionPane.showMessageDialog(null, "Falha na conexão!");
			System.exit(0);
		}
		
		avisodinheiro.setBounds(895, 590, 232, 38);
		add(avisodinheiro);
		avisodinheiro.setVisible(false);

		painelitens.setBounds(465, 130, 250, 525);
		// painelitens.setBorder(BorderFactory.createDashedBorder(Color.red));
		painelitensinit();

		scroll = new JScrollPane(painelitens);
		scroll.setBounds(465, 130, 250, 525);
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		scroll.setBackground(new Color(0, 0, 0, 0));
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		scroll.setBorder(null);
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

		add(scroll);

		resultados.setSize(200, 25);
		resultados.setLocation(465, 105);
		resultados.setForeground(new Color(255, 255, 255, 150));
		resultados.setFont(new Font("courier new", Font.ITALIC, 14));
		add(resultados);

		totaleme.setText(carteira + " R$");
		totaleme.setSize(200, 25);
		totaleme.setLocation(75, 58);
		totaleme.setForeground(Color.white);
		totaleme.setFont(new Font("courier new", Font.ITALIC, 20));
		add(totaleme);

		fechar.setSize(40, 40);
		fechar.setLocation(1200, 40);
		fechar.setContentAreaFilled(false);
		add(fechar);

		comprarbt.setSize(330, 75);
		comprarbt.setLocation(830, 613);
		comprarbt.setContentAreaFilled(false);
		add(comprarbt);

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
		updatepanel();
		removepanel();
		add(addPanel);
		add(updatePanel);
		add(removePanel);
		
		addPanel.setVisible(false);
		updatePanel.setVisible(false);
		removePanel.setVisible(false);
		add(bpPanel);
		bg.setSize(1280, 720);
		add(bg);
		add(pnVideo);
	}

	public void addpanel() {
		addPanel = new JPanel(null);
		addPanel.setSize(500, 415);
		addPanel.setBorder(BorderFactory.createDashedBorder(Color.red));
		addPanel.setLocation(743, 125);
		addPanel.setOpaque(false);

		nome = new JSearchField();
		nome.setEmptyText("Nome:");
		nome.setSize(300, 30);
		nome.setLocation((addPanel.getWidth() - nome.getWidth()) / 2, 25);
		nome.setFont(new Font("Courier new", 0, 24));
		nome.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		nome.setBackground(new Color(16, 14, 18));
		nome.setForeground(Color.white);
		nome.setBorder(BorderFactory.createLineBorder(Color.white, 2));

		preco = new JSearchField();
		preco.setEmptyText("R$");
		preco.setSize(200, 30);
		preco.setLocation((addPanel.getWidth() - preco.getWidth()) / 2, 340);
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
		comum.setSize(150, 50);
		comum.setLocation(((addPanel.getWidth() - comum.getWidth()) / 2), 65);
		comum.setOpaque(false);
		comum.setIcon(RBOFF);
		comum.setSelectedIcon(RBON);
		comum.setDisabledIcon(RBOFF);
		raridade.add(comum);
		
		raridade.add(raro);
		raro.setSize(150, 50);
		raro.setLocation(((addPanel.getWidth() - raro.getWidth()) / 2), 115);
		raro.setOpaque(false);
		raro.setIcon(RBOFF);
		raro.setSelectedIcon(RBON);
		raro.setDisabledIcon(RBOFF);

		raridade.add(unico);
		unico.setSize(150, 50);
		unico.setLocation(((addPanel.getWidth() - unico.getWidth()) / 2), 165);
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
		arma.setLocation(((addPanel.getWidth() - arma.getWidth()) / 2), 225);
		arma.setOpaque(false);
		arma.setIcon(RBOFF);
		arma.setSelectedIcon(RBON);
		arma.setDisabledIcon(RBOFF);

		tipo.add(armadura);
		armadura.setSize(150, 50);
		armadura.setLocation(((addPanel.getWidth() - armadura.getWidth()) / 2), 275);
		armadura.setOpaque(false);
		armadura.setIcon(RBOFF);
		armadura.setSelectedIcon(RBON);
		armadura.setDisabledIcon(RBOFF);

		btadd_add.setSize(100, 25);
		btadd_add.setLocation((addPanel.getWidth() - btadd_add.getWidth()) / 2, 390);
		btadd_add.setBackground(new Color(13, 8, 13));
		btadd_add.setFont(new Font("Courier new", 0, 12));
		btadd_add.setText("Confirmar");
		btadd_add.setForeground(Color.white);

		Warning.setSize(404, 211);
		Warning.setLocation((addPanel.getWidth() - Warning.getWidth()) / 2,
				(addPanel.getHeight() - Warning.getHeight()) / 2);

		fechar_Warning.setSize(25, 25);
		fechar_Warning.setLocation((addPanel.getWidth() - fechar_Warning.getWidth() + 304) / 2,
				(addPanel.getHeight() - fechar_Warning.getHeight() - 123) / 2);
		fechar_Warning.setContentAreaFilled(false);
		
		addPanel.add(fechar_Warning);
		addPanel.add(Warning);
		Warning.setVisible(false);
		fechar_Warning.setVisible(false);

		addPanel.add(btadd_add);
		addPanel.add(nome);
		addPanel.add(preco);
		addPanel.add(comum);
		addPanel.add(raro);
		addPanel.add(unico);
		addPanel.add(arma);
		addPanel.add(armadura);
	}

	JComboBox cb, cb2;
	
	public void updatepanel() {
		updatePanel = new JPanel(null);
		updatePanel.setSize(500, 415);
		updatePanel.setBorder(BorderFactory.createDashedBorder(Color.green));
		updatePanel.setLocation(743, 125);
		updatePanel.setOpaque(false);
		
		cb = new JComboBox();
		int[] vetor = new int[37];
		
		for (int i = 1; i < vetor.length; i++) {
			cb.addItem(i);
		}
		cb.setSize(50,25);
		cb.setLocation(30,27);
		
		nome2 = new JSearchField();
		nome2.setEmptyText("Nome:");
		nome2.setSize(300, 30);
		nome2.setLocation((addPanel.getWidth() - nome2.getWidth()) / 2, 25);
		nome2.setFont(new Font("Courier new", 0, 24));
		nome2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		nome2.setBackground(new Color(16, 14, 18));
		nome2.setForeground(Color.white);
		nome2.setBorder(BorderFactory.createLineBorder(Color.white, 2));

		preco2 = new JSearchField();
		preco2.setEmptyText("R$");
		preco2.setSize(200, 30);
		preco2.setLocation((addPanel.getWidth() - preco2.getWidth()) / 2, 340);
		preco2.setFont(new Font("Courier new", 0, 22));
		preco2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		preco2.setBackground(new Color(16, 14, 18));
		preco2.setForeground(Color.white);
		preco2.setBorder(BorderFactory.createLineBorder(Color.white, 2));

		comum2 = new JRadioButton("Comum");
		comum2.setFont(new Font("Courier new", 0, 18));
		comum2.setForeground(Color.white);

		raro2 = new JRadioButton("Raro");
		raro2.setFont(new Font("Courier new", 0, 18));
		raro2.setForeground(Color.white);

		unico2 = new JRadioButton("Único");
		unico2.setFont(new Font("Courier new", 0, 18));
		unico2.setForeground(Color.white);

		raridade2 = new ButtonGroup();
		raridade2.add(comum2);
		comum2.setSize(150, 50);
		comum2.setLocation(((addPanel.getWidth() - comum2.getWidth()) / 2), 65);
		comum2.setOpaque(false);
		comum2.setIcon(RBOFF);
		comum2.setSelectedIcon(RBON);
		comum2.setDisabledIcon(RBOFF);

		raridade2.add(raro2);
		raro2.setSize(150, 50);
		raro2.setLocation(((addPanel.getWidth() - raro2.getWidth()) / 2), 115);
		raro2.setOpaque(false);
		raro2.setIcon(RBOFF);
		raro2.setSelectedIcon(RBON);
		raro2.setDisabledIcon(RBOFF);

		raridade2.add(unico2);
		unico2.setSize(150, 50);
		unico2.setLocation(((addPanel.getWidth() - unico2.getWidth()) / 2), 165);
		unico2.setOpaque(false);
		unico2.setIcon(RBOFF);
		unico2.setSelectedIcon(RBON);
		unico2.setDisabledIcon(RBOFF);

		arma2 = new JRadioButton("Arma");
		arma2.setFont(new Font("Courier new", 0, 18));
		arma2.setForeground(Color.white);

		armadura2 = new JRadioButton("Armadura");
		armadura2.setFont(new Font("Courier new", 0, 18));
		armadura2.setForeground(Color.white);

		tipo2 = new ButtonGroup();
		tipo2.add(arma2);
		arma2.setSize(150, 50);
		arma2.setLocation(((addPanel.getWidth() - arma2.getWidth()) / 2), 225);
		arma2.setOpaque(false);
		arma2.setIcon(RBOFF);
		arma2.setSelectedIcon(RBON);
		arma2.setDisabledIcon(RBOFF);

		tipo2.add(armadura2);
		armadura2.setSize(150, 50);
		armadura2.setLocation(((addPanel.getWidth() - armadura2.getWidth()) / 2), 275);
		armadura2.setOpaque(false);
		armadura2.setIcon(RBOFF);
		armadura2.setSelectedIcon(RBON);
		armadura2.setDisabledIcon(RBOFF);

		btupdateADD.setSize(100, 25);
		btupdateADD.setLocation((addPanel.getWidth() - btupdateADD.getWidth()) / 2, 390);
		btupdateADD.setBackground(new Color(13, 8, 13));
		btupdateADD.setFont(new Font("Courier new", 0, 12));
		btupdateADD.setText("Alterar");
		btupdateADD.setForeground(Color.white);
		
		
		updatePanel.add(cb);
		updatePanel.add(btupdateADD);
		updatePanel.add(nome2);
		updatePanel.add(preco2);
		updatePanel.add(comum2);
		updatePanel.add(raro2);
		updatePanel.add(unico2);
		updatePanel.add(arma2);
		updatePanel.add(armadura2);
		
	}

	public void removepanel() {

		removePanel = new JPanel(null);
		removePanel.setSize(500, 415);
		removePanel.setBorder(BorderFactory.createDashedBorder(Color.blue));
		removePanel.setLocation(743, 125);
		removePanel.setOpaque(false);
		
		cb2 = new JComboBox();
		int[] vetor = new int[37];
		
		for (int i = 1; i < vetor.length; i++) {
			cb2.addItem(i);
		}
		cb2.setSize(50,25);
		cb2.setLocation(30,27);
		
		nome3 = new JSearchField();
		nome3.setEmptyText("Nome:");
		nome3.setSize(300, 30);
		nome3.setLocation((addPanel.getWidth() - nome3.getWidth()) / 2, 25);
		nome3.setFont(new Font("Courier new", 0, 24));
		nome3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		nome3.setBackground(new Color(16, 14, 18));
		nome3.setForeground(Color.gray);
		nome3.setBorder(BorderFactory.createLineBorder(Color.white, 2));

		preco3 = new JSearchField();
		preco3.setEmptyText("R$");
		preco3.setSize(200, 30);
		preco3.setLocation((addPanel.getWidth() - preco3.getWidth()) / 2, 340);
		preco3.setFont(new Font("Courier new", 0, 22));
		preco3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		preco3.setBackground(new Color(16, 14, 18));
		preco3.setForeground(Color.gray);
		preco3.setBorder(BorderFactory.createLineBorder(Color.white, 2));

		comum3 = new JRadioButton("Comum");
		comum3.setFont(new Font("Courier new", 0, 18));
		comum3.setForeground(Color.white);

		raro3 = new JRadioButton("Raro");
		raro3.setFont(new Font("Courier new", 0, 18));
		raro3.setForeground(Color.white);

		unico3 = new JRadioButton("Único");
		unico3.setFont(new Font("Courier new", 0, 18));
		unico3.setForeground(Color.white);

		raridade3 = new ButtonGroup();
		raridade3.add(comum3);
		comum3.setSize(150, 50);
		comum3.setLocation(((addPanel.getWidth() - comum3.getWidth()) / 2), 65);
		comum3.setOpaque(false);
		comum3.setIcon(RBOFF);
		comum3.setSelectedIcon(RBON);
		comum3.setDisabledIcon(RBOFF);

		raridade3.add(raro3);
		raro3.setSize(150, 50);
		raro3.setLocation(((addPanel.getWidth() - raro3.getWidth()) / 2), 115);
		raro3.setOpaque(false);
		raro3.setIcon(RBOFF);
		raro3.setSelectedIcon(RBON);
		raro3.setDisabledIcon(RBOFF);

		raridade3.add(unico3);
		unico3.setSize(150, 50);
		unico3.setLocation(((addPanel.getWidth() - unico3.getWidth()) / 2), 165);
		unico3.setOpaque(false);
		unico3.setIcon(RBOFF);
		unico3.setSelectedIcon(RBON);
		unico3.setDisabledIcon(RBOFF);

		arma3 = new JRadioButton("Arma");
		arma3.setFont(new Font("Courier new", 0, 18));
		arma3.setForeground(Color.white);

		armadura3 = new JRadioButton("Armadura");
		armadura3.setFont(new Font("Courier new", 0, 18));
		armadura3.setForeground(Color.white);

		tipo3 = new ButtonGroup();
		tipo3.add(arma3);
		arma3.setSize(150, 50);
		arma3.setLocation(((addPanel.getWidth() - arma3.getWidth()) / 2), 225);
		arma3.setOpaque(false);
		arma3.setIcon(RBOFF);
		arma3.setSelectedIcon(RBON);
		arma3.setDisabledIcon(RBOFF);

		tipo.add(armadura3);
		armadura3.setSize(150, 50);
		armadura3.setLocation(((addPanel.getWidth() - armadura3.getWidth()) / 2), 275);
		armadura3.setOpaque(false);
		armadura3.setIcon(RBOFF);
		armadura3.setSelectedIcon(RBON);
		armadura3.setDisabledIcon(RBOFF);

		btremoveADD.setSize(100, 25);
		btremoveADD.setLocation((addPanel.getWidth() - btremoveADD.getWidth()) / 2, 390);
		btremoveADD.setBackground(new Color(13, 8, 13));
		btremoveADD.setFont(new Font("Courier new", 0, 12));
		btremoveADD.setText("Remover");
		btremoveADD.setForeground(Color.white);
		
		nome3.setEditable(false);
		preco3.setEditable(false);
		comum3.setEnabled(false);
		raro3.setEnabled(false);
		unico3.setEnabled(false);
		arma3.setEnabled(false);
		armadura3.setEnabled(false);
		
		removePanel.add(cb2);
		removePanel.add(btremoveADD);
		removePanel.add(nome3);
		removePanel.add(preco3);
		removePanel.add(comum3);
		removePanel.add(raro3);
		removePanel.add(unico3);
		removePanel.add(arma3);
		removePanel.add(armadura3);
	}

	int temp = 0;

	public void defeventos() {

		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f.itens.setId(cb.getSelectedIndex()+1);
				
				if (f.localizar()) {
				
					nome2.setText(f.itens.getNome());
					preco2.setText(f.itens.getPreco());
					
					if(f.itens.getComum().contentEquals("Y")) {
						comum2.setSelected(true);
					}if(f.itens.getRaro().contentEquals("Y")) {
						raro2.setSelected(true);
					}if(f.itens.getUnico().contentEquals("Y")) {
						unico2.setSelected(true);
					}if(f.itens.getArma().contentEquals("Y")) {
						arma2.setSelected(true);
					}if(f.itens.getArmadura().contentEquals("Y")) {
						armadura2.setSelected(true);
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "item não encontrado");
				}
			}
		});
		
		cb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f.itens.setId(cb2.getSelectedIndex()+1);
				
				if (f.localizar()) {
				
					nome3.setText(f.itens.getNome());
					preco3.setText(f.itens.getPreco());
					
					if(f.itens.getComum().contentEquals("Y")) {
						comum3.setSelected(true);
					}if(f.itens.getRaro().contentEquals("Y")) {
						raro3.setSelected(true);
					}if(f.itens.getUnico().contentEquals("Y")) {
						unico3.setSelected(true);
					}if(f.itens.getArma().contentEquals("Y")) {
						arma3.setSelected(true);
					}if(f.itens.getArmadura().contentEquals("Y")) {
						armadura3.setSelected(true);
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "item não encontrado");
				}
			}
		});
		
		
		btupdateADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				f.itens.setNome(nome2.getText());
				f.itens.setPreco(preco2.getText());
				f.itens.setId(cb.getSelectedIndex());
				
				if(comum2.isSelected()) {					
					f.itens.setComum("Y");
					f.itens.setRaro("N");
					f.itens.setUnico("N");
					
				}else if(raro2.isSelected()) {					
					f.itens.setComum("N");
					f.itens.setRaro("Y");
					f.itens.setUnico("N");
					
				}else if(unico2.isSelected()) {
					f.itens.setComum("N");
					f.itens.setRaro("N");
					f.itens.setUnico("Y");
					
				}
				
				if(arma2.isSelected()) {
					f.itens.setArma("Y");
					f.itens.setArmadura("N");
					
				}else if(armadura2.isSelected()) {
					f.itens.setArma("N");
					f.itens.setArmadura("Y");
					
				}				
				JOptionPane.showMessageDialog(null, f.atualizar(itensDAO.ALTERACAO));

			}
		});
		
		btremoveADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				f.itens.setNome(nome3.getText());
				f.itens.setPreco(preco3.getText());
				f.itens.setId(cb2.getSelectedIndex());
				
				if(comum3.isSelected()) {					
					f.itens.setComum("Y");
					f.itens.setRaro("N");
					f.itens.setUnico("N");
					
				}else if(raro3.isSelected()) {					
					f.itens.setComum("N");
					f.itens.setRaro("Y");
					f.itens.setUnico("N");
					
				}else if(unico3.isSelected()) {
					f.itens.setComum("N");
					f.itens.setRaro("N");
					f.itens.setUnico("Y");
					
				}
				
				if(arma3.isSelected()) {
					f.itens.setArma("Y");
					f.itens.setArmadura("N");
					
				}else if(armadura3.isSelected()) {
					f.itens.setArma("N");
					f.itens.setArmadura("Y");
					
				}				
				JOptionPane.showMessageDialog(null, f.atualizar(itensDAO.EXCLUSAO));
				
			}
		});
		
		
		btupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addPanel.setVisible(false);
				updatePanel.setVisible(true);
				removePanel.setVisible(false);
			}
		});
		
		btremove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addPanel.setVisible(false);
				updatePanel.setVisible(false);
				removePanel.setVisible(true);
			
			}
		});
		
		btadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addPanel.setVisible(true);
				updatePanel.setVisible(false);
				removePanel.setVisible(false);
			}
		});
		
		fechar_Warning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Warning.setVisible(false);
				fechar_Warning.setVisible(false);
			}
		});
		
		btadd_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				f.itens.setNome(nome.getText());
				f.itens.setPreco(preco.getText());

				if(comum.isSelected()) {					
					f.itens.setComum("Y");
					f.itens.setRaro("N");
					f.itens.setUnico("N");
					
				}else if(raro.isSelected()) {					
					f.itens.setComum("N");
					f.itens.setRaro("Y");
					f.itens.setUnico("N");
					
				}else if(unico.isSelected()) {
					f.itens.setComum("N");
					f.itens.setRaro("N");
					f.itens.setUnico("Y");
					
				}
				
				if(arma.isSelected()) {
					f.itens.setArma("Y");
					f.itens.setArmadura("N");
					
				}else if(armadura.isSelected()) {
					f.itens.setArma("N");
					f.itens.setArmadura("Y");
					
				}				
				JOptionPane.showMessageDialog(null, f.atualizar(itensDAO.INCLUSAO));
				
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
					updatePanel.setVisible(false);
					removePanel.setVisible(false);
					btadd.setVisible(false);
					btupdate.setVisible(false);
					btremove.setVisible(false);
				}

			}
		});

		fechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					rs.close();
					st.close();
					bd.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
				System.exit(0);

			}
		});

		comprarbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int total = 0;

				for (int i = 0; i < selected.size(); i++) {
					total += precos.get(selected.get(i) + 1);

				}
				if (carteira < total) {
					avisodinheiro.setVisible(true);

				} else {
					for (int j = 0; j < selected.size(); j++) {
						idbanidas.add(selected.get(j));
					}
					avisodinheiro.setVisible(false);
					carteira -= total;
					totaleme.setText(carteira + " R$");
					execute();
					painelitensinit();
					total = 0;
					selected.clear();
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

	
	public static void main(String[] args) {
		MineDungeonsShop frame = new MineDungeonsShop();
		frame.setTitle("MineDungeons Shop");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((sd.width - frame.getWidth()) / 2, (sd.height - frame.getHeight()) / 2);
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Emerald17X24.png"));

	}
}
