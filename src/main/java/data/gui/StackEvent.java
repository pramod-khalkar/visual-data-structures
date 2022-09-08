package data.gui;

/**
 * @author : Pramod Khalkar
 * @since : 14/08/22, Sun
 * description: This file belongs to visual-data-structures
 **/
public interface StackEvent {
    void clear();

    Long pop();

    void push(Long item);
}
