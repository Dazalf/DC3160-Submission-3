package eat3160.HLSP22.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eat3160.HLSP22.model.Users;
import eat3160.HLSP22.service.StrengthExerciseService;
import eat3160.HLSP22.service.UserService;

/**
 * This controller handles requests related to the starting and ending of a session, i.e., logging in and logging out. 
 * @author alf-s
 */


@Controller
public class SessionController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/loginform")
	public String getLoginForm(HttpSession session, HttpServletResponse response) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			return "loginForm";
		}else {
			//If the user isn't logged in, send an 404 error and return null as the 'view'. This ensures the user cannot access
			//the loginForm if they are already logged in. 
			response.sendError(403);
			return null;
		}		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, HttpServletResponse response, Model model, @RequestParam String email, @RequestParam String password)
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			
			if(userService.validateUser(email, password)) {
				session.setAttribute("loggedIn", true);
				session.setAttribute("userID", userService.getUserIDByEmail(email));

				response.sendRedirect("/homepage");
    			return null;
    			
			}else {
				model.addAttribute("error", "Incorrect username or password. Please try again."); 
				return "loginForm";
			}
			
		}else {
			//If the user is already logged in, send an 403 error. 
			response.sendError(403);
			return null;
		}		
	}
		
	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) throws Exception {
		
		//If session exists, then invalidate, i.e., log out the user.  
		if(session != null) {
			session.invalidate();
		}		
		
		response.sendRedirect("/loginform");
		return null;
	}
	
	
}
