package com.example.jarayoung.core.user;

import com.example.jarayoung.baseModels.BaseException;
import com.example.jarayoung.baseModels.BasicServerStatus;
import com.example.jarayoung.core.user.model.GetUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProvider {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserDao userDao;

    public UserProvider(UserDao userDao) {
        this.userDao = userDao;
    }

    public GetUserRes getUserInfo(int userIdx) throws BaseException {
        try{
            return userDao.getUserInfo(userIdx);
        } catch (Exception exception) {
            throw new BaseException(BasicServerStatus.DATABASE_ERROR);
        }
    }
}
