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
    private String topicTitle;
    private int aprobationStat;

    private int numberOfPosts;

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
    private List<UserTopicApprobationState> userTopicApprobationStates;

    @OneToMany
    private List<TopicUserViewState> topicUserViewStates;

    //used only to display!!!
    @Transient
    private TopicStateENUM topicStateENUM;

    public TopicStateENUM getTopicStateENUM() {
        return topicStateENUM;
    }

    public void setTopicStateENUM(TopicStateENUM topicStateENUM) {
        this.topicStateENUM = topicStateENUM;
    }

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

    public List<UserTopicApprobationState> getUserTopicApprobationStates() {
        return userTopicApprobationStates;
    }

    public void setUserTopicApprobationStates(List<UserTopicApprobationState> userTopicApprobationStates) {
        this.userTopicApprobationStates = userTopicApprobationStates;
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

    public int getAprobationStat() {
        return aprobationStat;
    }

    public void setAprobationStat(int aprobationStat) {
        this.aprobationStat = aprobationStat;
    }

    public int getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(int numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public List<TopicUserViewState> getTopicUserViewStates() {
        return topicUserViewStates;
    }

    public void setTopicUserViewStates(List<TopicUserViewState> topicUserViewStates) {
        this.topicUserViewStates = topicUserViewStates;
    }
}
