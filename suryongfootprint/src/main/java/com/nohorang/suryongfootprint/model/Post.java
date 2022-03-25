package com.nohorang.suryongfootprint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Date;
import java.net.URI;

@Getter
@Setter
@Entity
@Table(name = "POST")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    @Lob
    private URI img;
    private String content;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="challenge_id")
    private Challenge challenge;

    @ManyToOne
    @JoinColumn(name="count_id")
    private Count count;
}
