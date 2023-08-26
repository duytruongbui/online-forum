package com.n157.onlineforumchat;

import com.n157.onlineforumchat.auth.AuthenticationService;
import com.n157.onlineforumchat.auth.RegisterRequest;
import com.n157.onlineforumchat.post.Post;
import com.n157.onlineforumchat.post.PostService;
import com.n157.onlineforumchat.topic.Topic;
import com.n157.onlineforumchat.topic.TopicService;
import com.n157.onlineforumchat.user.UserRepository;
import com.n157.onlineforumchat.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class OnlineForumChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineForumChatApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedTopics(TopicService topicService,
                                        AuthenticationService service,
                                        PostService postService,
                                        UserService userService) {
        return args -> {
            List<Topic> topics = new ArrayList<>();
            topics.add(new Topic("Thời sự"));
            topics.add(new Topic("Kinh doanh"));
            topics.add(new Topic("Khoa học"));
            topics.add(new Topic("Giải trí"));
            topics.add(new Topic("Thể thao"));
            topics.add(new Topic("Pháp luật"));
            topics.add(new Topic("Giáo dục"));
            topics.add(new Topic("Sức khỏe"));
            topics.add(new Topic("Đời sống"));
            topics.add(new Topic("Tâm sự"));
            topicService.createTopics(topics);

//            var admin = RegisterRequest.builder()
//                    .firstname("duy")
//                    .lastname("truong")
//                    .email("truongbuiquocduy@gmail.com")
//                    .password("123456")
//                    .username("duytruong")
//                    .build();

//            var admin1 = RegisterRequest.builder()
//                    .firstname("a")
//                    .lastname("b")
//                    .email("a@abc.com")
//                    .password("123456")
//                    .username("abc")
//                    .build();
//            System.out.println("Admin token: " + service.register(admin1).getAccessToken());

//            var post1 = Post.builder()
//                    .title("title1")
//                    .content("content1")
//                    .topic(topicService.getTopicById(1L))
//                    .user(userService.getUserByUsername("abc"))
//                    .createdAt(LocalDateTime.now())
//                    .build();

//            var post2 = Post.builder()
//                    .title("title2")
//                    .content("content2")
//                    .topic(topicService.getTopicById(1L))
//                    .user(userService.getUserByUsername("abc"))
//                    .createdAt(LocalDateTime.now())
//                    .build();
//            postService.createPost(post1);
//            postService.createPost(post2);
        };
    }
}
