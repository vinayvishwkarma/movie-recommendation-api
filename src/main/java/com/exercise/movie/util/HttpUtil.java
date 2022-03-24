package com.exercise.movie.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

/**
 * this util class can be used to fetch the third party data for movie recommendation
 */
@Component
public class HttpUtil {

    /**
     * method can be used to call any third party api like Nelfix ,IMDB to fetch the latest record
     * for the movie
     *
     * @param url
     */

    public HttpResponse getRequest(String url) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        return client.execute(get);
    }
}
