package com.exercise.movie.controller;

import com.exercise.movie.dto.MovieDto;
import com.exercise.movie.service.MovieRecommendationService;
import com.exercise.movie.util.Genre;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockServletContext.class)
public class MovieRecommendationControllerTest {

    @InjectMocks
    private MovieRecommendationController recommendationController;

    @Mock
    private MovieRecommendationService movieRecommendationService;

    List<MovieDto> movieDtoList ;

    @Before
    public void setUp() throws IOException {
        movieDtoList = Arrays.asList(mockMovieDto());
        when(movieRecommendationService.recommendMoviesByGenre(any(String.class))).thenReturn(movieDtoList);
    }

    @Test
    public void testRecommendMovies(){
       ResponseEntity<List<MovieDto>> response =  recommendationController.recommendMovies("DRAMA");
       assertNotNull(response);
       MovieDto m = response.getBody().get(0);
       assertEquals(m.getName(), "Don");
       assertEquals(m.getPlatform(), "Netflix");
    }

    private MovieDto mockMovieDto(){
        MovieDto movieDto = new MovieDto();
        movieDto.setGenre(Genre.DRAMA);
        movieDto.setInfo("this movie is DRAMA movie");
        movieDto.setName("Don");
        movieDto.setPlatform("Netflix");
        movieDto.setRating(6.5);
        return movieDto;
    }

}
