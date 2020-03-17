package com.gsys.twtsrch.config;

import com.gsys.twtsrch.exception.RestTemplateResponseHandler;
import com.gsys.twtsrch.util.InfoMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    private Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);

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
}
