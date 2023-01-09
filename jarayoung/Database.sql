-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- User Table Create SQL
CREATE TABLE User
(
    `userIdx`       INT             NOT NULL    AUTO_INCREMENT COMMENT '유저 인덱스',
    `name`          VARCHAR(25)     NOT NULL    COMMENT '유저 이름',
    `ID`            VARCHAR(45)     NOT NULL    COMMENT '아이디',
    `password`      VARCHAR(200)    NOT NULL    COMMENT '비밀번호',
    `email`         VARCHAR(60)     NOT NULL    COMMENT '이메일',
    `babyName`      VARCHAR(25)     NOT NULL    COMMENT '아기 이름',
    `babyBirthday`  DATE            NOT NULL    COMMENT '아기 생년월일',
    `createdAt`     TIMESTAMP       NOT NULL    DEFAULT current_timestamp COMMENT '유저 가입 일자',
    `updatedAt`     TIMESTAMP       NOT NULL    DEFAULT current_timestamp on update current_timestamp COMMENT '유저 정보 수정일자',
    PRIMARY KEY (userIdx)
);

ALTER TABLE User COMMENT '유저 테이블';


-- VideoTest Table Create SQL
CREATE TABLE VideoTest
(
    `videoTestIdx`  INT          NOT NULL    AUTO_INCREMENT COMMENT '영상 테스트 인덱스',
    `userIdx`       INT          NOT NULL    COMMENT '테스트 진행한 유저 인덱스',
    `testDate`      TIMESTAMP    NOT NULL    COMMENT '테스트 일자',
    `overallScore`  FLOAT        NOT NULL    COMMENT '전체 스코어',
    PRIMARY KEY (videoTestIdx)
);

ALTER TABLE VideoTest COMMENT '영상 테스트 테이블';

ALTER TABLE VideoTest
    ADD CONSTRAINT FK_VideoTest_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- VoiceTest Table Create SQL
CREATE TABLE VoiceTest
(
    `voiceTestIdx`  INT          NOT NULL    AUTO_INCREMENT COMMENT '음성 테스트 인덱스',
    `userIdx`       INT          NOT NULL    COMMENT '테스트 진행한 유저 인덱스',
    `testDate`      TIMESTAMP    NOT NULL    COMMENT '테스트 일자',
    `overallScore`  FLOAT        NOT NULL    COMMENT '전체 스코어',
    PRIMARY KEY (voiceTestIdx)
);

ALTER TABLE VoiceTest COMMENT '음성 테스트 테이블';

ALTER TABLE VoiceTest
    ADD CONSTRAINT FK_VoiceTest_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- VoiceTestScore Table Create SQL
CREATE TABLE VoiceTestScore
(
    `voiceScoreIdx`  INT      NOT NULL    AUTO_INCREMENT COMMENT '음성 점수 인덱스',
    `voiceTestIdx`   INT      NOT NULL    COMMENT '음성 테스트 인덱스',
    `voiceLable1`    FLOAT    NOT NULL,
    PRIMARY KEY (voiceScoreIdx)
);

ALTER TABLE VoiceTestScore COMMENT '음성 테스트 세부 점수';

ALTER TABLE VoiceTestScore
    ADD CONSTRAINT FK_VoiceTestScore_voiceTestIdx_VoiceTest_voiceTestIdx FOREIGN KEY (voiceTestIdx)
        REFERENCES VoiceTest (voiceTestIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- VideoTestScore Table Create SQL
CREATE TABLE VideoTestScore
(
    `videoScoreIdx`  INT      NOT NULL    AUTO_INCREMENT COMMENT '영상 점수 인덱스',
    `videoTestIdx`   INT      NOT NULL    COMMENT '비디오 테스트 인덱스',
    `videoLable1`    FLOAT    NOT NULL,
    PRIMARY KEY (videoScoreIdx)
);

ALTER TABLE VideoTestScore COMMENT '영상 테스트 세부 점수';

ALTER TABLE VideoTestScore
    ADD CONSTRAINT FK_VideoTestScore_videoTestIdx_VideoTest_videoTestIdx FOREIGN KEY (videoTestIdx)
        REFERENCES VideoTest (videoTestIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;