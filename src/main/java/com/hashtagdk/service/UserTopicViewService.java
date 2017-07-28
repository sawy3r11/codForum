package com.hashtagdk.service;

import com.hashtagdk.model.Topic;

import com.hashtagdk.model.TopicStateENUM;
import com.hashtagdk.model.User;
import com.hashtagdk.model.TopicUserViewState;
import com.hashtagdk.repository.TopicRepository;
import com.hashtagdk.repository.TopicViewUserStateRepository;
import com.hashtagdk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dawid on 7/27/17.
 */
@Service
public class UserTopicViewService {

    @Autowired
    private TopicViewUserStateRepository topicViewUserStateRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicService topicService;


    public void addedNewUser(User user){
        List<Topic> topicList = topicRepository.findAll();
        for(Topic topic : topicList){
            TopicUserViewState topicUserViewState = new TopicUserViewState();
            topicUserViewState.setTopicStateENUM(TopicStateENUM.NEW_POST);
            topicUserViewState.setUser(user);
            topicUserViewState.setTopic(topic);

            topicViewUserStateRepository.save(topicUserViewState);
        }
    }

    public void addedNewTopic(Topic topic){
        List<User> userList = userRepository.findAll();
        for(User user:userList){
            TopicUserViewState topicUserViewState = new TopicUserViewState();
            topicUserViewState.setTopic(topic);
            topicUserViewState.setUser(user);
            topicUserViewState.setTopicStateENUM(TopicStateENUM.NEW_POST);
            topicViewUserStateRepository.save(topicUserViewState);
        }
    }

    public void addedNewPost(Topic topic){
        List<TopicUserViewState> topicUserViewStates = topicViewUserStateRepository.findByTopic(topic);
        for(TopicUserViewState topicUserViewState:topicUserViewStates){
            topicUserViewState.setTopicStateENUM(TopicStateENUM.NEW_POST);
            topicViewUserStateRepository.save(topicUserViewState);
        }
    }

    public List<TopicUserViewState> findUserTopicState( User user){
        return topicViewUserStateRepository.findByUser(user);
    }

    public void changeToViewed(User user, Topic topic){
        if(topic.getTopicStateENUM() == TopicStateENUM.NEW_POST){
            TopicUserViewState topicUserViewState = topicViewUserStateRepository.findByTopicAndUser(topic, user);
            topicUserViewState.setTopicStateENUM(TopicStateENUM.VIEWED);
            topicViewUserStateRepository.save(topicUserViewState);

            topicService.incrementViewNumber(topic);
        }

    }

    public TopicStateENUM getTopicState(Topic topic, User user){
        return topicViewUserStateRepository.findByTopicAndUser(topic, user).getTopicStateENUM();
    }
}
