package com.exercise.movie.controller;

import java.io.IOException;
import java.util.List;

import com.exercise.movie.dto.MovieDto;
import com.exercise.movie.service.MovieRecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/recommendations")
public class MovieRecommendationController {

    private static final Logger logger = LoggerFactory.getLogger(
            MovieRecommendationController.class);

    @Autowired
    private MovieRecommendationService movieRecommendationService;

    @Operation(summary = "Recommend the movie on the basis of genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "recommended movie successfully",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Recommendation not found for the given genre",
                    content = @Content)})
    @GetMapping(path = "/movies/{genre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieDto>> recommendMovies(@PathVariable String genre) {
        try {
            List<MovieDto> movieDtos = movieRecommendationService.recommendMoviesByGenre(genre);
            return new ResponseEntity<>(movieDtos, HttpStatus.OK);
        } catch (IOException ioe) {
            logger.error("Exception while fetching the movie data", ioe);
            return ResponseEntity.status(500).build();
        }
    }

}
