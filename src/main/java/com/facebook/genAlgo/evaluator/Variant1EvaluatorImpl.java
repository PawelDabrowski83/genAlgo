package com.facebook.genAlgo.evaluator;

import com.facebook.genAlgo.gene.Gene;

import static java.lang.Math.log;

public class Variant1EvaluatorImpl implements Evaluator {

  private final char target;

  public Variant1EvaluatorImpl(char target) {
    this.target = target;
  }

  @Override
  public float setFitness(Gene gene) {
    float delta = gene.getValues()[0] - target;
    return (float) (1 / (1 + log(1 + delta)));
  }
}
