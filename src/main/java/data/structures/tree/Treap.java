package data.structures.tree;

import java.util.Random;

/**
 * Date: 17/01/22
 * Time: 10:39 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class Treap<T extends Comparable<? super T>> extends BSTree<T> {

    private final Random random = new Random();

    public Treap() {
        super();
    }

    @Override
    public Node<T> insert(T value) {
        TreapNode<T> newNode = new TreapNode<>(value);
        setRootNode(isEmpty() ? newNode : insert0((TreapNode<T>) getRootNode(), newNode));
        return newNode;
    }

    public Node<T> insert(T value, int priority) {
        TreapNode<T> newNode = new TreapNode<>(value, priority);
        setRootNode(isEmpty() ? newNode : insert0((TreapNode<T>) getRootNode(), newNode));
        return newNode;
    }

    private TreapNode<T> insert0(TreapNode<T> tNode, TreapNode<T> newNode) {
        if (tNode == null) {
            return newNode;
        }
        if (tNode.getData().compareTo(newNode.getData()) > 0) {
            tNode.setLeft(insert0((TreapNode<T>) tNode.getLeft(), newNode));
            if (tNode.getLeft() != null && ((TreapNode<T>) tNode.getLeft()).getPriority() > tNode.getPriority()) {
                tNode = (TreapNode<T>) rotateRight(tNode);
            }
        } else if (tNode.getData().compareTo(newNode.getData()) < 0) {
            tNode.setRight(insert0((TreapNode<T>) tNode.getRight(), newNode));
            if (tNode.getRight() != null && ((TreapNode<T>) tNode.getRight()).getPriority() > tNode.getPriority()) {
                tNode = (TreapNode<T>) rotateLeft(tNode);
            }
        } else {
            throw new RuntimeException(String.format("Duplicate value %s", newNode.getData()));
        }
        return tNode;
    }

    private Node<T> rotateRight(TreapNode<T> y) {
        Node<T> x = y.getLeft();
        Node<T> z = x.getRight();
        x.setRight(y);
        y.setLeft(z);
        return x;
    }

    private Node<T> rotateLeft(TreapNode<T> y) {
        Node<T> x = y.getRight();
        Node<T> z = x.getLeft();
        x.setLeft(y);
        y.setRight(z);
        return x;
    }

    @Override
    public void remove(T value) {
        TreapNode<T> tobeDeleted = new TreapNode<>(value);
        if (!isEmpty()) {
            setRootNode(delete0((TreapNode<T>) getRootNode(), tobeDeleted));
        }
    }

    private TreapNode<T> delete0(TreapNode<T> tNode, TreapNode<T> tobeDeleted) {
        if (tNode == null) {
            return null;
        }
        if (tNode.getData().compareTo(tobeDeleted.getData()) > 0) {
            tNode.setLeft(delete0((TreapNode<T>) tNode.getLeft(), tobeDeleted));
        } else if (tNode.getData().compareTo(tobeDeleted.getData()) < 0) {
            tNode.setRight(delete0((TreapNode<T>) tNode.getRight(), tobeDeleted));
        } else {
            if (tNode.getLeft() == null && tNode.getRight() == null) {
                tNode = null;
            } else if (tNode.getLeft() != null && tNode.getRight() != null) {
                if (((TreapNode<T>) tNode.getLeft()).getPriority() < ((TreapNode<T>) tNode.getRight()).getPriority()) {
                    tNode = (TreapNode<T>) rotateLeft(tNode);
                    tNode.setLeft(delete0((TreapNode<T>) tNode.getLeft(), tobeDeleted));
                } else {
                    tNode = (TreapNode<T>) rotateRight(tNode);
                    tNode.setRight(delete0((TreapNode<T>) tNode.getRight(), tobeDeleted));
                }
            } else {
                tNode = tNode.getLeft() != null ? (TreapNode<T>) tNode.getLeft() : (TreapNode<T>) tNode.getRight();
            }
        }
        return tNode;
    }

    class TreapNode<T> extends Node<T> {
        private int priority;

        public TreapNode(T data, int priority) {
            super(data);
            this.priority = priority;
        }

        public TreapNode(T data) {
            this(data, random.nextInt(500));
        }

        public int getPriority() {
            return this.priority;
        }

        @Override
        public String toString() {
            return super.toString() + String.format("(P:%d)", getPriority());
        }
    }
}
