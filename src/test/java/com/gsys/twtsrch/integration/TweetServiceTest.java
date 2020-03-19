package com.gsys.twtsrch.integration;

import com.gsys.twtsrch.TwtSrchApplication;
import com.gsys.twtsrch.dto.TweetText;
import com.gsys.twtsrch.service.TweetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = TwtSrchApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TweetServiceTest {

    @Autowired
    private TweetService tweetService;

    @Test
    public void getTweetTexts_shouldReturn_savedTweetTexts() {
        TweetText mockTweetText1 = new TweetText();
        mockTweetText1.setText("Text 1");

        TweetText mockTweetText2 = new TweetText();
        mockTweetText2.setText("Text 2");

        List<TweetText> tweetTexts = new ArrayList<>();
        tweetTexts.add(mockTweetText1);
        tweetTexts.add(mockTweetText2);

        List<TweetText> savedTweetTexts = tweetService.saveTweetTexts(tweetTexts);
        List<TweetText> fetchedTweetTexts = tweetService.getTweetTexts();

        assertThat(fetchedTweetTexts.get(0).getText()).isSameAs(savedTweetTexts.get(0).getText());
        assertThat(fetchedTweetTexts.get(1).getText()).isSameAs(savedTweetTexts.get(1).getText());
    }


}
