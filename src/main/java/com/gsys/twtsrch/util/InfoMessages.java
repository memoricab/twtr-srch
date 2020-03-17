package com.gsys.twtsrch.util;

public class InfoMessages {

    // Twitter DAO
    public static final String TOTAL_UPDATED_COUNT_FOR_BATCH_UPDATE = "Executed batch and total count of effected rows: {}" ;
    public static final String TOTAL_UPDATED_COUNT_FOR_BATCH_UPDATE_FOR_EACH_BATCH = "Executed all batches and total count of effected rows: {}" ;

    // Rest Template
    public static final String TWITTER_SERVER_ERROR = "Twitter API returned server error";
    public static final String CLIENT_BAD_REQUEST_TWITTER_ERROR = "Twitter API returned bad request error. Request is not valid to twitter servers. Response code: {}" ;
    public static final String TWITTER_CLIENT_APP_IS_UNAUTHORIZED = "The reason response was bad request is twitter client app is not authenticated";
    public static final String CREATING_REST_TEMPLATE_INSTANCE = "Creating rest template instance.";
    public static final String CREATING_REST_TEMPLATE_TO_REQUEST_TWTR_API_ACCESS_TOKEN = "Creating rest template for to request access token." ;
    public static final String TWITTER_API_ACCESS_TOKEN_RECEIVED_SUCCESSFULLY = "Received twitter api access token successfully. Token: {}";
    public static final String EXCHANGE_TWITTER_API = "Exchanging request for uri: {}";
    public static final String NO_RECENT_TWEETS_FROM_TWITTER_API = "No recent tweets different than already inserted.";
}
