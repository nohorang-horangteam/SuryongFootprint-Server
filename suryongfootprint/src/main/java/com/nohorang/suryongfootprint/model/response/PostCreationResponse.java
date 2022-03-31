package com.nohorang.suryongfootprint.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class PostCreationResponse {
    private int postId;
    private String img;
    private String content;
    private Date date;
    private int countId;
    private String userId;
    private int challengeId;
}
