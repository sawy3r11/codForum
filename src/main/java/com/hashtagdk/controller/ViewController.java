package com.hashtagdk.controller;

import com.hashtagdk.model.User;
import com.hashtagdk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by dawid on 7/14/17.
 */
@Controller
public class ViewController {

    private UserService userService;

    @Autowired
    public ViewController(UserService userService){
        this.userService = userService;
    }


    @RequestMapping(method = RequestMethod.GET, value = {"/login", "/"})
    public ModelAndView getLoginPage(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");

        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("register");
        }
        else if(!user.getPassword().equals(user.getPassword2())){
            modelAndView.setViewName("register");
            modelAndView.addObject("passwordDiffrent", true);
        }

        else if (userService.checkIfExist(user.getLogin())){
            modelAndView.addObject("userExist", true);
        }
        else{
            // add new templates.user
            userService.save(user);
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }
}
