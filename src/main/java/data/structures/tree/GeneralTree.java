package data.structures.tree;

/**
 * Date: 01/01/22
 * Time: 9:05 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class GeneralTree<T> extends AbstractTree<T> {

    public GeneralTree() {
        super();
    }

    public void insert(T value, T parent) {
        Node<T> newGNode = new Node<>(value);
        Node<T> parentGNode = new Node<>(parent);
        newGNode.setData(value);
        if (root == null || parent == null) {
            root = newGNode;
        } else {
            Node<T> parent_ = findParent(this.root, parentGNode);
            if (parent_ != null) {
                if (parent_.getLeft() == null) {
                    parent_.setLeft(newGNode);
                } else {
                    if (parent_.getLeft().getRight() == null) {
                        parent_.getLeft().setRight(newGNode);
                    } else {
                        Node<T> sibling = parent_.getLeft().getRight();
                        while (sibling.getRight() != null) {
                            sibling = sibling.getRight();
                        }
                        sibling.setRight(newGNode);
                    }
                }
            }
        }
    }

    private Node<T> findParent(Node<T> GNode_, Node<T> parentGNode) {
        if (GNode_.equals(parentGNode)) {
            return GNode_;
        } else {
            if (GNode_.getLeft() != null) {
                return findParent(GNode_.getLeft(), parentGNode);
            }
            if (GNode_.getRight() != null) {
                return findParent(GNode_.getRight(), parentGNode);
            }
        }
        return null;
    }

    @Override
    public void insert(T value) {
        insert(value, this.root != null ? this.root.getData() : value);
    }

    @Override
    public void delete(T value) {
    }

    @Override
    public boolean search(T value) {
        return search0(this.root, new Node<>(value));
    }

    private boolean search0(Node<T> root, Node<T> value) {
        if (root != null) {
            if (root.equals(value)) {
                return true;
            } else {
                if (root.getLeft() != null) {
                    return search0(root.getLeft(), value);
                }
                if (root.getRight() != null) {
                    return search0(root.getRight(), value);
                }
            }
        }
        return false;
    }

    @Override
    protected Type getTreeType() {
        return Type.N_ARRAY;
    }
}
