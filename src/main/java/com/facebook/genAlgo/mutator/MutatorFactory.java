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

    public MutatorService getMutator(){
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
        return null;
    }
}
