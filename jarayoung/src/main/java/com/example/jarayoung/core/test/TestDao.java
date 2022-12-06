package com.example.jarayoung.core.test;

import com.example.jarayoung.core.test.model.GetTestListRes;
import com.example.jarayoung.core.test.model.TestListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TestDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void getDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public GetTestListRes getTestList(int userIdx) {
        String videoQuery = "select videoTestIdx, testDate from VideoTest where userIdx = ? order by testDate;";
        String voiceQuery = "select voiceTestIdx, testDate from VoiceTest where userIdx = ? order by testDate;";

        Object[] queryParams = new Object[] {
                userIdx
        };

        List<TestListData> voiceData;
        List<TestListData> videoData;

        voiceData = this.jdbcTemplate.query(voiceQuery, ((rs, rowNum) -> new TestListData(
                rs.getInt("voiceTestIdx"),
                rs.getString("testDate")
        )), queryParams);

        videoData = this.jdbcTemplate.query(videoQuery, ((rs, rowNum) -> new TestListData(
                rs.getInt("videoTestIdx"),
                rs.getString("testDate")
        )), queryParams);

        return new GetTestListRes(voiceData, videoData);
    }
}
