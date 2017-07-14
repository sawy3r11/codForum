package com.hashtagdk.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by dawid on 7/14/17.
 */

@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;
    private String content;
    private int aprobationState;
    private int lastUpdateDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "topicId")
    private Topic topic;

    @OneToMany(mappedBy = "post")
    private List<PostAprobation> postAprobations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAprobationState() {
        return aprobationState;
    }

    public void setAprobationState(int aprobationState) {
        this.aprobationState = aprobationState;
    }

    public int getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(int lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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

    public List<PostAprobation> getPostAprobations() {
        return postAprobations;
    }

    public void setPostAprobations(List<PostAprobation> postAprobations) {
        this.postAprobations = postAprobations;
    }
}
