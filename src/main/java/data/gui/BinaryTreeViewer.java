package data.gui;

import data.utils.Helper;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.javads.tree.Tree;

/**
 * @author : Pramod Khalkar
 * @since : 14/08/22, Sun
 * description: This file belongs to visual-data-structures
 **/
public class BinaryTreeViewer<T extends Comparable<T>> extends TitlePanel implements TreeEvent {

    protected final Tree<NodeData<T>> tree;
    protected final TreeDrawing<T> treeDrawing;

    public BinaryTreeViewer(String header, Tree<NodeData<T>> tree) {
        super(header);
        this.tree = tree;
        T[] numbers = (T[]) Helper.randomNumbers(10);
        this.tree.insert(Arrays.stream(numbers).map(NodeData::new).toArray(NodeData[]::new));
        treeDrawing = getTreePanel();
        add(treeDrawing);
    }

    protected TreeDrawing<T> getTreePanel() {
        return new BinaryTreeDrawing<>(tree.getRootNode(), this);
    }

    @Override
    public void add() {
        try {
            T input = (T) acceptInputInLong();
            tree.insert(new NodeData<>(input, true));
            this.treeDrawing.update(tree.getRootNode());
        } catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }

    @Override
    public void remove() {
        T input = (T) acceptInputInLong();
        tree.remove(new NodeData<>(input));
        this.treeDrawing.update(tree.getRootNode());
    }

    @Override
    public void removeAllItems() {
        tree.removeAll();
        this.treeDrawing.update(tree.getRootNode());
    }

    static class ButtonPanel extends JPanel implements ActionListener {
        private final JButton add, remove, removeAll;
        private final TreeEvent treeEvent;

        public ButtonPanel(TreeEvent treeEvent) {
            this.treeEvent = treeEvent;
            setLayout(new FlowLayout());
            add = new JButton("Add");
            add.addActionListener(this);
            add(add);
            remove = new JButton("Remove");
            remove.addActionListener(this);
            add(remove);
            removeAll = new JButton("Remove All");
            removeAll.addActionListener(this);
            add(removeAll);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == remove) {
                treeEvent.remove();
            } else if (source == add) {
                treeEvent.add();
            } else if (source == removeAll) {
                treeEvent.removeAllItems();
            }
        }
    }
}
