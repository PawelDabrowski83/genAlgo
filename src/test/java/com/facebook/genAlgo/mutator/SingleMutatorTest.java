package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.utils.RandomProvider;

import static org.mockito.Mockito.mock;

public class SingleMutatorTest {

    RandomProvider randomProvider = mock(RandomProvider.class);
    MutatorService mutatorService = new SingleMutator(randomProvider);

}
