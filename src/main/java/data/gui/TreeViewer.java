package data.gui;

import static java.awt.BorderLayout.CENTER;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import data.utils.Helper;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.javads.tree.Node;
import org.javads.tree.RedBlackTree;
import org.javads.tree.Tree;

/**
 * @author : Pramod Khalkar
 * @since : 14/08/22, Sun
 * description: This file belongs to visual-data-structures
 **/
public class TreeViewer<T extends Comparable<T>> extends AbstractPanel implements TreeEvent {

    protected final Tree<NodeData<T>> tree;
    protected final TreeComponentPanel<T> treeComponentPanel;

    public TreeViewer(String header, Tree<NodeData<T>> tree) {
        super(header);
        this.tree = tree;
        T[] numbers = (T[]) Helper.randomNumbers(10);
        this.tree.insert(Arrays.stream(numbers).map(NodeData::new).toArray(NodeData[]::new));
        treeComponentPanel = new TreeComponentPanel<>(tree.getRootNode(), this);
        add(treeComponentPanel);
    }

    @Override
    public void add() {
        try {
            T input = (T) acceptInputInLong();
            tree.insert(new NodeData<>(input, true));
            this.treeComponentPanel.update(tree.getRootNode());
        } catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }

    @Override
    public void remove() {
        T input = (T) acceptInputInLong();
        tree.remove(new NodeData<>(input));
        this.treeComponentPanel.update(tree.getRootNode());
    }

    @Override
    public void removeAllItems() {
        tree.removeAll();
        this.treeComponentPanel.update(tree.getRootNode());
    }

    static class TreeComponentPanel<T extends Comparable<T>> extends JPanel {
        static private int CANVAS_WIDTH = 1000;
        private int rootY = 10;
        private int NODE_SIZE_WIDTH = 60;
        private int NODE_SIZE_HEIGHT = 30;
        private int ROW_HEIGHT = 60;
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        public TreeComponentPanel(Node<NodeData<T>> root, TreeEvent treeEvent) {
            setLayout(new BorderLayout());
            add(new ButtonPanel(treeEvent), BorderLayout.NORTH);
            update(root);
            mxGraphComponent graphComponent = new mxGraphComponent(graph);
            add(graphComponent, CENTER);
        }

        public Object drawTree(Node<NodeData<T>> root, int depth, int index) {
            if (root == null) {
                return null;
            }
            int myX = (int) ((CANVAS_WIDTH * (index)) / (Math.pow(2, depth - 1) + 1));
            Object rootVertex = graph.insertVertex(parent, null, root.toString(),
                    myX, depth * ROW_HEIGHT + rootY, NODE_SIZE_WIDTH, NODE_SIZE_HEIGHT, applyNodeStyle(root));

            Object rightChildVertex = drawTree(root.getRight(), depth + 1,
                    index * 2);

            if (rightChildVertex != null) {// edge
                graph.insertEdge(parent, null, "R", rootVertex, rightChildVertex,
                        "startArrow=none;endArrow=none;strokeWidth=1;strokeColor=green");
            }

            Object leftChildVertex = drawTree(root.getLeft(), depth + 1,
                    index * 2 - 1);

            if (leftChildVertex != null) { // edge
                graph.insertEdge(parent, null, "L", rootVertex, leftChildVertex,
                        "startArrow=none;endArrow=none;strokeWidth=1;strokeColor=green");
            }
            return rootVertex;
        }

        private String applyNodeStyle(Node<NodeData<T>> node) {
            NodeStyle style = new NodeStyle();
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
    }

    static class ButtonPanel extends JPanel implements ActionListener {
        private final JButton add, remove, removeAll;
        private final TreeEvent treeEvent;

        public ButtonPanel(TreeEvent treeEvent) {
            this.treeEvent = treeEvent;
            setLayout(new FlowLayout());
            add = new JButton("Add");
            add.addActionListener(this);
            add(add);
            remove = new JButton("Remove");
            remove.addActionListener(this);
            add(remove);
            removeAll = new JButton("Remove All");
            removeAll.addActionListener(this);
            add(removeAll);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == remove) {
                treeEvent.remove();
            } else if (source == add) {
                treeEvent.add();
            } else if (source == removeAll) {
                treeEvent.removeAllItems();
            }
        }
    }

    private static class NodeStyle {
        private String fontColor;
        private String fillColor;
        private String strokeColor;
        private Integer strokeWidth;

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
            if (list.size() > 0) {
                return String.join(";", list);
            }
            return null;
        }
    }
}
