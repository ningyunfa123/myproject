package com.baidu.mybaidu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by cheng on 2017/8/20.
 */
@Controller
public class SignController {

    @RequestMapping("/signup")
    public String signup(HttpSession session){
        return "main/signup";
    }

}
