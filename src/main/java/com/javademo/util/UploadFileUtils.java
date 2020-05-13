package com.javademo.util;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import javax.servlet.http.*;

import java.io.File;
import java.io.IOException;

import java.util.*;

/**
 * 文件上传 工具类
 * @author shuyi
 * @date 2020/5/13
 */
public class UploadFileUtils {
    /**
     *  获取请求中的文件并上传
     * @param httpServletRequest  请求对象
     * @param useNewName  boolean useNewName 是否使用原文件名称
     * @throws IOException
     */
    public static void uploadFile(HttpServletRequest httpServletRequest,boolean useNewName) throws IOException{
        CommonsMultipartResolver multipartResolver=
                new CommonsMultipartResolver(httpServletRequest.getServletContext());
        /**判断是否有文件上传*/
        if(multipartResolver.isMultipart(httpServletRequest)){
            /**向上转型获取更多功能*/
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)multipartResolver;
            Iterator<String> iter =multiRequest.getFileNames();
            while (iter.hasNext()){
                MultipartFile multipartFile=multiRequest.getFile(iter.next());
                if(multipartFile.isEmpty()){
                    System.out.println("文件未上传！");
                    continue;
                }
                uploadFileFromMulFile(multipartFile,httpServletRequest,useNewName);
            }
        }
    }


    public static void uploadFileFromMulFile(MultipartFile file,HttpServletRequest request,boolean useNewName) throws IOException{
        /**获取服务器项目发布运行所在地址*/
        String uploadDir =request.getSession().getServletContext().getRealPath("/")+"upload";
        String originalName=file.getOriginalFilename();
        /**使用时间戳解决文件重名的问题*/
        if(useNewName){
            long timestamp=System.currentTimeMillis();
            originalName=timestamp+"-"+originalName;
        }
        String destFilePath=uploadDir+ File.separator+originalName;
        file.transferTo(new File(destFilePath));

    }
}
