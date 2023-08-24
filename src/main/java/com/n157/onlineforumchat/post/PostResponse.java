package com.n157.onlineforumchat.post;

import lombok.Data;

@Data
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String userName;
    private String topicName;
}
