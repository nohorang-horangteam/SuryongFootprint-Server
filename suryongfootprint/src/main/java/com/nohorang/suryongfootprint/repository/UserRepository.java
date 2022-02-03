package com.nohorang.suryongfootprint.repository;

import com.nohorang.suryongfootprint.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUser_nameAndUser_email(String user_name, String user_email);
    Optional<User> findByUser_idAndUser_nameAndUser_email(String user_id, String user_name, String user_email);
}
