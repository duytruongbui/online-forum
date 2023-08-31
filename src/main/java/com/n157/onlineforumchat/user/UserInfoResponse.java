package com.n157.onlineforumchat.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResponse {
    private String email;
    private String firstname;
    private String lastname;
    private String username;
}