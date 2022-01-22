package data.structures.tree;

import data.structures.Printable;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Date: 05/01/22
 * Time: 5:59 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public abstract class PrintableTree<T> extends AbstractTree<T> implements Printable {

    protected abstract Type getTypeOfTree();

    @Override
    public void printOn(PrintStream outputStream) {
        Node<T> root = getRootNode();
        if (root != null) {
            if (getTypeOfTree() == Type.N_ARRAY) {
                printNArrayTree("      ", root, getChildrenFunc(getTypeOfTree()), false, outputStream);
            } else {
                printBinaryTree(getRootNode(), outputStream);
            }
        }
    }

    private Function<Node<T>, List<Node<T>>> getChildrenFunc(Type type) {
        switch (type) {
            case N_ARRAY:
                return (node -> {
                    List<Node<T>> childs = new ArrayList<>();
                    if (node.getLeft() != null) {
                        Node<T> child = node.getLeft();
                        while (child != null) {
                            childs.add(child);
                            child = child.getRight();
                        }
                    }
                    return childs;
                });
            case BINARY:
            default:
                return (node -> {
                    List<Node<T>> childs = new ArrayList<>();
                    if (node.getLeft() != null) {
                        childs.add(node.getLeft());
                    }
                    if (node.getRight() != null) {
                        childs.add(node.getRight());
                    }
                    return childs;
                });
        }
    }


    private void printNArrayTree(String prefix, Node<T> node,
                                 Function<Node<T>, List<Node<T>>> getChildrenFunc,
                                 boolean isTail,
                                 PrintStream outputStream) {
        String nodeName = node.toString();
        String nodeConnection = isTail ? "└── " : "├── ";
        outputStream.println(prefix + nodeConnection + nodeName);
        List<Node<T>> children = getChildrenFunc.apply(node);
        for (int i = 0; i < children.size(); i++) {
            String newPrefix = prefix + (isTail ? "    " : "│   ");
            printNArrayTree(newPrefix, children.get(i), getChildrenFunc, i == children.size() - 1, outputStream);
        }
    }

    private void printBinaryTree(Node<T> root, PrintStream outputStream) {
        List<List<String>> lines = new ArrayList<>();

        List<Node<T>> level = new ArrayList<>();
        List<Node<T>> next = new ArrayList<>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();

            nn = 0;

            for (Node<T> n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    // Actual node value goes here
                    String aa = n.toString();
                    line.add(aa);
                    if (aa.length() > widest) {
                        widest = aa.length();
                    }

                    next.add(n.getLeft());
                    next.add(n.getRight());

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

            List<Node<T>> tmp = level;
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
                    outputStream.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            outputStream.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            outputStream.print(j % 2 == 0 ? " " : "─");
                        }
                        outputStream.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            outputStream.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                outputStream.println();
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
                    outputStream.print(" ");
                }
                outputStream.print(f);
                for (int k = 0; k < gap2; k++) {
                    outputStream.print(" ");
                }
            }
            outputStream.println();

            perpiece /= 2;
        }
    }
}
