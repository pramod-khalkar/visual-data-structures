package data.structures.queue;

import data.structures.Printable;
import java.io.PrintStream;

/**
 * Date: 14/01/22
 * Time: 1:54 am
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public abstract class PrintableQueue<T> extends AbstractQueue<T> implements Printable {

    public PrintableQueue(int capacity) {
        super(capacity);
    }

    @Override
    public void printOn(PrintStream outputStream) {
        int maxWordWidth = findMaxWidth(this.storageArray);
        int maxWidth = 5 + maxWordWidth;
        int firstHalfWidth = (maxWidth - maxWordWidth) / 2;
        int secondHalfWidth = firstHalfWidth + ((maxWidth - maxWordWidth) % 2);
        if (this.front != -1) {
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
                midleBorder.append(String.format(line2, this.storageArray[i]));
                lowerBorder.append(line3);
            }
            upperBorder.insert(0, "         ");
            midleBorder.insert(0, "FRONT-->>");
            lowerBorder.insert(0, "         ");
            upperBorder.append("         ");
            midleBorder.append(" REAR-->>");
            lowerBorder.append("         ");

            outputStream.println(upperBorder);
            outputStream.println(midleBorder);
            outputStream.println(lowerBorder);
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
