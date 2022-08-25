package data.gui;

import data.utils.UnBalNodeInput;
import java.util.Optional;
import org.javads.tree.Tree;
import org.javads.tree.UnBalanceNaryTree;

/**
 * @author : Pramod Khalkar
 * @since : 16/08/22, Tue
 * description: This file belongs to visual-data-structures
 **/
public class GenNaryTreeViewer<T extends Comparable<T>> extends BinaryTreeViewer<T> {

    public GenNaryTreeViewer(String header, Tree<NodeData<T>> tree) {
        super(header, tree);
    }

    @Override
    public void add() {
        Optional<UnBalNodeInput> input = acceptForUnBalNode(false);
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

    @Override
    protected TreeDrawing<T> getTreePanel() {
        return new GenNaryTreeDrawing<>(tree.getRootNode(), this);
    }
}
