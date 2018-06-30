package solidPrinciples.L.liskovSubstitution.coreJavaExample;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterExample implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Casting is also a Liskov violation
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.addCookie(new Cookie("name", "value"));
        httpServletRequest.getHeaderNames().asIterator().forEachRemaining(System.out::println);

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//Also violates interface segregation
    }

    @Override
    public void destroy() {
//And more interface segregation violation!
    }
}
