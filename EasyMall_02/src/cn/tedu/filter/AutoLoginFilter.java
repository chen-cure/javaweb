package cn.tedu.filter;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;

@WebFilter("/*")
public class AutoLoginFilter implements Filter {
    private UserServiceImpl userService = new UserServiceImpl();
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //1.是否登录，只有未登录才做自动登录
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Cookie finc = null;
        if (session.getAttribute("user")==null){
            //2是否带了自动登录cookie,带了自动登录cookie才能做自动登录
            Cookie[] cookies = req.getCookies();
            if (cookies.length>0){
                for (Cookie c : cookies){
                    if ("autologin".equals(c.getName())){
                        finc=c;
                        break;
                    }
                }
            }
//            //3.检测自动登录cookie中的用户名和密码是否成功
//            if (finc!=null){
//                String message = URLDecoder.decode( finc.getValue(),"utf-8");
//                String username = message.split("#")[0];
//                String password = message.split("#")[1];
//                try {
//                    User user = userService.login(username, password);
//                    req.getSession().setAttribute("user",user);
//                } catch (MsgException e) {
//                    Cookie cookie = new Cookie("autologin","");
//                    cookie.setMaxAge(0);
//                    cookie.setPath(req.getContextPath()+"/");
//                    res.addCookie(cookie);
//                }
//            }
        }
        //3.检测自动登录cookie中的用户名和密码是否成功
        if (finc!=null){
            String message = URLDecoder.decode(finc.getValue(),"utf-8");
            String username = message.split("#")[0];
            String password = message.split("#")[1];


            try {
                //4.未登录，带了自动cookie，用户名密码正确则登录
                User user = userService.login(username, password);
                req.getSession().setAttribute("user",user);
            } catch (MsgException e) {
                Cookie cookie = new Cookie("autologin","");
                cookie.setMaxAge(0);
                cookie.setPath(req.getContextPath()+"/");
                res.addCookie(cookie);
            }
        }
        //5.无论是否自动登录，放行资源
        chain.doFilter(request, response);

    }

    public void destroy() {
    }
}
