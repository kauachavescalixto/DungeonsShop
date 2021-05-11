package aloha.dungeons.testes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JViewport;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class two extends JFrame{
	
	JPanel pn = new JPanel();
	JPanel bigpanel = new JPanel();
	private final JFXPanel jfxpanel = new JFXPanel();
	one[] panel = new one[3];
	
	public two() {
		setLayout(null);
		setBackground(new Color(31,30,35));
		
		init();
		pn.setSize(446,696);
		pn.setLayout(new BorderLayout());
		pn.add(jfxpanel, BorderLayout.CENTER);
		
		pn.setBackground(null);
		
		panel[0] = new one(1);
		panel[1] = new one(2);
		panel[2] = new one(3);
		bigpanel.setLocation(446,0);
		bigpanel.setSize(200,600);
		bigpanel.add(panel[0]);
		bigpanel.add(panel[1]);
		bigpanel.add(panel[2]);
		
		add(pn);
		add(bigpanel);
	}
	public void init() {
		
		
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				File file = new File("C:\\DungeonsVideo.m4v");
				MediaPlayer video = new MediaPlayer(new Media(file.toURI().toString()));
				jfxpanel.setScene(new Scene(new Group(new MediaView(video))));
				video.setVolume(0);
				video.setCycleCount(MediaPlayer.INDEFINITE);
				video.play();
				
			}
		});
	}
	
	public static void main(String[] args) {
		
		two frame = new two();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700,700);
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((sd.width - frame.getWidth()) / 2, (sd.height - frame.getHeight()) / 2);
		frame.setVisible(true);
		
	}
}
