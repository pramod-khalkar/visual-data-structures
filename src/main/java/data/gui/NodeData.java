package data.gui;

import java.util.Objects;

/**
 * @author : Pramod Khalkar
 * @since : 20/08/22, Sat
 * description: This file belongs to visual-data-structures
 **/
public class NodeData<T extends Comparable<T>> implements Comparable<NodeData<T>> {
    private T value;
    private boolean recent;

    public NodeData(T value) {
        this.value = value;
        this.recent = false;
    }

    public NodeData(T value, boolean recent) {
        this.value = value;
        this.recent = recent;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isRecent() {
        return this.recent;
    }

    public void setRecent(boolean recent) {
        this.recent = recent;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public int compareTo(NodeData<T> o) {
        return this.value.compareTo(o.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NodeData<?> nodeData = (NodeData<?>) o;
        return value.equals(nodeData.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
