package com.n157.onlineforumchat.comment;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/add-comment") /* POST /:post_id: Create a new comment for a post (for authorized users).*/
    public ResponseEntity<String> createComment(@RequestBody CommentDTO commentDTO) {
        commentService.save(commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully");
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDTO>> getAllCommentsForPost(@RequestParam Long postId) {
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForPost(postId));
    }


    /* PUT /:comment_id: Update a comment (for authorized users).*/

    /* DELETE /:comment_id: Delete a comment (for authorized users).*/
}
