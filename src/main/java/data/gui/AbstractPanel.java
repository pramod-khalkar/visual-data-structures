package data.gui;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import data.utils.PriorityNodeInput;
import data.utils.UnBalNodeInput;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import org.javads.tree.Side;

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

    public Long acceptInputInLong() {
        String input = JOptionPane.showInputDialog(this, "Enter value");
        return Long.parseLong(input);
    }

    public Optional<UnBalNodeInput> acceptForUnBalNode() {
        return acceptForUnBalNode(true);
    }

    public Optional<PriorityNodeInput> acceptForPriorityNode() {
        JTextField value = new JTextField();
        JTextField priority = new JTextField();
        final JComponent[] components = new JComponent[] {
                new JLabel("Node value"),
                value,
                new JLabel("Node priority"),
                priority
        };
        int result = JOptionPane.showConfirmDialog(this, components, "Enter value", JOptionPane.DEFAULT_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            PriorityNodeInput priorityNodeInput = new PriorityNodeInput();
            priorityNodeInput.setValue(Long.valueOf(value.getText()));
            priorityNodeInput.setPriority(Integer.valueOf(priority.getText()));
            return Optional.of(priorityNodeInput);
        }
        return Optional.empty();
    }

    public Optional<UnBalNodeInput> acceptForUnBalNode(boolean isSideNeeded) {
        JTextField value = new JTextField();
        JTextField parent = new JTextField();
        JComboBox<String> side = new JComboBox<>(new String[] {"Left", "Right"});
        final List<JComponent> components = new ArrayList<>();
        components.add(new JLabel("Node Value"));
        components.add(value);
        if (isSideNeeded) {
            components.add(new JLabel("Side"));
            components.add(side);
        }
        components.add(new JLabel("Parent Node Value"));
        components.add(parent);
        int result = JOptionPane.showConfirmDialog(this, components.toArray(new JComponent[0]), "Enter value", JOptionPane.DEFAULT_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            UnBalNodeInput unBal = new UnBalNodeInput();
            unBal.setValue(Long.valueOf(value.getText()));
            if (isSideNeeded) {
                if (side.getSelectedItem() == "Left") {
                    unBal.setSide(Side.LEFT);
                } else {
                    unBal.setSide(Side.RIGHT);
                }
            }
            unBal.setParent(Long.valueOf(parent.getText()));
            return Optional.of(unBal);
        }
        return Optional.empty();
    }

    public void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Something went wrong", ERROR_MESSAGE);
    }
}
