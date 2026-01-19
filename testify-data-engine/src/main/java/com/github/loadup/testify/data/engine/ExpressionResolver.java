package com.github.loadup.testify.data.engine;

import com.github.loadup.testify.data.engine.variable.VariableContext;
import com.github.loadup.testify.data.engine.variable.VariableEngine;

import java.util.Map;

public class ExpressionResolver {
    private final VariableEngine engine = new VariableEngine();

    public Map<String, Object> resolveAll(Map<String, String> rawVars, Object... templates) {
        // 1. 首先解析变量块
        Map<String, Object> context = engine.resolveVariables(rawVars);
        VariableContext.set(context);

        // 2. 将解析后的变量回填到 context 自身（处理变量间引用）
        // 3. 解析其他模板数据（如 input, expect）
        // 这里可以根据需要进行扩展
        return context;
    }
}