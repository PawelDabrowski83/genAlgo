package com.facebook.genAlgo.crossover;

public class CrossoverServiceFactory {

    public enum CrossoverEnum {
        DEFAULT,
        BIT_PAIR,
        EVEN_BITS,
        MIXING_HALVES,
        ODD_BITS
    }

    public CrossoverService getCrossoverService() {
        return getCrossoverService(CrossoverEnum.DEFAULT);
    }

    public CrossoverService getCrossoverService(CrossoverEnum option) {
        CrossoverService crossoverService = null;
        switch (option) {
            case DEFAULT, BIT_PAIR -> crossoverService = new BitPairCrossoverServiceImpl();
            case EVEN_BITS -> crossoverService = new EvenBitsCrossoverServiceImpl();
            case MIXING_HALVES -> crossoverService = new MixingHalvesCrossoverServiceImpl();
            case ODD_BITS -> crossoverService = new OddBitsCrossoverServiceImpl();
        }
        return crossoverService;
    }
}
