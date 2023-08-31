package com.n157.onlineforumchat.comment;

import com.n157.onlineforumchat.auth.AuthenticationService;
import com.n157.onlineforumchat.post.Post;
import com.n157.onlineforumchat.post.PostRepository;
import com.n157.onlineforumchat.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final AuthenticationService authenticationService;
    private final CommentRepository commentRepository;

    public void save(CommentDTO commentDTO) {
        LocalDateTime dateTime = LocalDateTime.now();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedDateTime = dateTime.format(formatter);
        Post post = postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentDTO.getPostId().toString()));

        Comment comment = Comment.builder()
                .text(commentDTO.getText())
                .createdAt(formattedDateTime)
                .post(post)
                .user(authenticationService.getCurrentUser())
                .build();

        commentRepository.save(comment);
    }

    public List<CommentDTO> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment comment : commentRepository.findByPost(post)) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setPostId(comment.getPost().getId());
            commentDTO.setText(comment.getText());
            commentDTO.setUserName(comment.getUser().getRealname());

            commentDTOs.add(commentDTO);
        }
        return commentDTOs;
    }

}
