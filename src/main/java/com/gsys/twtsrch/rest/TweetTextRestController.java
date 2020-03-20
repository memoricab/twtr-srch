package com.gsys.twtsrch.rest;

import com.gsys.twtsrch.dto.TweetText;
import com.gsys.twtsrch.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/tweets")
public class TweetTextRestController {

    @Autowired
    private TweetService tweetService;

    @GetMapping(produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public List<TweetText> getTweetTexts() {
        return tweetService.getTweetTexts();
    }
}
