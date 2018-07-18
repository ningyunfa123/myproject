package com.baidu.mybaidu.utils;



import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {
    private static final Logger logger = Logger.getLogger(FileUtils.class);
    public static String getFileContents(String filePath) throws IOException {
        BufferedReader fileBuf = null;
        StringBuilder sb = new StringBuilder();
        String contents = null;
        try{
            fileBuf = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
            while((contents = fileBuf.readLine())!=null){
                contents=contents.trim();
                sb.append(contents);
            }
        }catch (IOException e){
            e.printStackTrace();
            throw e;
        }
        return sb.toString();
    }

}
