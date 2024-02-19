package com.test.companyBatch.job;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientUtil {

    public static WebClient getBaseUrl(final String uri) {
        return WebClient.builder()
                .baseUrl(uri)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, String.valueOf(MediaType.APPLICATION_XML))
                .build()
                .mutate()
                .build();
    }

}
