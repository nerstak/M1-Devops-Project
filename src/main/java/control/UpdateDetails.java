package control;


import control.session_beans.*;
import models.*;
import utils.ProcessString;

import javax.ejb.EJB;
import javax.persistence.EntityExistsException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import static utils.Constants.*;

public class UpdateDetails extends ServletModel{
    @EJB
    private InternshipSessionBean internshipsSB;
    @EJB
    private KeywordsSessionBean keywordsSB;
    @EJB
    private SkillsSessionBean skillsSB;
    @EJB
    private StudentSessionBean studentSB;
    @EJB
    private CommentsSessionBean commentsSB;
    @EJB
    private CompanySessionBean companySB;
    
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String INTERNSHIP_ATTRIBUTE = "internshipId";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get the name of the button that call the servlet
        String detailsSubmitButton = request.getParameter("updateDetails");
        String internshipId = request.getParameter(INTERNSHIP_ATTRIBUTE);
        InternshipEntity internshipEntity = internshipsSB.find(UUID.fromString(internshipId));
        boolean successRequest = false;
        switch (detailsSubmitButton){
            case "company":
                successRequest = updateCompany(request, internshipEntity);
                break;
            case "student":
                successRequest = updateStudent(request, internshipEntity);
                break;
            case "internship":
                successRequest = updateInternship(request,internshipEntity);
                break;
            case "keywords":
                successRequest = updateKeywords(request, internshipEntity);
                break;
            case "skills":
                successRequest = updateSkills(request, internshipEntity);
                break;
            default:
                break;
        }
        redirectToDetailsPage(request, response, internshipEntity, successRequest);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirect(response,CONTROLLER_HOMEPAGE);
    }

    /**
     * Get the information from the form and update the company and internship tables
     * @param request, servlet request
     * @param internshipEntity internship
     * @return true if the database has been updated
     */
    protected boolean updateCompany(HttpServletRequest request, InternshipEntity internshipEntity){
        //Get data from form
            //Company
        String companyName = request.getParameter("companyName");
        String companyId = request.getParameter("companyId");
        String companyAddress = request.getParameter("companyAddress");
            //Internship
        String internshipId = request.getParameter(INTERNSHIP_ATTRIBUTE);
        String begin = request.getParameter("begin");
        String end = request.getParameter("end");
        String mds = request.getParameter("mds");

        //Check if all data are not empty
        if (ProcessString.areStringEmpty(companyId, internshipId, begin, end, mds, companyAddress, companyName)){
            request.setAttribute(MESSAGE_ATTRIBUTE, ERR_EMPTY_FIELDS);
            return false;
        }

        //Check if all data are not empty
        if (ProcessString.isDateBefore(end, begin)){
            request.setAttribute(MESSAGE_ATTRIBUTE, ERR_DATE_AFTER);
            return false;
        }

        internshipEntity.setEnding(Date.valueOf(end));
        internshipEntity.setBeginning(Date.valueOf(begin));
        internshipEntity.setInternSupervisor(mds);
        CompanyEntity companyEntity = internshipEntity.getCompany();
        companyEntity.setAddress(companyAddress);
        companyEntity.setName(companyName);
        companySB.save(companyEntity);
        internshipsSB.save(internshipEntity);

        return true;
    }

    /**
     * Get the information from the form and update the student table
     * @param request, servlet request
     * @param internshipEntity internship
     * @return true if the database has been updated
     */
    protected boolean updateStudent(HttpServletRequest request, InternshipEntity internshipEntity){
        UUID studentId = UUID.fromString(request.getParameter("studentId"));
        String group = request.getParameter("group");
        String firstName =  request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String linkedin = request.getParameter("linkedin");
        String email = request.getParameter("email");

        StudentEntity student = internshipEntity.getStudent();
        student.setStudentGroup(group);
        student.setLinkedinProfile(linkedin);
        student.setFirstname(firstName);
        student.setName(lastName);
        student.setEmail(email);

        //Check if data are empty (expect linkedin url)
        if(ProcessString.areStringEmpty(studentId.toString(), firstName, lastName, email, group)){
            request.setAttribute(MESSAGE_ATTRIBUTE, ERR_EMPTY_FIELDS);
            return false;
        }
        studentSB.save(student);

        return true;
    }

    /**
     * Get the information from the form and update the student, comments and internship table
     * @param request, servlet request
     * @param internshipEntity internship
     * @return true if the database has been updated
     */
    protected boolean updateInternship(HttpServletRequest request, InternshipEntity internshipEntity){
        String description = request.getParameter("description");
        String tutorComments = request.getParameter("tutorComments");
        String studentComments = request.getParameter("studentComments");
        String commentsId = request.getParameter("commentsId");
        String internshipId = request.getParameter(INTERNSHIP_ATTRIBUTE);
        String titleId = request.getParameter("titleId");
        String title = request.getParameter("reportTitle");


        //Check if data(IDs) are empty
        if(ProcessString.areStringEmpty(titleId, commentsId, internshipId)){
            request.setAttribute(MESSAGE_ATTRIBUTE, ERR_EMPTY_FIELDS);
            return false;
        }

        internshipEntity.setDescription(description);
        internshipEntity.getFinalReport().setTitle(title);
        CommentsEntity comments = internshipEntity.getComments();
        comments.setStudentComm(studentComments);
        comments.setSupervisorComm(tutorComments);
        commentsSB.save(comments);
        internshipsSB.save(internshipEntity);

        return true;
    }

    /**
     * Get the information from the form and update the skill and student_to_skills table
     * @param request, servlet request
     * @param internshipEntity Internship entity
     * @return true if the database has been updated
     */
    protected boolean updateSkills(HttpServletRequest request, InternshipEntity internshipEntity)  {
        //Get the skill from the form
        String skill = request.getParameter("skill");

        //Check if skill is empty
        if (skill.isEmpty()){
            request.setAttribute(MESSAGE_ATTRIBUTE, ERR_EMPTY_FIELDS);
            return false;
        }

        //Capitalize the first letter
        skill = ProcessString.capitalizeAndLowerCase(skill);

        SkillsEntity skillsEntity = skillsSB.getSkillByName(skill);
        try {
            if (skillsEntity == null) {
                // Creating skill if not existing
                skillsEntity = new SkillsEntity();
                skillsEntity.setSkillId(UUID.randomUUID());
                skillsEntity.setSkill(skill);
                skillsSB.save(skillsEntity);
            }

            // Adding skill to student
            StudentEntity student = internshipEntity.getStudent();
            if(!student.getSkills().contains(skillsEntity)) {
                student.getSkills().add(skillsEntity);
                studentSB.save(student);
                return true;
            }
            return false;
        } catch (EntityExistsException e) {
            return false;
        }
    }

    /**
     * Get the information from the form and update the keyword and internship_to_keywords table
     * @param request, servlet request
     * @param internshipEntity Internship
     * @return true if the database has been updated
     */
    protected boolean updateKeywords(HttpServletRequest request, InternshipEntity internshipEntity){
        //Get the skill from the form
        String keyword = request.getParameter("keyword");

        //Check if skill is empty
        if (keyword.isEmpty()){
            request.setAttribute(MESSAGE_ATTRIBUTE, ERR_EMPTY_FIELDS);
            return false;
        }

        //Capitalize the first letter
        keyword = ProcessString.capitalizeAndLowerCase(keyword);

        KeywordsEntity keywordsEntity = keywordsSB.getKeywordByName(keyword);
        try {
            if (keywordsEntity == null) {
                // Creating skill if not existing
                keywordsEntity = new KeywordsEntity();
                keywordsEntity.setKeywordId(UUID.randomUUID());
                keywordsEntity.setKeyword(keyword);
                keywordsSB.save(keywordsEntity);
            }

            // Adding keyword to internship
            if(!internshipEntity.getListKeywords().contains(keywordsEntity)) {
                internshipEntity.getListKeywords().add(keywordsEntity);
                internshipsSB.save(internshipEntity);
                return true;
            }
            return false;
        } catch (EntityExistsException e) {
            return false;
        }
    }

    /**
     * Redirect to details jsp
     * @param request the request
     * @param response response
     * @param internshipEntity Concerned internship
     * @param successRequest Success of request
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void redirectToDetailsPage(HttpServletRequest request, HttpServletResponse response, InternshipEntity internshipEntity, boolean successRequest) {
        if (successRequest){
            request.setAttribute(MESSAGE_ATTRIBUTE, SUCCESS_BD);
        }else{
            if(request.getAttribute(MESSAGE_ATTRIBUTE) == null){
                request.setAttribute(MESSAGE_ATTRIBUTE, ERR_FAILED_UPDATE_DB);
            }
        }

        // We force the update of values
        internshipEntity = internshipsSB.find(internshipEntity.getInternshipId());

        //Set request attributes
        request.setAttribute("internshipData", internshipEntity);
        request.setAttribute("listOfSkills", skillsSB.getSkills());
        request.setAttribute("listOfKeywords",keywordsSB.getKeywords());

        forward(request,response,MISSION_PAGE);
    }
}
