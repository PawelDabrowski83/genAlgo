package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.mutator.MutatorFactory.MutatorEnum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MutatorFactoryTest {

    @DisplayName("Should return SingleMutator when getMutator() method without parameters is called")
    @Test
    public void shouldReturnSingleMutatorWhenGetMutatorIsEvoked() {
        // given
        MutatorFactory mutatorFactory = new MutatorFactory();

        // when
        MutatorService mutator = mutatorFactory.getMutator();

        // then
        assertThat(mutator).isInstanceOf(SingleMutator.class);
    }

    @DisplayName("Should return SingleMutator with mutationChance equal 0.05 when getMutator() method without parameter is called")
    @Test
    public void shouldReturnSingleMutatorWithDefaultMutationChanceWhenGetMutatorIsEvoked() {
        // given
        MutatorFactory mutatorFactory = new MutatorFactory();

        // when
        MutatorService mutator = mutatorFactory.getMutator();

        // then
        assertThat(((SingleMutator)mutator).getMutationChance()).isEqualTo(0.05f);
    }


}
