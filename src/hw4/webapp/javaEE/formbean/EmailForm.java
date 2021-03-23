package hw4.webapp.javaEE.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class EmailForm {
	private String  email;
    public EmailForm(HttpServletRequest request) {
    	email = request.getParameter("email");
    }
    
    public String getEmail() {
        return email;
    }
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (email == null || email.length() == 0) {
            errors.add("email is required");
            return errors;
        }


        return errors;
    }
}
