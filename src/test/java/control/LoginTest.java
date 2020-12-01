package control;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import control.session_beans.*;
import models.*;

import static org.mockito.BDDMockito.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

import static utils.Constants.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    TutorSessionBean tutorSB;

    @Mock
    TutorEntity tutor;

    @InjectMocks
    Login login;

    @Test
    public void doGetNonNullTutorTest() throws ServletException, IOException {
        //Given
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("tutor")).willReturn(tutor);

        //When
        login.doGet(request, response);

        //Then
        then(response).should().sendRedirect(CONTROLLER_HOMEPAGE);
    }

    @Test
    public void doGetNullTutorTest() throws ServletException, IOException {
        //Given
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("tutor")).willReturn(null);
        given(request.getRequestDispatcher(LOGIN_PAGE)).willReturn(requestDispatcher); //This has the double dunction of checking that this is called

        //When
        login.doGet(request, response);

        //Then
        then(requestDispatcher).should().forward(request, response);
    }

    @Test
    public void doPostNonNullTutorTest() throws ServletException, IOException {
        //Given
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("tutor")).willReturn(tutor);

        //When
        login.doPost(request, response);

        //Then
        then(response).should().sendRedirect(CONTROLLER_HOMEPAGE);
    }

    @Test
    public void doPostEmptyLoginTest() throws ServletException, IOException {
        //Given
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("tutor")).willReturn(null);
        given(request.getParameter("login")).willReturn("");
        given(request.getParameter("pwd")).willReturn("password");
        given(request.getRequestDispatcher(LOGIN_PAGE)).willReturn(requestDispatcher);

        //When
        login.doPost(request, response);

        //Then
        then(request).should().setAttribute("errorMessage", ERR_MISSING_FIELD);
        then(requestDispatcher).should().forward(request, response);
    }

    @Test
    public void doPostEmptyPwdTest() throws ServletException, IOException {
        //Given
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("tutor")).willReturn(null);
        given(request.getParameter("login")).willReturn("jean.pierre@efrei.net");
        given(request.getParameter("pwd")).willReturn("");
        given(request.getRequestDispatcher(LOGIN_PAGE)).willReturn(requestDispatcher);

        //When
        login.doPost(request, response);

        //Then
        then(request).should().setAttribute("errorMessage", ERR_MISSING_FIELD);
        then(requestDispatcher).should().forward(request, response);
    }

    @Test
    public void doPostInvalidCredentialsTest() throws ServletException, IOException {
        //Given
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("tutor")).willReturn(null);
        given(request.getParameter("login")).willReturn("jean.pierre@efrei.net");
        given(request.getParameter("pwd")).willReturn("wrongPassword");
        given(request.getRequestDispatcher(LOGIN_PAGE)).willReturn(requestDispatcher);
        ArrayList<TutorEntity> arrayList = new ArrayList();
        given(tutorSB.getTutors("jean.pierre@efrei.net", "wrongPassword")).willReturn(arrayList);

        //When
        login.doPost(request, response);

        //Then
        then(request).should().setAttribute("errorMessage", ERR_INV_CRED_MESS);
        then(requestDispatcher).should().forward(request, response);
    }

    @Test
    public void doPostValidCredentialsTest() throws ServletException, IOException {
        //Given
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("tutor")).willReturn(null);
        given(request.getParameter("login")).willReturn("jean.pierre@efrei.net");
        given(request.getParameter("pwd")).willReturn("password");
        given(request.getRequestDispatcher(LOGIN_PAGE)).willReturn(requestDispatcher);
        ArrayList<TutorEntity> arrayList = new ArrayList();
        arrayList.add(tutor);
        given(tutorSB.getTutors("jean.pierre@efrei.net", "password")).willReturn(arrayList);

        //When
        login.doPost(request, response);

        //Then
        then(session).should().setAttribute("tutor", arrayList.get(0));
        then(response).should().sendRedirect(CONTROLLER_HOMEPAGE);
    }
}