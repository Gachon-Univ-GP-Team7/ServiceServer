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
    SUCCESS(true, 200, "Request Success");

    private final boolean isSuccess;
    private final int returnCode;
    private final String returnMessage;

    BasicServerStatus(boolean isSuccess, int returnCode, String returnMessage){
        this.isSuccess = isSuccess;
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }
}
