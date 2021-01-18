package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.utils.BitwiseUtils;
import com.facebook.genAlgo.utils.RandomProvider;

public class MultipleMutator implements MutatorService {

    private final RandomProvider randomProvider;
    private final BitwiseUtils bitwiseUtils = new BitwiseUtils();
    private float mutationChance;

    public MultipleMutator(RandomProvider randomProvider, float mutationChance) {
        this.randomProvider = randomProvider;
        this.mutationChance = mutationChance;
    }

    public float getMutationChance() {
        return mutationChance;
    }

    public void setMutationChance(float mutationChance) {
        this.mutationChance = mutationChance;
    }

    @Override
    public void mutate(Gene gene) {
        if (mutationOccurs()) {
            int bitsToMutate = randomProvider.getInt(16);
            char geneValue = gene.getValue();

            for (int i = 0; i < bitsToMutate; i++) {
                int bitIndex = randomProvider.getInt(16);
                int bit = bitwiseUtils.getBit(geneValue, bitIndex);
                int oppositeBit = bit ^ 1;
                geneValue = (char) bitwiseUtils.setBit(geneValue, bitIndex, oppositeBit);
            }
            gene.setValue(geneValue);
        }
    }

    private boolean mutationOccurs() {
        float mutationScore = randomProvider.getFloat();
        return mutationScore < mutationChance ? true : false;
    }
}
