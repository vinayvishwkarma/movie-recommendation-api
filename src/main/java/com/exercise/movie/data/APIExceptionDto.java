package com.exercise.movie.data;

import java.util.Date;

import lombok.Data;

@Data
public class APIExceptionDto {

    private String msg;
    private Date date;
    private Integer code;
}
