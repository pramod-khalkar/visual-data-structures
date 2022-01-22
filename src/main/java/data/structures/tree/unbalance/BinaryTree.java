package data.structures.tree.unbalance;

import data.structures.Algorithm;
import data.structures.tree.Node;
import data.structures.tree.NodePosition;
import data.structures.tree.PrintableTree;
import data.structures.tree.Type;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

/**
 * Date: 04/01/22
 * Time: 11:32 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class BinaryTree<T> extends PrintableTree<T> implements Algorithm {

    private final Random random;

    public BinaryTree() {
        super();
        random = new Random();
    }

    @Override
    public Node<T> insert(T value) {
        Node<T> newNode = super.insert(value);
        Node<T> tNode = randomInsert(getRootNode(), newNode, (random.nextInt(2) == 0) ? NodePosition.LEFT : NodePosition.RIGHT);
        setRootNode(tNode);
        return newNode;
    }

    private Node<T> randomInsert(Node<T> node, Node<T> newNode, NodePosition nodePosition) {
        if (node == null) {
            return newNode;
        } else if (nodePosition == NodePosition.LEFT) {
            if (node.getLeft() == null) {
                node.setLeft(randomInsert(node.getLeft(), newNode, nodePosition));
            } else {
                node.setRight(randomInsert(node.getRight(), newNode, nodePosition));
            }
        } else if (nodePosition == NodePosition.RIGHT) {
            if (node.getRight() == null) {
                node.setRight(randomInsert(node.getRight(), newNode, nodePosition));
            } else {
                node.setLeft(randomInsert(node.getLeft(), newNode, nodePosition));
            }
        } else {
            throw new RuntimeException(String.format("Duplicate value %s", newNode.getData()));
        }
        return node;
    }

    public Node<T> insert(T value, T parent, NodePosition nodePosition) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(parent);
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            setRootNode(newNode);
        } else {
            Optional<Node<T>> parentNode = search0(getRootNode(), new Node<>(parent));
            if (parentNode.isPresent()) {
                parentInsert(newNode, parentNode.get(), nodePosition);
            } else {
                throw new RuntimeException(String.format("Parent node not available %s", parent));
            }
        }
        return newNode;
    }

    private void parentInsert(Node<T> newNode, Node<T> parentNode, NodePosition nodePosition) {
        if (parentNode.equals(newNode)) {
            throw new RuntimeException(String.format("Duplicate value %s", newNode.getData()));
        } else {
            if (nodePosition == NodePosition.LEFT && parentNode.getLeft() == null) {
                parentNode.setLeft(newNode);
            } else if (nodePosition == NodePosition.RIGHT && parentNode.getRight() == null) {
                parentNode.setRight(newNode);
            } else {
                throw new RuntimeException(String.format("Parent %s already has %s node", newNode.getData(), nodePosition));
            }
        }
    }

    @Override
    public Optional<Node<T>> search(T value) {
        super.search(value);
        return search0(getRootNode(), new Node<>(value));
    }

    private Optional<Node<T>> search0(Node<T> node, Node<T> searchNode) {
        if (node != null) {
            if (node.equals(searchNode)) {
                return Optional.of(node);
            } else {
                Optional<Node<T>> leftNode = search0(node.getLeft(), searchNode);
                Optional<Node<T>> rightNode = search0(node.getRight(), searchNode);
                return leftNode.isPresent() ? leftNode : rightNode;
            }
        }
        return Optional.empty();
    }

    @Override
    public void remove(T value) {
        super.remove(value);
        setRootNode(delete0(getRootNode(), new Node<>(value)));
    }

    private Node<T> delete0(Node<T> node, Node<T> toBeDelete) {
        if (node == null) {
            return node;
        } else if (node.equals(toBeDelete)) {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                node.setData(node.getRight().getData());
                node.setRight(delete0(node.getRight(), node));
            }
        } else {
            node.setLeft(delete0(node.getLeft(), toBeDelete));
            node.setRight(delete0(node.getRight(), toBeDelete));
        }
        return node;
    }

    @Override
    protected Type getTypeOfTree() {
        return Type.BINARY;
    }
}
