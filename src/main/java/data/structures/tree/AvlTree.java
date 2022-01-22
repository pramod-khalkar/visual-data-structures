package data.structures.tree;

import java.util.Objects;
import java.util.Optional;

/**
 * Date: 05/01/22
 * Time: 10:02 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class AvlTree<T extends Comparable<? super T>> extends BSTree<T> {

    public AvlTree() {
        super();
    }

    @Override
    public Node<T> insert(T value) {
        Objects.requireNonNull(value);
        AvlNode<T> newNode = new AvlNode<>(value);
        setRootNode(insert0((AvlNode<T>) getRootNode(), newNode));
        return newNode;
    }

    AvlNode<T> insert0(AvlNode<T> tNode, AvlNode<T> newNode) {
        if (tNode == null) {
            return newNode;
        } else if (tNode.getData().compareTo(newNode.getData()) > 0) {
            tNode.setLeft(insert0((AvlNode<T>) tNode.getLeft(), newNode));
        } else if (tNode.getData().compareTo(newNode.getData()) < 0) {
            tNode.setRight(insert0((AvlNode<T>) tNode.getRight(), newNode));
        } else {
            throw new RuntimeException(String.format("Duplicate value %s", newNode.getData()));
        }
        return reBalance(tNode);
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

    protected <E> int heightOf(Node<E> tNode) {
        int height = 0;
        if (tNode == null) {
            height = -1;
        } else {
            height = Math.max(heightOf(tNode.getLeft()), heightOf(tNode.getRight())) + 1;
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
    public void remove(T value) {
        super.remove(value);
        reBalance((AvlNode<T>) getRootNode());
    }

    @Override
    public Optional<Node<T>> search(T value) {
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

        @Override
        public String toString() {
            return super.toString() + String.format("(bf:%d)", getBalanceFactor());
        }
    }
}
