package com.baidu.mybaidu.controller;

import com.baidu.mybaidu.utils.ShellUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shell")
public class ShellController {
    private static final Logger logger = Logger.getLogger(ShellController.class);
    @Value("${shellFilePath}")
    private String shellPath;
    @ResponseBody
    @RequestMapping("/shellexecute")
    public Map<String,Object> executeShell(HttpServletRequest request){
        Map<String,Object> res = new HashMap<>();
        String port = request.getParameter("port");
        try {
            ShellUtils.execShell(shellPath, port);
        }catch (Exception e){
            res.put("errno","-1");
            res.put("msg","执行异常");
            return res;
        }
        res.put("errno","0");
        res.put("msg","success");
        return res;
    }
}
