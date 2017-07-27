package com.hashtagdk.repository;

import com.hashtagdk.model.Post;
import com.hashtagdk.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dawid on 7/26/17.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTopic(Topic topic);
}
