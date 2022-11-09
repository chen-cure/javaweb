package cn.tedu.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/servlet/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        Cookie cookie = new Cookie("autologin","");
        cookie.setMaxAge(0);
        cookie.setPath(request.getContextPath()+"/");
        response.addCookie(cookie);
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
