package data.structures.stack;

import data.structures.ArrayStorage;
import data.structures.Printable;
import java.io.PrintStream;

/**
 * Date: 14/01/22
 * Time: 12:50 am
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public abstract class PrintableStack<T> extends ArrayStorage<T> implements Printable {

    public PrintableStack(int capacity) {
        super(capacity);
    }

    @Override
    public void printOn(PrintStream outputStream) {
        int maxLength = maxLength();
        int width = 5 + maxLength;
        int firstHalfWidth = width / 2;
        int secondHalfWidth = firstHalfWidth + (width % 2);
        for (int i = this.storageArray.length - 1; i >= 0; i--) {
            if (this.storageArray[i] != null) {
                outputStream.println();
                outputStream.print("|");
                insertCharacter(' ', firstHalfWidth, outputStream);
                outputStream.printf("%" + maxLength + "s", this.storageArray[i]);
                insertCharacter(' ', secondHalfWidth, outputStream);
                outputStream.print("|");

                outputStream.println();
                outputStream.print("└");
                insertCharacter('─', (width + maxLength), outputStream);
                outputStream.print("┘");
            }
        }
    }

    private void insertCharacter(char value, int noOfSpaces, PrintStream outputStream) {
        for (int i = 0; i < noOfSpaces; i++) {
            outputStream.print(value);
        }
    }

    private int maxLength() {
        int maxLength = 0;
        for (int i = 0; i < this.storageArray.length; i++) {
            if (this.storageArray[i] != null) {
                int value = this.storageArray[i].toString().length();
                if (value > maxLength) {
                    maxLength = value;
                }
            }
        }
        return maxLength;
    }
}
