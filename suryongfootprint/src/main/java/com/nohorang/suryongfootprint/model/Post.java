package com.nohorang.suryongfootprint.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "POST")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    @Lob
    private byte[] img;
    private String content;
    private Date date;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JoinColumn(name="challenge_id")
    @JsonManagedReference
    private Challenge challenge;

    @ManyToOne
    @JoinColumn(name="count_id")
    @JsonManagedReference
    private Count count;


}
