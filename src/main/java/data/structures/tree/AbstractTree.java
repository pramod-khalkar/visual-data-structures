package data.structures.tree;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Date: 05/01/22
 * Time: 8:14 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public abstract class AbstractTree<T> extends TreePrinter<Node<T>> implements Tree<T> {
    protected Node<T> root;

    protected AbstractTree() {
        this.root = null;
    }

    @Override
    protected Node<T> getRootNode() {
        return this.root;
    }

    @Override
    public boolean search(T value) {
        Objects.requireNonNull(value);
        return false;
    }

    @Override
    public void insert(T value) {
        Objects.requireNonNull(value);
    }

    @Override
    public void delete(T value) {
        Objects.requireNonNull(value);
    }

    @Override
    public List<T> traverseAndCollect() {
        return Collections.emptyList();
    }

    @Override
    public void traverseAndPrint() {
        print();
    }
}
