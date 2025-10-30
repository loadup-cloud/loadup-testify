package com.loadup.testify.core.model;

import java.util.Map;

public class TableData {
    private String refId;
    private Map<String, Object> columns;

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public Map<String, Object> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, Object> columns) {
        this.columns = columns;
    }
}