package com.gsys.twtsrch.repository.dao;

import com.gsys.twtsrch.entity.Tweet;

import java.util.List;

public interface TweetDao {
    List<Tweet> batchInsert(List<Tweet> tweets);
    List<Tweet> batchInsert(List<Tweet> tweets, int batchSize);
    List<Tweet> findAll();
}
