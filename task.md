# Testify Framework Implementation Tasks

## Phase 1: Core & Data Engine

- [/] **testify-core**: Core Models and Utilities
    - [ ] TestContext record model
    - [ ] YamlLoader basic implementation
    - [ ] Add support for TestContainers versioning in dependencies
    - [ ] Fix YamlLoader to properly handle VariableEngine dependency location

- [ ] **testify-data-engine**: Variable Resolution Engine
    - [ ] VariableEngine basic structure
    - [ ] Enhance Faker integration (support more expressions)
    - [ ] Implement time offset calculations (${time.now('+1d')})
    - [ ] Support variable cross-references and dependency resolution
    - [ ] Add comprehensive variable resolution tests
    - [ ] Fix package location (should not be in com.github.loadup.testify.core)

## Phase 2: Assert Engine

- [ ] **testify-assert-engine**: Assertion and Diff Reporting
    - [ ] RowDiff, FieldDiff, MatchResult models
    - [ ] DiffReportBuilder basic structure
    - [ ] OperatorProcessor with switch pattern matching
    - [ ] Complete all OperatorMatcher implementations:
        - [ ] SimpleMatcher (eq, ne)
        - [ ] NumberMatcher (gt, ge, lt, le) with proper type handling
        - [ ] RegexMatcher
        - [ ] ApproxTimeMatcher (complete timestamp conversion)
        - [ ] JsonMatcher (ensure LENIENT mode support)
    - [ ] Implement case-insensitive database column name matching
    - [ ] Add underscore-to-camelCase conversion support
    - [ ] DbAssertEngine basic structure
    - [ ] Enhance DbAssertEngine with improved error reporting

## Phase 3: Mock Engine & Spring Boot Starter

- [ ] **testify-mock-engine**: Mock Lifecycle Management
    - [ ] Create MockRegistry for tracking mocks
    - [ ] Implement dynamic Bean replacement with Mockito spy/mock
    - [ ] Add auto-reset mechanism in AfterMethod
    - [ ] Support YAML mock config parsing (return/throw)
    - [ ] Handle method parameter matching and stubbing

- [ ] **testify-spring-boot-starter**: Framework Entry Point
    - [ ] Fix TestifyAutoConfiguration (currently has stub classes)
    - [ ] Implement TestifyContainerManager for Testcontainers 2.0.3
    - [ ] Create TestifyListener (TestNG IInvokedMethodListener)
    - [ ] Implement TestifyDataProvider with reflection-based parameter conversion
    - [ ] Create TestifyBase abstract class
    - [ ] Add @ServiceConnection support for Spring Boot 3.4
    - [ ] Implement SQL cleanup with variable substitution
    - [ ] Add database setup (insert) support
    - [ ] Create configuration properties (@ConfigurationProperties)

## Phase 4: Demo & Verification

- [ ] **testify-demo**: End-to-End Integration
    - [ ] Create demo service with POJO parameters
    - [ ] Add database operations (CRUD)
    - [ ] Write sample YAML test cases
    - [ ] Implement complete test flow:
        - [ ] Variable generation
        - [ ] SQL cleanup with variables
        - [ ] Mock configuration
        - [ ] Database assertion
        - [ ] Rich diff reporting
    - [ ] Add README with usage examples
    - [ ] Create sample test scenarios

## Additional Tasks

- [ ] Fix dependency management issues in parent POM
- [ ] Update all module POMs with correct dependencies
- [ ] Add JDK 21 specific features where applicable
- [ ] Write comprehensive unit tests for each module
- [ ] Add integration tests
- [ ] Create documentation
