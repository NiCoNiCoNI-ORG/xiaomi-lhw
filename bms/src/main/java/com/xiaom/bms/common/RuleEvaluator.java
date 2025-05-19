package com.xiaom.bms.common;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.math.BigDecimal;

public class RuleEvaluator {

    private final ScriptEngine engine;

    public RuleEvaluator() {
        ScriptEngineManager manager = new ScriptEngineManager();
        this.engine = manager.getEngineByName("JavaScript");
    }

    public int evaluate(BigDecimal mx, BigDecimal mi, String condition) throws Exception {
        engine.put("Mx", mx.doubleValue());
        engine.put("Mi", mi.doubleValue());
        Object result = engine.eval(condition);
        return Boolean.TRUE.equals(result) ? 1 : 0;
    }
}