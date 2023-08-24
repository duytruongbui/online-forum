package com.n157.onlineforumchat.demo;

//import io.swagger.v3.oas.annotations.Hidden;
import com.n157.onlineforumchat.topic.Topic;
import com.n157.onlineforumchat.topic.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class HomeController {

  private final TopicService topicService;

  @GetMapping("")
  public List<String> getTopics() {
    List<Topic> topics = topicService.getAllTopics();
    return topics.stream().map(Topic::getName).collect(Collectors.toList());
  }

}
