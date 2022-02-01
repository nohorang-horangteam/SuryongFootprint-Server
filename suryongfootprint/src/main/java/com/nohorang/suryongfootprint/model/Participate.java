package com.nohorang.suryongfootprint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "participate_in")
public class Participate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int participate_in_id;
    private String user_id;
    private int challenge_id;
    private String img;
    private String participate_in_text;
    private int participate_in_stat;
    private LocalDateTime participate_in_date;
}
