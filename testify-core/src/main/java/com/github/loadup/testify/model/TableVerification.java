package com.github.loadup.testify.model;

import java.util.List;
import java.util.Map;

public class TableVerification {
    private String table;
    private Map<String, Object> where;
    private Integer expectedCount;
    private List<ColumnVerification> columns;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Map<String, Object> getWhere() {
        return where;
    }

    public void setWhere(Map<String, Object> where) {
        this.where = where;
    }

    public Integer getExpectedCount() {
        return expectedCount;
    }

    public void setExpectedCount(Integer expectedCount) {
        this.expectedCount = expectedCount;
    }

    public List<ColumnVerification> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnVerification> columns) {
        this.columns = columns;
    }
}

