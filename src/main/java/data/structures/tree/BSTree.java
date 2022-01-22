package data.structures.tree;

import static data.structures.tree.Type.BINARY;

import data.structures.Algorithm;
import java.util.Objects;
import java.util.Optional;

/**
 * Date: 05/01/22
 * Time: 8:17 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class BSTree<T extends Comparable<? super T>> extends PrintableTree<T> implements Algorithm {

    public BSTree() {
        super();
    }

    @Override
    public Optional<Node<T>> search(T value) {
        Objects.requireNonNull(value);
        return search0(getRootNode(), new Node<>(value));
    }

    private Optional<Node<T>> search0(Node<T> tNode, Node<T> searchNode) {
        if (tNode != null) {
            if (tNode.getData().compareTo(searchNode.getData()) == 0) {
                return Optional.of(tNode);
            } else {
                if (tNode.getData().compareTo(searchNode.getData()) > 0) {
                    return search0(tNode.getLeft(), searchNode);
                } else if (tNode.getData().compareTo(searchNode.getData()) < 0) {
                    return search0(tNode.getRight(), searchNode);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Node<T> insert(T value) {
        Objects.requireNonNull(value);
        Node<T> newNode = new Node<>(value);
        setRootNode(insert0(getRootNode(), newNode));
        return newNode;
    }

    private Node<T> insert0(Node<T> tNode, Node<T> newNode) {
        if (tNode == null) {
            return newNode;
        } else if (tNode.getData().compareTo(newNode.getData()) > 0) {
            tNode.setLeft(insert0(tNode.getLeft(), newNode));
        } else if (tNode.getData().compareTo(newNode.getData()) < 0) {
            tNode.setRight(insert0(tNode.getRight(), newNode));
        } else {
            throw new RuntimeException(String.format("Duplicate value %s", newNode.getData()));
        }
        return tNode;
    }

    @Override
    public void remove(T value) {
        Objects.requireNonNull(value);
        delete0(getRootNode(), value);
    }

    private Node<T> delete0(Node<T> tNode, T nodeTobeDeleted) {
        if (tNode == null) {
            return tNode;
        }
        if (nodeTobeDeleted.compareTo(tNode.getData()) < 0) {
            tNode.setLeft(delete0(tNode.getLeft(), nodeTobeDeleted));
        } else if (nodeTobeDeleted.compareTo(tNode.getData()) > 0) {
            tNode.setRight(delete0(tNode.getRight(), nodeTobeDeleted));
        } else {
            if (tNode.getLeft() == null) {
                return tNode.getRight();
            } else if (tNode.getRight() == null) {
                return tNode.getLeft();
            } else {
                tNode.setData(minValue(tNode.getRight()));
                tNode.setRight(delete0(tNode.getRight(), tNode.getData()));
            }
        }
        return tNode;
    }

    private T minValue(Node<T> node) {
        T minVal = node.getData();
        while (node.getLeft() != null) {
            minVal = node.getLeft().getData();
            node = node.getLeft();
        }
        return minVal;
    }

    @Override
    protected Type getTypeOfTree() {
        return BINARY;
    }
}
