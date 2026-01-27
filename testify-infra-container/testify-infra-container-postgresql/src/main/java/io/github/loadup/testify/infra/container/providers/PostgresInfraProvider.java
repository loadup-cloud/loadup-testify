package io.github.loadup.testify.infra.container.providers;

import io.github.loadup.testify.infra.container.InfraProvider;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.util.Map;
import java.util.Optional;

@Slf4j
public class PostgresInfraProvider implements InfraProvider {
    public static final String DEFAULT_POSTGRES_VERSION = "postgres:15-alpine";

    private PostgreSQLContainer container;

    @Override
    public String getType() {
        return "postgresql";
    }

    @Override
    public void start(Map<String, Object> config, boolean reuse) {
        var image = (String) config.getOrDefault("image", DEFAULT_POSTGRES_VERSION);

        container =
                new PostgreSQLContainer(image)
                        .withDatabaseName((String) config.getOrDefault("database", "testify_db"))
                        .withUsername("testify")
                        .withPassword("testify")
                        .withReuse(reuse);

        // 支持初始化脚本
        Optional.ofNullable(config.get("init_script"))
                .map(Object::toString)
                .ifPresent(container::withInitScript);

        container.start();
    }

    @Override
    public Map<String, String> getExposedProperties() {
        if (container == null || !container.isRunning()) return Map.of();

        return Map.of(
                "spring.datasource.url", container.getJdbcUrl(),
                "spring.datasource.username", container.getUsername(),
                "spring.datasource.password", container.getPassword(),
                "spring.datasource.driver-class-name", container.getDriverClassName());
    }

    @Override
    public void stop() {
        if (container != null) container.stop();
    }
}
