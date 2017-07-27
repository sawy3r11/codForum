package com.hashtagdk.repository;

import com.hashtagdk.model.Topic;
import com.hashtagdk.model.User;
import com.hashtagdk.model.TopicUserViewState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dawid on 7/27/17.
 */
@Repository
public interface TopicViewUserStateRepository extends JpaRepository<TopicUserViewState, Long> {
    List<TopicUserViewState> findByTopic(Topic topic);
    List<TopicUserViewState> findByUser(User user);
    TopicUserViewState findByTopicAndUser(Topic topic, User user);
}
