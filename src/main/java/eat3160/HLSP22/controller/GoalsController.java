package eat3160.HLSP22.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eat3160.HLSP22.model.GoalsEntity;
import eat3160.HLSP22.model.GoalsQuizBean;
import eat3160.HLSP22.service.GoalsService;


/**
 * This controller handles requests related to the goals of a user. 
 * @author alf-s//
 */

@RequestMapping("/goals*")
@Controller
public class GoalsController {
	
	@Autowired
	private GoalsService goalsService;
	
	@ModelAttribute("goals")
	public GoalsEntity createGoalObject() {
		return new GoalsEntity();
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
			model.addAttribute("goals",  goalsService.findByUserId((Integer)session.getAttribute("userID")));
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
			
			GoalsEntity e = goalsService.findByUserId((Integer)session.getAttribute("userID"));
			
			if(e == null) {
				e = new GoalsEntity();
				e.setWeeklyAerobicExercise(0);
				e.setWeeklyStrengthExercise(0);
			}
			
			model.addAttribute("goals", e);
			
			model.addAttribute("quiz", new GoalsQuizBean());
			
			return "editGoals";
		}		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateUserDetails(@ModelAttribute("goals") GoalsEntity goalsEntity, HttpServletResponse response, HttpSession session, Model model) 
			throws Exception {	
		
		if(session.getAttribute("loggedIn") == null || (boolean)session.getAttribute("loggedIn") == false) {
			//If the user isn't logged in, send an 403 error and return null as the 'view'. This ensures the user cannot access
			//the view if they are already logged in. 
			response.sendError(403);
			return null;
		}else {
			//goalsService.save((Integer)session.getAttribute("userID"), goalsEntity);
			goalsEntity.setUserID((Integer)session.getAttribute("userID"));
			goalsService.save(goalsEntity);
			return "redirect:/goals/view";
			
		}
		
	}

}
