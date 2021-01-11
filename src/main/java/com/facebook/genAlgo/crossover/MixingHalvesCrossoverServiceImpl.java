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
        char gene1Value = calculateValue(gene1, gene2);
        char gene2Value = calculateValue(gene2, gene1);

        gene1.setValue(gene1Value);
        gene2.setValue(gene2Value);
    }

    private char calculateValue(Gene geneTarget, Gene geneSource) {
        int geneTargetValue = geneTarget.getValue();
        int secondHalfGene1 = bitwiseUtils.getByte(geneSource.getValue(), 1);
        geneTargetValue = bitwiseUtils.setByte(geneTargetValue, 1, secondHalfGene1);
        return (char) geneTargetValue;
    }
}
