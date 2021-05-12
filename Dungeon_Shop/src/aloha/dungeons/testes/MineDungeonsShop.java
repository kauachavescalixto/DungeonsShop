package aloha.dungeons.testes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MineDungeonsShop extends JFrame{
	
	int qntd = 10;
	
	JPanel pnVideo = new JPanel();
	private final JFXPanel jfxpanel = new JFXPanel();
	
	ItemPanel[] panel = new ItemPanel[qntd];
	JPanel bigpanel;
	JScrollPane scroll;
	
	JLabel bg = new JLabel(new ImageIcon(getClass().getResource("BG.png")));
	
	String[] conditions = new String[]{
			"Somente Comuns","Somente Raros","Somente Únicos","Somente Armas", "Somente Armaduras"
		};
	
	BarPanel[] bp = new BarPanel[5];
	JPanel bpPanel;
	
	JButton comprarbt = new JButton("");
	
	JButton fechar = new JButton("");
	
	public static ArrayList<Integer> selected = new ArrayList<>();
	
	public MineDungeonsShop() {
		setLayout(null);
		setBackground(new Color(31,30,35));
		init();
		videoinstance();
		defeventos();
		
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
	public void init() {
		
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
			bp[i] = new BarPanel(conditions[i]);
			bp[i].setBackground(new Color(0,0,0,0));
			bpPanel.add(bp[i]);
		}
		
		
		GridLayout layoult = new GridLayout(0,2);
		layoult.setHgap(10);
		layoult.setVgap(25);
		bigpanel = new JPanel(layoult);
		bigpanel.setOpaque(false);
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new ItemPanel(i);
			
			panel[i].setSize(111,180);
			
			bigpanel.setBackground(new Color(0,0,0,0));
			bigpanel.add(panel[i]);
		}
		scroll = new JScrollPane(bigpanel);
		scroll.setLocation(465,130);
		scroll.setSize(270,525);
		//scroll.setBorder(BorderFactory.createDashedBorder(Color.green));
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		scroll.setBackground(new Color(0,0,0,0));
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		scroll.getVerticalScrollBar().setUI(new CustomizarScroll());
		scroll.setBorder(null);
		add(scroll);
		add(bpPanel);
		bg.setSize(1280,720);
		add(bg);
		add(pnVideo);
	}
	public void defeventos() {
		comprarbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < selected.size(); i++) {
					panel[selected.get(i)].setVisible(false);
				}
				selected.clear();
			}
		});
		
		fechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}
	
	
	public class CustomizarScroll extends BasicScrollBarUI {
		@Override
        protected JButton createDecreaseButton(int orientation) {
            return new InvisibleScrollBarButton();
        }
        @Override
        protected JButton createIncreaseButton(int orientation) {
            return new InvisibleScrollBarButton();
        }
        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = new Color(31,30,35);
        }
	}
	private static class InvisibleScrollBarButton extends JButton {
         private InvisibleScrollBarButton() {
             setFocusable(true);
             setFocusPainted(false);
             setBorderPainted(false);
             setBorder(BorderFactory.createEmptyBorder());
         }
     }
	
	public static void main(String[] args) {
		
		MineDungeonsShop frame = new MineDungeonsShop();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1290,750);
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((sd.width - frame.getWidth()) / 2, (sd.height - frame.getHeight()) / 2);
		frame.setVisible(true);
		
	}
}
