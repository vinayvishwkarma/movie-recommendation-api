package com.exercise.movie.IT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import com.exercise.movie.Application;
import com.exercise.movie.dto.MovieDto;
import com.exercise.movie.util.Genre;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * IT to test actual scenario , we can just call the end point like in real world and check the
 * response
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
public class RecommendationIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private Integer randomServerPort;

    private MovieDto expectedMovieDto;

    @Before
    public void init() {
        expectedMovieDto = mockMovieDto();
    }

    @Test
    public void testMovieRecommendationUrl() {
        String url = "http://localhost:" + randomServerPort + "/v1/recommendations/movies/DRAMA";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity request = new HttpEntity<>(headers);
        ResponseEntity<MovieDto[]> response =
                this.restTemplate.exchange(url, HttpMethod.GET, request, MovieDto[].class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        List<MovieDto> movieDtoList = Arrays.asList(response.getBody());
        MovieDto movieDto = movieDtoList.get(0);
        assertEquals(expectedMovieDto.getGenre(), movieDto.getGenre());
        assertEquals(expectedMovieDto.getInfo(), movieDto.getInfo());
        assertEquals(expectedMovieDto.getName(), movieDto.getName());
        assertEquals(expectedMovieDto.getPlatform(), movieDto.getPlatform());
    }

    @Test
    public void testRecommendationNotFoundException() {
        String url = "http://localhost:" + randomServerPort + "/v1/recommendations/movies/123";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity request = new HttpEntity<>(headers);
        ResponseEntity<String> response = this.restTemplate.
                exchange(url, HttpMethod.GET, request, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertTrue(response.getBody().contains("movie not found for genre"));
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
