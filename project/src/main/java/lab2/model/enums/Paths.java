package lab2.model.enums;

public enum Paths {
    ACCESS_ERROR_PAGE("templates/no-rights.jsp"),
    GENERAL_ERROR("templates/general-error.jsp"),
    LOGIN("templates/login.jsp"),
    REGISTER("templates/register.jsp"),
    APPROVE_ROOM_REQ("templates/admin/approve-request.jsp"),
    ADMIN_MAIN("templates/admin/success_admin.jsp"),
    USER_MAIN("templates/user/user-main.jsp");

    private final String url;

    Paths(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
