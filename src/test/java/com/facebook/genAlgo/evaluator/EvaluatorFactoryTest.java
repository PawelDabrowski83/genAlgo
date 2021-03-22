package com.facebook.genAlgo.evaluator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EvaluatorFactoryTest {

    char target;
    EvaluatorFactory evaluatorFactory;

    @BeforeEach
    public void init() {
        target = 'Z';
        evaluatorFactory = new EvaluatorFactory();
    }


}
