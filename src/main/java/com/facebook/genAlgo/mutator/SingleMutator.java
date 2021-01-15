package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.utils.RandomProvider;

public class SingleMutator implements MutatorService {

    private final RandomProvider randomProvider;
    private float mutationChance;

    public SingleMutator(RandomProvider randomProvider, float mutationChance) {
        this.randomProvider = randomProvider;
        this.mutationChance = mutationChance;
    }

    @Override
    public void mutate(Gene gene) {

    }
}
