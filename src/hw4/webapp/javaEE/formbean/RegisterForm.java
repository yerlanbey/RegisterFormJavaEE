package hw4.webapp.javaEE.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class RegisterForm {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String button;
    private String confirm_password;
    
    public RegisterForm(HttpServletRequest request) {
    	first_name = request.getParameter("first_name");
    	last_name = request.getParameter("last_name");
    	email = request.getParameter("email");
        password = request.getParameter("password");
        button = request.getParameter("button");
        confirm_password = request.getParameter("confirm_password");
        
    }

    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public String getButton() {
        return button;
    }
    
    public String getConfirmPassword() {
        return confirm_password;
    }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (first_name == null || first_name.length() == 0) {
            errors.add("User Name is required");
        }
        if (last_name == null || last_name.length() == 0) {
            errors.add("Last Name is required");
        }
        if (email == null || email.length() == 0) {
            errors.add("Email is required");
        }
        if (!email.contains("@")) {
            errors.add("Not correct Email ");
        }
        if (password == null || password.length() == 0) {
            errors.add("Password is required");
        }
        
        if(!confirm_password.equals(password)) {
        	errors.add("Not match Password");
        }

        if (errors.size() > 0) {
            return errors;
        }

        
        if (first_name.matches(".*[<>\"].*")) {
            errors.add("User Name may not contain angle brackets or quotes");
        }
        if (email.matches(".*[<>\"].*")) {
            errors.add("User Name may not contain angle brackets or quotes");
        }

        return errors;
    }
}
