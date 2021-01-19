package com.facebook.genAlgo.evaluator;

import com.facebook.genAlgo.gene.Gene;

public class MaxDeltaEvaluatorImpl implements Evaluator {

    private final char target;

    public MaxDeltaEvaluatorImpl(char target) {
        this.target = target;
    }

    @Override
    public void setFitness(Gene gene) {
        float delta = Math.abs(gene.getValue() - target);
        float fitness = (float) ((65535- delta) / 65535);
        gene.setFitness(fitness);
    }
}
