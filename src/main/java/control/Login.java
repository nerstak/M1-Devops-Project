package control;

import control.session_beans.TutorSessionBean;
import models.TutorEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static utils.Constants.*;

/**
 * Login controller, to handle connection
 */
@WebServlet(name = "Login")
public class Login extends ServletModel {
    @EJB
    private TutorSessionBean tutorSB;

    private static final String TUTOR_ATTRIBUTE = "tutor";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute(TUTOR_ATTRIBUTE) != null) {
            redirect(response,CONTROLLER_HOMEPAGE);
        } else {
            forward(request,response,LOGIN_PAGE);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Only for post request
        HttpSession session = request.getSession();

        if(session.getAttribute(TUTOR_ATTRIBUTE) != null) {
            redirect(response,CONTROLLER_HOMEPAGE);
            return;
        }

        String email = request.getParameter("login");
        String pwd = request.getParameter("pwd");

        if (email.isEmpty() || pwd.isEmpty()) {
            request.setAttribute("errorMessage", ERR_MISSING_FIELD);
            forward(request,response,LOGIN_PAGE); //redirect to welcome if error
            return;
        }

        ArrayList<TutorEntity> tutors = new ArrayList<>();
        tutors.addAll(tutorSB.getTutors(email, pwd));

        if (!tutors.isEmpty()) {
            session.setAttribute(TUTOR_ATTRIBUTE, tutors.get(0));

            redirect(response,CONTROLLER_HOMEPAGE);
        } else {
            request.setAttribute("errorMessage", ERR_INV_CRED_MESS);
            forward(request,response,LOGIN_PAGE);
        }
    }
}
