package data.gui;

import javax.swing.JPanel;
import org.javads.tree.Node;

/**
 * @author : Pramod Khalkar
 * @since : 22/08/22, Mon
 * description: This file belongs to visual-data-structures
 **/
public abstract class TreeDrawing<T extends Comparable<T>> extends JPanel {
    abstract Object drawTree(Node<NodeData<T>> root, int depth, int index);

    abstract String applyNodeStyle(Node<NodeData<T>> node);

    abstract void update(Node<NodeData<T>> root);
}
