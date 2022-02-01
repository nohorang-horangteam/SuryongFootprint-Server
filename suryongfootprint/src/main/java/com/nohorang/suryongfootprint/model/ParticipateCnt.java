package com.nohorang.suryongfootprint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "participate_in_cnt")
@IdClass(ParticipateCnt.class)
public class ParticipateCnt implements Serializable {
    @Id
    private String user_id;
    @Id
    private int challenge_id;
    private int participate_in_count;
    private int post_count;
}
