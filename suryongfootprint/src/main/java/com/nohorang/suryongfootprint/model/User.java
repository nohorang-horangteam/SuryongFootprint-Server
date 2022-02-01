package com.nohorang.suryongfootprint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    private String user_id;
    private String user_email;
    private String user_pw;
    private String user_name;
    private String user_nickname;
    private int user_approval;
}
