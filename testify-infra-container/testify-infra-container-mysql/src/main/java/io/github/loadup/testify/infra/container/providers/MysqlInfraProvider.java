package io.github.loadup.testify.infra.container.providers;

import io.github.loadup.testify.infra.container.InfraProvider;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.mysql.MySQLContainer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MysqlInfraProvider implements InfraProvider {
    private MySQLContainer mysqlContainer;

    public static final String DEFAULT_MYSQL_VERSION = "mysql:8.0";

    @Override
    public String getType() {
        return "mysql";
    }

    @Override
    @SuppressWarnings("resource")
    public void start(Map<String, Object> config, boolean reuse) {
        String image = (String) config.getOrDefault("image", DEFAULT_MYSQL_VERSION);
        String initScript = (String) config.get("init_script");

        mysqlContainer =
                new MySQLContainer(image)
                        .withDatabaseName((String) config.getOrDefault("database", "testify_db"))
                        .withUsername("testify")
                        .withPassword("testify")
                        .withCommand("--max-allowed-packet=268435456")
                        // 开启连接复用
                        .withReuse(reuse);

        if (null != initScript && !initScript.isEmpty()) {
            mysqlContainer.withInitScript(initScript);
        }

        log.info(">>> [TESTIFY-INFRA] Starting MySQL container: {}", image);
        mysqlContainer.start();
    }

    @Override
    public Map<String, String> getExposedProperties() {
        Map<String, String> props = new HashMap<>();
        if (mysqlContainer != null && mysqlContainer.isRunning()) {
            props.put("spring.datasource.url", mysqlContainer.getJdbcUrl());
            props.put("spring.datasource.username", mysqlContainer.getUsername());
            props.put("spring.datasource.password", mysqlContainer.getPassword());
            // 确保驱动一致性
            props.put("spring.datasource.driver-class-name", mysqlContainer.getDriverClassName());
        }
        return props;
    }

    @Override
    public void stop() {
        if (mysqlContainer != null) {
            mysqlContainer.stop();
        }
    }
}
