package com.hashtagdk.repository;

import com.hashtagdk.model.Post;
import com.hashtagdk.model.User;
import com.hashtagdk.model.PostApprobation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dawid on 7/27/17.
 */
@Repository
public interface PostAppprobationRepository extends JpaRepository<PostApprobation, Long>{
    PostApprobation findByUserAndPost(User user, Post post);
}
