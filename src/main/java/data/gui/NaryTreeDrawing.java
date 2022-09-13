package data.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javads.nlinear.Node;
import org.javads.nlinear.tree.GeneralNaryTree;

/**
 * @author : Pramod Khalkar
 * @since : 22/08/22, Mon
 * description: This file belongs to visual-data-structures
 **/
public class NaryTreeDrawing<T extends Comparable<T>> extends BinaryTreeDrawing<T> {
    private Map<Integer, List<GeneralNaryTree.GNode>> nodes = new HashMap<>();
    private int level = 0;
    private Map<Integer, Integer> levelItemCount;

    public NaryTreeDrawing(Node root, TreeEvent treeEvent) {
        super(root, treeEvent);
    }

    @Override
    public Object drawTree(Node root, int depth, int index) {
        if (root != null) {
            GeneralNaryTree.GNode<T> rootN = (GeneralNaryTree.GNode<T>) root;
            int x = CANVAS_WIDTH / 2, y = 10;
            Object root_ = drawNTree(rootN, x, y, 0);
        }
        return null;
    }

    private Object drawNTree(GeneralNaryTree.GNode<T> rootN, int x, int y, int level) {
        Object gObj = graph.insertVertex(parent, null, rootN.getData().toString(),
                x, y, NODE_SIZE_WIDTH, NODE_SIZE_HEIGHT, applyNodeStyle(null).getStyle());
//        if (levelItemCount == null) {
//            levelItemCount = new HashMap<>();
//        }
//        levelItemCount.put(level, levelItemCount.get(level) == null ? 0 : levelItemCount.get(level) + 1);
        drawChildren(y, gObj, rootN.getChildren(), level + 1);
        return gObj;
    }

    private void drawChildren(int y, Object root, List<GeneralNaryTree.GNode<T>> childs, int level) {
        int x = 10;
        if (childs.size() > 0) {
//            int gap = CANVAS_WIDTH / childs.size();
            if (levelItemCount == null) {
                levelItemCount = new HashMap<>();
            }
            int gap = CANVAS_WIDTH / childs.size() + (levelItemCount.get(level) == null ? 0 : levelItemCount.get(level));
            for (int i = 0; i < childs.size(); i++) {
                x += gap;
                Object child = drawNTree(childs.get(i), x + 10, y + 50, level);
                levelItemCount.put(level, levelItemCount.get(level) == null ? 0 : levelItemCount.get(level) + 1);
                graph.insertEdge(parent, null, "", root, child, EDGE_STYLE);
            }
        }
    }
}
