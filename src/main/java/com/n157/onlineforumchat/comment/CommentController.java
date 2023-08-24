package com.n157.onlineforumchat.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping /* GET /:post_id: Retrieve all comments for a specific post.*/
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @PostMapping /* POST /:post_id: Create a new comment for a post (for authorized users).*/
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(comment);
        return ResponseEntity.created(URI.create("/api/comments/" + createdComment.getId()))
                .body(createdComment);
    }

    /* PUT /:comment_id: Update a comment (for authorized users).*/

    /* DELETE /:comment_id: Delete a comment (for authorized users).*/
}
