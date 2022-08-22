package data.gui;

import data.utils.UnBalNodeInput;
import java.util.Optional;
import org.javads.tree.Tree;
import org.javads.tree.UnBalanceBinaryTree;

/**
 * @author : Pramod Khalkar
 * @since : 16/08/22, Tue
 * description: This file belongs to visual-data-structures
 **/
public class GeneralBinaryTreeViewer<T extends Comparable<T>> extends TreeViewer<T> {

    public GeneralBinaryTreeViewer(String header, Tree<NodeData<T>> tree) {
        super(header, tree);
    }

    @Override
    public void add() {
        Optional<UnBalNodeInput> input = acceptForUnBalNode();
        try {
            if (input.isPresent()) {
                UnBalanceBinaryTree<NodeData<T>> unBalTree = (UnBalanceBinaryTree<NodeData<T>>) tree;
                unBalTree.insert(new NodeData<T>((T) input.get().getValue(), true),
                        new NodeData<T>((T) input.get().getParent()),
                        input.get().getSide());
                treeComponentPanel.update(tree.getRootNode());
            }
        } catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }
}
