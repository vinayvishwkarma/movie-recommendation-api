package com.exercise.movie.exception;

import java.util.Date;

import com.exercise.movie.data.APIExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<APIExceptionDto> handleRecommendationNotFoundException(
            RecommendationNotFoundException re, WebRequest request) {
        APIExceptionDto apiException = new APIExceptionDto();
        apiException.setCode(404);
        apiException.setMsg(re.getMessage());
        apiException.setDate(new Date());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<APIExceptionDto> handleIllegalArgumentException(
            IllegalArgumentException iae, WebRequest request) {
        APIExceptionDto apiException = new APIExceptionDto();
        apiException.setCode(400);
        apiException.setMsg(iae.getMessage());
        apiException.setDate(new Date());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

}
