package com.gsys.twtsrch.service;

import com.gsys.twtsrch.dto.TweetText;

import java.util.List;

public interface TweetService {
    List<TweetText> saveTweetTexts(List<TweetText> tweetTexts);
    List<TweetText> getTweetTexts();
}
