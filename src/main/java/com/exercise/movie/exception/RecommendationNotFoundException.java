package com.exercise.movie.exception;

public class RecommendationNotFoundException extends RuntimeException {

    public RecommendationNotFoundException(String msg) {
        super(msg);
    }
}
