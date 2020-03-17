package com.gsys.twtsrch.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Tweet implements Serializable {
    private static final long serialVersionUID = 2389736318006981413L;
    private Long id;
    private String text;
}
