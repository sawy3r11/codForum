package com.hashtagdk.model;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dawid on 7/14/17.
 */
@Entity
@Table(name="USER_STATS")
public class UserStat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int numberOfPosts;
    private int numberOfTopic;
    /*TODO
    * dodać avatar*/
    private int aprobation;
    private Date registrationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(int numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public int getNumberOfTopic() {
        return numberOfTopic;
    }

    public void setNumberOfTopic(int numberOfTopic) {
        this.numberOfTopic = numberOfTopic;
    }

    public int getAprobation() {
        return aprobation;
    }

    public void setAprobation(int aprobation) {
        this.aprobation = aprobation;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
