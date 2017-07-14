package com.hashtagdk.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by dawid on 7/14/17.
 */
@Entity
@Table(name = "TOPICS")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTopic;
    private Date date;
    private String content;
    /*TODO
    * dodac enum z state*/
    private Date lastUpdateDate;

    @OneToOne
    private TopicStat topicStat;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "topic")
    private List<UserTopicState> userTopicStates;
}
