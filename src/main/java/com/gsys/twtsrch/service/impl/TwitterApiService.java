package com.gsys.twtsrch.service.impl;

import com.gsys.twtsrch.config.TwitterApiSecurityConfig;
import com.gsys.twtsrch.dto.TweetText;
import com.gsys.twtsrch.dto.TwitterSearchApiResponse;
import com.gsys.twtsrch.service.TweetService;
import com.gsys.twtsrch.util.InfoMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @see: https://developer.twitter.com/en/docs/tweets/search/api-reference/get-search-tweets
 */
@Service
class TwitterApiService {
    private static String sinceId;
    private Logger logger = LoggerFactory.getLogger(TwitterApiService.class);


    @Value("${twitter.api.search-tweets-uri}")
    private String twtrSearchTweetsURI;

    @Value("${twitter.api.search-query-param}")
    private String twtrSearchQueryParam;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TweetService tweetService;

    @Scheduled(fixedDelay = 60000)
    private void fetchTweetsFromTwitter() {
        String searchUri = twtrSearchTweetsURI + "&q=" + twtrSearchQueryParam + "&since_id=" + getSinceId();
        TwitterSearchApiResponse twitterSearchApiResponse = exchange(searchUri, new ParameterizedTypeReference<TwitterSearchApiResponse>() {
        });
        setSinceId(twitterSearchApiResponse.getSearch_metadata().get("max_id_str").toString());
        processTwitterSearchApiResponse(twitterSearchApiResponse);
    }

    private void processTwitterSearchApiResponse(TwitterSearchApiResponse twitterSearchApiResponse) {
        List<TweetText> tweetTexts = twitterSearchApiResponse.getStatuses().stream().map(status -> {
            TweetText tweetText = new TweetText();
            tweetText.setText(status.getUser().get("screen_name") + " says that,  " + status.getText());
            return tweetText;
        }).collect(Collectors.toList());
        saveTweets(tweetTexts);
    }

    private void saveTweets(List<TweetText> tweetTexts) {
        if (!tweetTexts.isEmpty()) {
            tweetService.saveTweetTexts(tweetTexts);
        } else {
            logger.info(InfoMessages.NO_RECENT_TWEETS_FROM_TWITTER_API);
        }
    }

    private void setSinceId(String sinceId) {
        this.sinceId = sinceId;
    }

    private String getSinceId() {
        return sinceId;
    }

    private <T> T exchange(String uri, ParameterizedTypeReference<T> responseType) {
        logger.info(InfoMessages.EXCHANGE_TWITTER_API, uri);
        return restTemplate.exchange(
                uri,
                HttpMethod.GET,
                new HttpEntity<Object>(generateHeadersWithAccessToken()),
                responseType).getBody();
    }

    private MultiValueMap<String, String> generateHeadersWithAccessToken() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", "Bearer " + TwitterApiSecurityConfig.getTwitterApiAccessToken());
        return headers;
    }
}
