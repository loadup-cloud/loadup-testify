package io.github.loadup.testify.infra.container.providers;

import io.github.loadup.testify.infra.container.InfraProvider;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.mongodb.MongoDBContainer;

import java.util.Map;

@Slf4j
public class MongoInfraProvider implements InfraProvider {
    public static final String DEFAULT_MONGODB_VERSION = "mongo:7.0";

    private MongoDBContainer container;

    @Override
    public String getType() {
        return "mongodb";
    }

    @Override
    public void start(Map<String, Object> config, boolean reuse) {
        var image = (String) config.getOrDefault("image", DEFAULT_MONGODB_VERSION);

        container = new MongoDBContainer(image).withReuse(reuse);

        container.start();
    }

    @Override
    public Map<String, String> getExposedProperties() {
        if (container == null || !container.isRunning()) return Map.of();

        // 动态注入 MongoDB 连接 URI，Spring Data MongoDB 会优先识别此属性
        return Map.of(
                "spring.data.mongodb.uri",
                container.getReplicaSetUrl(),
                "spring.data.mongodb.host",
                container.getReplicaSetUrl(),
                "spring.data.mongodb.port",
                container.getFirstMappedPort().toString());
    }

    @Override
    public void stop() {
        if (container != null) container.stop();
    }
}
