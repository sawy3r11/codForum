package com.hashtagdk.repository;

import com.hashtagdk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dawid on 7/14/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
        User findByLogin(String login);
        //List<User> findByLoginAndPassword(String login, String password);

        @Query(value = "select count(u) from User  u")
        Integer numberOfUser();
}
