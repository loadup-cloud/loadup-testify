package io.github.loadup.testify.core.model;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MockRule {
    private List<Object> expectedArgs; // 预期参数条件
    private Object returnValue; // 原始返回配置
    private Throwable throwEx; // 异常配置
    private Class<?> returnType; // 方法返回类型
    private Map<String, Object> context; // 变量上下文
    private Long delay; // 新增存储
    private boolean hit; // 记录是否被触发过
}
