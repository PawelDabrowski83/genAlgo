package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.utils.BitwiseUtils;
import com.facebook.genAlgo.utils.RandomProvider;

import static com.facebook.genAlgo.crossover.CrossoverConfig.GENE_VALUES_BIT_LIMIT;

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
            int bitsToMutate = randomProvider.getIntFromRange(1, GENE_VALUES_BIT_LIMIT);

            MutatorService singleMutator = new SingleMutator(randomProvider, getMutationChance());
            while (bitsToMutate-- > 0) {
                singleMutator.mutate(gene);
            }

        }
    }

    private boolean mutationOccurs() {
        float mutationScore = randomProvider.getFloat();
        return mutationScore < mutationChance;
    }

}
