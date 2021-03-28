package com.facebook.genAlgo.evaluator;

public class EvaluatorFactory {

    public enum EvaluatorEnum {
        DEFAULT,
        MAX_DELTA,
        LOG
    }

    Evaluator getEvaluator(char target) {
        return null;
    }

    Evaluator getEvaluator(char target, EvaluatorEnum option) {
        Evaluator evaluator = null;
        switch (option) {
            case DEFAULT, MAX_DELTA -> evaluator = new MaxDeltaEvaluatorImpl(target);
            case LOG -> evaluator = new LogarithmicEvaluatorImpl(target);
        }
        return evaluator;
    }
}
