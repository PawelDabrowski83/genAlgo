package com.facebook.genAlgo.evaluator;

public class EvaluatorFactory {

    public enum EvaluatorEnum {
        DEFAULT,
        MAX_DELTA,
        LOG
    }

    public Evaluator getEvaluator(char target) {
        return getEvaluator(target, EvaluatorEnum.DEFAULT);
    }

    public Evaluator getEvaluator(char target, EvaluatorEnum option) {
        if (EvaluatorEnum.LOG.equals(option)) {
            return new LogarithmicEvaluatorImpl(target);
        }
        return new MaxDeltaEvaluatorImpl(target);
    }
}
