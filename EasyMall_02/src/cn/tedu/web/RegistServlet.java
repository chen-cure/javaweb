package cn.tedu.web;

import cn.tedu.domain.User;
import cn.tedu.service.UserServiceImpl;
import cn.tedu.utiles.MD5Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/servlet/RegistServlet")

public class RegistServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //乱码
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
        //获取数据
        String username = request.getParameter("username".trim());
        String password=request.getParameter("password");
        String password2 = request.getParameter("password2");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String valistr = request.getParameter("valistr");
        //校验数据
        String verify = (String) request.getSession().getAttribute("verify");
        if (verify==null || "".equals(verify) || valistr==null || "".equals(valistr)|| !valistr.equals(verify)){
            request.setAttribute("msg","验证码不正确");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }else{
            request.getSession().invalidate();
        }
        if (username==null||username.equals("")){
            request.setAttribute("msg","用户名不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if (password==null||"".equals(password)){
            request.setAttribute("msg","密码不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if (password2==null||"".equals(password2)){
            request.setAttribute("msg","确认密码不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if (nickname==null||"".equals(nickname)){
            request.setAttribute("msg","昵称不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if (email==null||"".equals(email)){
            request.setAttribute("msg","邮箱不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if (!password.equals(password2)){
            request.setAttribute("msg","密码不一致");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if (!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
            request.setAttribute("msg","邮箱格式不正确");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        try{
            userService.regist(new User(0,username, MD5Utils.md5(password),nickname,email));
        }catch (Exception e){
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        response.getWriter().write("恭喜注册成功，3秒后跳转首页");
        response.setHeader("refresh","3;url="+request.getContextPath()+"/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
