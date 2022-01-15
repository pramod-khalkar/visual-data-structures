package data.structures.gui;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import data.structures.queue.Queue;
import data.structures.queue.QueueImpl;
import data.structures.stack.Stack;
import data.structures.stack.StackImpl;
import data.structures.tree.AvlTree;
import data.structures.tree.BinarySearchTree;
import data.structures.tree.BinaryTree;
import data.structures.tree.GeneralTree;
import data.structures.tree.Tree;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

/**
 * Date: 15/01/22
 * Time: 12:30 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class GUIMainWindow extends JFrame implements ActionListener, ItemListener {

    private static final Pattern NUM_CHECK_PATTERN = Pattern.compile("^[0-9]*$");

    private final List<String> ALGORITHMS = Arrays.stream(Algo.values()).map(Algo::getValue).collect(Collectors.toList());
    private final List<String> DATA_TYPES = Arrays.stream(DataType.values()).map(DataType::getValue).collect(Collectors.toList());

    private JLabel algoTypeLabel, dataTypeLabel;
    private JToolBar tb;
    private JComboBox<String> algoTypeCombo, dataTypeCombo;
    private JButton insert, delete, clear;
    private JTextArea textArea;
    private JScrollPane textAreaScrollPane;


    public final static Integer DEFAULT_ARRAY_SIZE = 100;
    public final static DataType DEFAULT_DATA_TYPE = DataType.INTEGER;
    public final static Algo DEFAULT_ALGO = Algo.STACK;
    private Stack stack;
    private Queue queue;
    private Tree tree;
    private DataType selectedDataType;
    private Algo selectedAlgo;

    public GUIMainWindow(int width, int height) throws IOException {
        selectedDataType = DEFAULT_DATA_TYPE;
        selectedAlgo = DEFAULT_ALGO;
        setGUIComponent(width, height);
        initDataStructures();
    }

    private void setGUIComponent(int width, int height) throws IOException {
        setSize(width, height);
        setTitle("Data structures");
        setLayout(new BorderLayout());
        setToolBarUI();
        setVisualViewUI();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setToolBarUI() {
        JPanel tbPanel = new JPanel();
        algoTypeLabel = new JLabel("Algorithm");
        algoTypeCombo = new JComboBox<String>(ALGORITHMS.toArray(new String[0]));
        algoTypeCombo.addItemListener(this);
        dataTypeLabel = new JLabel("Data type");
        dataTypeCombo = new JComboBox<String>(DATA_TYPES.toArray(new String[0]));
        dataTypeCombo.addItemListener(this);
        insert = new JButton("Insert");
        insert.addActionListener(this);
        delete = new JButton("Delete");
        delete.addActionListener(this);
        clear = new JButton("Clear");
        clear.addActionListener(this);
        tbPanel.add(algoTypeLabel);
        tbPanel.add(algoTypeCombo);
        tbPanel.add(dataTypeLabel);
        tbPanel.add(dataTypeCombo);
        tbPanel.add(insert);
        tbPanel.add(delete);
        tbPanel.add(clear);
        tb = new JToolBar();
        tb.add(tbPanel);
        add(tb, BorderLayout.NORTH);
    }

    private void setVisualViewUI() throws IOException {
        textArea = new JTextArea("Visual representation of data structure");
        textArea.setEditable(false);
        textArea.setMargin(new Insets(50, 50, 5, 5));
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        textAreaScrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(textAreaScrollPane, BorderLayout.CENTER);
    }

    private <T> void initDataStructures() {
        TextAreaOutputStream textAreaOutputStream = new TextAreaOutputStream(textArea, System.out, "");
        System.setOut(textAreaOutputStream);
        resetAll();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == insert) {
            String userEnteredValue = JOptionPane.showInputDialog(this, "Enter value");
            try {
                if (validateInputWithSelectedDataType(userEnteredValue)) {
                    insertOperation(userEnteredValue);
                } else {
                    showErrorMsg(String.format("Data type mismatch(Required: %s)", selectedDataType.getValue()));
                }
            } catch (Exception ex) {
                showErrorMsg(ex.getMessage());
            }

        } else if (e.getSource() == delete) {

        } else if (e.getSource() == clear) {
            resetAll();
        }
    }

    private void showErrorMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", ERROR_MESSAGE);
    }

    private void showInfoMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean validateInputWithSelectedDataType(String inputValueByUser) {
        DataType typeByUser;
        if (isNumeric(inputValueByUser)) {
            if (isWithDecimal(inputValueByUser)) {
                typeByUser = DataType.FLOAT;
            } else {
                typeByUser = DataType.INTEGER;
            }
        } else {
            if (inputValueByUser.length() > 1) {
                typeByUser = DataType.STRING;
            } else {
                typeByUser = DataType.CHAR;
            }
        }
        return typeByUser == selectedDataType;
    }

    private boolean isWithDecimal(String numValue) {
        return numValue.contains(".");
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return NUM_CHECK_PATTERN.matcher(strNum).find();
    }

    private void insertOperation(Object value) {
        switch (this.selectedAlgo) {
            case STACK:
                stack.push(value);
                clearTextWindow();
                stack.print();
                break;
            case QUEUE:
                queue.enqueue(value);
                clearTextWindow();
                queue.print();
                break;
            case GENERAL:
            case BINARY:
            case BST:
            case AVL:
                tree.insert(value);
                clearTextWindow();
                tree.traverseAndPrint();
                break;
            default:
                showInfoMsg("Please select algorithm");
        }
    }

    private void clearTextWindow() {
        this.textArea.setText("");
    }

    private void resetAll() {
        textArea.setText("");
        stack = new StackImpl<>(DEFAULT_ARRAY_SIZE);
        queue = new QueueImpl<>(DEFAULT_ARRAY_SIZE);
        switch (selectedAlgo) {
            case BST:
                tree = new BinarySearchTree<>();
                break;
            case AVL:
                tree = new AvlTree<>();
                break;
            case GENERAL:
                tree = new GeneralTree<>();
                break;
            default:
                tree = new BinaryTree<>();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == dataTypeCombo) {
            if (Objects.nonNull(dataTypeCombo.getSelectedItem())) {
                this.selectedDataType = DataType.getType(dataTypeCombo.getSelectedItem().toString());
                resetAll();
            }
        } else if (e.getSource() == algoTypeCombo) {
            if (Objects.nonNull(algoTypeCombo.getSelectedItem())) {
                this.selectedAlgo = Algo.getAlgo(algoTypeCombo.getSelectedItem().toString());
                resetAll();
            }
        }
    }

    enum Algo {
        STACK("Stack"),
        QUEUE("Queue"),
        BINARY("Binary tree"),
        BST("Binary search tree"),
        AVL("AVL tree"),
        GENERAL("General tree");

        private final String value;

        Algo(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Algo getAlgo(String valueStr) {
            return Arrays.stream(Algo.values())
                    .filter(val -> val.getValue().equals(valueStr))
                    .findAny()
                    .orElse(STACK);
        }
    }

    enum DataType {
        INTEGER("Integer"),
        FLOAT("Float"),
        STRING("String"),
        CHAR("Character");

        private final String value;

        DataType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public static DataType getType(String valueStr) {
            return Arrays.stream(DataType.values())
                    .filter(val -> val.getValue().equals(valueStr))
                    .findAny()
                    .orElse(STRING);
        }
    }
}
