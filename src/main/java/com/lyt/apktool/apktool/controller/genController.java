package com.lyt.apktool.apktool.controller;

import com.lyt.apktool.apktool.utils.BashUtil;
import com.lyt.apktool.apktool.utils.download;
import com.lyt.apktool.apktool.utils.upload;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


@RestController
@RequestMapping("/")
public class genController {

    @RequestMapping(value = "/postApk", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public void postApk(HttpServletRequest request, HttpServletResponse response) throws IOException {


        //request流转换为String
        StringBuilder sb = new StringBuilder();
        ServletInputStream is = request.getInputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = is.read(buf)) != -1) {
            sb.append(new String(buf, 0, len));
        }
        is.close();

        //生成JavaFileX.java
        upload.genJavaFile(sb);

        //执行autoGen.sh合并java文件 + 打包成apk
        BashUtil bash = new BashUtil("./autoGen.sh");
        System.out.println(bash.run());

        //apk文件下载



        System.out.println("[Successful to received Text]\n " + sb.toString());

    }

    @RequestMapping(value = "/download",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public byte[] getApk() throws IOException {

        File file = new File("./apk_signed.apk");

        FileInputStream inputStream = new FileInputStream(file);

        byte[] bytes = new byte[inputStream.available()];

        inputStream.read(bytes, 0, inputStream.available());

        return bytes;

    }

    @GetMapping("/getApk/{id}")
    public byte[] getApk(@PathVariable(name = "id")String id, @RequestParam(name = "code") String code) throws IOException {

//        System.out.println("code = " + code);
//        int seq = 1;
//        code = code.substring(7);
//        String[] dataSet = code.split("package");
//        for(String s : dataSet)
//        {
//            s = "package " + s;
//
//            String filePath="./java" + (seq++) + ".java";
//            FileOutputStream fos = new FileOutputStream(filePath);
//            fos.write(s.getBytes());
//            fos.close();
//        }

        return code.getBytes();
    }





}
