package com.baidu.mybaidu.utils;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DataTransferUtils {

    private static final Logger logger = Logger.getLogger(DataTransferUtils.class);

    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonToMap(String jsonString) {
        Gson gson = new Gson();
        Map<String, Object> map;
        try {
            map = gson.fromJson(jsonString, HashMap.class);
        } catch (Exception e) {
            logger.error("json转map失败");
            throw e;
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> beanToMap(Object object, Boolean isExcludeNullValue) {
        Map<String, String> map = new HashMap();
        Class<?> clazz = object.getClass();
        for (Class currentClazz = clazz; currentClazz != Object.class; currentClazz = currentClazz.getSuperclass()) {
            Field[] fields = currentClazz.getDeclaredFields();
            try {
                for (Field field : fields) {
                    field.setAccessible(true);

                    Method method;
                    try {
                        method = currentClazz.getMethod("get" + CommonUtils.toUpperFirstChar(field.getName()));
                    } catch (NoSuchMethodException var12) {
                        continue;
                    } catch (Exception var13) {
                        var13.printStackTrace();
                        return Maps.newHashMap();
                    }
                    Object value = method.invoke(object);
                    if (!isExcludeNullValue || value != null) {
                        map.put(field.getName(), String.valueOf(value));
                    }
                }
            } catch (Exception var14) {
                var14.printStackTrace();
                return Maps.newHashMap();
            }
        }

        return map;
    }

    @SuppressWarnings("unchecked")
    public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        return (T) JSONObject.toBean(jsonObject, beanCalss);


    }

    public static String beanToJson(Object bean) {

        JSONObject json = JSONObject.fromObject(bean);

        return json.toString();


    }
}
