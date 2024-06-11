package com.project1.ticketing.api.utils.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseResponseStatus {
    SUCCESS(true, HttpStatus.OK.value(), "요청에 성공하였습니다."),
    FAIL(false, HttpStatus.BAD_REQUEST.value(), "요청에 실패하였습니다.");

    private final boolean success;
    private final int code;
    private final String message;

    BaseResponseStatus(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }


    }
