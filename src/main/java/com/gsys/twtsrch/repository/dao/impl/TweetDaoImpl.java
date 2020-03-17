package com.gsys.twtsrch.repository.dao.impl;

import com.gsys.twtsrch.entity.Tweet;
import com.gsys.twtsrch.repository.dao.TweetDao;
import com.gsys.twtsrch.util.InfoMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
class TweetDaoImpl implements TweetDao {
    private Logger logger = LoggerFactory.getLogger(TweetDaoImpl.class);
    private static final String INSERT_QUERY = "INSERT_INTO TWEETS (ID, TEXT) VALUES(?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tweet> batchInsert(List<Tweet> tweets) {
        int[] result = jdbcTemplate.batchUpdate(INSERT_QUERY, createBatchPreparedStatementSetter(tweets));
        logger.info(InfoMessages.TOTAL_UPDATED_COUNT_FOR_BATCH_UPDATE, calculateTotalUpdatedCount(result));
        return tweets;
    }

    public List<Tweet> batchInsert(List<Tweet> tweets, int batchSize) {
        int[][] result = jdbcTemplate.batchUpdate(INSERT_QUERY, tweets, batchSize, createParameterizedPreparedStatementSetter());
        logger.info(InfoMessages.TOTAL_UPDATED_COUNT_FOR_BATCH_UPDATE_FOR_EACH_BATCH, calculateTotalUpdatedCountForEachBatch(result));
        return tweets;
    }

    private BatchPreparedStatementSetter createBatchPreparedStatementSetter(List<Tweet> tweets) {
        return new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Tweet tweet = tweets.get(i);
                preparedStatement.setString(2, tweet.getText());
            }

            @Override
            public int getBatchSize() {
                return tweets.size();
            }
        };
    }

    private ParameterizedPreparedStatementSetter<Tweet> createParameterizedPreparedStatementSetter() {
        return (preparedStatement, tweet) -> preparedStatement.setString(2, tweet.getText());
    }

    private int calculateTotalUpdatedCount(int[] resultOfBatchUpdate) {
        int totalUpdatedCount = 0;
        for (int result : resultOfBatchUpdate) {
            totalUpdatedCount += result;
        }
        return totalUpdatedCount;
    }

    private int calculateTotalUpdatedCountForEachBatch(int[][] resultOfBatchUpdate) {
        int totalUpdatedCount = 0;
        for (int[] batch : resultOfBatchUpdate) {
            for (int result : batch) {
                totalUpdatedCount += result;
            }
        }
        return totalUpdatedCount;
    }
}
