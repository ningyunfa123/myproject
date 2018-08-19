package com.baidu.mybaidu.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ShellUtils {
    /**
     * 执行shell
     * @param execCmd 传入脚本或者命令
     * @param para 传入参数
     */
    private static void execShell1(boolean execCmd, String... para) {
        StringBuffer paras = new StringBuffer();
        Arrays.stream(para).forEach(x -> paras.append(x).append(" "));
        try {
            String cmd = "", shpath = "";
            if (execCmd) {
                // 命令模式
                shpath = "echo";
            } else {
                shpath = "/Users/yangyibo/Desktop/callShell.sh";

            }
            cmd = shpath + " " + paras.toString();
            Process ps = Runtime.getRuntime().exec(cmd);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解决了 参数中包含 空格和脚本没有执行权限的问题
     * @param scriptPath 脚本路径
     * @param para 参数数组
     */
    public static void execShell(String scriptPath, String ... para) throws Exception {
        try {
            if(System.getProperty("os.name").toLowerCase().startsWith("win")){
                throw new Exception("操作系统非linux");
            }
            StringBuilder scriptPathBuilder = new StringBuilder(scriptPath);
            if(para!=null &&para.length>0) {
                Arrays.stream(para).forEach(aPara -> scriptPathBuilder.append(" ").append(aPara));
            }
            String[] cmd = new String[]{"/bin/sh","-c",scriptPathBuilder.toString()};
            //解决脚本没有执行权限
            /*
            ProcessBuilder builder = new ProcessBuilder("/bin/chmod", "755",scriptPath);
            Process process = builder.start();
            process.waitFor();
            */
            Process ps = Runtime.getRuntime().exec(cmd,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
