# LoadUp Testify Architecture

LoadUp Testify is a data-driven testing SDK designed for Spring Boot applications. It emphasizes "Convention over Configuration," decoupling test data from test logic to improve maintainability and developer efficiency.

## High-Level Architecture

```mermaid
graph TD
    UserTest[User Test Class] --> TestifyEngine[TestExecutionEngine]
    TestifyEngine --> TestCaseLoader[TestCaseLoader]
    TestifyEngine --> PrepareDataService[PrepareDataService]
    TestifyEngine --> ExpectedDataService[ExpectedDataService]
    TestifyEngine --> AssertionService[AssertionService]
    
    TestCaseLoader --> FileSystem[Test Data Files (YAML/CSV/JSON)]
    PrepareDataService --> DB[Database]
    ExpectedDataService --> DB
    
    subgraph Core
        TestifyEngine
        TestCaseLoader
        VariableResolver[VariableResolver]
    end
    
    subgraph Data
        PrepareDataService
        ExpectedDataService
    end
    
    subgraph Assertions
        AssertionService
        JsonAssertion[JsonAssertion]
        DatabaseAssertion[DatabaseAssertion]
    end
    
    subgraph Containers
        ContextCustomizer[TestifyContainersContextCustomizer] --> Testcontainers[Testcontainers]
        ContextCustomizer --> SpringContext[Spring ApplicationContext]
    end
```

## Key Components

### 1. Core Module (`loadup-testify-core`)
- **TestExecutionEngine**: The heart of the framework. Orchestrates the test lifecycle: preparation, execution, and verification.
    - Handles `@TestifyConcurrency` for performance testing.
    - Manages the test execution flow.
- **TestCaseLoader**: improved in v1.0. Responsbile for implicit test case discovery.
    - Scans convention-based directories (`{ServiceName}.{methodName}/{caseId}`).
    - auto-generates default configurations if `test_config.yaml` is missing.
    - Implements **Smart Argument Injection** by mapping CSV headers to method parameters.
- **TestifyTest**: A meta-annotation that links TestNG's DataProvider to the Testify engine, enabling argument injection.

### 2. Data Module (`loadup-testify-data`)
- **PrepareDataService**: Handles loading data into the database before test execution.
- **ExpectedDataService**: Loads expected data and queries the database for actual data after execution.
- **DataSourceSupport**: Abstract abstraction for different data sources (JDBC, potentially NoSQL in future).

### 3. Assertions Module (`loadup-testify-assertions`)
- **AssertionService**: Provides a unified API for assertions.
- Supports JSON comparison (using Jackson/Skyscreamer logic) and database row verification.
- Extensible via `AssertionRule`.

### 4. Containers Module (`loadup-testify-containers`)
- **Integration**: Seamlessly integrates Testcontainers with Spring Boot tests.
- **@EnableContainer**: Annotation to declaratively start containers.
- **Auto-Configuration**: Automatically injects container properties (e.g., JDBC-URL, Redis host/port, S3 endpoint) into the Spring Environment.
- **ContextCustomizer**: Uses Spring's `ContextCustomizer` SPI to start containers early in the context lifecycle.

## Design Patterns

- **Convention over Configuration**: The framework infers test cases, arguments, and expected results from the file system structure, minimizing boilerplate code.
- **Strategy Pattern**: used for variable resolution (`VariableResolver`) and assertions.
- **Singleton Pattern**: used in `TestifyContainerRegistry` to efficiently share containers across test classes.

## Features

- **Implicit Discovery**: No need to explicitly list test cases; the folder structure drives the test suite.
- **Smart Argument Injection**: Test method arguments are automatically populated from CSV data.
- **Zero-Config Testcontainers**: Detects generic containers (like Redis, MySQL) and auto-configures Spring Boot properties without manual `DynamicPropertySource` code.
- **Concurrency Testing**: lightweight performance testing via `@TestifyConcurrency`.
