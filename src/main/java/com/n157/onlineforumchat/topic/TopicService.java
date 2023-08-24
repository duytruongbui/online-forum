package com.n157.onlineforumchat.topic;

import com.n157.onlineforumchat.topic.exception.SubredditNameAlreadyExistsException;
import com.n157.onlineforumchat.topic.exception.SubredditNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;

    public void createTopics(List<Topic> topics) {
        topicRepository.saveAll(topics);
    }

    public Topic createTopic(Topic topic) {
        Optional<Topic> existingSubreddit = topicRepository.findByName(topic.getName());
        if (existingSubreddit.isPresent()) {
            throw new SubredditNameAlreadyExistsException("Subreddit name already exists: " + topic.getName());
        }
        return topicRepository.save(topic);
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic getTopicById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new SubredditNotFoundException("Topic not found with id: " + id));
    }

    public Topic getTopicByName(String name) {
        return topicRepository.findByName(name)
                .orElseThrow(() -> new SubredditNotFoundException("Topic not found: " + name));
    }

    public Topic updateTopic(Long id, Topic updatedTopic) {
        Topic topic = getTopicById(id);
        topic.setName(updatedTopic.getName());
//        topic.setDescription(updatedTopic.getDescription());

        return topicRepository.save(topic);
    }

    public void deleteTopic(Long id) {
        Topic topic = getTopicById(id);
        topicRepository.delete(topic);
    }

}
