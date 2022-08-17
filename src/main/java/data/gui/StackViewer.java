package data.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;

import data.utils.Helper;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author : Pramod Khalkar
 * @since : 14/08/22, Sun
 * description: This file belongs to visual-data-structures
 **/
public class StackViewer extends AbstractPanel implements StackEvent {
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
    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            stackView.repaint();
        }
    }

    @Override
    public void push() {
        stack.push(acceptInputInLong());
        stackView.repaint();
    }

    static class ButtonPanel extends JPanel implements ActionListener {
        private final JButton push, pop, clear;
        private StackEvent stackEvent;

        public ButtonPanel(StackEvent stackEvent) {
            this.stackEvent = stackEvent;
            setLayout(new FlowLayout());
            push = new JButton("Push");
            push.addActionListener(this);
            add(push);
            pop = new JButton("Pop");
            pop.addActionListener(this);
            add(pop);
            clear = new JButton("Clear");
            clear.addActionListener(this);
            add(clear);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == pop) {
                stackEvent.pop();
            } else if (source == push) {
                stackEvent.push();
            } else if (source == clear) {
                stackEvent.clear();
            }
        }
    }

    static class StackComponentPanel extends JPanel {

        public StackComponentPanel(StackView stackView, StackEvent stackEvent) {
            setLayout(new BorderLayout());
            add(new ButtonPanel(stackEvent), NORTH);
            add(stackView, CENTER);
        }
    }

    static class StackView extends JPanel {
        private final java.util.Stack<Long> stack;
        private static final int BOX_WIDTH = 150;
        private static final int BOX_HEIGHT = 30;
        private static final int BOTTOM_MARGIN = BOX_HEIGHT + 20;
        private static final int LINE_HEIGHT = 20;
        private int x, y;

        public StackView(java.util.Stack<Long> stack) {
            this.stack = stack;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
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
                g.setColor(Color.LIGHT_GRAY);
                g.fill3DRect(x, y, BOX_WIDTH, BOX_HEIGHT, true);
                g.setColor(Color.BLACK);
                String item = String.valueOf(stack.get(i));
                g.drawString(item, (x + ((BOX_WIDTH - (item.length() * 7)) / 2)), (y + (BOX_HEIGHT / 2)) + 5);
                y -= BOX_HEIGHT + 1;
            }
        }
    }
}
