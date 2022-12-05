package com.example.jarayoung.baseModels;

import lombok.Getter;

/**
 * Basic Constant class
 *
 * @author LEE JIHO
 * @since 22.12.02
 * @implNote 수정이 너무 잦을거 같아서 따로 수정자, 수정일자는 적지 않겠습니다.
 * */
@Getter
public enum BasicServerStatus {
    /**
     * 200 : 성공
     * */
    SUCCESS(true, 200, "Request Success"),

    /**
     * 1000 : 데이터베이스 에러
     * */
    DATABASE_ERROR(false, 1000, "Database Error");

    /**
     * 2000 ~ 2999 : Auth 디렉토리에서 발생하는 Error
     * */

    /**
     * 3000 ~ 3999 : User 디렉토리에서 발생하는 Error
     * */

    /**
     * 4000 ~ 4999 : Test 디렉토리에서 발생하는 Error
     * */

    private final boolean isSuccess;
    private final int returnCode;
    private final String returnMessage;

    BasicServerStatus(boolean isSuccess, int returnCode, String returnMessage){
        this.isSuccess = isSuccess;
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }
}
