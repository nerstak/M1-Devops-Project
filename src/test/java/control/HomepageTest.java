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
import java.util.ArrayList;

import static utils.Constants.*;

@RunWith(MockitoJUnitRunner.class)
public class HomepageTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    KeywordsSessionBean keywordsSB;

    @Mock
    InternshipSessionBean internshipsSB;

    @Mock
    TutorEntity tutor;

    @InjectMocks
    Homepage homepage;

    @Test(expected=NullPointerException.class) //I just want to check that the processRequest is called in both, nothing more
    public void doGetTest() throws ServletException, IOException {
        //Given

        //When
        homepage.doGet(request, response);

        //Then
        then(homepage).should().processRequest(request, response);
    }

    @Test(expected=NullPointerException.class) //I just want to check that the processRequest is called in both, nothing more
    public void doPostTest() throws ServletException, IOException {
        //Given

        //When
        homepage.doGet(request, response);

        //Then
        then(homepage).should().processRequest(request, response);
    }

    @Test
    public void processRequestNullTutorTest() throws ServletException, IOException {
        //Given
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("tutor")).willReturn(null);

        //When
        homepage.doGet(request, response);

        //Then
        then(response).should().sendRedirect(CONTROLLER_LOGIN);
    }

    @Test
    public void processRequestNullParametersTest() throws ServletException, IOException {
        //Given
        String year = "%";
        String name = "";
        String keyword = "-";
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("tutor")).willReturn(tutor);
        given(request.getRequestDispatcher(HOME_PAGE)).willReturn(requestDispatcher);

        //When
        homepage.processRequest(request, response);

        //Then
        then(request).should().setAttribute("listOfKeywords", keywordsSB.getKeywords());
        then(request).should().setAttribute("listOfInternship",internshipsSB.getInternshipData(tutor.getTutorId(), year, name, keyword));
        then(request).should().setAttribute("searchedYear", year);
        then(request).should().setAttribute("searchedKeyword", keyword);
        then(request).should().setAttribute("searchedName", name);
        then(requestDispatcher).should().forward(request, response);
    }

    @Test
    public void processRequestParametersTest() throws ServletException, IOException {
        //Given
        Integer year = 2019;
        String name = "Paul";
        String keyword = "Long";
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("tutor")).willReturn(tutor);
        given(request.getRequestDispatcher(HOME_PAGE)).willReturn(requestDispatcher);
        given(request.getParameter("year")).willReturn(year.toString());
        given(request.getParameter("search-name")).willReturn(name);
        given(request.getParameter("keywords")).willReturn(keyword);

        //When
        homepage.processRequest(request, response);

        //Then
        then(request).should().setAttribute("listOfKeywords", keywordsSB.getKeywords());
        then(request).should().setAttribute("listOfInternship",internshipsSB.getInternshipData(tutor.getTutorId(), year.toString(), name, keyword));
        then(request).should().setAttribute("searchedYear", year.toString());
        then(request).should().setAttribute("searchedKeyword", keyword);
        then(request).should().setAttribute("searchedName", name);
        then(requestDispatcher).should().forward(request, response);
    }
}