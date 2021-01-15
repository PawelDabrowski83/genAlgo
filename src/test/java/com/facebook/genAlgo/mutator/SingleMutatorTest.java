package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.gene.Gene;
import com.facebook.genAlgo.utils.RandomProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SingleMutatorTest {

    RandomProvider randomProvider = mock(RandomProvider.class);
    MutatorService mutatorService = new SingleMutator(randomProvider);

}
