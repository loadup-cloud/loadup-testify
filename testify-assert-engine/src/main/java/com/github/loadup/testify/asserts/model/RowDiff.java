package com.github.loadup.testify.asserts.model;


import java.util.Map;

public record RowDiff(int index, String status, String message, Map<String, Object> rawExpected, Map<String, FieldDiff> fieldDiffs) {}
