package com.nohorang.suryongfootprint.model.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String user_id;
    private String user_pw;
}
