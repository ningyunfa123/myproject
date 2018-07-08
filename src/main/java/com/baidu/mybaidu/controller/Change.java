package com.baidu.mybaidu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping(value = "/test")
public class Change {


    @RequestMapping(value = "/first")
    public String change(){
        return "/test/ksp";
    }
}
