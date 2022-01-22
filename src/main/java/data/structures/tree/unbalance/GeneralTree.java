package data.structures.tree.unbalance;

import data.structures.Algorithm;
import data.structures.tree.Node;
import data.structures.tree.PrintableTree;
import data.structures.tree.Type;
import java.util.Optional;

/**
 * Date: 01/01/22
 * Time: 9:05 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class GeneralTree<T> extends PrintableTree<T> implements Algorithm {

    public GeneralTree() {
        super();
    }

    @Override
    public Node<T> insert(T value) {
        return insert(value, !isEmpty() ? getRootNode().getData() : value);
    }

    public Node<T> insert(T value, T parent) {
        Node<T> newGNode = new Node<>(value);
        Node<T> parentGNode = new Node<>(parent);
        newGNode.setData(value);
        if (isEmpty() || parent == null) {
            setRootNode(newGNode);
        } else {
            Node<T> parent_ = findParent(getRootNode(), parentGNode);
            if (parent_ != null) {
                if (parent_.getLeft() == null) {
                    parent_.setLeft(newGNode);
                } else {
                    if (parent_.getLeft().getRight() == null) {
                        parent_.getLeft().setRight(newGNode);
                    } else {
                        Node<T> sibling = parent_.getLeft().getRight();
                        while (sibling.getRight() != null) {
                            sibling = sibling.getRight();
                        }
                        sibling.setRight(newGNode);
                    }
                }
            }
        }
        return newGNode;
    }

    private Node<T> findParent(Node<T> tNode, Node<T> searchingNode) {
        if (tNode.equals(searchingNode)) {
            return tNode;
        } else {
            if (tNode.getLeft() != null) {
                return findParent(tNode.getLeft(), searchingNode);
            }
            if (tNode.getRight() != null) {
                return findParent(tNode.getRight(), searchingNode);
            }
        }
        return null;
    }

    @Override
    public void remove(T value) {
        Node<T> tobeDeleted = new Node<>(value);
        if (!isEmpty()) {
            if (getRootNode().equals(tobeDeleted)) {
                setRootNode(null);
            } else {
                setRootNode(delete0(getRootNode(), tobeDeleted));
            }
        }
    }

    private Node<T> delete0(Node<T> tNode, Node<T> deletingNode) {
        if (tNode == null) {
            return tNode;
        } else if (tNode.equals(deletingNode)) {
            tNode = tNode.getRight();
        } else {
            tNode.setLeft(delete0(tNode.getLeft(), deletingNode));
            tNode.setRight(delete0(tNode.getRight(), deletingNode));
        }
        return tNode;
    }

    @Override
    public Optional<Node<T>> search(T value) {
        return search0(getRootNode(), new Node<>(value));
    }

    private Optional<Node<T>> search0(Node<T> tNode, Node<T> value) {
        if (tNode != null) {
            if (tNode.equals(value)) {
                return Optional.of(tNode);
            } else {
                if (tNode.getLeft() != null) {
                    return search0(tNode.getLeft(), value);
                }
                if (tNode.getRight() != null) {
                    return search0(tNode.getRight(), value);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    protected Type getTypeOfTree() {
        return Type.N_ARRAY;
    }
}
