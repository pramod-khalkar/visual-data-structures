package data.structures.queue;

import data.structures.ArrayStorage;

/**
 * Date: 19/01/22
 * Time: 11:12 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public abstract class AbstractQueue<T> extends ArrayStorage<T> {
    protected int front, rear;

    protected AbstractQueue(int capacity) {
        super(capacity);
        this.front = -1;
        this.rear = -1;
    }

    @Override
    protected void reInitStorage() {
        super.reInitStorage();
        this.front = -1;
        this.rear = -1;
    }
}
