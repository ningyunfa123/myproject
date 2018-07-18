package com.baidu.mybaidu.schedule;

import com.baidu.mybaidu.dto.VpsRecordForm;
import com.baidu.mybaidu.service.VPSRecordeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Component
public class VpsMonitor {

    private static final Logger logger = Logger.getLogger(VpsMonitor.class);

    @Autowired
    VPSRecordeService vpsRecordeService;
    @Scheduled(cron = "0 0 3 * * ?")
    public void work(){
        List<Map<String,Object>> vpsRecordList = vpsRecordeService.getByConds(null,null,null,null);
        logger.error("开始执行");
        if(!CollectionUtils.isEmpty(vpsRecordList)){
            logger.error("数据不为空");
            for(Map<String,Object> vpsRecord:vpsRecordList){
                Integer useTime = (Integer) vpsRecord.get("use_time");
                Long transUseTime = Long.valueOf(useTime)*3600000*24;
                Long createTime = (Long) vpsRecord.get("update_time");
                if(System.currentTimeMillis()>(transUseTime+createTime)){
                    VpsRecordForm vpsRecordForm = new VpsRecordForm();
                    vpsRecordForm.setId(vpsRecord.get("id").toString());
                    vpsRecordForm.setStatus("2");
                    Integer count = 0;
                    Boolean flag = true;
                    do {
                        Boolean result = vpsRecordeService.update(vpsRecordForm);
                        if(result){
                            logger.debug("数据更新成功");
                        }
                        flag = !result;
                        count+=1;
                    }while (flag && count<3);

                }
            }
        }
    }
}
