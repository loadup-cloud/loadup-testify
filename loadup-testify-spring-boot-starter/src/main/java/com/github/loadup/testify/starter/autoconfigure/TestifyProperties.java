package com.github.loadup.testify.starter.autoconfigure;

/*-
 * #%L
 * LoadUp Testify - Spring Boot Starter
 * %%
 * Copyright (C) 2026 LoadUp Cloud
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for LoadUp Testify.
 * These properties can be set in application.properties or application.yml.
 *
 * Example:
 * <pre>
 * loadup.testify.enabled=true
 * loadup.testify.date-tolerance=5000
 * loadup.testify.clear-tables-before-test=true
 * </pre>
 */
@Data
@ConfigurationProperties(prefix = "loadup.testify")
public class TestifyProperties {

    /**
     * Enable or disable LoadUp Testify auto-configuration.
     */
    private boolean enabled = true;

    /**
     * Default tolerance for datetime comparisons in milliseconds.
     */
    private long dateTolerance = 5000L;

    /**
     * Whether to clear database tables before inserting PrepareData.
     */
    private boolean clearTablesBeforeTest = true;

    /**
     * Whether to enable detailed logging for assertions.
     */
    private boolean verboseLogging = true;

    /**
     * Base path for test data files (relative to classpath).
     * If not set, uses the test class package path.
     */
    private String testDataBasePath;
}
