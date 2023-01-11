package lab2.controller.filters;

import lab2.controller.util.Localization;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LanguageFilter implements Filter {
    private static final Logger logger = Logger.getLogger(LanguageFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("Language filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Localization.changeLocale(request);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("Language filter destroyed");
    }
}
