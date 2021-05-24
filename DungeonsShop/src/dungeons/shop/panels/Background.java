package dungeons.shop.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dungeons.shop.bd.CallDatabase;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Background extends JPanel{

	JPanel pnVideo = new JPanel();
	private final JFXPanel jfxpanel = new JFXPanel();

	JLabel resultados = new JLabel("");
	JLabel totaleme = new JLabel("");
	int Nresultados = 0;
	int carteira = (int) (Math.random() * 10000);

	JLabel bg = new JLabel(new ImageIcon(getClass().getResource("BG.png")));

	ImageIcon IMGcomprarbt = new ImageIcon(getClass().getResource("ComprarBt336X84.png"));
	JButton comprarbt = new JButton("", IMGcomprarbt);

	JButton fechar = new JButton("");
	
	Warnings avisos;
	
	public Background(int w, int h) {
		setPreferredSize(new Dimension(w , h));
		videoinstance();
		defeventos();
		init();
		
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
	
	public void init() {
		
		setLayout(null);
		
		
		
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
		add(pnVideo);
		
		bg.setSize(1280, 720);
		add(bg);
	}

	public void defeventos(){
		fechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
				CallDatabase.DatabaseClose();
				System.exit(0);

			}
		});

		comprarbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int total = 0;

				for (int i = 0; i < CallDatabase.selected.size(); i++) {
					total += CallDatabase.precos.get(CallDatabase.selected.get(i) + 1);

				}
				if (carteira < total) {
					avisos = new Warnings(0);
				} else {
					for (int j = 0; j < CallDatabase.selected.size(); j++) {
						CallDatabase.idbanidas.add(CallDatabase.selected.get(j));
					}
					avisos = new Warnings(-1);
					carteira -= total;
					totaleme.setText(carteira + " R$");
					CallDatabase.execute();
					PanelItens.painelitensinit();
					total = 0;
					selected.clear();
				}

			}
		});
	}

}
