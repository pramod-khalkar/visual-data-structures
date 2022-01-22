package data.gui;

import static data.gui.MainWindow.Ops.CLEAR;
import static data.gui.MainWindow.Ops.DELETE;
import static data.gui.MainWindow.Ops.INSERT;

import data.structures.queue.Queue;
import data.structures.stack.Stack;
import data.structures.tree.AvlTree;
import data.structures.tree.BSTree;
import data.structures.tree.SplayTree;
import data.structures.tree.Treap;
import data.structures.tree.Tree;
import data.structures.tree.unbalance.GeneralTree;
import java.io.IOException;

/**
 * Date: 20/01/22
 * Time: 1:59 am
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class MainWindow extends InitialUISetup {

    public MainWindow(int width, int height) throws IOException {
        super(width, height);
    }

    @Override
    <T extends Comparable<? super T>> void insert(T value, AlgorithmHolder algo) {
        executeOptions(INSERT, value, algo);
    }

    @Override
    <T extends Comparable<? super T>> void delete(T value, AlgorithmHolder algo) {
        executeOptions(DELETE, value, algo);
    }

    @Override
    void clear(AlgorithmHolder algo) {
        executeOptions(CLEAR, null, algo);
    }

    private <T extends Comparable<? super T>> void executeOptions(Ops operation, T value, AlgorithmHolder algo) {
        switch (algo.getDisplayName()) {
            case Stack:
                Stack<Object> stack_ = (Stack<Object>) algo.getAlgorithm();
                if (operation == INSERT) {
                    stack_.push(value);
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == DELETE) {
                    stack_.pop();
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == CLEAR) {
                    stack_.clear();
                    clearScreen();
                }
                break;
            case Queue:
                Queue<Object> queue_ = (Queue<Object>) algo.getAlgorithm();
                if (operation == INSERT) {
                    queue_.enqueue(value);
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == DELETE) {
                    queue_.dequeue();
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == CLEAR) {
                    queue_.clear();
                    clearScreen();
                }
                break;
            case BinaryTree:
                Tree<Object> binTree = (data.structures.tree.unbalance.BinaryTree<Object>) algo.getAlgorithm();
                if (operation == INSERT) {
                    binTree.insert(value);
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == DELETE) {
                    binTree.remove(value);
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == CLEAR) {
                    binTree.removeAll();
                    clearScreen();
                }
                break;
            case Treap:
                Tree<T> treap_ = (Treap<T>) algo.getAlgorithm();
                if (operation == INSERT) {
                    treap_.insert(value);
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == DELETE) {
                    treap_.remove(value);
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == CLEAR) {
                    treap_.removeAll();
                    clearScreen();
                }

                break;
            case BSTree:
                Tree<T> bsTree_ = (BSTree<T>) algo.getAlgorithm();
                if (operation == INSERT) {
                    bsTree_.insert(value);
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == DELETE) {
                    bsTree_.remove(value);
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == CLEAR) {
                    bsTree_.removeAll();
                    clearScreen();
                }
                break;
            case AvlTree:
                Tree<T> avlTree_ = (AvlTree<T>) algo.getAlgorithm();
                if (operation == INSERT) {
                    avlTree_.insert(value);
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == DELETE) {
                    avlTree_.remove(value);
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == CLEAR) {
                    avlTree_.removeAll();
                    clearScreen();
                }
            case SplayTree:
                Tree<T> splayTree = (SplayTree<T>) algo.getAlgorithm();
                if (operation == INSERT) {
                    splayTree.insert(value);
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == DELETE) {
                    splayTree.remove(value);
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == CLEAR) {
                    splayTree.removeAll();
                    clearScreen();
                }
                break;
            case GeneralTree:
                Tree<T> generalTree = (GeneralTree<T>) algo.getAlgorithm();
                if (operation == INSERT) {
                    generalTree.insert(value);
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == DELETE) {
                    generalTree.remove(value);
                    clearScreenAndPrint(algo.getAlgorithm());
                } else if (operation == CLEAR) {
                    generalTree.removeAll();
                    clearScreen();
                }
                break;
        }
    }

    enum Ops {
        INSERT, DELETE, CLEAR;
    }
}
