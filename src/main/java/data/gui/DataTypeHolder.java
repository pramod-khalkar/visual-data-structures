package data.gui;

/**
 * Date: 20/01/22
 * Time: 1:39 am
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class DataTypeHolder {
    private final String displayName;
    private final Class<?> clazz;

    DataTypeHolder(String displayName, Class<?> clazz) {
        this.displayName = displayName;
        this.clazz = clazz;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
