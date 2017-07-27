package com.hashtagdk.service;

import com.hashtagdk.model.Post;
import com.hashtagdk.model.Topic;
import com.hashtagdk.repository.PostRepository;
import com.hashtagdk.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dawid on 7/26/17.
 */
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TopicRepository topicRepository;

    public void addNewPost(Post post){
        java.util.Date date = new java.util.Date();
        post.setDate(date);
        postRepository.save(post);
    }

    public List<Post> findPostbyTopic(Topic topic){
        return postRepository.findByTopic(topic);
    }
}
