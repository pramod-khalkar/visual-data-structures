package data.structures.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import data.structures.Printable;

/**
 * Date: 05/01/22
 * Time: 5:59 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public abstract class TreePrinter<T extends Node<?>> implements Printable {

    protected abstract T getRootNode();

    protected abstract Type getTreeType();

    @Override
    public void print() {
        T root = getRootNode();
        if (root != null) {
            if (getTreeType() == Type.N_ARRAY) {
                printNArrayTree("      ", root, getChildrenFunc(getTreeType()), false);
            } else {
                printBinaryTree(getRootNode());
            }
        }
    }

    private Function<T, List<T>> getChildrenFunc(Type type) {
        switch (type) {
            case N_ARRAY:
                return (node -> {
                    List<T> childs = new ArrayList<>();
                    if (node.getLeft() != null) {
                        T child = (T) node.getLeft();
                        while (child != null) {
                            childs.add(child);
                            child = (T) child.getRight();
                        }
                    }
                    return childs;
                });
            case BINARY:
            default:
                return (node -> {
                    List<T> childs = new ArrayList<>();
                    if (node.getLeft() != null) {
                        childs.add((T) node.getLeft());
                    }
                    if (node.getRight() != null) {
                        childs.add((T) node.getRight());
                    }
                    return childs;
                });
        }
    }


    private void printNArrayTree(String prefix, T node, Function<T, List<T>> getChildrenFunc, boolean isTail) {
        String nodeName = node.toString();
        String nodeConnection = isTail ? "└── " : "├── ";
        System.out.println(prefix + nodeConnection + nodeName);
        List<T> children = getChildrenFunc.apply(node);
        for (int i = 0; i < children.size(); i++) {
            String newPrefix = prefix + (isTail ? "    " : "│   ");
            printNArrayTree(newPrefix, children.get(i), getChildrenFunc, i == children.size() - 1);
        }
    }

    private void printBinaryTree(T root) {
        List<List<String>> lines = new ArrayList<>();

        List<T> level = new ArrayList<T>();
        List<T> next = new ArrayList<T>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();

            nn = 0;

            for (T n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.getData().toString();
                    line.add(aa);
                    if (aa.length() > widest) {
                        widest = aa.length();
                    }

                    next.add((T) n.getLeft());
                    next.add((T) n.getRight());

                    if (n.getLeft() != null) {
                        nn++;
                    }
                    if (n.getRight() != null) {
                        nn++;
                    }
                }
            }

            if (widest % 2 == 1) {
                widest++;
            }

            lines.add(line);

            List<T> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (line.get(j) != null) {
                                c = '└';
                            }
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) {
                    f = "";
                }
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }
}
