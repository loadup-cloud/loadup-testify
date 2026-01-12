package com.github.loadup.testify.containers.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.GenericContainer;

/**
 * RegistryManager for holding shared Testcontainer instances. Implements the Singleton Container
 * pattern.
 */
@Slf4j
public class TestifyContainerRegistry {

  private static final Map<String, GenericContainer<?>> CONTAINERS = new ConcurrentHashMap<>();

  private TestifyContainerRegistry() {
    // Singleton util
  }

  /**
   * Get or create a container instance.
   *
   * @param containerClass the class of the container
   * @param image the docker image (optional)
   * @param shared whether the container should be shared/cached
   * @return the container instance (started)
   */
  @SuppressWarnings("unchecked")
  public static synchronized GenericContainer<?> getContainer(
      Class<? extends GenericContainer> containerClass, String image, boolean shared) {
    String key = containerClass.getName() + ":" + (image != null ? image : "default");

    if (shared && CONTAINERS.containsKey(key)) {
      GenericContainer<?> container = CONTAINERS.get(key);
      if (container.isRunning()) {
        log.debug("Reusing existing container: {}", key);
        return container;
      } else {
        CONTAINERS.remove(key);
      }
    }

    log.info("Starting new container: {}", key);
    try {
      GenericContainer<?> container;
      if (image != null && !image.isEmpty()) {
        // Try to find a constructor that accepts a docker image name
        try {
          container = containerClass.getConstructor(String.class).newInstance(image);
        } catch (NoSuchMethodException e) {
          // Fallback to default constructor if possible and set image if supported?
          // Most Testcontainers classes like MySQLContainer(String) exist.
          container = containerClass.getConstructor().newInstance();
          // We can't set image easily on existing instance unless it exposes method.
          log.warn(
              "Constructor(String) not found for {}, ignoring image override '{}'",
              containerClass.getSimpleName(),
              image);
        }
      } else {
        container = containerClass.getConstructor().newInstance();
      }

      container.start();

      if (shared) {
        CONTAINERS.put(key, container);
        // Register shutdown hook? Testcontainers usually handles Ryuk automatically.
      }
      return container;

    } catch (Exception e) {
      throw new RuntimeException("Failed to start container: " + key, e);
    }
  }

  /** Stop all registered containers. */
  public static synchronized void stopAll() {
    CONTAINERS.values().forEach(GenericContainer::stop);
    CONTAINERS.clear();
  }
}
