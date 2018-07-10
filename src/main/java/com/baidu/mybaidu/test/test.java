package com.baidu.mybaidu.test;

import com.baidu.mybaidu.controller.Change;
import com.baidu.mybaidu.dto.NotNullTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;
import com.baidu.mybaidu.dao.UserDao;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/config/applicationContext.xml","classpath:/config/spring-mvc.xml"} , loader = GenericXmlContextLoader.class)
public class test {

    @Resource(name = "notNullTest")
    private NotNullTest notNullTest;
    @Value("${test}")
    private String tr;
    @Autowired
    com.baidu.mybaidu.service.Test test;
    @Autowired
    Change change;
    @Test
    public void test(){
        notNullTest.setNot(null);
        notNullTest.setNull(null);
        //Change change = new Change();

        //System.out.println(change.test(notNullTest));
        System.out.println(test.Test().toString());
        System.out.println(tr);
    }
}
