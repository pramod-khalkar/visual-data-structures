package data.gui;

import static data.gui.AlgorithmNames.Queue;
import static data.gui.AlgorithmNames.Stack;
import static data.utils.Helper.isNumeric;
import static data.utils.Helper.isWithDecimal;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

import data.structures.Printable;
import data.structures.queue.SimpleQueue;
import data.structures.stack.SimpleStack;
import data.structures.tree.AvlTree;
import data.structures.tree.BSTree;
import data.structures.tree.SplayTree;
import data.structures.tree.Treap;
import data.structures.tree.unbalance.BinaryTree;
import data.structures.tree.unbalance.GeneralTree;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
 * Date: 20/01/22
 * Time: 1:31 am
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public abstract class InitialUISetup extends JFrame implements ActionListener, ItemListener {

    private JLabel algoTypeLabel, dataTypeLabel;
    private JToolBar tb;
    private JComboBox<AlgorithmHolder> algoTypeCombo;
    private JComboBox<DataTypeHolder> dataTypeCombo;
    private JButton insert, delete, clear;
    private JTextArea textArea;
    private JScrollPane textAreaScrollPane;
    private DataTypeHolder selectedDataTypeHolder;
    private AlgorithmHolder selectedAlgoDetails;
    private TextAreaOutputStream textAreaOutputStream;

    private final List<AlgorithmHolder> algorithmHolderList = Stream.of(
            new AlgorithmHolder(Stack, new SimpleStack<>()),
            new AlgorithmHolder(AlgorithmNames.Queue, new SimpleQueue<>()),
            new AlgorithmHolder(AlgorithmNames.BinaryTree, new BinaryTree<>()),
            new AlgorithmHolder(AlgorithmNames.BSTree, new BSTree<>()),
            new AlgorithmHolder(AlgorithmNames.AvlTree, new AvlTree<>()),
            new AlgorithmHolder(AlgorithmNames.SplayTree, new SplayTree<>()),
            new AlgorithmHolder(AlgorithmNames.Treap, new Treap<>()),
            new AlgorithmHolder(AlgorithmNames.GeneralTree, new GeneralTree<>())
    ).collect(Collectors.toList());

    private final List<DataTypeHolder> dataTypeHolderList = Stream.of(
            new DataTypeHolder("Integer", Integer.class),
            new DataTypeHolder("Float", Float.class),
            new DataTypeHolder("String", String.class),
            new DataTypeHolder("Character", Character.class)
    ).collect(Collectors.toList());

    abstract <T extends Comparable<? super T>> void insert(T value, AlgorithmHolder algo) throws Exception;

    abstract <T extends Comparable<? super T>> void delete(T value, AlgorithmHolder algo);

    abstract void clear(AlgorithmHolder algo);

    protected InitialUISetup(int width, int height) throws IOException {
        setGUIComponent(width, height);
        this.selectedDataTypeHolder = dataTypeHolderList.get(0);
        this.selectedAlgoDetails = algorithmHolderList.get(0);
    }

    private void setGUIComponent(int width, int height) throws IOException {
        setSize(width, height);
        setTitle("Data structures");
        setLayout(new BorderLayout());
        setToolBarUI();
        setTxtAreaConsole();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setToolBarUI() {
        JPanel tbPanel = new JPanel();
        algoTypeLabel = new JLabel("Algorithm");
        algoTypeCombo = new JComboBox<>(algorithmHolderList.toArray(new AlgorithmHolder[0]));
        algoTypeCombo.addItemListener(this);
        dataTypeLabel = new JLabel("Data type");
        dataTypeCombo = new JComboBox<>(dataTypeHolderList.toArray(new DataTypeHolder[0]));
        dataTypeCombo.addItemListener(this);
        insert = new JButton("Push");
        insert.addActionListener(this);
        delete = new JButton("Pop");
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

    private void setTxtAreaConsole() throws IOException {
        textArea = new JTextArea("Visual representation of data structure");
        textAreaOutputStream = new TextAreaOutputStream(textArea, System.out);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(50, 50, 5, 5));
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        textAreaScrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(textAreaScrollPane, BorderLayout.CENTER);
    }

    // Dialogs
    protected void showErrorMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", ERROR_MESSAGE);
    }

    protected void showInfoMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == dataTypeCombo) {
            this.selectedDataTypeHolder = (DataTypeHolder) dataTypeCombo.getSelectedItem();
            clear(this.selectedAlgoDetails);
        } else if (e.getSource() == algoTypeCombo) {
            if (this.selectedAlgoDetails != null) {
                clearScreenAndPrint(this.selectedAlgoDetails.getAlgorithm());
                if (this.selectedAlgoDetails.getDisplayName() == Stack) {
                    insert.setText("Push");
                    delete.setText("Pop");
                } else if (this.selectedAlgoDetails.getDisplayName() == AlgorithmNames.Queue) {
                    insert.setText("Enqueue");
                    delete.setText("Dequeue");
                } else {
                    insert.setText("Insert");
                    delete.setText("Delete");
                }
            }
            this.selectedAlgoDetails = (AlgorithmHolder) algoTypeCombo.getSelectedItem();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == insert || e.getSource() == delete) {
            if ((this.selectedAlgoDetails.getDisplayName() == Stack || this.selectedAlgoDetails.getDisplayName() == Queue)
                    && e.getSource() == delete) {
                delete(null, this.selectedAlgoDetails);
            } else {
                String userInput = JOptionPane.showInputDialog(this, "Enter value");
                try {
                    if (userInput != null && !validateInputAndTrigger(userInput, e)) {
                        showErrorMsg(String.format("Invalid input exception, required %s", this.selectedDataTypeHolder.getClazz().getSimpleName()));
                    }
                } catch (Exception ex) {
                    showErrorMsg(ex.getMessage());
                }
            }
        } else if (e.getSource() == clear) {
            clear(this.selectedAlgoDetails);
        }
    }

    private boolean validateInputAndTrigger(String userInput, ActionEvent e) throws Exception {
        if (isNumeric(userInput)) {
            if (isWithDecimal(userInput)) {
                if (this.selectedDataTypeHolder.getClazz() == Float.class) {
                    if (e.getSource() == insert) {
                        insert(Float.valueOf(userInput), selectedAlgoDetails);
                    } else if (e.getSource() == delete) {
                        delete(Float.valueOf(userInput), selectedAlgoDetails);
                    }
                    return true;
                }
            } else {
                if (this.selectedDataTypeHolder.getClazz() == Integer.class) {
                    if (e.getSource() == insert) {
                        insert(Integer.valueOf(userInput), selectedAlgoDetails);
                    } else if (e.getSource() == delete) {
                        delete(Integer.valueOf(userInput), selectedAlgoDetails);
                    }
                    return true;
                }
            }
        } else {
            if (userInput.length() > 1) {
                if (this.selectedDataTypeHolder.getClazz() == String.class) {
                    if (e.getSource() == insert) {
                        insert(userInput, selectedAlgoDetails);
                    } else if (e.getSource() == delete) {
                        delete(userInput, selectedAlgoDetails);
                    }
                    return true;
                }
            } else {
                if (this.selectedDataTypeHolder.getClazz() == Character.class) {
                    if (e.getSource() == insert) {
                        insert(userInput.charAt(0), selectedAlgoDetails);
                    } else if (e.getSource() == delete) {
                        delete(userInput.charAt(0), selectedAlgoDetails);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    protected void printOnScreen(Printable printable) {
        printable.printOn(textAreaOutputStream);
    }

    protected void clearScreen() {
        this.textArea.setText("");
    }

    protected void clearScreenAndPrint(Printable printable) {
        clearScreen();
        printOnScreen(printable);
    }
}
