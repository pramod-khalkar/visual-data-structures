package data.gui;

import data.utils.PriorityNodeInput;
import java.util.Optional;
import org.javads.tree.TreapTree;
import org.javads.tree.Tree;

/**
 * @author : Pramod Khalkar
 * @since : 16/08/22, Tue
 * description: This file belongs to visual-data-structures
 **/
public class TreapTreeViewer extends TreeViewer {

    public TreapTreeViewer(String header, Tree<Long> tree) {
        super(header, tree);
    }

    @Override
    public void add() {
        Optional<PriorityNodeInput> input = acceptForPriorityNode();
        try {
            if (input.isPresent()) {
                TreapTree<Long> unBalTree = (TreapTree<Long>) tree;
                unBalTree.insert(input.get().getValue(), input.get().getPriority());
                treeComponentPanel.update(tree.getRootNode());
            }
        } catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }
}
