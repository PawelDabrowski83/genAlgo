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

    }

    public MutatorService getMutator(float mutationChance, MutatorEnum option) {
        return null;
    }
}
