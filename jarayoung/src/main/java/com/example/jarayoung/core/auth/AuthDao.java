package com.example.jarayoung.core.auth;

import com.example.jarayoung.core.auth.model.PostApplyReq;
import com.example.jarayoung.core.auth.model.PostApplyRes;
import com.example.jarayoung.core.auth.model.PostLoginReq;
import com.example.jarayoung.core.auth.model.PostLoginRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class AuthDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void getDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public PostApplyRes userApply(PostApplyReq postApplyReq) {
        String queryString =
                "insert into User (userName, userPassword, email, babyName, babyBirthday) values (?, ?, ?, ?, ?);";

        Object[] queryParams =  new Object[] {
                postApplyReq.getUserName(),
                postApplyReq.getPassword(),
                postApplyReq.getEmail(),
                postApplyReq.getInfantName(),
                postApplyReq.getInfantBirthday()
        };

        int result = this.jdbcTemplate.update(queryString, queryParams);

        return new PostApplyRes(result > 0);
    }

    public PostLoginRes userLogin(PostLoginReq postLoginReq) {

        String queryString = "select ifnull(userIdx, -1) from User where email = ? and userPassword = ?;";

        Object[] queryParams = new Object[]{
                postLoginReq.getEmail(),
                postLoginReq.getPassword()
        };

        int result = this.jdbcTemplate.queryForObject(queryString, int.class, queryParams);

        return new PostLoginRes(result != -1, result);

    }
}
