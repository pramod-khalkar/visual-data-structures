package data.structures;

/**
 * Date: 31/12/21
 * Time: 9:56 am
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public abstract class ArrayStorage<T> {
    protected T[] storageArray;
    protected int capacity;
    protected int index = 0;

    protected ArrayStorage(int capacity) {
        this.capacity = capacity;
        this.storageArray = (T[]) new Object[this.capacity];
    }

    protected void reInitStorage() {
        this.index = 0;
        this.storageArray = (T[]) new Object[this.capacity];
    }
}
