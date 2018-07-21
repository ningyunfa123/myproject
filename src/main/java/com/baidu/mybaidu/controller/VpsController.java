package com.baidu.mybaidu.controller;


import com.baidu.mybaidu.dto.ApplyVpsDto;
import com.baidu.mybaidu.pojo.User;
import com.baidu.mybaidu.service.ApplyVpsService;
import com.baidu.mybaidu.service.VPSRecordeService;
import com.baidu.mybaidu.utils.sign.SignUtils;
import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vps")
public class VpsController {

    private static final Logger logger = Logger.getLogger(VpsController.class);
    @Autowired
    ApplyVpsService applyVpsService;
    @Autowired
    VPSRecordeService vpsRecordeService;

    @ResponseBody
    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    public Map<String,Object> applyVps(@RequestBody @Valid ApplyVpsDto applyVpsDto, HttpServletRequest request,BindingResult result){
        Map<String,Object> returnResult = new HashMap<>();
        if(result.hasErrors()){
            returnResult.put("errno","-1");
            returnResult.put("msg","必要参数为空");
            return returnResult;
        }
        Map<String,Object> applyResponse;

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if(currentUser == null){
            logger.error("用户未登录！");
            returnResult.put("errno","-1");
            returnResult.put("msg","您还未登录，请登录后再操作。");
            return returnResult;
        }
        if(StringUtils.isEmpty(applyVpsDto.getCertCode())){
            logger.error("入参验证码为空");
            returnResult.put("errno","-2");
            returnResult.put("msg","请输入验证码");
            return returnResult;
        }
        String sign = SignUtils.buildSign(applyVpsDto,1,"123456");
        if(StringUtils.isEmpty(applyVpsDto.getSign()) || !applyVpsDto.getSign().equals(sign)){
            returnResult.put("errno","-3");
            returnResult.put("msg","签名校验失败");
            return returnResult;
        }
        logger.error("签名校验成功");
        applyVpsDto.setUserName(currentUser.getUserName());
        try{
            applyResponse = applyVpsService.applyVps(applyVpsDto);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("申请失败");
            returnResult.put("errno","-2");
            returnResult.put("msg","申请失败");
            return returnResult;
        }
        if(applyResponse.get("errno").equals("0")){
            Map<String,Object> data = new HashMap<>();
            @SuppressWarnings("unchecked")
            Map<String,Object> data1 = (Map<String, Object>) applyResponse.get("data");
            returnResult.put("errno","0");
            returnResult.put("msg","success");
            data.put("port",data1.get("port"));
            data.put("password",data1.get("password"));
            returnResult.put("data",data);
            return  returnResult;
        }else{
            returnResult.put("errno",applyResponse.get("errno"));
            returnResult.put("msg",applyResponse.get("msg"));
            return returnResult;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/fastquery",method = RequestMethod.GET)
    public Map<String,Object> vpsQuery(HttpServletRequest request){
        Map<String,Object> returnResult = new HashMap<>();
        //登录校验
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        if(user == null){
            logger.fatal("用户未登录");
            returnResult.put("errno","-1");
            returnResult.put("msg","您还未登录，请登录后再操作。");
            return returnResult;
        }
        String userName = user.getUserName();
        List<Map<String,Object>> vpsRecordList;
        try{
            vpsRecordList = vpsRecordeService.getByConds(null,null,null,userName);
        }catch (Exception e){
            e.printStackTrace();
            logger.fatal("查询数据库失败");
            throw e;
        }
        if(CollectionUtils.isEmpty(vpsRecordList)){
            returnResult.put("errno","-2");
            returnResult.put("msg","您还没有已购买的vps");
            return returnResult;
        }else{
            List<String> portInfos = new ArrayList<>();
            List<String> passInfos = new ArrayList<>();
            for(Map<String,Object> vpsRecord:vpsRecordList){
                portInfos.add(vpsRecord.get("port").toString());
                passInfos.add(vpsRecord.get("password").toString());
            }
            String portString = StringUtils.join(portInfos,",");
            String passString = StringUtils.join(passInfos,",");
            Map<String,Object> data = new HashMap<>();
            data.put("port",portString);
            data.put("password",passString);
            returnResult.put("errno","0");
            returnResult.put("msg","success");
            returnResult.put("data",data);
            return returnResult;
        }
    }
}
