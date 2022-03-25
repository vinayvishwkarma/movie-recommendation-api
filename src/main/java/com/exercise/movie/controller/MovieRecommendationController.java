package com.exercise.movie.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.exercise.movie.data.Genre;
import com.exercise.movie.data.MovieDto;
import com.exercise.movie.service.MovieRecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@Tag(name = "Movie recommendation  API")
@Slf4j
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
    public ResponseEntity<List<MovieDto>> recommendMovies(
            @PathVariable String genre) {
        try {
            validateRequest(genre);
            List<MovieDto> movies = movieRecommendationService.recommendMoviesByGenre(genre);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (IOException ioe) {
            logger.error("Exception while fetching the movie data", ioe);
            return ResponseEntity.status(500).build();
        }
    }

    private void validateRequest(String genre) {
        if (StringUtils.isEmpty(genre) || !genre.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException(String.format("No numeric or alpha numeric string "
                    + "are "
                    + "allowed like %s. please pass the valid "
                    + "genre string "
                    + "like drama"
                    + " or DRAMA", genre));
        }
        Optional<Genre> g =
                Stream.of(Genre.values()).filter(e -> e.toString().equalsIgnoreCase(genre))
                        .findFirst();
        if(!g.isPresent()){
            throw new IllegalArgumentException("Invalid genre string");
        }
    }
}
