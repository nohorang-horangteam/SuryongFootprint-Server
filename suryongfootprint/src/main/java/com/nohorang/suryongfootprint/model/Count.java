package com.nohorang.suryongfootprint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "COUNT")
public class Count {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int countId;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="challenge_id", nullable=false)
    private Challenge challenge;

    @Column(name="post_count",nullable=false)
    private int postCount;

    @Column(name="approval_count", nullable=false)
    private int approvalCount;

    @Column(name="state", nullable=false)
    private int state;
}




