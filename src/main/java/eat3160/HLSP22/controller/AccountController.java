package eat3160.HLSP22.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
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

@RequestMapping("/account*")
@Controller
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public UserEntity createUserObject() {
		return new UserEntity();
	}
	
	@RequestMapping("/view")
	public String viewUserDetails(HttpSession session, HttpServletResponse response, HttpServletRequest request, Model model) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {	
			model.addAttribute("user", userService.findById((Integer)session.getAttribute("userID")).get());
			return "viewUserProfile";
		}	
		
	}
	
	@RequestMapping("/edit")
	public String editUserDetails(HttpSession session, HttpServletResponse response, Model model) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			model.addAttribute("user", userService.findById((Integer)session.getAttribute("userID")).get());
			return "editUserProfile";
		}		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateUserDetails(@ModelAttribute("user") UserEntity user, HttpServletResponse response, HttpSession session, Model model) 
			throws Exception {	
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			user.setUserID((Integer) session.getAttribute("userID"));
			
			boolean result = userService.save(user);
			
			//2.1 In an if statement, check if the result variable is null or not. 
			if(result == true) {
				
				//2.2: If it returns null, it was successful. Redirect them to the view profile page.
				session.setAttribute("SuccessfulProfileUpdate", true);
				return "redirect:/account/view";
			
			}else {
				//2.3: If not null, the array will contain an error message which needs to be added as an attribute to the session and sent back 
	    		//	   to the edit profile page, which'll show the errors. 
				session.setAttribute("UserProfileErrors", "Updating your account was unsuccessful. Please ensure the inputted email isn't already in use and try again.");
				return "redirect:/account/edit";
			}
		}
		
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deleteAccount(HttpSession session) 
			throws Exception {

		//Call the delete users method, inputting the current users id from the sessions attribute as a parameter.
		userService.delete((Integer)session.getAttribute("userID"));
		//Once deletion is completed... end the current session with the user.
		session.invalidate();
		//send back to the website's homepage.
		return "redirect:/homepage";
        
	}
	
	@RequestMapping(value="/isEmailAvailable", method = RequestMethod.POST)
	public void isEmailAvailable(HttpSession session, HttpServletResponse response, @RequestParam String email) 
			throws Exception {

		response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int id = (Integer) session.getAttribute("userID");
        boolean available = userService.doesEmailExist(email, id);
        out.println(available);
        out.close();
        
	}
}
