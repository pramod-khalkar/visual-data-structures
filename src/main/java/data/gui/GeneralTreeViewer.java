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
public class GeneralTreeViewer extends TreeViewer {

    public GeneralTreeViewer(String header, Tree<Long> tree) {
        super(header, tree);
    }

    @Override
    public void add() {
        Optional<UnBalNodeInput> input = acceptForUnBalNode();
        try {
            if (input.isPresent()) {
                UnBalanceBinaryTree<Long> unBalTree = (UnBalanceBinaryTree<Long>) tree;
                unBalTree.insert(input.get().getValue(), input.get().getParent(), input.get().getSide());
                treeComponentPanel.update(tree.getRootNode());
            }
        } catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }
}
