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


import hw4.webapp.javaEE.dao.UserDAO;
import hw4.webapp.javaEE.dao.MyDAOException;
import hw4.webapp.javaEE.datamean.User;
import hw4.webapp.javaEE.formbean.RegisterForm;



/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    // Send ServletException to UserDAO
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
    // end sending ServletException 
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		PrintWriter out = response.getWriter();
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
        	RegisterForm form = new RegisterForm(request);
            request.setAttribute("form", form);

            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                RequestDispatcher d = request.getRequestDispatcher("Register.jsp");
                d.forward(request, response);
                return;
            }
            User user_check = userDAO.read(form.getEmail());
            if (form.getButton().equals("Register") && user_check == null) {
                User user = new User();
                user.setFirst_name(form.getFirst_name());
                user.setLast_name(form.getLast_name());
                user.setEmail(form.getEmail());
                user.setPassword(form.getPassword());
                userDAO.create(user);
                response.sendRedirect("Visitorpage");

            }else {
                errors.add("User is already exists");
                RequestDispatcher d = request.getRequestDispatcher("Register.jsp");
                d.forward(request, response);
            }
   

        } catch (MyDAOException e) {
            errors.add(e.getMessage());
            RequestDispatcher d = request.getRequestDispatcher("error.jsp");
            d.forward(request, response);
        }
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


}
