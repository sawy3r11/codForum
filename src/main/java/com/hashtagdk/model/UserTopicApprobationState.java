package com.hashtagdk.model;

import javax.persistence.*;

/**
 * Created by dawid on 7/14/17.
 */

@Entity
public class UserTopicApprobationState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /*TODO
    * dodadc   state!*/

    @ManyToOne
    private User user;

    @ManyToOne
    private Topic topic;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
