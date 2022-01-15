package data.structures.tree;

import java.util.Objects;

/**
 * Date: 05/01/22
 * Time: 10:02 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class AvlTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    public AvlTree() {
        super();
    }

    @Override
    public void insert(T value) {
        Objects.requireNonNull(value);
        this.root = insert0((AvlNode<T>) getRootNode(), value);
    }

    AvlNode<T> insert0(AvlNode<T> root, T value) {
        if (root == null) {
            return new AvlNode<>(value);
        } else if (root.getData().compareTo(value) > 0) {
            root.setLeft(insert0((AvlNode<T>) root.getLeft(), value));
        } else if (root.getData().compareTo(value) < 0) {
            root.setRight(insert0((AvlNode<T>) root.getRight(), value));
        } else {
            throw new RuntimeException(String.format("Duplicate value %s", value));
        }
        return reBalance(root);
    }

    private AvlNode<T> reBalance(AvlNode<T> z) {
        z.updateHeight();
        int bal = z.getBalanceFactor();
        if (bal > 1) {
            if (heightOf(z.getRight().getRight()) > heightOf(z.getRight().getLeft())) {
                z = rotateLeft(z);
            } else {
                z.setRight(rotateRight((AvlNode<T>) z.getRight()));
                z = rotateLeft(z);
            }
        } else if (bal < -1) {
            if (heightOf(z.getLeft().getLeft()) > heightOf(z.getLeft().getRight())) {
                z = rotateRight(z);
            } else {
                z.setLeft(rotateLeft((AvlNode<T>) z.getLeft()));
                z = rotateRight(z);
            }
        }
        return z;
    }

    protected <E> int balanceFactorOf(Node<E> node) {
        return heightOf(node.getLeft()) - heightOf(node.getRight());
    }

    protected <E> int heightOf(Node<E> node) {
        int height = 0;
        if (node == null) {
            height = -1;
        } else {
            height = Math.max(heightOf(node.getLeft()), heightOf(node.getRight())) + 1;
        }
        return height;
    }

    private AvlNode<T> rotateRight(AvlNode<T> y) {
        AvlNode<T> x = (AvlNode<T>) y.getLeft();
        AvlNode<T> z = (AvlNode<T>) x.getRight();
        x.setRight(y);
        y.setLeft(z);
        y.updateHeight();
        x.updateHeight();
        return x;
    }

    private AvlNode<T> rotateLeft(AvlNode<T> y) {
        AvlNode<T> x = (AvlNode<T>) y.getRight();
        AvlNode<T> z = (AvlNode<T>) x.getLeft();
        x.setLeft(y);
        y.setRight(z);
        y.updateHeight();
        x.updateHeight();
        return x;
    }

    @Override
    public void delete(T value) {
        super.delete(value);
        reBalance((AvlNode<T>) getRootNode());
    }

    @Override
    public boolean search(T value) {
        return super.search(value);
    }

    class AvlNode<T> extends Node<T> {
        private int height;

        public AvlNode(T data) {
            super(data);
            this.height = -1;
        }

        public int getBalanceFactor() {
            int rightH, leftH;
            rightH = this.getRight() == null ? 0 : ((AvlNode<T>) this.getRight()).getHeight();
            leftH = this.getLeft() == null ? 0 : ((AvlNode<T>) this.getLeft()).getHeight();
            return rightH - leftH;
        }

        public int getHeight() {
            return this.height;
        }

        public void updateHeight() {
            this.height = heightOf(this);
        }
    }
}
