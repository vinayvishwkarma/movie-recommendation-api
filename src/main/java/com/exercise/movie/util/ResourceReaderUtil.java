package com.exercise.movie.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import com.exercise.movie.dto.MovieDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;


@Slf4j
public class ResourceReaderUtil {

    private static Logger logger = LoggerFactory.getLogger(ResourceReaderUtil.class);

    /**
     * this util method to process the data from file and provide the MovieDto
     *
     * @param resource - file resource to read
     * @return list<MovieDto> -  list of movies prasent in file
     */
    public static List<MovieDto> jsonToMovieDto(Resource resource) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<MovieDto> movieDtoList;
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            movieDtoList = Arrays.asList(objectMapper.readValue(reader, MovieDto[].class));
            return movieDtoList;
        } catch (IOException ioe) {
            logger.error("error while fetching data from json file : ", ioe);
            throw new IOException("error while fetching data from json file");
        }
    }
}
