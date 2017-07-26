package com.hashtagdk.model;

import javax.persistence.*;
import java.sql.Clob;
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
    private String topicTitle;

    @Column(length=10485760)
    private String topicContent;
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

    public Long getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(Long idTopic) {
        this.idTopic = idTopic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public TopicStat getTopicStat() {
        return topicStat;
    }

    public void setTopicStat(TopicStat topicStat) {
        this.topicStat = topicStat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserTopicState> getUserTopicStates() {
        return userTopicStates;
    }

    public void setUserTopicStates(List<UserTopicState> userTopicStates) {
        this.userTopicStates = userTopicStates;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }
}
