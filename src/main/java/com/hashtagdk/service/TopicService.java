package com.hashtagdk.service;

import com.hashtagdk.model.Topic;
import com.hashtagdk.model.TopicStateENUM;
import com.hashtagdk.model.TopicUserViewState;
import com.hashtagdk.model.User;
import com.hashtagdk.repository.TopicRepository;
import com.hashtagdk.repository.TopicViewUserStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dawid on 7/26/17.
 */
@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicViewUserStateRepository topicViewUserStateRepository;

    public void addNewTopic(Topic topic, User user){
        topic.setUser(user);
        java.util.Date date = new java.util.Date();
        topic.setDate(date);
        topic.setLastUpdateDate(date);
        topic.setAprobationStat(0);
        topic.setNumberOfPosts(0);
        topicRepository.save(topic);
    }

    public List<Topic> getTopic(int limit, int offset, User user){
        //List<Topic> topicList = topicRepository.findAll();
        List<Topic> topicList = topicRepository.finAllAndOrderByAprobationStat();
        if(!topicList.isEmpty()){
            int sizeOfList = topicList.size();
            if(sizeOfList < (offset+limit)){
                limit = sizeOfList-1;
            }
            topicList.subList(limit, (limit+offset));
        }

        for(Topic topic:topicList){
            TopicUserViewState topicUserViewState = topicViewUserStateRepository.findByTopicAndUser(topic, user);
            topic.setTopicStateENUM(topicUserViewState.getTopicStateENUM());
        }

        return topicList;
    }

    public Topic getTopic(Long id){
        return topicRepository.findByIdTopic(id);
    }

    public void incrementNumberOfPost(Topic topic){
        topic.setNumberOfPosts(topic.getNumberOfPosts()+1);
        topicRepository.save(topic);
    }
}
