package com.example.jarayoung.core.auth;

import com.example.jarayoung.baseModels.BaseException;
import com.example.jarayoung.baseModels.BasicServerStatus;
import com.example.jarayoung.core.auth.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final AuthDao authDao;

    public AuthService(AuthDao authDao) {
        this.authDao = authDao;
    }

    public PostApplyRes userApply(PostApplyReq postApplyReq) throws BaseException {
        try{
            return authDao.userApply(postApplyReq);
        }catch (Exception exception){
            logger.error(exception.getMessage());
            throw new BaseException(BasicServerStatus.DATABASE_ERROR);
        }
    }

    public PostLoginRes userLogin(PostLoginReq postLoginReq) throws BaseException {

        try{
            return authDao.userLogin(postLoginReq);
        }catch (Exception exception){
            throw new BaseException(BasicServerStatus.DATABASE_ERROR);
        }
    }

    public PostEmailRes checkEmailDuplication(String email) throws BaseException{

        try{
            return authDao.checkEmailDuplication(email);
        } catch (Exception exception) {
            throw new BaseException(BasicServerStatus.DATABASE_ERROR);
        }
    }
}
