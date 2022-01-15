package data;

import data.structures.gui.GUIMainWindow;
import java.io.IOException;

/**
 * Date: 31/12/21
 * Time: 12:14 am
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class ApplicationLancher {

    public static void main(String[] args) throws IOException {
        new GUIMainWindow(800, 500);
    }
}
