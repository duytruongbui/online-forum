package com.n157.onlineforumchat.post;

import com.n157.onlineforumchat.topic.Topic;
import com.n157.onlineforumchat.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @Column(name = "post_content")
    private String content;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

}