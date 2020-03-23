package com.javademo.common.util;

import com.javademo.entity.system.VisitUser;

import java.util.ArrayList;

/**
 * 工具类，通过session在链表中找到相应的用户对象
 * @author shuyi
 * @date 2020/3/23
 */
public class SessionUtil {

    public static VisitUser getUserBySessionID(ArrayList<VisitUser> userlist, String sessionId) {
        for (int i = 0; i < userlist.size(); i++) {
            VisitUser user=userlist.get(i);
            if (user.getSessionId().equals(sessionId)){
                return user;
            }
        }
        return null;
    }

}
