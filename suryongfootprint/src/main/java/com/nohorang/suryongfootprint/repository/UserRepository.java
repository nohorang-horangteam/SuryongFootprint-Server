package com.nohorang.suryongfootprint.repository;

import com.nohorang.suryongfootprint.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
//    List<User> findByUser_name(String user_name);
//    Optional<User> findByUser_idAndUser_nameAndUser_email(String user_id, String user_name, String user_email);
}
