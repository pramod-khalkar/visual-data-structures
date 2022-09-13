package data.gui;

import data.utils.Helper;
import java.util.Arrays;
import org.javads.nlinear.tree.Tree;

/**
 * @author : Pramod Khalkar
 * @since : 14/08/22, Sun
 * description: This file belongs to visual-data-structures
 **/
public class TreeViewer<T extends Comparable<T>> extends TitlePanel implements TreeEvent {
    protected final Tree<NodeData<T>> tree;
    protected TreeDrawing treeDrawing;

    public TreeViewer(String header, Tree<NodeData<T>> tree) {
        super(header);
        this.tree = tree;
        T[] numbers = (T[]) Helper.randomNumbers(10);
        this.tree.insert(Arrays.stream(numbers).map(NodeData::new).toArray(NodeData[]::new));
        treeDrawing = getTreeDrawer();
        add(treeDrawing);
    }

    /**
     * @return Returns drawing object to change drawing, override this in sub class if want to change
     */
    protected TreeDrawing getTreeDrawer() {
        return new BinaryTreeDrawing<>(tree.getRootNode(), this);
    }

    @Override
    public void add(Long item) {
        try {
            tree.insert((NodeData<T>) new NodeData<>(item, true));
            this.treeDrawing.update(tree.getRootNode());
        } catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }

    @Override
    public void remove(Long item) {
        tree.remove((NodeData<T>) new NodeData<>(item));
        this.treeDrawing.update(tree.getRootNode());
    }

    @Override
    public void removeAllItems() {
        tree.removeAll();
        this.treeDrawing.update(tree.getRootNode());
    }
}
