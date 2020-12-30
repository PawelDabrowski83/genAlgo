package com.facebook.genAlgo.evaluator;

import com.facebook.genAlgo.gene.Gene;

public class Variant2EvaluatorImpl implements Evaluator {

    private final char target;

    public Variant2EvaluatorImpl(char target) {
        this.target = target;
    }

    @Override
    public float setFitness(Gene gene) {
        float delta = gene.getValues()[0] - target;
        return(float) ((655- delta) / 65535);
    }
}
