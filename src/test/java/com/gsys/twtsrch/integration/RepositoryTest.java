package com.gsys.twtsrch.integration;

import com.gsys.twtsrch.TwtSrchApplication;
import com.gsys.twtsrch.entity.Tweet;
import com.gsys.twtsrch.repository.Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TwtSrchApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RepositoryTest {

    @Autowired
    private Repository repository;

    @Test
    public void getTweets_shouldReturn_savedTweets(){
        Tweet mockTweet = new Tweet();
        mockTweet.setText("Text 1");

        Tweet mockTweet2 = new Tweet();
        mockTweet2.setText("Text 2");

        List<Tweet> mockTweets = new ArrayList<>();
        mockTweets.add(mockTweet);
        mockTweets.add(mockTweet2);

        Iterable<Tweet> savedTweets = repository.saveAll(mockTweets);
        Iterable<Tweet> fetchedTweets = repository.findAll();

        Iterator<Tweet> itrSavedTweets = savedTweets.iterator();
        Iterator<Tweet> itrFetchedTweets = fetchedTweets.iterator();

        while(itrSavedTweets.hasNext()){
            while(itrFetchedTweets.hasNext()){
                assertThat(itrSavedTweets.next().getText()).isSameAs(itrFetchedTweets.next().getText());
                assertThat(itrSavedTweets.next().getText()).isSameAs(itrFetchedTweets.next().getText());
            }
        }
    }
}
