package com.n157.onlineforumchat.post;

import com.n157.onlineforumchat.topic.TopicService;
import com.n157.onlineforumchat.user.User;
import com.n157.onlineforumchat.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserRepository userRepository;
    private final TopicService topicService;

    @GetMapping("topic/{topic_id}") /* GET /: Get a list of posts in topic.*/
    public ResponseEntity<List<PostResponse>> getPostsByTopic(@PathVariable Long topic_id) {
        return ResponseEntity.ok().body(postService.getPostsByTopic(topic_id));
    }

    @GetMapping("/posts") /* GET /: Get a list of posts by username(email).*/
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@RequestParam String username) {
        return status(HttpStatus.OK).body(postService.getPostsByUsername(username));
    }

    @PostMapping("/add-post") /* POST /: Create a new post (for authorized users).*/
    public ResponseEntity<String> createPost(@RequestBody PostResponse postResponse) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }

        String userMail = authentication.getName();
        Optional<User> userOptional = userRepository.findByEmail(userMail);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userOptional.get();

        Post post = Post.builder()
                .title(postResponse.getTitle())
                .content(postResponse.getContent())
                .createdAt(LocalDateTime.now())
                .user(user)
                .topic(topicService.getTopicByName(postResponse.getTopicName()))
                .build();
        postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
    }

//    @PutMapping("/{post_id}") /* PUT /:post_id: Update a post (for authorized users). */
//    public ResponseEntity<String> updatePost(@PathVariable("post_id") Long postId,
//                                             @RequestBody PostDTO postDTO) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
//        }
//
//        String userName = authentication.getName();
//        Optional<User> userOptional = userRepository.findByUsername(userName);
//
//        if (userOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//
//        User user = userOptional.get();
//
//        Optional<Post> postOptional = postRepository.findById(postId);
//
//        if (postOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
//        }
//
//        Post existingPost = postOptional.get();
//
//        if (!existingPost.getUser().equals(user)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to update this post");
//        }
//
//        // Perform further validation on postDTO, e.g., checking for valid content, etc.
//        // ...
//
//        // Update the post
//        existingPost.setContent(postDTO.getContent());
//        postRepository.save(existingPost);
//
//        return ResponseEntity.ok("Post updated successfully");
//    }


    /* DELETE /:post_id: Delete a post (for authorized users).*/
}
