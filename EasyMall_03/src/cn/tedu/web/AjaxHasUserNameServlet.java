package cn.tedu.web;

import cn.tedu.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/servlet/AjaxHasUserNameServlet")
public class AjaxHasUserNameServlet extends HttpServlet {
    private UserServiceImpl service = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");

        String username =request.getParameter("username");
//        System.out.println(username);
        boolean hasUsername = service.hasUsername(username);
//        System.out.println(hasUsername);
        response.getWriter().write(hasUsername?"用户名已存在":"用户名可使用");
//        if (hasUsername){
//            response.getWriter().write("用户名可使用");
//            return;
//        }else{
//            response.getWriter().write("用户名不可用");
//            return;
//        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
