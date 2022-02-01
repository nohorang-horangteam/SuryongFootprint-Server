package com.nohorang.suryongfootprint.repository;

import com.nohorang.suryongfootprint.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
