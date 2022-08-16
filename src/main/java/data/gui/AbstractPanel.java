package data.gui;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 * @author : Pramod Khalkar
 * @since : 14/08/22, Sun
 * description: This file belongs to visual-data-structures
 **/
public abstract class AbstractPanel extends JPanel {

    public AbstractPanel(String header) {
        setLayout(new BorderLayout());
        JLabel title = new JLabel(header, SwingConstants.CENTER);
        title.setForeground(getForegroundColor());
        title.setFont(new Font(getFontName(), getFontStyle(), getFontSize()));
        title.setBorder(new BevelBorder(BevelBorder.RAISED));
        add(title, BorderLayout.NORTH);
    }

    public int getFontSize() {
        return 20;
    }

    public int getFontStyle() {
        return Font.BOLD;
    }

    public String getFontName() {
        return "Serif";
    }

    public Color getForegroundColor() {
        return Color.BLACK;
    }

    public Object receiveInput() {
        return JOptionPane.showInputDialog(this, "Enter value");
    }

    public void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Something went wrong", ERROR_MESSAGE);
    }
}
