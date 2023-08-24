package com.n157.onlineforumchat.post;

import com.n157.onlineforumchat.post.exception.PostNotFoundException;
import com.n157.onlineforumchat.post.exception.TopicNotFoundException;
import com.n157.onlineforumchat.post.exception.UserNotFoundException;
import com.n157.onlineforumchat.topic.Topic;
import com.n157.onlineforumchat.topic.TopicRepository;
import com.n157.onlineforumchat.topic.exception.SubredditNotFoundException;
import com.n157.onlineforumchat.user.User;
import com.n157.onlineforumchat.user.UserRepository;
import com.n157.onlineforumchat.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    public Post createPost(Post post) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException("User not found: " + userId));
//
//        Topic topic = topicRepository.findById(topicId)
//                .orElseThrow(() -> new TopicNotFoundException("Topic not found: " + topicId));
//
//        post.setUser(user);
//        post.setTopic(topic);
//        post.setCreatedAt(LocalDateTime.now());

        return postRepository.save(post);
    }

    public List<Post> getPostsByTopicId(Long topicId) {
        Topic topic = topicRepository.findById(topicId).orElse(null);

        if (topic != null) {
            // Trigger lazy loading of posts within the same transaction
            List<Post> posts = topic.getPosts(); // Lazy loading happens here
            return posts;
        } else {
            return new ArrayList<>(); // or throw an exception, handle as needed
        }
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByTopic(Long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException(topicId.toString()));
        List<Post> posts = postRepository.findAllByTopic(topic);

        return getPostResponses(posts);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        List<Post> posts = postRepository.findByUser(user);

        return getPostResponses(posts);
    }

    private List<PostResponse> getPostResponses(List<Post> posts) {
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post post : posts) {
            PostResponse postResponse = new PostResponse();
            postResponse.setId(post.getId());
            postResponse.setTitle(post.getTitle());
            postResponse.setContent(post.getContent());
            postResponse.setUserName(post.getUser().getUsername());
            postResponse.setTopicName(post.getTopic().getName());

            postResponses.add(postResponse);
        }
        return postResponses;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

//    public List<Post> getPostsByTopic(Long topicId) {
//        return postRepository.findByTopicId(topicId);
//    }

//    public Post updatePost(Long id, Post updatedPost) {
//        Post existingPost = getPostById(id);
//        existingPost.setTitle(updatedPost.getTitle());
//        existingPost.setContent(updatedPost.getContent());
//        return postRepository.save(existingPost);
//    }
//
//    public void deletePost(Long id) {
//        Post post = getPostById(id);
//        postRepository.delete(post);
//    }
}
