package com.exercise.movie.dto;

import com.exercise.movie.util.Genre;
import lombok.Data;

@Data
public class MovieDto {

    private Long id;
    private String name;
    private String platform;
    private Genre genre;
    private Double rating;
    private String info;
}
