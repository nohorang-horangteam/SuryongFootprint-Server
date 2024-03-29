package com.nohorang.suryongfootprint.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "APPROVAL")
@IdClass(ApprovalId.class)
public class Approval {
    @Id
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonManagedReference
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name="count_id", nullable=false)
    @JsonManagedReference
    private Count count;
}

