package com.gsys.twtsrch.repository.impl;

import com.gsys.twtsrch.entity.Tweet;
import com.gsys.twtsrch.repository.TweetRepository;
import com.gsys.twtsrch.repository.dao.TweetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class TweetRepositoryImpl implements TweetRepository {

    @Autowired
    private TweetDao tweetDao;

    @Value("${tweets.persistence.batch-size}")
    private int batchSize;

    @Override
    public List<Tweet> saveAll(List<Tweet> tweets) {
        if (batchSize != 0) {
            return tweetDao.batchInsert(tweets, batchSize);
        }
        return tweetDao.batchInsert(tweets);
    }

    @Override
    public List<Tweet> findAll() {
        return tweetDao.findAll();
    }
}
