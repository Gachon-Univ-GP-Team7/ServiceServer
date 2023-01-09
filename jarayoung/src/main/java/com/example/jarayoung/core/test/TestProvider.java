package com.example.jarayoung.core.test;

import com.example.jarayoung.baseModels.BaseException;
import com.example.jarayoung.baseModels.BasicServerStatus;
import com.example.jarayoung.core.test.model.GetTestGraphRes;
import com.example.jarayoung.core.test.model.GetTestListRes;
import com.example.jarayoung.core.test.model.GetTestProgressView;
import com.example.jarayoung.core.test.model.GetTestScoreRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TestProvider {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final TestDao testDao;

    public TestProvider(TestDao testDao) {
        this.testDao = testDao;
    }

    /**
     * 테스트 기록 리스트 조회 API
     * */
    public GetTestListRes getTestList(int userIdx) throws BaseException {

        try{
            return testDao.getTestList(userIdx);
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new BaseException(BasicServerStatus.DATABASE_ERROR);

        }
    }

    /**
     * 테스트 기록 점수 그래프 조회 API
     * */
    public GetTestGraphRes getTestGraphList(int userIdx) throws BaseException{
        try{
            return testDao.getTestGraphList(userIdx);
        }catch (Exception exception) {
            throw new BaseException(BasicServerStatus.DATABASE_ERROR);
        }
    }

    /**
     * 테스트 기록 상세조회 API (음성)
     * */
    public GetTestScoreRes getVoiceTestScore(int testIdx) throws BaseException{

        try{
            return testDao.getVoiceTestScore(testIdx);
        }catch (Exception exception){
            throw new BaseException(BasicServerStatus.DATABASE_ERROR);
        }
    }

    /**
     * 테스트 기록 상세조회 API (영상)
     * */
    public GetTestScoreRes getVideoTestScore(int testIdx) throws BaseException{

        try{
            return testDao.getVideoTestScore(testIdx);
        }catch (Exception exception){
            throw new BaseException(BasicServerStatus.DATABASE_ERROR);
        }
    }

    /**
     * 테스트 진행 뷰 구성 API (통합 (모드 받아야함))
     * */
    public GetTestProgressView getTestProgressView(int userIdx, int testMode) throws BaseException{

        try{
            return testDao.getTestProgressView(userIdx, testMode);
        }catch (Exception exception){
            throw new BaseException(BasicServerStatus.DATABASE_ERROR);
        }

    }
}
