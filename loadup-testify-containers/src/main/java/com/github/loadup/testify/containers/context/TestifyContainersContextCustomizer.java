package com.github.loadup.testify.containers.context;

import com.github.loadup.testify.containers.annotation.EnableContainer;
import com.github.loadup.testify.containers.registry.TestifyContainerRegistry;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.MergedContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.JdbcDatabaseContainer;

/** Customizer to start containers and inject properties before context refresh. */
@Slf4j
@EqualsAndHashCode
@RequiredArgsConstructor
public class TestifyContainersContextCustomizer implements ContextCustomizer {

  private final Set<EnableContainer> annotations;

  @Override
  public void customizeContext(
      ConfigurableApplicationContext context, MergedContextConfiguration mergedConfig) {
    // Check mode: external, containers, or auto
    String mode =
        context.getEnvironment().getProperty("loadup.testify.infrastructure.mode", "auto");

    if ("external".equalsIgnoreCase(mode)) {
      log.info("Testify infrastructure mode is external, skipping container startup.");
      return;
    }

    for (EnableContainer annotation : annotations) {
      Class<? extends GenericContainer> containerClass = annotation.value();
      String image = annotation.image().isEmpty() ? null : annotation.image();
      boolean shared = annotation.shared();

      GenericContainer<?> container =
          TestifyContainerRegistry.getContainer(containerClass, image, shared);

      // Auto-configure DataSource properties
      if (container instanceof JdbcDatabaseContainer) {
        JdbcDatabaseContainer<?> jdbcContainer = (JdbcDatabaseContainer<?>) container;
        log.info(
            "Injecting DataSource properties for container: {}",
            jdbcContainer.getDockerImageName());

        TestPropertyValues.of(
                "spring.datasource.url=" + jdbcContainer.getJdbcUrl(),
                "spring.datasource.username=" + jdbcContainer.getUsername(),
                "spring.datasource.password=" + jdbcContainer.getPassword(),
                "spring.datasource.driver-class-name=" + jdbcContainer.getDriverClassName())
            .applyTo(context.getEnvironment());
      } else if (container
          instanceof org.testcontainers.containers.localstack.LocalStackContainer) {
        // LocalStack (S3) support
        org.testcontainers.containers.localstack.LocalStackContainer localStack =
            (org.testcontainers.containers.localstack.LocalStackContainer) container;
        log.info("Injecting LocalStack (S3) properties");

        TestPropertyValues.of(
                "spring.cloud.aws.endpoint=" + localStack.getEndpoint(),
                "spring.cloud.aws.region.static=" + localStack.getRegion(),
                "spring.cloud.aws.credentials.access-key=" + localStack.getAccessKey(),
                "spring.cloud.aws.credentials.secret-key=" + localStack.getSecretKey(),
                // For AWS SDK v2 support
                "spring.cloud.aws.s3.endpoint=" + localStack.getEndpoint())
            .applyTo(context.getEnvironment());

      } else if (container.getDockerImageName() != null
          && container.getDockerImageName().contains("redis")) {
        // Redis Support (Generic Container)
        log.info("Injecting Redis properties");
        // Assume standard port 6379 if not mapped otherwise, but safeguards needed.
        // Container should be started, so getFirstMappedPort works.
        Integer mappedPort = container.getMappedPort(6379);

        TestPropertyValues.of(
                "spring.data.redis.host=" + container.getHost(),
                "spring.data.redis.port=" + mappedPort)
            .applyTo(context.getEnvironment());

      } else {
        // Generic container support? Maybe expose host/ports via dynamic properties?
        // For now, simple JDBC support is the primary goal.
        // We could inject "container.servicename.host" etc.
        String serviceName = containerClass.getSimpleName().toLowerCase().replace("container", "");
        TestPropertyValues.of(
                "testify.containers." + serviceName + ".host=" + container.getHost(),
                "testify.containers." + serviceName + ".port=" + container.getFirstMappedPort())
            .applyTo(context.getEnvironment());
      }
    }
  }
}
