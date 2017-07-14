package com.hashtagdk.repository;

import com.hashtagdk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dawid on 7/14/17.
 */
@Repository
public interface userRepository extends JpaRepository<User, Long>{
    User findByLogin(String login);
}
