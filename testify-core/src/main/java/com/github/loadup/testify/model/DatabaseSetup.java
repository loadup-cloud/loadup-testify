package com.github.loadup.testify.model;

import java.util.List;
import java.util.Map;

public class DatabaseSetup {
    private boolean cleanBefore = false;
    private boolean cleanAfter = false;
    private List<String> truncateTables;
    private Map<String, List<TableData>> data;

    public boolean isCleanBefore() {
        return cleanBefore;
    }

    public void setCleanBefore(boolean cleanBefore) {
        this.cleanBefore = cleanBefore;
    }

    public boolean isCleanAfter() {
        return cleanAfter;
    }

    public void setCleanAfter(boolean cleanAfter) {
        this.cleanAfter = cleanAfter;
    }

    public List<String> getTruncateTables() {
        return truncateTables;
    }

    public void setTruncateTables(List<String> truncateTables) {
        this.truncateTables = truncateTables;
    }

    public Map<String, List<TableData>> getData() {
        return data;
    }

    public void setData(Map<String, List<TableData>> data) {
        this.data = data;
    }
}

