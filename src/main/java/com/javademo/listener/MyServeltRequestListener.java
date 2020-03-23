package com.javademo.listener;

import com.javademo.common.util.SessionUtil;
import com.javademo.entity.system.VisitUser;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * ServletRequestListener 监听器
 * @author shuyi
 * @date 2020/3/23
 */
@WebListener
public class MyServeltRequestListener implements ServletRequestListener {

    @SuppressWarnings("unchecked")
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("---------------------------->请求创建");
        ArrayList<VisitUser> userList=null;
        //获取全局变量中的链表，若不存在，则创建一个新的链表
        userList= (ArrayList<VisitUser>) sre.getServletContext().getAttribute("userlist");
        if (userList == null) {
            userList = new ArrayList<VisitUser>();
        }
        //获取request对象
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        //获取sessionId
        String sessionId = request.getSession().getId();
        //通过sessionId在链表中查找对象，若没有此对象，则创建加入到链表中
        if (SessionUtil.getUserBySessionID(userList, sessionId) == null) {
            VisitUser user = new VisitUser();
            user.setSessionId(sessionId);
            user.setFirstTime(new SimpleDateFormat("YYYY-MM-DD hh:mm:ss").format(new Date()));
            user.setIp(request.getRemoteAddr());
            userList.add(user);
        }
        //将链表设置为全局变量
        sre.getServletContext().setAttribute("userList", userList);
    }


    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

        System.out.println("---------------------------->请求销毁");
    }


}
