package com.hashtagdk.controller;

import com.hashtagdk.model.Post;
import com.hashtagdk.model.Topic;
import com.hashtagdk.model.User_Role;
import com.hashtagdk.service.PostService;
import com.hashtagdk.service.TopicService;
import com.hashtagdk.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.hashtagdk.model.User;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.util.List;

/**
 * Created by dawid on 7/26/17.
 */
@Controller
public class TopicController {

    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private PostService postService;

    @RequestMapping(value = "/user/topics", method = RequestMethod.GET)
    public ModelAndView topic(){
        ModelAndView modelAndView = new ModelAndView();

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(auth.getName());
        modelAndView.addObject("login", user.getLogin());

        List<Topic> topicList = topicService.getTopic(10, 0);
        modelAndView.addObject("topics", topicList);
        modelAndView.setViewName("user/topics");

        return modelAndView;
    }

    @RequestMapping(value = "/user/addTopic", method = RequestMethod.GET)
    public ModelAndView addTopic(){
        ModelAndView modelAndView = new ModelAndView();
        Topic topic = new Topic();
        modelAndView.addObject("topic", topic);

        //username
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(auth.getName());
        modelAndView.addObject("login", user.getLogin());

        modelAndView.setViewName("user/addTopic");
        return modelAndView;
    }

    @RequestMapping(value = "/user/addTopic", method = RequestMethod.POST)
    public String addTopicPost(Topic topic){
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.findByLogin(auth.getName());
        topicService.addNewTopic(topic, user);

        return "redirect:/user/topics";
    }

    @RequestMapping(value = "/user/topic/{topicId}", method = RequestMethod.GET)
    public ModelAndView getTopicPage(@PathVariable Long topicId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/postTopic");

        //topic
        Topic topic = topicService.getTopic(topicId);
        modelAndView.addObject("topic", topic);

        //posts
        List<Post> postList = postService.findPostbyTopic(topic);
        modelAndView.addObject("posts", postList);

        //username
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(auth.getName());
        modelAndView.addObject("login", user.getLogin());

        return modelAndView;
    }
}
