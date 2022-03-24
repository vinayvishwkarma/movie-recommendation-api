package com.exercise.movie.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.exercise.movie.dto.MovieDto;
import com.exercise.movie.util.Genre;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockServletContext.class)
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
