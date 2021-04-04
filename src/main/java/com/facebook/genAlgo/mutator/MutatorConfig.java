package com.facebook.genAlgo.mutator;

public class MutatorConfig {

    public static final float DEFAULT_MUTATION_CHANCE = 0.05f;
    public static final float ZERO_MUTATION_CHANCE = 0f;

    public enum MutatorEnum {
        ZERO,
        SINGLE,
        MULTIPLE,
        DEFAULT
    }
}
