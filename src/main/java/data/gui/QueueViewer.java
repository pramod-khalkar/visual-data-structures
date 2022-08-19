package data.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;

import data.utils.Helper;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author : Pramod Khalkar
 * @since : 14/08/22, Sun
 * description: This file belongs to visual-data-structures
 **/
public class QueueViewer extends AbstractPanel implements QueueEvent {
    private final Queue<Long> queue = new LinkedList<>();
    private QueueView queueView;

    public QueueViewer() {
        super("Queue");
        queue.addAll(Arrays.asList(Helper.randomNumbers(5)));
        queueView = new QueueView(queue);
        add(new QueueComponentPanel(queueView, this));
    }

    @Override
    public void enqueue() {
        queue.offer(acceptInputInLong());
        queueView.repaint();
    }

    @Override
    public void dequeue() {
        if (!queue.isEmpty()) {
            queue.poll();
            queueView.repaint();
        }
    }

    @Override
    public void clear() {
        if (!queue.isEmpty()) {
            queue.clear();
            repaint();
        }
    }

    static class QueueComponentPanel extends JPanel {

        public QueueComponentPanel(QueueView queueView, QueueEvent queueEvent) {
            setLayout(new BorderLayout());
            add(new ButtonPanel(queueEvent), NORTH);
            JScrollPane sPane = new JScrollPane(queueView);
            add(sPane, CENTER);
        }
    }

    static class QueueView extends JPanel {
        private java.util.Queue<Long> queue;
        private static final int BOX_WIDTH = 80;
        private static final int BOX_HEIGHT = 50;
        private static final int LEFT_MARGIN = 10;
        private static final int RIGHT_MARGIN = 10;
        private static final int LINE_HEIGHT = 20;
        private int x, y;

        public QueueView(Queue<Long> queue) {
            this.queue = queue;
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            x = LEFT_MARGIN;
            y = (getHeight() / 2) - 50;
            List<Long> list = new LinkedList<>(this.queue);
            Collections.reverse(list);
            for (int i = 0; i < list.size(); i++) {
                g.setColor(Color.decode("#BDDBFF"));
                g.fill3DRect(x, y, BOX_WIDTH, BOX_HEIGHT, true);
                g.setColor(Color.BLACK);
                String item = String.valueOf(list.get(i));
                g.drawString(item, (x + ((BOX_WIDTH - (item.length() * 7)) / 2)), (y + (BOX_HEIGHT / 2)) + 5);
                if (i == 0 || i == list.size() - 1) {
                    int x1 = x + (BOX_WIDTH / 2);
                    if (list.size() == 1) {
                        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                        g2d.drawLine(x + 5, y - LINE_HEIGHT, x + 5, y);
                        g2d.drawLine(x + 5, y, (x + 5) - 5, y - 5);
                        g2d.drawLine(x + 5, y, (x + 5) + 5, y - 5);
                        g2d.drawString("Rear", x + 5, y - 20);

                        g2d.drawLine(x + (BOX_WIDTH - 5), y - LINE_HEIGHT, x + (BOX_WIDTH - 5), y);
                        g2d.drawLine(x + (BOX_WIDTH - 5), y, (x + BOX_WIDTH) - 10, y - 5);
                        g2d.drawLine(x + (BOX_WIDTH - 5), y, (x + BOX_WIDTH), y - 5);
                        g2d.drawString("Front", x + (BOX_WIDTH - 5), y - 20);
                    } else {
                        if (i == 0) {
                            g2d.drawString("Rear", x1, y - 20);
                        } else {
                            g2d.drawString("Front", x1, y - 20);
                        }
                        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                        g2d.drawLine(x1, y - LINE_HEIGHT, x1, y);
                        g2d.drawLine(x1, y, x1 - 5, y - 5);
                        g2d.drawLine(x1, y, x1 + 5, y - 5);
                    }
                }
                x += BOX_WIDTH + 1;
                if (x >= (getWidth() + RIGHT_MARGIN + LEFT_MARGIN)) {
                    Dimension d = getPreferredSize();
                    int width = x + RIGHT_MARGIN;
                    setPreferredSize(new Dimension(width, d.height));
                    revalidate();
                }
            }
        }
    }

    static class ButtonPanel extends JPanel implements ActionListener {
        private final JButton enqueue, dequeue, clear;
        private final QueueEvent queueEvent;

        public ButtonPanel(QueueEvent queueEvent) {
            this.queueEvent = queueEvent;
            setLayout(new FlowLayout());
            enqueue = new JButton("Enqueue");
            enqueue.addActionListener(this);
            add(enqueue);
            dequeue = new JButton("Dequeue");
            dequeue.addActionListener(this);
            add(dequeue);
            clear = new JButton("Clear");
            clear.addActionListener(this);
            add(clear);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == dequeue) {
                queueEvent.dequeue();
            } else if (source == enqueue) {
                queueEvent.enqueue();
            } else if (source == clear) {
                queueEvent.clear();
            }
        }
    }
}
