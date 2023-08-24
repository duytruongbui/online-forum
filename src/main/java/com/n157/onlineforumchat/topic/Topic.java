package com.n157.onlineforumchat.topic;

import com.n157.onlineforumchat.post.Post;
import com.n157.onlineforumchat.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long id;
    @Column(name = "topic_name")
    private String name;

    @OneToMany
    private List<Post> posts;
    public Topic(String name) {
        this.name = name;
    }

}


