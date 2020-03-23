package com.javademo.listener;

import com.javademo.common.util.SessionUtil;
import com.javademo.entity.system.VisitUser;



import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;

/**
 * 定义事件监听器
 * HttpSessionListener 监听器
 * @author shuyi
 * @date 2020/3/23
 */
@WebListener
public class UserHttpSessionListener implements HttpSessionListener {
    private int count=0;

    private void sessionCreate(HttpSessionEvent httpSessionEvent){
        count++;
        httpSessionEvent.getSession().getServletContext().setAttribute("count",count);
    }

    @SuppressWarnings("uncreate")
    private void sessionDestroy(HttpSessionEvent httpSessionEvent){
        count--;
        httpSessionEvent.getSession().getServletContext().setAttribute("count",count);
        ArrayList<VisitUser> userList=(ArrayList<VisitUser>)httpSessionEvent.getSession().getServletContext().getAttribute("userList");
        //如果用户退出了，但是session还没过期，就手动删除session中userList对应的user
        ////在此用户被销毁的时候，将链表中对应的用户对象删除
        if (SessionUtil.getUserBySessionID(userList, httpSessionEvent.getSession().getId()) != null) {
            userList.remove(SessionUtil.getUserBySessionID(userList, httpSessionEvent.getSession().getId()));
        }

        //将userList集合  重新保存到servletContext
        httpSessionEvent.getSession().getServletContext().setAttribute("userList", userList);

    }
}
