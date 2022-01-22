package data.structures.tree;

import java.util.Optional;

/**
 * Date: 16/01/22
 * Time: 7:10 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class SplayTree<T extends Comparable<? super T>> extends BSTree<T> {

    public SplayTree() {
        super();
    }

    @Override
    public Optional<Node<T>> search(T value) {
        Optional<Node<T>> result = super.search(value);
        setRootNode(splayTheTree(getRootNode(), new Node<>(value)));
        return result;
    }

    @Override
    public Node<T> insert(T value) {
        Node<T> newNode = super.insert(value);
        setRootNode(splayTheTree(getRootNode(), newNode));
        return newNode;
    }

    @Override
    public void remove(T value) {
        Node<T> nodeToDelete = new Node<>(value);
        setRootNode(splayTheTree(getRootNode(), nodeToDelete));
        if (getRootNode().getData().compareTo(nodeToDelete.getData()) == 0) {
            Node<T> leftSubTree = getRootNode().getLeft();
            Node<T> rightSubTree = getRootNode().getRight();
            leftSubTree = splayTheTree(leftSubTree, maxValueNode(leftSubTree));
            if (leftSubTree != null) {
                leftSubTree.setRight(rightSubTree);
            } else {
                leftSubTree = rightSubTree;
            }
            setRootNode(leftSubTree);
        }
    }

    private Node<T> maxValueNode(Node<T> node) {
        while (node != null && node.getRight() != null) {
            node = maxValueNode(node.getRight());
        }
        return node;
    }

    private Node<T> splayTheTree(Node<T> tNode, Node<T> node) {
        if (tNode == null || tNode.getData().compareTo(node.getData()) == 0) {
            return tNode;
        }
        if (tNode.getData().compareTo(node.getData()) > 0) {
            if (tNode.getLeft() == null) {
                return tNode;
            }
            if (tNode.getLeft().getData().compareTo(node.getData()) > 0) {
                tNode.getLeft().setLeft(splayTheTree(tNode.getLeft().getLeft(), node));
                tNode = rotateRight(tNode);
            } else if (tNode.getLeft().getData().compareTo(node.getData()) < 0) {
                tNode.getLeft().setRight(splayTheTree(tNode.getLeft().getRight(), node));
                if (tNode.getLeft().getRight() != null) {
                    tNode.setLeft(rotateLeft(tNode.getLeft()));
                }
            }
            return tNode.getLeft() == null ? tNode : rotateRight(tNode);
        } else {
            if (tNode.getRight() == null) {
                return tNode;
            }
            if (tNode.getRight().getData().compareTo(node.getData()) > 0) {
                tNode.getRight().setLeft(splayTheTree(tNode.getRight().getLeft(), node));
                if (tNode.getRight().getLeft() != null) {
                    tNode.setRight(rotateRight(tNode.getRight()));
                }
            } else if (tNode.getRight().getData().compareTo(node.getData()) < 0) {
                tNode.getRight().setRight(splayTheTree(tNode.getRight().getRight(), node));
                tNode = rotateLeft(tNode);
            }
            return tNode.getRight() == null ? tNode : rotateLeft(tNode);
        }
    }

    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.getLeft();
        Node<T> z = x.getRight();
        x.setRight(y);
        y.setLeft(z);
        return x;
    }

    private Node<T> rotateLeft(Node<T> y) {
        Node<T> x = y.getRight();
        Node<T> z = x.getLeft();
        x.setLeft(y);
        y.setRight(z);
        return x;
    }
}
