package com.project1.ticketing.api.utils.exceptions;

import com.project1.ticketing.api.utils.response.BaseResponseStatus;
import org.yaml.snakeyaml.representer.BaseRepresenter;

public class BaseException extends RuntimeException{
    private BaseResponseStatus status;

    public BaseException(BaseResponseStatus status){
        super(status.getMessage());
        this.status = status;
    }

}
