package data.gui;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.javads.tree.Node;

/**
 * @author : Pramod Khalkar
 * @since : 22/08/22, Mon
 * description: This file belongs to visual-data-structures
 **/
public class GenNaryTreeDrawing<T extends Comparable<T>> extends BinaryTreeDrawing<T> {

    public GenNaryTreeDrawing(Node<NodeData<T>> root, TreeEvent treeEvent) {
        super(root, treeEvent);
    }

    @Override
    public Object drawTree(Node<NodeData<T>> root, int depth, int index) {
        if (root != null) {
            int myX = (int) ((CANVAS_WIDTH * (index)) / (Math.pow(2, depth - 1) + 1));
            Object rootVertex = graph.insertVertex(parent, null, root.toString(),
                    myX, depth * ROW_HEIGHT + rootY, NODE_SIZE_WIDTH, NODE_SIZE_HEIGHT, applyNodeStyle(root));
            List<Node<NodeData<T>>> children = getChildren(root);
            drawNTree(root, rootVertex, depth, children.size(), 10);
            return rootVertex;
        }
        return null;
    }

    private void drawNTree(Node<NodeData<T>> root, Object childParent, int depth, int index, int myX) {
        if (root == null) {
            return;
        }
        depth += 1;
        List<Node<NodeData<T>>> children = getChildren(root);
        int grandChildCount = children.stream().map(n -> getChildren(n).size()).mapToInt(Integer::intValue).sum();
        index = Math.abs(index - children.size());
        int slice = children.size() > 0 ? CANVAS_WIDTH / (children.size() + index) : 0;
        for (int i = 0; i < children.size(); i++) {
            int prevGrandChildCount =
                    children.subList(0, i != 0 ? i - 1 : 0).stream().map(n -> getChildren(n).size()).mapToInt(Integer::intValue).sum();
            Object child = graph.insertVertex(parent, null, children.get(i).toString(),
                    myX, depth * ROW_HEIGHT + rootY, NODE_SIZE_WIDTH, NODE_SIZE_HEIGHT, applyNodeStyle(children.get(i)));
            myX += slice;
            if (child != null) {
                graph.insertEdge(parent, null, "", childParent, child, EDGE_STYLE);
            }
            drawNTree(children.get(i), child, depth + 1, grandChildCount, prevGrandChildCount * NODE_SIZE_WIDTH);
        }
    }

    private List<Node<NodeData<T>>> getChildren(Node<NodeData<T>> parent) {
        if (parent != null && parent.getLeft() != null) {
            List<Node<NodeData<T>>> list = new LinkedList<>();
            Node<NodeData<T>> child = parent.getLeft();
            while (child != null) {
                list.add(child);
                child = child.getRight();
            }
            return list;
        }
        return Collections.emptyList();
    }
}
