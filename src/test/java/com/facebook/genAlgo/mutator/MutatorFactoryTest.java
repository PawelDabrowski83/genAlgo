package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.mutator.MutatorFactory.MutatorEnum;
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

    @DisplayName("Should return instance of MultipleMutator when getMutator() with MULTIPLE enum as parameter is called")
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

    @DisplayName("Should return MultipleMutator with mutationChance equal 0.3 when getMutator() with MULTIPLE enum as parameter is called")
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

    @DisplayName("Should return SingleMutator with mutationChance equal 0.3 when getMutator() with DEFAULT enum as parameter is called")
    @Test
    public void shouldReturnSingleMutatorWithGivenMutationChanceWhenGetMutatorIsEvokedWithDefaultEnumParameter() {
        // given
        MutatorFactory mutatorFactory = new MutatorFactory();
        float mutationChance = 0.3f;

        // when
        MutatorService mutator = mutatorFactory.getMutator(mutationChance, MutatorEnum.DEFAULT);

        // then
        assertThat(((SingleMutator)mutator).getMutationChance()).isEqualTo(mutationChance);
    }

    @DisplayName("Should return SingleMutator when getMutator() with ZERO enum and mutationChance equal 0 as parameter is called")
    @Test
    public void shouldReturnSingleMutatorWhenGetMutatorIsEvokedWithZeroEnumParameter() {
        // given
        MutatorFactory mutatorFactory = new MutatorFactory();
        float mutationChance = 0f;

        // when
        MutatorService mutator = mutatorFactory.getMutator(mutationChance, MutatorEnum.ZERO);

        // then
        assertThat(mutator).isInstanceOf(SingleMutator.class);
    }

    @DisplayName("Should throw exception when getMutator() with ZERO enum as parameter is called")
    @Test
    public void shouldThrowExceptionWhenMutatorWithZeroMutationChanceWhenGetMutatorIsEvokedWithZeroEnumParameter() {
        // given
        MutatorFactory mutatorFactory = new MutatorFactory();
        float mutationChance = 0.3f;

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> mutatorFactory.getMutator(mutationChance, MutatorEnum.ZERO));
    }
}
