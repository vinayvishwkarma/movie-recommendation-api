package com.exercise.movie.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.exercise.movie.data.MovieDto;
import com.exercise.movie.exception.RecommendationNotFoundException;
import com.exercise.movie.util.ResourceReaderUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class MovieRecommendationService {

    private static final String MOVIE_JSON_FILEPATH = "classpath:data/movies.json";

    @Value(MOVIE_JSON_FILEPATH)
    private Resource resourceFile;

    /**
     * returns the movie based on the genre
     */
    public List<MovieDto> recommendMoviesByGenre(final String genre) throws IOException {
        List<MovieDto> movies = ResourceReaderUtil.jsonToMovieDto(resourceFile);
        List<MovieDto> recommendedMoviesByGenre = movies.stream()
                .filter(m -> m.getGenre().toString().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(recommendedMoviesByGenre)) {
            throw new RecommendationNotFoundException(
                    String.format("movie not found for genre : %s", genre));
        }
        return recommendedMoviesByGenre;
    }
}
