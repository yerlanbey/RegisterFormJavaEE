package hw4.webapp.javaEE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hw4.webapp.javaEE.dao.MyDAOException;
import hw4.webapp.javaEE.dao.UserDAO;
import hw4.webapp.javaEE.formbean.EmailForm;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        String jdbcDriverName = context.getInitParameter("jdbcDriverName");
        String jdbcURL = context.getInitParameter("jdbcURL");

        try {
            userDAO = new UserDAO(jdbcDriverName, jdbcURL, "users");
        } catch (MyDAOException e) {
            throw new ServletException(e);
        }
    }
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        
		try {
			EmailForm form = new EmailForm(request);
			System.out.println(form.getEmail());

            userDAO.delete(form.getEmail());

            response.sendRedirect("Users");
        } catch (MyDAOException e) {
            errors.add(e.getMessage());
            RequestDispatcher d = request.getRequestDispatcher("error.jsp");
            d.forward(request, response);
        }
	}

}
