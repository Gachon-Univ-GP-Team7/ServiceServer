package com.example.jarayoung.core.user;

import com.example.jarayoung.core.user.model.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Repository
public class UserDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public void getDatasource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public GetUserRes getUserInfo(int userIdx) {
        String queryString = "select userIdx, userName, email, babyName, babyBirthday from User where userIdx = ?;";
        String getVoiceDateQuery = "select ifnull(min(testDate), 'NULL') as testDate from VoiceTest where userIdx = ? order by voiceTestIdx desc limit 1;";
        String getVideoDateQuery = "select ifnull(min(testDate), 'NULL') as testDate from VideoTest where userIdx = ? order by videoTestIdx desc limit 1;";

        String voiceDate = this.jdbcTemplate.queryForObject(getVoiceDateQuery, String.class, userIdx);
        String videoDate = this.jdbcTemplate.queryForObject(getVideoDateQuery, String.class, userIdx);

        String getVoiceFinalQuery = "select overallScore from VoiceTest where userIdx = ? order by voiceTestIdx desc limit 1;";
        String getVideoFinalQuery = "select overallScore from VideoTest where userIdx = ? order by videoTestIdx desc limit 1;";

        String finalDate;
        float finalOverall;

        if(voiceDate.equals("NULL") && !videoDate.equals("NULL")){
            finalDate = videoDate;
            finalOverall = this.jdbcTemplate.queryForObject(getVideoFinalQuery, Float.class, userIdx);
        }
        else if (!voiceDate.equals("NULL") && videoDate.equals("NULL")) {
            finalDate = voiceDate;
            finalOverall = this.jdbcTemplate.queryForObject(getVoiceFinalQuery, Float.class, userIdx);
        }
        else if (!voiceDate.equals("NULL") && !videoDate.equals("NULL")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime voice = LocalDateTime.parse(voiceDate, formatter);
            LocalDateTime video = LocalDateTime.parse(videoDate, formatter);

            //음성이 더 최근일때
            if(voice.isAfter(video)){
                finalDate = voiceDate;
                finalOverall = this.jdbcTemplate.queryForObject(getVoiceFinalQuery, Float.class, userIdx);
            }
            //영상이 더 최근일때
            else{
                finalDate = videoDate;
                finalOverall = this.jdbcTemplate.queryForObject(getVideoFinalQuery, Float.class, userIdx);
            }
        }
        else{
            finalDate = "NULL";
            finalOverall = 0;
        }

        return this.jdbcTemplate.queryForObject(queryString, (rs, rowNum) ->
                new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("babyName"),
                        rs.getString("babyBirthday"),
                        finalDate,
                        finalOverall

                ), userIdx);
    }
}
