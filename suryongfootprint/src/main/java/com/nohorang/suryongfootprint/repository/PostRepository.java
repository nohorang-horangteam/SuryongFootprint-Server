package com.nohorang.suryongfootprint.repository;

import com.nohorang.suryongfootprint.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
