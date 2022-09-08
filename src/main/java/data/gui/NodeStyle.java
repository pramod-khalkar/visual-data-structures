package data.gui;

import java.util.LinkedList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.javads.nlinear.Node;
import org.javads.nlinear.tree.BiNode;
import org.javads.nlinear.tree.RedBlackTree;

/**
 * @author : Pramod Khalkar
 * @since : 29/08/22, Mon
 * description: This file belongs to visual-data-structures
 **/
@Data
@Builder
public class NodeStyle {
    private String fontColor;
    private String fillColor;
    private String strokeColor;
    private Integer strokeWidth;
    private String shape;

    public String getStyle() {
        List<String> list = new LinkedList<>();
        if (fontColor != null) {
            list.add("fontColor=" + this.fontColor);
        }
        if (fillColor != null) {
            list.add("fillColor=" + this.fillColor);
        }
        if (strokeColor != null) {
            list.add("strokeColor=" + this.strokeColor);
        }
        if (strokeWidth != null) {
            list.add("strokeWidth=" + this.strokeWidth);
        }
        if (shape != null) {
            list.add("shape=" + this.shape);
        }
        if (list.size() > 0) {
            return String.join(";", list);
        }
        return null;
    }

    static NodeStyle defaultNodeStyle(Node node) {
        NodeStyle style = NodeStyle.builder().build();
        if (node instanceof BiNode) {
            highLighBiNodeIfNeeded((BiNode<?>) node, style);
        }
        if (node instanceof RedBlackTree.RbNode) {
            rbNodeStyle((RedBlackTree.RbNode<?>) node, style);
            highLighBiNodeIfNeeded((BiNode<?>) node, style);
        }
        return style;
    }

    private static void rbNodeStyle(RedBlackTree.RbNode<?> rbNode, NodeStyle style) {
        style.setFontColor("white");
        if (rbNode.getColor() == RedBlackTree.Color.RED) {
            style.setFillColor("red");
        } else {
            style.setFillColor("black");
        }
    }

    private static void highLighBiNodeIfNeeded(BiNode<?> node, NodeStyle style) {
        if (((BiNode<NodeData<?>>) node).getData().isRecent()) {
            ((BiNode<NodeData<?>>) node).getData().setRecent(false);
            style.setStrokeColor("#00ff00");
            style.setStrokeWidth(2);
        }
    }
}
