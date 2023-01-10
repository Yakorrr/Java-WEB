package lab2.controller.filters;

import com.sun.istack.internal.NotNull;
import lab2.model.enums.Role;
import lab2.model.entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static lab2.controller.util.Paths.ACCESS_ERROR_PAGE;
import static lab2.controller.util.Paths.GENERAL_ERROR;
import static lab2.controller.util.SessionTool.getUser;

@WebFilter("/*")
public class AccessFilter implements Filter {
    private static final List<String> adminPages = Arrays.asList("/admin", "/admin-users", "/admin-tables",
            "/admin-update", "/admin-users-update", "/approve", "/admin-update-rooms", "/logout");

    private static final List<String> userPages = Arrays.asList("/user-main",
            "/user-my-bills", "/new-request", "/user-my-requests", "/logout");

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("Access filter initialized");
    }

    private static boolean requiresAdminRights(@NotNull String servletPath) {
        return adminPages.stream().filter(servletPath::equals).count() == 1;
    }

    private static boolean requiresUserRights(@NotNull String servletPath) {
        return userPages.stream().filter(servletPath::equals).count() == 1;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getServletPath();
        User user = getUser(request.getSession());
        if (!requiresUserRights(servletPath) && !requiresAdminRights(servletPath)) {
            if (user == null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (user.getRole() == Role.USER) {
                request.getRequestDispatcher("templates/user/user-main.jsp").forward(request, response);
            } else if (user.getRole() == Role.ADMIN) {
                request.getRequestDispatcher("admin").forward(request, response);
            } else {
                request.getRequestDispatcher(GENERAL_ERROR.getUrl()).forward(request, response);
            }
            return;
        } else if (user != null && isAuthorized(servletPath, user)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        System.out.println("No rights: redirecting back");
        request.getRequestDispatcher(ACCESS_ERROR_PAGE.getUrl()).forward(request, response);
    }

    private boolean isAuthorized(@NotNull String servletPath, @NotNull User user) {
        return ((requiresAdminRights(servletPath)) && (user.getRole() == Role.ADMIN)) ||
                ((requiresUserRights(servletPath)) && (user.getRole() == Role.USER));
    }

    @Override
    public void destroy() {
        System.out.println("Access filter destroyed");
    }
}
