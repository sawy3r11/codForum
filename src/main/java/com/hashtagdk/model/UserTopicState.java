package com.hashtagdk.model;

import javax.persistence.*;

/**
 * Created by dawid on 7/14/17.
 */

@Entity
public class UserTopicState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /*TODO
    * dodadc   state!*/
    private int topicAprob;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idTopic")
    private Topic topic;
}
