package data.structures.tree;

import java.util.Objects;

/**
 * Date: 05/01/22
 * Time: 8:17 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    public BinarySearchTree() {
        super();
    }

    @Override
    public boolean search(T value) {
        Objects.requireNonNull(value);
        return search0(getRootNode(), new Node<>(value));
    }

    private boolean search0(Node<T> rootNode, Node<T> searchNode) {
        if (rootNode != null) {
            if (rootNode.getData().compareTo(searchNode.getData()) == 0) {
                return true;
            } else {
                if (rootNode.getData().compareTo(searchNode.getData()) > 0) {
                    return search0(rootNode.getLeft(), searchNode);
                } else if (rootNode.getData().compareTo(searchNode.getData()) < 0) {
                    return search0(rootNode.getRight(), searchNode);
                }
            }
        }
        return false;
    }

    @Override
    public void insert(T value) {
        Objects.requireNonNull(value);
        this.root = insert0(getRootNode(), value);
    }

    private Node<T> insert0(Node<T> root, T value) {
        if (root == null) {
            return new Node<>(value);
        } else if (root.getData().compareTo(value) > 0) {
            root.setLeft(insert0(root.getLeft(), value));
        } else if (root.getData().compareTo(value) < 0) {
            root.setRight(insert0(root.getRight(), value));
        } else {
            throw new RuntimeException(String.format("Duplicate value %s", value));
        }
        return root;
    }

    @Override
    public void delete(T value) {
        Objects.requireNonNull(value);
        delete0(getRootNode(), value);
    }

    private Node<T> delete0(Node<T> rootNode, T valueTobeDeleted) {
        if (rootNode == null) {
            return rootNode;
        }
        if (valueTobeDeleted.compareTo(rootNode.getData()) < 0) {
            rootNode.setLeft(delete0(rootNode.getLeft(), valueTobeDeleted));
        } else if (valueTobeDeleted.compareTo(rootNode.getData()) > 0) {
            rootNode.setRight(delete0(rootNode.getRight(), valueTobeDeleted));
        } else {
            if (rootNode.getLeft() == null) {
                return rootNode.getRight();
            } else if (rootNode.getRight() == null) {
                return rootNode.getLeft();
            } else {
                rootNode.setData(minValue(rootNode.getRight()));
                rootNode.setRight(delete0(rootNode.getRight(), rootNode.getData()));
            }
        }
        return rootNode;
    }

    private T minValue(Node<T> node) {
        T minVal = node.getData();
        while (node.getLeft() != null) {
            minVal = node.getLeft().getData();
            node = node.getLeft();
        }
        return minVal;
    }
}
