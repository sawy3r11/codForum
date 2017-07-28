package com.hashtagdk.controller;

import com.hashtagdk.model.Aprobation;
import com.hashtagdk.model.Post;
import com.hashtagdk.model.Topic;
import com.hashtagdk.model.User;
import com.hashtagdk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dawid on 7/26/17.
 */
@Controller
public class PostController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserTopicViewService userTopicViewService;

    @RequestMapping(value = "/user/addPost/{idTopic}", method = RequestMethod.GET)
    public ModelAndView addNewPost(@PathVariable Long idTopic){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("idTopic", idTopic);
        Post post = new Post();
        modelAndView.addObject("post", post);
        modelAndView.setViewName("user/addPost");
        return modelAndView;
    }

    @RequestMapping(value = "/user/addPost/{idTopic}", method = RequestMethod.POST)
    public String addPost(@PathVariable Long idTopic, Post post){

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(auth.getName());
        Topic topic = topicService.getTopic(idTopic, user);
        topicService.incrementNumberOfPost(topic);

        //add user to Post
        post.setUser(user);
        //add topic
        post.setTopic(topic);
        //update LastUpdateDate
        topicService.updateLastUpdate(topic);

        userTopicViewService.addedNewPost(topic);

        postService.addNewPost(post);
        return "redirect:/user/topic/"+idTopic;
    }

    @RequestMapping(value = "/user/topic/{idTopic}/post/{aprob}/{idPost}")
    public String aprobPost(@PathVariable("idTopic") Long idTopic, @PathVariable("aprob") String aprob, @PathVariable("idPost") Long idPost){

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(auth.getName());

        if(aprob.equals("plus")){
            postService.aprobPost(postService.findById(idPost), Aprobation.PLUS, user);
        }
        if(aprob.equals("minus")){
            postService.aprobPost(postService.findById(idPost), Aprobation.MINUS, user);
        }


        return "redirect:/user/topic/"+idTopic;
    }
}
