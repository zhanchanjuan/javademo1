package com.javademo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业信息详情
 * @author shuyi
 * @date 2020/3/31
 */
@Data
public class CmsCompanyBasics implements Serializable {
  private static final long serialVersionUID = -3481354578230282243L;

  private Integer authId;
  private String companyId;

  private String companyName;

  private String business;
  private String describes;
  private String natureCode;
  private String industryType;

  private Integer status;

  private Integer authStatus;

  private Integer delFlag;
  private String authMsg;
  private Date addTime;
  private Date modTime;
  private String remarks;
}
