package com.n157.onlineforumchat.post;

import com.n157.onlineforumchat.topic.Topic;
import com.n157.onlineforumchat.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTopic(Topic topic);

    List<Post> findByUser(User user);

}