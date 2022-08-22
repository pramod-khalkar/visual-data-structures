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
public class TreapTreeViewer<T extends Comparable<T>> extends TreeViewer<T> {

    public TreapTreeViewer(String header, Tree<NodeData<T>> tree) {
        super(header, tree);
    }

    @Override
    public void add() {
        Optional<PriorityNodeInput> input = acceptForPriorityNode();
        try {
            if (input.isPresent()) {
                TreapTree<NodeData<T>> unBalTree = (TreapTree<NodeData<T>>) tree;
                unBalTree.insert(new NodeData<T>((T) input.get().getValue(), true),
                        input.get().getPriority());
                treeComponentPanel.update(tree.getRootNode());
            }
        } catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }
}
