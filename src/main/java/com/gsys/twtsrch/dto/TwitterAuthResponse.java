package com.gsys.twtsrch.dto;

import lombok.Data;

import java.io.Serializable;

/*
No name convention as this will be serialized directly from json. A response wrapper is a solution to justify name convention.
 */
@Data
public class TwitterAuthResponse implements Serializable {
    private static final long serialVersionUID = -1129303468214929722L;
    private String token_type;
    private String access_token;
}
