package data.gui;

import org.javads.nlinear.tree.Tree;

/**
 * @author : Pramod Khalkar
 * @since : 29/08/22, Mon
 * description: This file belongs to visual-data-structures
 **/
public class BTreeViewer<T extends Comparable<T>> extends TreeViewer<T> {

    public BTreeViewer(String header, Tree<NodeData<T>> tree) {
        super(header, tree);
    }

    @Override
    protected TreeDrawing getTreeDrawer() {
        return new BTreeDrawing<>(tree.getRootNode(), this);
    }
}
