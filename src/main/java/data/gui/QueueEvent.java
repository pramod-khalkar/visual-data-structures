package data.gui;

/**
 * @author : Pramod Khalkar
 * @since : 14/08/22, Sun
 * description: This file belongs to visual-data-structures
 **/
public interface QueueEvent {
    void enqueue(Long item);

    Long dequeue();

    void clear();
}
