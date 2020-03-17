package com.gsys.twtsrch.config;

import com.gsys.twtsrch.dto.TwitterAuthResponse;
import com.gsys.twtsrch.exception.RestTemplateResponseHandler;
import com.gsys.twtsrch.util.InfoMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Configuration
public class RestTemplateConfig {
    private static final String TWITTER_OAUTH2_TOKEN_API = "https://api.twitter.com/oauth2/token";
    private Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);
    private static String ACCESS_TOKEN;

    @Value("${twitter.app.client-key}")
    private String twtrClientKey;

    @Value("${twitter.app.client-secret}")
    private String twtrClientSecret;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private RestTemplateResponseHandler restTemplateResponseHandler;

    @Bean
    public RestTemplate restTemplate() {
        logger.info(InfoMessages.CREATING_REST_TEMPLATE_INSTANCE);
        return restTemplateBuilder.errorHandler(restTemplateResponseHandler)
                .build();
    }

    public String getTwitterApiAccessToken() {
        return ACCESS_TOKEN;
    }

    private static void setTwitterApiAccessToken(String accessToken) {
        ACCESS_TOKEN = accessToken;
    }

    @Bean
    protected void requestAccessToken() {
        logger.info(InfoMessages.CREATING_REST_TEMPLATE_TO_REQUEST_TWTR_API_ACCESS_TOKEN);
        RestTemplate restTemplate = restTemplateBuilder.errorHandler(restTemplateResponseHandler).build();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(createMultiValueMapForTokenRequest(), createHeadersForTokenRequest());
        ResponseEntity<TwitterAuthResponse> response =
                restTemplate.exchange(TWITTER_OAUTH2_TOKEN_API,
                        HttpMethod.POST,
                        request,
                        TwitterAuthResponse.class);
        logger.info(InfoMessages.TWITTER_API_ACCESS_TOKEN_RECEIVED_SUCCESSFULLY, response.getBody().getAccess_token());
        setTwitterApiAccessToken(response.getBody().getAccess_token());
    }

    private MultiValueMap<String, String> createMultiValueMapForTokenRequest() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");
        return map;
    }

    private HttpHeaders createHeadersForTokenRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + encodeClientCredentials());
        return headers;
    }

    private String encodeClientCredentials() {
        String credentials = twtrClientKey + ":" + twtrClientSecret;
        return Base64.getEncoder().encodeToString(credentials.getBytes());
    }


}
