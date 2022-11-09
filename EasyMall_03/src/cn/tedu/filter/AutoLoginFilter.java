package cn.tedu.filter;

import cn.tedu.domain.User;
import cn.tedu.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

@WebFilter("/*")
public class AutoLoginFilter implements Filter {
    private UserServiceImpl service =new UserServiceImpl();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.是否登录，只有未登录才做自动登录
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Cookie finc = null;
        if (session.getAttribute("user")==null){
            Cookie[] cookies = request.getCookies();
            if (cookies.length>0){
                for(Cookie c : cookies){
                    if("autologin".equals(c.getName())){
                        finc=c;
                        break;
                    }
                }
            }
        }
        //2是否带了自动登录cookie,带了自动登录cookie才能做自动登录
        if (finc!=null){
        //3.检测自动登录cookie中的用户名和密码是否成功
//            String username = finc.getValue().split("#")[0];
//            String password = finc.getValue().split("#")[1];
            String message = URLDecoder.decode(finc.getValue(),"utf-8");
            String username = message.split("#")[0];
            String password = message.split("#")[1];
            try{
                User user = service.login(username,password);
        //4.未登录，带了自动cookie，用户名密码正确则登录
                request.getSession().setAttribute("user",user);
            }catch (Exception e){
                Cookie cookie = new Cookie("autologin","");
                cookie.setMaxAge(0);
                cookie.setPath(request.getContextPath()+"/");
                response.addCookie(cookie);
            }
        }
        //5.无论是否自动登录，放行资源
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}

