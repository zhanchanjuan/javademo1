package com.javademo.service.impl;

import com.javademo.service.DownLoadFileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author shuyi
 * @date 2020/5/13
 */
public class DownLoadFileServiceImpl  implements DownLoadFileService {
//    @Override
//    public void doGet(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws ServletException, IOException {
//        String fileName=httpServletRequest.getParameter("filename");
//        fileName =new String(fileName.getBytes("iso8859-1"),"UTF-8");
//    }
//
//    @Override
//    public void doGet(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
//
//    }

//    @Override
//    public void doPost() {
//
//    }

    /**
     * 对文件名称做处理
     * @param fileName 文件名称
     * @param fileSaveRootPath 文件根路径
     * @return
     */
    public String findFilePathByFileName(String fileName,String fileSaveRootPath){
        //这里运用hash算法打散存储文件
        int hashCode=fileName.hashCode();
            //得到名为1-16的下级文件夹
        int dir1=hashCode&0xf;
            //得到名为1到16的下下级文件夹
        int dir2=(hashCode*0xf0)>>4;
            //得到文件名
        String dir =fileSaveRootPath+"\\"+dir1+"\\"+dir2;
        File file=new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        return dir;
    }


}
