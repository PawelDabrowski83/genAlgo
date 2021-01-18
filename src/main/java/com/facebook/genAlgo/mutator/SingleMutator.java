package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.crossover.CrossoverConfig;
import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.utils.BitwiseUtils;
import com.facebook.genAlgo.utils.RandomProvider;

import static com.facebook.genAlgo.crossover.CrossoverConfig.GENE_VALUES_BIT_LIMIT;

public class SingleMutator implements MutatorService {

    private final RandomProvider randomProvider;
    private final BitwiseUtils bitwiseUtils = new BitwiseUtils();
    private float mutationChance;

    public SingleMutator(RandomProvider randomProvider, float mutationChance) {
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
            int bitIndex = randomProvider.getInt(GENE_VALUES_BIT_LIMIT);
            int bit = bitwiseUtils.getBit(gene.getValue(), bitIndex);
            int oppositeBit = bit ^ 1;
            char geneValueAfterMutation = (char) bitwiseUtils.setBit(gene.getValue(), bitIndex, oppositeBit);

            gene.setValue(geneValueAfterMutation);
        }
    }

    private boolean mutationOccurs() {
        float mutationScore = randomProvider.getFloat();
        return mutationScore < mutationChance;
    }
}
