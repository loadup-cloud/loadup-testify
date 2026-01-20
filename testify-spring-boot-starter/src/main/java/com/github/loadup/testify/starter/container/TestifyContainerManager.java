package com.github.loadup.testify.starter.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages Testcontainers lifecycle for integration tests. Uses reflection to avoid compile-time
 * dependencies on Testcontainers.
 */
public class TestifyContainerManager {

  private static final Logger logger = LoggerFactory.getLogger(TestifyContainerManager.class);

  private Object mySQLContainer;
  private Object redisContainer;

  public TestifyContainerManager() {
    logger.info("TestifyContainerManager initialized");
    initializeContainers();
  }

  /** Initialize and start containers using reflection. */
  private void initializeContainers() {
    try {
      // Check if Testcontainers is available
      Class.forName("org.testcontainers.containers.MySQLContainer");

      // MySQL Container
      Class<?> mysqlContainerClass = Class.forName("org.testcontainers.containers.MySQLContainer");
      Class<?> dockerImageClass = Class.forName("org.testcontainers.utility.DockerImageName");

      Object dockerImage =
          dockerImageClass.getMethod("parse", String.class).invoke(null, "mysql:8.0");

      mySQLContainer =
          mysqlContainerClass.getConstructor(dockerImageClass).newInstance(dockerImage);

      // Configure MySQL container
      mySQLContainer
          .getClass()
          .getMethod("withDatabaseName", String.class)
          .invoke(mySQLContainer, "testdb");
      mySQLContainer
          .getClass()
          .getMethod("withUsername", String.class)
          .invoke(mySQLContainer, "test");
      mySQLContainer
          .getClass()
          .getMethod("withPassword", String.class)
          .invoke(mySQLContainer, "test");

      // Start container
      mySQLContainer.getClass().getMethod("start").invoke(mySQLContainer);
      String jdbcUrl =
          (String) mySQLContainer.getClass().getMethod("getJdbcUrl").invoke(mySQLContainer);
      logger.info("MySQL container started: {}", jdbcUrl);

      // Redis Container
      Class<?> genericContainerClass =
          Class.forName("org.testcontainers.containers.GenericContainer");
      Object redisImage =
          dockerImageClass.getMethod("parse", String.class).invoke(null, "redis:7-alpine");

      redisContainer =
          genericContainerClass.getConstructor(dockerImageClass).newInstance(redisImage);

      // Configure Redis container
      redisContainer
          .getClass()
          .getMethod("withExposedPorts", int[].class)
          .invoke(redisContainer, new int[] {6379});

      // Start Redis container
      redisContainer.getClass().getMethod("start").invoke(redisContainer);
      String redisHost =
          (String) redisContainer.getClass().getMethod("getHost").invoke(redisContainer);
      Integer redisPort =
          (Integer)
              redisContainer
                  .getClass()
                  .getMethod("getMappedPort", int.class)
                  .invoke(redisContainer, 6379);
      logger.info("Redis container started: {}:{}", redisHost, redisPort);

    } catch (ClassNotFoundException e) {
      logger.warn("Testcontainers not available, containers will not be started");
    } catch (Exception e) {
      logger.error("Failed to start test containers", e);
      throw new RuntimeException("Container startup failed", e);
    }
  }

  /** Get JDBC URL from MySQL container. */
  public String getJdbcUrl() {
    if (mySQLContainer != null) {
      try {
        return (String) mySQLContainer.getClass().getMethod("getJdbcUrl").invoke(mySQLContainer);
      } catch (Exception e) {
        logger.error("Failed to get JDBC URL", e);
      }
    }
    return null;
  }

  /** Get database username. */
  public String getUsername() {
    if (mySQLContainer != null) {
      try {
        return (String) mySQLContainer.getClass().getMethod("getUsername").invoke(mySQLContainer);
      } catch (Exception e) {
        logger.error("Failed to get username", e);
      }
    }
    return null;
  }

  /** Get database password. */
  public String getPassword() {
    if (mySQLContainer != null) {
      try {
        return (String) mySQLContainer.getClass().getMethod("getPassword").invoke(mySQLContainer);
      } catch (Exception e) {
        logger.error("Failed to get password", e);
      }
    }
    return null;
  }

  /** Get Redis host. */
  public String getRedisHost() {
    if (redisContainer != null) {
      try {
        return (String) redisContainer.getClass().getMethod("getHost").invoke(redisContainer);
      } catch (Exception e) {
        logger.error("Failed to get Redis host", e);
      }
    }
    return null;
  }

  /** Get Redis port. */
  public Integer getRedisPort() {
    if (redisContainer != null) {
      try {
        return (Integer)
            redisContainer
                .getClass()
                .getMethod("getMappedPort", int.class)
                .invoke(redisContainer, 6379);
      } catch (Exception e) {
        logger.error("Failed to get Redis port", e);
      }
    }
    return null;
  }

  /** Stop all containers. */
  public void stop() {
    logger.info("Stopping test containers");

    if (mySQLContainer != null) {
      try {
        mySQLContainer.getClass().getMethod("stop").invoke(mySQLContainer);
        logger.info("MySQL container stopped");
      } catch (Exception e) {
        logger.error("Failed to stop MySQL container", e);
      }
    }

    if (redisContainer != null) {
      try {
        redisContainer.getClass().getMethod("stop").invoke(redisContainer);
        logger.info("Redis container stopped");
      } catch (Exception e) {
        logger.error("Failed to stop Redis container", e);
      }
    }
  }
}
