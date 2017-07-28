package com.hashtagdk.controller;

import com.hashtagdk.model.*;
import com.hashtagdk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    @Autowired
    private TopicPostStatisticService topicPostStatisticService;
    @Autowired
    private UserTopicViewService userTopicViewService;

    @RequestMapping(value = "/user/topics", method = RequestMethod.GET)
    public ModelAndView topic(){
        ModelAndView modelAndView = new ModelAndView();

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(auth.getName());
        modelAndView.addObject("login", user.getLogin());

        List<Topic> topicList = topicService.getTopics(10, 0, user);
        modelAndView.addObject("topics", topicList);
        modelAndView.setViewName("user/topics");

        //Forum stat
        modelAndView.addObject("forumStat", topicPostStatisticService.getForumStat());

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

        userTopicViewService.addedNewTopic(topic);

        return "redirect:/user/topics";
    }

    @RequestMapping(value = "/user/topic/{topicId}", method = RequestMethod.GET)
    public ModelAndView getTopic(@PathVariable Long topicId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/postTopic");

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(auth.getName());

        //topic
        Topic topic = topicService.getTopic(topicId, user);
        modelAndView.addObject("topic", topic);

        //posts
        //List<Post> postList = postService.findPostbyTopic(topic);
        //new method!!!
        List<Post> postList = postService.findPostByTopicOrderByApprobationState(topic);
        modelAndView.addObject("posts", postList);

        //username
        modelAndView.addObject("login", user.getLogin());

        //set like Viewed!
        userTopicViewService.changeToViewed(user, topic);

        //check if Author
        if(topic.getUser().equals(user)){
            modelAndView.addObject("author", true);
        }
        else{
            modelAndView.addObject("author", false);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/user/topic/{aprob}/{idTopic}")
    public String addAprob(@PathVariable String aprob, @PathVariable Long idTopic){
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(auth.getName());

        Topic topic = topicService.getTopic(idTopic, user);
        if(aprob.equals("plus")){
            topicPostStatisticService.addAprobation(user, topic, Aprobation.PLUS);
        }
        else if (aprob.equals("minus")){
            topicPostStatisticService.addAprobation(user, topic, Aprobation.MINUS);
        }

        return "redirect:/user/topic/"+idTopic;
    }

    @RequestMapping(value = "user/topic/close/{idTopic}")
    public String closeTopic(@PathVariable Long idTopic){
        topicService.closeTopic(idTopic);
        return "redirect:/user/topic/"+idTopic;
    }
}
