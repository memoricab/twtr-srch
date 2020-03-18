package com.gsys.twtsrch.service.impl;

import com.gsys.twtsrch.dto.TweetText;
import com.gsys.twtsrch.entity.Tweet;
import com.gsys.twtsrch.repository.TweetRepository;
import com.gsys.twtsrch.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public List<TweetText> saveTweetTexts(List<TweetText> tweetTexts) {
        tweetRepository.saveAll(mapTweetTextsToTweets(tweetTexts));
        return tweetTexts;
    }

    @Override
    public List<TweetText> getTweetTexts() {
        return mapTweetsToTweetTexts(tweetRepository.findAll());
    }

    private List<TweetText> mapTweetsToTweetTexts(List<Tweet> tweets) {
        return tweets.stream().map(tweet -> {
            TweetText tweetText = new TweetText();
            tweetText.setText(tweet.getText());
            return tweetText;
        }).collect(Collectors.toList());
    }

    private List<Tweet> mapTweetTextsToTweets(List<TweetText> tweetTexts) {
        return tweetTexts.stream().map(tweetText -> {
            Tweet tweet = new Tweet();
            tweet.setText(tweetText.getText());
            return tweet;
        }).collect(Collectors.toList());
    }
}
