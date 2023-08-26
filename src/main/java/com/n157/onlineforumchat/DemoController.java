package com.n157.onlineforumchat;

import com.n157.onlineforumchat.auth.AuthenticationRequest;
import com.n157.onlineforumchat.auth.AuthenticationService;
import com.n157.onlineforumchat.post.Post;
import com.n157.onlineforumchat.post.PostRepository;
import com.n157.onlineforumchat.post.PostResponse;
import com.n157.onlineforumchat.post.PostService;
import com.n157.onlineforumchat.user.User;
import com.n157.onlineforumchat.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hello")
@RequiredArgsConstructor
public class DemoController {

    private final AuthenticationService service;
    private final UserRepository userRepository;
    private final PostService postService;

//    @GetMapping
//    public ResponseEntity<String> sayHello() {
//        return ResponseEntity.ok("Hello from secured endpoint");
//    }
//
//    @GetMapping("topics/{topic_id}")
//    public ResponseEntity<List<PostResponse>> getPostsByTopic(@PathVariable Long topic_id) {
//        return ResponseEntity.ok().body(postService.getPostsByTopic(topic_id));
//    }
//
//
//    @PostMapping
//    public ResponseEntity<String> login(@RequestBody AuthenticationRequest request) {
//        Optional<User> user = userRepository.findByEmail(request.getEmail());
//        return ResponseEntity.ok(request.getEmail());
//    }


}
