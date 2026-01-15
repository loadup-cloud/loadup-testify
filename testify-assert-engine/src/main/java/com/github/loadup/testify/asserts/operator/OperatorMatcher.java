package com.github.loadup.testify.asserts.operator;

import java.util.Map;

public interface OperatorMatcher {
    /**
     * @param actual 数据库查询到的实际值
     * @param expected YAML中配置的期望对象 (如 {op: "gt", val: 100})
     * @return 匹配结果描述，如果成功返回 null，失败返回错误原因
     */
    String match(Object actual, Object expected);

    boolean support(Object expectedConfig);
}