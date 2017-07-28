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
    @Autowired
    private UserTopicViewService userTopicViewService;

    public void addNewTopic(Topic topic, User user){
        topic.setUser(user);
        java.util.Date date = new java.util.Date();
        topic.setDate(date);
        topic.setLastUpdateDate(date);
        topic.setAprobationStat(0);
        topic.setNumberOfPosts(0);
        topic.setViewNumber(0);
        topic.setClosed(false);
        topicRepository.save(topic);
    }

    public List<Topic> getTopics(int limit, int offset, User user){
        //List<Topic> topicList = topicRepository.findAll();
        //List<Topic> topicList = topicRepository.finAllAndOrderByAprobationStat();
        List<Topic> topicList = topicRepository.findAllAndOrderByLastUpdateDate();
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

    public Topic getTopic(Long id, User user){

        Topic topic = topicRepository.findByIdTopic(id);
        TopicStateENUM topicStateENUM = topicViewUserStateRepository.findByTopicAndUser(topic, user).getTopicStateENUM();
        topic.setTopicStateENUM(topicStateENUM);
        return topic;
    }

    public void incrementNumberOfPost(Topic topic){
        topic.setNumberOfPosts(topic.getNumberOfPosts()+1);
        topicRepository.save(topic);
    }

    public void incrementViewNumber(Topic topic){
        topic.setViewNumber(topic.getViewNumber()+1);
        topicRepository.save(topic);
    }

    public void updateLastUpdate(Topic topic){
        java.util.Date date = new java.util.Date();
        topic.setLastUpdateDate(date);
        topicRepository.save(topic);
    }

    public void closeTopic(Long idTopic){
        Topic topic = topicRepository.findByIdTopic(idTopic);
        topic.setClosed(Boolean.TRUE);
        topicRepository.save(topic);
    }
}
