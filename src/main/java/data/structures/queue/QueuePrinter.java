package data.structures.queue;

import data.structures.Printable;

/**
 * Date: 14/01/22
 * Time: 1:54 am
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class QueuePrinter<T> implements Printable {

    private final T[] arr;
    private final int rear, front;
    private final Queue<T> queue;

    public QueuePrinter(Queue<T> queue, T[] arr, int rear, int front) {
        this.arr = arr;
        this.rear = rear;
        this.front = front;
        this.queue = queue;
    }

    @Override
    public void print() {
        int maxWordWidth = findMaxWidth(this.arr);
        int maxWidth = 5 + maxWordWidth;
        int firstHalfWidth = (maxWidth - maxWordWidth) / 2;
        int secondHalfWidth = firstHalfWidth + ((maxWidth - maxWordWidth) % 2);
        if (!queue.isEmpty()) {
            String line1 = "┍" + insertCharacter('-', maxWidth) + "┑";//"+--------------+";
            String line2 = "|" + insertCharacter(' ', firstHalfWidth)
                    + "%" + maxWordWidth + "s"
                    + insertCharacter(' ', secondHalfWidth) + "|";
            String line3 = "┕" + insertCharacter('-', maxWidth) + "┙";//"+--------------+";
            StringBuilder upperBorder = new StringBuilder();
            StringBuilder midleBorder = new StringBuilder();
            StringBuilder lowerBorder = new StringBuilder();

            for (int i = this.rear; i >= this.front; i--) {
                upperBorder.append(line1);
                midleBorder.append(String.format(line2, this.arr[i]));
                lowerBorder.append(line3);
            }
            upperBorder.insert(0, "         ");
            midleBorder.insert(0, "FRONT-->>");
            lowerBorder.insert(0, "         ");
            upperBorder.append("         ");
            midleBorder.append(" REAR-->>");
            lowerBorder.append("         ");

            System.out.println(upperBorder);
            System.out.println(midleBorder);
            System.out.println(lowerBorder);
        }
    }

    private String insertCharacter(char character, int times) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            builder.append(character);
        }
        return builder.toString();
    }

    private int findMaxWidth(T[] storageArray) {
        int max = 0;
        for (T t : storageArray) {
            if (t != null) {
                if (t.toString().length() > max) {
                    max = t.toString().length();
                }
            }
        }
        return max;
    }
}
