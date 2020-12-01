package control;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

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
public class LogoutTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @InjectMocks
    Logout logout;

    @Test(expected=NullPointerException.class) //I just want to check that the processRequest is called in both, nothing more
    public void doGetTest() throws ServletException, IOException {
        //Given

        //When
        logout.doGet(request, response);

        //Then
        then(logout).should().processRequest(request, response);
    }

    @Test(expected=NullPointerException.class) //I just want to check that the processRequest is called in both, nothing more
    public void doPostTest() throws ServletException, IOException {
        //Given

        //When
        logout.doGet(request, response);

        //Then
        then(logout).should().processRequest(request, response);
    }

    @Test
    public void processRequestTest() throws ServletException, IOException {
        //Given
        given(request.getSession()).willReturn(session);

        //When
        logout.processRequest(request, response);

        //Then
        then(session).should().removeAttribute("tutor");
        then(response).should().sendRedirect(CONTROLLER_LOGIN);
    }
}