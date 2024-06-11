package com.project1.ticketing.api.utils.exceptions;

import com.project1.ticketing.api.utils.response.BaseResponseStatus;

public class InvalidTokenException extends BaseException{
    public InvalidTokenException(BaseResponseStatus status) {
        super(status);

    }
}
