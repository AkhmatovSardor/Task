package com.company.Task.exception;

import com.company.Task.dto.ErrorDto;
import com.company.Task.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerRecourse {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ResponseEntity<Response<Void>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<ErrorDto> errors = fieldErrors.stream().map(f -> {
            String field = f.getField();
            String message = f.getDefaultMessage();
            String rejectionValue = String.valueOf(f.getRejectedValue());
            return new ErrorDto(field, message + "Rejection Value " + rejectionValue);
        }).toList();
        return ResponseEntity.badRequest().body(Response.<Void>builder().message("Validation error!").errors(errors).build());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public List<ErrorDto> serviceExceptionHandler(ServiceException e) {
        List<ErrorDto> errors = new ArrayList<>();
        errors.add(new ErrorDto("Error message", e.getMessage()));
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(LogicException.class)
    public List<ErrorDto> serviceExceptionHandler2(LogicException e) {
        List<ErrorDto> errors = new ArrayList<>();
        errors.add(new ErrorDto("error message", e.getMessage()));
        return errors;
    }
}