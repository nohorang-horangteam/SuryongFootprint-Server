package com.nohorang.suryongfootprint.repository;

import com.nohorang.suryongfootprint.model.Approval;
import com.nohorang.suryongfootprint.model.ApprovalId;
import com.nohorang.suryongfootprint.model.Count;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovalRepository extends JpaRepository<Approval, ApprovalId> {
    List<Approval> findByCount(Count count);
}
