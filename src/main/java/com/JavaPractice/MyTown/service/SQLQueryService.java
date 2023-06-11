package com.JavaPractice.MyTown.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SQLQueryService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<List<Object>> executeQuery(String sqlQuery) {
        try {
            return jdbcTemplate.query(sqlQuery, new ResultRowMapper());
        } catch (Exception e) {
            throw new RuntimeException("Error executing the SQL query: " + e.getMessage(), e);
        }
    }
    private static class ResultRowMapper implements RowMapper<List<Object>> {
        @Override
        public List<Object> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            int columnCount = resultSet.getMetaData().getColumnCount();
            List<Object> row = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                row.add(resultSet.getObject(i));
            }
            return row;
        }
    }
    private static class ResultLabelMapper implements RowMapper<List<String>> {
        @Override
        public List<String> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            int columnCount = resultSet.getMetaData().getColumnCount();
            ResultSetMetaData meta = resultSet.getMetaData();
            List<String> columns = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(meta.getColumnLabel(i));
            }
            return columns;
        }
    }
}
