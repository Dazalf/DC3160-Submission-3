package eat3160.HLSP22.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eat3160.HLSP22.model.UserBean;
import eat3160.HLSP22.model.UserEntity;
import eat3160.HLSP22.model.Users;
import eat3160.HLSP22.service.UserService;

/**
 * This controller handles requests related to the registration of a user. 
 * @author alf-s
 */


@Controller
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public UserEntity createUserObject() {
		return new UserEntity();
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
	public String registerUser(HttpSession session, HttpServletResponse response, Model model,  @ModelAttribute("user") UserEntity user) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			
			boolean result = userService.save(user);
			
    		if(result == true) {
 
    			session.setAttribute("SuccessfulRegistration", true);
    			response.sendRedirect("/loginform");
    			return null;
    		
    		}else {
    			model.addAttribute("errors", "Registration of account was unsuccessful. Please ensure your email isn't already in use and try again.");
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
        boolean available = userService.doesEmailExist(email);
        out.println(available);
        out.close();
        
	}
}
