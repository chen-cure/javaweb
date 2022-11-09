package cn.tedu.web;

import cn.tedu.utiles.VerifyCode;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/servlet/VerifyServlet")
public class VerifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //清楚缓存
        response.setIntHeader("Expires",-1);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");
        //解决乱码问题
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
        //生成验证码，并把验证码保存到session中
        VerifyCode code = new VerifyCode();
        code.drawImage(response.getOutputStream());
        String code1 = code.getCode();
        request.getSession().setAttribute("verify",code1);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
