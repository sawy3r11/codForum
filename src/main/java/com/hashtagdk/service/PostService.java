package com.hashtagdk.service;

import com.hashtagdk.model.Aprobation;
import com.hashtagdk.model.Post;
import com.hashtagdk.model.PostApprobation;
import com.hashtagdk.model.Topic;
import com.hashtagdk.model.User;
import com.hashtagdk.repository.PostAppprobationRepository;
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
    @Autowired
    private PostAppprobationRepository postAppprobationRepository;

    public void addNewPost(Post post){
        java.util.Date date = new java.util.Date();
        post.setDate(date);
        post.setAprobationState(0);
        postRepository.save(post);
    }

    public List<Post> findPostbyTopic(Topic topic){
        return postRepository.findByTopic(topic);
    }

    public List<Post> findPostByTopicOrderByApprobationState(Topic topic){
        return postRepository.findByTopicOrderByAprobationStateDesc(topic);
    }

    public boolean aprobPost(Post post, Aprobation aprobation, User user){

        PostApprobation postApprobation = postAppprobationRepository.findByUserAndPost(user, post);
        if(postApprobation!=null){
            return false;
        }

        if(aprobation == Aprobation.PLUS){
            post.setAprobationState(post.getAprobationState()+1);
        }
        if(aprobation == Aprobation.MINUS){
            post.setAprobationState(post.getAprobationState()-1);
        }
        postRepository.save(post);

        postApprobation = new PostApprobation();
        postApprobation.setUser(user);
        postApprobation.setPost(post);
        postAppprobationRepository.save(postApprobation);
        return true;
    }

    public Post findById(Long id){
        return postRepository.findById(id);
    }
}
