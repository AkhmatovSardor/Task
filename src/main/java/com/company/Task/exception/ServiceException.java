package com.company.Task.exception;

import com.company.Task.dto.Response;

public class ServiceException extends RuntimeException {
    public ServiceException(Response dto) {
        super(dto.toString());
    }

}
