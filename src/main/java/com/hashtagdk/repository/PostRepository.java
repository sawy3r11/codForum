package com.hashtagdk.repository;

import com.hashtagdk.model.Post;
import com.hashtagdk.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dawid on 7/26/17.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTopic(Topic topic);
    Post findById(Long id);

    /*@Query(value = "select p from Post p where p.topic=?1 order by p.aprobationState")
    List<Post> findByT*/
    List<Post> findByTopicOrderByAprobationStateDesc(Topic topic);
}
