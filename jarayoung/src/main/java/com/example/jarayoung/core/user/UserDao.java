package com.example.jarayoung.core.user;

import com.example.jarayoung.core.user.model.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public void getDatasource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public GetUserRes getUserInfo(int userIdx) {
        String queryString = "select userIdx, userName, email, babyName, babyBirthday from User where userIdx = ?;";

        return this.jdbcTemplate.queryForObject(queryString, (rs, rowNum) ->
                new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("email"),
                        rs.getString("babName"),
                        rs.getString("babyBirthday")
                ), userIdx);
    }
}
