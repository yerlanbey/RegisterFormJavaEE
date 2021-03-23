package hw4.webapp.javaEE.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class LoginForm {
    private String email;
    private String password;
    private String button;

    public LoginForm(HttpServletRequest request) {

    	email = request.getParameter("email");
        password = request.getParameter("password");
        button = request.getParameter("button");

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
        
        
        public List<String> getValidationErrors() {
            List<String> errors = new ArrayList<String>();


            if (email == null || email.length() == 0) {
                errors.add("Email is required");
            }
            
            if (password == null || password.length() == 0) {
                errors.add("Password is required");
            }

            if (errors.size() > 0) {
                return errors;
            }

            
            if (email.matches(".*[<>\"].*")) {
                errors.add("Email may not contain angle brackets or quotes");
            }

            return errors;
        }
}

