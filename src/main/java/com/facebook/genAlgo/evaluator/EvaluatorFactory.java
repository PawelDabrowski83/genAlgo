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
        return null;
    }
}
