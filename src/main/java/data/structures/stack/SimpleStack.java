package data.structures.stack;

import data.structures.Algorithm;

/**
 * Date: 31/12/21
 * Time: 12:14 am
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class SimpleStack<T> extends PrintableStack<T> implements Stack<T>, Algorithm {

    public SimpleStack() {
        this(10);
    }

    public SimpleStack(int capacity) {
        super(capacity);
    }

    @Override
    public void push(T value) throws RuntimeException {
        if (index < this.capacity) {
            this.storageArray[++index] = value;
        } else {
            throw new RuntimeException("Stack is full");
        }
    }

    @Override
    public T pop() throws RuntimeException {
        if (index >= 0) {
            T popedElement = this.storageArray[index];
            this.storageArray[index] = null;
            index--;
            return popedElement;
        }
        throw new RuntimeException("Stack is empty");
    }

    @Override
    public void clear() {
        reInitStorage();
    }
}
