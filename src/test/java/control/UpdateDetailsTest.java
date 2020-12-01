package control;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import control.session_beans.*;
import models.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static utils.Constants.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateDetailsTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    InternshipSessionBean internshipsSB;

    @Mock
    KeywordsSessionBean keywordsSB;

    @Mock
    SkillsSessionBean skillsSB;

    @Mock
    StudentSessionBean studentSB;

    @Mock
    CommentsSessionBean commentsSB;

    @Mock
    CompanySessionBean companySB;

    @Mock
    InternshipEntity internshipEntity;

    @Mock
    CompanyEntity companyEntity;

    @Mock
    StudentEntity studentEntity;

    @Mock
    CommentsEntity commentsEntity;

    @Mock
    SkillsEntity skillsEntity;

    @Mock
    KeywordsEntity keywordsEntity;

    @Mock
    FinalReportEntity finalReportEntity;

    @Mock
    List<SkillsEntity> skillsList;

    @Mock
    List<KeywordsEntity> keywordsList;

    @InjectMocks
    UpdateDetails updateDetails;

    @Test
    public void doGetTest() throws ServletException, IOException {
        //Given

        //When
        updateDetails.doGet(request, response);

        //Then
        then(response).should().sendRedirect(CONTROLLER_HOMEPAGE);
    }

    @Test //This test is here to test the overrall flow of a doPost request when it is successful, functions are tested independently afterwards
    public void doPostSuccessRequestTest() throws ServletException, IOException {
        //Given
        String updateButtonDetails = "skills";
        given(request.getParameter("updateDetails")).willReturn(updateButtonDetails);
        String internshipId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        UUID UUIDinternshipId = UUID.fromString(internshipId);
        given(request.getParameter("internshipId")).willReturn(internshipId);
        given(internshipsSB.find(UUIDinternshipId)).willReturn(internshipEntity);

        String skill = "Mockito master"; //This must be in this format: "Xxxxx xxx xx"
        given(request.getParameter("skill")).willReturn(skill);
        given(skillsSB.getSkillByName(skill)).willReturn(skillsEntity);
        given(internshipEntity.getStudent()).willReturn(studentEntity);
        given(studentEntity.getSkills()).willReturn(skillsList);

        given(internshipEntity.getInternshipId()).willReturn(UUIDinternshipId);
        given(internshipsSB.find(UUIDinternshipId)).willReturn(internshipEntity);
        given(request.getRequestDispatcher(MISSION_PAGE)).willReturn(requestDispatcher);

        //When
        updateDetails.doPost(request, response);

        //Then
        then(skillsList).should().contains(skillsEntity);
        then(skillsList).should().add(skillsEntity);
        then(studentSB).should().save(studentEntity);

        then(request).should().setAttribute("message", SUCCESS_BD);
        then(request).should().setAttribute("internshipData", internshipEntity);
        then(request).should().setAttribute("listOfSkills", skillsSB.getSkills());
        then(request).should().setAttribute("listOfKeywords",keywordsSB.getKeywords());
        then(requestDispatcher).should().forward(request, response);
    }

    @Test
    public void updateCompanySuccessTest() {
        //Given
        String companyName = "Framasoft";
        given(request.getParameter("companyName")).willReturn(companyName);
        String companyId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        given(request.getParameter("companyId")).willReturn(companyId);
        String companyAddress = "12 rue des paquerettes Paris";
        given(request.getParameter("companyAddress")).willReturn(companyAddress);
        String internshipId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        given(request.getParameter("internshipId")).willReturn(internshipId);
        String begin = "2020-10-10";
        given(request.getParameter("begin")).willReturn(begin);
        String end = "2020-11-11";
        given(request.getParameter("end")).willReturn(end);
        String mds = "Jean Jacques";
        given(request.getParameter("mds")).willReturn(mds);
        given(internshipEntity.getCompany()).willReturn(companyEntity);

        //When
        boolean result = updateDetails.updateCompany(request, internshipEntity);

        //Then
        then(internshipEntity).should().setEnding(Date.valueOf(end));
        then(internshipEntity).should().setBeginning(Date.valueOf(begin));
        then(internshipEntity).should().setInternSupervisor(mds);
        then(companyEntity).should().setAddress(companyAddress);
        then(companyEntity).should().setName(companyName);
        then(companySB).should().save(companyEntity);
        then(internshipsSB).should().save(internshipEntity);
    }

    @Test
    public void updateStudentSuccessTest() {
        //Given
        String studentId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        given(request.getParameter("studentId")).willReturn(studentId);
        String group = "M1";
        given(request.getParameter("group")).willReturn(group);
        String firstName = "Elon";
        given(request.getParameter("firstName")).willReturn(firstName);
        String lastName = "Musk";
        given(request.getParameter("lastName")).willReturn(lastName);
        String linkedin = "ElonMusk";
        given(request.getParameter("linkedin")).willReturn(linkedin);
        String email = "elon@musk.com";
        given(request.getParameter("email")).willReturn(email);

        given(internshipEntity.getStudent()).willReturn(studentEntity);

        //When
        boolean result = updateDetails.updateStudent(request, internshipEntity);

        //Then
        then(studentEntity).should().setStudentGroup(group);
        then(studentEntity).should().setLinkedinProfile(linkedin);
        then(studentEntity).should().setFirstname(firstName);
        then(studentEntity).should().setName(lastName);
        then(studentEntity).should().setEmail(email);
        then(studentSB).should().save(studentEntity);
    }

    @Test
    public void updateInternshipSuccessTest() {
        //Given
        String description = "this is a description";
        given(request.getParameter("description")).willReturn(description);
        String tutorComments = "this a the tutor's comments";
        given(request.getParameter("tutorComments")).willReturn(tutorComments);
        String studentComments = "this a the student's comments";
        given(request.getParameter("studentComments")).willReturn(studentComments);
        String commentsId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        given(request.getParameter("commentsId")).willReturn(commentsId);
        String internshipId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        given(request.getParameter("internshipId")).willReturn(internshipId);
        String titleId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        given(request.getParameter("titleId")).willReturn(titleId);
        String reportTitle = "Title";
        given(request.getParameter("reportTitle")).willReturn(reportTitle);
        given(internshipEntity.getComments()).willReturn(commentsEntity);
        given(internshipEntity.getFinalReport()).willReturn(finalReportEntity);

        //When
        boolean result = updateDetails.updateInternship(request, internshipEntity);

        //Then
        then(internshipEntity).should().setDescription(description);
        then(finalReportEntity).should().setTitle(reportTitle);
        then(commentsEntity).should().setStudentComm(studentComments);
        then(commentsEntity).should().setSupervisorComm(tutorComments);
        then(commentsSB).should().save(commentsEntity);
        then(internshipsSB).should().save(internshipEntity);
    }

    @Test
    public void updateSkillsSuccessTest() {
        //Given
        String skill = "Mockito master"; //This must be in this format: "Xxxxx xxx xx"
        given(request.getParameter("skill")).willReturn(skill);
        given(skillsSB.getSkillByName(skill)).willReturn(skillsEntity);
        given(internshipEntity.getStudent()).willReturn(studentEntity);
        given(studentEntity.getSkills()).willReturn(skillsList);

        //When
        boolean result = updateDetails.updateSkills(request, internshipEntity);

        //Then
        then(skillsList).should().contains(skillsEntity);
        then(skillsList).should().add(skillsEntity);
        then(studentSB).should().save(studentEntity);
    }

    @Test
    public void updateKeywordsSuccessTest() {
        //Given
        String keyword = "Jee"; //This must be in this format: "Xxxxx xxx xx"
        given(request.getParameter("keyword")).willReturn(keyword);
        given(keywordsSB.getKeywordByName(keyword)).willReturn(keywordsEntity);
        given(internshipEntity.getListKeywords()).willReturn(keywordsList);

        //When
        boolean result = updateDetails.updateKeywords(request, internshipEntity);

        //Then
        then(keywordsList).should().contains(keywordsEntity);
        then(keywordsList).should().add(keywordsEntity);
        then(internshipsSB).should().save(internshipEntity);
    }

    @Test
    public void redirectToDetailsPageSuccessTest() throws ServletException, IOException {
        //Given
        UUID internshipId = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        given(internshipEntity.getInternshipId()).willReturn(internshipId);
        given(internshipsSB.find(internshipId)).willReturn(internshipEntity);
        given(request.getRequestDispatcher(MISSION_PAGE)).willReturn(requestDispatcher);

        //When
        updateDetails.redirectToDetailsPage(request, response, internshipEntity, true);

        //Then
        then(request).should().setAttribute("message", SUCCESS_BD);
        then(request).should().setAttribute("internshipData", internshipEntity);
        then(request).should().setAttribute("listOfSkills", skillsSB.getSkills());
        then(request).should().setAttribute("listOfKeywords",keywordsSB.getKeywords());
        then(requestDispatcher).should().forward(request, response);
    }
}
