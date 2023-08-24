package com.n157.onlineforumchat.topic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @GetMapping  /* GET /: Get a list of all topics. */
    public ResponseEntity<List<TopicDTO>> getAllTopics() {
        List<Topic> topics = topicService.getAllTopics();
        List<TopicDTO> topicDTOs = new ArrayList<>();
        for (Topic topic : topics) {
            TopicDTO topicDTO = new TopicDTO();
            topicDTO.setId(topic.getId());
            topicDTO.setName(topic.getName());
            topicDTOs.add(topicDTO);
        }

        return ResponseEntity.ok(topicDTOs);
    }

//    @GetMapping("/{topic_id}") /* GET /:topic_id: Retrieve details about a specific subreddit.*/
//    public ResponseEntity<Topic> getTopicById(@PathVariable Long topic_id) {
//        Topic topic = topicService.getTopicById(topic_id);
//        return ResponseEntity.ok(topic);
//    }
//
//    @GetMapping("/{name}")
//    public ResponseEntity<TopicDTO> getSubredditByName(@PathVariable String name) {
//        Topic subreddit = topicService.getTopicByName(name);
//
//        if (subreddit != null) {
//            TopicDTO subredditDTO = new TopicDTO();
//            subredditDTO.setName(subreddit.getName());
////            subredditDTO.setDescription(subreddit.getDescription());
//            return ResponseEntity.ok(subredditDTO);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping  /* POST /: Create a new topic (for authorized users). */
//    public ResponseEntity<Topic> createTopic(@RequestBody TopicDTO topicDTO) {
//        Topic topic = new Topic();
//        topic.setName(topicDTO.getName());
////        topic.setDescription(topicDTO.getDescription());
//
//        Topic createdTopic = topicService.createTopic(topic);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdTopic);
//    }
//    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
//        Topic createdTopic = topicService.createTopic(topic);
//        return ResponseEntity.created(URI.create("/api/topics/" + createdTopic.getId()))
//                .body(createdTopic);
//    }

}
