package com.nohorang.suryongfootprint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "COUNT")
@IdClass(CountId.class)
public class Count {
    @Id
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name="challenge_id", nullable=false)
    private Challenge challenge;

    @Column(name="challenge_count", nullable=false)
    private int challenge_count;

    @Column(name="post_count",nullable=false)
    private int post_count;
}




