package com.baidu.mybaidu.schedule;

import com.baidu.mybaidu.dto.Vpsform;
import com.baidu.mybaidu.service.VPSService;
import com.baidu.mybaidu.utils.DataTransferUtils;
import com.baidu.mybaidu.utils.FileUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

@Component
public class UpdateVpsTask {
    private static final Logger logger = Logger.getLogger(UpdateVpsTask.class);

    @Value("${filePath}")
    private String path;
    @Autowired
    VPSService vpsService;
    @Scheduled(cron = "0 0 3 * * ? ")
    public void work() throws IOException {
        String jsonString = FileUtils.getFileContents(path);
        Map<String,Object> configMap = DataTransferUtils.jsonToMap(jsonString);
        //获取配置文件port password映射
        Map<String,String> configPortPassword = (Map<String, String>) configMap.get("port_password");
        logger.error("config"+configPortPassword);
        //获取数据库vps信息
        List<Map<String,Object>> vpsFromDB = vpsService.getByConds(null,null,null,0);
        logger.error("vpsFromDB:"+vpsFromDB);
        if(!CollectionUtils.isEmpty(vpsFromDB)){
            Map<String,String> dbPortPassword = new HashMap<>();
            for(Map<String,Object> vps:vpsFromDB){
                //对数据库port password 映射到map
                dbPortPassword.put(vps.get("port").toString(),vps.get("password").toString());
            }
            logger.error("dbportPassword:"+dbPortPassword);
            //将配置文件port转成list
            List<Integer> configPortList = new ArrayList<>();// = Arrays.asList(portPassword.keySet().toArray());
            for(Map.Entry<String,String> entry:configPortPassword.entrySet()){
                configPortList.add(Integer.valueOf(entry.getKey()));
                logger.error("configPortList:"+configPortList);
            }
            //将数据库port转成list
            List<Integer> dbPortList = new ArrayList<>();
            for(Map.Entry<String,String> entry:dbPortPassword.entrySet()){
                dbPortList.add(Integer.valueOf(entry.getKey()));
                logger.error("dbPortList:"+dbPortList);
            }
            //获取数据库中于配置文件不存在的端口信息并插入vps表
            for(Integer port:configPortList){
                if(!dbPortPassword.containsKey(port.toString())){
                    List<Map<String,Object>> dbPortinvalid = vpsService.getByConds(null,port,null,1);
                    if(!CollectionUtils.isEmpty(dbPortinvalid)){
                        Vpsform vpsform1 = new Vpsform();
                        vpsform1.setId(dbPortinvalid.get(0).get("id").toString());
                        vpsform1.setDeleteFlag("0");
                        vpsService.update(vpsform1);
                    }else{
                        String password = configPortPassword.get(port.toString());
                        Vpsform vpsform = new Vpsform();
                        vpsform.setPort(port.toString());
                        vpsform.setPassword(password);
                        vpsform.setIp(configMap.get("server").toString());
                        try {
                            vpsService.insert(vpsform);

                        }catch (Exception e){
                            e.printStackTrace();
                            logger.error("vps信息插入数据库失败");
                        }
                    }

                }
            }
            //获取配置信息中已删除的数据并将vps表中的数据置为已删除
            for(Integer port:dbPortList){
                if(!configPortPassword.containsKey(port.toString())){
                    Map<String,Object> res = vpsService.getByConds(null,port,null,0).get(0);
                    Vpsform vpsform = new Vpsform();
                    vpsform.setId(res.get("id").toString());
                    vpsform.setDeleteFlag("1");
                    try {
                        vpsService.update(vpsform);
                    }catch (Exception e){
                        e.printStackTrace();
                        logger.error("删除数据库vps信息失败");
                    }
                }
            }
        }else{
            if(!CollectionUtils.isEmpty(configPortPassword)){
                for(Map.Entry<String,String> entry:configPortPassword.entrySet()){
                    Vpsform vpsform = new Vpsform();
                    vpsform.setPassword(entry.getValue());
                    vpsform.setPort(entry.getKey());
                    vpsform.setIp(configMap.get("server").toString());
                    try {
                        vpsService.insert(vpsform);

                    }catch (Exception e){
                        e.printStackTrace();
                        logger.error("vps信息插入数据库失败");
                    }
                }
            }
        }
    }

}
