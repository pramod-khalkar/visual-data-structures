package data.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;

import data.utils.Helper;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Optional;
import java.util.Stack;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * @author : Pramod Khalkar
 * @since : 14/08/22, Sun
 * description: This file belongs to visual-data-structures
 **/
public class StackViewer extends TitlePanel implements StackEvent {
    private final static Stack<Long> stack = new Stack<>();
    private final StackView stackView;

    public StackViewer() {
        super("Stack");
        Arrays.stream(Helper.randomNumbers(10)).forEach(stack::push);
        stackView = new StackView(stack);
        add(new StackComponentPanel(stackView, this));
    }

    @Override
    public void clear() {
        if (!stack.isEmpty()) {
            stack.clear();
            stackView.repaint();
        }
    }

    @Override
    public Long pop() {
        Long item = null;
        if (!stack.isEmpty()) {
            item = stack.pop();
            stackView.repaint();
        }
        return item;
    }

    @Override
    public void push(Long item) {
        stack.push(item);
        stackView.repaint();
    }

    static class StackComponentPanel extends JPanel implements ActionListener {
        private final JButton push, pop, clear;
        private final JTextField inputField;
        private final StackEvent stackEvent;

        public StackComponentPanel(StackView stackView, StackEvent stackEvent) {
            this.stackEvent = stackEvent;
            push = new JButton("Push");
            push.addActionListener(this);
            pop = new JButton("Pop");
            pop.addActionListener(this);
            clear = new JButton("Clear");
            clear.addActionListener(this);
            inputField = new JTextField();
            inputField.setToolTipText("Number allowed only");
            setLayout(new BorderLayout());
            Box hBox = Box.createHorizontalBox();
            hBox.add(new JLabel("   Input:  "));
            hBox.add(inputField);
            hBox.add(push);
            hBox.add(pop);
            hBox.add(clear);
            add(hBox, NORTH);
            JScrollPane sPane = new JScrollPane(stackView);
            add(sPane, CENTER);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == pop) {
                stackEvent.pop();
            } else if (source == push) {
                Optional<Long> value = Helper.checkAndGetValidInput(inputField);
                value.ifPresent(stackEvent::push);
                inputField.setText("");
                inputField.setFocusable(true);
            } else if (source == clear) {
                stackEvent.clear();
            }
        }
    }

    static class StackView extends JPanel {
        private final java.util.Stack<Long> stack;
        private static final int BOX_WIDTH = 150;
        private static final int BOX_HEIGHT = 30;
        private static final int BOTTOM_MARGIN = BOX_HEIGHT + 20;
        private static final int TOP_MARGIN = BOX_HEIGHT + 20;
        private static final int LINE_HEIGHT = 20;
        private int x, y;

        public StackView(java.util.Stack<Long> stack) {
            this.stack = stack;
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            x = getWidth() / 2 - (BOX_WIDTH / 2);
            y = getHeight() - BOTTOM_MARGIN;
            for (int i = 0; i < stack.size(); i++) {
                if (i == stack.size() - 1) {
                    g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    int y1 = y + (BOX_HEIGHT / 2);
                    String label = "Top";
                    g2d.drawLine(x, y1, x - LINE_HEIGHT, y1);
                    g2d.drawLine(x, y1, x - 5, y1 - 5);
                    g2d.drawLine(x, y1, x - 5, y1 + 5);
                    g2d.drawString(label, x - (LINE_HEIGHT + (label.length() * 9)), y1);

                    g2d.drawLine(x + BOX_WIDTH, y1, x + BOX_WIDTH + LINE_HEIGHT, y1);
                    g2d.drawLine(x + BOX_WIDTH, y1, x + BOX_WIDTH + 5, y1 - 5);
                    g2d.drawLine(x + BOX_WIDTH, y1, x + BOX_WIDTH + 5, y1 + 5);
                    g2d.drawString(label, x + BOX_WIDTH + LINE_HEIGHT + 2, y1);
                }
                g.setColor(Color.decode("#BDDBFF"));
                g.fill3DRect(x, y, BOX_WIDTH, BOX_HEIGHT, true);
                g.setColor(Color.BLACK);
                String item = String.valueOf(stack.get(i));
                g.drawString(item, (x + ((BOX_WIDTH - (item.length() * 7)) / 2)), (y + (BOX_HEIGHT / 2)) + 5);
                y -= BOX_HEIGHT + 1;
                if (y <= TOP_MARGIN) {
                    Dimension d = getPreferredSize();
                    int height = d.height + TOP_MARGIN;
                    setPreferredSize(new Dimension(d.width, height));
                    revalidate();
                }
            }
        }
    }
}
