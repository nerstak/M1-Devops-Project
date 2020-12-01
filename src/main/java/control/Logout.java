package control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static utils.Constants.CONTROLLER_LOGIN;

/**
 * Logs out an user
 */
@WebServlet(name = "Logout")
public class Logout extends ServletModel {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    /**
     * Process the log out request, no matter what the method is
     * @param request Request
     * @param response Response
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("tutor");

        redirect(response, CONTROLLER_LOGIN);
    }
}
