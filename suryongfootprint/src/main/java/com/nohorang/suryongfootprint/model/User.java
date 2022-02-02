package com.nohorang.suryongfootprint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "USER")
public class User {
    @Id
    @Column(name="user_id", nullable=false, length=20)
    private String user_id;

    @Column(name="user_email", nullable=false, length=30)
    private String user_email;

    @Column(name="user_pw", nullable=false, length=20)
    private String user_pw;

    @Column(name="user_name", nullable=false, length=10)
    private String user_name;

    @Column(name="user_nickname", nullable=false, length=10)
    private String user_nickname;

    //User의 Badge (1:N 관계 메핑)
    //User의 Post (1:N 관계 매핑)
    //User의 Count (1:N 관계 매핑)
}
