package com.exercise.movie.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.exercise.movie.data.Genre;
import com.exercise.movie.data.MovieDto;
import com.exercise.movie.exception.RecommendationNotFoundException;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MovieRecommendationServiceTest {

    private static final String MOVIE_JSON_FILEPATH = "classpath:data/movies.json";

    private MovieRecommendationService movieRecommendationService;

    @Value(MOVIE_JSON_FILEPATH)
    private Resource resourceFile;

    List<MovieDto> movieDtoList;

    @Before
    public void setUp() throws IllegalAccessException {
        movieRecommendationService = new MovieRecommendationService();
        FieldUtils.writeField(movieRecommendationService, "resourceFile", resourceFile, true);
        movieDtoList = Arrays.asList(mockMovieDto());
    }

    @Test
    public void testRecommendMoviesByGenre() throws IOException {
        List<MovieDto> dtos = movieRecommendationService.recommendMoviesByGenre("DRAMA");
        assertNotNull(dtos);
        assertEquals(1, dtos.size());
        MovieDto m = dtos.get(0);
        assertEquals("Don", m.getName());
        assertEquals("DRAMA", m.getGenre().toString());
    }

    @Test(expected = RecommendationNotFoundException.class)
    public void testRecommendNotFoundException() throws IOException {
        movieRecommendationService.recommendMoviesByGenre("ERT");
    }

    @Test(expected = RecommendationNotFoundException.class)
    public void testRecommendNotFoundExceptionForInvalidGenreString() throws IOException {
        movieRecommendationService.recommendMoviesByGenre("123");
    }

    private MovieDto mockMovieDto() {
        MovieDto movieDto = new MovieDto();
        movieDto.setGenre(Genre.DRAMA);
        movieDto.setInfo("this movie is DRAMA movie");
        movieDto.setName("Don");
        movieDto.setPlatform("Netflix");
        movieDto.setRating(6.5);
        return movieDto;
    }

}
