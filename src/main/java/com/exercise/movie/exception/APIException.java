package com.exercise.movie.exception;

import java.util.Date;

import lombok.Data;

@Data
public class APIException {

    private String msg;
    private Date date;
    private Integer code;
}
