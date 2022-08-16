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
import org.javads.tree.Tree;

/**
 * @author : Pramod Khalkar
 * @since : 14/08/22, Sun
 * description: This file belongs to visual-data-structures
 **/
public class TreeViewer extends AbstractPanel implements TreeEvent {

    private final Tree<String> tree;
    private final TreeComponentPanel<String> treeComponentPanel;

    public TreeViewer(String header, Tree<String> tree) {
        super(header);
        this.tree = tree;
        this.tree.insert(Helper.randomNumbers());
        treeComponentPanel = new TreeComponentPanel<>(tree.getRootNode(), this);
        add(treeComponentPanel);
    }

    @Override
    public void add() {
        tree.insert((String) receiveInput());
        this.treeComponentPanel.update(tree.getRootNode());
    }

    @Override
    public void remove() {
        tree.remove((String) receiveInput());
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
        private int NODE_SIZE = 25;
        private int ROW_HEIGHT = 50;
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
            Object rootVertex = graph.insertVertex(parent, null, root.getData(),
                    myX, depth * ROW_HEIGHT + rootY, NODE_SIZE, NODE_SIZE);

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
