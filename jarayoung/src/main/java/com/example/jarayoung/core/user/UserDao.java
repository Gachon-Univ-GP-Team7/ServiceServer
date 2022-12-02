package com.example.jarayoung.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public void getDatasource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }
}
