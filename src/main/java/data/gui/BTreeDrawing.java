package data.gui;

import java.util.ArrayList;
import java.util.List;
import org.javads.nlinear.Node;
import org.javads.nlinear.tree.BTree;

/**
 * @author : Pramod Khalkar
 * @since : 29/08/22, Mon
 * description: This file belongs to visual-data-structures
 **/
public class BTreeDrawing<T extends Comparable<T>> extends BinaryTreeDrawing<T> {
    private final static int TREE_HEIGHT = 32;
    private Object[] mObjLists;
    private final static int NODE_DIST = 16;
    private final static int HEIGHT_STEP = 80;
    private StringBuilder mBuf;

    public BTreeDrawing(Node root, TreeEvent treeEvent) {
        super(root, treeEvent);
    }

    @Override
    public Object drawTree(Node root, int depth, int index) {
        mObjLists = new Object[TREE_HEIGHT];
        mBuf = new StringBuilder();
        List<Object> pObjList = new ArrayList<Object>();
        List<Object> cObjList = new ArrayList<Object>();
        List<Object> tempObjList;
        for (int i = 0; i < TREE_HEIGHT; ++i) {
            mObjLists[i] = null;
        }
        try {
            generateGraphObject((BTree.BTNode<Integer>) root, 0);
        } catch (Exception btex) {
            btex.printStackTrace();
        }
        int nStartXPos;
        int nStartYPos = 10;
        int cellWidth;
        for (int i = 0; i < mObjLists.length; ++i) {
            cObjList.clear();
            List<KeyData> objList = (List<KeyData>) mObjLists[i];
            if (objList == null) {
                continue;
            }
            int totalWidth = 0;
            int nCount = 0;
            for (KeyData keyData : objList) {
                totalWidth += keyData.mKeys.length() * 6;
                if (nCount > 0) {
                    totalWidth += NODE_DIST;
                }
                ++nCount;
            }
            nStartXPos = (CANVAS_WIDTH - totalWidth) / 2;
            if (nStartXPos < 0) {
                nStartXPos = 0;
            }
            for (KeyData keyData : objList) {
                int len = keyData.mKeys.length();
                if (len == 1) {
                    len += 2;
                }
                cellWidth = (len * 6) + 5;
                Object gObj = graph.insertVertex(parent, null, keyData.mKeys,
                        nStartXPos, nStartYPos, cellWidth, NODE_SIZE_HEIGHT, applyNodeStyle(null).getStyle());
                cObjList.add(gObj);
                nStartXPos += (cellWidth + NODE_DIST);
            }
            if (i > 0) {
                // Connect the nodes
                List<KeyData> keyList = (List<KeyData>) mObjLists[i - 1];
                int j = 0, k = 0;
                for (Object pObj : pObjList) {
                    KeyData keyData = keyList.get(j);
                    for (int l = 0; l < keyData.mKeyNum + 1; ++l) {
                        graph.insertEdge(parent, null, "", pObj, cObjList.get(k), EDGE_STYLE);
                        ++k;
                    }
                    ++j;
                }
            }
            // Swap two object lists for next loop
            tempObjList = pObjList;
            pObjList = cObjList;
            cObjList = tempObjList;
            nStartYPos += HEIGHT_STEP;
        }
        return null;
    }

    private void generateGraphObject(BTree.BTNode<Integer> treeNode, int nLevel) {
        if ((treeNode == null) ||
                (treeNode.currentIndex == 0)) {
            return;
        }
        int currentKeyNum = treeNode.currentIndex;
        BTree.Data<Integer> keyVal;
        List<KeyData> keyList = (List<KeyData>) mObjLists[nLevel];
        if (keyList == null) {
            keyList = new ArrayList<>();
            mObjLists[nLevel] = keyList;
        }
        mBuf.setLength(0);
        // Render the keys in the node
        for (int i = 0; i < currentKeyNum; ++i) {
            if (i > 0) {
                mBuf.append(" | ");
            }
            keyVal = treeNode.data[i];
            mBuf.append(keyVal.getValue());
        }
        keyList.add(new KeyData(mBuf.toString(), currentKeyNum));
        if (treeNode.isLeaf) {
            return;
        }
        ++nLevel;
        for (int i = 0; i < currentKeyNum + 1; ++i) {
            generateGraphObject(treeNode.children[i], nLevel);
        }
    }

    static class KeyData {
        String mKeys = null;
        int mKeyNum = 0;

        KeyData(String keys, int keyNum) {
            mKeys = keys;
            mKeyNum = keyNum;
        }
    }
}
