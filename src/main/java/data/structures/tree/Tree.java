package data.structures.tree;

import java.util.List;

/**
 * Date: 01/01/22
 * Time: 9:01 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public interface Tree<T> {

    boolean search(T value);

    void insert(T value);

    void delete(T value);

    List<T> traverseAndCollect();

    void traverseAndPrint();
}
