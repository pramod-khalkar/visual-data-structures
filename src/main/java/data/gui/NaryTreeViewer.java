package data.gui;

import data.utils.UnBalNodeInput;
import java.util.Optional;
import org.javads.nlinear.tree.Tree;
import org.javads.nlinear.tree.UnBalanceNaryTree;

/**
 * @author : Pramod Khalkar
 * @since : 16/08/22, Tue
 * description: This file belongs to visual-data-structures
 **/
public class NaryTreeViewer<T extends Comparable<T>> extends TreeViewer<T> {

    public NaryTreeViewer(String header, Tree<NodeData<T>> tree) {
        super(header, tree);
    }

    @Override
    protected TreeDrawing getTreeDrawer() {
        return new NaryTreeDrawing<>(tree.getRootNode(), this);
    }

    @Override
    public void add(Long item) {
        Optional<UnBalNodeInput> input = acceptForUnBalNode(String.valueOf(item), false);
        try {
            if (input.isPresent()) {
                UnBalanceNaryTree<NodeData<T>> unBalTree = (UnBalanceNaryTree<NodeData<T>>) tree;
                NodeData<T> element = new NodeData<T>((T) input.get().getValue(), true);
                NodeData<T> parent = new NodeData<T>((T) input.get().getParent());
                unBalTree.insert(element, parent);
                treeDrawing.update(tree.getRootNode());
            }
        } catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }
}
