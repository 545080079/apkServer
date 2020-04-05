package com.lyt.apktool.apktool.utils;

import java.io.FileOutputStream;
import java.io.IOException;

public class upload {


    //代码转化为.java
    public static void genJavaFile(StringBuilder sb) throws IOException {

        int seq = 1;
        String code = sb.toString();

        //以package作为文件分割符
        code = code.substring(7, code.length());
        String[] dataSet = code.split("package");
        for(String s : dataSet)
        {
            StringBuilder tsb = new StringBuilder("package");
            tsb.append(s);

            String filePath="./javaFile" + (seq++) + ".java";
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(tsb.toString().getBytes());
            fos.close();
        }

    }
}
