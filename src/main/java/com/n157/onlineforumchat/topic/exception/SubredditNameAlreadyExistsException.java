package com.n157.onlineforumchat.topic.exception;

public class SubredditNameAlreadyExistsException extends RuntimeException {
    public SubredditNameAlreadyExistsException(String message) {
        super(message);
    }
}
