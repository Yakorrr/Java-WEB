package lab2.controller.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LogFilter implements Filter {
    private static final Logger logger = Logger.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("Log filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String servletPath = request.getServletPath();

        logger.info("ServletPath: " + servletPath + ", URL = " + request.getRequestURL());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("Log filter destroyed");
    }
}
