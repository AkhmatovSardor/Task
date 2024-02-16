package com.company.Task.exception;

import com.company.Task.dto.Response;

public class Errors extends RuntimeException {
    public Errors() {
        super();
    }

    public Errors(String message) {
        super(message);
    }

    public Errors(Response dto) {
        super(dto.toString());
    }


}
