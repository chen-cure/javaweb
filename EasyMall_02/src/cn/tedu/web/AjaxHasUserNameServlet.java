package cn.tedu.web;

import cn.tedu.service.UserServiceImpl;
import cn.tedu.utiles.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/servlet/AjaxHasUserNameServlet")
public class AjaxHasUserNameServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决乱码问题
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");   //使用全栈乱码解决
        //获取请求参数
        String username = request.getParameter("username");
        System.out.println(username);
        //检查用户名是否存在
        boolean hasUsername = userService.hasUername(username);
        response.getWriter().write(hasUsername?"用户名已存在":"用户名可使用");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
