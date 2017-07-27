package com.hashtagdk.model;

import javax.persistence.*;

/**
 * Created by dawid on 7/27/17.
 */
@Entity
public class TopicUserViewState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Topic topic;

    @Enumerated
    private TopicStateENUM topicStateENUM;

    //getters s


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public TopicStateENUM getTopicStateENUM() {
        return topicStateENUM;
    }

    public void setTopicStateENUM(TopicStateENUM topicStateENUM) {
        this.topicStateENUM = topicStateENUM;
    }
}
