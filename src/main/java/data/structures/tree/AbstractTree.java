package data.structures.tree;

import java.util.Objects;
import java.util.Optional;

/**
 * Date: 05/01/22
 * Time: 8:14 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public abstract class AbstractTree<T> implements Tree<T> {
    private Node<T> root;

    protected AbstractTree() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return getRootNode() == null;
    }

    protected Node<T> getRootNode() {
        return this.root;
    }

    protected void setRootNode(Node<T> root) {
        this.root = root;
    }

    @Override
    public Optional<Node<T>> search(T value) {
        Objects.requireNonNull(value);
        return Optional.empty();
    }

    @Override
    public Node<T> insert(T value) {
        Objects.requireNonNull(value);
        return new Node<>(value);
    }

    @Override
    public void remove(T value) {
        Objects.requireNonNull(value);
    }

    @Override
    public void removeAll() {
        setRootNode(null);
    }
}
