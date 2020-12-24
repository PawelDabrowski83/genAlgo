package com.facebook.genAlgo.gene;

import com.facebook.genAlgo.utils.RandomProvider;

public class Gene {

    private RandomProvider randomProvider;
    private char[] values;

    public Gene(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
        generateValuesLength();
        generateValues();
    }

    private void generateValuesLength() {
        int length = randomProvider.getRandom(10);
        values = new char[length];
    }

    private void generateValues() {
        for (int i = 0; i < values.length; i++) {
            values[i] = (char) randomProvider.getRandom(100);

        }
    }

    public char[] getValues() {
        return values;
    }
}
