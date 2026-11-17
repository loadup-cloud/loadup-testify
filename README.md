# LoadUp Testify

Testify 是 JUnit Jupiter / Spring Boot Test 的测试辅助工具，提供案例文件加载、变量展开、数据准备，以及响应、异常和数据库断言。测试顺序与业务调用由普通 Java 测试控制；Testify 不负责工作流编排、HTTP 请求、Mock、建表或生产运行时功能。

## 接入

在 `dependencyManagement` 导入 [Testify BOM](loadup-testify-bom/README.md)，再以 `test` scope 添加 starter。JUnit 与 Spring Boot Test 由消费工程的 BOM 管理，需显式添加；真实数据库测试还需消费工程提供 JDBC driver、schema 初始化和可选 Testcontainers。

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.github.loadup-cloud</groupId>
            <artifactId>loadup-testify-bom</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.github.loadup-cloud</groupId>
        <artifactId>loadup-testify-spring-boot-starter</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## 最小断言

通过构造器注入 `ScenarioAssert`，将实际结果与字段期望比较：

```java
private final ScenarioAssert assertions;

@Autowired
MyServiceTest(ScenarioAssert assertions) {
    this.assertions = assertions;
}

@Test
void verifiesResultFields() {
    assertions.verify(
            Map.of("status", "CREATED", "amount", 1200),
            Map.of("status", "CREATED", "amount", Map.of("op", "ge", "val", 1000)));
}
```

## 使用边界

- 无数据库时可使用响应、异常和变量断言；starter 的 JDBC 依赖仍可能触发数据源自动配置，按 Spring Boot 版本排除相应 `DataSourceAutoConfiguration`。
- `TestScenario.clean` 是物理删除，只能用于独占测试数据；使用唯一业务 ID 和限定范围清理。JDBC 不触发应用 ORM 的审计、租户或逻辑删除监听器。
- 异步断言需要可见的已提交数据。测试事务未提交时，后台消费者通常无法读取；共享容器并行测试不要整表清理。
- 案例文件应来自可信测试资源；SpEL 变量表达式不是不可信输入的沙箱。解析变量、绑定业务输入和调用服务都由测试显式执行。
- `TestScenario.assertDb` 与 `ScenarioAssert.verifyDb` 是不同 API，默认等待策略和失败语义不同。

## 文档导航

- [Starter 接入与自动配置](loadup-testify-spring-boot-starter/README.md)：Bean 条件、案例加载、JDBC API 和扩展入口。
- [断言引擎接入与语义](loadup-testify-core/README.md)：变量函数、操作符、数据库匹配、异常比较和失败报告。
- [总体架构](ARCHITECTURE.md)：模块职责、依赖方向、生命周期与隔离不变量。
- [可执行示例](loadup-testify-test/README.md)：测试资源、示例场景和运行方式。
