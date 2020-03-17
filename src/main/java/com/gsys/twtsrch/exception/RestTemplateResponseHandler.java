package com.gsys.twtsrch.exception;

import com.gsys.twtsrch.util.InfoMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseHandler implements ResponseErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestTemplateResponseHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return (clientHttpResponse.getStatusCode().series() == CLIENT_ERROR || clientHttpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            logger.error(InfoMessages.TWITTER_SERVER_ERROR);
        } else if (clientHttpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            logger.error(InfoMessages.CLIENT_BAD_REQUEST_TWITTER_ERROR, clientHttpResponse.getStatusCode());
            if (clientHttpResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                logger.error(InfoMessages.TWITTER_CLIENT_APP_IS_UNAUTHORIZED);
            }
        }
    }
}

