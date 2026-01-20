package com.github.loadup.testify.starter.db;

/** Interface for providing database connection parameters. */
public interface DbConnectionProvider {

  /** Get JDBC URL. */
  String getJdbcUrl();

  /** Get database username. */
  String getUsername();

  /** Get database password. */
  String getPassword();
}
