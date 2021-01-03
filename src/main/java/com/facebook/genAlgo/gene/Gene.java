package com.facebook.genAlgo.gene;

import com.facebook.genAlgo.utils.RandomProvider;

public class Gene {

    private final RandomProvider randomProvider;
    private char value;

    public Gene(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
        generateValue();
    }

    private void generateValue() {
        value = (char) randomProvider.getRandom(1000);
    }

    public char getValue() {
        return value;
    }
}
