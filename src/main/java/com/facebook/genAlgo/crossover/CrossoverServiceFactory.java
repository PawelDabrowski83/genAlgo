package com.facebook.genAlgo.crossover;

public class CrossoverServiceFactory {

    public enum CrossoverServiceEnum {
        DEFAULT,
        BIT_PAIR,
        EVEN_BITS,
        MIXING_HALVES,
        ODD_BITS
    }

    public CrossoverService getCrossoverService() {
        return null;
    }

    public CrossoverService getCrossoverService(CrossoverServiceEnum option) {
        return null;
    }
}
