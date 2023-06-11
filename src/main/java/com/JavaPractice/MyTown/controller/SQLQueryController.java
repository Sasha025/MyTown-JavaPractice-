package com.JavaPractice.MyTown.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SQLQueryController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @RequestMapping("/sql-query")
    public String showSQLQueryPage(Model model) {
        model.addAttribute("pageTitle", "SQL Query Page");
        return "sql-query";
    }
    @PostMapping("/execute-query")
    public String executeQuery(@RequestParam("sqlQuery") String sqlQuery, Model model) {
        try {
            List<List<Object>> queryResult = jdbcTemplate.query(sqlQuery, new ResultRowMapper());
            List<List<String>> queryResultC = jdbcTemplate.query(sqlQuery, new ResultLabelMapper());
            QueryResult result = new QueryResult();
            QueryResult resultC = new QueryResult();
            result.setRows(queryResult);
            resultC.setColumns(queryResultC.get(0));
            model.addAttribute("pageTitle", "SQL Query Page");
            model.addAttribute("queryResult", result);
            model.addAttribute("columnHeaders", resultC); // Добавленная строка
        } catch (Exception e) {
            model.addAttribute("error", "Error executing the SQL query: " + e.getMessage());
        }
        return "sql-query";
    }
    private static class ResultRowMapper implements RowMapper<List<Object>> {
     @Override
     public List<Object> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
         ResultSetMetaData meta = resultSet.getMetaData();
         int columnCount = meta.getColumnCount();
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
                ResultSetMetaData meta = resultSet.getMetaData();
                int columnCount = meta.getColumnCount();
                List<String> columns = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    columns.add(meta.getColumnName(i));
                }
                return columns;
            }
        }
}