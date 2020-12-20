package com.facebook.genAlgo;

import java.util.concurrent.ThreadLocalRandom;

public class RandomProviderImpl implements RandomProvider {

    @Override
    public int getRandom(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }
}
