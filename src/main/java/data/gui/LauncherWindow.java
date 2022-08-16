package data.gui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            setVisible(true);
            crd = new CardLayout();
            cPane = getContentPane();
            cPane.setLayout(crd);
        });
    }

    private void setMenuBar() {
        dataStruct = new JMenu("Data Structure");
        stackMItem = new JMenuItem("Stack");
        stackMItem.addActionListener(this);
        dataStruct.add(stackMItem);

        queueMItem = new JMenuItem("Queue");
        queueMItem.addActionListener(this);
        dataStruct.add(queueMItem);

        treeMenu = new JMenu("Tree");
        dataStruct.add(treeMenu);

        avlTreeMItem = new JMenuItem("AVL Tree");
        avlTreeMItem.addActionListener(this);
        treeMenu.add(avlTreeMItem);

        bsTreeMItem = new JMenuItem("BS Tree");
        bsTreeMItem.addActionListener(this);
        treeMenu.add(bsTreeMItem);

        genTreeMItem = new JMenuItem("General Tree");
        genTreeMItem.addActionListener(this);
        treeMenu.add(genTreeMItem);

        genNTreeMItem = new JMenuItem("General Nary Tree");
        genNTreeMItem.addActionListener(this);
        treeMenu.add(genNTreeMItem);

        redTreeMItem = new JMenuItem("Red Black Tree");
        redTreeMItem.addActionListener(this);
        treeMenu.add(redTreeMItem);

        treapMItem = new JMenuItem("Treap");
        treapMItem.addActionListener(this);
        treeMenu.add(treapMItem);

        splayTreeMItem = new JMenuItem("Splay Tree");
        splayTreeMItem.addActionListener(this);
        treeMenu.add(splayTreeMItem);

        graphMenu = new JMenu("Graph");
        dataStruct.add(graphMenu);

        mb = new JMenuBar();
        mb.add(dataStruct);
        setJMenuBar(mb);
    }

    public void launch() {
        SwingUtilities.invokeLater(() -> {
            add("stack", stackWindow);
            add("queue", queueWindow);
            add("avl", new TreeViewer("Avl Tree", new AvlTree<>()));
            add("bst", new TreeViewer("Binary Search Tree", new BSTree<>()));
            add("gen", new TreeViewer("Simple Binary Tree", new GeneralBinaryTree<>()));
            add("gen-n-arr", new TreeViewer("General Nary Tree", new GeneralNaryTree<>()));
            add("treap", new TreeViewer("Treap", new Treap<>()));
            add("splay", new TreeViewer("Splay Tree", new SplayTree<>()));
            add("rb", new TreeViewer("Red Black Tree", new RedBlackTree<>()));
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
