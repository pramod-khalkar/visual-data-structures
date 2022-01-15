package data.structures.queue;

import data.structures.ArrayStorage;

/**
 * Date: 31/12/21
 * Time: 9:54 am
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class QueueImpl<T> extends ArrayStorage<T> implements Queue<T> {
    private int front, rear;

    public QueueImpl() {
        this(10);
    }

    public QueueImpl(int capacity) {
        super(capacity);
        this.front = -1;
        this.rear = -1;
    }

    @Override
    public void enqueue(T value) throws RuntimeException {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        } else {
            if (this.front == -1) {
                this.front++;
            }
            this.storageArray[++this.rear] = value;
        }
    }

    @Override
    public T dequeue() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        } else {
            T element = this.storageArray[this.front++];
            if (this.front > this.rear) {
                this.front = this.rear = -1;
            }
            return element;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.front == -1;
    }

    @Override
    public boolean isFull() {
        return this.front == -1 && this.rear == this.capacity;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        } else {
            return this.storageArray[this.front];
        }
    }

    @Override
    public void print() {
        QueuePrinter<T> queuePrinter = new QueuePrinter<>(this, this.storageArray, this.rear, this.front);
        queuePrinter.print();
    }
}
