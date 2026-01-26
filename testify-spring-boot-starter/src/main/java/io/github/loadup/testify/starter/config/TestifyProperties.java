package io.github.loadup.testify.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** Testify framework configuration properties. */
@ConfigurationProperties(prefix = "testify")
public class TestifyProperties {

  private Containers containers = new Containers();
  private Database database = new Database();

  public Containers getContainers() {
    return containers;
  }

  public void setContainers(Containers containers) {
    this.containers = containers;
  }

  public Database getDatabase() {
    return database;
  }

  public void setDatabase(Database database) {
    this.database = database;
  }

  public static class Containers {
    private boolean enabled = false; // Default to false, use physical DB

    public boolean isEnabled() {
      return enabled;
    }

    public void setEnabled(boolean enabled) {
      this.enabled = enabled;
    }
  }

  public static class Database {
    private String columnNamingStrategy = "caseInsensitive"; // or "camelCase", "snake_case"

    public String getColumnNamingStrategy() {
      return columnNamingStrategy;
    }

    public void setColumnNamingStrategy(String columnNamingStrategy) {
      this.columnNamingStrategy = columnNamingStrategy;
    }
  }
}
