package com.facebook.genAlgo.evaluator;

import com.facebook.genAlgo.gene.Gene;

public class Variant1EvaluatorImpl implements Evaluator {

    private final char target;

    public Variant1EvaluatorImpl(char target) {
        this.target = target;
    }

    @Override
    public float setFitness(Gene gene) {
        return 0;
    }
}
