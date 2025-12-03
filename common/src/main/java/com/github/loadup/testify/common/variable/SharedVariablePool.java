package com.github.loadup.testify.common.variable;

/*-
 * #%L
 * LoadUp Testify - Common
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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-safe shared variable pool for storing and retrieving variables across test cases.
 * Supports automatic capture of Datafaker-generated values and cross-file references.
 */
public class SharedVariablePool {

    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL_POOL = 
            ThreadLocal.withInitial(ConcurrentHashMap::new);

    private SharedVariablePool() {
        // Utility class
    }

    /**
     * Store a variable in the pool.
     *
     * @param name  the variable name
     * @param value the variable value
     */
    public static void put(String name, Object value) {
        THREAD_LOCAL_POOL.get().put(name, value);
    }

    /**
     * Retrieve a variable from the pool.
     *
     * @param name the variable name
     * @return the variable value, or null if not found
     */
    public static Object get(String name) {
        return THREAD_LOCAL_POOL.get().get(name);
    }

    /**
     * Retrieve a variable from the pool with a default value.
     *
     * @param name         the variable name
     * @param defaultValue the default value if not found
     * @return the variable value, or the default value if not found
     */
    public static Object getOrDefault(String name, Object defaultValue) {
        return THREAD_LOCAL_POOL.get().getOrDefault(name, defaultValue);
    }

    /**
     * Check if a variable exists in the pool.
     *
     * @param name the variable name
     * @return true if the variable exists
     */
    public static boolean contains(String name) {
        return THREAD_LOCAL_POOL.get().containsKey(name);
    }

    /**
     * Remove a variable from the pool.
     *
     * @param name the variable name
     * @return the removed value, or null if not found
     */
    public static Object remove(String name) {
        return THREAD_LOCAL_POOL.get().remove(name);
    }

    /**
     * Get all variables in the pool.
     *
     * @return a copy of the current variable pool
     */
    public static Map<String, Object> getAll() {
        return new ConcurrentHashMap<>(THREAD_LOCAL_POOL.get());
    }

    /**
     * Clear all variables from the pool for the current thread.
     */
    public static void clear() {
        THREAD_LOCAL_POOL.get().clear();
    }

    /**
     * Clean up the thread-local storage to prevent memory leaks.
     */
    public static void cleanup() {
        THREAD_LOCAL_POOL.remove();
    }
}
