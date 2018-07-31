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
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void work() throws IOException {
        String jsonString = FileUtils.getFileContents(path);
        Map<String,Object> configMap = DataTransferUtils.jsonToMap(jsonString);
        //获取配置文件port password映射
        Map<String,String> configPortPassword = (Map<String, String>) configMap.get("port_password");
        logger.debug("config"+configPortPassword);
        //获取数据库vps信息
        List<Map<String,Object>> vpsFromDB = vpsService.getByConds(null,null,null,0);
        logger.debug("vpsFromDB:"+vpsFromDB);
        if(!CollectionUtils.isEmpty(vpsFromDB)){
            Map<String,String> dbPortPassword = new HashMap<>();
            //数据映射
            vpsFromDB.forEach(vps->dbPortPassword.put(vps.get("port").toString(),vps.get("password").toString()));
            logger.debug("dbportPassword:"+dbPortPassword);
            //将配置文件port转成list
            List<Integer> configPortList = new ArrayList<>();// = Arrays.asList(portPassword.keySet().toArray());
            configPortPassword.forEach((key, value) -> configPortList.add(Integer.valueOf(key)));
            logger.debug("configPortList:"+configPortList);
            //将数据库port转成list
            List<Integer> dbPortList = new ArrayList<>();
            dbPortPassword.forEach((key,value)->dbPortList.add(Integer.valueOf(key)));
            logger.debug("dbPortList:"+dbPortList);
            //获取数据库中于配置文件不存在的端口信息并插入vps表
            for(Integer port:configPortList){
                if(!dbPortPassword.containsKey(port.toString())){
                    List<Map<String,Object>> dbPortinvalid = vpsService.getByConds(null,port,null,1);
                    if(!CollectionUtils.isEmpty(dbPortinvalid)){
                        Vpsform vpsform1 = new Vpsform();
                        vpsform1.setId(dbPortinvalid.get(0).get("id").toString());
                        vpsform1.setDeleteFlag("0");
                        vpsform1.setStatus("1");
                        vpsform1.setPassword(configPortPassword.get(port.toString()));
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
                            logger.error(" 定时更新vpsrecord表任务vps信息插入数据库失败");
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
                        logger.error("定时更新vpsrecord表任务删除数据库vps信息失败");
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
                        logger.error("定时更新vpsrecord表任务vps信息插入数据库失败");
                    }
                }
            }
        }
    }

}
