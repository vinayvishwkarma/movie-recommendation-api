package com.exercise.movie.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import com.exercise.movie.data.MovieDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ResourceReaderUtilTest {

    private static final String MOVIE_JSON_FILEPATH = "classpath:data/movies.json";

    @Value(MOVIE_JSON_FILEPATH)
    private Resource resourceFile;

    @Test
    public void testJsonToMovieDto() throws IOException {
        List<MovieDto> movieDtoList = ResourceReaderUtil.jsonToMovieDto(resourceFile);
        assertNotNull(movieDtoList);
        MovieDto expectedMovieDto = movieDtoList.get(0);
        assertEquals(expectedMovieDto.getName(), "Don");
        assertEquals(expectedMovieDto.getGenre().toString(), "DRAMA");
    }

}
