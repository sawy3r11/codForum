package com.hashtagdk.model;

import javax.persistence.*;

/**
 * Created by dawid on 7/14/17.
 */
@Entity
public class TopicStat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int aprobationStat;
    private int visitStat;
    private int answerStat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAprobationStat() {
        return aprobationStat;
    }

    public void setAprobationStat(int aprobationStat) {
        this.aprobationStat = aprobationStat;
    }

    public int getVisitStat() {
        return visitStat;
    }

    public void setVisitStat(int visitStat) {
        this.visitStat = visitStat;
    }

    public int getAnswerStat() {
        return answerStat;
    }

    public void setAnswerStat(int answerStat) {
        this.answerStat = answerStat;
    }
}
