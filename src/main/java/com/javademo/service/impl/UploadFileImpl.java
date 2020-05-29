package com.javademo.service.impl;

import com.javademo.service.UploadFileService;
import com.javademo.common.util.UploadFileUtils;


import java.io.File;

/**
 * @author shuyi
 * @date 2020/5/13
 */
public class UploadFileImpl implements UploadFileService{

    @Override
    public void uploadFile() {
        File file=new File("E:\\upload\\1.jap");
        UploadFileUtils uploadFileUtils=new UploadFileUtils();
//        HttpServletRequest httpServletRequest=new HttpServletRequest(){}
//        uploadFileUtils.uploadFile(httpServletRequest,true);
    }
}
