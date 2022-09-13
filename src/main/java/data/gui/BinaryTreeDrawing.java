package data.gui;

import static java.awt.BorderLayout.CENTER;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import org.javads.nlinear.Node;
import org.javads.nlinear.tree.BiNode;

/**
 * @author : Pramod Khalkar
 * @since : 22/08/22, Mon
 * description: This file belongs to visual-data-structures
 **/
public class BinaryTreeDrawing<T extends Comparable<T>> extends TreeDrawing {
    protected mxGraph graph = new mxGraph();
    protected Object parent = graph.getDefaultParent();

    public BinaryTreeDrawing(Node root, TreeEvent treeEvent) {
        super(treeEvent);
        update(root);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        add(graphComponent, CENTER);
    }

    @Override
    public Object drawTree(Node root, int depth, int index) {
        if (root == null) {
            return null;
        }
        int myX = (int) ((CANVAS_WIDTH * (index)) / (Math.pow(2, depth - 1) + 1));
        Object rootVertex = graph.insertVertex(parent, null, root.toString(),
                myX, depth * ROW_HEIGHT + rootY, NODE_SIZE_WIDTH, NODE_SIZE_HEIGHT, applyNodeStyle(root).getStyle());

        Object rightChildVertex = drawTree(((BiNode<NodeData<T>>) root).getRight(), depth + 1, index * 2);

        if (rightChildVertex != null) {// edge
            graph.insertEdge(parent, null, "R", rootVertex, rightChildVertex, EDGE_STYLE);
        }

        Object leftChildVertex = drawTree(((BiNode<NodeData<T>>) root).getLeft(), depth + 1, index * 2 - 1);

        if (leftChildVertex != null) { // edge
            graph.insertEdge(parent, null, "L", rootVertex, leftChildVertex, EDGE_STYLE);
        }
        return rootVertex;
    }

    @Override
    public void update(Node root) {
        graph.getModel().beginUpdate();
        try {
            Object[] cells = graph.getChildCells(parent, true, false);
            graph.removeCells(cells, true);
            drawTree(root, 1, 1);
        } finally {
            graph.getModel().endUpdate();
        }
    }

    @Override
    public NodeStyle applyNodeStyle(Node node) {
        return NodeStyle.defaultNodeStyle(node);
    }
}
