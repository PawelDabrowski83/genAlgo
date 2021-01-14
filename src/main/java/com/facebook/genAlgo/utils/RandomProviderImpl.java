package com.facebook.genAlgo.utils;

import com.facebook.genAlgo.utils.RandomProvider;

import java.util.concurrent.ThreadLocalRandom;

public class RandomProviderImpl implements RandomProvider {

    @Override
    public int getInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    @Override
    public float getFloat(float bound) {
        return ThreadLocalRandom.current().nextFloat();
    }
}
