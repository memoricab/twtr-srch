package com.gsys.twtsrch.integration;

import com.gsys.twtsrch.TwtSrchApplication;
import com.gsys.twtsrch.dto.TweetText;
import com.gsys.twtsrch.service.TweetService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TwtSrchApplication.class)
@AutoConfigureMockMvc
public class TweetTextRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TweetService tweetService;

    @Test
    public void whenGetTweetTexts_shouldReturnTweetTexts_HttpStatusIsOK() throws Exception {
        TweetText mockTweetText1 = new TweetText();
        mockTweetText1.setText("Text 1");

        TweetText mockTweetText2 = new TweetText();
        mockTweetText2.setText("Text 2");

        List<TweetText> tweetTexts = new ArrayList<>();
        tweetTexts.add(mockTweetText1);
        tweetTexts.add(mockTweetText2);

        when(tweetService.getTweetTexts()).thenReturn(tweetTexts);

        mockMvc.perform(get("/api/tweetText").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].text").value(mockTweetText1.getText()))
                .andExpect(jsonPath("$[1].text").value(mockTweetText2.getText()));
    }
}
