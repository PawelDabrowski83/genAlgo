package com.facebook.genAlgo.mutator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MutatorFactoryTest {

    @DisplayName("Should return SingleMutator when getMutator() method without parameters is called")
    @Test
    public void shouldReturnSingleMutatorWhenGetMutatorIsEvoked() {
        // given
        MutatorFactory mutatorFactory = new MutatorFactory();

        // when
        MutatorService mutator = mutatorFactory.getMutator();

        // then
        Assertions.assertThat(mutator).isInstanceOf(SingleMutator.class);
    }


}
