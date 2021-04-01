package com.facebook.genAlgo.mutator;

import com.facebook.genAlgo.mutator.MutatorFactory.MutatorEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MutatorFactoryTest {

    public static final float DEFAULT_MUTATION_CHANCE = 0.05f;

    MutatorFactory mutatorFactory;

    @BeforeEach
    public void init() {
        mutatorFactory = new MutatorFactory();
    }

    @DisplayName("Should return SingleMutator when getMutator() method without parameters is called")
    @Test
    public void shouldReturnSingleMutatorWhenGetMutatorIsEvoked() {
        // when
        MutatorService mutator = mutatorFactory.getMutator();

        // then
        assertThat(mutator).isInstanceOf(SingleMutator.class);
    }

    @DisplayName("Should return SingleMutator with mutationChance equal default value when getMutator() method without parameter is called")
    @Test
    public void shouldReturnSingleMutatorWithDefaultMutationChanceWhenGetMutatorIsEvoked() {
        // when
        MutatorService mutator = mutatorFactory.getMutator();

        // then
        assertThat(((SingleMutator)mutator).getMutationChance()).isEqualTo(DEFAULT_MUTATION_CHANCE);
    }

    @DisplayName("Should return instance of MultipleMutator when getMutator() with MULTIPLE enum as parameter is called")
    @Test
    public void shouldReturnInstanceOfMultipleMutatorWhenGetMutatorIsEvoked() {
        // given
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
        float mutationChance = 0.3f;

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> mutatorFactory.getMutator(mutationChance, MutatorEnum.ZERO));
    }

    @DisplayName("Should return instance of MultipleMutator when getMutator() with single parameter MULTIPLE enum is called")
    @Test
    public void shouldReturnInstanceOfMultipleMutatorWhenGetMutatorWithSingleParamIsEvoked() {
        // when
        MutatorService mutator = mutatorFactory.getMutator(MutatorEnum.MULTIPLE);

        // then
        assertThat(mutator).isInstanceOf(MultipleMutator.class);
    }

    @DisplayName("Should return instance of MultipleMutator with default mutationChance when getMutator() with single parameter MULTIPLE enum is called")
    @Test
    public void shouldReturnInstanceOfMultipleMutatorWithDefaultMutationChanceWhenGetMutatorWithSingleParamIsEvoked() {
        // when
        MutatorService mutator = mutatorFactory.getMutator(MutatorEnum.MULTIPLE);

        // then
        assertThat(((MultipleMutator)mutator).getMutationChance()).isEqualTo(DEFAULT_MUTATION_CHANCE);
    }

    @DisplayName("Should return instance of SingleMutator when getMutator() with single parameter SINGLE enum is called")
    @Test
    public void shouldReturnInstanceOfSingleMutatorWhenGetMutatorWithSingleParamIsEvoked() {
        // when
        MutatorService mutator = mutatorFactory.getMutator(MutatorEnum.SINGLE);

        // then
        assertThat(mutator).isInstanceOf(SingleMutator.class);
    }

    @DisplayName("Should return instance of SingleMutator with default mutationChance when getMutator() with single parameter SINGLE enum is called")
    @Test
    public void shouldReturnInstanceOfSingleMutatorWithDefaultMutationChanceWhenGetMutatorWithSingleParamIsEvoked() {
        // when
        MutatorService mutator = mutatorFactory.getMutator(MutatorEnum.SINGLE);

        // then
        assertThat(((SingleMutator)mutator).getMutationChance()).isEqualTo(DEFAULT_MUTATION_CHANCE);
    }

    @DisplayName("Should return instance of SingleMutator when getMutator() with single parameter DEFAULT enum is called")
    @Test
    public void shouldReturnInstanceOfSingleMutatorWhenGetMutatorWithSingleDefaultParamIsEvoked() {
        // when
        MutatorService mutator = mutatorFactory.getMutator(MutatorEnum.DEFAULT);

        // then
        assertThat(mutator).isInstanceOf(SingleMutator.class);
    }

    @DisplayName("Should return instance of SingleMutator with default mutationChance when getMutator() with single parameter DEFAULT enum is called")
    @Test
    public void shouldReturnInstanceOfSingleMutatorWithDefaultMutationChanceWhenGetMutatorWithSingleDefaultParamIsEvoked() {
        // when
        MutatorService mutator = mutatorFactory.getMutator(MutatorEnum.DEFAULT);

        // then
        assertThat(((SingleMutator)mutator).getMutationChance()).isEqualTo(DEFAULT_MUTATION_CHANCE);
    }

    @DisplayName("Should return instance of SingleMutator when getMutator() with single parameter ZERO enum is called")
    @Test
    public void shouldReturnInstanceOfSingleMutatorWhenGetMutatorWithSingleZeroParamIsEvoked() {
        // when
        MutatorService mutator = mutatorFactory.getMutator(MutatorEnum.ZERO);

        // then
        assertThat(mutator).isInstanceOf(SingleMutator.class);
    }

    @DisplayName("Should return instance of SingleMutator with zero mutationChance when getMutator() with single parameter ZERO enum is called")
    @Test
    public void shouldReturnInstanceOfSingleMutatorWithZeroMutationChanceWhenGetMutatorWithSingleDefaultParamIsEvoked() {
        // when
        MutatorService mutator = mutatorFactory.getMutator(MutatorEnum.ZERO);

        // then
        assertThat(((SingleMutator)mutator).getMutationChance()).isEqualTo(0f);
    }
}
