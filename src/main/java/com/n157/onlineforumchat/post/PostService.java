package com.n157.onlineforumchat.post;

import com.n157.onlineforumchat.post.exception.PostNotFoundException;
import com.n157.onlineforumchat.post.exception.TopicNotFoundException;
import com.n157.onlineforumchat.topic.Topic;
import com.n157.onlineforumchat.topic.TopicRepository;
import com.n157.onlineforumchat.user.User;
import com.n157.onlineforumchat.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    @Transactional(readOnly = true)
    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
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
            postResponse.setUserName(post.getUser().getRealname());
            postResponse.setTopicName(post.getTopic().getName());

            postResponses.add(postResponse);
        }
        return postResponses;
    }

    public List<Post> searchPosts(String keyword) {
        return postRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getRelatedPosts(Long postId) {
        Post currentPost = postRepository.findById(postId).orElse(null);

        if (currentPost == null) {
            return Collections.emptyList();
        }

        List<Post> relatedPosts = new ArrayList<>();


        List<Post> allPosts = postRepository.findAll();

        for (Post post : allPosts) {
            if (!post.getId().equals(currentPost.getId())) {
                double similarity = calculateCosineSimilarity(currentPost.getTitle(), post.getTitle ());
                if (similarity >= 0.4) {
                    relatedPosts.add(post);
                }
            }
        }
        return relatedPosts;
    }

    private double calculateCosineSimilarity(String text1, String text2) {
        String[] terms1 = text1.split("\\s+");
        String[] terms2 = text2.split("\\s+");

        Set<String> allTerms = new HashSet<>();
        Collections.addAll(allTerms, terms1);
        Collections.addAll(allTerms, terms2);

        RealVector vector1 = createVectorWithAllTerms(allTerms, terms1);
        RealVector vector2 = createVectorWithAllTerms(allTerms, terms2);

        return vector1.cosine(vector2);
    }

    private RealVector createVectorWithAllTerms(Set<String> allTerms, String[] terms) {
        RealVector vector = new ArrayRealVector(allTerms.size());

        int i = 0;
        for (String term : allTerms) {
            if (Arrays.asList(terms).contains(term)) {
                vector.setEntry(i, 1);
            } else {
                vector.setEntry(i, 0);
            }
            i++;
        }
        return vector;
    }
}
