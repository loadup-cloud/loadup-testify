package com.github.loadup.testify.demo.debug;

import com.github.loadup.testify.core.loader.YamlLoader;
import com.github.loadup.testify.core.model.TestContext;

public class YamlDebugTest {
  public static void main(String[] args) {
    try {
      YamlLoader loader = new YamlLoader();
      TestContext context = loader.load("testcases/UserServiceTest/testCreateUser.yaml");
      System.out.println("Successfully loaded YAML:");
      System.out.println("Variables: " + context.variables());
      System.out.println("Input: " + context.input());
      System.out.println("Setup: " + context.setup());
      System.out.println("Expect: " + context.expect());
    } catch (Exception e) {
      System.err.println("Failed to load YAML: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
