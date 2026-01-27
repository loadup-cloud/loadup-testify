package io.github.loadup.testify.infra.container.providers;

import io.github.loadup.testify.infra.container.InfraProvider;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

@Slf4j
public class RedisInfraProvider implements InfraProvider {
    public static final String DEFAULT_REDIS_VERSION = "redis:7-alpine";

    private GenericContainer<?> redisContainer;
    private static final int REDIS_PORT = 6379;

    @Override
    public String getType() {
        return "redis";
    }

    @Override
    public void start(Map<String, Object> config, boolean reuse) {
        // 支持从配置中读取镜像，默认使用 redis:alpine
        var image = (String) config.getOrDefault("image", DEFAULT_REDIS_VERSION);

        redisContainer =
                new GenericContainer<>(DockerImageName.parse(image))
                        .withExposedPorts(REDIS_PORT)
                        .withReuse(reuse); // 开启复用

        redisContainer.start();
    }

    @Override
    public Map<String, String> getExposedProperties() {
        if (redisContainer == null || !redisContainer.isRunning()) {
            return Map.of();
        }

        String host = redisContainer.getHost();
        Integer port = redisContainer.getMappedPort(REDIS_PORT);

        // 返回 Spring Boot 标准的 Redis 配置属性
        // 支持 Spring Data Redis 的默认配置项
        return Map.of(
                "spring.data.redis.host",
                host,
                "spring.data.redis.port",
                port.toString(),
                // 兼容老版本或不同前缀的配置
                "spring.redis.host",
                host,
                "spring.redis.port",
                port.toString());
    }

    @Override
    public void stop() {
        if (redisContainer != null) {
            redisContainer.stop();
        }
    }
}
