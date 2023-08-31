package com.n157.onlineforumchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineForumChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineForumChatApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner seedTopics(TopicService topicService,
//                                        AuthenticationService service,
//                                        PostService postService,
//                                        UserService userService) {
//        return args -> {
//            List<Topic> topics = new ArrayList<>();
//            topics.add(new Topic("Công nghệ"));
//            topics.add(new Topic("Kinh doanh và khởi nghiệp"));
//            topics.add(new Topic("Phát triển bản thân"));
//            topics.add(new Topic("Khoa học"));
//            topics.add(new Topic("Sức khỏe và Y học"));
//            topics.add(new Topic("Giáo dục"));
//            topics.add(new Topic("Du lịch"));
//            topics.add(new Topic("Sự kiện và Xã hội"));
//            topics.add(new Topic("Trải nghiệm và tâm sự"));
//            topics.add(new Topic("Đầu tư tài chính"));
//            topicService.createTopics(topics);
//
//            var admin = RegisterRequest.builder()
//                    .firstname("duy")
//                    .lastname("truong")
//                    .email("truongbuiquocduy@gmail.com")
//                    .password("123456")
//                    .username("duytruong")
//                    .build();
//
//            var admin1 = RegisterRequest.builder()
//                    .firstname("a")
//                    .lastname("b")
//                    .email("a@abc.com")
//                    .password("123456")
//                    .username("abc")
//                    .build();
//            service.register(admin);
//            service.register(admin1);
//            System.out.println("Admin token: " + service.register(admin1).getAccessToken());
//
//            var post1 = Post.builder()
//                    .title("title1")
//                    .content("content1")
//                    .topic(topicService.getTopicById(1L))
//                    .user(userService.getUserByUsername("abc"))
//                    .createdAt(LocalDateTime.now())
//                    .build();
//
//            var post2 = Post.builder()
//                    .title("title2")
//                    .content("content2")
//                    .topic(topicService.getTopicById(1L))
//                    .user(userService.getUserByUsername("abc"))
//                    .createdAt(LocalDateTime.now())
//                    .build();
//            postService.createPost(post1);
//            postService.createPost(post2);
//        };
//    }
}
