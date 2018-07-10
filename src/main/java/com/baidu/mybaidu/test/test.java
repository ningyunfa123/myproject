package com.baidu.mybaidu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;
import com.baidu.mybaidu.dao.UserDao;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/config/applicationContext.xml"} , loader = GenericXmlContextLoader.class)
public class test {

    @Value("${test}")
    private String tr;
    @Autowired
    com.baidu.mybaidu.service.Test test;
    @Test
    public void test(){
        System.out.println(test.Test().toString());
        System.out.println(tr);
    }
}
