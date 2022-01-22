package data.structures.queue;

import data.structures.Algorithm;

/**
 * Date: 31/12/21
 * Time: 9:45 am
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public interface Queue<T> extends Algorithm {
    void enqueue(T value);

    T dequeue();

    boolean isEmpty();

    boolean isFull();

    T peek();

    void clear();
}
