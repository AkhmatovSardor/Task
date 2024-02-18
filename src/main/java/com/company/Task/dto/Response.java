package com.company.Task.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private int code;
    private String message;
    private boolean success;
    private T data;
    private List<ErrorDto> errors;
}