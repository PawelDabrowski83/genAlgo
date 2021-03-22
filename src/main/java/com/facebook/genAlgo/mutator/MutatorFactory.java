package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.utils.RandomProviderImpl;

public class MutatorFactory {

    public static final float DEFAULT_MUTATION_CHANCE = 0.05f;

    public enum MutatorEnum {
        ZERO,
        SINGLE,
        MULTIPLE,
        DEFAULT
    }

    public MutatorService getMutator() {
        return new SingleMutator(new RandomProviderImpl(), DEFAULT_MUTATION_CHANCE);
    }

    public MutatorService getMutator(MutatorEnum option) {
        switch (option) {
            case ZERO -> {
                return new SingleMutator(new RandomProviderImpl(), 0f);
            }
            case SINGLE, DEFAULT -> {
                return new SingleMutator(new RandomProviderImpl(), DEFAULT_MUTATION_CHANCE);
            }
            case MULTIPLE -> {
                return new MultipleMutator(new RandomProviderImpl(), DEFAULT_MUTATION_CHANCE);
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }
    }

    public MutatorService getMutator(float mutationChance, MutatorEnum option) {
        MutatorService mutator = null;
        switch (option) {
            case ZERO -> {
                if (mutationChance != 0f) {
                    throw new IllegalArgumentException();
                }
                mutator =  new SingleMutator(new RandomProviderImpl(), 0f);
            }
            case SINGLE, DEFAULT -> {
                mutator = new SingleMutator(new RandomProviderImpl(), mutationChance);
            }
            case MULTIPLE -> {
                mutator = new MultipleMutator(new RandomProviderImpl(), mutationChance);
            }
        }
        return mutator;
    }
}
