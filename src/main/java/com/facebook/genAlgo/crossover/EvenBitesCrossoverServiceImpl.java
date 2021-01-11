package com.facebook.genAlgo.crossover;

import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.utils.BitwiseUtils;

public class EvenBitesCrossoverServiceImpl implements CrossoverService {

    private final BitwiseUtils bitwiseUtils;

    public EvenBitesCrossoverServiceImpl() {
        bitwiseUtils = new BitwiseUtils();
    }

    @Override
    public void cross(Gene gene1, Gene gene2) {

    }
}
