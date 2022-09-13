package data.gui;

import data.utils.UnBalNodeInput;
import java.util.Optional;
import org.javads.nlinear.tree.Tree;
import org.javads.nlinear.tree.UnBalanceBiTree;

/**
 * @author : Pramod Khalkar
 * @since : 16/08/22, Tue
 * description: This file belongs to visual-data-structures
 **/
public class BinaryTreeViewer<T extends Comparable<T>> extends TreeViewer<T> {

    public BinaryTreeViewer(String header, Tree<NodeData<T>> tree) {
        super(header, tree);
    }

    @Override
    public void add(Long item) {
        Optional<UnBalNodeInput> input = acceptForUnBalNode(String.valueOf(item));
        try {
            if (input.isPresent()) {
                UnBalanceBiTree<NodeData<T>> unBalTree = (UnBalanceBiTree<NodeData<T>>) tree;
                unBalTree.insert(new NodeData<T>((T) input.get().getValue(), true),
                        new NodeData<T>((T) input.get().getParent()),
                        input.get().getSide());
                treeDrawing.update(tree.getRootNode());
            }
        } catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }
}
