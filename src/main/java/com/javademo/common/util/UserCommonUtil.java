package com.javademo.common.util;


import com.javademo.common.constants.UserLoginType;
import com.javademo.common.exception.CommonException;

/**
 * @author cj
 * @date 2020/1/9
 * 用户中心通用工具类
 */
public class UserCommonUtil {

    /**
     * 生成平台用户名
     *
     * @return
     */
    public static String generateUserName(String userName, String type) {
        String prefix = null;
        if (UserLoginType.USER_LOGIN_TYPE_QQ.toString().equals(type)) {
            prefix = "qq";
        } else if (UserLoginType.USER_LOGIN_TYPE_WECHAT.toString().equals(type)) {
            prefix = "weixin";
        } else if (UserLoginType.USER_LOGIN_TYPE_SINA_BLOG.toString().equals(type)) {
            prefix = "sinablog";
        } else if (UserLoginType.USER_LOGIN_TYPE_WECHAT_PUBLIC_WEB.toString().equals(type)) {
            prefix = "gzh_web";
        } else {
            throw new CommonException("类型不匹配");
        }
        int num = (int) (Math.random() * 100000000);
        while (num < 10000000) {
            num = (int) (Math.random() * 100000000);
        }
        String newUserName = prefix + "_" + num;
        return newUserName;
    }

    public static void main(String[] args) {
        // System.out.println(generateUserName(UserLoginType.USER_LOGIN_TYPE_QQ.toString()));
    }

    public static String generateUserName(String type) {
        return generateUserName(null, type);
    }

}
