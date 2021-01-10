package com.facebook.genAlgo.crossover;

import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.utils.BitwiseUtils;

public class MixingHalvesCrossoverServiceImpl implements CrossoverService{

    private final BitwiseUtils bitwiseUtils;

    public MixingHalvesCrossoverServiceImpl() {
        this.bitwiseUtils = new BitwiseUtils();
    }

    @Override
    public void cross(Gene gene1, Gene gene2) {
        int targetGene1 = gene1.getValue();
        int targetGene2 = gene2.getValue();

        int secondHalfGene1 = bitwiseUtils.getByte(targetGene2, 1);
        int secondHalfGene2 = bitwiseUtils.getByte(targetGene1, 1);

        targetGene1 = bitwiseUtils.setByte(targetGene1, 1, secondHalfGene1);
        targetGene2 = bitwiseUtils.setByte(targetGene2, 1, secondHalfGene2);

        gene1.setValue((char) targetGene1);
        gene2.setValue((char) targetGene2);
    }
}
