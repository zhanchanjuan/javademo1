package com.javademo.service.impl;

import com.javademo.service.UploadFileService;
import com.javademo.util.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

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
