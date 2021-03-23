package hw4.webapp.javaEE;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hw4.webapp.javaEE.dao.LoginDAO;
import hw4.webapp.javaEE.dao.MyDAOException;
import hw4.webapp.javaEE.datamean.User;
import hw4.webapp.javaEE.formbean.LoginForm;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private LoginDAO loginDAO;
    // Send ServletException to UserDAO
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        String jdbcDriverName = context.getInitParameter("jdbcDriverName");
        String jdbcURL = context.getInitParameter("jdbcURL");

        try {
        	loginDAO = new LoginDAO(jdbcDriverName, jdbcURL, "users");
        } catch (MyDAOException e) {
            throw new ServletException(e);
        }
    }
    // end sending ServletException 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		HttpSession session = request.getSession();
        if (session.getAttribute("sessionID") != null) {
        	response.sendRedirect("Visitorpage");
            return;
        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            LoginForm form = new LoginForm(request);
            request.setAttribute("form", form);

            if ("GET".equals(request.getMethod())) {
                RequestDispatcher d = request.getRequestDispatcher("index.jsp");
                d.forward(request, response);
                return;
            }

            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                RequestDispatcher d = request.getRequestDispatcher("index.jsp");
                d.forward(request, response);
                return;
            }

            User user = loginDAO.read(form.getEmail());
            if (user == null) {
                errors.add("Email is invalid");
                RequestDispatcher d = request.getRequestDispatcher("index.jsp");
                d.forward(request, response);
                return;
            }

            if (!form.getPassword().equals(user.getPassword())) {
                errors.add("Incorrect password");
                RequestDispatcher d = request.getRequestDispatcher("index.jsp");
                d.forward(request, response);
                return;
            }

            session.setAttribute("sessionID", user);
            response.sendRedirect("Users");

        } catch (MyDAOException e) {
            errors.add(e.getMessage());
            RequestDispatcher d = request.getRequestDispatcher("error.jsp");
            d.forward(request, response);
        }
    }
}
