package com.n157.onlineforumchat.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {
    private final PostService postService;
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> searchQuestions(@RequestParam String keyword) {
        List<Post> results = postService.searchPosts(keyword);
        return ResponseEntity.ok(results);
    }

}
