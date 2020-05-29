package com.javademo.common.util;

import com.javademo.common.exception.CommonException;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 返回文件流至浏览器
 * @author shuyi 工具类
 * @date 2020/5/13
 */
public class DownLoadFileUtil {


    /**
     * 返回文件流至前端进行下载
     * @param response
     * @param srcFilePath
     * @throws IllegalAccessException
     */

    public static void responseFileForLoad(HttpServletResponse response,String srcFilePath) throws IllegalAccessException {
        File file = checkFile(srcFilePath, false);
        responseFile(response,file,"application/octet-stream",true);

    }

    /**
     * 返回文件流至前端进行展示
     * @param response
     * @param srcFilePath
     * @param contentType
     * @throws IllegalAccessException
     */
    public static void responseFileForShow(HttpServletResponse response,String srcFilePath,String contentType) throws IllegalAccessException {
        File file=checkFile(srcFilePath,false);
        responseFile(response,file,contentType,false);
    }


    /**
     * 检查文件公共方法
     * @param filePath 文件路径
     * @param create 是否存在
     * @return
     * @throws IllegalAccessException
     */

    private static File checkFile(String filePath, boolean create) throws IllegalAccessException {
        File file=new File(filePath);
        if(!file.exists()){
            if(create){
                file.mkdirs();
            }else{
                throw new IllegalAccessException("文件不存在：{}！"+filePath);
            }
        }
        return file;
    }

    /**
     * 使用response返回文件流
     * @param response  进行流的返回
     * @param file  需要返回的文件
     * @param contentType contentType类型
     * @param isLoad  true时下载，false时展示
     */
    private static void responseFile(HttpServletResponse response,File file,String contentType,boolean isLoad){
        try{
            BufferedInputStream is=new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream os=new BufferedOutputStream(new FileOutputStream(file));
            response.setContentType(contentType);
            /**如果是下载，指定文件名称*/
            if(isLoad){
                String fileName=file.getName();
                /**指定字符集，解决文件乱码的问题*/
                response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()));
            }
            byte[]buffer=new byte[8192];
            int count=0;
            while((count=is.read(buffer,0,8192))!=-1){
                os.write(buffer,0,count);
            }
            os.flush();
        }catch (Exception e){
            throw new CommonException("文件流返回失败"+e);
        }
    }


}
