package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.utils.RandomProvider;

public class MultipleMutator implements MutatorService {

    private final RandomProvider randomProvider;

    public MultipleMutator(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
    }

    @Override
    public void mutate(Gene gene) {

    }
}
