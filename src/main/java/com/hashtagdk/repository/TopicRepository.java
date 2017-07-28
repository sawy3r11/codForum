package com.hashtagdk.repository;

import com.hashtagdk.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by dawid on 7/26/17.
 */
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    /*
    JPQL don't suport limit and offset
    @Query("select t from topics t order by t.last_update_date desc LIMIT :limit  OFFSET :offset")
    List<Topic> getTopics(@Param("limit") Integer limit,
                          @Param("offset") Integer offset);*/
    List<Topic> findAll();
    Topic findByIdTopic(Long idTopic);

    @Query(value = "select t from Topic t order by t.aprobationStat")
    List<Topic> finAllAndOrderByAprobationStat();

    @Query(value = "select t from Topic t order by t.lastUpdateDate desc")
    List<Topic> findAllAndOrderByLastUpdateDate();

    @Query(value = "select count (t) from Topic t")
    Integer getNumberOfTopic();

    @Query(value = "select sum(t.viewNumber) from Topic t")
    Integer getnumberOfView();
}
