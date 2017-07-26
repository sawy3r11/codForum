package com.hashtagdk.service;

import com.hashtagdk.model.Topic;
import com.hashtagdk.model.User;
import com.hashtagdk.repository.TopicRepository;
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

    public void addNewTopic(Topic topic, User user){
        topic.setUser(user);
        java.util.Date date = new java.util.Date();
        topic.setDate(date);
        topic.setLastUpdateDate(date);
        topicRepository.save(topic);
    }

    public List<Topic> getTopic(int limit, int offset){
        List<Topic> topicList = topicRepository.findAll();
        int sizeOfList = topicList.size();
        if(sizeOfList < (offset+limit)){
           limit = sizeOfList-1;
        }

        topicList.subList(limit, (limit+offset));

        return topicList;
    }
}
