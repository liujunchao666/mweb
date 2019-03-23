package cn.restlibs.servlet;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter1 implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("创建 ");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        System.out.println("拦截前 ");
        chain.doFilter(request, response);        //放行，如果没有这句，客户端没有响应。
        System.out.println("拦截后 ");
    }

    public void destroy() {
        System.out.println("销毁 ");
    }

}
