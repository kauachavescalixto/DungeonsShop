package dungeons.shop.externalClasses;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author Victor Stafusa
 */
public class JSearchField extends JTextField {

    private static final long serialVersionUID = 1L;
    private final JLabel emptyTextLabel;

    public JSearchField() {
        this.emptyTextLabel = new JLabel();
        emptyTextLabel.setFont(new Font("courier new",0,23));
        emptyTextLabel.setForeground(new Color(255,255,255,100));
        this.setLayout(new BorderLayout());
        this.add(emptyTextLabel, BorderLayout.WEST);
       /* DocumentListener listener = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                emptyTextLabel.setVisible(getText().isEmpty());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                emptyTextLabel.setVisible(getText().isEmpty());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                emptyTextLabel.setVisible(getText().isEmpty());
            }
        };
        this.getDocument().addDocumentListener(listener);*/
    }

    public String getEmptyText() {
        return emptyTextLabel.getText();
    }

    public void setEmptyText(String emptyText) {
        emptyTextLabel.setText(emptyText);
    }
    // Você pode usar este método se precisar fazer alterações no JLabel.
    public JLabel getEmptyTextLabel() {
        return emptyTextLabel;
    }

 
}