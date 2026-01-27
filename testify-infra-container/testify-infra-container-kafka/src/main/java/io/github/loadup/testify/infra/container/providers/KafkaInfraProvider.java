package io.github.loadup.testify.infra.container.providers;

import io.github.loadup.testify.infra.container.InfraProvider;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

@Slf4j
public class KafkaInfraProvider implements InfraProvider {
    public static final String DEFAULT_KAFKA_VERSION = "apache/kafka:4.1.1";

    private KafkaContainer container;

    @Override
    public String getType() {
        return "kafka";
    }

    @Override
    public void start(Map<String, Object> config, boolean reuse) {
        var image = (String) config.getOrDefault("image", DEFAULT_KAFKA_VERSION);

        container = new KafkaContainer(DockerImageName.parse(image)).withReuse(reuse);

        container.start();
    }

    @Override
    public Map<String, String> getExposedProperties() {
        if (container == null || !container.isRunning()) return Map.of();

        // 核心：将容器产生的动态随机地址注入到 Spring Kafka 配置中
        return Map.of(
                "spring.kafka.bootstrap-servers",
                container.getBootstrapServers(),
                "spring.kafka.consumer.bootstrap-servers",
                container.getBootstrapServers(),
                "spring.kafka.producer.bootstrap-servers",
                container.getBootstrapServers());
    }

    @Override
    public void stop() {
        if (container != null) container.stop();
    }
}
