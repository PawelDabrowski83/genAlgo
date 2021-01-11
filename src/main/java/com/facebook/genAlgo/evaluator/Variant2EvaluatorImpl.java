package com.facebook.genAlgo.evaluator;

import com.facebook.genAlgo.gene.Gene;

public class Variant2EvaluatorImpl implements Evaluator {

    private final char target;

    public Variant2EvaluatorImpl(char target) {
        this.target = target;
    }

    @Override
    public void setFitness(Gene gene) {
        float delta = Math.abs(gene.getValue() - target);
        float fitness = (float) ((65535- delta) / 65535);
        gene.setFitness(fitness);
    }
}
