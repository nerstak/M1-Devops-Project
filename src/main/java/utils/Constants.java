package utils;

public class Constants {
    /* Pages */
    public static final String LOGIN_PAGE = "./WEB-INF/login.jsp";
    public static final String HOME_PAGE = "./WEB-INF/homepage.jsp";
    public static final String MISSION_PAGE = "./WEB-INF/mission-details.jsp";

    public static final String CONTROLLER_LOGIN = "Login";
    public static final String CONTROLLER_HOMEPAGE = "Homepage";
    public static final String CONTROLLER_LOGOUT = "Logout";
    public static final String CONTROLLER_DETAILS = "Details";

    /* Errors */
    public static final String ERR_INV_CRED_MESS = "Invalid credentials!";
    public static final String ERR_MISSING_FIELD = "At least one field is missing";
    public static final String ERR_FAILED_UPDATE_DB = "The database could not be updated, try again";
    public static final String ERR_EMPTY_FIELDS = "Fields can't be empty";
    public static final String ERR_DATE_AFTER = "The begin date can't be after end date";

    /*Information*/
    public static final String SUCCESS_BD = "The database has been updated";

}
