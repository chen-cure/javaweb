package cn.tedu.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(value = {"/servlet/*","*.jsp"})
public class EncodingFilter implements Filter {
    private ServletContext sc = null;
    public void init(FilterConfig config) throws ServletException {
        sc= config.getServletContext();
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String encoding = sc.getInitParameter("Encoding");
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset="+encoding);
        chain.doFilter(request, response);
    }
}
