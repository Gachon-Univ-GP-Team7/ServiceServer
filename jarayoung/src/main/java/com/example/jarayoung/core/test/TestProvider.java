package com.example.jarayoung.core.test;

import com.example.jarayoung.baseModels.BaseException;
import com.example.jarayoung.baseModels.BasicServerStatus;
import com.example.jarayoung.core.test.model.GetTestListRes;
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

    public GetTestListRes getTestList(int userIdx) throws BaseException {

        try{
            return testDao.getTestList(userIdx);
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new BaseException(BasicServerStatus.DATABASE_ERROR);

        }
    }
}
