package dungeons.shop.panels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Warnings {
	
	private final int MONEY = 0;
	
	JLabel avisodinheiro;

	
	public Warnings(int warning) {
		avisodinheiro = new JLabel(new ImageIcon(getClass().getResource("avisodinheiro232X38.png")));
		
		
		if(warning == MONEY) {
			
		}
	
	}
	
}
