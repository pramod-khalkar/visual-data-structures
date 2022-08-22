package data.gui;

import data.utils.Helper;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.javads.tree.AvlTree;
import org.javads.tree.BSTree;
import org.javads.tree.GeneralBinaryTree;
import org.javads.tree.GeneralNaryTree;
import org.javads.tree.RedBlackTree;
import org.javads.tree.SplayTree;
import org.javads.tree.Treap;

/**
 * @author : Pramod Khalkar
 * @since : 14/08/22, Sun
 * description: This file belongs to visual-data-structures
 **/
public class LauncherWindow extends JFrame implements ActionListener {

    private JMenu dataStruct, treeMenu, graphMenu;
    private JMenuItem avlTreeMItem, bsTreeMItem, genTreeMItem, stackMItem, queueMItem,
            genNTreeMItem, redTreeMItem, splayTreeMItem, treapMItem;
    private JMenuBar mb;
    private CardLayout crd;
    private Container cPane;
    private final StackViewer stackWindow = new StackViewer();
    private final QueueViewer queueWindow = new QueueViewer();

    public LauncherWindow() {
        SwingUtilities.invokeLater(() -> {
            setTitle("VISUAL DATA STRUCTURES");
            setMenuBar();
            setSize(1000, 600);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            crd = new CardLayout();
            cPane = getContentPane();
            cPane.setLayout(crd);
        });
    }

    private void setMenuBar() {
        dataStruct = new JMenu("Data Structures");
        dataStruct.setIcon(new ImageIcon(Helper.loadImageIcon("images/ds.png")));
        stackMItem = new JMenuItem("Stack");
        stackMItem.setIcon(new ImageIcon(Helper.loadImageIcon("images/stack.png")));
        stackMItem.addActionListener(this);
        dataStruct.add(stackMItem);

        queueMItem = new JMenuItem("Queue");
        queueMItem.setIcon(new ImageIcon(Helper.loadImageIcon("images/queue.png")));
        queueMItem.addActionListener(this);
        dataStruct.add(queueMItem);

        treeMenu = new JMenu("Tree");
        treeMenu.setIcon(new ImageIcon(Helper.loadImageIcon("images/tree.png")));
        dataStruct.add(treeMenu);

        avlTreeMItem = new JMenuItem("AVL Tree");
        avlTreeMItem.setIcon(new ImageIcon(Helper.loadImageIcon("images/circle.png")));
        avlTreeMItem.addActionListener(this);
        treeMenu.add(avlTreeMItem);

        bsTreeMItem = new JMenuItem("Binary Search Tree");
        bsTreeMItem.setIcon(new ImageIcon(Helper.loadImageIcon("images/circle.png")));
        bsTreeMItem.addActionListener(this);
        treeMenu.add(bsTreeMItem);

        genTreeMItem = new JMenuItem("General Binary Tree");
        genTreeMItem.setIcon(new ImageIcon(Helper.loadImageIcon("images/circle.png")));
        genTreeMItem.addActionListener(this);
        treeMenu.add(genTreeMItem);

        genNTreeMItem = new JMenuItem("General Nary Tree");
        genNTreeMItem.setIcon(new ImageIcon(Helper.loadImageIcon("images/circle.png")));
        genNTreeMItem.addActionListener(this);
        treeMenu.add(genNTreeMItem);

        redTreeMItem = new JMenuItem("Red Black Tree");
        redTreeMItem.setIcon(new ImageIcon(Helper.loadImageIcon("images/circle.png")));
        redTreeMItem.addActionListener(this);
        treeMenu.add(redTreeMItem);

        treapMItem = new JMenuItem("Treap");
        treapMItem.setIcon(new ImageIcon(Helper.loadImageIcon("images/circle.png")));
        treapMItem.addActionListener(this);
        treeMenu.add(treapMItem);

        splayTreeMItem = new JMenuItem("Splay Tree");
        splayTreeMItem.setIcon(new ImageIcon(Helper.loadImageIcon("images/circle.png")));
        splayTreeMItem.addActionListener(this);
        treeMenu.add(splayTreeMItem);

        graphMenu = new JMenu("Graph");
        graphMenu.setIcon(new ImageIcon(Helper.loadImageIcon("images/graph.png")));
        dataStruct.add(graphMenu);

        mb = new JMenuBar();
        mb.add(dataStruct);
        setJMenuBar(mb);
    }

    public void launch() {
        SwingUtilities.invokeLater(() -> {
            add("queue", queueWindow);
            add("stack", stackWindow);
            add("avl", new TreeViewer<Long>("AVL Tree", new AvlTree<>()));
            add("bst", new TreeViewer<Long>("Binary Search Tree", new BSTree<>()));
            add("gen", new GeneralBinaryTreeViewer<Long>("General Binary Tree", new GeneralBinaryTree<>()));
            add("gen-n-arr", new GeneralNaryTreeViewer<Long>("General Nary Tree", new GeneralNaryTree<>()));
            add("treap", new TreapTreeViewer<Long>("Treap", new Treap<>()));
            add("splay", new TreeViewer<Long>("Splay Tree", new SplayTree<>()));
            add("rb", new TreeViewer<Long>("Red Black Tree", new RedBlackTree<>()));
            setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == stackMItem) {
            crd.show(cPane, "stack");
        } else if (source == queueMItem) {
            crd.show(cPane, "queue");
        } else if (source == avlTreeMItem) {
            crd.show(cPane, "avl");
        } else if (source == genTreeMItem) {
            crd.show(cPane, "gen");
        } else if (source == bsTreeMItem) {
            crd.show(cPane, "bst");
        } else if (source == redTreeMItem) {
            crd.show(cPane, "rb");
        } else if (source == splayTreeMItem) {
            crd.show(cPane, "splay");
        } else if (source == genNTreeMItem) {
            crd.show(cPane, "gen-n-arr");
        } else if (source == treapMItem) {
            crd.show(cPane, "treap");
        }
    }
}
