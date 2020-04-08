package com.javademo.controller;


import com.javademo.dao.ReportExcelDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @author shuyi
 * @date 2020/3/31
 */
@RequestMapping
@Controller
public class ReportFormController {

    @Autowired
    private ReportExcelDao reportExcelDao;

    /**
     * 导出报表
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        //获取数据
//        List<Map<String,Object>> list = reportExcelDao.selectComInfoList();
//
//        //excel标题
//        String[] title = {"审核id","企业id","企业名称","主营业务","企业性质编号","行业类型","认证状态","添加时间"};
//
//        //excel文件名
//        String fileName = "企业认证信息表"+System.currentTimeMillis()+".xls";
//
//        //sheet名
//        String sheetName = "企业认证信息表";

//        for (int i = 0; i < list.size(); i++) {
//            String content;
////            content[i] = new String[title.length];
////            Map<String,Object> obj = list.get(i);
////            content[i][0] = obj.get("authId");
////            content[i][1] = obj.get("companyId");
////            content[i][2] = obj.get("commpanyName");
////            content[i][3] = obj.get("business");
////            content[i][4] = obj.get("natureCode");
////            content[i][5] = obj.get("industryType");
////            content[i][6] = obj.get("authStatus");
////            content[i][7] = obj.get("addTime");
//       }

        //创建HSSFWorkbook
//        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title,content, null);

        //响应到客户端
//    try {
//        this.setResponseHeader(response, fileName);
//         OutputStream os = response.getOutputStream();
//            wb.write(os);
//              os.flush();
//              os.close();
//         } catch (Exception e) {
//              e.printStackTrace();
//        }
//    }

        //发送响应流方法
//    public void setResponseHeader(HttpServletResponse response, String fileName) {
//        try {
//            try {
//                fileName = new String(fileName.getBytes(),"ISO8859-1");
//            } catch (UnsupportedEncodingException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            response.setContentType("application/octet-stream;charset=ISO8859-1");
//            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
//            response.addHeader("Pargam", "no-cache");
//            response.addHeader("Cache-Control", "no-cache");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
    }
}

