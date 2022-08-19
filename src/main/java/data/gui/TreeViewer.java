package data.gui;

import static java.awt.BorderLayout.CENTER;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import data.utils.Helper;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class TreeViewer extends AbstractPanel implements TreeEvent {

    protected final Tree<Long> tree;
    protected final TreeComponentPanel<Long> treeComponentPanel;

    public TreeViewer(String header, Tree<Long> tree) {
        super(header);
        this.tree = tree;
        this.tree.insert(Helper.randomNumbers(10));
        treeComponentPanel = new TreeComponentPanel<>(tree.getRootNode(), this);
        add(treeComponentPanel);
    }

    @Override
    public void add() {
        try {
            tree.insert(acceptInputInLong());
            this.treeComponentPanel.update(tree.getRootNode());
        } catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }

    @Override
    public void remove() {
        tree.remove(acceptInputInLong());
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

        public TreeComponentPanel(Node<T> root, TreeEvent treeEvent) {
            setLayout(new BorderLayout());
            add(new ButtonPanel(treeEvent), BorderLayout.NORTH);
            update(root);
            mxGraphComponent graphComponent = new mxGraphComponent(graph);
            add(graphComponent, CENTER);
        }

        public Object drawTree(Node<T> root, int depth, int index) {
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

        private String applyNodeStyle(Node<T> node) {
            if (node instanceof RedBlackTree.RbNode) {
                RedBlackTree.RbNode<T> rbNode = (RedBlackTree.RbNode<T>) node;
                StringBuilder sb = new StringBuilder("fontColor=white");
                if (rbNode.getColor() == RedBlackTree.Color.RED) {
                    sb.append(";fillColor=red");
                } else {
                    sb.append(";fillColor=black");
                }
                return sb.toString();
            }
            return null;
        }

        public void update(Node<T> root) {
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
}
