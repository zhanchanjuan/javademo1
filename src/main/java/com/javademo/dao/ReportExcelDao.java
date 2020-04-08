package com.javademo.dao;

import com.javademo.entity.CmsCompanyBasics;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author shuyi
 * @date 2020/3/31
 */
@Mapper
@Repository
public interface ReportExcelDao {
    /**
     * 查询企业信息详情--用于导出Excel
     * @return
     */
    List<Map<String,Object>> selectComInfoList();

}
