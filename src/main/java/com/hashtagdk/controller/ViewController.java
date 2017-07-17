package com.hashtagdk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dawid on 7/14/17.
 */
@Controller
public class ViewController {

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String getLoginView(){
        return "login";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String getRegisterView(){
        return "register";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/index")
    public String getIndex(){
        return "index";
    }
}
