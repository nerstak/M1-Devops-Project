package control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Basis of any Controller, as it creates the connection with the database
 */
@WebServlet(name = "ServletModel")
public abstract class ServletModel extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    /**
     * Redirect to a controller
     * @param response Response
     * @param page Address
     */
    protected void redirect(HttpServletResponse response, String page) {
        try {
            response.sendRedirect(page);
        } catch (IOException e) {
            Logger.getLogger(ServletModel.class.getName()).log(Level.SEVERE, "Redirection failed to " + page + ".", e);
        }
    }

    /**
     * Forward to a page
     * @param request Request
     * @param response Response
     * @param page Address
     */
    protected void forward(HttpServletRequest request, HttpServletResponse response, String page) {
        try {
            request.getRequestDispatcher(page).forward(request,response);
        }  catch (IOException | ServletException e) {
            Logger.getLogger(ServletModel.class.getName()).log(Level.SEVERE, "Redirection failed to " + page + ".", e);
        }
    }
}
