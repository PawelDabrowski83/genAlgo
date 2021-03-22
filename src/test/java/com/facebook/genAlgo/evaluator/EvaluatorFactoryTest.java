package com.facebook.genAlgo.evaluator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EvaluatorFactoryTest {

    char target;
    EvaluatorFactory evaluatorFactory;

    @BeforeEach
    public void init() {
        target = 'Z';
        evaluatorFactory = new EvaluatorFactory();
    }

    @Test
    public void shouldReturnMaxDeltaEvaluatorWhenGetEvaluatorWithoutArgsIsEvoked() {
        // when
        Evaluator evaluator = evaluatorFactory.getEvaluator(target);

        // then
        assertThat(evaluator).isInstanceOf(MaxDeltaEvaluatorImpl.class);
    }

    @Test
    public void shouldReturnMaxDeltaEvaluatorWhenGetEvaluatorWithDefaultArgIsEvoked() {
        // when
        Evaluator evaluator = evaluatorFactory.getEvaluator(target, EvaluatorFactory.EvaluatorEnum.DEFAULT);

        // then
        assertThat(evaluator).isInstanceOf(MaxDeltaEvaluatorImpl.class);
    }


    @Test
    public void shouldReturnMaxDeltaEvaluatorWhenGetEvaluatorWithMaxDeltaArgIsEvoked() {
        // when
        Evaluator evaluator = evaluatorFactory.getEvaluator(target, EvaluatorFactory.EvaluatorEnum.MAX_DELTA);

        // then
        assertThat(evaluator).isInstanceOf(MaxDeltaEvaluatorImpl.class);
    }

    @Test
    public void shouldReturnLogarithmicEvaluatorWhenGetEvaluatorWithLogArgIsEvoked() {
        // when
        Evaluator evaluator = evaluatorFactory.getEvaluator(target, EvaluatorFactory.EvaluatorEnum.LOG);

        // then
        assertThat(evaluator).isInstanceOf(LogarithmicEvaluatorImpl.class);
    }


}
