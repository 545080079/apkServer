package com.lyt.apktool.apktool;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApktoolApplicationTests {

    //去掉代码中包含的回车符，处理单双引号
    public static String preProcess(String code)
    {
        code = code.replaceAll("\r\n","");
        code = code.replaceAll("\\'","\\\\'");
        code = code.replaceAll("\\{","\\\\{");
        code = code.replaceAll("\\}","\\\\}");
        return code;
    }



    public static void main(String[] args)
    {
        String str = "luo\r\ny{u}(t)'a'p";
        System.out.println(str);
         str= preProcess(str);
        System.out.println(str);
    }


    @Test
    void contextLoads() {
    }

}
