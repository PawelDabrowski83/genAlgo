package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.utils.RandomProviderImpl;

public class MutatorFactory {

    public static final float DEFAULT_MUTATION_CHANCE = 0.05f;
    public static final float ZERO_MUTATION_CHANCE = 0f;

    public enum MutatorEnum {
        ZERO,
        SINGLE,
        MULTIPLE,
        DEFAULT
    }

    public MutatorService getMutator() {
        return getMutator(DEFAULT_MUTATION_CHANCE, MutatorEnum.DEFAULT);
    }

    public MutatorService getMutator(MutatorEnum option) {
        if (MutatorEnum.ZERO.equals(option)) {
            return getMutator(ZERO_MUTATION_CHANCE, option);
        }
        return getMutator(DEFAULT_MUTATION_CHANCE, option);
    }

    public MutatorService getMutator(float mutationChance, MutatorEnum option) {
        if (MutatorEnum.ZERO.equals(option) && mutationChance != ZERO_MUTATION_CHANCE) {
            throw new IllegalArgumentException();
        }

        if (MutatorEnum.MULTIPLE.equals(option)) {
            return new MultipleMutator(new RandomProviderImpl(), mutationChance);
        }
        return new SingleMutator(new RandomProviderImpl(), mutationChance);
    }
}
