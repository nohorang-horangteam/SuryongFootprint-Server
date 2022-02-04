package com.nohorang.suryongfootprint.repository;

import com.nohorang.suryongfootprint.model.Post;
import com.nohorang.suryongfootprint.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer>{
    List<Post> findByUser(User user);
}
