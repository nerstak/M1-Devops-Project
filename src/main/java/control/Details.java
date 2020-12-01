package control;

import control.session_beans.*;
import models.*;
import utils.ProcessString;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import static utils.Constants.CONTROLLER_HOMEPAGE;
import static utils.Constants.MISSION_PAGE;

/**
 * Details controller, for any additional information on internship
 */
@WebServlet(name = "Details")
public class Details extends ServletModel {
    @EJB
    private InternshipSessionBean internshipsSB;
    @EJB
    private KeywordsSessionBean keywordsSB;
    @EJB
    private SkillsSessionBean skillsSB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Check from which submit button the request come from
        String internshipSubmit = request.getParameter("internshipSubmit");
        String internshipId = request.getParameter("internshipId");
        InternshipEntity internshipEntity;

        if (internshipSubmit.equals("details")) {
            internshipEntity = internshipsSB.find(UUID.fromString(internshipId));

            //Set request attributes
            request.setAttribute("internshipData", internshipEntity);
            request.setAttribute("listOfSkills", skillsSB.getSkills());
            request.setAttribute("listOfKeywords",keywordsSB.getKeywords());

            forward(request,response,MISSION_PAGE);
        } else if (internshipSubmit.equals("modify")) {
            internshipEntity = internshipsSB.find(UUID.fromString(internshipId));

            if(updateAllData(request, internshipEntity)) {
                internshipsSB.save(internshipEntity);
            }
            redirect(response,CONTROLLER_HOMEPAGE);
        } else {
            redirect(response,CONTROLLER_HOMEPAGE);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirect(response,CONTROLLER_HOMEPAGE);
    }

    /**
     * Update all data for one internship
     * @param request, http request object
     * @param internshipEntity internship
     * @return true if the db is updated
     */
    protected boolean updateAllData(HttpServletRequest request, InternshipEntity internshipEntity){
        //Check if update is done, if it not return false
        if (!updateInternship(request, internshipEntity)){
            return false;
        }

        if (!updateStudent(request, internshipEntity)){
            return false;
        }

        if (!updateMarks(request, internshipEntity)){
            return false;
        }

        updateVisit(request, internshipEntity);
        updateFinalReport(request, internshipEntity);

        return true;
    }

    /**
     * Update the student
     * @param request, http request object
     * @param internshipEntity internship
     * @return true if the db is updated
     */
    protected boolean updateStudent(HttpServletRequest request, InternshipEntity internshipEntity){
        //Student
        String studentGroup = request.getParameter("studentGroup");
        String studentFirstname = request.getParameter("studentFirstname");
        String studentName = request.getParameter("studentName");

        StudentEntity student = internshipEntity.getStudent();

        if(ProcessString.areStringEmpty(studentFirstname, studentGroup, studentName) || student == null){
            return false;
        }

        student.setStudentGroup(studentGroup);
        student.setFirstname(studentFirstname);
        student.setName(studentName);
        //studentSB.save(student);
        internshipEntity.setStudent(student);

        return true;
    }

    /**
     * Update the marks
     * @param request, http request object
     * @param internshipEntity internship
     * @return true if the db is updated
     */
    protected boolean updateMarks(HttpServletRequest request, InternshipEntity internshipEntity){
        //Marks
        String commMark = request.getParameter("commMark");
        String techMark = request.getParameter("techMark");

        if(ProcessString.areStringEmpty(commMark, techMark)){
            return false;
        }

        if((Integer.parseInt(commMark) < 0) || (Integer.parseInt(commMark) > 20)){
            return false;
        }

        if((Integer.parseInt(techMark) < 0) || (Integer.parseInt(techMark) > 20)){
            return false;
        }

        MarksEntity marks = internshipEntity.getMarks();
        marks.setCommunication(Integer.valueOf(commMark));
        marks.setTech(Integer.valueOf(techMark));

        internshipEntity.setMarks(marks);

        return true;
    }

    /**
     * Update the visit
     * @param request, http request object
     * @param internshipEntity internship
     */
    protected void updateVisit(HttpServletRequest request, InternshipEntity internshipEntity){
        //Visit
        boolean visitPlanned = request.getParameter("visitPlanned") != null;
        boolean visitDone = request.getParameter("visitDone") != null;
        VisitEntity visit = internshipEntity.getVisit();

        visit.setDone(visitDone);
        visit.setPlanned(visitPlanned);

        internshipEntity.setVisit(visit);
    }

    /**
     * Update the internship
     * @param request, http request object
     * @param internshipEntity internship
     * @return true if the db is updated
     */
    protected boolean updateInternship(HttpServletRequest request, InternshipEntity internshipEntity){
        //Internship
        String beginningDate = request.getParameter("beginningDate");
        String endDate = request.getParameter("endDate");
        String supervisor = request.getParameter("supervisor");
        boolean defense = request.getParameter("defense") != null;
        boolean webSurvey = request.getParameter("webSurvey") != null;
        boolean companyEval = request.getParameter("companyEval") != null;
        boolean cdc = request.getParameter("cdc") != null;

        //Check if all data are not empty and begin date is before end date
        if((ProcessString.areStringEmpty(beginningDate, endDate, supervisor)) || (ProcessString.isDateBefore(endDate, beginningDate))) {
            return false;
        }

        internshipEntity.setDefense(defense);
        internshipEntity.setCompanyEval(companyEval);
        internshipEntity.setWebSurvey(webSurvey);
        internshipEntity.setCdc(cdc);
        internshipEntity.setInternSupervisor(supervisor);
        internshipEntity.setBeginning(Date.valueOf(beginningDate));
        internshipEntity.setEnding(Date.valueOf(endDate));

        return true;
    }

    /**
     * Update the report table
     * @param request, http request
     * @param internshipEntity internship
     */
    protected void updateFinalReport(HttpServletRequest request, InternshipEntity internshipEntity){
        //Report
        boolean report = request.getParameter("releasedReport") != null;

        FinalReportEntity finalReport = internshipEntity.getFinalReport();
        finalReport.setReport(report);

        internshipEntity.setFinalReport(finalReport);
    }
}
