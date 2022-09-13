package data.gui;

import static java.awt.BorderLayout.NORTH;

import data.utils.Helper;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.javads.nlinear.Node;

/**
 * @author : Pramod Khalkar
 * @since : 22/08/22, Mon
 * description: This file belongs to visual-data-structures
 **/
public abstract class TreeDrawing extends JPanel implements ActionListener {
    protected static int CANVAS_WIDTH = 1000;
    protected int rootY = 5;
    protected int NODE_SIZE_WIDTH = 60;
    protected int NODE_SIZE_HEIGHT = 25;
    protected int ROW_HEIGHT = 55;
    protected static final String EDGE_STYLE = "startArrow=none;strokeWidth=1;strokeColor=green";
    private final JButton add, remove, clear;
    private final JTextField inputField;
    private TreeEvent treeEvent;

    public TreeDrawing(TreeEvent treeEvent) {
        this.treeEvent = treeEvent;
        setLayout(new BorderLayout());
        add = new JButton("Add");
        add.addActionListener(this);
        remove = new JButton("Remove");
        remove.addActionListener(this);
        clear = new JButton("Clear");
        clear.addActionListener(this);
        inputField = new JTextField();
        inputField.setToolTipText("Number allowed only");
        inputField.setFocusable(true);
        Box hBox = Box.createHorizontalBox();
        hBox.add(new JLabel("   Input:  "));
        hBox.add(inputField);
        hBox.add(add);
        hBox.add(remove);
        hBox.add(clear);
        add(hBox, NORTH);
    }

    public abstract Object drawTree(Node root, int depth, int index);

    public abstract void update(Node root);

    public abstract NodeStyle applyNodeStyle(Node node);

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == remove) {
            Optional<Long> value = Helper.checkAndGetValidInput(inputField);
            value.ifPresent(treeEvent::remove);
        } else if (source == add) {
            Optional<Long> value = Helper.checkAndGetValidInput(inputField);
            value.ifPresent(treeEvent::add);
        } else if (source == clear) {
            treeEvent.removeAllItems();
        }
        inputField.setFocusable(true);
        inputField.setText("");
    }
}