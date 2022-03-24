package com.exercise.movie.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<APIException> handleRecommendationNotFoundException(
            RecommendationNotFoundException re, WebRequest request) {
        APIException apiException = new APIException();
        apiException.setCode(404);
        apiException.setMsg(re.getMessage());
        apiException.setDate(new Date());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

}
