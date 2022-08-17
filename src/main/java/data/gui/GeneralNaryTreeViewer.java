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
public class GeneralNaryTreeViewer extends TreeViewer {

    public GeneralNaryTreeViewer(String header, Tree<Long> tree) {
        super(header, tree);
    }

    @Override
    public void add() {
        Optional<UnBalNodeInput> input = acceptForUnBalNode(false);
        try {
            if (input.isPresent()) {
                UnBalanceNaryTree<Long> unBalTree = (UnBalanceNaryTree<Long>) tree;
                unBalTree.insert(input.get().getValue(), input.get().getParent());
                treeComponentPanel.update(tree.getRootNode());
            }
        } catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }
}
