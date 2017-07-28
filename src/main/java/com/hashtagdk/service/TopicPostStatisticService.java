package com.hashtagdk.service;

import com.hashtagdk.model.*;
import com.hashtagdk.repository.TopicRepository;
import com.hashtagdk.repository.UserRepository;
import com.hashtagdk.repository.UserTopicStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dawid on 7/27/17.
 */
@Service
public class TopicPostStatisticService {

    @Autowired
    private UserTopicStateRepository userTopicStateRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;

    public Boolean addAprobation(User user, Topic topic, Aprobation aprobation){
        UserTopicApprobationState userTopicApprobationState = userTopicStateRepository.findByUserAndTopic(user, topic);
        if(userTopicApprobationState !=null){
            return false;
        }
        if(aprobation == Aprobation.PLUS){
            topic.setAprobationStat(topic.getAprobationStat()+1);
        }
        if(aprobation == Aprobation.MINUS){
            topic.setAprobationStat(topic.getAprobationStat()-1);
        }
        topicRepository.save(topic);

        userTopicApprobationState = new UserTopicApprobationState();
        userTopicApprobationState.setUser(user);
        userTopicApprobationState.setTopic(topic);
        userTopicStateRepository.save(userTopicApprobationState);

        return true;
    }

    public ForumStat getForumStat(){
        ForumStat forumStat = new ForumStat();

        forumStat.setNumberOfTopic(topicRepository.getNumberOfTopic());
        forumStat.setNumberOfUSer(userRepository.numberOfUser());
        forumStat.setNumberOfView(topicRepository.getnumberOfView());
        return forumStat;
    }

}

