package com.n157.onlineforumchat.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/bot")
public class ChatGptController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public BotChatResponse chat(@RequestParam("prompt") String prompt){
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);

        assert chatGptResponse != null;

        String responseContent = chatGptResponse.getChoices().get(0).getMessage().getContent();

        return new BotChatResponse(responseContent);
    }
}
