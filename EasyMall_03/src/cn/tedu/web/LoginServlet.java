package cn.tedu.web;

import cn.tedu.domain.User;
import cn.tedu.filter.MD5Utils;
import cn.tedu.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/servlet/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserServiceImpl service = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
        String username =request.getParameter("username");
        String password = request.getParameter("password");

        //使用MD5加密密码
        password = MD5Utils.md5(password);

        if("true".equals(request.getParameter("remname"))){
            Cookie cookie = new Cookie("remname", URLEncoder.encode(username,"utf-8"));
            cookie.setPath(request.getContextPath()+"/");
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);
        }else{
            Cookie cookie = new Cookie("remname","");
            cookie.setPath(request.getContextPath()+"/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        if ("true".equals(request.getParameter("autologin"))){
            Cookie cookie = new Cookie("autologin",URLEncoder.encode(username+"#"+password,"utf-8"));
            cookie.setPath(request.getContextPath()+"/");
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);
        }

        User user = service.login(username,password);
        if (user==null){
            request.setAttribute("msg","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }else{
            request.getSession().setAttribute("user",user);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
