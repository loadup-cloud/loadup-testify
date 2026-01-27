package io.github.loadup.testify.starter.container;

import io.github.loadup.testify.infra.container.manager.ContainerManager;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;

@Slf4j
public class TestifyInfraInitializer
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final String ENABLED_KEY = "loadup.testify.testcontainers.enabled";
    private static final String REUSE_KEY = "loadup.testify.testcontainers.reuse";
    private static final String SERVICES_KEY = "loadup.testify.testcontainers.services";

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        ConfigurableEnvironment env = context.getEnvironment();

        // 1. 只判断这一个核心开关
        Boolean enabled = env.getProperty(ENABLED_KEY, Boolean.class, false);
        Boolean globalReuse = env.getProperty(REUSE_KEY, Boolean.class, true);
        if (Boolean.TRUE.equals(enabled)) {
            // 2. 使用 Spring Boot 3 Binder 安全解析 List<Map>
            List<Map<String, Object>> serviceConfigs = resolveConfigs(env);

            if (serviceConfigs.isEmpty()) {
                return;
            }

            // 3. 启动容器
            ContainerManager manager = new ContainerManager();
            manager.init(serviceConfigs, globalReuse);

            // 4. 动态属性注入
            Map<String, String> infraProps = manager.getAllProperties();
            if (!infraProps.isEmpty()) {
                // 强制将配置注入到最高优先级，覆盖 application.yaml 中的直连配置
                env.getPropertySources()
                        .addFirst(new MapPropertySource("testifyInfraProps", (Map) infraProps));
            }
        }
    }

    private List<Map<String, Object>> resolveConfigs(Environment env) {
        // 1. 构造嵌套泛型：Map<String, Object>
        ResolvableType mapType =
                ResolvableType.forClassWithGenerics(Map.class, String.class, Object.class);

        // 2. 构造外层泛型：List<Map<String, Object>>
        ResolvableType listType = ResolvableType.forClassWithGenerics(List.class, mapType);

        // 3. 执行绑定
        return Binder.get(env)
                .bind(SERVICES_KEY, Bindable.<List<Map<String, Object>>>of(listType))
                .orElse(Collections.emptyList());
    }
}
