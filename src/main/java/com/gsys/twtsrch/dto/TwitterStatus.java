package com.gsys.twtsrch.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * No name convention as this will be serialized directly from json. A response wrapper is a solution to justify name convention.
 * @see: https://developer.twitter.com/en/docs/tweets/search/api-reference/get-search-tweets
 */
@Data
public class TwitterStatus implements Serializable {
    private static final long serialVersionUID = 3800202252022863103L;
    private String text;
    private LinkedHashMap<String, Object> user;
}
