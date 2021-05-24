package dungeons.shop.panels;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DatabasePanels extends JPanel {
	
	AddPanel addpanel;
	UpdatePanel updatepanel;
	RemovePanel removepanel;
	
	ImageIcon imgEdit = new ImageIcon(getClass().getResource("editbt50X50.png"));
	ImageIcon imgAdd = new ImageIcon(getClass().getResource("addbt50X50.png"));
	ImageIcon imgUpdate = new ImageIcon(getClass().getResource("updatebt50X50.png"));
	ImageIcon imgRemove = new ImageIcon(getClass().getResource("removebt50X50.png"));

	JButton btedit = new JButton("", imgEdit);
	JButton btadd = new JButton("", imgAdd);
	JButton btupdate = new JButton("", imgUpdate);
	JButton btremove = new JButton("", imgRemove);
	
	public DatabasePanels() {
		addpanel = new AddPanel();
		updatepanel = new UpdatePanel();
		removepanel = new RemovePanel();
		
	}
	
}
