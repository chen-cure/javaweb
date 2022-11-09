package cn.tedu.web;

import cn.tedu.utiles.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/VerifyServlet")
public class VerifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //清楚缓存
        response.setIntHeader("Expires",-1);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");
        //生成验证码
        VerifyCode vc = new VerifyCode();
        //输出验证码
        vc.drawImage(response.getOutputStream());
        //将验证码保存到session中
        String code = vc.getCode();
        request.getSession().setAttribute("verify",code);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
