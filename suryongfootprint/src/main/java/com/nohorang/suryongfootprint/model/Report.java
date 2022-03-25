package com.nohorang.suryongfootprint.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "REPORT")
@IdClass(ReportId.class)
public class Report {
    @Id
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name="challenge_id", nullable=false)
    private Challenge challenge;

    @Column(name="count", nullable=false)
    private int count;
}

