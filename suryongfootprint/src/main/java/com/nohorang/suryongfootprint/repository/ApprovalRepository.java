package com.nohorang.suryongfootprint.repository;

import com.nohorang.suryongfootprint.model.Approval;
import com.nohorang.suryongfootprint.model.ApprovalId;
import com.nohorang.suryongfootprint.model.Count;
import com.nohorang.suryongfootprint.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApprovalRepository extends JpaRepository<Approval, ApprovalId> {
    Optional<Approval> findByCountAndUser(Count count, User user);
}
