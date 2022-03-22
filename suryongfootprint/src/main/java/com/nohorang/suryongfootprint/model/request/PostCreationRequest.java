package com.nohorang.suryongfootprint.model.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.net.URI;

@Data
public class PostCreationRequest {
    private URI img;
    private String content;
    private int state;
    private String userId;
    private int challengeId;

}
