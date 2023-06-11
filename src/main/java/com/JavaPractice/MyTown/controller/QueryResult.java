package com.JavaPractice.MyTown.controller;

import java.util.List;

public class QueryResult {
    private List<String> columns;
    private List<List<Object>> rows;

    public List<String> getColumns() {
        return columns;
    }
    public void setColumns(List<String> columns) {
        this.columns = columns;
    }
    public List<List<Object>> getRows() {
        return rows;
    }
    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }
}
