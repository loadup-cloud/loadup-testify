package io.github.loadup.testify.asserts.facade;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.loadup.testify.asserts.engine.TestifyAssertEngine;
import io.github.loadup.testify.core.variable.VariableContext;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class AssertionFacade {

    private final Map<String, TestifyAssertEngine> engineMap;

    public AssertionFacade(List<TestifyAssertEngine> engines) {
        // 自动将所有引擎按 supportKey 存入 Map
        this.engineMap =
                engines.stream()
                        .collect(
                                Collectors.toMap(
                                        TestifyAssertEngine::supportKey,
                                        e -> e,
                                        (existing, replacement) -> {
                                            // 如果发生冲突，抛出明确的错误信息
                                            throw new IllegalStateException(
                                                    String.format(
                                                            "Duplicate AssertEngine key: '%s' found in %s and %s",
                                                            existing.supportKey(),
                                                            existing.getClass().getSimpleName(),
                                                            replacement.getClass().getSimpleName()));
                                        }));
    }

    public List<String> executeAll(JsonNode expectRoot, Object actualRes, Throwable businessError) {
        List<String> reports = new ArrayList<>();
        // 获取当前的变量上下文
        Map<String, Object> context = VariableContext.get();
        // 遍历 YAML 中 expect 下的所有节点
        expectRoot
                .fields()
                .forEachRemaining(
                        entry -> {
                            String key = entry.getKey();
                            JsonNode config = entry.getValue();
                            TestifyAssertEngine engine = engineMap.get(key);

                            if (engine != null) {
                                // 根据 key 决定传入哪个实际对象
                                Object actual =
                                        switch (key) {
                                            case "response" -> actualRes;
                                            case "exception" -> businessError;
                                            case "database" -> null; // DB 引擎通常内部自己去查库，不需要外传实际值
                                            default -> null;
                                        };

                                try {
                                    engine.compare(config, actual, context, reports);
                                } catch (Exception e) {
                                    log.error("Assert Error:", e);
                                    reports.add("❌ 引擎 [" + key + "] 执行崩溃: " + e.getMessage());
                                }
                            }
                        });

        return reports;
    }
}
