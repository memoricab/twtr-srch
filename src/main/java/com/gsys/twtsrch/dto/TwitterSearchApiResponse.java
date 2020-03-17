package com.gsys.twtsrch.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @see: https://developer.twitter.com/en/docs/tweets/search/api-reference/get-search-tweets
 */
@Data
public class TwitterSearchApiResponse implements Serializable {
    private static final long serialVersionUID = -6908067604424606324L;
    private List<TwitterStatus> statuses;
    private LinkedHashMap<String, Object> search_metadata;

}
