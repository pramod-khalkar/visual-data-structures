package data.structures.tree;

import java.util.Optional;

/**
 * Date: 01/01/22
 * Time: 9:01 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public interface Tree<T> {

    Optional<Node<T>> search(T value);

    Node<T> insert(T value);

    void remove(T value);

    void removeAll();

    boolean isEmpty();
}
