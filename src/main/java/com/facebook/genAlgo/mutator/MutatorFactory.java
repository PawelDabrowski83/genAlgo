package com.facebook.genAlgo.mutator;

public class MutatorFactory {

    public enum MutatorEnum {
        ZERO,
        SINGLE,
        MULTIPLE,
        DEFAULT
    }

    MutatorService getMutator(){
        return null;
    }
    MutatorService getMutator(MutatorEnum option) {
        return null; }
    MutatorService getMutator(float mutationChance, MutatorEnum option) {
        return null;
    }
}
