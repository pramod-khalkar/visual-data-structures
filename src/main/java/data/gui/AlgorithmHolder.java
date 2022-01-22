package data.gui;

import data.structures.Algorithm;

/**
 * Date: 20/01/22
 * Time: 1:38 am
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class AlgorithmHolder {
    private final AlgorithmNames displayName;
    private final Algorithm algorithm;

    public AlgorithmHolder(AlgorithmNames displayName, Algorithm algorithm) {
        this.displayName = displayName;
        this.algorithm = algorithm;
    }

    public AlgorithmNames getDisplayName() {
        return displayName;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    @Override
    public String toString() {
        return this.displayName.name();
    }
}
