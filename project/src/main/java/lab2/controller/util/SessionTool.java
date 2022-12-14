package lab2.controller.util;

import lab2.model.entities.User;

import javax.servlet.http.HttpSession;

public class SessionTool {

    /**
     * Returns current User from HttpSession
     *
     * @param session - nullable HttpSession object
     * @return returns current User or null if the parameter is not set, or session is null
     */
    public static User getUser(HttpSession session) {
        return session == null ? null : (User) session.getAttribute("user");
    }

    private SessionTool() {
    }
}
