package com.facebook.genAlgo.evaluator;

import com.facebook.genAlgo.gene.Gene;


import static java.lang.Math.log10;

public class Variant1EvaluatorImpl implements Evaluator {

  private final char target;

  public Variant1EvaluatorImpl(char target) {
    this.target = target;
  }
  @Override
  public void setFitness(Gene gene) {
    float delta = Math.abs(gene.getValue() - target);
    return (float) (1 / (1 + log10(1 + delta)));
  }
}
