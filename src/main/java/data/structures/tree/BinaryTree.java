package data.structures.tree;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

/**
 * Date: 04/01/22
 * Time: 11:32 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class BinaryTree<T> extends AbstractTree<T> {

    private final Random random;

    public BinaryTree() {
        super();
        random = new Random();
    }

    @Override
    public void insert(T value) {
        super.insert(value);
        this.root = randomInsert(getRootNode(), value, (random.nextInt(2) == 0) ? NodePosition.LEFT : NodePosition.RIGHT);
    }

    private Node<T> randomInsert(Node<T> node, T value, NodePosition nodePosition) {
        if (node == null) {
            return new Node<>(value);
        } else if (nodePosition == NodePosition.LEFT) {
            if (node.getLeft() == null) {
                node.setLeft(randomInsert(node.getLeft(), value, nodePosition));
            } else {
                node.setRight(randomInsert(node.getRight(), value, nodePosition));
            }
        } else if (nodePosition == NodePosition.RIGHT) {
            if (node.getRight() == null) {
                node.setRight(randomInsert(node.getRight(), value, nodePosition));
            } else {
                node.setLeft(randomInsert(node.getLeft(), value, nodePosition));
            }
        } else {
            throw new RuntimeException(String.format("Duplicate value %s", value));
        }
        return node;
    }

    public void insert(T value, T parent, NodePosition nodePosition) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(parent);
        Node<T> newNode = new Node<>(value);
        if (getRootNode() == null) {
            this.root = newNode;
        } else {
            Optional<Node<T>> parentNode = search0(getRootNode(), new Node<>(parent));
            if (parentNode.isPresent()) {
                parentInsert(newNode, parentNode.get(), nodePosition);
            } else {
                throw new RuntimeException(String.format("Parent node not available %s", parent));
            }
        }
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
    public boolean search(T value) {
        super.search(value);
        return search0(getRootNode(), new Node<>(value)).isPresent();
    }

    private Optional<Node<T>> search0(Node<T> rootNode, Node<T> searchNode) {
        if (rootNode != null) {
            if (rootNode.equals(searchNode)) {
                return Optional.of(rootNode);
            } else {
                Optional<Node<T>> leftNode = search0(rootNode.getLeft(), searchNode);
                Optional<Node<T>> rightNode = search0(rootNode.getRight(), searchNode);
                return leftNode.isPresent() ? leftNode : rightNode;
            }
        }
        return Optional.empty();
    }

    @Override
    public void delete(T value) {
        super.delete(value);
        delete0(getRootNode(), new Node<>(value));
    }

    private Node<T> delete0(Node<T> rootNode, Node<T> toBeDelete) {
        if (rootNode == null) {
            return rootNode;
        } else if (rootNode.equals(toBeDelete)) {
            if (rootNode.getLeft() == null && rootNode.getRight() != null) {
                return rootNode.getRight();
            } else if (rootNode.getRight() == null && rootNode.getLeft() != null) {
                return rootNode.getLeft();
            } else {
                rootNode.setData(rootNode.getRight().getData());
                rootNode.setRight(delete0(rootNode.getRight(), rootNode));
            }
        } else {
            rootNode.setLeft(delete0(rootNode.getLeft(), toBeDelete));
            rootNode.setRight(delete0(rootNode.getRight(), toBeDelete));
        }
        return rootNode;
    }

    @Override
    protected Type getTreeType() {
        return Type.BINARY;
    }
}
