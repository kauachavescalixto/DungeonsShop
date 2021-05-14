package Dshop;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.ls.LSInput;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public class MineDungeonsShop extends JFrame{

	JPanel pnVideo = new JPanel();
	private final JFXPanel jfxpanel = new JFXPanel();
	
	ArrayList<ItemPanel> panel = new ArrayList<ItemPanel>();
	
	JPanel painelitens;
	JScrollPane scroll;

	
	JLabel resultados = new JLabel("");
	JLabel totaleme = new JLabel("");
	int Nresultados = 0;
	int carteira = (int)(Math.random()*5000);
	
	JLabel bg = new JLabel(new ImageIcon(getClass().getResource("BG.png")));
	JLabel avisodinheiro = new JLabel(new ImageIcon(getClass().getResource("avisodinheiro232X38.png")));
	
	BarPanel[] bp = new BarPanel[7];
	
	
	JPanel bpPanel;
	
	JButton comprarbt = new JButton("");
	
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
	
	public MineDungeonsShop() {
		setLayout(null);
		setBackground(new Color(31,30,35));
		setSize(1290,750);
		
		GridLayout layoult = new GridLayout(0,2);
		layoult.setHgap(10);
		layoult.setVgap(25);
		painelitens = new JPanel(layoult);
		painelitens.setOpaque(false);
		
		bd = new bd();
		if(!bd.getConnection()){
			JOptionPane.showMessageDialog(null,"Falha na  conexão!");
			System.exit(0);
		}
		
		execute();
		init();
		videoinstance();
		defeventos();
		
	}
	
	public void escrever() {
		
		texto="select * from itens2";
		
		for (int i = 0; i < condicoesdobd.size(); i++) {
			
				if(i>0) {
					texto+=" and "+condicoesdobd.get(i);
				}else {
					texto+=" where "+condicoesdobd.get(i);
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
	
	public void chamarbancodedados() {
		
		try{
			int qtdeColunas = rs.getMetaData().getColumnCount();
			
			IDdositensdobd.clear();
			while(rs.next()){
				try{
					String[] dados = new String[qtdeColunas];
					for(int i = 1; i<=qtdeColunas; i++){
						dados[i-1] = rs.getString(i);
					}
					IDdositensdobd.add(Integer.parseInt(rs.getString("id")));
					precos.add(Integer.parseInt(rs.getString("preco")));
					nomes.add(rs.getString("nome"));
					listacomuns.add(rs.getString("comum"));
					listararos.add(rs.getString("raro"));
					listaunicos.add(rs.getString("unico"));
				}catch (SQLException erro){
					System.out.println(erro.toString());
				}

			}

			
			for (int i = 0; i < idbanidas.size(); i++) {
				for (int j = 0; j < IDdositensdobd.size(); j++) {
					if(IDdositensdobd.get(j)==(idbanidas.get(i)+2)) {
						
						IDdositensdobd.remove(j);
					}
				}
			}
					
			Nresultados = IDdositensdobd.size();
			resultados.setText(Nresultados+" Resultados");
			
			
		}catch (Exception erro){
			JOptionPane.showMessageDialog(null,"Comando Inválido"+erro.toString());
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
		pnVideo.setSize(440,556);
		pnVideo.setLayout(new BorderLayout());
		pnVideo.add(jfxpanel, BorderLayout.CENTER);
		pnVideo.setBackground(null);
		pnVideo.setLocation(0,98);
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
		
		avisodinheiro.setBounds(895,590,232,38);
		add(avisodinheiro);
		avisodinheiro.setVisible(false);
		
		painelitens.setBounds(465,130,250,525);
		//painelitens.setBorder(BorderFactory.createDashedBorder(Color.red));
		painelitensinit();
		
		
		scroll = new JScrollPane(painelitens);
		scroll.setBounds(465,130,250,525);
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		scroll.setBackground(new Color(0,0,0,0));
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		scroll.setBorder(null);
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		
		add(scroll);
		
		
		resultados.setSize(200,25);
		resultados.setLocation(465,105);
		resultados.setForeground(new Color(255,255,255,150));
		resultados.setFont(new Font("courier new",Font.ITALIC,14));
		add(resultados);
		
		totaleme.setText(carteira+" R$");
		totaleme.setSize(200,25);
		totaleme.setLocation(75,58);
		totaleme.setForeground(Color.white);
		totaleme.setFont(new Font("courier new",Font.ITALIC,20));
		add(totaleme);
		
		fechar.setSize(40,40);
		fechar.setLocation(1200,40);
		fechar.setContentAreaFilled(false);
		add(fechar);
		
		comprarbt.setSize(328,75);
		comprarbt.setLocation(844,613);
		comprarbt.setContentAreaFilled(false);
		add(comprarbt);
		
		bpPanel = new JPanel(new GridLayout(0,1));
		bpPanel.setSize(500,500);
		bpPanel.setLocation(743,90);
		bpPanel.setBackground(new Color(0,0,0,0));
		bpPanel.setOpaque(false);
		//bpPanel.setBorder(BorderFactory.createDashedBorder(Color.red));
		for (int i = 0; i < bp.length; i++) {
			bp[i] = new BarPanel(i);
			bp[i].setBackground(new Color(0,0,0,0));
			bpPanel.add(bp[i]);
		}
		
		
		
		add(bpPanel);
		bg.setSize(1280,720);
		add(bg);
		add(pnVideo);
	}	
	
	
	
	public void defeventos() {

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
					total+=precos.get(selected.get(i)+1);
					
				}
				if(carteira<total) {
					avisodinheiro.setVisible(true);
					
				}else {
					for (int j = 0; j < selected.size(); j++) {
						idbanidas.add(selected.get(j));
					}
					avisodinheiro.setVisible(false);
					carteira-=total;
					totaleme.setText(carteira+" R$");
					execute();
					painelitensinit();
					total=0;
					selected.clear();
				}
				
			}
		});
		
		bp[0].check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(bp[0].check.isSelected()) {
										
					condicoesdobd.add("comum=\"Y\"");
					
					execute();
					painelitensinit();
					
				}else{
					for (int i = 0; i < condicoesdobd.size(); i++) {
						if(condicoesdobd.get(i).contentEquals("comum=\"Y\"")) {
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
				if(bp[1].check.isSelected()) {

					
					condicoesdobd.add("raro=\"Y\"");
					execute();
					painelitensinit();

				}else{
					for (int i = 0; i < condicoesdobd.size(); i++) {
						if(condicoesdobd.get(i).contentEquals("raro=\"Y\"")) {
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
				if(bp[2].check.isSelected()) {

					
					condicoesdobd.add("unico=\"Y\"");
					execute();
					painelitensinit();

				}else{
					for (int i = 0; i < condicoesdobd.size(); i++) {
						if(condicoesdobd.get(i).contentEquals("unico=\"Y\"")) {
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
				if(bp[3].check.isSelected()) {

					
					condicoesdobd.add("arma=\"Y\"");
					execute();
					painelitensinit();

				}else{
					for (int i = 0; i < condicoesdobd.size(); i++) {
						if(condicoesdobd.get(i).contentEquals("arma=\"Y\"")) {
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
				if(bp[4].check.isSelected()) {

					
					condicoesdobd.add("armadura=\"Y\"");
					execute();
					painelitensinit();

				}else{
					for (int i = 0; i < condicoesdobd.size(); i++) {
						if(condicoesdobd.get(i).contentEquals("armadura=\"Y\"")) {
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
				if(bp[5].check.isSelected()) {

					
					condicoesdobd.add("preco>500");
					execute();
					painelitensinit();

				}else{
					for (int i = 0; i < condicoesdobd.size(); i++) {
						if(condicoesdobd.get(i).contentEquals("preco>500")) {
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
				if(bp[6].check.isSelected()) {

					
					condicoesdobd.add("preco<500");
					execute();
					painelitensinit();

				}else{
					for (int i = 0; i < condicoesdobd.size(); i++) {
						if(condicoesdobd.get(i).contentEquals("preco<500")) {
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((sd.width - frame.getWidth()) / 2, (sd.height - frame.getHeight()) / 2);
		frame.setVisible(true);
		
	}
}
