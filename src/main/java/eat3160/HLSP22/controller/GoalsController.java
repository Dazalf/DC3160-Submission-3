package eat3160.HLSP22.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import eat3160.HLSP22.model.Goals;
import eat3160.HLSP22.model.GoalsBean;

/**
 * This controller handles requests related to the goals of a user. 
 * @author alf-s
 */

@RequestMapping("/goals*")
@Controller
public class GoalsController {
	
	@ModelAttribute("goals")
	public GoalsBean createGoalObject() {
		return new GoalsBean();
	}
	
	@RequestMapping("/view")
	public String viewGoals(HttpSession session, HttpServletResponse response, HttpServletRequest request, Model model) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			Goals goals = new Goals();
			model.addAttribute("goals",  goals.getUserGoals((Integer)session.getAttribute("userID")));
			return "viewGoals";
		}	
		
	}
	
	@RequestMapping("/edit")
	public String editGoals(HttpSession session, HttpServletResponse response, Model model) 
			throws Exception {
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			Goals goals = new Goals();
			model.addAttribute("goals",  goals.getUserGoals((Integer)session.getAttribute("userID")));
			return "editGoals";
		}		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateUserDetails(@ModelAttribute("goals") GoalsBean goalsBean, HttpServletResponse response, HttpSession session, Model model) 
			throws Exception {	
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			Goals goals = new Goals();
			goals.updateUserGoals((Integer)session.getAttribute("userID"), goalsBean); 

			return "redirect:/goals/view";
			
		}
		
	}

}
