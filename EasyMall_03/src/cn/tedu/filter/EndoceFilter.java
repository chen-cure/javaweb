package cn.tedu.filter;

import cn.tedu.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter({"/servlet/*","*.jsp"})
public class EndoceFilter implements Filter {
    private ServletContext sc = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        sc=filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String endoce = sc.getInitParameter("Endoce");
        servletRequest.setCharacterEncoding(endoce);
        servletResponse.setContentType("text/html;charset="+endoce);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
