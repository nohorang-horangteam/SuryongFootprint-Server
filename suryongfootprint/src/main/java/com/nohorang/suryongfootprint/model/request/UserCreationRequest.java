package com.nohorang.suryongfootprint.model.request;
import lombok.Data;

@Data
public class UserCreationRequest {
    private String user_id;
    private String user_email;
    private String user_pw;
    private String user_name;
    private String user_nickname;
    private int user_approval;
}
