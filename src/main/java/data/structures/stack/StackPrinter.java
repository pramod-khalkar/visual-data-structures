package data.structures.stack;

import data.structures.Printable;

/**
 * Date: 14/01/22
 * Time: 12:50 am
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class StackPrinter<T> implements Printable {

    private final T[] storageArray;

    public StackPrinter(T[] storageArray) {
        this.storageArray = storageArray;
    }

    @Override
    public void print() {
        int maxLength = maxLength();
        int width = 5 + maxLength;
        int firstHalfWidth = width / 2;
        int secondHalfWidth = firstHalfWidth + (width % 2);
        for (int i = this.storageArray.length - 1; i >= 0; i--) {
            if (this.storageArray[i] != null) {
                System.out.println();
                System.out.print("|");
                insertCharacter(' ', firstHalfWidth);
                System.out.printf("%" + maxLength + "s", this.storageArray[i]);
                insertCharacter(' ', secondHalfWidth);
                System.out.print("|");

                System.out.println();
                System.out.print("└");
                insertCharacter('─', (width + maxLength));
                System.out.print("┘");
            }
        }
    }

    private void insertCharacter(char value, int noOfSpaces) {
        for (int i = 0; i < noOfSpaces; i++) {
            System.out.print(value);
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
