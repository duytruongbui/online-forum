package com.n157.onlineforumchat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
@RequiredArgsConstructor
public class DemoController {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userMail = authentication.getName();
        String response = "Hello, " + userMail;
        return ResponseEntity.ok(response);
    }
}
