package eat3160.HLSP22.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eat3160.HLSP22.model.UserBean;
import eat3160.HLSP22.model.Users;

/**
 * This controller handles requests related to the registration of a user. 
 * @author alf-s
 */


@Controller
public class RegistrationController {
	
	@ModelAttribute("user")
	public UserBean createUserObject() {
		return new UserBean();
	}
	
	@RequestMapping("/signupform")
	public String getLoginForm(HttpSession session, HttpServletResponse response) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			return "signupForm";
		}else {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the loginForm if they are already logged in. 
			response.sendError(403);
			return null;
		}		
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public String registerUser(HttpSession session, HttpServletResponse response, Model model,  @ModelAttribute("user") UserBean user) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			
			Users users = new Users();
    		ArrayList<String> result = users.registerUser(user);
    		
    		if(result.isEmpty()) {
    			//2.2: If it returns null, it was successful. Redirect them to the login page so they can log in. Session attribute used
    			//instead of request attribute as we're using sendRedirect, and therefore to let the login page know the registrations been
    			//successful is through the session (as it won't be sent a request attribute in redirect). 
    			session.setAttribute("SuccessfulRegistration", true);
    			response.sendRedirect("/loginform");
    			return null;
    		}else {
    			//2.3: If not null, the array will contain an error message which needs to be added as an attribute to the request, i.e.,
    			// through the model, and return the signupForm.
    			model.addAttribute("errors", result);
    			return "signupForm";
    		}
		}else {
			//If the user is logged in, send an 403 error and return null as the 'view'. This is because only logged out users should be able to sign up. 
			response.sendError(403);
			return null;
		}		
	}
	
	@RequestMapping(value="/isEmailAvailable", method = RequestMethod.POST)
	public void isEmailAvailable(HttpSession session, HttpServletResponse response, @RequestParam String email) 
			throws Exception {

		response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        boolean available = new Users().doesEmailExist(email);
        out.println(available);
        out.close();
        
	}
}
