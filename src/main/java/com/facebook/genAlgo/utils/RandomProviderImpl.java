package com.facebook.genAlgo.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomProviderImpl implements RandomProvider {

    @Override
    public int getInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    @Override
    public int getIntFromRange(int fromInclusive, int toExclusive) {
        return ThreadLocalRandom.current().nextInt(fromInclusive, toExclusive);
    }

    @Override
    public float getFloat() {
        return ThreadLocalRandom.current().nextFloat();
    }
}
