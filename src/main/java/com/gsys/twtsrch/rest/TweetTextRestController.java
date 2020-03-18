package com.gsys.twtsrch.rest;

import com.gsys.twtsrch.dto.TweetText;
import com.gsys.twtsrch.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tweetText")
public class TweetTextRestController {

    @Autowired
    private TweetService tweetService;

    @GetMapping(produces = "application/json; charset = UTF-8")
    public List<TweetText> getTweetTexts() {
        return tweetService.getTweetTexts();
    }
}
