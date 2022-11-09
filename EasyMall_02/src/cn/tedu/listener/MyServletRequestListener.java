package cn.tedu.listener;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.Date;

@WebListener
public class MyServletRequestListener implements ServletRequestListener {
//    private static Logger logger = Logger.getLogger(MyServletRequestListener.class);
//    Logger.getLogger();
    private static Logger logger = Logger.getLogger(MyServletRequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        String addr = request.getRemoteAddr();
        StringBuffer url = request.getRequestURL();
        String time = new Date().toLocaleString();
        logger.debug("["+time+"]"+"用户:"+addr+"请求结束:"+url);
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        String addr = request.getRemoteAddr();
        StringBuffer url = request.getRequestURL();
        String time= new Date().toLocaleString();
        logger.debug("["+time+"]"+"用户:"+addr+"请求开始:"+url);

    }
}
