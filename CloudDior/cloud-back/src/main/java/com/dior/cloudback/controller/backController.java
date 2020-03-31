package com.dior.cloudback.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class backController {

    @RequestMapping("index")
    public String toIndex(){
        return "Index";
    }

    @RequestMapping("testFenli")
    public String testFenli(){
        return "testFenli";
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }



}
