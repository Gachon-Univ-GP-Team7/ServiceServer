package com.example.jarayoung.core.test;

import com.example.jarayoung.core.test.model.*;
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

    /**
     * 테스트 기록 리스트 조회 API
     * */
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

    /**
     * 테스트 기록 점수 그래프 조회 API
     * */
    public GetTestGraphRes getTestGraphList(int userIdx) {

        String voiceListQuery =
                "select voiceTestIdx, testDate, overallScore from VoiceTest where userIdx = ? order by testDate;";
        String videoListQuery =
                "select videoTestIdx, testDate, overallScore from VideoTest where userIdx = ? order by testDate;";

        List<TestGraphListData> voiceGraphList = this.jdbcTemplate.query(voiceListQuery, (rs, rowNum) -> (
                new TestGraphListData(
                        rs.getInt("voiceTestIdx"),
                        rs.getString("testDate"),
                        rs.getFloat("overallScore")
                )
            ), userIdx);

        List<TestGraphListData> videoGraphList = this.jdbcTemplate.query(videoListQuery, (rs, rowNum) -> (
                new TestGraphListData(
                        rs.getInt("videoTestIdx"),
                        rs.getString("testDate"),
                        rs.getFloat("overallScore")
                )
            ), userIdx);

        return new GetTestGraphRes(voiceGraphList, videoGraphList);
    }

    /**
     * 테스트 기록 상세조회 API (음성)
     * */
    public GetTestScoreRes getVoiceTestScore(int testIdx) {
        String queryString =
                "select VT.voiceTestIdx, testDate, overallScore, voiceLable1 from\n" +
                "VoiceTest as VT left join VoiceTestScore VTS on VT.voiceTestIdx = VTS.voiceTestIdx\n" +
                "where VT.voiceTestIdx = ?;";

        return this.jdbcTemplate.queryForObject(queryString, (rs, rowNum) -> (
                new GetTestScoreRes(
                        rs.getInt("voiceTestIdx"),
                        rs.getString("testDate"),
                        rs.getFloat("overallScore"),
                        rs.getFloat("voiceLable1")
                )
            ), testIdx);
    }

    /**
     * 테스트 기록 상세조회 API (영상)
     * */
    public GetTestScoreRes getVideoTestScore(int testIdx) {
        String queryString =
                "select VT.videoTestIdx, testDate, overallScore, videoLable1 from\n" +
                "VideoTest as VT left join VideoTestScore VTS on VT.videoTestIdx = VTS.videoTestIdx\n" +
                "where VT.videoTestIdx = ?;";

        return this.jdbcTemplate.queryForObject(queryString, (rs, rowNum) -> (
                new GetTestScoreRes(
                        rs.getInt("videoTestIdx"),
                        rs.getString("testDate"),
                        rs.getFloat("overallScore"),
                        rs.getFloat("videoLable1")
                )
        ), testIdx);
    }

    /**
     * 테스트 진행 뷰 구성 API (통합 (모드 받아야함))
     * */
    public GetTestProgressView getTestProgressView(int userIdx, int testMode) {
        String voiceQuery =
                "select count(voiceTestIdx) as testCount from VoiceTest where userIdx = ?;";
        String videoQuery =
                "select count(videoTestIdx) as testCount from VideoTest where userIdx = ?;";

        if(testMode == 0){ //Voice
            return this.jdbcTemplate.queryForObject(voiceQuery, (rs, rowNum) -> (
                    new GetTestProgressView(
                            rs.getInt("testCount") + 1
                    )
                ), userIdx);
        }
        else{ //Video
            return this.jdbcTemplate.queryForObject(videoQuery, (rs, rowNum) -> (
                    new GetTestProgressView(
                            rs.getInt("testCount") + 1
                    )
            ), userIdx);
        }
    }


}
