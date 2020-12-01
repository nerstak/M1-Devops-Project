package control;

import control.session_beans.InternshipSessionBean;
import control.session_beans.KeywordsSessionBean;
import models.TutorEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static utils.Constants.CONTROLLER_LOGIN;
import static utils.Constants.HOME_PAGE;

/**
 * Homepage controller, displaying list of internships
 */
@WebServlet(name = "Homepage")
public class Homepage extends ServletModel {
    @EJB
    private KeywordsSessionBean keywordsSB;
    @EJB
    private InternshipSessionBean internshipsSB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        TutorEntity tutor = (TutorEntity) session.getAttribute("tutor");

        String year;
        String name;
        String keyword;

        if (tutor != null) {
            request.setAttribute("listOfKeywords", keywordsSB.getKeywords());


            year = request.getParameter("year");
            if (year == null) {
                year = "%";
            }

            name = request.getParameter("search-name");
            if (name == null) {
                name = "";
            }

            keyword = request.getParameter("keywords");
            if(keyword == null) {
                keyword = "-";
            }

            request.setAttribute("listOfInternship",internshipsSB.getInternshipData(tutor.getTutorId(), year, name, keyword));
            request.setAttribute("searchedYear", year);
            request.setAttribute("searchedKeyword", keyword);
            request.setAttribute("searchedName", name);

            forward(request,response,HOME_PAGE);
        } else {
            redirect(response,CONTROLLER_LOGIN);
        }
    }
}
