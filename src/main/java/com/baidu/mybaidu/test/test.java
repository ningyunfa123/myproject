package com.baidu.mybaidu.test;


import com.baidu.mybaidu.controller.VpsController;
import com.baidu.mybaidu.dto.*;
import com.baidu.mybaidu.schedule.UpdateVpsTask;
import com.baidu.mybaidu.service.*;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.IOException;


@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/config/applicationContext.xml","classpath:/config/spring-mvc.xml"} , loader = GenericXmlContextLoader.class)
public class test {
    private static final Logger logger = Logger.getLogger(test.class);
    @Resource(name = "notNullTest")
    private NotNullTest notNullTest;
    @Value("${test}")
    private String tr;
    @Value("${filePath}")
    private String filePath;
    @Autowired
    VPSTypeService vpsTypeService;
    @Autowired
    VpsController vpsController;
    @Autowired
    UpdateVpsTask updateVpsTask;
    @Test
    public void test() throws IOException {
        ApplyVpsDto applyVpsDto = new ApplyVpsDto();
        applyVpsDto.setUseTime("1");
        applyVpsDto.setVpsType("2");
        applyVpsDto.setCertCode("23FDE44");
        applyVpsDto.setPassword("1111");
        updateVpsTask.work();
        Gson gson = new Gson();
        //Map<String,Object> res = gson.fromJson(FileUtils.getFileContents(filePath),HashMap.class);
        //Map<String,String> teres = (Map<String, String>) res.get("port_password");
        //System.out.println(teres.keySet().toArray()[0]);
        logger.error("loger测试");
        //System.out.println(SignUtils.buildSign(applyVpsDto,1,"123"));
        //System.out.println(vpsController.applyVps(applyVpsDto,null));
        //System.out.println(vpsRecordeService.getByConds(2,null,null,null));
    }
}
