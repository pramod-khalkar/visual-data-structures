package data.utils;


import org.javads.nlinear.tree.BiSide;

/**
 * @author : Pramod Khalkar
 * @since : 16/08/22, Tue
 * description: This file belongs to visual-data-structures
 **/
public class UnBalNodeInput {
    private BiSide side;
    private Long parent;
    private Long value;

    public BiSide getSide() {
        return side;
    }

    public void setSide(BiSide side) {
        this.side = side;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
