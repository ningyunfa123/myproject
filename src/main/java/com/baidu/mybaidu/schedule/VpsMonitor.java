package com.baidu.mybaidu.schedule;

import com.baidu.mybaidu.dto.VpsRecordForm;
import com.baidu.mybaidu.service.VPSRecordeService;
import com.baidu.mybaidu.utils.DataTransferUtils;
import com.baidu.mybaidu.utils.FileUtils;
import com.baidu.mybaidu.utils.ShellUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class VpsMonitor {

    private static final Logger logger = Logger.getLogger(VpsMonitor.class);
    @Value("${filePath}")
    private String configPath;
    @Autowired
    VPSRecordeService vpsRecordeService;
    @Scheduled(cron = "0 0/30 * * * ?")
    public void work() throws IOException {
        List<Map<String,Object>> vpsRecordList = vpsRecordeService.getByConds(null,null,null,null);
        logger.error("开始执行");
        String shadowsocksConfig = null;
        Map<String,String> configPortPassword = null;
        Map<String,Object> configMap = null;
        List<String> vpsRecordIds = new ArrayList<>();
        if(!CollectionUtils.isEmpty(vpsRecordList)){
            logger.error("数据不为空"+vpsRecordList);
            shadowsocksConfig = FileUtils.getFileContents(configPath);
            configMap = DataTransferUtils.jsonToMap(shadowsocksConfig);
            //获取配置文件port password映射
            configPortPassword = (Map<String, String>) configMap.get("port_password");
            Boolean flag = false;
            for(Map<String,Object> vpsRecord:vpsRecordList){
                Integer useTime = (Integer) vpsRecord.get("use_time");
                Long transUseTime = Long.valueOf(useTime)*86400000L;
                Long createTime = (Long) vpsRecord.get("update_time");
                if(System.currentTimeMillis()>(transUseTime+createTime)){
                    flag = true;
                    logger.fatal("vps信息更新定时任务存在已过期的数据");
                    configPortPassword.remove(vpsRecord.get("port").toString());
                    vpsRecordIds.add(vpsRecord.get("id").toString());
                }
            }
            if(flag){
                //删除配置文件中已失效的port和密码
                configMap.put("port_password",configPortPassword);
                JSONObject transResult = JSONObject.fromObject(configMap);
                logger.fatal("transResult:"+transResult.toString());
                try {
                    Writer w = new FileWriter(configPath, false);
                    w.write(transResult.toString());
                    w.close();
                }catch (Exception e){
                    logger.fatal("vps信息更新定时任务写文件失败");
                    e.printStackTrace();
                    throw e;
                }finally {
                    try {
                        ShellUtils.execShell("/home/ningyunfa1/vpsproject/rebootshadowsocks.sh");
                    }catch (Exception e){
                        logger.fatal("excute rebootshadowsocks.sh faild");
                    }
                }
                for(String id:vpsRecordIds){
                    VpsRecordForm vpsRecordForm = new VpsRecordForm();
                    vpsRecordForm.setId(id);
                    vpsRecordForm.setStatus("0");
                    Integer count = 0;
                    Boolean flag1 = true;
                    do {
                        Boolean result = true;
                        try {
                            result = vpsRecordeService.update(vpsRecordForm);
                        }catch (Exception e){
                            e.printStackTrace();
                            logger.fatal("vps信息更新定时任务定时更新vps状态更新数据库失败");
                        }
                        if(result){
                            logger.debug("数据更新成功");
                        }
                        flag1 = !result;
                        count++;
                    }while (flag1 && count<3);
                }
            }
        }else {
            logger.fatal("定时更新状态未查询到数据");
        }
    }
}
