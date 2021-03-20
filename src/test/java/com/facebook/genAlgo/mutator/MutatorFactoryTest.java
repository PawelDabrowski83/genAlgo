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

    @DisplayName("Should return instance of MultipleMutator when getMutator() method without parameter is called")
    @Test
    public void shouldReturnInstanceOfMultipleMutatorWhenGetMutatorIsEvoked() {
        // given
        MutatorFactory mutatorFactory = new MutatorFactory();
        float mutationChance = 0.2f;

        // when
        MutatorService mutator = mutatorFactory.getMutator(mutationChance, MutatorEnum.MULTIPLE);

        // then
        assertThat(mutator).isInstanceOf(MultipleMutator.class);
    }

    @DisplayName("Should return MultipleMutator with mutationChance equal 0.3 when getMutator() method without parameter is called")
    @Test
    public void shouldReturnMultipleMutatorWithGivenMutationChanceWhenGetMutatorIsEvoked() {
        // given
        MutatorFactory mutatorFactory = new MutatorFactory();
        float mutationChance = 0.3f;

        // when
        MutatorService mutator = mutatorFactory.getMutator(mutationChance, MutatorEnum.MULTIPLE);

        // then
        assertThat(((MultipleMutator)mutator).getMutationChance()).isEqualTo(mutationChance);
    }

    @DisplayName("Should return SingleMutator when getMutator() with DEFAULT enum as parameter is called")
    @Test
    public void shouldReturnSingleMutatorWhenGetMutatorIsEvokedWithDefaultEnumParameter() {
        // given
        MutatorFactory mutatorFactory = new MutatorFactory();
        float mutationChance = 0.3f;

        // when
        MutatorService mutator = mutatorFactory.getMutator(mutationChance, MutatorEnum.DEFAULT);

        // then
        assertThat(mutator).isInstanceOf(SingleMutator.class);
    }

    @DisplayName("Should return SingleMutator with mutationChance equal 0.05 when getMutator() with DEFAULT enum as parameter is called")
    @Test
    public void shouldReturnSingleMutatorWithDefaultMutationChanceWhenGetMutatorIsEvokedWithDefaultEnumParameter() {
        // given
        MutatorFactory mutatorFactory = new MutatorFactory();
        float mutationChance = 0.3f;

        // when
        MutatorService mutator = mutatorFactory.getMutator(mutationChance, MutatorEnum.DEFAULT);

        // then
        assertThat(((SingleMutator)mutator).getMutationChance()).isEqualTo(0.05f);
    }


}
