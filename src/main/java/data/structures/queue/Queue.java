package data.structures.queue;

/**
 * Date: 31/12/21
 * Time: 9:45 am
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public interface Queue<T> {
    void enqueue(T value);

    T dequeue();

    boolean isEmpty();

    boolean isFull();

    T peek();

    void print();
}
