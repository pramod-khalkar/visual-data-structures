package data.gui;

import static java.awt.BorderLayout.CENTER;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;
import org.javads.tree.Node;
import org.javads.tree.RedBlackTree;

/**
 * @author : Pramod Khalkar
 * @since : 22/08/22, Mon
 * description: This file belongs to visual-data-structures
 **/
public class BinaryTreeDrawing<T extends Comparable<T>> extends TreeDrawing<T> {
    protected static int CANVAS_WIDTH = 1000;
    protected int rootY = 10;
    protected int NODE_SIZE_WIDTH = 55;
    protected int NODE_SIZE_HEIGHT = 55;
    protected int ROW_HEIGHT = 90;
    protected mxGraph graph = new mxGraph();
    protected Object parent = graph.getDefaultParent();
    protected static final String EDGE_STYLE = "startArrow=none;strokeWidth=1;strokeColor=green";

    public BinaryTreeDrawing(Node<NodeData<T>> root, TreeEvent treeEvent) {
        setLayout(new BorderLayout());
        add(new BinaryTreeViewer.ButtonPanel(treeEvent), BorderLayout.NORTH);
        update(root);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        add(graphComponent, CENTER);
    }

    @Override
    public Object drawTree(Node<NodeData<T>> root, int depth, int index) {
        if (root == null) {
            return null;
        }
        int myX = (int) ((CANVAS_WIDTH * (index)) / (Math.pow(2, depth - 1) + 1));
        Object rootVertex = graph.insertVertex(parent, null, root.toString(),
                myX, depth * ROW_HEIGHT + rootY, NODE_SIZE_WIDTH, NODE_SIZE_HEIGHT, applyNodeStyle(root));

        Object rightChildVertex = drawTree(root.getRight(), depth + 1, index * 2);

        if (rightChildVertex != null) {// edge
            graph.insertEdge(parent, null, "R", rootVertex, rightChildVertex, EDGE_STYLE);
        }

        Object leftChildVertex = drawTree(root.getLeft(), depth + 1, index * 2 - 1);

        if (leftChildVertex != null) { // edge
            graph.insertEdge(parent, null, "L", rootVertex, leftChildVertex, EDGE_STYLE);
        }
        return rootVertex;
    }

    @Override
    public String applyNodeStyle(Node<NodeData<T>> node) {
        NodeStyle style = new NodeStyle();
        style.setShape("ellipse");
        if (node instanceof RedBlackTree.RbNode) {
            RedBlackTree.RbNode<T> rbNode = (RedBlackTree.RbNode<T>) node;
            style.setFontColor("white");
            if (rbNode.getColor() == RedBlackTree.Color.RED) {
                style.setFillColor("red");
            } else {
                style.setFillColor("black");
            }
        }
        if (node.getData().isRecent()) {
            node.getData().setRecent(false);
            style.setStrokeColor("#00ff00");
            style.setStrokeWidth(2);
        }
        return style.getStyle();
    }

    @Override
    public void update(Node<NodeData<T>> root) {
        graph.getModel().beginUpdate();
        try {
            Object[] cells = graph.getChildCells(parent, true, false);
            graph.removeCells(cells, true);
            drawTree(root, 1, 1);
        } finally {
            graph.getModel().endUpdate();
        }
    }

    private static class NodeStyle {
        private String fontColor;
        private String fillColor;
        private String strokeColor;
        private Integer strokeWidth;
        private String shape;

        public String getFontColor() {
            return fontColor;
        }

        public void setFontColor(String fontColor) {
            this.fontColor = fontColor;
        }

        public String getFillColor() {
            return fillColor;
        }

        public void setFillColor(String fillColor) {
            this.fillColor = fillColor;
        }

        public String getStrokeColor() {
            return strokeColor;
        }

        public void setStrokeColor(String strokeColor) {
            this.strokeColor = strokeColor;
        }

        public Integer getStrokeWidth() {
            return strokeWidth;
        }

        public void setStrokeWidth(Integer strokeWidth) {
            this.strokeWidth = strokeWidth;
        }

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public String getStyle() {
            List<String> list = new LinkedList<>();
            if (fontColor != null) {
                list.add("fontColor=" + this.fontColor);
            }
            if (fillColor != null) {
                list.add("fillColor=" + this.fillColor);
            }
            if (strokeColor != null) {
                list.add("strokeColor=" + this.strokeColor);
            }
            if (strokeWidth != null) {
                list.add("strokeWidth=" + this.strokeWidth);
            }
            if (shape != null) {
                list.add("shape=" + this.shape);
            }
            if (list.size() > 0) {
                return String.join(";", list);
            }
            return null;
        }
    }
}
