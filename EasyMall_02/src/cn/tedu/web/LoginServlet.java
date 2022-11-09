package cn.tedu.web;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.service.UserServiceImpl;
import cn.tedu.utiles.MD5Utils;
import sun.security.provider.MD5;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/servlet/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");

        String username=request.getParameter("username");
        String password=request.getParameter("password");

        if("true".equals(request.getParameter("remname"))){
            Cookie cookie = new Cookie("remname", URLEncoder.encode(username,"utf-8"));
            cookie.setMaxAge(60*60*24);
            cookie.setPath(request.getContextPath()+"/login.jsp");
            response.addCookie(cookie);
        }else{
            Cookie cookie = new Cookie("remname", "");
            cookie.setMaxAge(0);
            cookie.setPath(request.getContextPath()+"/login.jsp");
            response.addCookie(cookie);
        }

        try{
            User user = userService.login(username, MD5Utils.md5(password));
            request.getSession().setAttribute("user",user);

            //处理30天自动登录
            if("true".equals(request.getParameter("autologin"))){
                Cookie cookie = new Cookie("autologin",URLEncoder.encode(username+"#"+MD5Utils.md5(password),"utf-8"));
                cookie.setMaxAge(60*60*24*30);
                cookie.setPath(request.getContextPath()+"/");
                response.addCookie(cookie);
            }
        }catch (MsgException e){
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        response.sendRedirect(request.getContextPath()+"/index.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
