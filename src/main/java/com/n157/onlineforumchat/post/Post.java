package com.n157.onlineforumchat.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.n157.onlineforumchat.topic.Topic;
import com.n157.onlineforumchat.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;
    @Column(name = "post_title")
    private String title;
    @Column(name = "post_content", length = 10000)

    private String content;
    private String createdAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

}
