package com.gsys.twtsrch.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * No name convention as this will be serialized directly from json. A response wrapper is a solution to justify name convention.
 * @see: https://developer.twitter.com/en/docs/tweets/search/api-reference/get-search-tweets
 */
@Data
public class TwitterAuthResponse implements Serializable {
    private static final long serialVersionUID = -1129303468214929722L;
    private String token_type;
    private String access_token;
}
