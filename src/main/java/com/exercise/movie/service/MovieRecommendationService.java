package com.exercise.movie.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.exercise.movie.dto.MovieDto;
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
     * this method returns the movie based on the genre
     *
     * @param genre - movie genre
     */
    //TODO : need to add persistent layer and we can fetch the data from db
    public List<MovieDto> recommendMoviesByGenre(final String genre) throws IOException {
        List<MovieDto> movieDtos = ResourceReaderUtil.jsonToMovieDto(resourceFile);
        List<MovieDto> recommendedMovieByGenre = movieDtos.stream()
                .filter(m -> m.getGenre().toString().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(recommendedMovieByGenre)) {
            throw new RecommendationNotFoundException(
                    String.format("movie not found for genre : %s", genre));
        }
        return recommendedMovieByGenre;
    }
}
